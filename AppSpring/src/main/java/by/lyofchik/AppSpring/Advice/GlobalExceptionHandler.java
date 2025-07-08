package by.lyofchik.AppSpring.Advice;

import by.lyofchik.AppSpring.CustomException.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = CustomException.class)
    public ModelAndView defaultErrorHandler(CustomException e){
        log.error(e.getApiError().getDefaultMessage());
        return new ModelAndView("redirect:"+e.getApiError().getRedirectUrl());
    }
}
