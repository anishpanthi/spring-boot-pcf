package com.app.springboot.pcf.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Anish Panthi
 */
public class JwtTokenMissingException extends AuthenticationException {

    /**
     * @param msg
     */
    public JwtTokenMissingException(String msg) {
        super(msg);
    }

}