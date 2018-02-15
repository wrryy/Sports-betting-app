package pl.wrryy.amelco.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.wrryy.amelco.entity.*;
import pl.wrryy.amelco.service.*;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Secured("ROLE_USER")
@Controller
@SessionAttributes({"coupon"})
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private WalletEventService walletEventService;
    private MessageService messageService;
    private CouponService couponService;
    private TopicService topicService;
    private BetService betService;

    public UserController(UserService userService, WalletEventService walletEventService, MessageService messageService,
                          CouponService couponService, TopicService topicService, BetService betService) {
        this.userService = userService;
        this.walletEventService = walletEventService;
        this.messageService = messageService;
        this.couponService = couponService;
        this.topicService = topicService;
        this.betService = betService;
    }

    @ModelAttribute("user")
    public User loggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedUser = userService.findByUserName(auth.getName());
        return loggedUser;
    }

    @GetMapping("/account")
    public String userPanel(Model model) {
        return "user/account";
    }

    @GetMapping("/friends")
    public String friends(Model model) {
        model.addAttribute("user", loggedUser());
        return "user/friends";
    }

    @GetMapping("/deleteFriend")
    public String deleteFriend(@RequestParam long id) {
        User friendToDelete = userService.findOne(id);
        userService.friendRemove(loggedUser(), friendToDelete);
        return "redirect:/user/friends";
    }

    @PostMapping("/addFriend")
    public String searchAndAddFriend(@RequestParam String username, RedirectAttributes redirectAttributes) {
        User newFriend;
        if (username.contains("@")) {
            newFriend = userService.findByEmail(username);
        } else {
            newFriend = userService.findByUserName(username);
        }
        if (newFriend == null) {
            String message = "No such User found.";
            redirectAttributes.addFlashAttribute("message", message);
            return "redirect:/user/friends";
        } else {
            userService.friendAdd(loggedUser(), newFriend);
            return "redirect:/user/friends";
        }
    }

    @PostMapping("/sendMessage")
    public String sendMessage(@ModelAttribute Message message) {
        messageService.saveMessage(message);
        return "redirect:/user/messages";
    }

    @GetMapping("/messages")
    public String allConversations(Model model) {
        List<User> friends = userService.getMessagedFriends(loggedUser(), messageService.getMessagesByUser(loggedUser()));
        model.addAttribute("friends", friends);
        model.addAttribute("message", new Message());
        return "user/messages";
    }

    @GetMapping("/messages/{userName}")
    public String getConversation(@PathVariable String userName, Model model) {
//        User toUser = userService.findByUserName(userName);
        User toUser = userService.findByUserNameLike(userName);
        List<Message> messages = messageService.getConversationWithUser(loggedUser(), toUser);
        model.addAttribute("newMessage", new Message());
        model.addAttribute("messages", messages);
        model.addAttribute("friend", toUser);
        return "user/messages";
    }

    @GetMapping("/wallet")
    public String userWallet(Model model) {
        User loggedUser = loggedUser();
        model.addAttribute("wallet", walletEventService.findAllByUser(loggedUser));
        return "user/wallet";
    }

    @PostMapping("/walletWithdraw")
    public String walletWithdraw(@RequestParam BigDecimal value, RedirectAttributes redirectAttributes) {
        User loggedUser = loggedUser();
        BigDecimal userWallet = loggedUser.getWalletBalance();
        if (userWallet.compareTo(value) < 0) {
            String message = "Value to withdraw must be equal or less than Your wallet balance.";
            redirectAttributes.addFlashAttribute("message", message);
            return "redirect:/user/wallet";
        } else {
            userService.walletWithdraw(loggedUser, value);
            return "redirect:/user/wallet";
        }
    }

    @PostMapping("/walletDeposit")
    public String walletDeposit(@RequestParam double value, RedirectAttributes redirectAttributes) {
        User loggedUser = loggedUser();
        if (value >= 200) {
            value *= 1.1;
            String message = "You gave been granted with additional 10%!";
            redirectAttributes.addFlashAttribute("message", message);
        }
        userService.walletDeposit(loggedUser, BigDecimal.valueOf(value));
        return "redirect:/user/wallet";
    }

    @GetMapping("/bets")
    public String userBets(Model model) {
        model.addAttribute("cops", couponService.findAllByUser(loggedUser()));
        return "user/bet";
    }

    @PostMapping("/makeBet")
    public String makeBet(@RequestParam Game game, @ModelAttribute Bet bet, @ModelAttribute Coupon coupon, Model model) {
        try{
        if (bet.getStake().compareTo(loggedUser().getWalletBalance()) < 0) {
            bet.setGame(game);
            bet.setActive(true);
            coupon.setUser(loggedUser());
            bet.setCoupon(coupon);
            couponService.addBetToCoupon(coupon, bet);
            model.addAttribute("coupon", coupon);
        }}catch(NullPointerException e){}
        return "redirect:/";
    }

    @PostMapping("/closeCoupon")
    public String closeCoupon(Model model, @RequestParam boolean close, @ModelAttribute Coupon coupon) {
        try {
            coupon.setActive(close);
            coupon.setCreated(LocalDateTime.now());
            couponService.saveCoupon(coupon);
            List<Bet> bets = coupon.getBets();
            for (Bet bet : bets) {
                betService.setBetRate(bet);
                betService.saveBet(bet);
            }
            userService.walletPlaceBetsWithCouponClosed(loggedUser(), coupon);
            model.addAttribute("coupon", new Coupon());
        }catch (ConstraintViolationException e ){}
        return "redirect:/user/wallet";
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("/adduserToTopic")
    public String adduserToTopic(@RequestParam SubscriptionTopic topic, @RequestParam User user) {
        topicService.addUserToTopic(topic, user);
        return "redirect:/admin/topics";
    }

}
