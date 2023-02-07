package com.example.demo.exception;

import com.example.demo.dto.UserValidationDto;
import com.example.demo.validation.ValidationStatus;
import lombok.Getter;

import java.util.List;
@Getter
public class UsersValidationException  extends RuntimeException {
    private final List<ValidationStatus<String, UserValidationDto>> validationErrorStatuses;

    public UsersValidationException(List<ValidationStatus<String, UserValidationDto>> validationErrorStatuses) {
        this.validationErrorStatuses = validationErrorStatuses;
    }
}
