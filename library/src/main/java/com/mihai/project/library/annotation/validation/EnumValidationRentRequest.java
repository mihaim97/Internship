package com.mihai.project.library.annotation.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EnumRentRequestValidation.class)
public @interface EnumValidationRentRequest {
    String regexp();

    String message() default "must match {regexp}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
