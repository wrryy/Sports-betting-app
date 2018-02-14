package pl.wrryy.amelco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wrryy.amelco.entity.Rate;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long>{

}
