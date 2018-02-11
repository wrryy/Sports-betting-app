package pl.wrryy.amelco.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.wrryy.amelco.entity.Role;
import pl.wrryy.amelco.repository.RoleRepository;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findOne(int id) {
        return roleRepository.findOne(id);
    }

    @Override
    public void deleteRole(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public void deleteRole(int id) {
        roleRepository.delete(id);
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();}
    @Override
    public List<Role> findAll(Pageable pageable) {
        return roleRepository.findAll();}

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }
}
