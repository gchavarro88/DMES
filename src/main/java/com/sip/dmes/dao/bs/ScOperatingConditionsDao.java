/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.beans.template.TemplateBean;
import com.sip.dmes.dao.bo.IScOperatingConditions;
import com.sip.dmes.entitys.ScMachine;
import com.sip.dmes.entitys.ScOperatingConditions;
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
@Repository(value = "IScOperatingConditions")
public class ScOperatingConditionsDao implements IScOperatingConditions
{
    private final static Logger log = Logger.getLogger(ScOperatingConditionsDao.class);
    @PersistenceContext()
    EntityManager entityManager;
    
    @Override
    @Transactional
    public List<ScOperatingConditions> getOperatingConditions() throws Exception
    {
        List<ScOperatingConditions> result = null;
        try
        {
            Query query = entityManager.createNamedQuery("ScOperatingConditions.findAll");
            result = query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar todas las condiciones de funcionamiento", e);
        }
        return result;
    }

    @Override
    @Transactional
    public ScOperatingConditions findByIdOperatingConditions(ScOperatingConditions scOperatingConditions) throws Exception
    {
        ScOperatingConditions result = null;
        try
        {
            Query query  = entityManager.createNamedQuery("ScOperatingConditions.findByIdOperatingCondition");
            query.setParameter("idOperatingCondition", scOperatingConditions);
            result = (ScOperatingConditions) query.getSingleResult();
        }
        catch(Exception e)
        {
            log.error("Error intentando consultar la condicion de funcionamiento", e.getCause());
        }
        return result;
    }
    
    @Override
    @Transactional
    public List<ScOperatingConditions> findByIdMachine(ScMachine scMachine) throws Exception
    {
        List<ScOperatingConditions> result = null;
        try
        {
            Query query  = entityManager.createNamedQuery("ScOperatingConditions.findByIdMachine");
            query.setParameter("idMachine", scMachine);
            result = (List<ScOperatingConditions> )  query.getResultList();
        }
        catch(Exception e)
        {
            log.error("Error intentando consultar la condicion de funcionamiento por maquina", e.getCause());
        }
        return result;
    }

    @Override
    @Transactional
    public void createOperatingConditions(ScOperatingConditions scOperatingConditions) throws Exception
    {
        try
        {
            entityManager.persist(scOperatingConditions);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar crear la condicion de funcionamiento ",e );
        }
    }

    @Override
    @Transactional
    public void deleteOperatingConditionsById(ScOperatingConditions scOperatingConditions) throws Exception
    {
      int rows = -1;
        try
        {
            Query query = entityManager.createNamedQuery("ScOperatingConditions.deleteByIdOperatingCondition");
            query.setParameter("idOperatingCondition", scOperatingConditions);
            rows = query.executeUpdate();
         
        }
        catch (Exception e)
        {
            log.error("Error al intentar eliminar la condicion de funcionamiento", e);
        }
    }
    
    @Override
    @Transactional
    public void deleteOperatingConditionsByIdMachine(ScMachine scMachine) throws Exception
    {
      int rows = -1;
        try
        {
            Query query = entityManager.createNamedQuery("ScOperatingConditions.deleteByIdMachine");
            query.setParameter("idMachine", scMachine);
            rows = query.executeUpdate();
         
        }
        catch (Exception e)
        {
            log.error("Error al intentar eliminar la condicion de funcionamiento", e);
        }
    }

    @Override
    @Transactional
    public void updateOperatingConditions(ScOperatingConditions scOperatingConditions) throws Exception
    {
        try
        {
            entityManager.merge(scOperatingConditions);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar actualizar la condicion de funcionamiento ",e );
        }
    }
   
}
