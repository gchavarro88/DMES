/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.beans.template.TemplateBean;
import com.sip.dmes.dao.bo.IScType;
import com.sip.dmes.entitys.ScClassType;
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
@Repository(value = "IScType")
public class ScTypeDao implements IScType
{
    private final static Logger log = Logger.getLogger(ScTypeDao.class);
    @PersistenceContext()
    EntityManager entityManager;
    
    @Override
    @Transactional
    public List<ScType> getAllTypes() throws Exception
    {
        List<ScType> result = null;
        try
        {
            Query query = entityManager.createNamedQuery("ScType.findAll");
            result = query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar todas los tipos", e);
        }
        return result;
    }

    @Override
    @Transactional
    public ScType findByIdType(ScType scType) throws Exception
    {
        ScType result = null;
        try
        {
            Query query  = entityManager.createNamedQuery("ScType.findByIdType");
            query.setParameter("idType", scType);
            result = (ScType) query.getSingleResult();
        }
        catch(Exception e)
        {
            log.error("Error intentando consultar el tipo", e.getCause());
        }
        return result;
    }
    
    @Override
    @Transactional
    public ScType findByType(ScType scType) throws Exception
    {
        ScType result = null;
        try
        {
            Query query  = entityManager.createNamedQuery("ScType.findByType");
            query.setParameter("type", scType.getType());
            result = (ScType) query.getSingleResult();
        }
        catch(Exception e)
        {
            log.error("Error intentando consultar el tipo", e.getCause());
        }
        return result;
    }
    
    @Override
    @Transactional
    public List<ScType> findByIdClassType(ScClassType scClassType) throws Exception
    {
        List<ScType> result = null;
        try
        {
            Query query  = entityManager.createNamedQuery("ScClassType.findByClassType");
            query.setParameter("idClassType", scClassType);
            result = (List<ScType> )  query.getResultList();
        }
        catch(Exception e)
        {
            log.error("Error intentando consultar el tipo", e.getCause());
        }
        return result;
    }

    @Override
    @Transactional
    public void createType(ScType scType) throws Exception
    {
        try
        {
            entityManager.persist(scType);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar crear un tipo ",e );
        }
    }

    @Override
    @Transactional
    public void deleteTypeById(ScType scType) throws Exception
    {
      int rows = -1;
        try
        {
            Query query = entityManager.createNamedQuery("ScType.deleteByIdType");
            query.setParameter("idType", scType);
            rows = query.executeUpdate();
         
        }
        catch (Exception e)
        {
            log.error("Error al intentar eliminar un tipo", e);
        }
    }

    @Override
    @Transactional
    public void updateType(ScType scType) throws Exception
    {
        try
        {
            entityManager.merge(scType);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar actualizar un tipo ",e );
        }
    }
   
}
