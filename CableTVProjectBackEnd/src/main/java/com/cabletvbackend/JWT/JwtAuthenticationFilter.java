package com.cabletvbackend.JWT;

import com.cabletvbackend.Service.AuthProvider;
import com.cabletvbackend.Service.FillterHelper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private FillterHelper fillterHelper;

    public JwtAuthenticationFilter()
    {

    }

//    public JwtAuthenticationFilter(AuthenticationManager authenticationManager)
//    {
//        //this.authenticationManager = getAuthenticationManager();
//        setFilterProcessesUrl("/controller/login");
//    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

      return fillterHelper.extractAuth(request);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String  user = authResult.getPrincipal().toString();
        List<String> roles =null;
        if(authResult.getAuthorities()==null || authResult.getAuthorities().size()==0)
            roles = Arrays.asList(new String[]{"USER"});
        else
         roles = authResult.getAuthorities().stream()
                                        .map(GrantedAuthority::getAuthority)
                                        .collect(Collectors.toList());
        String token = fillterHelper.getToken(user,roles);

        response.addHeader(JwtConstants.TOKEN_HEADER,JwtConstants.TOKEN_PREFIX+token);
    }
}
