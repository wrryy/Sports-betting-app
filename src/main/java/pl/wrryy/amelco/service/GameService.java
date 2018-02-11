package pl.wrryy.amelco.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Game> findAll(Pageable pageable);
//    public Page<Game> findGamesByCategory(Sport sport);
//    public Page<Game> findGamesByCategoryName(String category);
    public List<Game> findGamesByDate(LocalDateTime date);
    public void toggleActive(Game game);

}
