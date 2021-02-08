package com.cabletvbackend.Security;

import com.cabletvbackend.JWT.JwtAuthenticationFilter;
import com.cabletvbackend.Service.SecurityUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    SecurityUserDetailService securityUserDetailService;
   @Autowired
   JwtAuthenticationFilter authenticationFilter;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityUserDetailService);
    }
//
//    @Bean
//    public JwtAuthenticationFilter getJWTAuthenticationFilter() throws Exception {
//        return new JwtAuthenticationFilter();
//    }
    @Bean
    public PasswordEncoder getPasswordEncoder()
    {
        return
                NoOpPasswordEncoder.getInstance();
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and()
//                .csrf().disable()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                .authorizeRequests().antMatchers("/controller/**").permitAll()
//                .anyRequest().authenticated();
//        http.addFilterBefore(getJWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.cors().and().csrf().disable().authorizeRequests().antMatchers("/controller/login","/controller/registartion",
                "/swagger-ui.html","/swagger-resources/**","/webjars/**")
                .permitAll().anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(authenticationFilter,UsernamePasswordAuthenticationFilter.class);


    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManager()throws Exception
    {
        return super.authenticationManagerBean();
    }

//    @Bean
//    public JwtAuthenticationFilter getJWTAuthenticationFilter() throws Exception {
//        JwtAuthenticationFilter filter = new JwtAuthenticationFilter();
//        filter.setAuthenticationManager(authenticationManager());
//        filter.setFilterProcessesUrl("/controller/login");
//        return filter;
//    }
}
