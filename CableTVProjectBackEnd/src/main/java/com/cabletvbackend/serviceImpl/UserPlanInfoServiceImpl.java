package com.cabletvbackend.serviceImpl;

import com.cabletvbackend.dao.UserPlanInfo;
import com.cabletvbackend.repository.UserPlanInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
@Service
public class UserPlanInfoServiceImpl {

    public static final  Logger logger =  Logger.getLogger(UserPlanInfoServiceImpl.class.getName());

    @Autowired
    UserPlanInfoService userPlanInfoService;

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public List<UserPlanInfo> getAllCustomersPlanInfo()
    {
        try {
            return userPlanInfoService.findAll();
        }
        catch (Exception ex)
        {
            logger.severe("Exception occurred while retrieving all the customer plan info "+ex);
        }
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public UserPlanInfo getCustomerPlanInfo(String id)
    {
        try {
            Optional<UserPlanInfo> userPlanInfo = userPlanInfoService.findById(id);

            if (userPlanInfo.isPresent()) {
                return userPlanInfo.get();
            }
        }
        catch (Exception ex)
        {
            logger.severe("Exception occurred while retrieving customer plan info "+ex);
        }
        return null;
    }

   @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public String insertCustomerInfo(UserPlanInfo userPlanInfo)
    {
        try {
            if (userPlanInfo != null && userPlanInfo.getCustomerId() != null) {
                userPlanInfoService.save(userPlanInfo);
                return "Successfully inserted Into DB";
            }
        }
        catch (Exception ex)
        {
            logger.severe("Exception occurred while inserting customer plan info into the database" +ex);
        }
        return "Error occurred";
    }

}
