package com.cabletvbackend.serviceImpl;

import com.cabletvbackend.constants.CableTVConstants;
import com.cabletvbackend.dao.Userdetails;
import com.cabletvbackend.password.PasswordUtils;
import com.cabletvbackend.repository.UserDetailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Level;

@Service
public class UserDetailServiceImpl {

    private static final Logger logger = LogManager.getLogger(UserDetailServiceImpl.class.getName());

    @Autowired
    UserDetailService userDetailService;

    @Autowired
    PasswordUtils passwordUtils;

    public boolean userRegistration(Userdetails userdetails)
    {
        Optional<String> hashPassword = passwordUtils.generateHashPassword(userdetails.getPassword(),CableTVConstants.SALT);
        if(hashPassword.isPresent())
        {
            userdetails.setPassword(hashPassword.get());
            userDetailService.save(userdetails);
            logger.fatal("Successfully updated the user information in to the DB");
            return true;
        }
        logger.fatal("Something went wrong with hash password");
        return false;
    }
    public boolean userAuthentication(Userdetails userdetails)
    {
        Optional<Userdetails> userinfo = userDetailService.findById(userdetails.getUsername());
        if(userinfo.isPresent())
        {
            return passwordUtils.verifypassword(userdetails.getPassword(),userinfo.get().getPassword(),CableTVConstants.SALT);
        }
        logger.fatal("Invalid user credentials");
        return false;
    }
}
