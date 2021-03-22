package com.Corust1411.batch.Model;

import com.Corust1411.batch.Entity.GateTransaction;
import lombok.Data;

import java.util.List;

@Data
public class GateTransactionByDateResponse {
    private String RespCode;
    private String RespDesc;
    private List<GateTransaction> gateTransactions;
}
