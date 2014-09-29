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
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("DMES_PersistenceUnit");

    public static EntityManagerFactory getEntityManagerFactory()
    {

        return emf;

    }

    public static EntityManager createEntity()
    {
        EntityManager em = getEntityManagerFactory().createEntityManager();

        return em;
    }

}
