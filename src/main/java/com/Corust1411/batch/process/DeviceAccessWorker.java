package com.Corust1411.batch.process;

import com.Corust1411.batch.config.AppConfig;
import com.Corust1411.batch.entity.Device;
import com.Corust1411.batch.entity.DeviceAccess;
import com.Corust1411.batch.repository.DeviceAccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Access;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DeviceAccessWorker implements ApplicationRunner {
    @Autowired
    AppConfig appConfig;
    @Autowired
    DeviceAccessRepository deviceAccessRepository;

    @Transactional
    @Override
    public void run(ApplicationArguments args) throws Exception {
        try{
            System.out.println("Starting");

            Insert();

        }catch(Exception e){
            System.out.println("main > error > "+e.getMessage());
        }finally {
            System.out.println("Finished");
        }
    }

    public void Insert(){
        try{
            DeviceAccess deviceAccess;
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS ");
            Date date = new Date();

            String Merchant = "110070357505555";
            String Terminal = "00216410";
            String Access_time = dateFormat.format(date);
            String Card_ID = "1411";
            String Gate_ID = "134";


            deviceAccess = new DeviceAccess();
            deviceAccess.setMerchant(Merchant);
            deviceAccess.setTerminal(Terminal);
            deviceAccess.setAccessTime(Access_time);
            deviceAccess.setCardID(Card_ID);
            deviceAccess.setGateID(Gate_ID);
            deviceAccess.setTransDate(new Date());
            deviceAccessRepository.Insert(deviceAccess);

        }catch(Exception e){
            System.out.println("Insert > error > "+e.getMessage());
        }
    }


}