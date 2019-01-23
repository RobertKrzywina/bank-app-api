package pl.robert.project.app.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pl.robert.project.app.admin.domain.AdminFacade;
import pl.robert.project.app.role.Role;
import pl.robert.project.app.user.domain.UserFacade;
import pl.robert.project.app.security.dto.AppUserDto;

import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
class UserDetailsServiceImp implements UserDetailsService {

    private AdminFacade adminFacade;
    private UserFacade userFacade;
    private AppUserDto dto;

    private Set<GrantedAuthority> authorities = new HashSet<>();

    private static final int PESEL_LENGTH = 11;

    Set<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        if (login.length() == PESEL_LENGTH) {
            dto = userFacade.getAppUser(login);
        } else {
            dto = adminFacade.getAppUser(login);
        }

        if (dto == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new org.springframework.security.core.userdetails.User(
                dto.getLogin(),
                dto.getPassword(),
                convertAuthorities(dto.getRoles()));
    }

    private Set<GrantedAuthority> convertAuthorities(Set<Role> userRoles) {
        for (Role ur : userRoles) {
            authorities.add(new SimpleGrantedAuthority(ur.getRole()));
        }
        return authorities;
    }
}
