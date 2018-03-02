package com.sumeeth.springboot.springsecurity.service;

import com.sumeeth.springboot.springsecurity.entity.CustomisedUser;
import com.sumeeth.springboot.springsecurity.entity.CustomizedUserRole;
import com.sumeeth.springboot.springsecurity.entity.SystemRoles;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomUserDetailServiceTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier(value = "customizedUserDetailsService")
    private CustomizedUserDetailsService userService;

    @Test
    public void testSaveUser() {
        Integer userId = userService.saveUser(createAdminUser());
        Assert.assertNotNull("Unable to save user!", userId);
    }

    @Test
    public void testDeleteUser() {
        userService.deleteUserById(1);
    }


    private CustomisedUser createAdminUser() {
        Set<CustomizedUserRole> roles = new HashSet<>();
        roles.add(new CustomizedUserRole(SystemRoles.GUEST));
        return new CustomisedUser.UserBuilder()
                .withUsername("toto")
                .withPassword(passwordEncoder.encode("toto"))
                .withAccountNonExpired(true)
                .withAccountNonLocked(true)
                .withCredentialsNonExpired(true)
                .withEnabled(false)
                .withRoles(roles)
                .build();

    }
}
