package com.Corust1411.batch.process;

import com.Corust1411.batch.config.AppConfig;
import com.Corust1411.batch.entity.GateInbound;
import com.Corust1411.batch.entity.GateTransaction;
import com.Corust1411.batch.repository.GateInboundRepository;
import com.Corust1411.batch.repository.GateTransactionRepository;
import com.Corust1411.batch.service.GateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DeviceAccessWorker implements ApplicationRunner {
    @Autowired
    AppConfig appConfig;
    @Autowired
    GateInboundRepository deviceAccessRepository;
    @Autowired
    GateService gateService;

    @Transactional
    @Override
    public void run(ApplicationArguments args) throws Exception {
        try{
            //System.out.println("Batch Access Starting");


        }catch(Exception e){
            System.out.println("main > error > "+e.getMessage());
        }finally {
            //System.out.println("Batch Access Finished");
        }
    }
    public void InsertAccess(){
        try{
            GateInbound gateInbound = new GateInbound();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
            DateFormat dateFormat2 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            Date date = new Date();

            String InboundID = "EK000001"+dateFormat2.format(date);
            String MerchantID = "110070357505555";
            String TerminalID = "0553490";
            String AccessDate = dateFormat.format(date);
            String CardID = "0234";
            String GateID = "9999";

            //gateInbound = new GateInbound();
            gateInbound.setInboundID(InboundID);
            gateInbound.setMerchantID(MerchantID);
            gateInbound.setTerminalID(TerminalID);
            gateInbound.setGateID(GateID);
            gateInbound.setCardID(CardID);
            gateInbound.setAccessDate(AccessDate);
            gateInbound.setTransDate(new Date());
            deviceAccessRepository.Insert(gateInbound);
        }catch(Exception e){
            System.out.println("DeviceAccessWorker_InsertAccess > error > "+e.getMessage());
        }
    }


}