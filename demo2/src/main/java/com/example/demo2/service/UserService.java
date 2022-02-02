package com.example.demo2.service;

import com.example.demo2.domain.Role;
import com.example.demo2.domain.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username,String roleName);
    User getUser(String username);
    List<User>getUsers();
}
