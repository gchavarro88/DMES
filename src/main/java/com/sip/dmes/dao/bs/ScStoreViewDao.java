/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.dao.bo.IScStoreView;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 * @author Gustavo Chavarro Ortiz
 */

@Repository(value = "IScStoreView")
public class ScStoreViewDao implements IScStoreView
{
    private final static Logger log = Logger.getLogger(ScStoreViewDao.class);
    @PersistenceContext()
    EntityManager entityManager;

    
    @Override
    public List<Object[]> getItemsByStoreRequisition(String namedQuery) throws Exception
    {
        List<Object[]> result = null;
        Query query  = entityManager.createNativeQuery(namedQuery); 
        try
        {
            result = (List<Object[]>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de los items de orden del almacén",e);
        }
        return result;
    }
    
    @Override
    public Object[] getItemByStoreRequisition(String namedQuery) throws Exception
    {
        Object[] result = null;
        Query query  = entityManager.createNativeQuery(namedQuery); 
        try
        {
            result = (Object[]) query.getSingleResult();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia del item de orden del almacén",e);
            throw e;
        }
        return result;
    }

    
    @Override
    public List<String> getItemsForAutocomplete(String nameQuery) throws Exception
    {
        List<Object[]> result = null;
        List<String> mixed = new ArrayList<>();
        try
        {
            Query query = entityManager.createNativeQuery(nameQuery);
            result = (List<Object[]>) query.getResultList();
            for(Object[] object: result)
            {
                String temp = object[0]+" - "+object[1];
                mixed.add(temp);
            }
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar los elementos a agregar", e);
        }
        return mixed;
    }
    
}
