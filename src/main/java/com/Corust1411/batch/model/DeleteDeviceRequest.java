package com.Corust1411.batch.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DeleteDeviceRequest {
    @JsonProperty("merchantID")
    private String MerchantID;
    @JsonProperty("terminalID")
    private String TerminalID;
}
