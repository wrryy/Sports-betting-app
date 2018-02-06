package pl.wrryy.amelco.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wrryy.amelco.entity.User;
import pl.wrryy.amelco.service.RoleService;
import pl.wrryy.amelco.service.UserService;
import pl.wrryy.amelco.system.CurrentUser;

import java.security.Principal;

@Secured("ROLE_USER")
@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/edit")
    public String editUser(Model model) {
        model.addAttribute("user", new User());
        return "user/edit";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute User user, BindingResult result, Principal principal, @AuthenticationPrincipal CurrentUser customUser) {
        String username = principal.getName();
        userService.findByUserName(username);
        if (result.hasErrors()) {
            return "user/edit";
        } else {
            userService.saveUser(user);
            return "user/edit";
        }
    }

}
