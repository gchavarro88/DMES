/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.factory.dao;

import java.io.Serializable;
import java.util.*;
import java.util.Map.*;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import org.apache.log4j.Logger;

public abstract class GenericDao<T> implements Serializable
{

    private static final long serialVersionUID = 1L;

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("DMES_PersistenceUnit");
    private EntityManager em;
    
    private Class<T> entityClass;

    public void beginTransaction()
    {
        em = emf.createEntityManager();

        em.getTransaction().begin();
    }

    public void commit()
    {
        em.getTransaction().commit();
    }

    public void rollback()
    {
        em.getTransaction().rollback();
    }

    public void closeTransaction()
    {
        em.close();
    }

    public void commitAndCloseTransaction()
    {
        commit();
        closeTransaction();
    }

    public void flush()
    {
        em.flush();
    }

    public void joinTransaction()
    {
        em = emf.createEntityManager();
        em.joinTransaction();
    }

    public GenericDao(Class<T> entityClass)
    {
        this.entityClass = entityClass;
    }

    public void save(T entity)
    {
        em.persist(entity);
    }

    protected void delete(Object id, Class<T> classe)
    {
        T entityToBeRemoved = em.getReference(classe, id);

        em.remove(entityToBeRemoved);
    }

    public T update(T entity)
    {
        return em.merge(entity);
    }

    public T find(int entityID)
    {
        return em.find(entityClass, entityID);
    }

    public T findReferenceOnly(int entityID)
    {
        return em.getReference(entityClass, entityID);
    }

    // Using the unchecked because JPA does not have a
    // em.getCriteriaBuilder().createQuery()<T> method
    @SuppressWarnings(
    {
        "unchecked", "rawtypes"
    })
    public List<T> findAll()
    {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).getResultList();
    }

    // Using the unchecked because JPA does not have a
    // query.getSingleResult()<T> method
    @SuppressWarnings("unchecked")
    protected T findOneResult(String namedQuery, Map<String, Object> parameters, Logger log)
    {
        T result = null;

        try
        {
            Query query = em.createNamedQuery(namedQuery);

            // Method that will populate parameters if they are passed not null and empty
            if (parameters != null && !parameters.isEmpty())
            {
                populateQueryParameters(query, parameters);
            }

            result = (T) query.getSingleResult();

        }
        catch (NoResultException e)
        {
            log.info("No existen registros para la clase: "+getClass());
        }
        catch (Exception e)
        {
            log.error("Error al consultar los registros para la clase: "+getClass(), e.getCause());
        }

        return result;
    }

    // Using the unchecked because JPA does not have a
    // query.getSingleResult()<T> method
    @SuppressWarnings("unchecked")
    protected List<T> findManyResult(String namedQuery, Map<String, Object> parameters, Logger log)
    {
        List<T> result = null;

        try
        {
            Query query = em.createNamedQuery(namedQuery);

            // Method that will populate parameters if they are passed not null and empty
            if (parameters != null && !parameters.isEmpty())
            {
                populateQueryParameters(query, parameters);
            }

            result = (List<T>) query.getResultList();

        }
        catch (NoResultException e)
        {
            log.info("No existen registros para la clase: "+getClass());
        }
        catch (Exception e)
        {
            log.error("Error al consultar los registros para la clase: "+getClass(), e.getCause());
        }

        return result;
    }
    private void populateQueryParameters(Query query, Map<String, Object> parameters)
    {
        for (Entry<String, Object> entry : parameters.entrySet())
        {
            query.setParameter(entry.getKey(), entry.getValue());
        }
    }
   
    
    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }
}
