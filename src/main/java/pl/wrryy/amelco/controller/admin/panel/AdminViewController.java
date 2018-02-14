package pl.wrryy.amelco.controller.admin.panel;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wrryy.amelco.entity.*;
import pl.wrryy.amelco.service.*;

import java.util.List;

@Secured("ROLE_ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminViewController {
    private UserService userService;
    private RoleService roleService;
    private GameService gameService;
    private TeamService teamService;
    private SportService sportService;
    private BetService betService;
    private ContentService contentService;
    private TopicService topicService;

    public AdminViewController(UserService userService, RoleService roleService, GameService gameService, TeamService teamService,
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

    @ModelAttribute("users")
    public List<User> getUsers() {
        return userService.findAll();
    }
    @ModelAttribute("roles")
    public List<Role> getRoles() {
        return roleService.findAll();
    }
    @ModelAttribute("games")
    public List<Game> getGames() { return gameService.findAll(); }
    @ModelAttribute("sports")
    public List<Sport> getSports() { return sportService.findAll(); }
    @ModelAttribute("teams")
    public List<Team> getTeams() { return teamService.findAll(); }
    @ModelAttribute("topics")
    public List<SubscriptionTopic> getTopics() { return topicService.findAll(); }

    @RequestMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("user", new User());
        return "admin/users";
    }

    @RequestMapping("/roles")
    public String listRoles(Model model) {
        model.addAttribute("role", new Role());
        model.addAttribute("roles",  roleService.findAll());
        return "admin/roles";
    }

    @RequestMapping("/games")
    public String listGames(Model model) {
        model.addAttribute("game", new Game());
        return "admin/games";
    }

    @RequestMapping("/teams")
    public String listTeams(Model model) {
        model.addAttribute("team", new Team());
        return "admin/teams";
    }

    @RequestMapping("/categories")
    public String listCategories(Model model) {
        model.addAttribute("sport", new Sport());
        return "admin/categories";
    }
    @RequestMapping("/content")
    public String listSubContent(Model model) {
        model.addAttribute("subscriptionContent", new SubscriptionContent());
        model.addAttribute("contents", contentService.findAll());
        model.addAttribute("topics", topicService.findAll());
        return "admin/content";
    }
    @RequestMapping("/topics")
    public String listTopics(Model model) {
        model.addAttribute("subscriptionTopic", new SubscriptionTopic());
        return "admin/topics";
    }


}
