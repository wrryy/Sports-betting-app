package pl.wrryy.amelco.service;

import org.springframework.stereotype.Service;
import pl.wrryy.amelco.entity.Bet;
import pl.wrryy.amelco.entity.Coupon;
import pl.wrryy.amelco.entity.User;
import pl.wrryy.amelco.repository.CouponRepository;

import java.util.List;

@Service
public class CouponService {
    private CouponRepository couponRepository;

    public CouponService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }
    public List<Coupon> findAllByUser(User user){ return couponRepository.findAllByUser(user);}
    public void saveCoupon(Coupon coupon){ couponRepository.save(coupon);}
    public void addBetToCoupon(Coupon coupon, Bet bet){
        List<Bet> bets = coupon.getBets();
        bets.add(bet);
        coupon.setBets(bets);
}}
