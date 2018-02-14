package pl.wrryy.amelco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wrryy.amelco.entity.Sport;
import pl.wrryy.amelco.entity.Game;
import pl.wrryy.amelco.entity.Team;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    public Game findAllByTeams(Team team);
    public List<Game> findBySport(Sport sport);
    public List<Game> findAllByEndedIsFalse();


}
