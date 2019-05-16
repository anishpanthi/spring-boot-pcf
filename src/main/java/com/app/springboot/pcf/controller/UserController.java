package com.app.springboot.pcf.controller;

import com.app.springboot.pcf.domain.User;
import com.app.springboot.pcf.dto.CrudResponseDto;
import com.app.springboot.pcf.dto.UserDto;
import com.app.springboot.pcf.dto.UserResponse;
import com.app.springboot.pcf.exception.ApiException;
import com.app.springboot.pcf.exception.NotFoundException;
import com.app.springboot.pcf.mapper.UserMapper;
import com.app.springboot.pcf.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author Anish Panthi
 */
@RestController
@Slf4j
@RequestMapping("/v1")
@Api(value = "User Controller", tags = "Operations pertaining to Users.")
public class UserController {

    private UserService userService;

    /**
     * Implement HATEOS here!!!
     *
     * @return
     * @throws ApiException
     */
    @GetMapping(value = "/users/username/{username}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getUserByUsername(@PathVariable("username") String username,
                                               @RequestHeader("Authorization") final String authorization) {
        Optional<User> userOptional = userService.findByUsername(username);
        return userOptional.map(user -> {
            final UserResponse userResponse = new UserResponse(Collections.singletonList(UserMapper.mapToDto(user)));
            userResponse.add(linkTo(methodOn(UserController.class).getUserByUsername(username, null)).withSelfRel());
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        }).orElseThrow(() -> new NotFoundException("User Not Found!!!"));
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<UserDto>> getAllUsers(@RequestHeader("Authorization") final String authorization) throws ApiException {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Returns User if ID is found.")
    @GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getSingleUser(@RequestHeader("Authorization") final String authorization,
                                           @ApiParam(value = "User ID", required = true) @PathVariable("id") Long id) throws ApiException {
        log.info("ID:: {}", id);
        return new ResponseEntity<>(userService.findOne(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Stores the user.")
    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> addUser(@RequestHeader("Authorization") final String authorization,
                                     @ApiParam(value = "User details to add", required = true) @RequestBody UserDto userDto) throws ApiException {
        return new ResponseEntity<>(userService.save(userDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Updates the user.")
    @PutMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> updateUser(@RequestHeader("Authorization") final String authorization,
                                        @ApiParam(value = "User details to update", required = true) @RequestBody UserDto userDto) throws ApiException {
        return new ResponseEntity<>(userService.update(userDto), HttpStatus.OK);
    }

    @ApiOperation(value = "Deletes the user based on provided information.")
    @DeleteMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> deleteUser(@RequestHeader("Authorization") final String authorization,
                                        @ApiParam(value = "User details to delete", required = true) @RequestBody UserDto userDto) throws ApiException {
        return new ResponseEntity<>(userService.delete(userDto), HttpStatus.OK);
    }

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
}
