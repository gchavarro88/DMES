/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.beans.template.TemplateBean;
import com.sip.dmes.dao.bo.IScTools;
import com.sip.dmes.entitys.ScClassType;
import com.sip.dmes.entitys.ScTools;
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
@Repository(value = "IScTools")
public class ScToolsDao implements IScTools
{
    private final static Logger log = Logger.getLogger(ScToolsDao.class);
    @PersistenceContext()
    EntityManager entityManager;
    
    @Override
    @Transactional
    public List<ScTools> getAllTool() throws Exception
    {
        List<ScTools> result = null;
        try
        {
            Query query = entityManager.createNamedQuery("ScTools.findAll");
            result = query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar todos las herramientas", e);
        }
        return result;
    }

    @Override
    @Transactional
    public ScTools findByIdTool(ScTools scTools) throws Exception
    {
        ScTools result = null;
        try
        {
            Query query  = entityManager.createNamedQuery("ScTools.findByIdTool");
            query.setParameter("idTool", scTools);
            result = (ScTools) query.getSingleResult();
        }
        catch(Exception e)
        {
            log.error("Error intentando consultar la herramienta", e.getCause());
        }
        return result;
    }
    
    @Override
    @Transactional
    public ScTools findByTool(ScTools scTools) throws Exception
    {
        ScTools result = null;
        try
        {
            Query query  = entityManager.createNamedQuery("ScTools.findByTool");
            query.setParameter("tool", scTools.getTool());
            result = (ScTools) query.getSingleResult();
        }
        catch(Exception e)
        {
            log.error("Error intentando consultar la herramienta", e.getCause());
        }
        return result;
    }
    
    @Override
    @Transactional
    public List<ScTools> findByIdType(ScType scType) throws Exception
    {
        List<ScTools> result = null;
        try
        {
            Query query  = entityManager.createNamedQuery("ScTools.findByIdType");
            query.setParameter("idType", scType);
            result = (List<ScTools>) query.getSingleResult();
        }
        catch(Exception e)
        {
            log.error("Error intentando consultar las herramientas por tipo", e.getCause());
        }
        return result;
    }

     @Override
    @Transactional
    public List<ScTools> findByIdClassType(ScClassType scClassType) throws Exception
    {
        List<ScTools> result = null;
        try
        {
            Query query  = entityManager.createNamedQuery("ScTools.findByIdClassType");
            query.setParameter("idClassType", scClassType);
            result = (List<ScTools>) query.getSingleResult();
        }
        catch(Exception e)
        {
            log.error("Error intentando consultar las herramientas por clae tipo", e.getCause());
        }
        return result;
    }

    
    @Override
    @Transactional
    public void createTool(ScTools scTools) throws Exception
    {
        try
        {
            entityManager.persist(scTools);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar crear una herramienta ",e );
        }
    }

    @Override
    @Transactional
    public void deleteToolById(ScTools scTools) throws Exception
    {
      int rows = -1;
        try
        {
            Query query = entityManager.createNamedQuery("ScTools.deleteByIdTool");
            query.setParameter("idTool", scTools);
            rows = query.executeUpdate();
         
        }
        catch (Exception e)
        {
            log.error("Error al intentar eliminar una herramienta", e);
        }
    }

    @Override
    @Transactional
    public void updateTool(ScTools scTools) throws Exception
    {
        try
        {
            entityManager.merge(scTools);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar actualizar una herramienta ",e );
        }
    }
   
}
