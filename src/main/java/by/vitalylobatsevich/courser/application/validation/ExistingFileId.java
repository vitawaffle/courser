package by.vitalylobatsevich.courser.application.validation;

import by.vitalylobatsevich.courser.application.validation.validator.ExistingFileIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExistingFileIdValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistingFileId {

    String message() default "There is no such file id";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
