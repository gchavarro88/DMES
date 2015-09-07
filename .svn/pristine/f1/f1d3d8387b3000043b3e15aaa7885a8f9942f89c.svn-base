/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.dao.bo.IScPersonDocumentationAttached;
import com.sip.dmes.entitys.ScPerson;
import com.sip.dmes.entitys.ScPersonDocumentationAttached;
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
@Repository(value = "IScPersonDocumentationAttached")
public class ScPersonDocumentationAttachedDao implements IScPersonDocumentationAttached{
    
    
    
    private final static Logger log = Logger.getLogger(ScPersonDocumentationAttached.class);
    @PersistenceContext()
    EntityManager entityManager;

    @Override
    @Transactional
    public void addScPersonDocumentationAttached(ScPersonDocumentationAttached ScPersonDocumentationAttached) {

        try {
            entityManager.persist(ScPersonDocumentationAttached);

        } catch (Exception e) {
            log.error("Error guardar los documentos de la persona", e);
        }

    }

    @Override
    @Transactional
    public void updateScPersonDocumentationAttached(ScPersonDocumentationAttached ScPersonDocumentationAttached) {

        try {
            entityManager.merge(ScPersonDocumentationAttached);

        } catch (Exception e) {
            log.error("Error actualizar documentos de la persona", e);
        }

    }

    @Override
    @Transactional
    public void deleteScPersonDocumentationAttached(ScPersonDocumentationAttached ScPersonDocumentationAttached) {

        try {
            entityManager.remove(ScPersonDocumentationAttached);

        } catch (Exception e) {
            log.error("Error el documento de la persona", e);
        }

    }

    @Override
    @Transactional
    public ScPersonDocumentationAttached getScPersonDocumentationAttachedById(int id) {
        ScPersonDocumentationAttached result = null;
        try {

            Query query = entityManager.createNamedQuery("ScPersonDocumentationAttached.findByIdPerson");
            result = (ScPersonDocumentationAttached) query.getSingleResult();

        } catch (Exception e) {

            log.error("Error intentando buscar la persona", e);
        }

        return result;
    }

    @Override
    @Transactional
    public List<ScPersonDocumentationAttached> getScPersonDocumentationAttacheds() {

        List<ScPersonDocumentationAttached> result = null;
        try {

            Query query = entityManager.createNamedQuery("ScPersonDocumentationAttached.findAll");
          
            result =  query.getResultList();

        } catch (Exception e) {

            log.error("Error consulta todas las personas", e);
        }

        return result;
    }

    @Override
    public List<ScPersonDocumentationAttached> getScPersonDocumentationAttachedsForPerson(ScPerson scPerson) {
      
         List<ScPersonDocumentationAttached> result = null;
        try {

            Query query = entityManager.createNamedQuery("ScPersonDocumentationAttached.findByIdPersonDocumentationAttached");
             query.setParameter("idPersonDocumentationAttached", scPerson);
            result =  query.getResultList();

        } catch (Exception e) {

            log.error("Error consulta toda la documentacion de una persona", e);
        }

        return result;
    }
    
}
