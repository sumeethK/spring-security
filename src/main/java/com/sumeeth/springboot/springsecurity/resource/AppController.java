package com.sumeeth.springboot.springsecurity.resource;

import com.sumeeth.springboot.springsecurity.entity.CustomisedUser;
import com.sumeeth.springboot.springsecurity.service.CustomizedUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    private CustomizedUserDetailsService userService;

    @RequestMapping("/hello")
    public String helloApp(){
        return "App works!";
    }


    @RequestMapping("/secured")
    public String securedHelloApp(){
        return "Your App is secured!";
    }

    @RequestMapping("/user/profile/{userId}")
    public @ResponseBody
    Object getUser(@PathVariable(name = "userId") Integer userId)
    {
        return userService.findCustomUserById(userId).get();
    }

    @RequestMapping("/user/{username}")
    public @ResponseBody
    CustomisedUser getUser(@PathVariable(name = "username") String name)
    {
        return userService.findUsersByUsername(name).get();
    }

    @RequestMapping("/accessDenied")
    public @ResponseBody
    String accessDenied(@AuthenticationPrincipal UserDetails userDetails)
    {
        if(null == userDetails){
            return "<h1> Access Denied </h1>";
        }
        return "<h1>User : "+ userDetails.getUsername() +" doesn't have enough authorities.</h1>";
    }


}
