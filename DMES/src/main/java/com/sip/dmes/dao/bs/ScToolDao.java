/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.beans.template.TemplateBean;
import com.sip.dmes.dao.bo.IScTool;
import com.sip.dmes.entitys.ScClassType;
import com.sip.dmes.entitys.ScCostCenter;
import com.sip.dmes.entitys.ScLocation;
import com.sip.dmes.entitys.ScMeasureUnit;
import com.sip.dmes.entitys.ScMoney;
import com.sip.dmes.entitys.ScPartner;
import com.sip.dmes.entitys.ScPriority;
import com.sip.dmes.entitys.ScTool;
import com.sip.dmes.entitys.ScStore;
import com.sip.dmes.entitys.ScTime;
import com.sip.dmes.entitys.ScTools;
import com.sip.dmes.entitys.ScType;
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
@Repository(value = "IScTool")
public class ScToolDao implements IScTool
{
    private final static Logger log = Logger.getLogger(ScToolDao.class);
    @PersistenceContext()
    EntityManager entityManager;

    @Override
    
    public List<ScTool> getAllTools() throws Exception
    {
        List<ScTool> result = null;
        Query query  = entityManager.createNamedQuery("ScTool.findAll"); 
        try
        {
            result = (List<ScTool>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de las herramientas",e);
        }
        return result;
    }

    @Override
    @Transactional 
    public void saveTool(ScTool tool) throws Exception
    {
        try
        {
            entityManager.persist(tool.getStock());
            entityManager.persist(tool.getDimension());
            entityManager.persist(tool);
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de los herramientas",e);
            throw e;
        }
    }

    @Override
    @Transactional
    public void deleteTool(ScTool tool) throws Exception
    {
        try
        {
            entityManager.remove(entityManager.contains(tool)?tool:entityManager.merge(tool));
            entityManager.remove(entityManager.contains(tool.getDimension())?tool:entityManager.merge(tool.getDimension()));
            entityManager.remove(entityManager.contains(tool.getStock())?tool:entityManager.merge(tool.getStock()));
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de los herramientas",e);
        }
    }

    @Override
    @Transactional
    public void updateTool(ScTool tool) throws Exception
    {
        try
        {
            entityManager.merge(tool.getDimension());
            entityManager.merge(tool.getStock());
            entityManager.merge(tool);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de los herramientas",e);
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
    public void saveLocationTool(ScLocation toolLocation) throws Exception
    {
        try
        {
            entityManager.persist(toolLocation);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de una localización de una herramienta",e);
            throw e;
        }
    }

    @Override
    public List<ScLocation> getAllToolLocations(ScStore store) throws Exception
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
            log.error("Error al intentar hacer la persistencia las localizaciones de herramientas",e);
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
    public ScTool getToolsById(Long idTool) throws Exception
    {
        ScTool result = null;
        Query query  = entityManager.createNamedQuery("ScTool.findByIdTool"); 
        query.setParameter("idTool", idTool);
        try
        {
            result = (ScTool) query.getSingleResult();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia del herramientas",e);
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
