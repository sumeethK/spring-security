package com.sumeeth.springboot.springsecurity.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.Assert;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class CustomizedUserRole {
    private static final long serialVersionUID = 500L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Integer roleId;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private  SystemRoles role;


    public CustomizedUserRole(){}

    public CustomizedUserRole(SystemRoles role) {
        Assert.hasText(role.name(), "A granted authority textual representation is required");
        this.role = role;
    }


    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else {
            return obj instanceof CustomizedUserRole ? this.role.equals(((CustomizedUserRole)obj).role) : false;
        }
    }

    public int hashCode() {
        return this.role.hashCode();
    }

    public String toString() {
        return this.role.name();
    }


    public SystemRoles getRole() {
        return role;
    }


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
