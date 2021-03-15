package com.Corust1411.batch.model;

import com.Corust1411.batch.entity.GateTransaction;
import lombok.Data;

import java.util.List;

@Data
public class GateTransactionByDateResponse {
    private String RespCode;
    private String RespDesc;
    private List<GateTransaction> gateTransactions;
}
