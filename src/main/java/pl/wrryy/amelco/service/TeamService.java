package pl.wrryy.amelco.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.wrryy.amelco.entity.Sport;
import pl.wrryy.amelco.entity.Team;
import pl.wrryy.amelco.repository.TeamRepository;

import java.util.List;

@Service
public class TeamService {

    private TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team findOne(long id) {
        return teamRepository.findOne(id);
    }

    public Team findByName(String name) {
        return teamRepository.findTeamByName(name);
    }

    public void saveTeam(Team team) {
        Team inDb = teamRepository.findTeamByName(team.getName());
        if (inDb == null || inDb.getSport() != team.getSport()) {
            teamRepository.save(team);
        }
    }

    public void deleteTeam(long id) {
        teamRepository.delete(id);
    }

    public List<Team> findAll() {
        return teamRepository.findAll();
    }
    public Page<Team> findAll(Pageable pageable) { return teamRepository.findAll(pageable); }

    public List<Team> findTeamsBySport(Sport sport) {
        return teamRepository.findTeamBySport(sport);
    }

    public List<Team> findTeamsBySportName(String category) {
        return teamRepository.findTeamBySport_Name(category);
    }

}

