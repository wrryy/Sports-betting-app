package pl.wrryy.amelco.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.wrryy.amelco.entity.Game;
import pl.wrryy.amelco.entity.Sport;
import pl.wrryy.amelco.entity.Team;
import pl.wrryy.amelco.repository.GameRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GameService {
    private GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game findOne(long id) {
        return gameRepository.findOne(id);
    }

    public Game findByTeam(Team team) { return gameRepository.findAllByTeams(team); }

    public void saveGame(Game game) { gameRepository.save(game); }

    public void deleteGame(Game game) { gameRepository.delete(game);}

    public void deleteGame(long id) {
        gameRepository.delete(id);
    }

    public List<Game> findAll() { return gameRepository.findAll(); }
    public Page<Game> findAll(Pageable pageable) { return gameRepository.findAll(pageable); }

    public List<Game> findGamesByCategory(Sport sport) { return gameRepository.findBySport(sport); }

    public List<Game> findGamesByCategoryName(String category) { return gameRepository.findBySport_Name(category); }

    public List<Game> findGamesByDate(LocalDateTime date) { return gameRepository.findByStartedAfter(date); }

    public void toggleActive(Game game) {
        game.setActive(!game.isActive());
        this.saveGame(game);
    }

//    public void scoreHomeTeam(Game game, int score) {
//        int[] result = game.getResult();
//        result[0]+=score;
//        game.setResult(result);
//        this.saveGame(game);
//    }
//
//    public void scoreAwayTeam(Game game, int score) {
//        int[] result = game.getResult();
//        result[0]-=score;
//        game.setResult(result);
//        this.saveGame(game);
//    }
}
