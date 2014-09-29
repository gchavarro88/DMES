/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.beans.template.TemplateBean;
import com.sip.dmes.dao.bo.IScUsers;
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
            result = ((List<ScUsers>) query.getResultList()).get(0);
        }
        catch(Exception e)
        {
            log.error("Error intentando consultar el usuario", e.getCause());
            e.printStackTrace();
        }
        return result;
    }
    
}
