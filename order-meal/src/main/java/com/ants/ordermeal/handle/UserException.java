package com.ants.ordermeal.handle;

import com.ants.ordermeal.enums.ResponseCode;

/**
 * Created by wolf
 */
public class UserException extends RuntimeException{

    private int code;

    public UserException(ResponseCode responseCode){
        super(responseCode.getDesc());
        this.code=responseCode.getCode();
    }

    public void setCode(){
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}