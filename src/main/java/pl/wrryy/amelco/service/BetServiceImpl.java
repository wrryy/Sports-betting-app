package pl.wrryy.amelco.service;

import org.springframework.stereotype.Service;
import pl.wrryy.amelco.entity.Bet;
import pl.wrryy.amelco.entity.Game;
import pl.wrryy.amelco.entity.User;
import pl.wrryy.amelco.repository.BetRepository;
import pl.wrryy.amelco.repository.UserRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BetServiceImpl implements BetService {
    private BetRepository betRepository;
    private UserRepository userRepository;

    public BetServiceImpl(BetRepository betRepository, UserRepository userRepository) {
        this.betRepository = betRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Bet findOne(long id) {
        return betRepository.findOne(id);
    }

    @Override
    public List<Bet> findByUser(User user) { return betRepository.findAllByUsers(user); }

    @Override
    public List<Bet> findByUsersName(String name) { return betRepository.findAllByUsersUserName(name); }

    @Override
    public List<Bet> findByGame(Game game) { return betRepository.findAllByGames(game); }

    @Override
    public void saveBet(Bet bet) { betRepository.save(bet); }

    @Override
    public void betNewBet(Bet bet) throws Exception {
        BigDecimal userBalance = bet.getUsers().get(0).getWalletBalance();
        if(userBalance.compareTo(bet.getStake())==-1){
            throw new Exception();
        }
        this.saveBet(bet);
    }

    @Override
    public void deleteBet(Bet bet) { betRepository.delete(bet); }

    @Override
    public void deleteBet(long id) {
        betRepository.delete(id);
    }

    @Override
    public List<Bet> findAll() { return betRepository.findAll(); }

    @Override
    public void toggleActive(Bet bet) {
        bet.setActive(!bet.isActive());
        this.saveBet(bet);
    }
}
