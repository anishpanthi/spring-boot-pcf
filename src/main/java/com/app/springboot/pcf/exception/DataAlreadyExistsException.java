package com.app.springboot.pcf.exception;

import lombok.Getter;

/**
 * @author Anish Panthi
 */
@Getter
public class DataAlreadyExistsException extends Exception{

    public DataAlreadyExistsException(String message){
        super(message);
    }
}
