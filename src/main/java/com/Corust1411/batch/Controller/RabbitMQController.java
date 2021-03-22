package com.Corust1411.batch.Controller;

import com.Corust1411.batch.Model.RabbitResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Corust1411.batch.Model.RabbitRequest;
import com.Corust1411.batch.Service.RabbitMQService;

@RestController
@RequestMapping(value = "/device")
public class RabbitMQController {

    @Autowired
    RabbitMQService rabbitMQService;

    //@Autowired
    //RabbitTemplate template;

    @PostMapping(value = "/rabbitmq",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RabbitResponse> producer(@RequestBody RabbitRequest rabbitRequest) {
        RabbitResponse response = new RabbitResponse();
        try {
            rabbitMQService.send(rabbitRequest);
            response.setRespCode("1000");
            response.setRespDesc("success");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch(Exception e){
            System.out.println("RabbitMQController_producer > error > " + e.getMessage());
            response.setRespDesc("fail");
            response.setRespCode("0001");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response);
        }
    }
}
