package ar.edu.unq.desapp.grupoD.backenddesapptp.security;

import ar.edu.unq.desapp.grupoD.backenddesapptp.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserSecurity implements UserDetails {

    private User user;
    private List<GrantedAuthority> auths;

    public UserSecurity(User user, List<GrantedAuthority> auths){
        this.user = user;
        this.auths = auths;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUser();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
