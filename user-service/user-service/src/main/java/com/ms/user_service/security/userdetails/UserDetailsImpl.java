package com.ms.user_service.security.userdetails;

import java.util.Collection;
import java.util.Collections;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ms.user_service.entitie.User;

import lombok.Getter;

@Getter
public class UserDetailsImpl implements UserDetails {

    private User user; 

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.emptyList();
}

    @Override
    public String getPassword() {
        return user.getPassword();
    } 

    @Override
    public String getUsername() {
        return user.getEmail();
    } 

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}