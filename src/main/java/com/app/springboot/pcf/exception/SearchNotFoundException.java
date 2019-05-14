package com.app.springboot.pcf.exception;

import lombok.Getter;

/**
 * @author Anish Panthi
 */
@Getter
public class SearchNotFoundException extends Exception{

    public SearchNotFoundException(String message){
        super(message);
    }
}
