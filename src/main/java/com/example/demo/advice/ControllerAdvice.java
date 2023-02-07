package com.example.demo.advice;

import com.example.demo.dto.UserValidationDto;
import com.example.demo.exception.FileNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.exception.UsersValidationException;
import com.example.demo.validation.ValidationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;


@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    private static final String RESOURCE_NOT_FOUND_EXCEPTION_MESSAGE = "Resources you have requested were not found.";

    private static final String USERS_VALIDATION_EXCEPTION_MESSAGE = "Users validation failed.";

    private static final String FILE_NOT_FOUND_VALIDATION_EXCEPTION_MESSAGE = "File not found!";

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ValidationResponse<String, UserValidationDto>> UserNotFoundExceptionHandler(UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ValidationResponse<>(RESOURCE_NOT_FOUND_EXCEPTION_MESSAGE, e.getValidationErrorStatuses())

        );
    }

    @ExceptionHandler(UsersValidationException.class)
    public ResponseEntity<ValidationResponse<String, UserValidationDto>> UserValidationExceptionExceptionHandler(UsersValidationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ValidationResponse<>(USERS_VALIDATION_EXCEPTION_MESSAGE, e.getValidationErrorStatuses()));
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<ValidationResponse<String, UserValidationDto>> FileNotExceptionExceptionHandler(FileNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ValidationResponse<>(FILE_NOT_FOUND_VALIDATION_EXCEPTION_MESSAGE, e.getValidationErrorStatuses()));
    }
}
