package com.sumeeth.springboot.springsecurity.util;

import com.sumeeth.springboot.springsecurity.entity.AuthDetail;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

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

    public static void validateAuthDetail(AuthDetail authDetail){
        if(null != authDetail && StringUtils.isEmpty(authDetail.getUsername())){
            throw new BadCredentialsException("Username can't be null!");
        }
        if(null != authDetail && StringUtils.isEmpty(authDetail.getPassword())){
            throw new BadCredentialsException("Password can't be null!");
        }
    }
}
