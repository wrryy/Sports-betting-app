package pl.wrryy.amelco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.wrryy.amelco.entity.User;
import pl.wrryy.amelco.repository.RoleRepository;
import pl.wrryy.amelco.service.UserService;

import javax.validation.Valid;

import static pl.wrryy.amelco.utils.MessageHelper.addSuccessAttribute;

@Controller
public class LoginController {
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;

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
    public String register(@Valid User user, BindingResult result, RedirectAttributes redirectAttrs) {
        if (result.hasErrors()) {
            return "user/register";
        } else {
            userService.registerUser(user);
            addSuccessAttribute(redirectAttrs, "info.success");
            return "index";
        }
    }
//    @RequestMapping("/adduser")
//    @ResponseBody
//    public String addAdmin(){
//        User user = new User();
//        user.setPassword("admin");
//        user.setUserName("admin");
//        user.setEmail("admin@email.com");
//        user.setActive(true);
//        Role userRole = roleRepository.findByName("ROLE_ADMIN");
//        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
//        userService.saveUser(user);
//        return "git";
//    }

}
