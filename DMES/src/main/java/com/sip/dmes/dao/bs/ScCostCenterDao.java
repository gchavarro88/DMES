/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.beans.template.TemplateBean;
import com.sip.dmes.dao.bo.IScCostCenter;
import com.sip.dmes.entitys.ScCostCenter;
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
@Repository(value = "IScCostCenter")
public class ScCostCenterDao implements IScCostCenter
{
    private final static Logger log = Logger.getLogger(ScCostCenterDao.class);
    @PersistenceContext()
    EntityManager entityManager;
    
    @Override
    @Transactional
    public List<ScCostCenter> getAllCostCenter() throws Exception
    {
        List<ScCostCenter> result = null;
        try
        {
            Query query = entityManager.createNamedQuery("ScCostCenter.findAll");
            result = query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar todos los centros de costo", e);
        }
        return result;
    }

    @Override
    @Transactional
    public ScCostCenter findByIdCostCenter(ScCostCenter scCostCenter) throws Exception
    {
        ScCostCenter result = null;
        try
        {
            Query query  = entityManager.createNamedQuery("ScCostCenter.findByIdCostCenter");
            query.setParameter("idCostCenter", scCostCenter);
            result = (ScCostCenter) query.getSingleResult();
        }
        catch(Exception e)
        {
            log.error("Error intentando consultar el centro de costo", e.getCause());
        }
        return result;
    }
    
    @Override
    @Transactional
    public ScCostCenter findByCostCenter(ScCostCenter scCostCenter) throws Exception
    {
        ScCostCenter result = null;
        try
        {
            Query query  = entityManager.createNamedQuery("ScType.findByType");
            query.setParameter("costCenter", scCostCenter.getCostCenter());
            result = (ScCostCenter) query.getSingleResult();
        }
        catch(Exception e)
        {
            log.error("Error intentando consultar el centro de costo", e.getCause());
        }
        return result;
    }
    
    @Override
    @Transactional
    public ScCostCenter findByNit(ScCostCenter scCostCenter) throws Exception
    {
        ScCostCenter result = null;
        try
        {
            Query query  = entityManager.createNamedQuery("ScCostCenter.findByNit");
            query.setParameter("nit", scCostCenter.getDescription());
            result = (ScCostCenter) query.getSingleResult();
        }
        catch(Exception e)
        {
            log.error("Error intentando consultar el centro de costo", e.getCause());
        }
        return result;
    }

    @Override
    @Transactional
    public void createCostCenter(ScCostCenter scCostCenter) throws Exception
    {
        try
        {
            entityManager.persist(scCostCenter);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar crear un centro de costo ",e );
        }
    }

    @Override
    @Transactional
    public void deleteCostCenterById(ScCostCenter scCostCenter) throws Exception
    {
      int rows = -1;
        try
        {
            Query query = entityManager.createNamedQuery("ScCostCenter.deleteByidCostCenter");
            query.setParameter("idCostCenter", scCostCenter);
            rows = query.executeUpdate();
         
        }
        catch (Exception e)
        {
            log.error("Error al intentar eliminar un centro de costo", e);
        }
    }

    @Override
    @Transactional
    public void updateCostCenter(ScCostCenter scCostCenter) throws Exception
    {
        try
        {
            entityManager.merge(scCostCenter);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar actualizar un centro de costos ",e );
        }
    }
   
}
