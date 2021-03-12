package com.Corust1411.batch.controller;

import com.Corust1411.batch.entity.GateTransaction;
import com.Corust1411.batch.model.GateInboundRequest;
import com.Corust1411.batch.model.GateInboundResponse;
import com.Corust1411.batch.repository.GateInboundRepository;
import com.Corust1411.batch.service.GateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gate")
public class GateController {
    @Autowired
    private GateInboundRepository gateInboundRepository;

    @Autowired
    private GateService gateService;

    @PostMapping(value = "/in",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GateInboundResponse> PostGateInbound(@RequestBody GateInboundRequest request, GateTransaction gateTransaction) {
        GateInboundResponse gateInboundResponse = new GateInboundResponse();
        gateInboundResponse.setRespCode("1000");
        gateInboundResponse.setRespDesc("success");

        //GateInboundResponse gateInboundResponse = new GateInboundResponse();
        //System.out.println("Response = " + gateInboundResponse.toString());

        gateService.Inbound(request);
        //gateService.Lookup(request);

        return ResponseEntity.ok(gateInboundResponse);
    }
    @PostMapping(value = "/out",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GateInboundResponse> PostGateOutbound(@RequestBody GateInboundRequest request, GateTransaction gateTransaction) {
        GateInboundResponse gateInboundResponse = new GateInboundResponse();
        gateInboundResponse.setRespCode("1000");
        gateInboundResponse.setRespDesc("success");

        gateService.Outbound(request);

        return ResponseEntity.ok(gateInboundResponse);
    }
    @GetMapping(value = "/get-trx/cardid/{{card}}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GateInboundResponse> GetGatefromcardID(@RequestBody GateInboundRequest request, GateTransaction gateTransaction) {
        GateInboundResponse gateInboundResponse = new GateInboundResponse();
        gateInboundResponse.setRespCode("1000");
        gateInboundResponse.setRespDesc("success");


        List<GateTransaction> gate =  gateService.GetfromcardID(request);
        System.out.println("Request : "+ gate);

        return ResponseEntity.ok(gateInboundResponse);
    }
    @GetMapping(value = "/get-trx/date/{{date}}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GateInboundResponse> GetGatefromdate(@RequestBody GateInboundRequest request, GateTransaction gateTransaction) {
        GateInboundResponse gateInboundResponse = new GateInboundResponse();
        gateInboundResponse.setRespCode("1000");
        gateInboundResponse.setRespDesc("success");


        List<GateTransaction> gate =  gateService.Getfromdate(request);
        System.out.println("Request : "+ gate);

        return ResponseEntity.ok(gateInboundResponse);
    }
}
