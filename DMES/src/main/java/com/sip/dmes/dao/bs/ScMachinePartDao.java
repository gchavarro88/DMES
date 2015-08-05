/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.dao.bo.IScMachinePart;
import com.sip.dmes.entitys.ScCostCenter;
import com.sip.dmes.entitys.ScDistributionUnit;
import com.sip.dmes.entitys.ScMachinePart;
import com.sip.dmes.entitys.ScLocation;
import com.sip.dmes.entitys.ScMachine;
import com.sip.dmes.entitys.ScMachineLocation;
import com.sip.dmes.entitys.ScMeasureUnit;
import com.sip.dmes.entitys.ScMoney;
import com.sip.dmes.entitys.ScPackingUnit;
import com.sip.dmes.entitys.ScPartner;
import com.sip.dmes.entitys.ScPriority;
import com.sip.dmes.entitys.ScStore;
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

@Repository("IScMachinePart")
public class ScMachinePartDao  implements  IScMachinePart
{
    
    private final static Logger log = Logger.getLogger(ScMachinePartDao.class);
    @PersistenceContext()
    EntityManager entityManager;

    @Override
    
    public List<ScMachinePart> getAllMachineParts() throws Exception
    {
        List<ScMachinePart> result = null;
        Query query  = entityManager.createNamedQuery("ScMachinePart.findAll"); 
        try
        {
            result = (List<ScMachinePart>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de las partes de una máquina",e);
        }
        return result;
    }

    @Override
    @Transactional 
    public void saveMachinePart(ScMachinePart machinePart) throws Exception
    {
        try
        {
//            entityManager.persist(machinePart.getMachinePartStock());
//            entityManager.persist(machinePart.getDimension());
            entityManager.persist(machinePart);
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de la parte de una máquina",e);
            throw e;
        }
    }

    @Override
    @Transactional
    public void deleteMachinePart(ScMachinePart machinePart) throws Exception
    {
        try
        {
            entityManager.remove(entityManager.contains(machinePart)?machinePart:entityManager.merge(machinePart));
            entityManager.flush(); 
//            entityManager.remove(entityManager.contains(machinePart.getDimension())?machinePart:entityManager.merge(machinePart.getDimension()));
//            entityManager.remove(entityManager.contains(machinePart.getMachinePartStock())?machinePart:entityManager.merge(machinePart.getMachinePartStock()));
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de la parte de una máquina",e);
        }
    }

    @Override
    @Transactional
    public void updateMachinePart(ScMachinePart machinePart) throws Exception
    {
        try
        {
//            entityManager.merge(machinePart.getDimension());
//            entityManager.merge(machinePart.getMachinePartStock());
            entityManager.merge(machinePart);
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de la parte de una máquina",e);
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

//    @Override
//    @Transactional
//    public void savePackingUnit(ScPackingUnit packingUnit) throws Exception
//    {
//        try
//        {
//            entityManager.persist(packingUnit);
//            entityManager.flush();
//        }
//        catch (Exception e)
//        {
//            log.error("Error al intentar hacer la persistencia de una unidad de empaque",e);
//            throw e;
//        }
//    }

    @Override
    @Transactional
    public void saveLocationMachinePart(ScMachineLocation machinePartLocation) throws Exception
    {
        try
        {
            entityManager.persist(machinePartLocation);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de una localización de una  máquina",e);
            throw e;
        }
    }

//    @Override
//    public List<ScPackingUnit> getAllPackingUnits() throws Exception
//    {
//        List<ScPackingUnit> result = null;
//        Query query  = entityManager.createNamedQuery("ScPackingUnit.findAll"); 
//        try
//        {
//            result = (List<ScPackingUnit>) query.getResultList();
//        }
//        catch (Exception e)
//        {
//            log.error("Error al intentar hacer la persistencia las unidades de empaque",e);
//        }
//        return result;
//    }

    @Override
    public List<ScMachineLocation> getAllMachinePartLocations(ScMachine  machine) throws Exception
    {
        List<ScMachineLocation> result = null;
        Query query  = entityManager.createNamedQuery("ScMachineLocation.findByMachine"); 
        query.setParameter("machine", machine);
        try
        {
            result = (List<ScMachineLocation>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia las localizaciones de la parte de una máquina",e);
        }
        return result;
    }

    @Override
    public List<ScMachine> getAllMachines() throws Exception
    {
        List<ScMachine> result = null;
        Query query  = entityManager.createNamedQuery("ScMachine.findAll"); 
        try
        {
            result = (List<ScMachine>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de las máquinas",e);
        }
        return result;
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

//    @Override
//    public List<ScDistributionUnit> getAllDistributionUnits() throws Exception
//    {
//        List<ScDistributionUnit> result = null;
//        Query query  = entityManager.createNamedQuery("ScDistributionUnit.findAll"); 
//        try
//        {
//            result = (List<ScDistributionUnit>) query.getResultList();
//        }
//        catch (Exception e)
//        {
//            log.error("Error al intentar hacer la persistencia de las unidades de distribución",e);
//        }
//        return result;
//    }

//    @Override
//    @Transactional
//    public void saveDistributionUnit(ScDistributionUnit distributionUnit) throws Exception
//    {
//        try
//        {
//            entityManager.persist(distributionUnit);
//            entityManager.flush();
//        }
//        catch (Exception e)
//        {
//            log.error("Error al intentar hacer la persistencia de una unidad de distribución",e);
//            throw e;
//        }
//    }

    @Override
    public ScMachinePart getMachinePartsById(Long idMachinePart) throws Exception
    {
        ScMachinePart result = null;
        Query query  = entityManager.createNamedQuery("ScMachinePart.findByIdMachinePart"); 
        query.setParameter("idMachinePart", idMachinePart);
        try
        {
            result = (ScMachinePart) query.getSingleResult();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia del insumo",e);
        }
        return result;
    }

}
