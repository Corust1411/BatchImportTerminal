package com.Corust1411.batch.service;

import com.Corust1411.batch.entity.GateInbound;
import com.Corust1411.batch.entity.GateOutbound;
import com.Corust1411.batch.model.GateInboundRequest;
import com.Corust1411.batch.model.GateInboundResponse;
import com.Corust1411.batch.repository.GateInboundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GateService {

    @Autowired
    GateInboundRepository gateInboundRepository;

    public GateInboundResponse Inbound(GateInboundRequest request){
        try {
            GateInboundResponse gateInboundResponse = new GateInboundResponse();

            //System.out.println("Response = " + gateInboundResponse.toString());
            System.out.println("Request = " + request.toString());


            String InboundID = request.getInboundID();
            String MerchantID = request.getMerchantID();
            String TerminalID = request.getTerminalID();
            String GateID = request.getGateID();
            String CardID = request.getCardID();
            String AccessDate = request.getAccessDate();
            String TransDate = request.getTransDate();

            GateInbound gateInbound;
            gateInbound = new GateInbound();

            gateInbound.setInboundID(InboundID);
            gateInbound.setMerchantID(MerchantID);
            gateInbound.setTerminalID(TerminalID);
            gateInbound.setGateID(GateID);
            gateInbound.setCardID(CardID);
            gateInbound.setAccessDate(AccessDate);
            gateInbound.setTransDate(TransDate);
            gateInboundRepository.Create(gateInbound);
            return null;
        } catch (Exception e) {
            System.out.println("GateService_Inbound > error > " + e.getMessage());
            return null;
        }
    }

    /*public GateInboundResponse Outbound(GateInboundRequest request){
        try{

        }catch(Exception e){
            System.out.println("GateService_Outbound > error > "+e.getMessage());
            return null;
        }
    }*/
}
