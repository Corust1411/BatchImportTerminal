package com.Corust1411.batch.Model;

import com.Corust1411.batch.Entity.Device;
import lombok.Data;

import java.util.List;

@Data
public class ListDeviceResponse {
    private String RespCode;
    private String RespDesc;
    private List<Device> device;
}
