package br.com.fiap.ponabri.service;

import br.com.fiap.ponabri.config.RabbitMQConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RabbitMQProducerServiceTest {

    private RabbitTemplate rabbitTemplate;
    private RabbitMQProducerService producerService;

    @BeforeEach
    public void setUp() {
        rabbitTemplate = mock(RabbitTemplate.class);
        producerService = new RabbitMQProducerService(rabbitTemplate);
    }

    @Test
    public void testSendMessage() {
        String message = "Test message";

        producerService.sendMessage(message);

        ArgumentCaptor<String> routingKeyCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);

        verify(rabbitTemplate, times(1)).convertAndSend(routingKeyCaptor.capture(), messageCaptor.capture());

        assertEquals(RabbitMQConfig.QUEUE_NAME, routingKeyCaptor.getValue());
        assertEquals(message, messageCaptor.getValue());
    }
}
