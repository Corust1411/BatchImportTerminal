package com.Corust1411.batch.service;

import com.Corust1411.batch.config.AppConfig;
import com.Corust1411.batch.entity.Device;
import com.Corust1411.batch.model.CreateDeviceRequest;
import com.Corust1411.batch.model.DeleteDeviceRequest;
import com.Corust1411.batch.model.DeviceResponse;
import com.Corust1411.batch.model.UpdateDeviceRequest;
import com.Corust1411.batch.repository.DeviceListRepository;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.opencsv.CSVWriter;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DeviceService{
    @Autowired
    DeviceListRepository deviceListRepository;
    @Autowired
    AppConfig appConfig;

    public Boolean CreateDevice(CreateDeviceRequest request){
        try{
            Device device = new Device();

            String MerchantID = request.getMerchantID();
            String TerminalID = request.getTerminalID();
            String Location = request.getLocation();
            String Effective = request.getEffective();
            String Status = request.getStatus();
            String Flag = request.getFlag();

            device.setMerchantID(MerchantID);
            device.setTerminalID(TerminalID);
            device.setLocation(Location);
            device.setEffective(Effective);
            device.setStatus(Status);
            device.setFlag(Flag);
            Boolean result = deviceListRepository.Insert(device);
            return result;
        }catch(Exception e){
            System.out.println("DeviceService_CreateDevice > error > " + e.getMessage());
        }
        return null;
    }
    public Integer UpdateDevice(UpdateDeviceRequest request){
        try{
            Device device = new Device();

            String MerchantID = request.getMerchantID();
            String TerminalID = request.getTerminalID();
            String Location = request.getLocation();
            String Effective = request.getEffective();
            String Status = request.getStatus();
            String Flag = request.getFlag();

            device.setMerchantID(MerchantID);
            device.setTerminalID(TerminalID);
            device.setLocation(Location);
            device.setEffective(Effective);
            device.setStatus(Status);
            device.setFlag(Flag);
            device.setTimestamp(new Date());
            Integer result = deviceListRepository.UpdateDeviceByRequest(device);
            return result;
        }catch(Exception e){
            System.out.println("DeviceService_UpdateDevice > error > " + e.getMessage());
            return null;
        }
    }
    public Integer DeleteDevice(DeleteDeviceRequest request){
        try{
            Device device = new Device();

            String MerchantID = request.getMerchantID();
            String TerminalID = request.getTerminalID();

            device.setMerchantID(MerchantID);
            device.setTerminalID(TerminalID);
            Integer result = deviceListRepository.DeleteDeviceByRequest(device);
            return result;
        }catch(Exception e){
            System.out.println("DeviceService_UpdateDevice > error > " + e.getMessage());
            return null;
        }
    }
    public List<Device> GetListDevice(){
        try{
            List<Device> result = deviceListRepository.GetListByRequest();
            return result;
        }catch(Exception e){
            System.out.println("DeviceService_GetListDevice > error > " + e.getMessage());
            return null;
        }
    }
    public List<Device> ExportListDevice(){
        try{
            List<Device> result = deviceListRepository.ExportListByRequest();
            return result;
        }catch(Exception e){
            System.out.println("DeviceService_ExportListDevice > error > " + e.getMessage());
            return null;
        }
    }
    public void CovertintoCSV(ArrayList<Device> deviceList) {
        try{
            String CSV_SEPARATOR = ",";
            String Pattern = "yyyyMMdd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Pattern);
            String date = simpleDateFormat.format(new Date());
            String export_file = appConfig.getExportDirectory() + appConfig.getExportFile().replace("{DATE}",date);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(export_file), "UTF-8"));

            String[] header = {"merchantid","terminalid","location","effective","status","flag","time_stamp"};

            String formattedString = Arrays.toString(header)
                    .replace("[", "")
                    .replace("]", "")
                    .trim();
            if(deviceList != null) {
                StringBuffer oneLine = new StringBuffer();

                oneLine.append(formattedString);
                bw.write(oneLine.toString());
                bw.newLine();
            }

            for (Device device : deviceList) {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(device.getMerchantID().trim().length() == 0? "" : device.getMerchantID());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(device.getTerminalID().trim().length() == 0? "" : device.getTerminalID());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(device.getLocation().trim().length() == 0? "" : device.getLocation());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(device.getEffective() == null? "" : device.getEffective());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(device.getStatus().trim().length() == 0? "" : device.getStatus());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(device.getFlag().trim().length() == 0? "" : device.getFlag());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(device.getTimestamp() == null? "" : device.getTimestamp());

                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }catch(Exception e){
            System.out.println("DeviceService_ConvertintoCSV > error > " + e.getMessage());
        }
    }
}
