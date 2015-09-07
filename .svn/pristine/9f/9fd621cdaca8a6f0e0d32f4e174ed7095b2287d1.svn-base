/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.dao.bo.IScModulePermission;
import com.sip.dmes.entitys.ScModulePermission;
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
@Repository("IScModulePermission")
public class ScModulePermissionDao implements IScModulePermission
{

    @PersistenceContext
    EntityManager entityManager;
    private final static Logger log = Logger.getLogger(ScModulePermissionDao.class);
    
    @Override
    @Transactional
    public List<ScModulePermission> findAllModulesPermissionByType(String type) throws Exception
    {
        List<ScModulePermission> result = null;
        try
        {
            Query query = entityManager.createNamedQuery("ScModulePermission.findByType");
            query.setParameter("type", type);
            result = query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar todos los permisos por módulo de cada tipo");
        }
        return result;
    }


    @Override
    public List<ScModulePermission> findAllModulesPermission() throws Exception
    {
        List<ScModulePermission> result = null;
        try
        {
            Query query = entityManager.createNamedQuery("ScModulePermission.findAll");
            result = query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar todos los permisos por módulo");
        }
        return result;
    }
    
    
}
