package com.Corust1411.batch.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "Gate_Outbound")
public class GateOutbound implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "outboundID")
    private String OutboundID;
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
