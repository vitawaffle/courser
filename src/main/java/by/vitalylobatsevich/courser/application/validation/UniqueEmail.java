package by.vitalylobatsevich.courser.application.validation;

import by.vitalylobatsevich.courser.application.validation.validator.UniqueEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {

    String message() default "This email is already taken";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
