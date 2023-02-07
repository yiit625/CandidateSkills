package com.example.demo.exception;

import com.example.demo.dto.UserValidationDto;
import com.example.demo.validation.ValidationStatus;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserNotFoundException extends RuntimeException {

    private static final String USERS_NOT_FOUND_EXCEPTION_MESSAGE = "User id not found.";

    private final List<ValidationStatus<String, UserValidationDto>> validationErrorStatuses;

    public UserNotFoundException(String incorrectId) {

        this.validationErrorStatuses = List.of(new ValidationStatus<>(
                incorrectId,
                null,
                List.of(USERS_NOT_FOUND_EXCEPTION_MESSAGE)));
    }

}
