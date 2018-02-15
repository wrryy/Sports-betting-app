package pl.wrryy.amelco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wrryy.amelco.entity.BetCategory;
import pl.wrryy.amelco.entity.Game;
import pl.wrryy.amelco.entity.Rate;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long>{

    public Rate findFirstByBetCategory_IdAndGame_IdAndCreatedBetween(long betCat_id, long game_id, LocalDateTime from, LocalDateTime to);
    public List<Rate> findTop5ByCreatedBefore(LocalDateTime now);
}
