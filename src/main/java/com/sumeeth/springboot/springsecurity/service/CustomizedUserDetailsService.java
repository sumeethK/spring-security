package com.sumeeth.springboot.springsecurity.service;

import com.sumeeth.springboot.springsecurity.entity.AuthDetail;
import com.sumeeth.springboot.springsecurity.entity.CustomUserDetails;
import com.sumeeth.springboot.springsecurity.entity.CustomisedUser;

import javax.security.auth.login.AccountException;
import java.util.Optional;

public interface CustomizedUserDetailsService
{
    Integer saveUser(CustomisedUser customisedUser);

    Optional<CustomisedUser> findUsersByUsername(String  username);

    Optional<CustomisedUser> findUserById(Integer  userId);

    Optional<Object> findCustomUserById(Integer  userId);

    void deleteUserById(Integer  userId);

    CustomUserDetails authenticate(AuthDetail authDetail) throws AccountException;
}
