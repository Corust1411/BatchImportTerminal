package com.Corust1411.batch.model;

import com.Corust1411.batch.entity.Device;
import lombok.Data;

import java.util.List;

@Data
public class ListDeviceResponse {
    private String RespCode;
    private String RespDesc;
    private List<Device> device;
}
