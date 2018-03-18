package com.sumeeth.springboot.springsecurity.entity;

public class UserProfile extends CustomisedUser {

    public UserProfile(Integer userId,String username) {
        this.userId=userId;
        this.username =username;
    }
}
