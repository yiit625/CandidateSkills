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
public class ValidationStatus<T1, T2> {
    private T1 validatedId;
    private T2 validatedObject;
    private List<String> exceptionMessages;
}
