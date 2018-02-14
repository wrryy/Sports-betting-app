package pl.wrryy.amelco.system.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.wrryy.amelco.entity.Rate;
import pl.wrryy.amelco.entity.Role;
import pl.wrryy.amelco.repository.RateRepository;
import pl.wrryy.amelco.repository.RoleRepository;

public class RateConverter implements Converter<String, Rate> {

    @Autowired
    private RateRepository rateRepository;

    @Override
    public Rate convert(String source) {
        Rate rate = rateRepository.findOne(Long.parseLong(source));
        return rate;
    }
}
