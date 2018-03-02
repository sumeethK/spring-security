package com.sumeeth.springboot.springsecurity.resource;

import com.sumeeth.springboot.springsecurity.entity.CustomisedUser;
import com.sumeeth.springboot.springsecurity.service.CustomizedUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/user/{username}")
    public @ResponseBody
    CustomisedUser getUser(@PathVariable(name = "username") String name)
    {
        return userService.findUsersByUsername(name).get();
    }
}
