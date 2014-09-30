/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.dao.bo.IScSubModulePermissionByRole;
import com.sip.dmes.entitys.ScModulePermission;
import com.sip.dmes.entitys.ScModulePermissionByRole;
import com.sip.dmes.entitys.ScRoles;
import com.sip.dmes.entitys.ScSubmodulePermission;
import com.sip.dmes.entitys.ScSubmodulePermissionByRole;
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
@Repository("IScSubModulePermissionByRole")
public class ScSubmodulePermissionByRoleDao implements IScSubModulePermissionByRole
{
    @PersistenceContext
    EntityManager entityManager;
    private final static Logger log = Logger.getLogger(ScSubmodulePermissionByRoleDao.class);

    @Override
    public List<ScSubmodulePermissionByRole> getAllIScModulePermissionsByRole(ScRoles scRole, ScModulePermission scModulePermission) throws Exception
    {
        List<ScSubmodulePermissionByRole> result = null;
        try
        {
            Query query = entityManager.createNamedQuery("ScSubmodulePermissionByRole.findByRoleAndScModulePermission");
            query.setParameter("scRoles", scRole);
            query.setParameter("idModulePermission", scModulePermission);
            result = (List<ScSubmodulePermissionByRole>) query.getResultList();
        }
        catch(Exception e)
        {
            log.error("Error intentando obtener los submodulos por rol "+scRole.getName()+" y modulo "+scModulePermission.getName() , e);
        }
        return result;
    }
    
}
