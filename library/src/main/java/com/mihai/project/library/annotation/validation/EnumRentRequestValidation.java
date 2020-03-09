package com.mihai.project.library.annotation.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class EnumRentRequestValidation implements ConstraintValidator<EnumValidationRentRequest, Enum<?>> {

    private Pattern pattern;

    @Override
    public void initialize(EnumValidationRentRequest constraintAnnotation) {
        try {
            pattern = Pattern.compile(constraintAnnotation.regexp());
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Invalid ", e);
        }
    }

    @Override
    public boolean isValid(Enum<?> enumValue, ConstraintValidatorContext constraintValidatorContext) {
        if (enumValue == null) {
            return true;
        }
        Matcher m = pattern.matcher(enumValue.name());
        return m.matches();
    }
}
