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
    private SportService sportService;
    private BetService betService;
    private BetCategoryService betCategoryService;

    public AdminDeleteController(UserService userService, RoleService roleService, GameService gameService, TeamService teamService,
                                 SportService sportService, BetService betService, BetCategoryService betCategoryService) {
        this.userService = userService;
        this.roleService = roleService;
        this.gameService = gameService;
        this.teamService = teamService;
        this.sportService = sportService;
        this.betService = betService;
        this.betCategoryService = betCategoryService;
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
        sportService.deleteSport(id);
        return "redirect:/admin/categories";
    }
    @GetMapping("/role")
    public String deleteRole(@RequestParam int id){
        roleService.deleteRole(id);
        return "redirect:/admin/roles";
    }
    @GetMapping("/betCat")
    public String deleteBetCat(@RequestParam long id){
        betCategoryService.deleteBetCategory(id);
        return "redirect:/admin/betCats";
    }



}
