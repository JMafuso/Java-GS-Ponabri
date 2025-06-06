package br.com.fiap.ponabri.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "ponabri.queue";

    @Bean
    public Queue ponabriQueue() {
        return new Queue(QUEUE_NAME, true);
    }
}
