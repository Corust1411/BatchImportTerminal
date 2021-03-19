package com.Corust1411.batch.controller;

import com.Corust1411.batch.model.RabbitResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Corust1411.batch.model.RabbitRequest;
import com.Corust1411.batch.service.RabbitMQService;

@RestController
@RequestMapping(value = "/device")
public class RabbitMQController {

    @Autowired
    RabbitMQService rabbitMQService;
    @Autowired
    RabbitTemplate template;

    @PostMapping(value = "/rabbitmq",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RabbitResponse> producer(@RequestBody RabbitRequest rabbitRequest) {
        try {
            RabbitResponse response = new RabbitResponse();
            response.setRespDesc(null);
            response.setRespCode(null);
            Integer result = rabbitMQService.send(rabbitRequest);

            if (result == 1){
                response.setRespCode("1000");
                response.setRespDesc("success");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }else{
                response.setRespDesc("fail");
                response.setRespCode("0001");
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response);
            }
        }catch(Exception e){
            System.out.println("RabbitMQController_producer > error > " + e.getMessage());
            return null;
        }
    }
}
