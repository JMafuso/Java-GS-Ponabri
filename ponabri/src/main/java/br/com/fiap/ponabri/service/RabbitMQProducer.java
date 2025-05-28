package br.com.fiap.ponabri.service;

import br.com.fiap.ponabri.model.Project;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQProducer {

    private final AmqpTemplate amqpTemplate;

    @Autowired
    public RabbitMQProducer(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void enviarMensagem(String routingKey, Project project) {
        amqpTemplate.convertAndSend("exchange_project", routingKey, project);
    }
}
