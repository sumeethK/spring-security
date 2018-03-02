package com.sumeeth.springboot.springsecurity.util;

import com.sumeeth.springboot.springsecurity.entity.CustomisedUser;
import org.springframework.security.core.userdetails.UserDetails;

import javax.security.auth.login.AccountException;
import javax.security.auth.login.AccountExpiredException;
import javax.security.auth.login.AccountLockedException;

public class UserUtil {

    public static void validateUser(UserDetails user) throws AccountException {
        if(!user.isAccountNonExpired()){
            throw new AccountExpiredException("User Account Expired!");
        }

       else if(!user.isCredentialsNonExpired()){
            throw new AccountExpiredException("User Credentials Expired!");
        }


        else if(!user.isAccountNonLocked()){
            throw new AccountLockedException("User Account Locked!");
        }


        else if(!user.isEnabled()){
            throw new AccountLockedException("User Account is not active!");
        }
    }
}
