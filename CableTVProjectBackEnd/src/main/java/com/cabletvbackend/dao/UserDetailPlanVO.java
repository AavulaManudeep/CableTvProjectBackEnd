package com.cabletvbackend.dao;


import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
@JsonIgnoreProperties
public class UserDetailPlanVO implements Serializable {

    private Map<String,Object> extraValues;

    @JsonProperty
    private String customerId;

    @JsonProperty
    @JsonAlias({"firstName"})
    private String customerFirstName;

    @JsonProperty
    @JsonAlias({"lastName"})
    private String customerLastName;

    @JsonProperty
    private UserPlanDetailsVO userPlanDetailsVO;

    @JsonProperty
    private UserAddress userAddress;

    @JsonProperty
    private Double amountDue;

    @JsonAnyGetter
    private Map<String, Object> getExtraValues()
    {
        return this.extraValues;
    }
    @JsonAnySetter
    private void setExtraValues(String name, Object value)
    {
        if(name=="price")
            amountDue=(Double)value;
        this.extraValues.put(name,value);
    }
}
