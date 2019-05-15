package com.app.springboot.pcf.mapper;

import com.app.springboot.pcf.domain.User;
import com.app.springboot.pcf.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Anish Panthi
 */
public class UserMapper {

    private static Function<User, UserDto> userToUserDto = user -> {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setContact(user.getContact());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        return userDto;
    };

    public static UserDto mapToDto(User user) {
        return userToUserDto.apply(user);
    }

    public static List<UserDto> mapToListDto(List<User> users) {
        return users.stream().map(userToUserDto).collect(Collectors.toList());
    }

    public static Page<UserDto> mapToPageDto(Page<User> userPage) {
        return convertToPage(userPage, Collections.emptyMap());
    }

    public static final Page<UserDto> convertToPage(Page<User> userPage, Map<String, Object> parameters) {
        List<UserDto> userDtos = new ArrayList(userPage.getContent().size());
        for (User user : userPage) {
            userDtos.add(userToUserDto.apply(user));
        }

        Pageable pageable = new PageRequest(userPage.getNumber(), userPage.getSize(), userPage.getSort());
        Page<UserDto> targetPage = new PageImpl(userDtos, pageable, userPage.getTotalElements());
        return targetPage;
    }
}
