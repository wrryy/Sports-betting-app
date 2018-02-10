package pl.wrryy.amelco.service;

import pl.wrryy.amelco.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {
    public User findOne(long id);
    public User findByUserName(String name);
    public User findByEmail(String email);
    public void saveUser(User user);
    public void registerUser(User user);
    public void deleteUser(User user);
    public void deleteUser(long id);
    public List<User> findAll();
    public void walletWithdraw(User user, BigDecimal amount);
    public void walletDeposit(User user, BigDecimal amount);
    public void walletAddHistoryEvent(User user, String event);
    public void toggleActive(User user);
    public void friendAdd(User user);
    public void friendRemove(User user);
}
