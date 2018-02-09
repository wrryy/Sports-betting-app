package pl.wrryy.amelco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wrryy.amelco.entity.*;
import pl.wrryy.amelco.service.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/add")
public class AdminAddController {
    private UserService userService;
    private RoleService roleService;
    private GameService gameService;
    private TeamService teamService;
    private SportService sportService;
    private BetService betService;
    private BetCategoryService betCategoryService;

    public AdminAddController(UserService userService, RoleService roleService, GameService gameService,
                              TeamService teamService, SportService sportService, BetService betService,
                              BetCategoryService betCategoryService) {
        this.userService = userService;
        this.roleService = roleService;
        this.gameService = gameService;
        this.teamService = teamService;
        this.sportService = sportService;
        this.betService = betService;
        this.betCategoryService = betCategoryService;
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
    @ModelAttribute("sports")
    public List<Sport> getSports() {
        return sportService.findAll();
    }
    @ModelAttribute("teams")
    public List<Team> getTeams() {
        return teamService.findAll();
    }

    @PostMapping("/user")
    public String addUser(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/users";
        } else {
            userService.saveUser(user);
            return "redirect:/admin/users";
        }
    }

    @PostMapping("/role")
    public String addRole(@Valid Role role, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/roles";
        } else {
            roleService.saveRole(role);
            return "redirect:/admin/roles";
        }
    }
    @PostMapping("/game")
    public String addGame(@Valid Game game, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/games";
        } else {
            gameService.saveGame(game);
            return "redirect:/admin/games";
        }
    }
    @PostMapping("/team")
    public String addTeam(@Valid Team team, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/teams";
        } else {
            teamService.saveTeam(team);
            return "redirect:/admin/teams";
        }
    }
    @PostMapping("/category")
    public String addCategory(@Valid Sport sport, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/categories";
        } else {
            sportService.saveSport(sport);
            return "redirect:/admin/categories";
        }
    }
    @PostMapping("/betCat")
    public String addBetCat(@Valid BetCategory betCategory, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/betCats";
        } else {
            betCategoryService.saveBetCategory(betCategory);
            return "redirect:/admin/betCats";
        }
    }
}
