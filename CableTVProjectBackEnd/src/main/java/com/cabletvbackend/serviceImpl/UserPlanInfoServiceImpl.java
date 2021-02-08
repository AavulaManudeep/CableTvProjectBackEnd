package com.cabletvbackend.serviceImpl;

import com.cabletvbackend.dao.UserDetailPlanVO;
import com.cabletvbackend.dao.UserPlanDetailsVO;
import com.cabletvbackend.dao.UserPlanInfo;
import com.cabletvbackend.repository.UserPlanInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.DataInput;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
@Service
public class UserPlanInfoServiceImpl {

    private static final Logger logger = LogManager.getLogger(UserPlanInfoServiceImpl.class.getName());

    @Autowired
    UserPlanInfoService userPlanInfoService;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public List<UserDetailPlanVO> getAllCustomersPlanInfo()
    {
        try {
            ParameterizedTypeReference<List<UserDetailPlanVO>> response =  new ParameterizedTypeReference<List<UserDetailPlanVO>>() {
            };
            List<UserDetailPlanVO> uList = new ArrayList<>();
            //uList =  restTemplate().exchange("http://localhost:8087/customerprofile/retrievall",HttpMethod.GET,"", uList.getClass());
            ResponseEntity<List<UserDetailPlanVO>>s = restTemplate().exchange("http://localhost:8087/customerprofile/retrievall",
                    HttpMethod.GET,null,response);
            //"http://localhost:8087/customerprofile/retrievall",

            //ObjectMapper mapper = new ObjectMapper();
            //uList = mapper.readValue(s,new ArrayList<UserDetailPlanVO>().getClass());

            //List<UserDetailPlanVO> ex = (List<UserDetailPlanVO>) restTemplate().getForObject("http://localhost:8087/customerprofile/retrievall", response);
           // System.out.println(ex);
            System.out.println(s.getBody());
            return s.getBody();
        }
        catch (Exception ex)
        {
            logger.fatal("Exception occurred while retrieving all the customer plan info "+ex);
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
            logger.fatal("Exception occurred while retrieving customer plan info "+ex);
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
            logger.fatal("Exception occurred while inserting customer plan info into the database" +ex);
        }
        return "Error occurred";
    }

}
