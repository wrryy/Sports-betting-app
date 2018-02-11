package pl.wrryy.amelco.service;

import org.springframework.data.domain.Pageable;
import pl.wrryy.amelco.entity.Role;

import java.util.List;

public interface RoleService {
    public Role findOne(int id);
    public Role findByName(String name);
    public void saveRole(Role role);
    public void deleteRole(Role role);
    public void deleteRole(int id);
    public List<Role> findAll();
    public List<Role> findAll(Pageable pageable);

}
