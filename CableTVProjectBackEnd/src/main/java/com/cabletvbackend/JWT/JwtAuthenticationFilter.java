package com.cabletvbackend.JWT;

import com.cabletvbackend.Service.AuthProvider;
import com.cabletvbackend.Service.FillterHelper;
import com.cabletvbackend.constants.CableTVConstants;
import com.cabletvbackend.dao.Userdetails;
import com.cabletvbackend.password.PasswordUtils;
import com.cabletvbackend.repository.UserDetailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.google.common.io.CharStreams;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private FillterHelper fillterHelper;

    @Autowired
    private  JwtUtill jwtUtill;

    @Autowired
    private PasswordUtils passwordUtils;
//
//    @Autowired
//    private AuthProvider authProv;
//
//    @Autowired
//    private UserDetailService userDetailService;

    public JwtAuthenticationFilter()
    {

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

  //      Authentication authProvider = fillterHelper.extractAuth(request);
      // String  user = "test";//authProvider.getPrincipal().toString();
        List<String> roles =null;
//        if(authProvider.getAuthorities()==null || authProvider.getAuthorities().size()==0)
//            roles = Arrays.asList(new String[]{"USER"});
//        else
//            roles = authProvider.getAuthorities().stream()
//                    .map(GrantedAuthority::getAuthority)
//                    .collect(Collectors.toList());
        roles = Arrays.asList(new String[]{"USER"});
       // String token = fillterHelper.getToken(user,roles);
       // String str = CharStreams.toString(request.getReader());
      //  Userdetails userdetails = new ObjectMapper().readValue(user,Userdetails.class);
       // String username = userdetails.getUsername();
       // String pass = "";
        //Optional<String> password = passwordUtils.generateHashPassword(userdetails.getPassword(), CableTVConstants.SALT);
        //if(password.isPresent())
       //     pass =password.get();
       // UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,null,null);
        //authentication.getPrincipal();
        //authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
       // SecurityContextHolder.getContext().setAuthentication(authentication);
      //  response.addHeader(JwtConstants.TOKEN_HEADER,JwtConstants.TOKEN_PREFIX+token);HttpServletRequest request

        try {
            String token = request.getHeader("Authorization");
            if (!Strings.isNullOrEmpty(token) && token.startsWith(JwtConstants.TOKEN_PREFIX))
            {
                token = token.substring(JwtConstants.TOKEN_PREFIX.length());
                String user = jwtUtill.getUserName(token);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, null);
                authentication.getPrincipal();
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        catch (AuthenticationException ex)
        {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
        chain.doFilter(request,response);
    }

//    public JwtAuthenticationFilter(AuthenticationManager authenticationManager)
//    {
//        //this.authenticationManager = getAuthenticationManager();
//        setFilterProcessesUrl("/controller/login");
//    }

//    @SneakyThrows
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//
//      return fillterHelper.extractAuth(request);
//    }

//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        String  user = authResult.getPrincipal().toString();
//        List<String> roles =null;
//        if(authResult.getAuthorities()==null || authResult.getAuthorities().size()==0)
//            roles = Arrays.asList(new String[]{"USER"});
//        else
//         roles = authResult.getAuthorities().stream()
//                                        .map(GrantedAuthority::getAuthority)
//                                        .collect(Collectors.toList());
//        String token = fillterHelper.getToken(user,roles);
//
//        response.addHeader(JwtConstants.TOKEN_HEADER,JwtConstants.TOKEN_PREFIX+token);
//        //chain.doFilter(request,response);
//
//    }

    @SneakyThrows
    public UsernamePasswordAuthenticationToken extractAuth(HttpServletRequest request)
    {
        String str = CharStreams.toString(request.getReader());
        Userdetails userdetails = new ObjectMapper().readValue(str,Userdetails.class);
        String username = userdetails.getUsername();
        String pass = "";
        Optional<String> password = passwordUtils.generateHashPassword(userdetails.getPassword(), CableTVConstants.SALT);
        if(password.isPresent())
            pass =password.get();
        UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(username,pass,null);
//        if(authentication.authenticate(userToken))
//            return authentication;
        return userToken;
    }
}
