/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.beans.template.TemplateBean;
import com.sip.dmes.dao.bo.IScPhoto;
import com.sip.dmes.entitys.ScMachine;
import com.sip.dmes.entitys.ScPhoto;
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
@Repository(value = "IScPhoto")
public class ScPhotoDao implements IScPhoto
{
    private final static Logger log = Logger.getLogger(ScPhotoDao.class);
    @PersistenceContext()
    EntityManager entityManager;
    
    @Override
    @Transactional
    public List<ScPhoto> getAllPhoto() throws Exception
    {
        List<ScPhoto> result = null;
        try
        {
            Query query = entityManager.createNamedQuery("ScPhoto.findAll");
            result = query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar todas las fotos", e);
        }
        return result;
    }

    @Override
    @Transactional
    public ScPhoto findByIdPhoto(ScPhoto scPhoto) throws Exception
    {
        ScPhoto result = null;
        try
        {
            Query query  = entityManager.createNamedQuery("ScPhoto.findByIdPhoto");
            query.setParameter("idPhoto", scPhoto);
            result = (ScPhoto) query.getSingleResult();
        }
        catch(Exception e)
        {
            log.error("Error intentando consultar la foto", e.getCause());
        }
        return result;
    }
    
    @Override
    @Transactional
    public ScPhoto findByPhotoName(ScPhoto scPhoto) throws Exception
    {
        ScPhoto result = null;
        try
        {
            Query query  = entityManager.createNamedQuery("ScPhoto.findByPhotoName");
            query.setParameter("photoName", scPhoto.getPhotoName());
            result = (ScPhoto) query.getSingleResult();
        }
        catch(Exception e)
        {
            log.error("Error intentando consultar la foto", e.getCause());
        }
        return result;
    }
    
    @Override
    @Transactional
    public List<ScPhoto> findByIdMachine(ScMachine scMachine) throws Exception
    {
        List<ScPhoto> result = null;
        try
        {
            Query query  = entityManager.createNamedQuery("ScPhoto.findByIdMachine");
            query.setParameter("idMachine", scMachine);
            result = (List<ScPhoto> )  query.getResultList();
        }
        catch(Exception e)
        {
            log.error("Error intentando consultar la foto por maquina", e.getCause());
        }
        return result;
    }

    @Override
    @Transactional
    public void  createPhoto(ScPhoto scPhoto) throws Exception
    {
        try
        {
            entityManager.persist(scPhoto);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar crear la foto ",e );
        }
    }

    @Override
    @Transactional
    public void deletePhotoById(ScPhoto scPhoto) throws Exception
    {
      int rows = -1;
        try
        {
            Query query = entityManager.createNamedQuery("ScPhoto.deleteByIdPhoto");
            query.setParameter("idPhoto", scPhoto);
            rows = query.executeUpdate();
         
        }
        catch (Exception e)
        {
            log.error("Error al intentar eliminar la foto", e);
        }
    }
    
    @Override
    @Transactional
    public void deletePhotoByIdMachine(ScMachine scMachine) throws Exception
    {
      int rows = -1;
        try
        {
            Query query = entityManager.createNamedQuery("ScPhoto.deleteByIdMachine");
            query.setParameter("idMachine", scMachine);
            rows = query.executeUpdate();
         
        }
        catch (Exception e)
        {
            log.error("Error al intentar eliminar la foto", e);
        }
    }

    @Override
    @Transactional
    public void updatePhoto(ScPhoto scPhoto) throws Exception
    {
        try
        {
            entityManager.merge(scPhoto);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar actualizar la foto ",e );
        }
    }
   
}
