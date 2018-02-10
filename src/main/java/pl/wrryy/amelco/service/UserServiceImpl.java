package pl.wrryy.amelco.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.wrryy.amelco.entity.User;
import pl.wrryy.amelco.entity.Role;
import pl.wrryy.amelco.repository.RoleRepository;
import pl.wrryy.amelco.repository.UserRepository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User findOne(long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User findByUserName(String name) { return userRepository.findByUserName(name);}

    @Override
    public User findByEmail(String email) { return userRepository.findByEmail(email);}

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.delete(id);
    }

    @Override
    public void deleteUser(User user) { userRepository.delete(user); }

    @Override
    public void registerUser(User user) {
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        user.setWalletBalance(BigDecimal.ZERO);
        this.saveUser(user);
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    public void walletWithdraw(User user, BigDecimal amount) {
        user.setWalletBalance(user.getWalletBalance().subtract(amount));
        String event = "Withdrew " + amount.toString()+ " .";
        walletAddHistoryEvent(user, event);
        this.saveUser(user);
    }

    @Override
    public void walletDeposit(User user, BigDecimal amount) {
        user.setWalletBalance(user.getWalletBalance().add(amount));
        String event = "Deposited " + amount.toString()+ " .";
        walletAddHistoryEvent(user, event);
        this.saveUser(user);
    }

    @Override
    public void walletAddHistoryEvent(User user, String event) {
        List<String> walletHistory = user.getWalletHistory();
        walletHistory.add(event);
    }

    @Override
    public void toggleActive(User user) {
        user.setActive(!user.isActive());
    }

    @Override
    public void friendAdd(User user) {
//        User loggedUser = this.getLoggedUser();
//        List<User> friends = loggedUser.getFriends();
//        friends.add(user);
//        loggedUser.setFriends(friends);
    }

    @Override
    public void friendRemove(User user) {
//        User loggedUser = this.getLoggedUser();
//        List<User> friends = loggedUser.getFriends();
//        friends.remove(user);
//        loggedUser.setFriends(friends);
    }
}
