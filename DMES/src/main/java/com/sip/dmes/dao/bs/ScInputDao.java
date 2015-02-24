/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.dao.bo.IScInput;
import com.sip.dmes.entitys.ScInput;
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

@Repository("IScInput")
public class ScInputDao  implements  IScInput
{
    
    private final static Logger log = Logger.getLogger(ScInputDao.class);
    @PersistenceContext()
    EntityManager entityManager;

    @Override
    
    public List<ScInput> getAllInputs() throws Exception
    {
        List<ScInput> result = null;
        Query query  = entityManager.createNamedQuery("ScInput.findAll"); 
        try
        {
            result = (List<ScInput>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de los insumos",e);
        }
        return result;
    }

    @Override
    @Transactional
    public void saveInput(ScInput input) throws Exception
    {
        try
        {
            entityManager.persist(input);
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de los insumos",e);
        }
    }

    @Override
    @Transactional
    public void deleteInput(ScInput input) throws Exception
    {
        try
        {
            entityManager.remove(entityManager.contains(input)?input:entityManager.merge(input));
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de los insumos",e);
        }
    }

    @Override
    @Transactional
    public void updateInput(ScInput input) throws Exception
    {
        try
        {
            entityManager.merge(input);
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de los insumos",e);
        }
    }

}
