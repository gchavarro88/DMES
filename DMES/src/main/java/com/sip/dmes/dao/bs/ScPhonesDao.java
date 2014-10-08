/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.dao.bo.IScPhones;
import com.sip.dmes.entitys.ScPhones;
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
public class ScPhonesDao implements IScPhones{
  
    
    private final static Logger log = Logger.getLogger(ScPhones.class);
    @PersistenceContext()
    EntityManager entityManager;

    @Override
    @Transactional
    public void addScPhones(ScPhones ScPhones) {

        try {
            entityManager.persist(ScPhones);

        } catch (Exception e) {
            log.error("Error guardar la telefono", e.getCause());
        }

    }

    @Override
    @Transactional
    public void updateScPhones(ScPhones ScPhones) {

        try {
            entityManager.merge(ScPhones);

        } catch (Exception e) {
            log.error("Error actualizar la telefono", e.getCause());
        }

    }

    @Override
    @Transactional
    public void deleteScPhones(ScPhones ScPhones) {

        try {
            entityManager.remove(ScPhones);

        } catch (Exception e) {
            log.error("Error al borrar la telefono", e.getCause());
        }

    }

    @Override
    @Transactional
    public ScPhones getScPhonesById(int id) {
        ScPhones result = null;
        try {

            Query query = entityManager.createNamedQuery("ScPhones.findByIdPerson");
            result = (ScPhones) query.getSingleResult();

        } catch (Exception e) {

            log.error("Error intentando buscar la telefono", e.getCause());
        }

        return result;
    }

    @Override
    @Transactional
    public List<ScPhones> getScPhoness() {

        List<ScPhones> result = null;
        try {

            Query query = entityManager.createNamedQuery("ScPhones.findAll");
            result = (List<ScPhones>) (ScPhones) query.getResultList();

        } catch (Exception e) {

            log.error("Error consulta todas las personas", e.getCause());
        }

        return result;
    }
}
