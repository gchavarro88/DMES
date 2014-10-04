/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bo;

import com.sip.dmes.entitys.ScPerson;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author user
 */
public interface IScPerson {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("DMES_PersistenceUnit");

    /**
     * Add ScPerson
     *
     * @param ScPerson ScPerson
     */
    public void addScPerson(ScPerson ScPerson);

    /**
     * Update ScPerson
     *
     * @param ScPerson ScPerson
     */
    public void updateScPerson(ScPerson ScPerson);

    /**
     * Delete ScPerson
     *
     * @param ScPerson ScPerson
     */
    public void deleteScPerson(ScPerson ScPerson);

    /**
     * Get ScPerson
     *
     * @param int ScPerson Id
     */
    public ScPerson getScPersonById(int id);

    /**
     * Get ScPerson List
     *
     */
    public List<ScPerson> getScPersons();

}
