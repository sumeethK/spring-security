package com.sumeeth.springboot.springsecurity.service;

import com.sumeeth.springboot.springsecurity.entity.CustomUserDetails;
import com.sumeeth.springboot.springsecurity.entity.CustomisedUser;
import org.springframework.security.core.userdetails.UserDetails;

import javax.security.auth.login.AccountException;
import java.util.Optional;

public interface CustomizedUserDetailsService
{
    Integer saveUser(CustomisedUser customisedUser);

    Optional<CustomisedUser> findUsersByUsername(String  username);

    Optional<CustomisedUser> findUserById(Integer  userId);

    void deleteUserById(Integer  userId);

    CustomUserDetails authenticate(String username, String password) throws AccountException;
}
