package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserValidationDto {
    private String id;
    private String name;
    private String description;
    private LocalDateTime dateTime;

    public static UserValidationDto fromUserCommandData(UserModel userCommandData) {
        return new UserValidationDto(
                userCommandData.getId(),
                userCommandData.getName(),
                userCommandData.getDescription(),
                userCommandData.getDateTime()
        );
    }

    public static List<UserValidationDto> fromUserCommandDataList(List<UserModel> userCommandDataList) {
        return userCommandDataList.stream().map(UserValidationDto::fromUserCommandData).toList();
    }
}
