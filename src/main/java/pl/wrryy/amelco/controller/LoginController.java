package pl.wrryy.amelco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.wrryy.amelco.entity.User;
import pl.wrryy.amelco.service.UserService;

import javax.validation.Valid;

import static pl.wrryy.amelco.utils.MessageHelper.addSuccessAttribute;

@Controller
public class LoginController {
    private UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "user/register";
    }

    @PostMapping("/register")
    public String register(@Valid User user, BindingResult result, RedirectAttributes redirectAttrs, Model model) {
        if (result.hasErrors()) {
            return "user/register";
        } else {
            try{
                userService.registerUser(user);
            }catch (Exception e){
                model.addAttribute("message", "Error: Login/Email is already in our database.");
                return "user/register";
            }
            addSuccessAttribute(redirectAttrs, "info.success");
            return "redirect:/";
        }
    }

}
