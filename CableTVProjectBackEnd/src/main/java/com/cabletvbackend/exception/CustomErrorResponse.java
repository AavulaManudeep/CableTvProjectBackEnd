package com.cabletvbackend.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomErrorResponse {

    String errorCode;
    String errorMsg;
    int status;

    public CustomErrorResponse(String errorCode,String erroeMsg)
    {
        this.errorCode = errorCode;
        this.errorMsg = erroeMsg;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd hh:mm:ss")
    LocalDateTime timeStamp;
}
