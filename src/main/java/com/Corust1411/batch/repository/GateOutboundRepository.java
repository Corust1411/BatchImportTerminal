package com.Corust1411.batch.repository;

import com.Corust1411.batch.entity.GateOutbound;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class GateOutboundRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public boolean Insert(GateOutbound gateOutbound){
        Boolean result = false;
        try{
           entityManager.persist(gateOutbound);
           result = true;
        }catch(Exception e){
            System.out.println("GateOutboundRepository_Insert > error > "+e.getMessage());
        }
        return result;
    }
    @Transactional
    public void Create(GateOutbound gateOutbound) {
        try {
            String sql = "Insert into Gate_Outbound (outboundID,merchantID,terminalID,gateID,cardID,accessDate,transDate) \n" +
                    "values (:outboundID,:merchantID,:terminalID,:gateID,:cardID,:accessDate,:transDate)";
            entityManager.createNativeQuery(sql)
                    .setParameter("outboundID",gateOutbound.getOutboundID())
                    .setParameter("merchantID", gateOutbound.getMerchantID())
                    .setParameter("terminalID", gateOutbound.getTerminalID())
                    .setParameter("gateID", gateOutbound.getGateID())
                    .setParameter("cardID", gateOutbound.getCardID())
                    .setParameter("accessDate", gateOutbound.getAccessDate())
                    .setParameter("transDate", gateOutbound.getTransDate()).executeUpdate();
        } catch (Exception e) {
            System.out.println("GateOutboundRepository_Create > error > " + e.getMessage());
        }
    }
    public void Update(GateOutbound gateOutbound){
        try{
            String sql = "update Gate_Outbound \n" +
                    "set gateID = : gateID , \n" +
                    "cardID = :cardID , \n" +
                    "accessDate = :accessDate , \n" +
                    "transDate = :transDate \n" +
                    "where merchantID = :merchantID and terminalID = :terminalID";
            entityManager.createNativeQuery(sql)
                    .setParameter("merchantID",gateOutbound.getMerchantID())
                    .setParameter("terminalID",gateOutbound.getTerminalID())
                    .setParameter("gateID",gateOutbound.getGateID())
                    .setParameter("cardID",gateOutbound.getCardID())
                    .setParameter("accessDate",gateOutbound.getAccessDate())
                    .setParameter("transDate",gateOutbound.getTransDate()).executeUpdate();
        }catch(Exception e){
            System.out.println("GateOutboundRepository_Update > error > "+e.getMessage());
        }
    }
    public void Delete(GateOutbound gateOutbound){
        try{
            String sql = "delete Gate_Outbound \n" +
                    "where merchantID = :merchantID and terminalID = :terminalID";
            entityManager.createNativeQuery(sql)
                    .setParameter("merchantID",gateOutbound.getMerchantID())
                    .setParameter("terminalID",gateOutbound.getTerminalID()).executeUpdate();
        }catch(Exception e){
            System.out.println("GateOutboundRepository_Delete > error > "+e.getMessage());
        }
    }
    public GateOutbound GetItem(String Merchant,String Terminal){
        try{
            String sql = "select * from Gate_Outbound \n" +
                    "where merchantID = :merchantID and terminalID = :terminalID";
            return (GateOutbound) entityManager.createNativeQuery(sql,GateOutbound.class)
                    .setParameter("MerchantID",Merchant)
                    .setParameter("TerminalID",Terminal).getSingleResult();
        }catch(NoResultException e){
            return null;
        }catch(Exception e){
            System.out.println("GateOutboundRepository_GetItem > error > "+e.getMessage());
            return null;
        }
    }
    public List<GateOutbound> GetList(){
        try{
            String sql = "select * from Gate_Outbound";
            return entityManager.createNativeQuery(sql).getResultList();
        }catch(Exception e){
            System.out.println("GateOutboundRepository_GetList > error > "+e.getMessage());
            return null;
        }
    }
}
