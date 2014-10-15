/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.beans.template.TemplateBean;
import com.sip.dmes.dao.bo.IScUsers;
import com.sip.dmes.entitys.ScRoles;
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
 * @author gchavarro88
 */
@Repository(value = "IScUsers")
public class ScUsersDao implements IScUsers
{
    private final static Logger log = Logger.getLogger(ScUsersDao.class);
    @PersistenceContext()
    EntityManager entityManager;

    @Override
    @Transactional
    public ScUsers findByLogin(String login) throws Exception
    {
        ScUsers result = null;
        try
        {
            Query query  = entityManager.createNamedQuery("ScUsers.findByLogin");
            query.setParameter("login", login);
            result = (ScUsers) query.getSingleResult();
        }
        catch(Exception e)
        {
            log.error("Error intentando consultar el usuario", e.getCause());
        }
        return result;
    }

    @Override
    @Transactional
    public void createUser(ScUsers scUsers) throws Exception
    {
        try
        {
            entityManager.persist(scUsers);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar crear un usuario ",e );
        }
    }

    @Override
    @Transactional
    public void deleteUser(ScUsers scUsers) throws Exception
    {
        try
        {
            entityManager.remove(scUsers);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar eliminar un usuario ",e );
        }
    }

    @Override
    @Transactional
    public void deleteUsersByRole(ScRoles scRoles) throws Exception
    {
        int rowsResult = 0;
        try
        {
            Query query = entityManager.createNamedQuery("ScUsers.deleteByRole");
            query.setParameter("idRole", scRoles);
            rowsResult = query.executeUpdate();
        }
        catch (Exception e)
        {
            log.error("Error al intentar eliminar los usuarios de un determinado rol",e);
        }
    }

    @Override
    @Transactional
    public void updateUser(ScUsers scUsers) throws Exception
    {
        try
        {
            entityManager.merge(scUsers);
            entityManager.flush();
        }
        catch (Exception e)
        {
            log.error("Error al intentar actualizar un usuario ",e );
        }
    }
    @Override
    @Transactional
    public List<ScUsers> findAll() throws Exception
    {
        List<ScUsers> result = null;
        try
        {
            Query query = entityManager.createNamedQuery("ScUsers.findAll");
            result = query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar todos los usuarios", e);
        }
        return result;
    }
    
}
