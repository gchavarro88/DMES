/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.beans.template.TemplateBean;
import com.sip.dmes.dao.bo.IScMachine;
import com.sip.dmes.entitys.ScMachine;
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
@Repository(value = "IScMachine")
public class ScMachineDao implements IScMachine
{
    private final static Logger log = Logger.getLogger(ScMachineDao.class);
    @PersistenceContext()
    EntityManager entityManager;
    
    @Override
    @Transactional
    public List<ScMachine> getAllMachine() throws Exception
    {
        List<ScMachine> result = null;
        try
        {
            Query query = entityManager.createNamedQuery("ScMachine.findAll");
            result = query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar todas las maquinas", e);
        }
        return result;
    }

    @Override
    @Transactional
    public ScMachine findByIdMachine(ScMachine scMachine) throws Exception
    {
        ScMachine result = null;
        try
        {
            Query query  = entityManager.createNamedQuery("ScMachine.findByIdMachine");
            query.setParameter("idMachine", scMachine);
            result = (ScMachine) query.getSingleResult();
        }
        catch(Exception e)
        {
            log.error("Error intentando consultar la maquina", e.getCause());
        }
        return result;
    }
    
    @Override
    @Transactional
    public  List<ScMachine>  findByIdMachineFather(ScMachine scMachine) throws Exception
    {
         List<ScMachine>  result = null;
        try
        {
            Query query  = entityManager.createNamedQuery("ScMachine.findByIdMachineFather");
            query.setParameter("idMachine", scMachine);
            result = ( List<ScMachine> ) query.getSingleResult();
        }
        catch(Exception e)
        {
            log.error("Error intentando consultar las partes de maquina ", e.getCause());
        }
        return result;
    }
    
    @Override
    @Transactional
    public ScMachine findByMachine(ScMachine scMachine) throws Exception
    {
        ScMachine result = null;
        try
        {
            Query query  = entityManager.createNamedQuery("ScMachine.findByMachine");
            query.setParameter("machine", scMachine.getMachine());
            result = (ScMachine) query.getSingleResult();
        }
        catch(Exception e)
        {
            log.error("Error intentando consultar la maquina", e.getCause());
        }
        return result;
    }
    
    @Override
    @Transactional
    public void createMachine(ScMachine scMachine) throws Exception
    {
        try
        {
            entityManager.persist(scMachine);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar crear la maquina ",e );
        }
    }

    @Override
    @Transactional
    public void deleteMachineById(ScMachine scMachine) throws Exception
    {
      int rows = -1;
        try
        {
            
            Query query = entityManager.createNamedQuery("ScMachine.deleteByIdMachineFather");
            query.setParameter("idMachine", scMachine);
            rows = query.executeUpdate();
            
            query = entityManager.createNamedQuery("ScMachine.deleteByIdMachine");
            query.setParameter("idMachine", scMachine);
            rows = query.executeUpdate();
         
        }
        catch (Exception e)
        {
            log.error("Error al intentar eliminar la maquina", e);
        }
    }
    
     @Override
    @Transactional
    public void deleteMachineFatherById(ScMachine scMachine) throws Exception
    {
      int rows = -1;
        try
        {
            Query query = entityManager.createNamedQuery("ScMachine.deleteByIdMachineFather");
            query.setParameter("idMachine", scMachine);
            rows = query.executeUpdate();
         
        }
        catch (Exception e)
        {
            log.error("Error al intentar eliminar las partes de la maquina", e);
        }
    }

    @Override
    @Transactional
    public void updateMachine(ScMachine scMachine) throws Exception
    {
        try
        {
            entityManager.merge(scMachine);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar actualizar una maquina ",e );
        }
    }
   
}
