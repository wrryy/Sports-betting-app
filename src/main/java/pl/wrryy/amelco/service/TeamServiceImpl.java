package pl.wrryy.amelco.service;

import org.springframework.stereotype.Service;
import pl.wrryy.amelco.entity.Category;
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
    public void saveTeam(Team team) { teamRepository.save(team); }

    @Override
    public void deleteTeam(Team team) { teamRepository.delete(team); }

    @Override
    public void deleteTeam(long id) {
        teamRepository.delete(id);
    }

    @Override
    public List<Team> findAll() {return teamRepository.findAll();}

    @Override
    public List<Team> findTeamsByCategory(Category category) { return teamRepository.findTeamByCategory(category);
    }

    @Override
    public List<Team> findTeamsByCategoryName(String category) { return teamRepository.findTeamByCategory_Name(category);}

}

