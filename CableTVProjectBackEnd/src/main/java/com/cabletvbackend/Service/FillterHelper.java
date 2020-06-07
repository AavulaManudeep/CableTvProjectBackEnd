package com.cabletvbackend.Service;

import com.cabletvbackend.JWT.JwtUtill;
import com.cabletvbackend.constants.CableTVConstants;
import com.cabletvbackend.dao.Userdetails;
import com.cabletvbackend.password.PasswordUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.CharStreams;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class FillterHelper {

    @Autowired
    JwtUtill jwtUtill;

    @Autowired
    PasswordUtils passwordUtils;

    @Autowired
    AuthProvider authentication;

    @Autowired
    SecurityUserDetailService securityUserDetailService;


//    public FillterHelper()
//    {
//        this.jwtUtill = new JwtUtill();
//        this.passwordUtils= new PasswordUtils();
//        this.contextLoader = new ContextLoader();
//        this.securityUserDetailService = new SecurityUserDetailService(passwordUtils,contextLoader);
//        this.authentication = new AuthProvider(this.securityUserDetailService);
//    }

    @SneakyThrows
    public Authentication extractAuth(HttpServletRequest request)
    {
        String str = CharStreams.toString(request.getReader());
        Userdetails userdetails = new ObjectMapper().readValue(str,Userdetails.class);
        String username = userdetails.getUsername();
        String pass = "";
        Optional<String> password = passwordUtils.generateHashPassword(userdetails.getPassword(), CableTVConstants.SALT);
        if(password.isPresent())
            pass =password.get();
        UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(username, password);
        if(authentication.authenticate(userToken))
            return authentication;
        return new AuthProvider(this.securityUserDetailService);
    }

    public String getToken(String token, List<String> roles)
    {
        return jwtUtill.generateToken(token,roles);
    }
}
