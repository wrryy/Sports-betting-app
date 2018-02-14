package pl.wrryy.amelco.system.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.wrryy.amelco.entity.Coupon;
import pl.wrryy.amelco.entity.Sport;
import pl.wrryy.amelco.repository.CouponRepository;
import pl.wrryy.amelco.repository.SportRepository;

public class CouponConverter implements Converter<String, Coupon> {

    @Autowired
    private CouponRepository repository;

    @Override
    public Coupon convert(String source) {
        Coupon coupon = repository.findOne(Long.parseLong(source));
        return coupon;
    }
}
