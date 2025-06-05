package br.com.fiap.ponabri.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import static org.mockito.Mockito.*;

public class RabbitMQConsumerServiceTest {

    private RabbitMQConsumerService consumerService;

    @BeforeEach
    public void setUp() {
        consumerService = Mockito.spy(new RabbitMQConsumerService());
    }

    @Test
    public void testReceiveMessage() {
        String message = "Test message";

        consumerService.receiveMessage(message);

        verify(consumerService, times(1)).receiveMessage(message);
    }
}
