/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.dao.bo.IScPersonSpecifications;
import com.sip.dmes.entitys.ScPerson;
import com.sip.dmes.entitys.ScPersonSpecifications;
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
public class ScPersonSpecificationsDao implements IScPersonSpecifications {

    private final static Logger log = Logger.getLogger(ScPersonSpecifications.class);
    @PersistenceContext()
    EntityManager entityManager;

    @Override
    @Transactional
    public void addScPersonSpecifications(ScPersonSpecifications ScPersonSpecifications) {

        try {
            entityManager.persist(ScPersonSpecifications);

        } catch (Exception e) {
            log.error("Error guardar la especificaciones", e.getCause());
        }

    }

    @Override
    @Transactional
    public void updateScPersonSpecifications(ScPersonSpecifications ScPersonSpecifications) {

        try {
            entityManager.merge(ScPersonSpecifications);

        } catch (Exception e) {
            log.error("Error actualizar la especificaciones", e.getCause());
        }

    }

    @Override
    @Transactional
    public void deleteScPersonSpecifications(ScPersonSpecifications ScPersonSpecifications) {

        try {
            entityManager.remove(ScPersonSpecifications);

        } catch (Exception e) {
            log.error("Error al borrar la especificaciones", e.getCause());
        }

    }

    @Override
    @Transactional
    public ScPersonSpecifications getScPersonSpecificationsById(int id) {
        ScPersonSpecifications result = null;
        try {

            Query query = entityManager.createNamedQuery("ScPersonSpecifications.findByIdPersonSpecifications");
            query.setParameter("idPersonSpecifications", id);
            result = (ScPersonSpecifications) query.getSingleResult();

        } catch (Exception e) {

            log.error("Error intentando buscar la especificaciones", e.getCause());
        }

        return result;
    }

    @Override
    @Transactional
    public List<ScPersonSpecifications> getScPersonSpecifications() {

        List<ScPersonSpecifications> result = null;
        try {

            Query query = entityManager.createNamedQuery("ScPersonSpecifications.findAll");
            result =  query.getResultList();

        } catch (Exception e) {

            log.error("Error consulta todas las especificaciones", e.getCause());
        }

        return result;
    }

    @Override
    public List<ScPersonSpecifications> getScPersonSpecificationsForPerson(ScPerson scPerson) {

        List<ScPersonSpecifications> result = null;
        try {

            Query query = entityManager.createNamedQuery("ScPersonSpecifications.findByIdPersonSpecifications");
            query.setParameter("idPersonSpecifications", scPerson);
            result = query.getResultList();

        } catch (Exception e) {

            log.error("Error consulta toda la especificaciones de una persona", e);
        }

        return result;
    }

}
