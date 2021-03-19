package com.Corust1411.batch.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RabbitRequest {
    @JsonProperty("merchantID")
    private String MerchantID;
    @JsonProperty("terminalID")
    private String TerminalID;
    @JsonProperty("location")
    private String Location;
    @JsonProperty("effective")
    private String Effective;
    @JsonProperty("status")
    private String Status;
    @JsonProperty("flag")
    private String Flag;

}
