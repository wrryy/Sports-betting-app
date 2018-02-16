package pl.wrryy.amelco.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.wrryy.amelco.entity.*;
import pl.wrryy.amelco.repository.RoleRepository;
import pl.wrryy.amelco.repository.UserRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

public class UserServiceTest {
    private UserService userService;
    private  UserRepository userRepository;
    private  RoleRepository roleRepository;
    private  WalletEventService walletEventService;
    private BCryptPasswordEncoder passwordEncoder;

    @Before
    public void setUp(){
        userRepository = Mockito.mock(UserRepository.class);
        roleRepository = Mockito.mock(RoleRepository.class);
        walletEventService = Mockito.mock(WalletEventService.class);
        passwordEncoder = Mockito.mock(BCryptPasswordEncoder.class);
        userService = new UserService(userRepository, roleRepository, walletEventService, passwordEncoder);
    }

    @Test()
    public void shouldChangeUserAttributes() {
        User user = new User(); user.setFirstName("Name"); user.setLastName("Last"); user.setEmail("ad@gmail.com");user.setPassword("123");
        Role role = new Role();
        role.setName("ROLE_USER");
        String pass = "222";
        Mockito.when(roleRepository.findByName(Mockito.any())).thenReturn(role);
        Mockito.when(passwordEncoder.encode(user.getPassword())).thenReturn(pass);
        Mockito.when(userRepository.save(user)).thenReturn(user);

        userService.registerUser(user);

        Assert.assertTrue(user.getRoles().contains(role));
        Assert.assertTrue(user.isActive()==true);
        Assert.assertEquals(user.getPassword(), pass);
        Assert.assertTrue(user.getWalletBalance().equals(BigDecimal.valueOf(100)));
    }

    @Test
    public void shouldIncreaseUserWalletBalance() {
        User user = new User(); user.setFirstName("Name"); user.setLastName("Last"); user.setEmail("ad@gmail.com");
        user.setPassword("123"); user.setWalletBalance(BigDecimal.ZERO);
        WalletEvent event = new WalletEvent(); event.setUser(user); event.setValue(BigDecimal.TEN); event.setType("test");
        Mockito.when(walletEventService.createWalletEvent("test", user, BigDecimal.TEN)).thenReturn(event);

        userService.walletPay(user, BigDecimal.TEN);

        Assert.assertEquals(user.getWalletBalance(), BigDecimal.TEN);
    }

    @Test
    public void shouldEvaluateWallet() {
        boolean result = userService.hasEnoughWalletBalance(new User(), new Coupon(), new Bet());
        Assert.assertFalse(result);

        User user = new User(); user.setFirstName("Name"); user.setLastName("Last"); user.setWalletBalance(BigDecimal.ONE);
        Coupon coupon = new Coupon(); coupon.setBets(new ArrayList<>());
        Bet bet = new Bet(); bet.setStake(BigDecimal.ONE);

        boolean result1 = userService.hasEnoughWalletBalance(user, coupon, bet);
        Assert.assertTrue(result1);

        coupon.setBets(Arrays.asList(bet));
        boolean result2 = userService.hasEnoughWalletBalance(user, coupon, bet);
        Assert.assertFalse(result2);

    }
}