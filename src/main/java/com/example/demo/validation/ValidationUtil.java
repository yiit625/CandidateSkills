package com.example.demo.validation;

import com.example.demo.domain.User;
import com.example.demo.exception.UserNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ValidationUtil {
    public void rejectRequestIfUserIdIsInvalid(String passedUserId, List<User> foundUsers) {

        List<String> foundUserIds = foundUsers.stream().map(User::getId).toList();

        if (!foundUserIds.contains(passedUserId)) throw new UserNotFoundException(passedUserId);
    }

    public boolean checkIfExceptionMessagesExist(ValidationStatus validationStatus) {
        return !validationStatus.getExceptionMessages().isEmpty();
    }

    public List<String> unwrapValidationMessages(List<Optional<String>> validationErrorMessages) {
        return validationErrorMessages.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

}
