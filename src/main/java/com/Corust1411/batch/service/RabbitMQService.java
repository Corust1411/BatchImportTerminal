package com.Corust1411.batch.service;

import com.Corust1411.batch.config.RabbitConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.Corust1411.batch.model.RabbitRequest;

@Service
public class RabbitMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${Corust1411.rabbitmq.exchange}")
    private String Exchange;

    @Value("${Corust1411.rabbitmq.routingkey}")
    private String Routingkey;

    @RabbitListener(queues = "${Corust1411.rabbitmq.queue}")
    public Integer send(RabbitRequest rabbitRequest) {
        try {
            rabbitTemplate.convertAndSend(Exchange,Routingkey,rabbitRequest.getMerchantID());
            rabbitTemplate.convertAndSend(Exchange,Routingkey,rabbitRequest.getTerminalID());
            rabbitTemplate.convertAndSend(Exchange,Routingkey,rabbitRequest.getLocation());
            rabbitTemplate.convertAndSend(Exchange,Routingkey,rabbitRequest.getEffective());
            rabbitTemplate.convertAndSend(Exchange,Routingkey,rabbitRequest.getStatus());
            rabbitTemplate.convertAndSend(Exchange,Routingkey,rabbitRequest.getFlag());
            System.out.println("Send msg = " + rabbitRequest);
            return 1;
        }catch(Exception e){
            System.out.println("RabbitMQSender_send > error > " + e.getMessage());
            return 0;
        }
    }

}