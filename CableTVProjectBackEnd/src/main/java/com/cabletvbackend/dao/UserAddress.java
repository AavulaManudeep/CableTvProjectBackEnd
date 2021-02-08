package com.cabletvbackend.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


import java.io.Serializable;

@Data
@JsonIgnoreProperties
public class UserAddress implements Serializable {

    @JsonProperty
    private Integer id;
    @JsonProperty
    private String address1;
    @JsonProperty
    private String address2;
    @JsonProperty
    private String aptno;
    @JsonProperty
    private String zipcode;
    @JsonProperty
    private String mandal;
    @JsonProperty
    private String district;
    @JsonProperty
    private String state;
}
