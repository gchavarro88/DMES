/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.dao.bo.IScModulePermissionByRole;
import com.sip.dmes.dao.bo.IScRoles;
import com.sip.dmes.entitys.ScModulePermissionByRole;
import com.sip.dmes.entitys.ScRoles;
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
@Repository("IScRoles")
public class ScRolesDao implements IScRoles
{

    @PersistenceContext
    EntityManager entityManager;
    private final static Logger log = Logger.getLogger(ScRolesDao.class);

    @Override
    @Transactional
    public List<ScRoles> getAllRoles() throws Exception
    {
        List<ScRoles> result = null;
        try
        {
            Query query  = entityManager.createNamedQuery("ScRoles.findAll");
            result = query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error intentando obtener todos los roles", e);
        }
        return result;
    }

    @Override
    public void createRole(ScRoles scRoles) throws Exception
    {
        try
        {
            entityManager.persist(scRoles);
        }
        catch (Exception e)
        {
            log.error("Error intentando crear un nuevo grupo o rol");
        }
    }

    @Override
    public void updateRole(ScRoles scRoles) throws Exception
    {
        try
        {
            entityManager.merge(scRoles);
        }
        catch (Exception e)
        {
            log.error("Error intentando actualizar un grupo o rol");
        }
    }

    @Override
    public void deleteteRole(ScRoles scRoles) throws Exception
    {
        try
        {
            entityManager.remove(scRoles);
        }
        catch (Exception e)
        {
            log.error("Error intentando eliminar un grupo o rol");
        }
    }

   
    
    
}
