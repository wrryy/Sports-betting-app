package pl.wrryy.amelco.system.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.wrryy.amelco.entity.Sport;
import pl.wrryy.amelco.repository.SportRepository;

public class SportConverter implements Converter<String, Sport> {

    @Autowired
    private SportRepository sportRepository;

    @Override
    public Sport convert(String source) {
        Sport sport = sportRepository.findOne(Long.parseLong(source));
        return sport;
    }
}
