package com.Corust1411.batch.process;

import com.Corust1411.batch.Application;
import com.Corust1411.batch.config.AppConfig;
import com.Corust1411.batch.entity.Device;
import com.Corust1411.batch.repository.DeviceListRepository;
import jdk.nashorn.internal.ir.Terminal;
import org.hibernate.engine.spi.EffectiveEntityGraph;
import org.hibernate.sql.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class DeviceWorker implements ApplicationRunner{
    @Autowired
    AppConfig appConfig;
    @Autowired
    DeviceListRepository deviceListRepository;

    @Transactional
    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            //System.out.println("Batch Import Starting");
            ////

            //InsertDB();
            //List<Device> deviceList = deviceListRepository.GetList();
            //

            /*for(Device dev:deviceList){
                System.out.println(dev);
            }*/

            //Device device1 = deviceListRepository.GetItem("000001997000003","EK100053");
            //System.out.println(device1);
            //device.setResult("success");
            //deviceListRepository.Insert(device);

        } catch (Exception e) {
            System.out.println("main > Error > " + e.getMessage());
        }
        /*finally {
            System.out.println("Batch Import Finished");
        }*/
    }


    public void InsertDB(){
        try{
            Device device = new Device();
            String path = GetFileName();
            String line = "";
            int count=0;
            BufferedReader reader = new BufferedReader(new FileReader(path));
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                String MerchantID = values[0];
                String TerminalID = values[1];
                String Location = values[2];
                String Effective = values[3];
                String Status = values[4];
                String Flag = values[5];
                String Result = "Undefined";
                if(count==0){
                    if (!ValidateHeader(values)) {
                        System.out.println("Header mismatch!");
                        break;
                    }else{
                        System.out.println(values[0]+"|"+values[1]+"|"+values[2]+"|"+values[3]+"|"+values[4]+"|"+values[5]+"|");

                    }
                }else{
                    String ValidateResult = ValidateDetail(values);
                    System.out.print(values[0]+"|"+values[1]+"|"+values[2]+"|"+values[3]+"|"+values[4]+"|"+values[5]+"|");
                    if(ValidateResult==null){
                        Result = "successful";
                        System.out.println("Result : successful");
                        if (count==0);
                        else{
                            Device device1 = deviceListRepository.GetItem(MerchantID, TerminalID);
                            if(device1 != null) {
                                device1.setLocation(Location);
                                device1.setEffective(Effective);
                                device1.setStatus(Status);
                                device1.setFlag(Flag);
                                device1.setResult(Result);
                                device1.setTimestamp(new Date());
                                deviceListRepository.Update(device1);
                            }else{
                                device = new Device();
                                device.setMerchantID(MerchantID);
                                device.setTerminalID(TerminalID);
                                device.setLocation(Location);
                                device.setEffective(Effective);
                                device.setStatus(Status);
                                device.setFlag(Flag);
                                device.setResult("successful");
                                device.setTimestamp(new Date());
                                deviceListRepository.Insert(device);
                            }
                        }
                    }else {
                        System.out.println("Result : " + ValidateResult);
                    }
                }
                count++;

            }
        }catch(Exception e){
            System.out.println("InsertDB > error > "+e.getMessage());
        }

    }
    public String GetFileName(){
        String Pattern = "yyyyMMdd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Pattern);
        String OutputName;
        String date = simpleDateFormat.format(new Date());

        OutputName = appConfig.getSourceDirectory() + appConfig.getSourceFile().replace("{DATE}", date);

        return OutputName;
    }
    public void ReadFile() {
        try {
            String path = GetFileName();
            String line = "";
            int count=0;
            BufferedReader reader = new BufferedReader(new FileReader(path));
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if(count==0){
                    if (!ValidateHeader(values)) {
                        System.out.println("Header mismatch!");
                        break;
                    }else{
                        System.out.println(values[0]+"|"+values[1]+"|"+values[2]+"|"+values[3]+"|"+values[4]+"|"+values[5]+"|");

                    }
                }else{
                    String ValidateResult = ValidateDetail(values);
                    System.out.print(values[0]+"|"+values[1]+"|"+values[2]+"|"+values[3]+"|"+values[4]+"|"+values[5]+"|");
                    if(ValidateResult==null){
                        System.out.println("Result : successful");
                    }else{
                        System.out.println("Result : "+ValidateResult);
                    }
                }
                count++;
            }
        }catch(Exception e){
            System.out.println("ReadFile > error > "+e.getMessage());
        }
    }
    public boolean ValidateHeader(String[] values){
        Boolean result=true;
        try {
            if (!values[0].contains("merchantid")) result = false;
            if (!values[1].contains("terminalid")) result = false;
            if (!values[2].contains("location")) result = false;
            if (!values[3].contains("effective")) result = false;
            if (!values[4].contains("status")) result = false;
            if (!values[5].contains("flag")) result = false;
        }catch(Exception e){
            System.out.println("ValidateHeader > error > "+e.getMessage());
        }
        return result;
    }
    public String ValidateDetail(String[] values){
        String detail=null;
        try {
            if (values[0].length() > 15) {
                detail = "merchantID incorrect";
                return detail;
            }
            if (values[1].length() > 8) {
                detail = "TerminalID incorrect";
                return detail;
            }
            if (values[2].length() > 100) {
                detail = "Location incorrect";
                return detail;
            }
            if (values[3].length() > 8) {
                detail = "Effective incorrect";
                return detail;
            }
            if (values[4].length() > 1) {
                detail = "Status incorrect";
                return detail;
            }
            if (values[5].length() > 1) {
                detail = "Flag incorrect";
                return detail;
            }
        }catch(Exception e){
            System.out.println("Validate > error > "+e.getMessage());
        }
        return detail;
    }
}
