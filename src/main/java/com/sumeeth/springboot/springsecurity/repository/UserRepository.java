package com.sumeeth.springboot.springsecurity.repository;

import com.sumeeth.springboot.springsecurity.entity.CustomisedUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UserRepository extends CrudRepository<CustomisedUser, Integer> {

     Optional<CustomisedUser> findByUsername(String username);

}
