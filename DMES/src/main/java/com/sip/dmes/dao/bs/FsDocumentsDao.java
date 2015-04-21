/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.dao.bo.IFsDocuments;
import com.sip.dmes.entitys.ScDocuments;
import com.sip.dmes.entitys.ScUsers;
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
@Repository("IFsDocuments")
public class FsDocumentsDao implements IFsDocuments
{

    @PersistenceContext
    EntityManager entityManager;
    private final static Logger log = Logger.getLogger(FsDocumentsDao.class);
    
    
    @Override
    @Transactional 
    public void createDocument(ScDocuments scDocuments) throws Exception
    {
        try
        {  
            entityManager.persist(scDocuments);
        }
        catch (Exception e)
        {
            log.error("Error al intentar crear un nuevo documento", e);
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
            log.error("Error al intentar actualizar un nuevo documento", e);
        }
    }

    @Override
    @Transactional 
    public void deleteteDocumentById(ScDocuments scDocuments) throws Exception
    {
        try
        { 
            String sql = "DELETE FROM ScDocuments s WHERE s.idDocument = :idDocument";
            Query query = entityManager.createQuery(sql);
            query.setParameter("idDocument", scDocuments.getIdDocument());
            int rows = query.executeUpdate();
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar eliminar un nuevo documento", e);
        }
    }

    @Override
    public List<ScDocuments> getAllDocumentsByUser(ScUsers  scUser) throws Exception
    {
        List<ScDocuments> result = null;
        try
        {
            Query query = entityManager.createNamedQuery("ScDocuments.findByPerson");
            query.setParameter("idPerson", scUser.getIdPerson());
            result = (List<ScDocuments>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intetnar consultar los documentos por el usuario "+scUser);
        }
        return result;
    }
    
    @Override
    public List<ScDocuments> getAllDocumentsToUser(ScUsers  scUser) throws Exception
    {
        List<ScDocuments> result = null;
        try
        {
            Query query = entityManager.createNamedQuery("ScDocuments.findToPerson");
            query.setParameter("scUser", scUser.getLogin());
            result = (List<ScDocuments>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intetnar consultar los documentos por el usuario "+scUser);
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
    public  List<ScUsers> getUsersToDocuments(ScUsers scUser) throws Exception
    {
        List<ScUsers> result = null;
        try
        {
            Query query = entityManager.createNamedQuery("ScUsers.findNotIdUser");
            query.setParameter("idUser", scUser.getIdUser());
            result = (List<ScUsers>) query.getResultList(); 
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar los usuariios para cargar archivos",e);
        }
        return result;
    }
    
    
    
}
