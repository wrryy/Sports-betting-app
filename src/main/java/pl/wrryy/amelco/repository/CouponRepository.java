package pl.wrryy.amelco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wrryy.amelco.entity.Coupon;
import pl.wrryy.amelco.entity.User;

import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long>{
    public List<Coupon> findAllByUser(User user);
}
