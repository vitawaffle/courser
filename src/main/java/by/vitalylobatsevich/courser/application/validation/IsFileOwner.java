package by.vitalylobatsevich.courser.application.validation;

import by.vitalylobatsevich.courser.application.validation.validator.ExistingFileIdValidator;
import by.vitalylobatsevich.courser.application.validation.validator.IsFileOwnerValidator;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IsFileOwnerValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface IsFileOwner {

    String message() default "You do not own this file";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
