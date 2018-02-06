package pl.wrryy.amelco.service;

import pl.wrryy.amelco.entity.Sport;
import pl.wrryy.amelco.entity.Team;

import java.util.List;

public interface TeamService {
    public Team findOne(long id);
    public Team findByName(String name);
    public void saveTeam(Team team);
    public void deleteTeam(Team team);
    public void deleteTeam(long id);
    public List<Team> findAll();
    public List<Team> findTeamsBySport(Sport sport);
    public List<Team> findTeamsBySportName(String category);
}
