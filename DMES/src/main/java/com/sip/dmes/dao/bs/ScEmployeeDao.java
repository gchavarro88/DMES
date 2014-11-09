/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.dao.bo.IScEmployee;
import com.sip.dmes.entitys.ScEmployee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author user
 */
@Repository("IScEmployee")
public class ScEmployeeDao implements IScEmployee
{
    @PersistenceContext
    EntityManager entityManager;
    private final static Logger log = Logger.getLogger(ScEmployeeDao.class);

    @Override
    @Transactional
    public void createEmployee(ScEmployee employee) throws Exception
    {
        try
        {
            entityManager.persist(employee);
        }
        catch (Exception e)
        {
            log.error("Error al intentar crear un nuevo empleado", e);
        }
    }

    @Override
    @Transactional
    public void updateEmployee(ScEmployee employee) throws Exception
    {
        try
        {
            entityManager.merge(employee);
        }
        catch (Exception e)
        {
            log.error("Error al intentar actualizar un empleado", e);
        }
    }

    @Override
    @Transactional
    public void deleteteEmployeeById(ScEmployee employee) throws Exception
    {
        try
        {
            entityManager.remove(employee);
        }
        catch (Exception e)
        {
            log.error("Error al intentar eliminar un empleado", e);
        }
    }

    @Override
    @Transactional
    public List<ScEmployee> getAllEmployees() throws Exception
    {
        List<ScEmployee> result = null;
        try
        {
            Query query = entityManager.createNamedQuery("ScEmployee.findAll");
            result = (List<ScEmployee>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar consutlar los empleados", e);
        }
        return result;
    }
   
    

}
