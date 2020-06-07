package com.cabletvbackend.controller;

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
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins= "*",allowedHeaders = "*")
@RestController
@RequestMapping("/controller")
public class Controller {
    //TODO:Junit Test Cases
    @Autowired
    UserDetailServiceImpl userDetailServiceImpl;
    private static final Logger logger = LogManager.getLogger("Controller");
    PasswordUtils passwordUtils = new PasswordUtils();
    @PostMapping(value = "/login",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> test(@RequestBody Map<String,String> userdetails)
    {
        if(userdetails.get("username")!=null && userdetails.get("password")!=null) {
            logger.log(Level.ALL,"ValidNames");
            logger.info("ValidNames");
            String password = userdetails.get("password");
            String username = userdetails.get("username");
            Userdetails login_userdetails = new Userdetails();
            login_userdetails.setPassword(password);
            login_userdetails.setUsername(username);
            boolean authenticated = userDetailServiceImpl.userAuthentication(login_userdetails);
            if(authenticated)
            {
                return new ResponseEntity<>("Success", HttpStatus.OK);
            }
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

}
