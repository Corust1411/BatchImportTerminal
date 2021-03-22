package com.Corust1411.batch.Entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "Gate_Inbound")
public class GateInbound implements Serializable {
    @Id
    @Column(name = "inboundIndex")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer InboundIndex;
    @Column(name = "inboundID")
    private String InboundID;
    @Column(name = "merchantID")
    private String MerchantID;
    @Column(name = "terminalID")
    private String TerminalID;
    @Column(name = "gateID")
    private String GateID;
    @Column(name = "cardID")
    private String CardID;
    @Column(name = "accessDate")
    private String AccessDate;
    @CreationTimestamp
    @Column(name = "transDate")
    private Date TransDate;
}
