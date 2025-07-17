package by.lyofchik.AppSpring.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class FileValidation implements ConstraintValidator<FileNotEmpty, MultipartFile> {
    @Override
    public boolean isValid(MultipartFile document, ConstraintValidatorContext context) {
        return !document.isEmpty();
    }
}
