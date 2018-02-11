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
    private SportService sportService;
    private BetService betService;

    public AdminViewController(UserService userService, RoleService roleService, GameService gameService,
                               TeamService teamService, SportService sportService, BetService betService) {
        this.userService = userService;
        this.roleService = roleService;
        this.gameService = gameService;
        this.teamService = teamService;
        this.sportService = sportService;
        this.betService = betService;
    }

//    @ModelAttribute("roles")
//    public List<Role> getRoles(@SortDefault("id") Pageable pageable) {
//        return roleService.findAll(pageable);
//    }
//    @ModelAttribute("users")
//    public List<User> getUsers(@SortDefault("id") Pageable pageable) {
//        return userService.findAll(pageable);
//    }
//    @ModelAttribute("games")
//    public Page<Game> getGames(@SortDefault("id") Pageable pageable) {
//        return gameService.findAll(pageable);
//    }
    @ModelAttribute("sports")
    public List<Sport> getSports(@SortDefault("id") Pageable pageable) {
        return sportService.findAll(); }
//    @ModelAttribute("teams")
//    public List<Team> getTeams(@SortDefault("id") Pageable pageable) {
//        return teamService.findAll(pageable);
//    }

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
        model.addAttribute("games", gameService.findAll(pageable));
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
        model.addAttribute("sport", new Sport());
        return "admin/categories";
    }


}
