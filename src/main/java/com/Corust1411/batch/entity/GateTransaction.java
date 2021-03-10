package com.Corust1411.batch.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
    private boolean IsOutbound;
    @Column(name = "inboundDate")
    private String InboundDate;
    @Column(name = "outboundDate")
    private String OutboundDate;
    @CreationTimestamp
    @Column(name = "transUpdateDate")
    private Date TranUpdateDate;
    @Column(name = "cardID")
    private String CardID;
}
