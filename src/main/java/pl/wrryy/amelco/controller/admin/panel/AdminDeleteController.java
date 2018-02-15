package pl.wrryy.amelco.controller.admin.panel;

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
    private BetCategoryService betCategoryService;
    private ContentService contentService;
    private TopicService topicService;

    public AdminDeleteController(UserService userService, RoleService roleService, GameService gameService, TeamService teamService,
                                 SportService sportService, BetCategoryService betCategoryService, ContentService contentService, TopicService topicService) {
        this.userService = userService;
        this.roleService = roleService;
        this.gameService = gameService;
        this.teamService = teamService;
        this.sportService = sportService;
        this.betCategoryService = betCategoryService;
        this.contentService = contentService;
        this.topicService = topicService;
    }

    @GetMapping("/user")
    public String deleteUser(@RequestParam long id){
        userService.deleteUser(id);
        return "redirect:/admin/users";
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
    @GetMapping("/content")
    public String deleteContent(@RequestParam long id){
        contentService.deleteContent(id);
        return "redirect:/admin/content";
    }
    @GetMapping("/topic")
    public String deleteTopic(@RequestParam long id){
        topicService.deleteTopic(id);
        return "redirect:/admin/topics";
    }



}
