package com.cabletvbackend.Service;

import com.cabletvbackend.constants.CableTVConstants;
import com.cabletvbackend.dao.Authorites;
import com.cabletvbackend.dao.Userdetails;
import com.cabletvbackend.password.PasswordUtils;
import com.cabletvbackend.repository.UserDetailService;
import com.cabletvbackend.serviceImpl.UserDetailServiceImpl;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.support.GenericWebApplicationContext;

import javax.annotation.ManagedBean;
import java.util.*;


@Service
public class SecurityUserDetails implements UserDetails{

    @Autowired
    UserDetailService userDetailService;

    @Autowired
    PasswordUtils passwordUtils;

    public SecurityUserDetails()
    {

    }
    private String username = "";
    private  String password = "";
    private boolean accountExp = false;
    Optional<Userdetails> userdetails = null;
    List<Authorites> authoritesList = new ArrayList<>();
    public UserDetails getCredentials( String loginName)
    {
        userdetails = userDetailService.findById(loginName);
        if(userdetails.isPresent()) {
                username = userdetails.get().getUsername();
                password = userdetails.get().getPassword();
                accountExp = true;
                return new User(username,password,authoritesList);
        }
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authoritesList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountExp;
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
