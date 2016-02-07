/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.dao.bo.IScStoreRequisition;
import com.sip.dmes.entitys.ScStoreRequisition;
import com.sip.dmes.entitys.ScStoreRequisitionState;
import com.sip.dmes.utilities.Utilities;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gustavo Chavarro Ortiz
 */

@Repository(value = "IScStoreRequisition")
public class ScStoreRequisitionDao implements IScStoreRequisition
{
    private final static Logger log = Logger.getLogger(ScStoreRequisitionDao.class);
    @PersistenceContext()
    EntityManager entityManager;

    @Override
    
    public List<ScStoreRequisition> getAllStoreRequisitions() throws Exception
    {
        List<ScStoreRequisition> result = null;
        Query query  = entityManager.createNamedQuery("ScStoreRequisition.findAll"); 
        try
        {
            result = (List<ScStoreRequisition>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de las requisiciones del almacén",e);
        }
        return result;
    }

    @Override
    public List<ScStoreRequisition> getStoreRequisitionsByStatus(List<Long> storeOrdrerStatus) throws Exception
    {
        List<ScStoreRequisition> result = null;
        Query query  = entityManager.createNamedQuery("ScStoreRequisition.findByState"); 
        query.setParameter("storeRequisitionStatus", storeOrdrerStatus);
        try
        {
            result = (List<ScStoreRequisition>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de las requisiciones por estados del almacén",e);
        }
        return result;
    }

    @Override
    public List<ScStoreRequisitionState> getAllStoreRequisitionState() throws Exception
    {
        List<ScStoreRequisitionState> result = null;
        Query query  = entityManager.createNamedQuery("ScStoreRequisitionState.findAll"); 
        try
        {
            result = (List<ScStoreRequisitionState>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de los estados de orden del almacén",e);
        }
        return result;
    }

    @Override
    public List<ScStoreRequisition> getStoreRequisitionsByParameters(Date initDate, Date endDate, String filterRequisitionType, 
            String filterRequisitionClass, String filterRequisitionState, String filterRequisitionRequired) throws Exception
    {
        String select = "SELECT s FROM ScStoreRequisition s WHERE 1=1 ";
        List<ScStoreRequisition> result = null;
        if(initDate != null)
        {
            select += " AND s.creationDate >= :initDate ";
        }
        if(endDate != null)
        {
            select += " AND s.creationDate <= :endDate ";
        }
        if(!Utilities.isEmpty(filterRequisitionType))
        {
            select += " AND s.orderType = :filterRequisitionType";
        }
        if(!Utilities.isEmpty(filterRequisitionRequired))
        {
            select += " AND s.requiredBy = :filterRequisitionRequired ";
        }
        if(!Utilities.isEmpty(filterRequisitionClass))
        {
            select += " AND s.orderClass = :filterRequisitionClass ";
        }
        if(!Utilities.isEmpty(filterRequisitionState))
        {
            select += " AND s.idState.description = :filterRequisitionState ";
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
            if(!Utilities.isEmpty(filterRequisitionType))
            {
                query.setParameter("filterRequisitionType", filterRequisitionType);
            }
            if(!Utilities.isEmpty(filterRequisitionRequired))
            {
                query.setParameter("filterRequisitionRequired", filterRequisitionRequired);
            }
            if(!Utilities.isEmpty(filterRequisitionClass))
            {
                query.setParameter("filterRequisitionClass", filterRequisitionClass);
            }
            if(!Utilities.isEmpty(filterRequisitionState))
            {
                query.setParameter("filterRequisitionState", filterRequisitionState);
            }
            result = (List<ScStoreRequisition>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de los estados de orden del almacén",e);
        }
        return result;
    }

    @Override
    @Transactional
    public void setStoreRequisition(ScStoreRequisition storeRequisition) throws Exception
    {
        try
        {
            if(storeRequisition != null)
            {
                entityManager.merge(storeRequisition);
            }
        }
        catch (Exception e)
        {
            log.error("Error al intentar actualizar la order del almacén",e);
        }
    }

    @Override
    public List<Object[]> getItemsByStoreRequisition(String namedQuery) throws Exception
    {
        List<Object[]> result = null;
        Query query  = entityManager.createNativeQuery(namedQuery); 
        try
        {
            result = (List<Object[]>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de los items de orden del almacén",e);
        }
        return result;
    }

    @Override
    @Transactional
    public void executeUpdate(String update) throws Exception
    {
        try
        {
            Query query = entityManager.createNativeQuery(update);
            int rows = query.executeUpdate();
        }
        catch (Exception e)
        {
            log.error("Error intentando actualizar el stock del almacén",e);
        }
    }
    
    
}
