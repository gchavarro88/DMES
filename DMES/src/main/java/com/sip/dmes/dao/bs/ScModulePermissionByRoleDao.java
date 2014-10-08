/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.dao.bo.IScModulePermissionByRole;
import com.sip.dmes.entitys.ScModulePermissionByRole;
import com.sip.dmes.entitys.ScRoles;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gchavarro88
 */
@Repository("IScModulePermissionByRole")
public class ScModulePermissionByRoleDao implements IScModulePermissionByRole
{

    @PersistenceContext
    EntityManager entityManager;
    private final static Logger log = Logger.getLogger(ScModulePermissionByRoleDao.class);
    
    @Override
    public List<ScModulePermissionByRole> getAllIScModulePermissionsByRole(ScRoles scRole) throws Exception
    {
        List<ScModulePermissionByRole> result = null;
        try
        {
            Query query = entityManager.createNamedQuery("ScModulePermissionByRole.findByIdRole");
            query.setParameter("idRole", scRole);
            result = (List<ScModulePermissionByRole>) query.getResultList();
        }
        catch(Exception e)
        {
            log.error("Error intentando obtener los modulos por rol "+scRole.getName(), e);
            e.printStackTrace();
        }
        return result;
    }
    
}
