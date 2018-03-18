package com.sumeeth.springboot.springsecurity.resource;


import com.sumeeth.springboot.springsecurity.entity.AuthDetail;
import com.sumeeth.springboot.springsecurity.entity.CustomUserDetails;
import com.sumeeth.springboot.springsecurity.entity.JwtUser;
import com.sumeeth.springboot.springsecurity.security.JwtGenerator;
import com.sumeeth.springboot.springsecurity.service.CustomizedUserDetailsService;
import com.sumeeth.springboot.springsecurity.util.ApiError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.sumeeth.springboot.springsecurity.util.UserUtil.validateAuthDetail;

@CrossOrigin
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
            validateAuthDetail(user);
            CustomUserDetails userDetails = userDetailsService.authenticate(user);
            JwtUser jwtUser = new JwtUser();
            jwtUser.setUserId(userDetails.getUserId());
            jwtUser.setRole(userDetails.getRoles().toString());
            jwtUser.setUsername(user.getUsername());
            jwtUser.setToken(jwtGenerator.generate(jwtUser));
            return jwtUser;
        }catch (Exception ex){
            return new ApiError(HttpStatus.FORBIDDEN, ex);
        }
    }
}
