package pl.wrryy.amelco.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.wrryy.amelco.entity.*;
import pl.wrryy.amelco.service.*;

import java.util.List;

@Controller
public class HomeController {
    private UserService userService;
    private RoleService roleService;
    private GameService gameService;
    private TeamService teamService;
    private SportService sportService;
    private BetService betService;
    private ContentService contentService;
    private TopicService topicService;

    public HomeController(UserService userService, RoleService roleService, GameService gameService, TeamService teamService,
                          SportService sportService, BetService betService, ContentService contentService, TopicService topicService) {
        this.userService = userService;
        this.roleService = roleService;
        this.gameService = gameService;
        this.teamService = teamService;
        this.sportService = sportService;
        this.betService = betService;
        this.contentService = contentService;
        this.topicService = topicService;
    }

    @ModelAttribute("roles")
    public List<Role> getRoles() {
        return roleService.findAll();
    }
    @ModelAttribute("users")
    public List<User> getUsers() {
        return userService.findAll();
    }
    @ModelAttribute("games")
    public List<Game> getGames() {
        return gameService.findAllActiveGames();
    }
    @ModelAttribute("sports")
    public List<Sport> getSports() {
        return sportService.findAll();
    }
    @ModelAttribute("teams")
    public List<Team> getTeams() {
        return teamService.findAll();

    }
    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/adduserToTopic")
    public String adduserToTopic(@RequestParam SubscriptionTopic topic, @RequestParam User user) {
        topicService.addUserToTopic(topic, user);
        return "redirect:/admin/topics";
    }

    @RequestMapping("/403")
    @ResponseBody
    public String err403() {
        return "403 bug     ";
    }

}
