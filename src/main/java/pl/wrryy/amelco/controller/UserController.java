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

import java.math.BigDecimal;

@Secured("ROLE_USER")
@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
//        model.addAttribute("message", message);
        return "user/friends";
    }

    @GetMapping("/deleteFriend")
    public String deleteFriend(@RequestParam long id) {
        User friendToDelete = userService.findOne(id);
        User loggedUser = loggedUser();
        loggedUser.getFriends().remove(friendToDelete);
        userService.saveUser(loggedUser);
//        List<User> friends = loggedUser.getFriends();
//        friends.remove(friendToDelete);
//        loggedUser.setFriends(friends);
        return "redirect/user/account";
    }

    @PostMapping("/searchFriend")
    public String searchFriend(@RequestParam String username, RedirectAttributes redirectAttributes) {
        User userToSearch = new User();
        if (username.contains("@")) {
            userToSearch = userService.findByEmail(username);
        } else {
            userToSearch = userService.findByUserName(username);
        }
        if (userToSearch == null) {
            redirectAttributes.addAttribute("message", "No such user in database");
            return "redirect:/user/friends";
        } else {
            User loggedUser = loggedUser();
            loggedUser.getFriends().add(userToSearch);
            userService.saveUser(loggedUser);
            return "redirect:/user/friends";
        }
    }

    @GetMapping("/wallet")
    public String userWallet(@ModelAttribute String message, Model model) {
        model.addAttribute(message);
        return "user/wallet";
    }

    @PostMapping("/walletWithdraw")
    public String walletWithdraw(@RequestParam BigDecimal value, RedirectAttributes redirectAttributes) {
        User loggedUser = loggedUser();
        BigDecimal userWallet = loggedUser.getWalletBalance();
        if (userWallet.compareTo(value) < 0) {
            redirectAttributes.addAttribute("message", "Value to withdraw must be equal or less than Your wallet balance.");
            return "redirect:/user/wallet";
        } else {
            userService.walletWithdraw(loggedUser, value);
            return "redirect:/user/wallet";
        }
    }

    @PostMapping("/walletDeposit")
    public String walletDeposit(@RequestParam double value) {
        User loggedUser = loggedUser();
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

