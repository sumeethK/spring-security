package com.sumeeth.springboot.springsecurity.resource;


import com.sumeeth.springboot.springsecurity.entity.AuthDetail;
import com.sumeeth.springboot.springsecurity.entity.CustomUserDetails;
import com.sumeeth.springboot.springsecurity.entity.CustomisedUser;
import com.sumeeth.springboot.springsecurity.entity.JwtUser;
import com.sumeeth.springboot.springsecurity.security.JwtGenerator;
import com.sumeeth.springboot.springsecurity.service.CustomizedUserDetailsService;
import com.sumeeth.springboot.springsecurity.util.ApiError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtGenerator jwtGenerator;
    @Autowired
    @Qualifier(value = "customizedUserDetailsService")
    private CustomizedUserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public Object authenticate(@RequestBody AuthDetail user) throws Exception {
        try {
            CustomUserDetails userDetails = userDetailsService.authenticate(user.getUsername(), user.getPassword());
            JwtUser jwtUser = new JwtUser();
            jwtUser.setId(userDetails.getUserId());
            jwtUser.setRole(userDetails.getRoles().toString());
            return jwtGenerator.generate(jwtUser);
        }catch (Exception ex){
            return new ApiError(HttpStatus.FORBIDDEN, ex);
//             return apiError.convertToJson();
        }
    }
}
