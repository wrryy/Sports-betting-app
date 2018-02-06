package pl.wrryy.amelco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wrryy.amelco.entity.Category;
import pl.wrryy.amelco.entity.Team;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    public Team findTeamByName(String name);
    public List<Team> findTeamByCategory(Category category);
    public List<Team> findTeamByCategory_Name(String categoryName);
}
