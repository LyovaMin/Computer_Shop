package by.lyofchik.AppSpring.Validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FileValidation.class)
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FileNotEmpty {
    String message() default "Product is empty";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
