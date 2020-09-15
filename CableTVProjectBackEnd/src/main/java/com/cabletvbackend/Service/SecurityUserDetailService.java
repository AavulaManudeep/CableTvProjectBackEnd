package com.cabletvbackend.Service;

import com.cabletvbackend.JWT.JwtUtill;
import com.cabletvbackend.dao.Userdetails;
import com.cabletvbackend.password.PasswordUtils;
import com.cabletvbackend.repository.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;

import java.util.*;

@Service
public class SecurityUserDetailService implements UserDetailsService {

    @Autowired
    SecurityUserDetails securityUserDetails;

    public SecurityUserDetailService(PasswordUtils passwordUtils, ContextLoader contextLoader)
    {
        this.securityUserDetails = new SecurityUserDetails();
    }
    public SecurityUserDetailService()
    {
        String uuid = UUID.randomUUID().toString();
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return securityUserDetails.getCredentials(s);
    }

}
