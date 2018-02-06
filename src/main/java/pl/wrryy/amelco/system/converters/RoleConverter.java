package pl.wrryy.amelco.system.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.wrryy.amelco.entity.Role;
import pl.wrryy.amelco.repository.RoleRepository;

public class RoleConverter implements Converter<String, Role> {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role convert(String source) {
        Role role = roleRepository.findOne(Integer.parseInt(source));
        return role;
    }
}
