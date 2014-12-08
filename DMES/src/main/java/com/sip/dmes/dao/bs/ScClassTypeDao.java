/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.beans.template.TemplateBean;
import com.sip.dmes.dao.bo.IScClassType;
import com.sip.dmes.entitys.ScClassType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gchavarro88
 */
@Repository(value = "IScClassType")
public class ScClassTypeDao implements IScClassType
{
    private final static Logger log = Logger.getLogger(ScClassTypeDao.class);
    @PersistenceContext()
    EntityManager entityManager;

    @Override
    @Transactional
    public ScClassType findByIdClassType(ScClassType scClassType) throws Exception
    {
        ScClassType result = null;
        try
        {
            Query query  = entityManager.createNamedQuery("ScClassType.findByIdClassType");
            query.setParameter("idClassType", scClassType.getIdClassType());
            result = (ScClassType) query.getSingleResult();
        }
        catch(Exception e)
        {
            log.error("Error intentando consultar la clase tipo", e.getCause());
        }
        return result;
    }
    
     @Override
    @Transactional
    public ScClassType findByClassType(ScClassType scClassType) throws Exception
    {
        ScClassType result = null;
        try
        {
            Query query  = entityManager.createNamedQuery("ScClassType.findByClassType");
            query.setParameter("classType", scClassType.getClassType());
            result = (ScClassType) query.getSingleResult();
        }
        catch(Exception e)
        {
            log.error("Error intentando consultar la clase tipo", e.getCause());
        }
        return result;
    }

    @Override
    @Transactional
    public void createClassType(ScClassType scClassType) throws Exception
    {
        try
        {
            entityManager.persist(scClassType);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar crear una clase tipo ",e );
        }
    }

    @Override
    @Transactional
    public void deleteClassTypeById(ScClassType scClassType) throws Exception
    {
      int rows = -1;
        try
        {
            Query query = entityManager.createNamedQuery("ScType.deleteByIdClassType");
            query.setParameter("idClassType", scClassType);
            rows = query.executeUpdate();
            
            query = entityManager.createNamedQuery("ScClassType.deleteByIdClassType");
            query.setParameter("idClassType", scClassType);
            rows = query.executeUpdate();
         
        }
        catch (Exception e)
        {
            log.error("Error al intentar eliminar una clase tipo", e);
        }
    }

    @Override
    @Transactional
    public void updateClassType(ScClassType scClassType) throws Exception
    {
        try
        {
            entityManager.merge(scClassType);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar actualizar una clase tipo ",e );
        }
    }
    
    @Override
    @Transactional
    public List<ScClassType> getAllClassTypes() throws Exception
    {
        List<ScClassType> result = null;
        try
        {
            Query query = entityManager.createNamedQuery("ScClassType.findAll");
            result = query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar todas las clase tipos", e);
        }
        return result;
    }
    
}
