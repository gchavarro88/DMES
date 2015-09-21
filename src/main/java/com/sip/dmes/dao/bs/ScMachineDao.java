/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Machines | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.dao.bo.IScMachine;
import com.sip.dmes.entitys.ScCostCenter;
import com.sip.dmes.entitys.ScFactoryLocation;
import com.sip.dmes.entitys.ScMachine;

import com.sip.dmes.entitys.ScMeasureUnit;
import com.sip.dmes.entitys.ScMoney;
import com.sip.dmes.entitys.ScPartner;
import com.sip.dmes.entitys.ScPriority;
import com.sip.dmes.entitys.ScTime;
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

    
    @Override
    public List<ScPartner> getAllPartners() throws Exception
    {
        List<ScPartner> result = null;
        Query query  = entityManager.createNamedQuery("ScPartner.findAll"); 
        try
        {
            result = (List<ScPartner>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de los proveedores",e);
        }
        return result;
    }

    @Override
    public List<ScCostCenter> getAllCostCenter() throws Exception
    {
        List<ScCostCenter> result = null;
        Query query  = entityManager.createNamedQuery("ScCostCenter.findAll"); 
        try
        {
            result = (List<ScCostCenter>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de los centros de costo",e);
        }
        return result;
    }

    @Override
    @Transactional
    public void saveCostCenter(ScCostCenter costCenter) throws Exception
    {
        try
        {
            entityManager.persist(costCenter);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de un centro de costos",e);
            throw e;
        }
    }


   

    @Override
    public List<ScPriority> getAllPrioritys() throws Exception
    {
        List<ScPriority> result = null;
        Query query  = entityManager.createNamedQuery("ScPriority.findAll"); 
        try
        {
            result = (List<ScPriority>) query.getResultList();
        }
        catch (Exception e) 
        {
            log.error("Error al intentar hacer la persistencia de las prioridades",e);
        }
        return result;
    }
 
    @Override
    public List<ScMeasureUnit> getAllMeasureUnits() throws Exception
    {
        List<ScMeasureUnit> result = null;
        Query query  = entityManager.createNamedQuery("ScMeasureUnit.findAll"); 
        try
        {
            result = (List<ScMeasureUnit>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de las medidas",e);
        }
        return result;
    }
    
    @Override
    public List<ScMoney> getAllMoneys() throws Exception
    {
        List<ScMoney> result = null;
        Query query  = entityManager.createNamedQuery("ScMoney.findAll"); 
        try
        {
            result = (List<ScMoney>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de las monedas",e);
        }
        return result;
    }

    @Override
    @Transactional
    public void saveMeasureUnit(ScMeasureUnit measureUnit) throws Exception
    {
        try
        {
            entityManager.persist(measureUnit);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de una medida",e);
            throw e;
        }
    }
    
     @Override
    public List<ScTime> getAllTimes() throws Exception
    {
        List<ScTime> result = null;
        Query query  = entityManager.createNamedQuery("ScTime.findAll"); 
        try
        {
            result = (List<ScTime>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de los tiempos",e);
        }
        return result;
    }

    @Override
    public List<ScFactoryLocation> getAllFactoryLocations() throws Exception
    {
        List<ScFactoryLocation> result = null;
        Query query  = entityManager.createNamedQuery("ScFactoryLocation.findAll"); 
        try
        {
            result = (List<ScFactoryLocation>) query.getResultList();
        }
        catch (Exception e) 
        {
            log.error("Error al intentar hacer la persistencia de las localizaciones de la fábrica",e);
        }
        return result;
    }

    @Override
    @Transactional
    public void saveFactoryLocation(ScFactoryLocation factoryLocation) throws Exception
    {
        try
        {
            entityManager.persist(factoryLocation);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de una localización de fábrica",e);
            throw e;
        }
    }
    
    @Override
    @Transactional 
    public void saveMachine(ScMachine machine) throws Exception
    {
        try
        {
            
            entityManager.persist(machine.getIdDimension());
            entityManager.persist(machine);
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de las máquinas",e);
            throw e;
        }
    }
    
    
    @Override
    @Transactional
    public void updateMachine(ScMachine machine) throws Exception
    {
        try
        {
            entityManager.merge(machine.getIdDimension());
            entityManager.merge(machine);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de las maquinas",e);
        }
    }

    @Override
    public List<Object[]> getCapacityByIdMachine(Long idMachine) throws Exception
    {
        List<Object[]> result = null;
        try
        {
            String nativeQuery = "SELECT pf.description, pp.name, pt.type, pm.time_use||' Minutos' AS process_time, pm.total_value_machine||' '||m.acronym \n" +
            "AS process_cost FROM dmes.sc_process_machine pm, dmes.sc_procces_product pp, dmes.sc_product_formulation pf, dmes.sc_process_type pt, \n" +
            "dmes.sc_machine ma, dmes.sc_money m WHERE pm.id_process = pp.id_process_product AND pp.id_product_formulation = pf.id_product_formulation \n" +
            "AND pt.id_process_type = pp.id_process_type AND pm.id_machine = ma.id_machine AND ma.id_money = m.id_money AND pm.id_machine = "+idMachine;
            
            Query query = entityManager.createNativeQuery(nativeQuery);
            result = (List<Object[]>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar las capacidades de la máquina "+idMachine,e);
        }
        return result;
    }
}
    

