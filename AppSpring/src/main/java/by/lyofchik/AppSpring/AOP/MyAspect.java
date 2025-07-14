package by.lyofchik.AppSpring.AOP;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MyAspect {
    @Pointcut("within(by.lyofchik.AppSpring.Configuration..*)")
    public void isConfigurationLayer() {
    }

    @Pointcut("@annotation(org.springframework.context.annotation.Bean)")
    public void isBean() {
    }

    @AfterReturning(pointcut = "isConfigurationLayer() && isBean())",
    returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("Конфигурация прошла успешно класс: {} Результат: {}",
                joinPoint.getTarget().getClass().getSimpleName() ,
                result);
    }
}
