package pl.wrryy.amelco.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.wrryy.amelco.service.UserService;

@Controller
public class HomeController {

    private UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping("/")
    public String home() {
        return "index";
    }
    @RequestMapping("/403")
    @ResponseBody
    public String err403() {
        return "403 bug     ";
    }
    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }

}
