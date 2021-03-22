package com.Corust1411.batch.Service;

import com.Corust1411.batch.Entity.GateInbound;
import com.Corust1411.batch.Entity.GateOutbound;
import com.Corust1411.batch.Entity.GateTransaction;
import com.Corust1411.batch.Model.GateInboundRequest;
import com.Corust1411.batch.Model.GateInboundResponse;
import com.Corust1411.batch.Repository.GateInboundRepository;
import com.Corust1411.batch.Repository.GateOutboundRepository;
import com.Corust1411.batch.Repository.GateTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

            String InboundID = request.getInboundID();
            String CardID = request.getCardID();
            String InboundDate = request.getAccessDate();
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

            String OutboundID = request.getOutboundID();
            String MerchantID = request.getMerchantID();
            String TerminalID = request.getTerminalID();
            String GateID = request.getGateID();
            String CardID = request.getCardID();
            String AccessDate = request.getOutboundDate();

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

            String CardID = request.getCardID();
            String OutboundID = request.getOutboundID();
            String OutboundDate = request.getOutboundDate();

            gateTransaction.setCardID(CardID);
            gateTransaction.setOutboundID(OutboundID);
            gateTransaction.setOutboundDate(OutboundDate);
            gateTransactionRepository.Update(gateTransaction);
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
    public List<GateTransaction> GetTransactionByCardID(String card){
        try{
            List<GateTransaction> gate = gateTransactionRepository.GetfromcardID(card);
            return gate;
        }catch(Exception e){
            System.out.println("GateService_GetBycardID > error > " + e.getMessage());
            return null;
        }
    }
    public List<GateTransaction> GetTransactionByDate(String date){
        try{
            List<GateTransaction> gate = gateTransactionRepository.Getfromdate(date);
            return gate;
        }catch(Exception e){
            System.out.println("GateService_GetBydate > error > " + e.getMessage());
            return null;
        }
    }
    public Integer DelTransactionByCardID(String card){
        try{
            Integer gate = gateTransactionRepository.DeleteTransactionByCardId(card);
            return gate;
        }catch(Exception e){
            System.out.println("GateService_DelTransactionByCardID > error > " + e.getMessage());
            return null;
        }
    }
}
