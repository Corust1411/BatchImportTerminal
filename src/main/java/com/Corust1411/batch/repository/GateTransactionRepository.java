package com.Corust1411.batch.repository;

import com.Corust1411.batch.entity.GateTransaction;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class GateTransactionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public boolean Insert(GateTransaction gateTransaction){
        boolean result = false;
        try{
            entityManager.persist(gateTransaction);
            result = true;
        }catch(Exception e){
            System.out.println("GateTransaction_Insert > error > "+e.getMessage());
        }
        return result;
    }
    public void Create(GateTransaction gateTransaction){
        try{
            String sql = "insert into Gate_Transaction (inboundID,outboundID,isOutBound,inboundDate,outboundDate,transUpdateDate,cardID \n)" +
                    "values (:inboundID,:outboundID,:isOutBound,:inboundDate,:outboundDate,:transUpdateDate,:cardID)";
            entityManager.createNativeQuery(sql)
                    .setParameter("inboundID",gateTransaction.getInboundID())
                    .setParameter("outboundID",gateTransaction.getOutboundID())
                    .setParameter("isOutBound",gateTransaction.getIsOutbound())
                    .setParameter("inboundDate",gateTransaction.getInboundDate())
                    .setParameter("outboundDate",gateTransaction.getOutboundID())
                    .setParameter("transUpdateDate",gateTransaction.getTranUpdateDate())
                    .setParameter("cardID",gateTransaction.getCardID()).executeUpdate();
        }catch(Exception e){
            System.out.println("GateTransaction_Create > error > "+e.getMessage());
        }
    }
    public void Update(GateTransaction gateTransaction){
        try{
            String sql = "update Gate_Transaction \n" +
                    "set ";
            entityManager.createNativeQuery(sql)
                        .executeUpdate();
        }catch(Exception e){
            System.out.println("GateTransaction_Update > error > "+e.getMessage());
        }
    }
    public void Delete(GateTransaction gateTransaction){
        try{
            String sql = "delete Gate_Transaction \n" +
                    "where ";
        }catch(Exception e){
            System.out.println("GateTransaction_Delete > error > "+e.getMessage());
        }
    }

}
