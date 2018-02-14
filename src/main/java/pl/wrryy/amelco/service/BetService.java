package pl.wrryy.amelco.service;

import org.springframework.stereotype.Service;
import pl.wrryy.amelco.entity.Bet;
import pl.wrryy.amelco.entity.Game;
import pl.wrryy.amelco.entity.User;
import pl.wrryy.amelco.repository.BetRepository;
import pl.wrryy.amelco.repository.UserRepository;

import java.util.List;

@Service
public class BetService  {
    private BetRepository betRepository;
    private UserRepository userRepository;

    public BetService(BetRepository betRepository, UserRepository userRepository) {
        this.betRepository = betRepository;
        this.userRepository = userRepository;
    }

    public void saveBet(Bet bet) { betRepository.save(bet); }

    public void deleteBet(long id) {
        betRepository.delete(id);
    }

    public List<Bet> findAll() { return betRepository.findAll(); }
    public List<Bet> findAllByActiveIsTrue() { return betRepository.findAllByActiveIsTrue(); }


}

