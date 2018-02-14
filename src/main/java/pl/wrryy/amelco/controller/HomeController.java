package pl.wrryy.amelco.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.wrryy.amelco.entity.*;
import pl.wrryy.amelco.service.*;

import java.util.List;

@Controller
@SessionAttributes({"coupon"})
public class HomeController {
    private UserService userService;
    private RoleService roleService;
    private GameService gameService;
    private SportService sportService;
    private BetCategoryService betCategoryService;
    private TopicService topicService;
    private DataFakerService fakerService;

    public HomeController(UserService userService, RoleService roleService, GameService gameService, SportService sportService,
                          BetCategoryService betCategoryService, TopicService topicService, DataFakerService fakerService) {
        this.userService = userService;
        this.roleService = roleService;
        this.gameService = gameService;
        this.sportService = sportService;
        this.betCategoryService = betCategoryService;
        this.topicService = topicService;
        this.fakerService = fakerService;
    }

    @ModelAttribute("sports")
    public List<Sport> getSports() { return sportService.findAll(); }
    @ModelAttribute("games")
    public List<Game> getGames() { return gameService.findAll(); }

    @ModelAttribute("bet")
    public Bet getBet() {
        Bet bet = new Bet();
        bet.setRate(new Rate());
        bet.setCoupon(new Coupon());
        return bet; }

    @ModelAttribute("betCats")
    public List<BetCategory> getBetCats() { return betCategoryService.findAll(); }
    @ModelAttribute("user")
    public User loggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedUser = userService.findByUserName(auth.getName());
        return loggedUser;
    }
    @ModelAttribute("coupon")
    public Coupon getCoupon(){
    Coupon coupon = new Coupon();
    coupon.setUser(loggedUser());
    coupon.setActive(true);
        return coupon;
    }

    @RequestMapping("/")
        public String home(Model model) {
        model.addAttribute("user", loggedUser());
        fakerService.regenerate();
            return "index";
    }

    @RequestMapping("/403")
    @ResponseBody
    public String err403() {
        return "403 :/";
    }

}
