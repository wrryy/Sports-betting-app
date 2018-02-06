package pl.wrryy.amelco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wrryy.amelco.entity.Sport;
import pl.wrryy.amelco.entity.Team;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    public Team findTeamByName(String name);
    public List<Team> findTeamBySport(Sport sport);
    public List<Team> findTeamBySport_Name(String sport);
}
