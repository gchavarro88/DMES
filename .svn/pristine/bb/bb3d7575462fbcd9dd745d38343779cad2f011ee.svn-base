/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.beans.template.TemplateBean;
import com.sip.dmes.dao.bo.IScMaintenancePlan;
import com.sip.dmes.entitys.ScMachine;
import com.sip.dmes.entitys.ScMaintenancePlan;
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
@Repository(value = "IScMaintenancePlan")
public class ScMaintenancePlanDao implements IScMaintenancePlan
{
    private final static Logger log = Logger.getLogger(ScMaintenancePlanDao.class);
    @PersistenceContext()
    EntityManager entityManager;
    
    @Override
    @Transactional
    public List<ScMaintenancePlan> getAllMaintenancePlan() throws Exception
    {
        List<ScMaintenancePlan> result = null;
        try
        {
            Query query = entityManager.createNamedQuery("ScMaintenancePlan.findAll");
            result = query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar todos los planes de mantenimiento ", e);
        }
        return result;
    }

    @Override
    @Transactional
    public ScMaintenancePlan findByIdMaintenancePlan(ScMaintenancePlan scMaintenancePlan) throws Exception
    {
        ScMaintenancePlan result = null;
        try
        {
            Query query  = entityManager.createNamedQuery("ScMaintenancePlan.findByIdMaintenancePlan");
            query.setParameter("idMaintenancePlan", scMaintenancePlan);
            result = (ScMaintenancePlan) query.getSingleResult();
        }
        catch(Exception e)
        {
            log.error("Error intentando consultar el plan de mantenimiento ", e.getCause());
        }
        return result;
    }
    
    @Override
    @Transactional
    public  List<ScMaintenancePlan> findByIdMachine(ScMachine scMachine) throws Exception
    {
         List<ScMaintenancePlan>  result = null;
        try
        {
            Query query  = entityManager.createNamedQuery("ScMaintenancePlan.findByIdMachine");
            query.setParameter("idMachine", scMachine);
            result = ( List<ScMaintenancePlan> ) query.getSingleResult();
        }
        catch(Exception e)
        {
            log.error("Error intentando consultar los planes de mantenimiento por maquina ", e.getCause());
        }
        return result;
    }
    
    @Override
    @Transactional
    public void createMaintenancePlan(ScMaintenancePlan scMaintenancePlan) throws Exception
    {
        try
        {
            entityManager.persist(scMaintenancePlan);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar crear el plan de mantenimiento ",e );
        }
    }

    @Override
    @Transactional
    public void deleteMaintenancePlanById(ScMaintenancePlan scMaintenancePlan) throws Exception
    {
      int rows = -1;
        try
        {
            Query query = entityManager.createNamedQuery("ScMaintenancePlan.deleteByIdMaintenancePlan");
            query.setParameter("idMaintenancePlan", scMaintenancePlan);
            rows = query.executeUpdate();
            
        }
        catch (Exception e)
        {
            log.error("Error al intentar eliminar el plan de mantenimiento ", e);
        }
    }
    
     @Override
    @Transactional
    public void deleteMaintenancePlanByIdMachine(ScMachine scMachine) throws Exception
    {
      int rows = -1;
        try
        {
            Query query = entityManager.createNamedQuery("ScMaintenancePlan.deleteByIdMachine");
            query.setParameter("idMachine", scMachine);
            rows = query.executeUpdate();
         
        }
        catch (Exception e)
        {
            log.error("Error al intentar eliminar los planes de mantenimiento por maquina", e);
        }
    }

    @Override
    @Transactional
    public void updateMaintenancePlan(ScMaintenancePlan scMaintenancePlan) throws Exception
    {
        try
        {
            entityManager.merge(scMaintenancePlan);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar actualizar el plan de mantenimiento ",e );
        }
    }
   
}
