package com.Corust1411.batch.repository;

import com.Corust1411.batch.entity.Device;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DeviceListRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public boolean Insert(Device device){
        Boolean result=false;
        try{
            entityManager.persist(device);
            result = true;
        }catch(Exception e){
            System.out.println("DeviceListRepository_Insert > error > "+e.getMessage());
        }
        return result;
    }
    @Transactional
    public Integer UpdateDeviceByRequest(Device device){
        try{
            String sql = "update Device_List \n" +
                    "set location = :location ,\n" +
                    "effective = :effective ,\n" +
                    "status = :status ,\n" +
                    "flag = :flag ,\n" +
                    "time_stamp = :time_stamp \n" +
                    "where merchantid=:merchantid and " +
                    "terminalid=:terminalid";
            return entityManager.createNativeQuery(sql)
                    .setParameter("merchantid",device.getMerchantID())
                    .setParameter("terminalid",device.getTerminalID())
                    .setParameter("location",device.getLocation())
                    .setParameter("effective",device.getEffective())
                    .setParameter("status",device.getStatus())
                    .setParameter("flag",device.getFlag())
                    .setParameter("time_stamp",device.getTimestamp()).executeUpdate();
        }catch(Exception e){
            System.out.println("DeviceListRepository_UpdateListRepository > error > " + e.getMessage());
            return null;
        }
    }
    @Transactional
    public Integer DeleteDeviceByRequest(Device device){
        try{
            String sql = "delete Device_List \n" +
                    "where merchantid = :merchantid and terminalid = :terminalid" ;
            return entityManager.createNativeQuery(sql)
                    .setParameter("merchantid",device.getMerchantID())
                    .setParameter("terminalid",device.getTerminalID()).executeUpdate();
        }catch(Exception e){
            System.out.println("DeviceListRepository_DeleteDeviceByRequest > error > " + e.getMessage());
            return null;
        }
    }
    public List<Device> GetListByRequest(){
        try{
            String sql = "select * from Device_List";
            return entityManager.createNativeQuery(sql,Device.class).getResultList();
        }catch(Exception e){
            System.out.println("DeviceListRepository_GetListByRequest > error > " + e.getMessage());
            return null;
        }
    }
    public List<Device> ExportListByRequest(){
        try{
            String sql = "select * from Device_List";
            return entityManager.createNativeQuery(sql,Device.class).getResultList();
        }catch(Exception e){
            System.out.println("DeviceListRepository_GetListByRequest > error > " + e.getMessage());
            return null;
        }
    }





    ///////
    public void Create(Device device){
        try{
            String sql="insert into Device_List (merchantid,terminalid,location,effective,status,flag,result,time_stamp)\n" +
                    "VALUES (:merchantid,:terminalid,:location,:effective,:status,:flag,:result,:time_stamp)";
            entityManager.createNativeQuery(sql)
                    .setParameter("merchantid",device.getMerchantID())
                    .setParameter("terminalid",device.getTerminalID())
                    .setParameter("location",device.getLocation())
                    .setParameter("effective",device.getEffective())
                    .setParameter("status",device.getStatus())
                    .setParameter("flag",device.getFlag())
                    .setParameter("result",device.getResult())
                    .setParameter("time_stamp",device.getTimestamp()).executeUpdate();

        }catch(Exception e){
            System.out.println("DeviceListRepository_Create > error > "+e.getMessage());
        }
    }
    public void Update(Device device){
        try{
            String sql="update Device_List \n" +
                    "set location = :location ,\n" +
                    "effective = :effective ,\n" +
                    "status = :status ,\n" +
                    "flag = :flag ,\n" +
                    "result = :result, \n" +
                    "time_stamp = :time_stamp \n" +
                    "where merchantid=:merchantid and " +
                    "terminalid=:terminalid";
            entityManager.createNativeQuery(sql)
                    .setParameter("merchantid",device.getMerchantID())
                    .setParameter("terminalid",device.getTerminalID())
                    .setParameter("location",device.getLocation())
                    .setParameter("effective",device.getEffective())
                    .setParameter("status",device.getStatus())
                    .setParameter("flag",device.getFlag())
                    .setParameter("result",device.getResult())
                    .setParameter("time_stamp",device.getTimestamp()).executeUpdate();

        }catch(Exception e){
            System.out.println("DeviceListRepository_Update > error > "+e.getMessage());
        }
    }
    public void Delete(Device device){
        try{
            String sql="delete Device_List \n" +
                    "where merchantid = :merchantid and terminalid = :terminalid";
            entityManager.createNativeQuery(sql)
                    .setParameter("merchantid",device.getMerchantID())
                    .setParameter("terminalid",device.getTerminalID()).executeUpdate();
        }catch(Exception e){
            System.out.println("DeviceListRepository_Delete > error > "+e.getMessage());
        }
    }
    public Device GetItem(String merchantID,String terminalID){
        try{
            String sql="select * from Device_List \n" +
                    "where merchantid = :merchantid and terminalid = :terminalid";
            return (Device)entityManager.createNativeQuery(sql,Device.class)
                    .setParameter("merchantid",merchantID)
                    .setParameter("terminalid",terminalID).getSingleResult();
        }catch(NoResultException e) {
            return null;
        }catch(Exception e){
            System.out.println("DeviceListRepository_GetItem > error > "+e.getMessage());
        }
        return null;
    }
    public List<Device> GetList(){
        try{
            String sql ="select * from Device_List";
            return entityManager.createNativeQuery(sql,Device.class).getResultList();

        }catch(Exception e){
            System.out.println("DeviceListRepository_GetList > error > "+e.getMessage());
            return null;
        }
    }
    public Device GetItem2(String merchantID,String terminalID) {
        try {
            String sql = "select merchantid, terminalid from Device_List \n" +
                    "where merchantid = :merchantid and terminalid = :terminalid";
            return (Device) entityManager.createNativeQuery(sql, Device.class)
                    .setParameter("merchantid", merchantID)
                    .setParameter("terminalid", terminalID).getSingleResult();

        } catch (Exception e) {
            System.out.println("DeviceListRepository_GetItem > error > " + e.getMessage());
        }
        return null;
    }
}