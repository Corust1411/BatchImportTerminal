package com.Corust1411.batch.repository;


import com.Corust1411.batch.entity.GateInbound;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class GateInboundRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public boolean Insert(GateInbound gateInbound){
        Boolean result=false;
        try{
            entityManager.persist(gateInbound);
            result = true;
        }catch(Exception e){
            System.out.println("GateInboundRepository_Insert > error > "+e.getMessage());
        }
        return result;
    }

    @Transactional
    public void Create(GateInbound gateInbound){
        try{
            String sql = "insert into Gate_Inbound (inboundID,merchantID,terminalID,gateID,cardID,accessDate,transDate) \n" +
                    "values (:inboundID,:merchantID,:terminalID,:gateID,:cardID,:accessDate,:transDate)";
            entityManager.createNativeQuery(sql)
                    .setParameter("inboundID",gateInbound.getInboundID())
                    .setParameter("merchantID",gateInbound.getMerchantID())
                    .setParameter("terminalID",gateInbound.getTerminalID())
                    .setParameter("gateID",gateInbound.getGateID())
                    .setParameter("cardID",gateInbound.getCardID())
                    .setParameter("accessDate",gateInbound.getAccessDate())
                    .setParameter("transDate",gateInbound.getTransDate()).executeUpdate();
        }catch(Exception e){
            System.out.println("GateInboundRepository_Create > error > "+e.getMessage());
        }
    }
    public void Update(GateInbound gateInbound){
        try{
            String sql = "update Gate_Inbound \n" +
                    "set accessDate = :accessDate    , \n" +
                    "cardID = :cardID , \n" +
                    "gateID = :gateID , \n" +
                    "transDate = :transDate " +
                    "where merchantID = :merchantID and terminalID = :terminalID";
            entityManager.createNativeQuery(sql)
                    .setParameter("merchantID",gateInbound.getMerchantID())
                    .setParameter("terminalID",gateInbound.getTerminalID())
                    .setParameter("gateID",gateInbound.getGateID())
                    .setParameter("cardID",gateInbound.getCardID())
                    .setParameter("accessDate",gateInbound.getAccessDate())
                    .setParameter("transDate",gateInbound.getTransDate())
                    .executeUpdate();
        }catch(Exception e){
            System.out.println("GateInboundRepository_Update > error > "+e.getMessage());
        }
    }
    public void Delete(GateInbound gateInbound){
        try{
            String sql = "delete Gate_Inbound \n" +
                    "where merchantID = :merchantID and terminalID = :terminalID";
            entityManager.createNativeQuery(sql)
                    .setParameter("merchantID",gateInbound.getMerchantID())
                    .setParameter("terminalID",gateInbound.getTerminalID()).executeUpdate();
        }catch(Exception e){
            System.out.println("GateInboundRepository_Delete > error > "+e.getMessage());
        }
    }
    public GateInbound GetItem(String Merchant, String Terminal){
        try{
            String sql = "select * from Gate_Inbound \n" +
                    "where merchantID = :merchantID and terminalID = :terminalID";
            return (GateInbound) entityManager.createNativeQuery(sql, GateInbound.class)
                    .setParameter("merchantID",Merchant)
                    .setParameter("terminalID",Terminal).getSingleResult();
        }catch(NoResultException e){
            return null;
        }catch(Exception e){
            System.out.println("GateInboundRepository_GetItem > error > "+e.getMessage());
            return null;
        }
    }
    public List<GateInbound> GetList(){
        try{
            String sql = "select * from Gate_Inbound";
            return entityManager.createNativeQuery(sql).getResultList();
        }catch(Exception e){
            System.out.println("GateInboundRepository_GetList > error > "+e.getMessage());
            return null;
        }
    }
}
