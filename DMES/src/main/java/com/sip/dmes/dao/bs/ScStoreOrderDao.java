/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.dao.bo.IScStoreOrder;
import com.sip.dmes.entitys.ScStoreOrder;
import com.sip.dmes.entitys.ScStoreOrderState;
import com.sip.dmes.utilities.Utilities;
import java.util.Date;
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

    @Override
    public List<ScStoreOrder> getStoreOrdersByStatus(List<Long> storeOrdrerStatus) throws Exception
    {
        List<ScStoreOrder> result = null;
        Query query  = entityManager.createNamedQuery("ScStoreOrder.findByState"); 
        query.setParameter("storeOrderStatus", storeOrdrerStatus);
        try
        {
            result = (List<ScStoreOrder>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de las ordenes por estados del almacén",e);
        }
        return result;
    }

    @Override
    public List<ScStoreOrderState> getAllStoreOrderState() throws Exception
    {
        List<ScStoreOrderState> result = null;
        Query query  = entityManager.createNamedQuery("ScStoreOrderState.findAll"); 
        try
        {
            result = (List<ScStoreOrderState>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de los estados de orden del almacén",e);
        }
        return result;
    }

    @Override
    public List<ScStoreOrder> getStoreOrdersByParameters(Date initDate, Date endDate, String filterOrderType, 
            String filterOrderClass, String filterOrderState, String filterOrderRequired) throws Exception
    {
        String select = "SELECT s FROM ScStoreOrder s WHERE 1=1 ";
        List<ScStoreOrder> result = null;
        if(initDate != null)
        {
            select += " AND s.creationDate >= :initDate ";
        }
        if(endDate != null)
        {
            select += " AND s.creationDate <= :endDate ";
        }
        if(!Utilities.isEmpty(filterOrderType))
        {
            select += " AND s.orderType = :filterOrderType";
        }
        if(!Utilities.isEmpty(filterOrderRequired))
        {
            select += " AND s.requiredBy = :filterOrderRequired ";
        }
        if(!Utilities.isEmpty(filterOrderClass))
        {
            select += " AND s.orderClass = :filterOrderClass ";
        }
        if(!Utilities.isEmpty(filterOrderState))
        {
            select += " AND s.idState.description = :filterOrderState ";
        }
        try
        {
            Query query = entityManager.createQuery(select);
            if(initDate != null)
            {
                query.setParameter("initDate", initDate);
            }
            if(endDate != null)
            {
                query.setParameter("endDate", endDate);
            }
            if(!Utilities.isEmpty(filterOrderType))
            {
                query.setParameter("filterOrderType", filterOrderType);
            }
            if(!Utilities.isEmpty(filterOrderRequired))
            {
                query.setParameter("filterOrderRequired", filterOrderRequired);
            }
            if(!Utilities.isEmpty(filterOrderClass))
            {
                query.setParameter("filterOrderClass", filterOrderClass);
            }
            if(!Utilities.isEmpty(filterOrderState))
            {
                query.setParameter("filterOrderState", filterOrderState);
            }
            result = (List<ScStoreOrder>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de los estados de orden del almacén",e);
        }
        return result;
    }

    @Override
    public void setStoreOrder(ScStoreOrder storeOrder) throws Exception
    {
        try
        {
            if(storeOrder != null)
            {
                entityManager.merge(storeOrder);
            }
        }
        catch (Exception e)
        {
            log.error("Error al intentar actualizar la order del almacén",e);
        }
    }
}
