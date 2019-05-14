package com.app.springboot.pcf.exception;

import lombok.Getter;

/**
 * @author Anish Panthi
 */
@Getter
public class ApiException extends Exception {

    public ApiException(String message){
        super(message);
    }
}
