package pl.wrryy.amelco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wrryy.amelco.entity.Sport;

@Repository
public interface SportRepository extends JpaRepository<Sport, Long>{
    public Sport findByName(String name);
}
