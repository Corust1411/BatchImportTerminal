package com.Corust1411.batch.repository;

import com.Corust1411.batch.entity.GateTransaction;
import com.sun.org.apache.xpath.internal.operations.Gte;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class GateTransactionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public boolean Insert(GateTransaction gateTransaction){
        boolean result = false;
        try{
            entityManager.persist(gateTransaction);
            result = true;
        }catch(Exception e){
            System.out.println("GateTransactionRepository_Insert > error > "+e.getMessage());
        }
        return result;
    }
    @Transactional
    public void Create(GateTransaction gateTransaction){
        try{
            String sql = "insert into Gate_Transaction (inboundID,outboundID,isOutBound,inboundDate,outboundDate,transUpdateDate,cardID \n)" +
                    "values (:inboundID,:outboundID,:isOutBound,:inboundDate,:outboundDate,:transUpdateDate,:cardID)";
            entityManager.createNativeQuery(sql)
                    .setParameter("inboundID",gateTransaction.getInboundID())
                    .setParameter("outboundID",gateTransaction.getOutboundID())
                    .setParameter("isOutBound",gateTransaction.isIsOutbound())
                    .setParameter("inboundDate",gateTransaction.getInboundDate())
                    .setParameter("outboundDate",gateTransaction.getOutboundID())
                    .setParameter("transUpdateDate",gateTransaction.getTranUpdateDate())
                    .setParameter("cardID",gateTransaction.getCardID()).executeUpdate();
        }catch(Exception e){
            System.out.println("GateTransactionRepository_Create > error > "+e.getMessage());
        }
    }
    @Transactional
    public void Update(GateTransaction gateTransaction){
        try{
            String sql = "update Gate_Transaction \n" +
                    "set outboundID = :outboundID , \n" +
                    "outboundDate = :outboundDate , \n" +
                    "isOutbound = 'true' \n" +
                    "where cardID = :cardID and isOutbound = 0";
            entityManager.createNativeQuery(sql)
                    .setParameter("cardID",gateTransaction.getCardID())
                    .setParameter("outboundDate",gateTransaction.getOutboundDate())
                    .setParameter("outboundID",gateTransaction.getOutboundID()).executeUpdate();
        }catch(Exception e){
            System.out.println("GateTransactionRepository_Update > error > "+e.getMessage());
        }
    }
    public void Delete(GateTransaction gateTransaction){
        try{
            String sql = "delete Gate_Transaction \n" +
                    "where ";
        }catch(Exception e){
            System.out.println("GateTransactionRepository_Delete > error > "+e.getMessage());
        }
    }
    public GateTransaction GetItem(String CardID){
        try{
            String sql = "select * from Gate_Transaction \n" +
                    "where cardID = :cardID and isOutbound = 0 order by transUpdatedate DESC";
            return (GateTransaction)entityManager.createNativeQuery(sql,GateTransaction.class)
                    .setParameter("cardID",CardID).setMaxResults(1).getSingleResult();
        }catch(NoResultException e){
            return null;
        }catch(Exception e){
            System.out.println("GateTransactionRepository_GetItem > error > " + e.getMessage());
            return null;
        }
    }
    public List<GateTransaction> GetfromcardID(String CardID){
        try{
            String sql = "select * from Gate_Transaction \n" +
                    "where cardID = :cardID";
            return entityManager.createNativeQuery(sql,GateTransaction.class)
                    .setParameter("cardID",CardID).getResultList();
        }catch(Exception e){
            System.out.println("GateTransactionRepository_GetList > error > "+e.getMessage());
            return null;
        }
    }
    public List<GateTransaction> Getfromdate(String date){
        try{
            String sql = "select * from Gate_Transaction\n" +
                    "where convert(varchar,transUpdatedate,23)=':transUpdateDate'";
            return entityManager.createNativeQuery(sql,GateTransaction.class)
                    .setParameter("transUpdateDate",date).getResultList();
        }catch(Exception e){
            System.out.println("GateTransactionRepository_GetList > error > "+e.getMessage());
            return null;
        }
    }


}
