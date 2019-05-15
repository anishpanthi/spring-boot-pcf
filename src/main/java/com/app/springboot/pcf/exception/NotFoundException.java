package com.app.springboot.pcf.exception;

import lombok.Getter;

/**
 * @author Anish Panthi
 */
@Getter
public class NotFoundException extends Exception{

    public NotFoundException(String message){
        super(message);
    }
}