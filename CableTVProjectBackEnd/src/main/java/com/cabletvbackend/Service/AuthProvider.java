package com.cabletvbackend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Component
public class AuthProvider implements Authentication {

    UsernamePasswordAuthenticationToken user;

    @Autowired
    SecurityUserDetailService securityUserDetailService;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return null;
    }

    public AuthProvider()
    {

    }

    public AuthProvider(SecurityUserDetailService securityUserDetailService) {
        //this.securityUserDetailService = securityUserDetailService;
    }
    public AuthProvider(UsernamePasswordAuthenticationToken user) {
        //this.securityUserDetailService = new SecurityUserDetailService();
        this.user = user;
        authenticate(user);
    }

    @Override
    public Object getCredentials() {
        return this.user.getCredentials();
    }

    @Override
    public Object getDetails() {
        return this.user.getDetails();
    }

    @Override
    public Object getPrincipal() {
        return this.user.getPrincipal();
    }

    @Override
    public boolean isAuthenticated() {
        return this.user.isAuthenticated();
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
        this.user.setAuthenticated(true);
    }

    @Override
    public String getName() {
        return user.getName();
    }
    public boolean authenticate(UsernamePasswordAuthenticationToken user)
    {
        UserDetails userDetails = securityUserDetailService.loadUserByUsername(user.getPrincipal().toString());
        String pass = userDetails.getPassword();
        pass.equals(user.getCredentials().toString());
        String cred = user.getCredentials().toString().replace("Optional[","").replace("]","");
        this.user = user;
        if(cred.equals(pass)) {
            return true;
        }
        return false;
    }

}
