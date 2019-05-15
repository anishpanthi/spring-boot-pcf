package com.app.springboot.pcf.repository;

import com.app.springboot.pcf.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author Anish Panthi
 */

public interface UserRepository extends CrudRepository<User, Long>, JpaRepository<User, Long> {

    User findByUsernameOrEmail(String username, String email);

    User findByUsername(String username);
}