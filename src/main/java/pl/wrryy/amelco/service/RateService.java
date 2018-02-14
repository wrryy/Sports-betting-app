package pl.wrryy.amelco.service;

import org.springframework.stereotype.Service;
import pl.wrryy.amelco.entity.Rate;
import pl.wrryy.amelco.repository.RateRepository;

@Service
public class RateService {
    private RateRepository rateRepository;

    public RateService(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }
    public void saveRate(Rate rate){ rateRepository.save(rate);}
    public Rate findOne(long id) {return rateRepository.findOne(id);}
}
