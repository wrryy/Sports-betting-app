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
    public void saveGame(Game game) {
        gameRepository.save(game);
    }
    public List<Game> findAll() {
        return gameRepository.findAll();
    }
    public List<Game> findTop20() { return gameRepository.findTop20ByOrderByStartDesc();
    }
    public List<Game> findAllActiveGames() { return gameRepository.findAllByEndedIsFalse(); }


    public Game findByTeam(Team team) {
        return gameRepository.findAllByTeams(team);
    } //TODO implement front in future
    public List<Game> findAllActiveBySport(Sport sport) {
        return gameRepository.findBySportAndStartedIsFalseAndEndedIsFalseOrderByStartedDesc(sport);}
    public List<Game> findAllPastBySport(Sport sport) {
        return gameRepository.findBySportAndStartedIsTrueAndEndedIsTrueOrderByStartedDesc(sport);}
}
