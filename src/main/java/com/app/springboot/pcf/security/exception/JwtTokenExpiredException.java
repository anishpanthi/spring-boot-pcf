package com.app.springboot.pcf.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Anish Panthi
 */
public class JwtTokenExpiredException extends AuthenticationException {

    /**
     * @param msg
     */
    public JwtTokenExpiredException(String msg) {
        super(msg);
    }

}
