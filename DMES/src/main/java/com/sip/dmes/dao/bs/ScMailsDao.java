/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.dao.bo.IScMails;
import com.sip.dmes.entitys.ScMails;
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

  @Repository("IScMails")
    public class ScMailsDao  implements  IScMails{
    
    private final static Logger log = Logger.getLogger(ScMails.class);
    @PersistenceContext()
    EntityManager entityManager;

    @Override
    @Transactional
    public void addScMails(ScMails ScMails) {

        try {
            entityManager.persist(ScMails);

        } catch (Exception e) {
            log.error("Error guardar la email", e);
        }

    }

    @Override
    @Transactional
    public void updateScMails(ScMails ScMails) {

        try {
            entityManager.merge(ScMails);

        } catch (Exception e) {
            log.error("Error actualizar la email", e);
        }

    }

    @Override
    @Transactional
    public void deleteScMails(ScMails ScMails) {

        try {
            entityManager.remove(ScMails);

        } catch (Exception e) {
            log.error("Error al borrar la email", e);
        }

    }

    @Override
    @Transactional
    public ScMails getScMailsById(int id) {
        ScMails result = null;
        try {

            Query query = entityManager.createNamedQuery("SScMails.findByIdMail");
            query.setParameter("idMail", id);
            
            result = (ScMails) query.getSingleResult();

        } catch (Exception e) {

            log.error("Error intentando buscar la email", e);
        }

        return result;
    }

    @Override
    @Transactional
    public List<ScMails> getScMailss() {

        List<ScMails> result = null;
        try {

            Query query = entityManager.createNamedQuery("ScMails.findAll");
            result =  query.getResultList();

        } catch (Exception e) {

            log.error("Error consulta todas las email", e);
        }

        return result;
    }
    
}
