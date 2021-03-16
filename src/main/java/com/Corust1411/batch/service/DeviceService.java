package com.Corust1411.batch.service;

import com.Corust1411.batch.entity.Device;
import com.Corust1411.batch.model.CreateDeviceRequest;
import com.Corust1411.batch.model.DeleteDeviceRequest;
import com.Corust1411.batch.model.DeviceResponse;
import com.Corust1411.batch.model.UpdateDeviceRequest;
import com.Corust1411.batch.repository.DeviceListRepository;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DeviceService {
    @Autowired
    DeviceListRepository deviceListRepository;
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
}
