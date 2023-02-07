package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.dto.UserModel;

import java.util.List;

public interface UserService {

    List<User> createUsers(List<UserModel> newUsers);

    void deleteUser(String userId);

    User getUserById(String userId);
}
