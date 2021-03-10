package com.Corust1411.batch.service;

import com.Corust1411.batch.entity.GateInbound;
import com.Corust1411.batch.entity.GateOutbound;
import com.Corust1411.batch.entity.GateTransaction;
import com.Corust1411.batch.model.GateInboundRequest;
import com.Corust1411.batch.model.GateInboundResponse;
import com.Corust1411.batch.repository.GateInboundRepository;
import com.Corust1411.batch.repository.GateOutboundRepository;
import com.Corust1411.batch.repository.GateTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class GateService {

    @Autowired
    GateInboundRepository gateInboundRepository;
    @Autowired
    GateTransactionRepository gateTransactionRepository;
    @Autowired
    GateOutboundRepository gateOutboundRepository;

    public GateInboundResponse Inbound(GateInboundRequest request){
        try {
            GateInbound gateInbound = new GateInbound();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
            DateFormat dateFormat2 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            Date date = new Date();

            String InboundID = request.getInboundID();
            String MerchantID = request.getMerchantID();
            String TerminalID = request.getTerminalID();
            String GateID = request.getGateID();
            String CardID = request.getCardID();
            String AccessDate = request.getAccessDate();

            gateInbound.setInboundID(InboundID);
            gateInbound.setMerchantID(MerchantID);
            gateInbound.setTerminalID(TerminalID);
            gateInbound.setGateID(GateID);
            gateInbound.setCardID(CardID);
            gateInbound.setAccessDate(AccessDate);
            gateInbound.setTransDate(new Date());
            gateInboundRepository.Insert(gateInbound);
            CreateTransaction(request);
        } catch (Exception e) {
            System.out.println("GateService_Inbound > error > " + e.getMessage());
        }
        return null;
    }
    public GateInboundResponse CreateTransaction(GateInboundRequest request){
        try{
            GateTransaction gateTransaction = new GateTransaction();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
            DateFormat dateFormat2 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            Date date = new Date();

            String InboundID = request.getInboundID();
            String CardID = request.getCardID();
            String InboundDate = dateFormat.format(date);
            Boolean IsOutbound = false;

            gateTransaction.setInboundID(InboundID);
            gateTransaction.setCardID(CardID);
            gateTransaction.setInboundDate(InboundDate);
            gateTransaction.setIsOutbound(IsOutbound);
            gateTransactionRepository.Insert(gateTransaction);
        }catch(Exception e){
            System.out.println("GateService_Transaction > error > " + e.getMessage());
        }
        return null;
    }
    public GateInboundResponse Outbound(GateInboundRequest request){
        try{
            GateOutbound gateOutbound = new GateOutbound();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
            DateFormat dateFormat2 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            Date date = new Date();

            String OutboundID = request.getOutboundID();
            String MerchantID = request.getMerchantID();
            String TerminalID = request.getTerminalID();
            String GateID = request.getGateID();
            String CardID = request.getCardID();
            String AccessDate = dateFormat.format(date);

            gateOutbound.setOutboundID(OutboundID);
            gateOutbound.setMerchantID(MerchantID);
            gateOutbound.setTerminalID(TerminalID);
            gateOutbound.setGateID(GateID);
            gateOutbound.setCardID(CardID);
            gateOutbound.setAccessDate(AccessDate);
            gateOutbound.setTransDate(new Date());
            gateOutboundRepository.Insert(gateOutbound);
            UpdateTransaction(request);
        }catch(Exception e){
            System.out.println("GateService_Outbound > error > " + e.getMessage());
        }
        return null;
    }
    public GateInboundResponse UpdateTransaction(GateInboundRequest request){
        try{
            GateTransaction gateTransaction = new GateTransaction();

            Boolean IsOutbound = true;
            String CardID = request.getCardID();
            String OutboundID = request.getOutboundID();
            String OutboundDate = request.getOutboundDate();

            gateTransaction.setCardID(CardID);
            gateTransaction.setOutboundID(OutboundID);
            gateTransaction.setOutboundDate(OutboundDate);
            gateTransaction.setIsOutbound(IsOutbound);
            gateTransactionRepository.Update(gateTransaction);
            System.out.println("Updated");
        }catch(Exception e){
            System.out.println("GateService_UpdateTransaction > error > " + e.getMessage());
        }
        return null;
    }
    public GateTransaction Lookup(GateInboundRequest request){
        try{
            String CardID = request.getCardID();
            GateTransaction gateTransaction1 = gateTransactionRepository.GetItem(CardID);
            return gateTransaction1;
        }catch(Exception e){
            System.out.println("GateService_Lookup > error > " + e.getMessage());
        }
        return null;
    }

}
