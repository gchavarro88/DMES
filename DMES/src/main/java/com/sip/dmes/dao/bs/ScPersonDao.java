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
    public void createScPerson(ScPerson ScPerson)
    {
        try
        {
            entityManager.persist(ScPerson);
        }
        catch (Exception e)
        {
            log.error("Error al intentar crear un Tercero", e);
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
            log.error("Error al intentar actualizar un Tercero", e);
        }
    }

    @Override
    @Transactional
    public void deleteScPerson(ScPerson ScPerson)
    {
        int rowsDelete = 0;
        try
        {
            Query query = entityManager.createNamedQuery("ScMails.deleteByPerson");
            query.setParameter("idPerson", ScPerson);
            rowsDelete = query.executeUpdate();
            query = entityManager.createNamedQuery("ScPhones.deleteByPerson");
            query.setParameter("idPerson", ScPerson);
            rowsDelete = query.executeUpdate();
            query = entityManager.createNamedQuery("ScPersonDocumentationAttached.deleteByPerson");
            query.setParameter("idPerson", ScPerson);
            rowsDelete = query.executeUpdate();
            query = entityManager.createNamedQuery("ScPersonObservations.deleteByPerson");
            query.setParameter("idPerson", ScPerson);
            rowsDelete = query.executeUpdate();
            query = entityManager.createNamedQuery("ScPersonSpecifications.deleteByPerson");
            query.setParameter("idPerson", ScPerson);
            rowsDelete = query.executeUpdate();
            query = entityManager.createNamedQuery("ScPerson.deleteByIdPerson");
            query.setParameter("idPerson", ScPerson.getIdPerson());
            rowsDelete = query.executeUpdate();
        }
        catch (Exception e)
        {
            log.error("Error al intentar eliminar una persona", e);
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
            log.error("Error al intentar consultar un tercero por ID", e);
        }

        return result;
    }

    @Override
    @Transactional
    public List<ScPerson> getAllScPersons()
    {

        List<ScPerson> result = null;
        try
        {

            Query query = entityManager.createNamedQuery("ScPerson.findAll");
            result = query.getResultList();

        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar todos los terceros", e);
        }

        return result;
    }

    @Override
    public List<ScPerson> findPersonWithOutUser() throws Exception
    {
        List result = null;
        try
        {
            Query query = entityManager.createNativeQuery("SELECT p.* FROM dmes.SC_PERSON p WHERE p.ID_PERSON NOT IN\n"
                    + "(SELECT u.ID_PERSON FROM dmes.SC_USERS u )");
            result = query.getResultList();

        }
        catch (Exception e)
        {
            log.error("Error intentando consultar las personas que no tienen usuario ", e);
        }
        return result;
    } 

    @Override
    public List findPersonWithOutPartnerOrEmployee() throws Exception
    {
        List result = null;
        try
        {
            Query query = entityManager.createNativeQuery("SELECT p.* FROM dmes.SC_PERSON p WHERE p.ID_PERSON NOT IN\n"
                    + "(SELECT e.ID_PERSON FROM dmes.SC_EMPLOYEE e ) AND p.ID_PERSON NOT IN \n"
                    + "(SELECT a.ID_PERSON FROM dmes.SC_PARTNER a )");
            result = query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error intentando consultar las personas que no tienen asociado un empleado o un proveedor ", e);
        }
        return result;
    }
}
