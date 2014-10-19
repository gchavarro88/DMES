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

    @PersistenceContext()
    EntityManager entityManager;

    private final static Logger log = Logger.getLogger(ScPerson.class);

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
            log.error("Error guardar la persona", e);
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
            log.error("Error actualizar la persona", e);
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

            log.error("Error al borrar la persona ", e);
        }

    }

    @Override
    @Transactional
    public ScPerson getScPersonById(long id)
    {
        ScPerson result = null;
        try
        {

            Query query = entityManager.createNamedQuery("ScPerson.findByIdPerson");
            query.setParameter("idPerson", id);
            result = (ScPerson) query.getSingleResult();

        }
        catch (Exception e)
        {

            log.error("Error intentando buscar la persona", e);
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
            result = query.getResultList();

        }
        catch (Exception e)
        {
        }

        return result;
    }

    @Override
    public List<ScPerson> findPersonWithOutUser() throws Exception
    {
        List<ScPerson> result = null;
        try
        {
            Query query = entityManager.createNativeQuery("SELECT p.* FROM dmes.SC_PERSON p WHERE p.ID_PERSON NOT IN\n"
                    + "(SELECT u.ID_PERSON FROM dmes.SC_USERS u )");
            result = (List<ScPerson>) query.getResultList();

        }
        catch (Exception e)
        {
            log.error("Error intentando consultar las personas que no tienen usuario ", e);
        }
        return result;
    }

}
