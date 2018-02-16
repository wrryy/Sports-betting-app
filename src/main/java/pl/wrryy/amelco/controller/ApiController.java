package pl.wrryy.amelco.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.wrryy.amelco.entity.Game;
import pl.wrryy.amelco.entity.Sport;
import pl.wrryy.amelco.entity.Team;
import pl.wrryy.amelco.service.GameService;
import pl.wrryy.amelco.service.SportService;
import pl.wrryy.amelco.service.TeamService;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ApiController {
    private TeamService teamService;
    private GameService gameService;
    private SportService sportService;


    public ApiController(TeamService teamService, GameService gameService, SportService sportService) {
        this.teamService = teamService;
        this.gameService = gameService;
        this.sportService = sportService;
    }

    @RequestMapping("/parse")
    @ResponseBody
    private String parse() {
        JSONParser parser = new JSONParser();
        try {
            JSONArray a = (JSONArray) parser.parse(new FileReader("C:\\Users\\wryy\\Downloads\\data.txt"));

            for (Object o : a) {
                Game game = new Game();
                JSONObject match = (JSONObject) o;

                game.setScoreHome(Integer.valueOf("" + match.get("homeGoals")));
                game.setScoreAway(Integer.parseInt("" + match.get("awayGoals")));

                Sport sport = sportService.findOne(1);
                List<Team> gameTeams = new ArrayList<>();
                JSONObject homeTeam = (JSONObject) match.get("homeTeam");
                Team team1 = new Team();
                team1.setName(homeTeam.get("name") + "");
                team1.setSport(sport);
                gameTeams.add(team1);
                teamService.saveTeam(team1);

                JSONObject awayTeam = (JSONObject) match.get("awayTeam");
                Team team2 = new Team();
                team2.setName(awayTeam.get("name") + "");
                team2.setSport(sport);
                gameTeams.add(team2);
                teamService.saveTeam(team2);
                Long epochStart = (long) match.get("start");
                LocalDateTime started = LocalDateTime.ofInstant(Instant.ofEpochMilli(epochStart), ZoneId.systemDefault());

//                game.setResult(goals);
                game.setTeams(gameTeams);
                game.setSport(sport);
                game.setStart(started);
                game.setStarted(true);
                game.setEnded(true);
                gameService.saveGame(game);
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e1) {
        } catch (ParseException e2) {
        }
        return "git";
    }

    @GetMapping("/0/{id}")
    public String endGameHome(@PathVariable long id) {
        Game game = gameService.findOne(id);
        game.setEnded(true);
        game.setScoreAway(0);
        game.setScoreHome(1);
        gameService.saveGame(game);
        return "redirect:/";
    }

    @GetMapping("/1/{id}")
    public String endGameAway(@PathVariable long id) {
        Game game = gameService.findOne(id);
        game.setEnded(true);
        game.setScoreAway(1);
        game.setScoreHome(0);
        gameService.saveGame(game);
        return "redirect:/";
    }

    @GetMapping("/reset")
    public String reset() {
        for (long i = 428; i < 431; i++) {
            Game game = gameService.findOne(i);
            game.setEnded(false);
            gameService.saveGame(game);
        }

        return "redirect:/";
    }
}
