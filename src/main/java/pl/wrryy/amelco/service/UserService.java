package pl.wrryy.amelco.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.wrryy.amelco.entity.User;
import pl.wrryy.amelco.entity.Role;
import pl.wrryy.amelco.entity.WalletEvent;
import pl.wrryy.amelco.repository.RoleRepository;
import pl.wrryy.amelco.repository.UserRepository;
import pl.wrryy.amelco.repository.WalletEventRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final WalletEventRepository walletEventRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, WalletEventRepository walletEventRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.walletEventRepository = walletEventRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findOne(long id) {
        return userRepository.findOne(id);
    }

    public User findByUserName(String name) { return userRepository.findByUserName(name);}

    public User findByEmail(String email) { return userRepository.findByEmail(email);}

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(long id) {
        userRepository.delete(id);
    }

    public void deleteUser(User user) { userRepository.delete(user); }

    public void registerUser(User user) {
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        user.setWalletBalance(BigDecimal.valueOf(100));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        this.saveUser(user);
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }

    public void walletWithdraw(User user, BigDecimal value) {
        WalletEvent event = new WalletEvent();
        event.setType("Withdrawal");
        event.setCreated(LocalDateTime.now());
        event.setUser(user);
        event.setValue(value);

        walletEventRepository.save(event);
        user.setWalletBalance(user.getWalletBalance().subtract(value));
        this.saveUser(user);
    }

    public void walletDeposit(User user, BigDecimal value) {
        WalletEvent event = new WalletEvent();
        event.setType("Deposit");
        event.setCreated(LocalDateTime.now());
        event.setUser(user);
        event.setValue(value);

        walletEventRepository.save(event);
        user.setWalletBalance(user.getWalletBalance().add(value));
        this.saveUser(user);
    }

    public void toggleActive(User user) {
        user.setActive(!user.isActive());
    }

    public void friendAdd(User loggedUser, User friendToAdd) {
        List<User> friends = loggedUser.getFriends();
        friends.add(friendToAdd);
        loggedUser.setFriends(friends);
    }

    public void friendRemove(User loggedUser, User friendToRemove) {
        List<User> friends = loggedUser.getFriends();
        friends.remove(friendToRemove);
        loggedUser.setFriends(friends);
    }

}