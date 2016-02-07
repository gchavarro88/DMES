/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.beans.template.TemplateBean;
import com.sip.dmes.dao.bo.IScPartsAndConsumables;
import com.sip.dmes.entitys.ScClassType;
import com.sip.dmes.entitys.ScPartsAndConsumables;
import com.sip.dmes.entitys.ScType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author carlos guzman
 */
@Repository(value = "IScPartsAndConsumables")
public class ScPartsAndConsumablesDao implements IScPartsAndConsumables
{
    private final static Logger log = Logger.getLogger(ScPartsAndConsumablesDao.class);
    @PersistenceContext()
    EntityManager entityManager;
    
    @Override
    @Transactional
    public List<ScPartsAndConsumables> getAllPartsAndConsumables() throws Exception
    {
        List<ScPartsAndConsumables> result = null;
        try
        {
            Query query = entityManager.createNamedQuery("ScPartsAndConsumables.findAll");
            result = query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar todos las Partes y consumibles", e);
        }
        return result;
    }

    @Override
    @Transactional
    public ScPartsAndConsumables findByIdPartsAndConsumables(ScPartsAndConsumables scPartsAndConsumables) throws Exception
    {
        ScPartsAndConsumables result = null;
        try
        {
            Query query  = entityManager.createNamedQuery("ScPartsAndConsumables.findByIdPartsAndConsumables");
            query.setParameter("idPartsAndConsumables", scPartsAndConsumables);
            result = (ScPartsAndConsumables) query.getSingleResult();
        }
        catch(Exception e)
        {
            log.error("Error intentando consultar las Partes y consumibles", e.getCause());
        }
        return result;
    }
    
    @Override
    @Transactional
    public ScPartsAndConsumables findByPartsAndConsumables(ScPartsAndConsumables scPartsAndConsumables) throws Exception
    {
        ScPartsAndConsumables result = null;
        try
        {
            Query query  = entityManager.createNamedQuery("ScPartsAndConsumables.findByPartsAndConsumables");
            query.setParameter("partsAndConsumables", scPartsAndConsumables.getPartsAndConsumables());
            result = (ScPartsAndConsumables) query.getSingleResult();
        }
        catch(Exception e)
        {
            log.error("Error intentando consultar las Partes y consumibles", e.getCause());
        }
        return result;
    }
    
    @Override
    @Transactional
    public List<ScPartsAndConsumables> findByIdType(ScType scType) throws Exception
    {
        List<ScPartsAndConsumables> result = null;
        try
        {
            Query query  = entityManager.createNamedQuery("ScPartsAndConsumables.findByIdType");
            query.setParameter("idType", scType);
            result = (List<ScPartsAndConsumables>) query.getSingleResult();
        }
        catch(Exception e)
        {
            log.error("Error intentando consultar las Partes y consumibles por tipo", e.getCause());
        }
        return result;
    }

     @Override
    @Transactional
    public List<ScPartsAndConsumables> findByIdClassType(ScClassType scClassType) throws Exception
    {
        List<ScPartsAndConsumables> result = null;
        try
        {
            Query query  = entityManager.createNamedQuery("ScPartsAndConsumables.findByIdClassType");
            query.setParameter("idClassType", scClassType);
            result = (List<ScPartsAndConsumables>) query.getSingleResult();
        }
        catch(Exception e)
        {
            log.error("Error intentando consultar las Partes y consumibles por clae tipo", e.getCause());
        }
        return result;
    }

    
    @Override
    @Transactional
    public void createPartsAndConsumables(ScPartsAndConsumables scPartsAndConsumables) throws Exception
    {
        try
        {
            entityManager.persist(scPartsAndConsumables);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar crear las Partes y consumibles ",e );
        }
    }

    @Override
    @Transactional
    public void deletePartsAndConsumableslById(ScPartsAndConsumables scPartsAndConsumables) throws Exception
    {
      int rows = -1;
        try
        {
            Query query = entityManager.createNamedQuery("ScPartsAndConsumables.deleteByIdPartsAndConsumables");
            query.setParameter("idPartsAndConsumables", scPartsAndConsumables);
            rows = query.executeUpdate();
         
        }
        catch (Exception e)
        {
            log.error("Error al intentar eliminar las Partes y consumibles", e);
        }
    }

    @Override
    @Transactional
    public void updatePartsAndConsumables(ScPartsAndConsumables scPartsAndConsumables) throws Exception
    {
        try
        {
            entityManager.merge(scPartsAndConsumables);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar actualizar las Partes y consumibles ",e );
        }
    }
   
}
