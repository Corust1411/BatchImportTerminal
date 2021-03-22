package com.Corust1411.batch.Controller;

import com.Corust1411.batch.Entity.GateTransaction;
import com.Corust1411.batch.Model.*;
import com.Corust1411.batch.Repository.GateInboundRepository;
import com.Corust1411.batch.Service.GateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<GateInboundResponse> PostGateInbound(@RequestBody GateInboundRequest request) {
        try{
            GateInboundResponse gateInboundResponse = new GateInboundResponse();
            gateInboundResponse.setRespCode("1000");
            gateInboundResponse.setRespDesc("success");

            gateService.Inbound(request);

            return ResponseEntity.ok(gateInboundResponse);
        }catch(Exception e){
            System.out.println("GateController_PostGateInbound > error > " + e.getMessage());
            return null;
        }
    }
    @PostMapping(value = "/out",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GateInboundResponse> PostGateOutbound(@RequestBody GateInboundRequest request) {
        try{
            GateInboundResponse gateInboundResponse = new GateInboundResponse();
            gateInboundResponse.setRespCode("1000");
            gateInboundResponse.setRespDesc("success");

            gateService.Outbound(request);

            return ResponseEntity.ok(gateInboundResponse);
        }catch(Exception e){
            System.out.println("GateController_PostGateOutbound > error > " + e.getMessage());
            return null;
        }

    }
    @GetMapping(value = "/transaction/cardid/{cardID}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GateTransactionByCardIdResponse> GetTransactionsfromcardID(@PathVariable("cardID") String cardID) {
        try{
            GateTransactionByCardIdResponse response = new GateTransactionByCardIdResponse();
            response.setRespCode(null);
            response.setRespDesc(null);
            List<GateTransaction> gate =  gateService.GetTransactionByCardID(cardID);

            if (gate.size() == 0){
                response.setRespCode("0001");
                response.setRespDesc("fail");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }else{
                response.setRespCode("1000");
                response.setRespDesc("success");
                response.setGateTransactions(gate);
                return ResponseEntity.ok(response);
            }
        }catch(Exception e){
            System.out.println("GateController_GetTransactionfromCardID > error > " + e.getMessage());
            return null;
        }
    }
    @GetMapping(value = "/transaction/date/{date}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GateTransactionByDateResponse> GetTransactionsfromdate(@PathVariable("date") String date) {
        try{
            GateTransactionByDateResponse response = new GateTransactionByDateResponse();
            response.setRespCode(null);
            response.setRespDesc(null);
            List<GateTransaction> gate =  gateService.GetTransactionByDate(date);

            if(gate.size() == 0){
                response.setRespDesc("fail");
                response.setRespCode("0001");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }else{
                response.setRespDesc("success");
                response.setRespCode("1000");
                response.setGateTransactions(gate);
                return ResponseEntity.ok(response);
            }
        }catch(Exception e){
            System.out.println("GateController_GetTransactionfromdate > error > " + e.getMessage());
            return null;
        }
    }
    @DeleteMapping(value = "/transaction/cardid/{cardID}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeleteTransactionByCardIdResponse> DelTransactionByCardID(@PathVariable("cardID") String card) {
        try{
            DeleteTransactionByCardIdResponse delete = new DeleteTransactionByCardIdResponse();
            delete.setRespCode(null);
            delete.setRespDesc(null);
            Integer gate = gateService.DelTransactionByCardID(card);

            if(gate > 0){
                delete.setRespDesc("success");
                delete.setRespCode("1000");
                return ResponseEntity.ok(delete);
            }else{
                delete.setRespDesc("fail");
                delete.setRespCode("0001");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(delete);
            }
        }catch(Exception e){
            System.out.println("GateController_GetTransactionfromdate > error > " + e.getMessage());
            return null;
        }
    }
}
