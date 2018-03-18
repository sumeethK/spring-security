package com.sumeeth.springboot.springsecurity.repository;

import com.sumeeth.springboot.springsecurity.entity.CustomisedUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UserRepository extends CrudRepository<CustomisedUser, Integer> {

     Optional<CustomisedUser> findByUsername(String username);
     Optional<CustomisedUser> findByUsernameAndPassword(String username, String password);

     @Query(value = "select * from user_data  where user_id = :userId",nativeQuery = true)
     Optional<Object> findCustomUserById(@Param("userId") Integer  userId);

}
