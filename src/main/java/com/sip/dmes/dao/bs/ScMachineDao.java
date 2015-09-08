/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Machines | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.dao.bo.IScMachine;
import com.sip.dmes.dao.bo.IScMachinePart;
import com.sip.dmes.entitys.ScCostCenter;
import com.sip.dmes.entitys.ScMachinePart;
import com.sip.dmes.entitys.ScMachine;

import com.sip.dmes.entitys.ScMeasureUnit;
import com.sip.dmes.entitys.ScMoney;
import com.sip.dmes.entitys.ScPartner;
import com.sip.dmes.entitys.ScPriority;
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

@Repository("IScMachine")
public class ScMachineDao  implements  IScMachine
{
    
    private final static Logger log = Logger.getLogger(ScMachineDao.class);
    @PersistenceContext()
    EntityManager entityManager;

    
    
    @Override
    public List<ScMachine> getAllMachine() throws Exception
    {
        List<ScMachine> result = null;
        try
        {
            Query query = entityManager.createNamedQuery("ScMachine.findAll");
            result = (List<ScMachine>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar las máquinas",e);
        }
        return result;
    }

    @Override
    public Object getInitialParameters() throws Exception
    {
        Object result = null;
        try
        {
            String sql = "SELECT MAX_SIZE_FILE, EXTENSION, PATH FROM dmes.sc_constants_load_files";
            Query query = entityManager.createNativeQuery(sql);
            result = query.getSingleResult();
        }
        catch(Exception e)
        {
            log.error("Error al intentar consultar los parámetros iniciales para cargar archivos",e);
        }
        return result;
    }

    @Override
    @Transactional
    public void deleteMachine(ScMachine machine) throws Exception
    {
        try
        {
            entityManager.remove(entityManager.contains(machine)?machine:entityManager.merge(machine));
            entityManager.remove(entityManager.contains(machine.getIdDimension())?machine:entityManager.merge(machine.getIdDimension()));
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de las máquinas",e);
        }
    }

}
