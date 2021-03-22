package com.Corust1411.batch.Service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.Corust1411.batch.Model.RabbitRequest;

@Service
public class RabbitMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${Corust1411.rabbitmq.exchange}")
    private String Exchange;

    @Value("${Corust1411.rabbitmq.routingkey}")
    private String Routingkey;

    public void send(RabbitRequest request) {
        try {
            rabbitTemplate.convertAndSend(Exchange,Routingkey,request);

            System.out.println("Send msg = " + request);
        }catch(Exception e){
            System.out.println("RabbitMQService_send > error > " + e.getMessage());
        }
    }

}