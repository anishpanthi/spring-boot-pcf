package com.app.springboot.pcf.security;

import java.lang.annotation.*;

/**
 * @author Anish Panthi
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface EnableEscapeForCGLibProxy {
}
