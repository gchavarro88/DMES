/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.dao.bo.IScPerson;
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
 * @author user
 */
@Repository(value = "IScPerson")
public class ScPersonDao implements IScPerson {

    private final static Logger log = Logger.getLogger(ScPerson.class);
    @PersistenceContext()
    EntityManager entityManager;

    @Override
    @Transactional
    public void addScPerson(ScPerson ScPerson) {

        try {
            entityManager.persist(ScPerson);

        } catch (Exception e) {
            log.error("Error guardar la persona", e.getCause());
        }

    }

    @Override
    @Transactional
    public void updateScPerson(ScPerson ScPerson) {

        try {
            entityManager.merge(ScPerson);

        } catch (Exception e) {
            log.error("Error actualizar la persona", e.getCause());
        }

    }

    @Override
    @Transactional
    public void deleteScPerson(ScPerson ScPerson) {

        try {
            entityManager.remove(ScPerson);

        } catch (Exception e) {
            
            log.error("Error al borrar la persona ", e);
        }

    }

    @Override
    @Transactional
    public ScPerson getScPersonById(int id) {
        ScPerson result = null;
        try {

            Query query = entityManager.createNamedQuery("ScPerson.findByIdPerson");
            result = (ScPerson) query.getSingleResult();

        } catch (Exception e) {

            log.error("Error intentando buscar la persona",e);
        }

        return result;
    }

    @Override
    @Transactional
    public List<ScPerson> getScPersons() {
        System.err.println("Estot en query");
        List<ScPerson> result = null;
        try {

            Query query = entityManager.createNamedQuery("ScPerson.findAll");
            result =  query.getResultList();
            
            System.err.println("Numero de personas :"+ result.size());

        } catch (Exception e) {

            log.error("Error consulta todas las personas", e);
        }

        return result;
    }

}
