package pl.robert.project.core.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pl.robert.project.app.admin.domain.Admin;
import pl.robert.project.app.admin.domain.AdminRepository;
import pl.robert.project.app.role.Role;

import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
@Getter
public class UserDetailsServiceImp implements UserDetailsService {

    private AdminRepository adminRepository;

    private Set<GrantedAuthority> authorities = new HashSet<>();

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Admin appUser = adminRepository.findByLogin(login);

        if (appUser == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new org.springframework.security.core.userdetails.User(
                        appUser.getLogin(),
                        appUser.getPassword(),
                        convertAuthorities(appUser.getRoles()));
    }

    private Set<GrantedAuthority> convertAuthorities(Set<Role> userRoles) {
        for(Role ur: userRoles) {
            authorities.add(new SimpleGrantedAuthority(ur.getRole()));
        }
        return authorities;
    }
}
