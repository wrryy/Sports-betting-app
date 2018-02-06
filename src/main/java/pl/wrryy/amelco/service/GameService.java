package pl.wrryy.amelco.service;

import pl.wrryy.amelco.entity.Sport;
import pl.wrryy.amelco.entity.Game;
import pl.wrryy.amelco.entity.Team;

import java.time.LocalDateTime;
import java.util.List;

public interface GameService {
    public Game findOne(long id);
    public Game findByTeam(Team team);
    public void saveGame(Game game);
    public void deleteGame(Game game);
    public void deleteGame(long id);
    public List<Game> findAll();
    public List<Game> findGamesByCategory(Sport sport);
    public List<Game> findGamesByCategoryName(String category);
    public List<Game> findGamesByDate(LocalDateTime date);
    public void toggleActive(Game game);
//    public void scoreHomeTeam(Game game, int score);
//    public void scoreAwayTeam(Game game, int score);

}
