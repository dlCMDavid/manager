package com.ecommerce.manager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
public class CustomValidationException extends RuntimeException {

    public CustomValidationException(BindingResult bindingResult)
    {
        super(String.join(",", getValidationMessage(bindingResult)));
    }

    private static String getValidationMessage(ObjectError error) {
        if (error instanceof FieldError fieldError) {
            Object invalidValue = fieldError.getRejectedValue();
            String message = fieldError.getDefaultMessage();
            return String.format("%s, but it was %s", message, invalidValue);
        }
        return String.format("%s: %s", error.getObjectName(), error.getDefaultMessage());
    }

    private static List<String> getValidationMessage(BindingResult bindingResult) {
        return bindingResult.getAllErrors()
                .stream()
                .map(CustomValidationException::getValidationMessage)
                .collect(Collectors.toList());
    }
}
