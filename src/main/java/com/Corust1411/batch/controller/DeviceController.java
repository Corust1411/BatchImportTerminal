package com.Corust1411.batch.controller;

import com.Corust1411.batch.entity.Device;
import com.Corust1411.batch.model.*;
import com.Corust1411.batch.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    DeviceService deviceService;

    @PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeviceResponse> PostCreateDevice(@RequestBody CreateDeviceRequest createDeviceRequest){
        try{
            DeviceResponse deviceResponse = new DeviceResponse();
            deviceResponse.setRespDesc(null);
            deviceResponse.setRespCode(null);

            Boolean gate = deviceService.CreateDevice(createDeviceRequest);
            if(gate == true){
                deviceResponse.setRespDesc("success");
                deviceResponse.setRespCode("1000");
                return ResponseEntity.ok(deviceResponse);
            }else{
                deviceResponse.setRespDesc("fail");
                deviceResponse.setRespCode("0001");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(deviceResponse);
            }
        }catch(Exception e){
            System.out.println("DeviceController_PostCreateDevice > error > " + e.getMessage());
            return null;
        }
    }
    @PutMapping(value = "/update",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeviceResponse> PostUpdateDevice(@RequestBody UpdateDeviceRequest updateDeviceRequest){
        try{
            DeviceResponse deviceResponse = new DeviceResponse();
            deviceResponse.setRespDesc(null);
            deviceResponse.setRespCode(null);

            Integer gate = deviceService.UpdateDevice(updateDeviceRequest);
            if (gate > 0){
                deviceResponse.setRespDesc("success");
                deviceResponse.setRespCode("1000");
                return ResponseEntity.ok(deviceResponse);
            }else{
                deviceResponse.setRespDesc("fail");
                deviceResponse.setRespCode("0001");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(deviceResponse);
            }
        }catch(Exception e){
            System.out.println("DeviceController_PostCreateDevice > error > " + e.getMessage());
            return null;
        }
    }
    @DeleteMapping(value = "/delete",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeviceResponse> PostDeleteDevice(@RequestBody DeleteDeviceRequest deleteDeviceRequest){
        try{
            DeviceResponse deviceResponse = new DeviceResponse();
            deviceResponse.setRespDesc(null);
            deviceResponse.setRespCode(null);

            Integer gate = deviceService.DeleteDevice(deleteDeviceRequest);
            if (gate > 0){
                deviceResponse.setRespDesc("success");
                deviceResponse.setRespCode("1000");
                return ResponseEntity.ok(deviceResponse);
            }else{
                deviceResponse.setRespDesc("fail");
                deviceResponse.setRespCode("0001");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(deviceResponse);
            }
        }catch(Exception e){
            System.out.println("DeviceController_PostCreateDevice > error > " + e.getMessage());
            return null;
        }
    }
    @GetMapping(value = "/list",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ListDeviceResponse> GetListDevice(){
        try{
            ListDeviceResponse list = new ListDeviceResponse();
            list.setRespDesc(null);
            list.setRespCode(null);
            List<Device> device = deviceService.GetListDevice();
            System.out.println(device);
            if(device.size() == 0){
                list.setRespDesc("fail");
                list.setRespCode("0001");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(list);
            }else {
                list.setRespDesc("success");
                list.setRespCode("1000");
                list.setDevice(device);
                return ResponseEntity.ok(list);
            }
        }catch(Exception e){
            System.out.println("DeviceController_GetListDevice > error > " + e.getMessage());
            return null;
        }
    }
}
