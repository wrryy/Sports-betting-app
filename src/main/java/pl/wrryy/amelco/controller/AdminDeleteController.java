package pl.wrryy.amelco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.wrryy.amelco.service.*;

@Controller
@RequestMapping("/admin/delete")
public class AdminDeleteController {
    private UserService userService;
    private RoleService roleService;
    private GameService gameService;
    private TeamService teamService;
    private CategoryService categoryService;
    private BetService betService;

    public AdminDeleteController(UserService userService, RoleService roleService, GameService gameService, TeamService teamService, CategoryService categoryService, BetService betService) {
        this.userService = userService;
        this.roleService = roleService;
        this.gameService = gameService;
        this.teamService = teamService;
        this.categoryService = categoryService;
        this.betService = betService;
    }

    @GetMapping("/user")
    public String deleteUser(@RequestParam long id){
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
    @GetMapping("/game")
    public String deleteGame(@RequestParam long id){
        gameService.deleteGame(id);
        return "redirect:/admin/games";
    }
    @GetMapping("/bet")
    public String deleteBet(@RequestParam long id){
        betService.deleteBet(id);
        return "redirect:/admin/bets";
    }
    @GetMapping("/team")
    public String deleteTeam(@RequestParam long id){
        teamService.deleteTeam(id);
        return "redirect:/admin/teams";
    }
    @GetMapping("/category")
    public String deleteCategory(@RequestParam long id){
        categoryService.deleteCategory(id);
        return "redirect:/admin/categories";
    }
    @GetMapping("/role")
    public String deleteRole(@RequestParam int id){
        roleService.deleteRole(id);
        return "redirect:/admin/roles";
    }



}
