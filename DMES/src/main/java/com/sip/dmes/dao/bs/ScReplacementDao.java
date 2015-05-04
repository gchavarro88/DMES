/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.dao.bo.IScReplacement;
import com.sip.dmes.entitys.ScCostCenter;
import com.sip.dmes.entitys.ScReplacement;
import com.sip.dmes.entitys.ScLocation;
import com.sip.dmes.entitys.ScMeasureUnit;
import com.sip.dmes.entitys.ScMoney;
import com.sip.dmes.entitys.ScPartner;
import com.sip.dmes.entitys.ScPriority;
import com.sip.dmes.entitys.ScStore;
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

@Repository("IScReplacement")
public class ScReplacementDao  implements  IScReplacement
{
    
    private final static Logger log = Logger.getLogger(ScReplacementDao.class);
    @PersistenceContext()
    EntityManager entityManager;

    @Override
    
    public List<ScReplacement> getAllReplacements() throws Exception
    {
        List<ScReplacement> result = null;
        Query query  = entityManager.createNamedQuery("ScReplacement.findAll"); 
        try
        {
            result = (List<ScReplacement>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de los insumos",e);
        }
        return result;
    }

    @Override
    @Transactional 
    public void saveReplacement(ScReplacement replacement) throws Exception
    {
        try
        {
            entityManager.persist(replacement.getStock());
            entityManager.persist(replacement.getDimension());
            entityManager.persist(replacement);
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de los insumos",e);
            throw e;
        }
    }

    @Override
    @Transactional
    public void deleteReplacement(ScReplacement replacement) throws Exception
    {
        try
        {
            entityManager.remove(entityManager.contains(replacement)?replacement:entityManager.merge(replacement));
            entityManager.remove(entityManager.contains(replacement.getDimension())?replacement:entityManager.merge(replacement.getDimension()));
            entityManager.remove(entityManager.contains(replacement.getStock())?replacement:entityManager.merge(replacement.getStock()));
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de los insumos",e);
        }
    }

    @Override
    @Transactional
    public void updateReplacement(ScReplacement replacement) throws Exception
    {
        try
        {
            entityManager.merge(replacement.getDimension());
            entityManager.merge(replacement.getStock());
            entityManager.merge(replacement);
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
    public void saveLocationReplacement(ScLocation replacementLocation) throws Exception
    {
        try
        {
            entityManager.persist(replacementLocation);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de una localización de un insumo",e);
            throw e;
        }
    }

    @Override
    public List<ScLocation> getAllReplacementLocations(ScStore store) throws Exception
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
    public ScReplacement getReplacementsById(Long idReplacement) throws Exception
    {
        ScReplacement result = null;
        Query query  = entityManager.createNamedQuery("ScReplacement.findByIdReplacement"); 
        query.setParameter("idReplacement", idReplacement);
        try
        {
            result = (ScReplacement) query.getSingleResult();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia del insumo",e);
        }
        return result;
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

}
