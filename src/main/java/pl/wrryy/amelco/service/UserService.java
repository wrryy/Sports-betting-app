package pl.wrryy.amelco.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.wrryy.amelco.entity.*;
import pl.wrryy.amelco.repository.RoleRepository;
import pl.wrryy.amelco.repository.UserRepository;
import pl.wrryy.amelco.repository.WalletEventRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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

    public User findByUserName(String name) {
        return userRepository.findByUserName(name);
    }

    public User findByUserNameLike(String name) {
        return userRepository.findUserByUserNameEquals(name);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void deleteUser(long id) {
        userRepository.delete(id);
    }

    public void registerUser(User user) {
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        user.setActive(true);
        user.setWalletBalance(BigDecimal.valueOf(100));
        addUser(user);
    }

    public List<User> findAll() {
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

    public void walletPlaceBetsWithCouponClosed(User user, Coupon coupon) {
        for (Bet bet : coupon.getBets()) {
            WalletEvent event = new WalletEvent();
            event.setType("Bet placed");
            event.setCreated(LocalDateTime.now());
            event.setUser(user);
            event.setValue(bet.getStake());
            walletEventRepository.save(event);
            user.setWalletBalance(user.getWalletBalance().subtract(bet.getStake()));
        }
        this.saveUser(user);
    }
    public List<User> getMessagedFriends(User user, List<Message> messages) {
        Set<User> set1 = messages.stream().map(Message::getFromUser).collect(Collectors.toSet());
        Set<User> set2 = messages.stream().map(Message::getToUser).collect(Collectors.toSet());
        set1.addAll(set2);
        List<User> messFriends = new ArrayList<>(set1);
        messFriends.remove(user);
        return messFriends;
    }

    public void toggleActive(User user) {
        user.setActive(!user.isActive());
    }

    public void friendAdd(User loggedUser, User friendToAdd) {
        List<User> friends = loggedUser.getFriends();
        if (!friends.contains(friendToAdd)) {
            friends.add(friendToAdd);
            loggedUser.setFriends(friends);
            saveUser(loggedUser);
        }
    }

    public void friendRemove(User loggedUser, User friendToRemove) {
        List<User> friends = loggedUser.getFriends();
        friends.remove(friendToRemove);
        loggedUser.setFriends(friends);
        saveUser(loggedUser);
    }

}