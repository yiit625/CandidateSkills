package com.example.demo.validation;

import com.example.demo.domain.User;
import com.example.demo.dto.UserValidationDto;
import com.example.demo.exception.UsersValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Component
public class UserValidationFacade {
    private final ValidationUtil validationUtil;

    private final UserRepositoryValidator userRepositoryValidator;

    public void rejectRequestIfUserIdIsInvalid(String passedUserId, List<User> foundUsers) {
        validationUtil.rejectRequestIfUserIdIsInvalid(passedUserId, foundUsers);
    }

    public void validateUserDeleteAction(String userId){
        validateIfUserExist(userId);
    }

    public void validateIfUserExist(String userId) {
        retrieveExceptionMessageIfExists(performUserIdValidation(userId));
    }

    private void verifyValidationStatuses(List<ValidationStatus<String, UserValidationDto>> validationErrors) {
        if (!validationErrors.isEmpty()) {
            throw new UsersValidationException(validationErrors);
        }
    }

    private void retrieveExceptionMessageIfExists(ValidationStatus<String, UserValidationDto> validationStatus){
        if(validationUtil.checkIfExceptionMessagesExist(validationStatus)){
            List<ValidationStatus<String, UserValidationDto>> validationStatuses = List.of(validationStatus);
            verifyValidationStatuses(validationStatuses);
        }
    }

    private ValidationStatus<String, UserValidationDto> performUserIdValidation(String userId) {
        Optional<String> performedValidation =
                userRepositoryValidator.checkIfUserIdExistsInRepository(userId);

        return new ValidationStatus.ValidationStatusBuilder<String, UserValidationDto>()
                .validatedId(userId)
                .exceptionMessages(performedValidation.stream().toList())
                .build();
    }
}
