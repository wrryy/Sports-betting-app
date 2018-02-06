package pl.wrryy.amelco.service;

import pl.wrryy.amelco.entity.Bet;
import pl.wrryy.amelco.entity.Game;
import pl.wrryy.amelco.entity.User;

import java.util.List;

public interface BetService {
    public Bet findOne(long id);
    public List<Bet> findByUser(User user);
    public List<Bet> findByUsersName(String name);
    public List<Bet> findByGame(Game game);
    public void saveBet(Bet bet);
    public void betNewBet(Bet bet) throws Exception;
    public void deleteBet(Bet bet);
    public void deleteBet(long id);
    public List<Bet> findAll();
    public void toggleActive(Bet bet);


}
