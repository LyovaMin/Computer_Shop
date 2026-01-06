package by.lyofchik.AppSpring.Feign;

import by.lyofchik.AppSpring.Configuration.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "money", url = "", configuration = FeignConfig.class)
public interface MoneyFeign {

}
