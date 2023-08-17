package com.example.young_hanabackend.security.model;

import com.example.young_hanabackend.common.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

@Data
public class FendResponseObject<T> {
    String message;
    T data;

    @JsonInclude(value= JsonInclude.Include.NON_NULL)
    String errorMessage;

    @JsonInclude(value= Include.NON_NULL)
    ErrorCode errorCode;

    public FendResponseObject() {}

    public FendResponseObject(String msg) {
        this.message = msg;
    }
}

