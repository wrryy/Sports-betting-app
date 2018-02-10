package pl.wrryy.amelco.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
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

    @RequestMapping("/get")
    @ResponseBody
    private JsonObject readUrl() throws Exception {
        String sURL = "https://api.crowdscores.com/v1/teams/4254";

        // Connect to the URL using java's native library
        URL url = new URL(sURL);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.setRequestProperty("x-crowdscores-api-key", "e9fc31f124b548e099fb9be219d502d7");
        request.connect();

        // Convert to a JSON object to print data
        JsonParser jp = new JsonParser(); //from gson
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
        JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object.
        String zipcode = rootobj.get("showLeagueTables").getAsString();
        return rootobj;
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

                int[] goals = new int[2];
                goals[0] = Integer.valueOf("" + match.get("homeGoals"));
                goals[1] = Integer.parseInt("" + match.get("awayGoals"));

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

                game.setResult(goals);
                game.setTeams(gameTeams);
                game.setSport(sport);
                game.setStarted(started);
                game.setActive(false);
                gameService.saveGame(game);
            }
        } catch (
                FileNotFoundException e)

        {
            return "FNF";
        } catch (
                ParseException e1)

        {
            return "PE";
        } catch (
                IOException e2)

        {
            return "IOE";
        } catch (
                Exception ee)

        {
            return ee.getMessage();
        }
        return "git";
    }
}
