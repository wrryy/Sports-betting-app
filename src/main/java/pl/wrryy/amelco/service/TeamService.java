package pl.wrryy.amelco.service;

import pl.wrryy.amelco.entity.Category;
import pl.wrryy.amelco.entity.Team;

import java.util.List;

public interface TeamService {
    public Team findOne(long id);
    public Team findByName(String name);
    public void saveTeam(Team team);
    public void deleteTeam(Team team);
    public void deleteTeam(long id);
    public List<Team> findAll();
    public List<Team> findTeamsByCategory(Category category);
    public List<Team> findTeamsByCategoryName(String category);
}
