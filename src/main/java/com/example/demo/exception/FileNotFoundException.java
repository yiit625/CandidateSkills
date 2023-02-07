package com.example.demo.exception;

import com.example.demo.dto.UserValidationDto;
import com.example.demo.validation.ValidationStatus;
import lombok.Getter;

import java.util.List;

@Getter
public class FileNotFoundException extends RuntimeException {

    private static final String FILE_NOT_FOUND_VALIDATION_EXCEPTION_MESSAGE = "File not found!";
    private final List<ValidationStatus<String, UserValidationDto>> validationErrorStatuses;

    public FileNotFoundException() {
        this.validationErrorStatuses = List.of(new ValidationStatus<>(
                null,
                null,
                List.of(FILE_NOT_FOUND_VALIDATION_EXCEPTION_MESSAGE)));
    }
}
