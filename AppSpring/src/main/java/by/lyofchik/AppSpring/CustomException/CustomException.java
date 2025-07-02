package by.lyofchik.AppSpring.CustomException;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends Exception {
    private final ApiError apiError;

    public CustomException(ApiError apiError) {
        super(apiError.getDefaultMessage());
        this.apiError = apiError;
    }

    @AllArgsConstructor
    @Getter
    public enum ApiError {
        NICKNAME_IS_BUSY(HttpStatus.CONFLICT, "Такой пользователь уже существует", "/registration"),
        INVALID_LOGIN_OR_PASSWORD(HttpStatus.BAD_REQUEST, "Неверный логин или пароль", "/login"),
        UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "Пользователь не авторизован", "/login");

        private final HttpStatus status;
        private final String defaultMessage;
        private final String redirectUrl;
    }
}
