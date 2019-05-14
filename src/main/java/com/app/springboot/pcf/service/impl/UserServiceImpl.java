package com.app.springboot.pcf.service.impl;

import com.app.springboot.pcf.domain.User;
import com.app.springboot.pcf.dto.UserDto;
import com.app.springboot.pcf.exception.ApiException;
import com.app.springboot.pcf.exception.SearchNotFoundException;
import com.app.springboot.pcf.repository.UserRepository;
import com.app.springboot.pcf.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Anish Panthi
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public List<UserDto> findAll() throws ApiException {
        List<User> usersInDb;
        try {
            usersInDb = userRepository.findAll();
            if (usersInDb.isEmpty()) {
                throw new SearchNotFoundException("Record Not Found!!!");
            }
        } catch (Exception e) {
            log.error("", e);
            throw new ApiException(e.getMessage());
        }

        List<UserDto> userDtoList = new ArrayList<>();

        usersInDb.forEach(user -> {
                    UserDto userDto = new UserDto();
                    BeanUtils.copyProperties(user, userDto);
                    userDtoList.add(userDto);
                }
        );
        return userDtoList;
    }

    @Override
    public UserDto findOne(Long id) throws ApiException {
        UserDto userDto = new UserDto();
        try {
            Optional<User> user = userRepository.findById(id);
            if (!user.isPresent()) {
                throw new SearchNotFoundException("Record Not Found By Given Id!!!");
            }
            BeanUtils.copyProperties(user.get(), userDto);
        } catch (Exception e) {
            log.error("", e);
            throw new ApiException(e.getMessage());
        }
        return userDto;
    }

    @Override
    public UserDto save(User user) throws ApiException {
        return null;
    }

    @Override
    public UserDto update(User user) throws ApiException {
        return null;
    }

    @Override
    public void delete(User user) throws ApiException {

    }

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
