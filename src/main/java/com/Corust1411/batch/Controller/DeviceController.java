package com.Corust1411.batch.Controller;

import com.Corust1411.batch.Entity.Device;
import com.Corust1411.batch.Model.*;
import com.Corust1411.batch.Service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

            Boolean cre = deviceService.CreateDevice(createDeviceRequest);
            if(cre == true){
                deviceResponse.setRespDesc("success");
                deviceResponse.setRespCode("1000");
                return ResponseEntity.status(HttpStatus.CREATED).body(deviceResponse);
            }else{
                deviceResponse.setRespDesc("fail");
                deviceResponse.setRespCode("0001");
                return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(deviceResponse);
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

            Integer update = deviceService.UpdateDevice(updateDeviceRequest);
            if (update > 0){
                deviceResponse.setRespDesc("success");
                deviceResponse.setRespCode("1000");
                return ResponseEntity.status(HttpStatus.OK).body(deviceResponse);
            }else{
                deviceResponse.setRespDesc("fail");
                deviceResponse.setRespCode("0001");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(deviceResponse);
            }
        }catch(Exception e){
            System.out.println("DeviceController_PostUpdateDevice > error > " + e.getMessage());
            return null;
        }
    }
    @DeleteMapping(value = "/delete",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeviceResponse> PostDeleteDevice(@RequestBody DeleteDeviceRequest deleteDeviceRequest){
        try{
            DeviceResponse deviceResponse = new DeviceResponse();
            deviceResponse.setRespDesc(null);
            deviceResponse.setRespCode(null);

            Integer del = deviceService.DeleteDevice(deleteDeviceRequest);
            if (del > 0){
                deviceResponse.setRespDesc("success");
                deviceResponse.setRespCode("1000");
                return ResponseEntity.status(HttpStatus.OK).body(deviceResponse);
            }else{
                deviceResponse.setRespDesc("fail");
                deviceResponse.setRespCode("0001");
                return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(deviceResponse);
            }
        }catch(Exception e){
            System.out.println("DeviceController_PostDeleteDevice > error > " + e.getMessage());
            return null;
        }
    }
    @GetMapping(value = "/list",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ListDeviceResponse> GetListDevice(){
        try{
            ListDeviceResponse list = new ListDeviceResponse();
            list.setRespDesc(null);
            list.setRespCode(null);
            List<Device> getlist = deviceService.GetListDevice();

            if(getlist.size() == 0){
                list.setRespDesc("fail");
                list.setRespCode("0001");
                return ResponseEntity.status(HttpStatus.GONE).body(list);
            }else {
                list.setRespDesc("success");
                list.setRespCode("1000");
                list.setDevice(getlist);
                return ResponseEntity.ok(list);
            }
        }catch(Exception e){
            System.out.println("DeviceController_GetListDevice > error > " + e.getMessage());
            return null;
        }
    }
    @GetMapping(value = "/list/export",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExportDeviceResponse> ExportListDevice(){
        try{
            ExportDeviceResponse export = new ExportDeviceResponse();
            export.setRespDesc(null);
            export.setRespCode(null);
            List<Device> exportlist = deviceService.ExportListDevice();


            deviceService.CovertintoCSV((ArrayList<Device>)exportlist);

            if(exportlist.size() == 0){
                export.setRespDesc("fail");
                export.setRespCode("0001");
                return ResponseEntity.status(HttpStatus.GONE).body(export);
            }else {
                export.setRespDesc("success");
                export.setRespCode("1000");
                return ResponseEntity.ok(export);
            }
        }catch(Exception e){
            System.out.println("DeviceController_GetListDevice > error > " + e.getMessage());
            return null;
        }
    }
}
