package com.cabletvbackend.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@JsonIgnoreProperties
public class UserPlanDetailsVO implements Serializable {

    @JsonProperty
    private Integer planId;

    @JsonProperty
    private String planName;

    @JsonProperty
    private Float price;

}
