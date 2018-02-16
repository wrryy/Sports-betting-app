package pl.wrryy.amelco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wrryy.amelco.entity.Bet;
import pl.wrryy.amelco.entity.Game;

import java.util.List;

@Repository
public interface BetRepository extends JpaRepository<Bet, Long> {
public List<Bet> findAllByActiveIsTrue();
public List<Bet> findAllByWonIsTrueAndPaidIsFalse();

}
