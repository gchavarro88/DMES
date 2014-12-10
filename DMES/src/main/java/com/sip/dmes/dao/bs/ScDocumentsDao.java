/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.beans.template.TemplateBean;
import com.sip.dmes.dao.bo.IScDocuments;
import com.sip.dmes.entitys.ScDocuments;
import com.sip.dmes.entitys.ScMachine;
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
@Repository(value = "IScDocuments")
public class ScDocumentsDao implements IScDocuments
{
    private final static Logger log = Logger.getLogger(ScDocumentsDao.class);
    @PersistenceContext()
    EntityManager entityManager;
    
    @Override
    @Transactional
    public List<ScDocuments> getAllDocuments() throws Exception
    {
        List<ScDocuments> result = null;
        try
        {
            Query query = entityManager.createNamedQuery("ScDocuments.findAll");
            result = query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar todos los documentos", e);
        }
        return result;
    }

    @Override
    @Transactional
    public ScDocuments findByIdDocument(ScDocuments scDocuments) throws Exception
    {
        ScDocuments result = null;
        try
        {
            Query query  = entityManager.createNamedQuery("ScDocuments.findByIdDocument");
            query.setParameter("idDocument", scDocuments);
            result = (ScDocuments) query.getSingleResult();
        }
        catch(Exception e)
        {
            log.error("Error intentando consultar el documento", e.getCause());
        }
        return result;
    }
    
    @Override
    @Transactional
    public ScDocuments findByDocumentName(ScDocuments scDocuments) throws Exception
    {
        ScDocuments result = null;
        try
        {
            Query query  = entityManager.createNamedQuery("ScDocuments.findByDocumentName");
            query.setParameter("documentName", scDocuments.getDocumentName());
            result = (ScDocuments) query.getSingleResult();
        }
        catch(Exception e)
        {
            log.error("Error intentando consultar el documento", e.getCause());
        }
        return result;
    }
    
    @Override
    @Transactional
    public List<ScDocuments> findByIdMachine(ScMachine scMachine) throws Exception
    {
        List<ScDocuments> result = null;
        try
        {
            Query query  = entityManager.createNamedQuery("ScDocuments.findByIdMachine");
            query.setParameter("idMachine", scMachine);
            result = (List<ScDocuments> )  query.getResultList();
        }
        catch(Exception e)
        {
            log.error("Error intentando consultar el documento por maquina", e.getCause());
        }
        return result;
    }

    @Override
    @Transactional
    public void  createDocument(ScDocuments scDocuments) throws Exception
    {
        try
        {
            entityManager.persist(scDocuments);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar crear el documento ",e );
        }
    }

    @Override
    @Transactional
    public void deleteDocumentById(ScDocuments scDocuments) throws Exception
    {
      int rows = -1;
        try
        {
            Query query = entityManager.createNamedQuery("ScDocuments.deleteByIdDocument");
            query.setParameter("idDocument", scDocuments);
            rows = query.executeUpdate();
         
        }
        catch (Exception e)
        {
            log.error("Error al intentar eliminar el documento", e);
        }
    }
    
    @Override
    @Transactional
    public void deleteDocumentByIdMachine(ScMachine scMachine) throws Exception
    {
      int rows = -1;
        try
        {
            Query query = entityManager.createNamedQuery("ScDocuments.deleteByIdMachine");
            query.setParameter("idMachine", scMachine);
            rows = query.executeUpdate();
         
        }
        catch (Exception e)
        {
            log.error("Error al intentar eliminar el documento", e);
        }
    }

    @Override
    @Transactional
    public void updateDocument(ScDocuments scDocuments) throws Exception
    {
        try
        {
            entityManager.merge(scDocuments);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar actualizar el documento ",e );
        }
    }
   
}
