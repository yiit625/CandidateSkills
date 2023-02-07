package com.example.demo.mapper;

import com.example.demo.domain.User;
import com.example.demo.dto.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserMapper {
    public User mapUserModelDataToEntity(UserModel newUser) {
        return new User(newUser.getId(), newUser.getName(), newUser.getDescription(), newUser.getDate());
    }
}
