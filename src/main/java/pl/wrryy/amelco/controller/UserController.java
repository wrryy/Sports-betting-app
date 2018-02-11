package pl.wrryy.amelco.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.wrryy.amelco.entity.User;
import pl.wrryy.amelco.service.UserService;
import pl.wrryy.amelco.service.WalletEventService;

import java.math.BigDecimal;

@Secured("ROLE_USER")
@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private WalletEventService walletEventService;

    public UserController(UserService userService, WalletEventService walletEventService) {
        this.userService = userService;
        this.walletEventService = walletEventService;
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
    public String userPane(Model model
//            ,@RequestParam String message
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedUser = userService.findByUserName(auth.getName());
        model.addAttribute("user", loggedUser);
        return "user/friends";
    }

    @GetMapping("/deleteFriend")
    public String deleteFriend(@RequestParam long id) {
        User friendToDelete = userService.findOne(id);
        User loggedUser = loggedUser();
        userService.friendRemove(loggedUser, friendToDelete);
        userService.saveUser(loggedUser);
        return "redirect: /user/account";
    }

    @PostMapping("/addFriend")
    public String searchFriend(@RequestParam String username, RedirectAttributes redirectAttributes) {
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
            User loggedUser = loggedUser();
            userService.friendAdd(loggedUser, newFriend);
            userService.saveUser(loggedUser);
            return "redirect:/user/friends";
        }
    }
    @PostMapping("/sendMessage")
    private String sendMessage(){
        //TODO
        return "";
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
        if (value>=200){
            value *= 1.1;
            String message = "You gave been granted with additional 10%!";
            redirectAttributes.addFlashAttribute("message", message);
        }
        userService.walletDeposit(loggedUser, BigDecimal.valueOf(value));
        return "redirect:/user/wallet";
    }
}
//    @PostMapping("/edit")
//    public String editUserPassword(BindingResult result) {
//TODO dodac @ReqParam, pozmieniac`
//        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (result.hasErrors()) {
//            return "user/edit";
//        } else {
//            userService.saveUser(user);
//            return "user/edit";
//        }
//    }

