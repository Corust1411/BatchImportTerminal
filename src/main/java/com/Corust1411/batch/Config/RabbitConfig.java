package com.Corust1411.batch.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${Corust1411.rabbitmq.queue}")
    String QueueName;

    @Value("${Corust1411.rabbitmq.exchange}")
    String Exchange;

    @Value("${Corust1411.rabbitmq.routingkey}")
    String Routingkey;

    @Bean
    Queue queue() {
        return new Queue(QueueName);
    }
    @Bean
    DirectExchange exchange() {
        return new DirectExchange(Exchange);
    }
    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(Routingkey);
    }
    @Bean
    public MessageConverter jsonMessageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }
    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbit = new RabbitTemplate(connectionFactory);
        rabbit.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbit;
    }

}