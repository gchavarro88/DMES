/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.dao.bo.IScStoreOrder;
import com.sip.dmes.entitys.ScStoreOrder;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 * @author Gustavo Chavarro Ortiz
 */

@Repository(value = "IScStoreOrder")
public class ScStoreOrderDao implements IScStoreOrder
{
    private final static Logger log = Logger.getLogger(ScStoreOrderDao.class);
    @PersistenceContext()
    EntityManager entityManager;

    @Override
    
    public List<ScStoreOrder> getAllStoreOrders() throws Exception
    {
        List<ScStoreOrder> result = null;
        Query query  = entityManager.createNamedQuery("ScStoreOrder.findAll"); 
        try
        {
            result = (List<ScStoreOrder>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de las ordenes del almacén",e);
        }
        return result;
    }

}
