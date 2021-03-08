package com.Corust1411.batch.controller;

import com.Corust1411.batch.model.GateInboundRequest;
import com.Corust1411.batch.model.GateInboundResponse;
import com.Corust1411.batch.repository.DeviceAccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gate")
public class GateController {
    @Autowired
    private DeviceAccessRepository deviceAccessRepository;

    @PostMapping(value = "/in",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GateInboundResponse> PostGateInbound(@RequestBody GateInboundRequest request) {
        GateInboundResponse gateInboundResponse = new GateInboundResponse();
        gateInboundResponse.setRespCode("1000");
        gateInboundResponse.setRespDesc("success");
        System.out.println("Request = " + request.toString());
        System.out.println("Response = " + gateInboundResponse.toString());
        return ResponseEntity.ok(gateInboundResponse);

    }


}