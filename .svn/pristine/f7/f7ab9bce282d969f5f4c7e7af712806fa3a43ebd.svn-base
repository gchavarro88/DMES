/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.dao.bo.IScPersonObservations;
import com.sip.dmes.entitys.ScPerson;
import com.sip.dmes.entitys.ScPersonObservations;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author user
 */
public class ScPersonObservationsDao implements IScPersonObservations {

    private final static Logger log = Logger.getLogger(ScPersonObservations.class);
    @PersistenceContext()
    EntityManager entityManager;

    @Override
    @Transactional
    public void addScPersonObservations(ScPersonObservations ScPersonObservations) {

        try {
            entityManager.persist(ScPersonObservations);

        } catch (Exception e) {
            log.error("Error guardar la observasion", e.getCause());
        }

    }

    @Override
    @Transactional
    public void updateScPersonObservations(ScPersonObservations ScPersonObservations) {

        try {
            entityManager.merge(ScPersonObservations);

        } catch (Exception e) {
            log.error("Error actualizar la observasion", e.getCause());
        }

    }

    @Override
    @Transactional
    public void deleteScPersonObservations(ScPersonObservations ScPersonObservations) {

        try {
            entityManager.remove(ScPersonObservations);

        } catch (Exception e) {
            log.error("Error al borrar la observasion", e.getCause());
        }

    }

    @Override
    @Transactional
    public ScPersonObservations getScPersonObservationsById(int id) {
        ScPersonObservations result = null;
        try {

            Query query = entityManager.createNamedQuery("ScPersonObservations.findByIdPersonObservations");
            query.setParameter("idPersonObservations", id);
            result = (ScPersonObservations) query.getSingleResult();

        } catch (Exception e) {

            log.error("Error intentando buscar la observasion", e.getCause());
        }

        return result;
    }

    @Override
    @Transactional
    public List<ScPersonObservations> getScPersonObservations() {

        List<ScPersonObservations> result = null;
        try {

            Query query = entityManager.createNamedQuery("ScPersonObservations.findAll");
            result =  query.getResultList();

        } catch (Exception e) {

            log.error("Error consulta todas las observasion", e.getCause());
        }

        return result;
    }

    @Override
    public List<ScPersonObservations> getScPersonObservationsForPerson(ScPerson scPerson) {

        List<ScPersonObservations> result = null;
        try {

            Query query = entityManager.createNamedQuery("ScPersonObservations.findByIdPersonObservations");
            query.setParameter("idPersonObservations", scPerson);
            result = query.getResultList();

        } catch (Exception e) {

            log.error("Error consulta toda la observaciones de una observasion", e);
        }

        return result;
    }

}
