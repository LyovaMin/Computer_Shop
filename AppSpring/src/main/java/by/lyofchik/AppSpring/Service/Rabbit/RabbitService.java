package by.lyofchik.AppSpring.Service.Rabbit;

import by.lyofchik.AppSpring.Model.DTO.ProductDTO;
import lombok.Data;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Data
public class RabbitService {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${queue.name}")
    private String name;

    public void send(ProductDTO message) {
        amqpTemplate.convertAndSend(name, message);
    }
}
