package com.sumeeth.springboot.springsecurity.service;

import com.sumeeth.springboot.springsecurity.entity.AuthDetail;
import com.sumeeth.springboot.springsecurity.entity.CustomUserDetails;
import com.sumeeth.springboot.springsecurity.entity.CustomisedUser;
import com.sumeeth.springboot.springsecurity.entity.UserProfile;
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
    public Optional<Object> findCustomUserById(Integer userId) {
        Object profile = usersRepository.findCustomUserById(userId);
        return Optional.ofNullable(profile);
//        return new UserProfile()
    }

    @Override
    public void deleteUserById(Integer userId) {
         usersRepository.deleteById(userId);
    }

    @Override
    public CustomUserDetails authenticate(AuthDetail authDetail) throws AccountException {
        Optional<CustomisedUser> optionalUsers =
                usersRepository.findByUsername(authDetail.getUsername());
        boolean isPasswordMatch=false;
        optionalUsers
                .orElseThrow(() -> new UsernameNotFoundException("Username: "+authDetail.getUsername() +" not found"));
        try {
            isPasswordMatch= passwordEncoder.matches(authDetail.getPassword(),optionalUsers.get().getPassword());
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
