/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.dao.bo.IScInput;
import com.sip.dmes.entitys.ScCostCenter;
import com.sip.dmes.entitys.ScInput;
import com.sip.dmes.entitys.ScInputLocation;
import com.sip.dmes.entitys.ScPackingUnit;
import com.sip.dmes.entitys.ScPartner;
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
            entityManager.persist(input);
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de los insumos",e);
        }
    }

    @Override
    @Transactional
    public void deleteInput(ScInput input) throws Exception
    {
        try
        {
            entityManager.remove(entityManager.contains(input)?input:entityManager.merge(input));
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
    public void saveLocationInput(ScInputLocation inputLocation) throws Exception
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
    public List<ScInputLocation> getAllInputLocations() throws Exception
    {
        List<ScInputLocation> result = null;
        Query query  = entityManager.createNamedQuery("ScInputLocation.findAll"); 
        try
        {
            result = (List<ScInputLocation>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia las localizaciones de insumo",e);
        }
        return result;
    }

}
