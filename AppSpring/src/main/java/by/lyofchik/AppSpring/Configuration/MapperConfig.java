package by.lyofchik.AppSpring.Configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class MapperConfig {
    @Bean
    public static ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT) // стратегия соответствие исходного и целевого обьекта
                .setFieldMatchingEnabled(true) // включение приватных полей
                .setSkipNullEnabled(true); // пропуск null полей
        // setFieldAccessLevel установка доступа к полям
        // addMapper для добавления маппера для конкретных полей
        return modelMapper;
    }
}
