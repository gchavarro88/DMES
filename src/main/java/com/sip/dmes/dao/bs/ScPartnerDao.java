/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.dao.bo.IScPartner;
import com.sip.dmes.entitys.ScPartner;
import com.sip.dmes.entitys.ScPerson;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author guschavor
 */
@Repository(value = "IScPartner")
public class ScPartnerDao implements IScPartner
{

    @PersistenceContext()
    EntityManager entityManager;

    private final static Logger log = Logger.getLogger(ScPerson.class);
    
    
    @Override
    public List<ScPartner> findAllPartners() throws Exception
    {
        List<ScPartner> result = null;
        try
        {
            Query query = entityManager.createNamedQuery("ScPartner.findAll");
            result = (List<ScPartner>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar todos los proveedores", e);
        }
        return result;
    }

    @Override
    public ScPartner findPartnerById(ScPartner scPartner) throws Exception
    {
        ScPartner result = null;
        try
        {
            Query query = entityManager.createNamedQuery("ScPartner.findByIdPartner");
            query.setParameter("idPartner", scPartner.getIdPartner());
            result = (ScPartner) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar un proveedor", e);
        }
        return result;
    }

    @Override
    @Transactional
    public void updatePartner(ScPartner scPartner) throws Exception
    {
        try
        {
            entityManager.merge(scPartner);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar actualizar un proveedor", e);
        }
    }

    @Override
    @Transactional
    public void deletePartner(ScPartner scPartner) throws Exception
    {
        int rows = 0;
        try
        {
            Query query = entityManager.createNamedQuery("ScServicesOrProducts.deleteByPartnerId");
            query.setParameter("idPartner", scPartner.getIdPartner());
            rows = query.executeUpdate();
            query = entityManager.createNamedQuery("ScPartner.deleteByPerson");
            query.setParameter("idPerson", scPartner.getIdPerson());
            rows = query.executeUpdate();
        }
        catch (Exception e)
        {
            log.error("Error al intentar eliminar un proveedor", e);
        }
    }
 
    @Override 
    @Transactional
    public void createPartner(ScPartner scPartner) throws Exception
    {
        try
        {
            entityManager.persist(scPartner);
        }
        catch (Exception e)
        {
            log.error("Error al intentar crear un proveedor", e);
        }
    }

    @Override
    public void deletePartnerById(ScPartner scPartner) throws Exception
    {
        int rows = -1;
        try
        {
            Query query = entityManager.createNamedQuery("ScServicesOrProducts.deleteByPartnerId");
            query.setParameter("idPartner", scPartner.getIdPartner());
            rows = query.executeUpdate();
            query = entityManager.createNamedQuery("ScPartner.deleteByPerson");
            query.setParameter("idPerson", scPartner.getIdPerson());
            rows = query.executeUpdate();
        }
        catch (Exception e)
        {
            log.error("Error al intentar eliminar un proveedor y todas sus dependencias"
                    + "", e);
        }
    }

    
}
