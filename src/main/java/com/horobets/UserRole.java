package com.horobets;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "users_roles")
public class UserRole {
    @Id
    @Column(name = "username")
    private String username;
    @Column(name = "rolename")
    private String rolename;
    public UserRole() {
    }
    public UserRole(String username, String rolename) {
        this.username = username;
        this.rolename = rolename;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getRolename() {
        return rolename;
    }
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
    @Override
    public String toString() {
        return getClass().getSimpleName()+"[username: "+username+" role: "+rolename+"]\n";
    }
}
