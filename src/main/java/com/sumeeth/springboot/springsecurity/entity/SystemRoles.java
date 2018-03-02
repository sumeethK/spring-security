package com.sumeeth.springboot.springsecurity.entity;

public enum SystemRoles {
    ROOT(111),
    ADMIN(222),
    NORMAL_USER(333),
    GUEST(444);

    private Integer roleCode;

    SystemRoles(int i) {
        roleCode=i;
    }
}
