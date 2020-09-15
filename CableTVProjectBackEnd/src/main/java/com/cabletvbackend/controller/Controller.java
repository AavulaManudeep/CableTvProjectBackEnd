package com.cabletvbackend.controller;

import com.cabletvbackend.JWT.JwtConstants;
import com.cabletvbackend.Service.FillterHelper;
import com.cabletvbackend.Service.SecurityUserDetails;
import com.cabletvbackend.dao.Userdetails;
import com.cabletvbackend.password.PasswordUtils;
import com.cabletvbackend.repository.UserDetailService;
import com.cabletvbackend.serviceImpl.UserDetailServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins= "*",allowedHeaders = "*")
@RestController
@RequestMapping("/controller")
public class Controller {
    //TODO:Junit Test Cases
    @Autowired
    UserDetailServiceImpl userDetailServiceImpl;
    @Autowired
    FillterHelper fillterHelper;

    private static final Logger logger = LogManager.getLogger("Controller");
    //PasswordUtils passwordUtils = new PasswordUtils();
    @PostMapping(value = "/login",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> test(@RequestBody SecurityUserDetails authentication)
    {
        if(authentication.getUsername()!=null && authentication.getPassword()!=null) {
            String password = authentication.getUsername();
            String username = authentication.getPassword();
            Userdetails login_userdetails = new Userdetails();
            login_userdetails.setPassword(password);
            login_userdetails.setUsername(username);
            boolean authenticated = userDetailServiceImpl.userAuthentication(login_userdetails);
            if(authenticated)
            {
                logger.log(Level.ALL,"ValidNames");
                logger.info("ValidNames");
                List<String> roles = null;
                roles = Arrays.asList(new String[]{"USER"});
                if(fillterHelper.getToken(username,roles)!=null) {
                    String token = JwtConstants.TOKEN_PREFIX + fillterHelper.getToken(username, roles);
                    return new ResponseEntity<>(token, HttpStatus.OK);
                }
                return new ResponseEntity<>("Internal Error",HttpStatus.INTERNAL_SERVER_ERROR);
            }
            logger.log(Level.ALL,"InvalidNames");
            //logger.log(Level.ALL,"I");
            logger.info("InvalidNames");
        }
        else {
            logger.log(Level.ALL,"InvalidNames");
            //logger.log(Level.ALL,"I");
            logger.info("InvalidNames");
        }
        return new ResponseEntity<>("Invalid user name or password", HttpStatus.UNAUTHORIZED);
    }
    @PostMapping(value = "/registartion",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registartion(@RequestBody Map<String,String> userDetails)
    {
        if(userDetails.get("username")!=null && userDetails.get("passcode")!=null && userDetails.get("confirmpassword")!=null)
        {
            String password = userDetails.get("passcode");
            String username = userDetails.get("username");
            Userdetails login_userdetails = new Userdetails();
            login_userdetails.setPassword(password);
            login_userdetails.setUsername(username);
            boolean registration = userDetailServiceImpl.userRegistration(login_userdetails);
            if(registration)
            {
                logger.info("Regration Success"+userDetails);
                return new  ResponseEntity<String>("Registerd",HttpStatus.OK);
            }

        }
        logger.info("Regration Faild"+userDetails);
        return new  ResponseEntity<String>("Fail to Reqister",HttpStatus.BAD_REQUEST);
    }


    @GetMapping("/public")
    public String publicAccess()
    {
        return "From Public";
    }


    @PostMapping("/tester")
    public String test()
    {
        return "Fun";
    }

}
