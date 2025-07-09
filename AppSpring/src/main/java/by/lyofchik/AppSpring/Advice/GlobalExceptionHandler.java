package by.lyofchik.AppSpring.Advice;

import by.lyofchik.AppSpring.CustomException.CustomException;
import by.lyofchik.AppSpring.Model.DTO.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
//    @ExceptionHandler(value = CustomException.class)
//    public ModelAndView defaultErrorHandler(CustomException e){
//        log.error(e.getApiError().getDefaultMessage());
//        return new ModelAndView("redirect:" + e.getApiError().getRedirectUrl());
//    }


    @ExceptionHandler(value = CustomException.class)
    public CommonResponse<Object> handleCustomException(CustomException e) {
      return CommonResponse.builder()
              .errorDescription(e.getMessage())
              .errorCode("-1")
              .data(null)
              .build();
    }
}
