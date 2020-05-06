package com.cabletvbackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@CrossOrigin(origins= "*",allowedHeaders = "*")
@RestController
@RequestMapping("/controller")
public class Controller {
    public static final Logger logger = Logger.getLogger(Controller.class.getName());
    @PostMapping(value = "/test",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> test(@RequestBody Map<String,String> userdetails)
    {
        if(userdetails.get("username").equalsIgnoreCase("Manu") && userdetails.get("passcode").equalsIgnoreCase("1234")) {
            logger.log(Level.ALL,"ValidNames");
            logger.info("ValidNames");
            return new ResponseEntity<>("Sucess", HttpStatus.OK);
        }
        else {
            logger.log(Level.ALL,"InvalidNames");
            logger.info("InvalidNames");
            return new ResponseEntity<>("Invalid user name or password", HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping(value = "/registartion",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registartion(@RequestBody Map<String,String> userDetails)
    {
        if(userDetails.get("username")!=null && userDetails.get("passcode")!=null && userDetails.get("confirmpassword")!=null)
        {
            logger.info("Regration Success"+userDetails);
            return new  ResponseEntity<String>("Registerd",HttpStatus.OK);
        }
        logger.info("Regration Faild"+userDetails);
        return new  ResponseEntity<String>("Fail to Reqister",HttpStatus.BAD_REQUEST);
    }
}