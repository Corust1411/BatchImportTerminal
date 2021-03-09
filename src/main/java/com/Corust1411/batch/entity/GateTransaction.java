package com.Corust1411.batch.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "Gate_Transaction")
public class GateTransaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transID")
    private String TransID;
    @Column(name = "inboundID")
    private String InboundID;
    @Column(name = "outBoundID")
    private String OutboundID;
    @Column(name = "isOutbound")
    private byte IsOutbound;
    @Column(name = "inboundDate")
    private String InboundDate;
    @Column(name = "outboundDate")
    private String OutboundDate;
    @Column(name = "transUpdateDate")
    private String TranUpdateDate;
    @Column(name = "cardID")
    private String CardID;
}
