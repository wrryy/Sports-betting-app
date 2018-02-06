package pl.wrryy.amelco.system.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.wrryy.amelco.entity.User;
import pl.wrryy.amelco.repository.UserRepository;

public class UserConverter implements Converter<String, User> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User convert(String source) {
        User user = userRepository.findOne(Long.parseLong(source));
        return user;
    }
}
