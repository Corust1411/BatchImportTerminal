package com.Corust1411.batch.repository;


import com.Corust1411.batch.entity.Device;
import com.Corust1411.batch.entity.DeviceAccess;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DeviceAccessRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public boolean Insert(DeviceAccess deviceAccess){
        Boolean result=false;
        try{
            entityManager.persist(deviceAccess);
            result = true;
        }catch(Exception e){
            System.out.println("Insert > error > "+e.getMessage());
        }
        return result;
    }

    public void create(DeviceAccess deviceAccess){
        try{
            String sql = "insert into Device_Access (Merchant,Terminal,Access_time,Card_ID,Gate_ID,trans_date) \n" +
                    "values (:Merchant,:Terminal,:Access_time,:Card_ID,:Gate_ID,:trans_date)";
            entityManager.createNativeQuery(sql)
                    .setParameter("Merhchant",deviceAccess.getMerchant())
                    .setParameter("Terminal",deviceAccess.getTerminal())
                    .setParameter("Access_time",deviceAccess.getAccessTime())
                    .setParameter("Card_ID",deviceAccess.getCardID())
                    .setParameter("Gate_ID",deviceAccess.getGateID())
                    .setParameter("Trans_date",deviceAccess.getTransDate()).executeUpdate();
        }catch(Exception e){
            System.out.println("Create > error > "+e.getMessage());
        }
    }
    public void update(DeviceAccess deviceAccess){
        try{
            String sql = "update Device_Access \n" +
                    "set Access_time = :Access_time , \n" +
                    "Card_ID = :Card_ID , \n" +
                    "Gate_ID = :Gate_ID , \n" +
                    "Trans_date = :Trans_date " +
                    "where Merchant = :Merchant and Terminal = :Terminal";
            entityManager.createNativeQuery(sql)
                    .setParameter("Merchant",deviceAccess.getMerchant())
                    .setParameter("Terminal",deviceAccess.getTerminal())
                    .setParameter("Access_time",deviceAccess.getAccessTime())
                    .setParameter("Card_ID",deviceAccess.getCardID())
                    .setParameter("Gate_ID",deviceAccess.getGateID())
                    .setParameter("Trans_date",deviceAccess.getTransDate())
                    .executeUpdate();
        }catch(Exception e){
            System.out.println("Update > error > "+e.getMessage());
        }
    }
    public void delete(DeviceAccess deviceAccess){
        try{
            String sql = "delete Device_Access \n" +
                    "where Merchant = :Merchant and Terminal = :Terminal";
            entityManager.createNativeQuery(sql)
                    .setParameter("Merchant",deviceAccess.getMerchant())
                    .setParameter("Terminal",deviceAccess.getTerminal()).executeUpdate();
        }catch(Exception e){
            System.out.println("Delete > error > "+e.getMessage());
        }
    }
    public DeviceAccess GetItem(String Merchant,String Terminal){
        try{
            String sql = "select * from Device_Access \n" +
                    "where Merchant = :Merchant and Terminal = :Terminal";
            return (DeviceAccess) entityManager.createNativeQuery(sql,DeviceAccess.class)
                    .setParameter("Merchant",Merchant)
                    .setParameter("Terminal",Terminal).getSingleResult();
        }catch(NoResultException e){
            return null;
        }catch(Exception e){
            System.out.println("GetItem > error > "+e.getMessage());
            return null;
        }
    }
    public List<DeviceAccess> GetList(){
        try{
            String sql = "select * from Device_Access";
            return entityManager.createNativeQuery(sql).getResultList();
        }catch(Exception e){
            System.out.println("GetList > error > "+e.getMessage());
            return null;
        }
    }
}
