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

    public Rate findFirstByBetCategoryAndGameOrderByCreatedDesc(BetCategory betCategory, Game game);
}
