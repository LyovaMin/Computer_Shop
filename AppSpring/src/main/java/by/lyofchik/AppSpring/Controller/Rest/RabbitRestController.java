package by.lyofchik.AppSpring.Controller.Rest;

import by.lyofchik.AppSpring.Model.DTO.ProductDTO;
import by.lyofchik.AppSpring.Service.Rabbit.RabbitService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rabbit")
@AllArgsConstructor
@Slf4j
public class RabbitRestController {
    RabbitService rabbitService;

    @PostMapping
    public void send(@RequestBody ProductDTO message) {
        log.info("Sending message: {}", message);
        rabbitService.send(message);
        log.info("Sent message: {}", message);
    }

    @GetMapping
    public void send2(@RequestParam String productName, @RequestParam int price) {
        log.info("Sending2 message: {}", productName);
        rabbitService.send(ProductDTO.builder()
                        .productName(productName)
                        .price(price)
                .build());
        log.info("Sent 2 message: {}", productName);
    }
}
