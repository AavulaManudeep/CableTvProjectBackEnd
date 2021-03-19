package com.cabletvbackend.controller;

import com.cabletvbackend.dao.UserDetailPlanVO;
import com.cabletvbackend.dao.UserPlanInfo;
import com.cabletvbackend.serviceImpl.UserPlanInfoServiceImpl;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins= "*",allowedHeaders = "*")
@RestController
@RequestMapping("/customerinfo")
public class CustomerInfoController {

    @Autowired
    UserPlanInfoServiceImpl userPlanInfoServiceImpl;

    @GetMapping("/retrieveall")
    public ResponseEntity<List<UserDetailPlanVO>> getAllCustomerInfo()
    {
        List<UserDetailPlanVO> userDetailPlanVO = userPlanInfoServiceImpl.getAllCustomersPlanInfo();
        return new ResponseEntity<List<UserDetailPlanVO>>(userDetailPlanVO,HttpStatus.OK);
    }
    @PostMapping(value = "/retrieve/userplandetais" ,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserPlanInfo> getCustomerInfo(@RequestBody String id)
    {
        if(id==null || id.isEmpty())
        {
            id = "111-TEST-000";
        }
        UserPlanInfo userPlanInfo = userPlanInfoServiceImpl.getCustomerPlanInfo(id);
        return new ResponseEntity<UserPlanInfo>(userPlanInfo,HttpStatus.OK);
    }
    @PostMapping(value = "/insert/userplandetails" ,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> insertCustomerInfo(@RequestBody UserPlanInfo userPlanInfo)
    {
        if(userPlanInfo==null || userPlanInfo.getCustomerId()==null
                || userPlanInfo.getCustomerId().isEmpty())
        {
           userPlanInfo = new UserPlanInfo();
           userPlanInfo.setAmountDue("0.00");
           userPlanInfo.setCustomerCurrentPlan("Unlimited-Gold");
           userPlanInfo.setCustomerId("111-TEST-000");
           userPlanInfo.setCustomerFirstName("Test");
           userPlanInfo.setCustomerLastName("Test");
        }
        String check = userPlanInfoServiceImpl.insertCustomerInfo(userPlanInfo);
        return new ResponseEntity<String>("Success",HttpStatus.OK);
    }

}
