package com.example.demo.validation;

import com.example.demo.repository.UserEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
public class UserRepositoryValidator {
    private static final String USER_DOES_NOT_EXIST_IN_REPOSITORY_EXCEPTION_MESSAGE = "Following user doesn't exist in the database";

    private final UserEntityRepository userEntityRepository;

    public Optional<String> checkIfUserIdExistsInRepository(String userId) {

        if (userEntityRepository.findById(userId).isEmpty()) {
            return Optional.of(USER_DOES_NOT_EXIST_IN_REPOSITORY_EXCEPTION_MESSAGE);
        }

        return Optional.empty();
    }
}
