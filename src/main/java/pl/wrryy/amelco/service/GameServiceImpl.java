package pl.wrryy.amelco.service;

import org.springframework.stereotype.Service;
import pl.wrryy.amelco.entity.Category;
import pl.wrryy.amelco.entity.Game;
import pl.wrryy.amelco.entity.Team;
import pl.wrryy.amelco.repository.GameRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    private GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game findOne(long id) {
        return gameRepository.findOne(id);
    }

    @Override
    public Game findByTeam(Team team) { return gameRepository.findAllByTeams(team); }

    @Override
    public void saveGame(Game game) { gameRepository.save(game); }

    @Override
    public void deleteGame(Game game) { gameRepository.delete(game);}

    @Override
    public void deleteGame(long id) {
        gameRepository.delete(id);
    }

    @Override
    public List<Game> findAll() { return gameRepository.findAll(); }

    @Override
    public List<Game> findGamesByCategory(Category category) { return gameRepository.findByCategory(category); }

    @Override
    public List<Game> findGamesByCategoryName(String category) { return gameRepository.findByCategory_Name(category); }

    @Override
    public List<Game> findGamesByDate(LocalDateTime date) { return gameRepository.findByStartedAfter(date); }

    @Override
    public void toggleActive(Game game) {
        game.setActive(!game.isActive());
        this.saveGame(game);
    }

//    @Override
//    public void scoreHomeTeam(Game game, int score) {
//        int[] result = game.getResult();
//        result[0]+=score;
//        game.setResult(result);
//        this.saveGame(game);
//    }
//
//    @Override
//    public void scoreAwayTeam(Game game, int score) {
//        int[] result = game.getResult();
//        result[0]-=score;
//        game.setResult(result);
//        this.saveGame(game);
//    }
}
