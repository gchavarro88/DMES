/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.dao.bo.IScInput;
import com.sip.dmes.entitys.ScCostCenter;
import com.sip.dmes.entitys.ScDistributionUnit;
import com.sip.dmes.entitys.ScInput;
import com.sip.dmes.entitys.ScLocation;
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

@Repository("IScInput")
public class ScInputDao  implements  IScInput
{
    
    private final static Logger log = Logger.getLogger(ScInputDao.class);
    @PersistenceContext()
    EntityManager entityManager;

    @Override
    
    public List<ScInput> getAllInputs() throws Exception
    {
        List<ScInput> result = null;
        Query query  = entityManager.createNamedQuery("ScInput.findAll"); 
        try
        {
            result = (List<ScInput>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de los insumos",e);
        }
        return result;
    }

    @Override
    @Transactional 
    public void saveInput(ScInput input) throws Exception
    {
        try
        {
            entityManager.persist(input.getInputStock());
            entityManager.persist(input.getDimension());
            entityManager.persist(input);
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de los insumos",e);
            throw e;
        }
    }

    @Override
    @Transactional
    public void deleteInput(ScInput input) throws Exception
    {
        try
        {
            entityManager.remove(entityManager.contains(input)?input:entityManager.merge(input));
            entityManager.remove(entityManager.contains(input.getDimension())?input:entityManager.merge(input.getDimension()));
            entityManager.remove(entityManager.contains(input.getInputStock())?input:entityManager.merge(input.getInputStock()));
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de los insumos",e);
        }
    }

    @Override
    @Transactional
    public void updateInput(ScInput input) throws Exception
    {
        try
        {
            entityManager.merge(input.getDimension());
            entityManager.merge(input.getInputStock());
            entityManager.merge(input);
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de los insumos",e);
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
    @Transactional
    public void savePackingUnit(ScPackingUnit packingUnit) throws Exception
    {
        try
        {
            entityManager.persist(packingUnit);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de una unidad de empaque",e);
            throw e;
        }
    }

    @Override
    @Transactional
    public void saveLocationInput(ScLocation inputLocation) throws Exception
    {
        try
        {
            entityManager.persist(inputLocation);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de una localización de un insumo",e);
            throw e;
        }
    }

    @Override
    public List<ScPackingUnit> getAllPackingUnits() throws Exception
    {
        List<ScPackingUnit> result = null;
        Query query  = entityManager.createNamedQuery("ScPackingUnit.findAll"); 
        try
        {
            result = (List<ScPackingUnit>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia las unidades de empaque",e);
        }
        return result;
    }

    @Override
    public List<ScLocation> getAllInputLocations(ScStore store) throws Exception
    {
        List<ScLocation> result = null;
        Query query  = entityManager.createNamedQuery("ScLocation.findByStore"); 
        query.setParameter("store", store);
        try
        {
            result = (List<ScLocation>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia las localizaciones de insumo",e);
        }
        return result;
    }

    @Override
    public List<ScStore> getAllStores() throws Exception
    {
        List<ScStore> result = null;
        Query query  = entityManager.createNamedQuery("ScStore.findAll"); 
        try
        {
            result = (List<ScStore>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de los almacenes",e);
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

    @Override
    public List<ScDistributionUnit> getAllDistributionUnits() throws Exception
    {
        List<ScDistributionUnit> result = null;
        Query query  = entityManager.createNamedQuery("ScDistributionUnit.findAll"); 
        try
        {
            result = (List<ScDistributionUnit>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de las unidades de distribución",e);
        }
        return result;
    }

    @Override
    @Transactional
    public void saveDistributionUnit(ScDistributionUnit distributionUnit) throws Exception
    {
        try
        {
            entityManager.persist(distributionUnit);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de una unidad de distribución",e);
            throw e;
        }
    }

    @Override
    public ScInput getInputsById(Long idInput) throws Exception
    {
        ScInput result = null;
        Query query  = entityManager.createNamedQuery("ScInput.findByIdInput"); 
        query.setParameter("idInput", idInput);
        try
        {
            result = (ScInput) query.getSingleResult();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia del insumo",e);
        }
        return result;
    }

}
