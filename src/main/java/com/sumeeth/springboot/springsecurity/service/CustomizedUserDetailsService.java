package com.sumeeth.springboot.springsecurity.service;

import com.sumeeth.springboot.springsecurity.entity.CustomisedUser;

import java.util.Optional;

public interface CustomizedUserDetailsService
{
    Integer saveUser(CustomisedUser customisedUser);

    Optional<CustomisedUser> findUsersByUsername(String  username);

    Optional<CustomisedUser> findUserById(Integer  userId);

    void deleteUserById(Integer  userId);
}
