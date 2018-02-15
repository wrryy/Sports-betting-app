package pl.wrryy.amelco.service;

import org.springframework.stereotype.Service;
import pl.wrryy.amelco.entity.Bet;
import pl.wrryy.amelco.entity.BetCategory;
import pl.wrryy.amelco.entity.Rate;
import pl.wrryy.amelco.repository.BetRepository;
import pl.wrryy.amelco.repository.RateRepository;
import pl.wrryy.amelco.repository.UserRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BetService {
    private BetRepository betRepository;
    private UserRepository userRepository;
    private RateRepository rateRepository;

    public BetService(BetRepository betRepository, UserRepository userRepository, RateRepository rateRepository) {
        this.betRepository = betRepository;
        this.userRepository = userRepository;
        this.rateRepository = rateRepository;
    }

    public void saveBet(Bet bet) {
        betRepository.save(bet);
    }

    public List<Bet> findAllByActiveIsTrue() {
        return betRepository.findAllByActiveIsTrue();
    }
    public List<Bet> findAllBetsByWonIsTrue(){
        return betRepository.findAllByWonIsTrue();
    }

    @Transactional
    public Bet setBetRate(Bet bet) {
        Rate rate = rateRepository.findFirstByBetCategoryAndGameOrderByCreatedDesc(bet.getBetCategory(), bet.getGame());
        bet.setRate(rate);
        return bet;
    }

}

