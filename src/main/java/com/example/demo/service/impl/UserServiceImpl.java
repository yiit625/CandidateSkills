package com.example.demo.service.impl;

import com.example.demo.domain.User;
import com.example.demo.dto.UserModel;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserEntityRepository;
import com.example.demo.service.UserService;
import com.example.demo.validation.UserValidationFacade;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserEntityRepository userRepository;

    private final UserMapper userMapper;

    private final UserValidationFacade userValidationFacade;
    @Override
    public List<User> createUsers(List<UserModel> newUsers) {
        List<User> userEntityList = new ArrayList<>();
        newUsers.forEach(newUser -> {
            userEntityList.add(userMapper.mapUserModelDataToEntity(newUser));
        });
        return userRepository.saveAll(userEntityList);
    }

    @Override
    public void deleteUser(String userId) {
        userValidationFacade.validateUserDeleteAction(userId);
        userRepository.deleteById(userId);
    }

    @Override
    public User getUserById(String userId) {
        List<User> foundUsers = userRepository.findAll();
        userValidationFacade.rejectRequestIfUserIdIsInvalid(userId, foundUsers);
        return userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
    }
}
