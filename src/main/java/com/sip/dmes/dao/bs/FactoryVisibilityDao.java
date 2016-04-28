/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.beans.template.TemplateBean;
import com.sip.dmes.dao.bo.IFactoryVisibility;
import com.sip.dmes.dao.bo.IScUsers;
import com.sip.dmes.entitys.ScFactoryLocation;
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
@Repository(value = "IFactoryVisibility")
public class FactoryVisibilityDao implements IFactoryVisibility
{
    private final static Logger log = Logger.getLogger(FactoryVisibilityDao.class);
    @PersistenceContext()
    EntityManager entityManager;

    @Override
    public List<ScFactoryLocation> getFactoryLocations()
    {
        List<ScFactoryLocation> result = null;
        try
        {
            Query query = entityManager.createNamedQuery("ScFactoryLocation.findAll");
            result = (List<ScFactoryLocation>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error intentando consultar las localizaciones de la fábrica ",e);
        }
        return result;
    }

    
    
}
