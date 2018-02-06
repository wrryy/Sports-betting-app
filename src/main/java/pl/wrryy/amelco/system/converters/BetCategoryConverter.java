package pl.wrryy.amelco.system.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.wrryy.amelco.entity.BetCategory;
import pl.wrryy.amelco.repository.BetCategoryRepository;

public class BetCategoryConverter implements Converter<String, BetCategory> {

    @Autowired
    private BetCategoryRepository betCategoryRepository;

    @Override
    public BetCategory convert(String source) {
        BetCategory betCategory = betCategoryRepository.findOne(Long.parseLong(source));
        return betCategory;
    }
}
