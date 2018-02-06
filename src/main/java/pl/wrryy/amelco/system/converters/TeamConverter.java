package pl.wrryy.amelco.system.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.wrryy.amelco.entity.Team;
import pl.wrryy.amelco.repository.TeamRepository;

public class TeamConverter implements Converter<String, Team> {

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public Team convert(String source) {
        Team team = teamRepository.findOne(Long.parseLong(source));
        return team;
    }
}
