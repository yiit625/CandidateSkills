package com.example.demo.validation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ValidationResponse<T1, T2> {
    private String responseMessage;
    private List<ValidationStatus<T1, T2>> validatedObjects;
}
