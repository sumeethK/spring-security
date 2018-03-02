package com.sumeeth.springboot.springsecurity.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "user_info",uniqueConstraints={@UniqueConstraint(columnNames={"username"})})
public class CustomisedUser{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer userId;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private boolean accountNonExpired;
    @Column
    private boolean accountNonLocked;
    @Column
    private boolean credentialsNonExpired;
    @Column
    private boolean enabled;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonManagedReference
    private Set<CustomizedUserRole> roles;

    public CustomisedUser(){};

    public CustomisedUser(CustomisedUser users) {
        this.enabled = users.isEnabled();
        this.roles = (Set<CustomizedUserRole>) users.getRoles();
        this.username = users.getUsername();
        this.userId = users.getUserId();
        this.password = users.getPassword();
        this.accountNonExpired=users.isAccountNonExpired();
        this.credentialsNonExpired=users.isCredentialsNonExpired();
        this.accountNonLocked=users.isAccountNonLocked();
    }

    public Collection<? extends CustomizedUserRole> getRoles() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

   
    public String getUsername() {
        return username;
    }

   
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

   
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

   
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

   
    public boolean isEnabled() {
        return enabled;
    }

    public Integer getUserId() {
        return userId;
    }


    public static class UserBuilder{
        private String username;

        private String password;

        private boolean accountNonExpired;
        private boolean accountNonLocked;
        private boolean credentialsNonExpired;
        private boolean enabled;
        private Set<CustomizedUserRole> roles;

        public UserBuilder withUsername(String username){
            this.username = username;
            return this;
        }
        public UserBuilder withPassword(String password){
            this.password = password;
            return this;
        }
        public UserBuilder withAccountNonExpired(boolean accountNonExpired){
            this.accountNonExpired = accountNonExpired;
            return this;
        }
        public UserBuilder withAccountNonLocked(boolean accountNonLocked){
            this.accountNonLocked = accountNonLocked;
            return this;
        }
        public UserBuilder withCredentialsNonExpired(boolean credentialsNonExpired){
            this.credentialsNonExpired = credentialsNonExpired;
            return this;
        }
        public UserBuilder withEnabled(boolean enabled){
            this.enabled = enabled;
            return this;
        }

        public UserBuilder withRoles(Set<CustomizedUserRole> roles){
            this.roles = roles;
            return this;
        }


        public CustomisedUser build(){
            CustomisedUser user=  new CustomisedUser();
            user.username = this.username;
            user.password = this.password;
            user.accountNonExpired = this.accountNonExpired;
            user.credentialsNonExpired = this.credentialsNonExpired;
            user.enabled = this.enabled;
            user.accountNonLocked = this.accountNonLocked;
            user.roles = this.roles;
            return user;

        }

    }

}
