package com.sumeeth.springboot.springsecurity.service;

import com.sumeeth.springboot.springsecurity.entity.CustomUserDetails;
import com.sumeeth.springboot.springsecurity.entity.CustomisedUser;
import com.sumeeth.springboot.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountException;
import java.util.Optional;

import static com.sumeeth.springboot.springsecurity.util.UserUtil.validateUser;

@Service(value = "customizedUserDetailsService")
public class CustomUserDetailServiceImpl implements UserDetailsService,CustomizedUserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository usersRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<CustomisedUser> optionalUsers = usersRepository.findByUsername(username);

        optionalUsers
                .orElseThrow(() -> new UsernameNotFoundException("Username: "+username +" not found"));
        return optionalUsers
                .map(CustomUserDetails::new).get();
    }

    @Override
    public Integer saveUser(CustomisedUser customisedUser) {
        return usersRepository.save(customisedUser).getUserId();
    }

    @Override
    public Optional<CustomisedUser> findUsersByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    @Override
    public Optional<CustomisedUser> findUserById(Integer userId) {
        return usersRepository.findById(userId);
    }

    @Override
    public void deleteUserById(Integer userId) {
         usersRepository.deleteById(userId);
    }

    @Override
    public CustomUserDetails authenticate(String username, String password) throws AccountException {
        Optional<CustomisedUser> optionalUsers =
                usersRepository.findByUsername(username);
boolean isPasswordMatch=false;
        optionalUsers
                .orElseThrow(() -> new UsernameNotFoundException("Username: "+username +" not found"));
        try {
            isPasswordMatch= passwordEncoder.matches(password,optionalUsers.get().getPassword());
        }catch (Exception e){
            throw new RuntimeException("Incorrect Username/password combination.");
        }
        if(isPasswordMatch) {
            CustomUserDetails customUserDetails = optionalUsers
                    .map(CustomUserDetails::new).get();
            validateUser(customUserDetails);
            return customUserDetails;
        }
        throw new RuntimeException("Incorrect Username/password combination.");

    }


}
