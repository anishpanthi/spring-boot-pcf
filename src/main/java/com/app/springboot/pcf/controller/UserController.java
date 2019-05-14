package com.app.springboot.pcf.controller;

import com.app.springboot.pcf.dto.UserDto;
import com.app.springboot.pcf.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Anish Panthi
 */
@RestController
@Slf4j
@RequestMapping("/v1")
public class UserController {

    private UserService userService;

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    public UserController(UserService userService) {
        this.userService = userService;
    }
}
