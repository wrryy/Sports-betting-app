package pl.wrryy.amelco.service;

import org.springframework.stereotype.Service;
import pl.wrryy.amelco.entity.Sport;
import pl.wrryy.amelco.entity.Team;
import pl.wrryy.amelco.repository.TeamRepository;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    private TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Team findOne(long id) {
        return teamRepository.findOne(id);
    }

    @Override
    public Team findByName(String name) {
        return teamRepository.findTeamByName(name);
    }

    @Override
    public void saveTeam(Team team) {
        Team inDb = teamRepository.findTeamByName(team.getName());
        if (inDb == null || inDb.getSport() != team.getSport()) {
            teamRepository.save(team);
        }
    }

    @Override
    public void deleteTeam(Team team) {
        teamRepository.delete(team);
    }

    @Override
    public void deleteTeam(long id) {
        teamRepository.delete(id);
    }

    @Override
    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    @Override
    public List<Team> findTeamsBySport(Sport sport) {
        return teamRepository.findTeamBySport(sport);
    }

    @Override
    public List<Team> findTeamsBySportName(String category) {
        return teamRepository.findTeamBySport_Name(category);
    }

}

