package by.vitalylobatsevich.courser.application.validation;

import by.vitalylobatsevich.courser.application.validation.validator.ExistingLanguageIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExistingLanguageIdValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistingLanguageId {

    String message() default "There is no such language id";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
