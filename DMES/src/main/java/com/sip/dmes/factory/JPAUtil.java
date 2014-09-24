/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.log4j.Logger;

/**
 *
 * @author gchavarro88
 */
public class JPAUtil
{

    private static Logger log = Logger.getLogger(JPAUtil.class);
    private static EntityManagerFactory emf;

    static
    {
        try
        {
            emf = Persistence
                    .createEntityManagerFactory("DMES_PersistenceUnit");
        }
        catch (Exception e)
        {
            log.error("Error al intentar obtener un entity manager", e.getCause());
        }
        
        EntityManager em = createEntityManager();
    }

    public static EntityManager createEntityManager()
    {
        return emf.createEntityManager();
    }

}
