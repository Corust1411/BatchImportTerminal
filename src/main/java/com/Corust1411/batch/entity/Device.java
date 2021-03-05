package com.Corust1411.batch.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "Device_List")
public class Device implements Serializable {
    @Id
    @Column(name = "deviceID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer deviceID;
    @Column(name = "merchantid")
    private String merchantID;
    @Column(name = "terminalid")
    private String terminalID;
    @Column(name = "location")
    private String location;
    @Column(name = "effective")
    private String effective;
    @Column(name = "status")
    private String status;
    @Column(name = "flag")
    private String flag;
    @Column(name = "result")
    private String result;
    @CreationTimestamp
    @Column(name="time_stamp")
    private Date timestamp;


}
