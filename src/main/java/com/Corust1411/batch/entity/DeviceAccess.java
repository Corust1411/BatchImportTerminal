package com.Corust1411.batch.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "Device_Access")
public class DeviceAccess implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Merchant")
    private String Merchant;
    @Column(name = "Terminal")
    private String Terminal;
    @Column(name = "Access_time")
    private String AccessTime;
    @Column(name = "Card_ID")
    private String CardID;
    @Column(name = "Gate_ID")
    private String GateID;
    @CreationTimestamp
    @Column(name = "Trans_date")
    private Date TransDate;
}
