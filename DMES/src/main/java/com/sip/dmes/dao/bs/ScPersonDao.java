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
public class ScPersonDao implements IScPerson
{

    private final static Logger log = Logger.getLogger(ScPerson.class);
    @PersistenceContext()
    EntityManager entityManager;

    @Override
    @Transactional
    public void addScPerson(ScPerson ScPerson)
    {
        try
        {
            entityManager.persist(ScPerson);
        }
        catch (Exception e)
        {
            log.error("Error al intentar guardar una persona", e);
        }

    }

    @Override
    @Transactional
    public void updateScPerson(ScPerson ScPerson)
    {
        try
        {
            entityManager.merge(ScPerson);
        }
        catch (Exception e)
        {
            log.error("Error intentar actualizar una persona", e);
        }

    }

    @Override
    @Transactional
    public void deleteScPerson(ScPerson ScPerson)
    {
        try
        {
            entityManager.remove(ScPerson);
        }
        catch (Exception e)
        {
            log.error("Error al intentar eliminar una persona", e);
        }
    }

    @Override
    @Transactional
    public ScPerson getScPersonById(long idPerson)
    {
        ScPerson result = null;
        try
        {
            Query query = entityManager.createNamedQuery("ScPerson.findByIdPerson");
            query.setParameter("idPerson", idPerson);
            result = (ScPerson) query.getSingleResult();
        }
        catch (Exception e)
        {
            log.error("Error intentando consultar la persona por edad", e);
        }
        return result;
    }

    @Override
    @Transactional
    public List<ScPerson> getScPersons()
    {
        List<ScPerson> result = null;
        try
        {
            Query query = entityManager.createNamedQuery("ScPerson.findAll");
            result =  query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error consulta todas las personas", e);
        }

        return result;
    }

}
