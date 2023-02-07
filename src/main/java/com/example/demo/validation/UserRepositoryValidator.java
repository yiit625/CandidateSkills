package com.example.demo.validation;

import com.example.demo.dto.UserValidationDto;
import com.example.demo.repository.UserEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
public class UserRepositoryValidator {
    private static final String USER_DOES_NOT_EXIST_IN_REPOSITORY_EXCEPTION_MESSAGE = "Following user doesn't exist in the database";

    private static final String USER_EXISTS_IN_REPOSITORY_EXCEPTION_MESSAGE = "Following user already exists in the database";

    private static final String USER_ID_NOT_FOUND_EXCEPTION_MESSAGE = "User is not found in the document";

    private final UserEntityRepository userEntityRepository;

    public Optional<String> checkIfUserIdExistsInRepository(String userId) {

        if (userEntityRepository.findById(userId).isEmpty()) {
            return Optional.of(USER_DOES_NOT_EXIST_IN_REPOSITORY_EXCEPTION_MESSAGE);
        }

        return Optional.empty();
    }

    public Optional<String> checkUserId(UserValidationDto userToValidate) {
        if (userToValidate.getId().isEmpty()) return Optional.of(USER_ID_NOT_FOUND_EXCEPTION_MESSAGE);
        return Optional.empty();
    }

    public Optional<String> checkIfUserExistsInRepository(UserValidationDto userToValidate) {
        if (userEntityRepository.existsById(userToValidate.getId())) {
            return Optional.of(USER_EXISTS_IN_REPOSITORY_EXCEPTION_MESSAGE);
        }

        return Optional.empty();
    }
}
