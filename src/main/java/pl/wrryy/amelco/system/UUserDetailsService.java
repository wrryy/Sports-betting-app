package pl.wrryy.amelco.system;

import pl.wrryy.amelco.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.wrryy.amelco.entity.User;
import pl.wrryy.amelco.service.UserService;

import java.util.HashSet;
import java.util.Set;

public class UUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = new User();

        if (username.contains("@")){
         user = userService.findByEmail(username);
        }else{
         user = userService.findByUserName(username);
        }

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new CurrentUser(user.getUserName(), user.getPassword(),
                grantedAuthorities, user.getId());
    }
}
