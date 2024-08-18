package com.nikhillab.service;

import java.util.List;
import java.util.Optional;

import com.nikhillab.dto.UserForm;
import com.nikhillab.entities.User;



public interface UserService {

    User saveUser(UserForm userForm);

    Optional<User> getUserById(Long id);

    Optional<User> updateUser(UserForm userForm);

    void deleteUser(Long id);

    boolean isUserExist(Long userId);

    boolean isUserExistByEmail(String email);

    List<User> getAllUsers();

    User getUserByEmail(String email);

    // add more methods here related user service[logic]
}

