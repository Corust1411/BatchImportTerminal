package com.Corust1411.batch.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class GateInboundRequest {
    @JsonProperty("inboundID")
    private String InboundID;
    @JsonProperty("merchantID")
    private String MerchantID;
    @JsonProperty("terminalID")
    private String TerminalID;
    @JsonProperty("gateID")
    private String GateID;
    @JsonProperty("cardID")
    private String CardID;
    @JsonProperty("accessDate")
    private String AccessDate;
    @JsonProperty("transDate")
    private String TransDate;
}
