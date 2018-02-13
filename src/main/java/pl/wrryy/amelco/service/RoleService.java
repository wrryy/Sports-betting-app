package pl.wrryy.amelco.service;

import org.springframework.stereotype.Service;
import pl.wrryy.amelco.entity.Role;
import pl.wrryy.amelco.repository.RoleRepository;

import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findOne(int id) {
        return roleRepository.findOne(id);
    }

    public void deleteRole(Role role) {
        roleRepository.delete(role);
    }

    public void deleteRole(int id) {
        roleRepository.delete(id);
    }

    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    public List<Role> findAll() {
        return roleRepository.findAll();}

    public void saveRole(Role role) {
        roleRepository.save(role);
    }
}
