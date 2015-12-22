/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.dao.bo.IOtProduction;
import com.sip.dmes.entitys.OtProductionOrder;
import com.sip.dmes.entitys.ScProductFormulation;
import com.sip.dmes.entitys.ScProductionState;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(value = "IOtProduction")
public class OtProductionDao implements IOtProduction
{
    private final static Logger log = Logger.getLogger(OtProductionDao.class);
    @PersistenceContext()
    EntityManager entityManager;

    @Override
    public List<ScProductionState> getListStates() throws Exception
    {
        List<ScProductionState> result = null;
        try
        {
            Query query = entityManager.createNamedQuery("ScProductionState.findAll");
            result = (List<ScProductionState>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error intentando consultar los estados del producto",e);
        }
        return result;
    }

    @Override
    public List<OtProductionOrder> getListProductionOrders() throws Exception
    {
        List<OtProductionOrder> result = null;
        try
        {
            Query query = entityManager.createNamedQuery("OtProductionOrder.findAll");
            result = (List<OtProductionOrder>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error intentando consultar la lista de ordenes del producto",e);
        }
        return result;
    }

    @Override
    public List<ScProductFormulation> getListProductFormulations() throws Exception
    {
        List<ScProductFormulation> result = null;
        try
        {
            Query query = entityManager.createNamedQuery("ScProductFormulation.findAll");
            result = (List<ScProductFormulation>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error intentando consultar los productos para la orden de producción",e);
        }
        return result;
    }

    @Override
    public List<OtProductionOrder> getProductionByParameters(Date initDate, Date endDate, String orderNumber, ScProductionState state) throws Exception
    {
        String nameQuery = "SELECT o FROM OtProductionOrder o WHERE 1=1 ";
        List<OtProductionOrder> result = null;
        
        if(initDate != null)
        {
            nameQuery += " AND o.creationDate >= :initDate ";
        }
        if(endDate != null)
        {
            nameQuery += " AND o.creationDate <= :endDate  ";
        }
        if(orderNumber != null)
        {
            nameQuery += " AND o.name LIKE :description ";
        }
        if(state != null)
        {
            nameQuery += " AND o.idProductionState.idProductionState = :idProductionState";
        }
        try
        {
            Query query = entityManager.createQuery(nameQuery);
            if(initDate != null)
            {
                query.setParameter("initDate", initDate);
            }
            if(endDate != null)
            {
                query.setParameter("endDate", endDate); 
            }
            if(orderNumber != null)
            {
                query.setParameter("description", "%"+orderNumber+"%");
            }
            if(state != null)
            {
                query.setParameter("idProductionState", state.getIdProductionState());
            }
            result = (List<OtProductionOrder>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error intentando consultar las ordenes de producción por parámetros",e);
        }
        return result;
    }

    @Override
    @Transactional
    public void saveOrderProduction(OtProductionOrder productionOrder) throws Exception
    {
        try
        {
            if(productionOrder != null)
            {
                entityManager.persist(productionOrder);
                
            }
        }
        catch (Exception e)
        {
            log.error("Error intentando guardar la orden producción",e);
            throw e;
        }
    }

    @Override
    @Transactional
    public void updateOrderProduction(OtProductionOrder productionOrder) throws Exception
    {
        try
        {
            if(productionOrder != null)
            {
                entityManager.merge(productionOrder);
            }
        }
        catch (Exception e)
        {
            log.error("Error intentando actualizar la orden producción",e);
            throw e;
        }
    }

    @Override
    @Transactional
    public void deleteOrderProduction(OtProductionOrder productionOrder) throws Exception
    {
        try
        {
            if(productionOrder != null)
            {
                entityManager.remove(entityManager.contains(productionOrder)?productionOrder:entityManager.merge(productionOrder));
            }
        }
        catch (Exception e)
        {
            log.error("Error intentando eliminar la orden producción",e);
            throw e;
        }
    }

    @Override
    public OtProductionOrder getProductionOrderById(Long idProductionOrder) throws Exception
    {
        OtProductionOrder result = null;
        try
        {
            Query query =  entityManager.createNamedQuery("OtProductionOrder.findByIdProductionOrder");
            query.setParameter("idProductionOrder", idProductionOrder);
            result = (OtProductionOrder) query.getSingleResult();
        }
        catch (Exception e)
        {
            log.error("Error intenando consultar la orden de producción",e);
            throw e;
        }
        return result;
    }

    

}
