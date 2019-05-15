package com.app.springboot.pcf.dto;

import lombok.*;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * @author Anish Panthi
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse extends ResourceSupport {

    private List<UserDto> userDtoList;
}
