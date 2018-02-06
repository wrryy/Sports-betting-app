package pl.wrryy.amelco.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    private CategoryService categoryService;
    private BetService betService;

    public AdminViewController(UserService userService, RoleService roleService, GameService gameService,
                               TeamService teamService, CategoryService categoryService, BetService betService) {
        this.userService = userService;
        this.roleService = roleService;
        this.gameService = gameService;
        this.teamService = teamService;
        this.categoryService = categoryService;
        this.betService = betService;
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
        return gameService.findAll();
    }
    @ModelAttribute("categories")
    public List<Sport> getCategories() {
        return categoryService.findAll();
    }
    @ModelAttribute("teams")
    public List<Team> getTeams() {
        return teamService.findAll();
    }
    @ModelAttribute("bets")
    public List<Bet> getBets() {
        
        return betService.findAll();
    }

    @RequestMapping("/users")
    public String listUsers(ModelMap model, @SortDefault("id") Pageable pageable) {
        model.addAttribute("user", new User());
        return "admin/users";
    }

    @RequestMapping("/roles")
    public String listRoles(ModelMap model, @SortDefault("id") Pageable pageable) {
        model.addAttribute("role", new Role());
        return "admin/roles";
    }

    @RequestMapping("/games")
    public String listGames(ModelMap model, @SortDefault("id") Pageable pageable) {
        model.addAttribute("game", new Game());
        return "admin/games";
    }

    @RequestMapping("/teams")
    public String listTeams(ModelMap model, @SortDefault("id") Pageable pageable) {
        model.addAttribute("team", new Team());
        return "admin/teams";
    }

    @RequestMapping("/categories")
    public String listCategories(ModelMap model, @SortDefault("id") Pageable pageable) {
        model.addAttribute("category", new Sport());
        return "admin/categories";
    }
    @RequestMapping("/bets")
    public String listBets(ModelMap model, @SortDefault("id") Pageable pageable) {
//        model.addAttribute("bet", new Bet());
        return "admin/bets";
    }


}
