package com.cabletvbackend.dao;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name= "userplaninfo", catalog = "cabletv_customerinfo")
public class UserPlanInfo {

    @Id
    @Column(name = "customer_id")
    private String customerId;

    @Column(name="customer_firstname")
    private String customerFirstName;

    @Column(name = "customer_lastname")
    private String customerLastName;

    @Column(name = "customer_current_plan")
    private String customerCurrentPlan;

    @Column(name ="amount_due")
    private String amountDue;

}
