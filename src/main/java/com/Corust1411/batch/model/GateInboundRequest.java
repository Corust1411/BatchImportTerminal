package com.Corust1411.batch.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GateInboundRequest {
    @JsonProperty("MerchantID")
    private String MerchantID;
    @JsonProperty("TerminalID")
    private String TerminalID;


}
