/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bo;

import com.sip.dmes.entitys.ScEmployee;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author user
 */
public interface IScEmployee {
    
    
     EntityManagerFactory emf = Persistence.createEntityManagerFactory("DMES_PersistenceUnit");

      /**
     * Add ScEmployee
     *
     * @param  ScEmployee ScEmployee
     */
    public void addScEmployee(ScEmployee ScEmployee);
 
    /**
     * Update ScEmployee
     *
     * @param  ScEmployee ScEmployee
     */
    public void updateScEmployee(ScEmployee ScEmployee);
 
    /**
     * Delete ScEmployee
     *
     * @param  ScEmployee ScEmployee
     */
    public void deleteScEmployee(ScEmployee ScEmployee);
 
    /**
     * Get ScEmployee
     *
     * @param  int ScEmployee Id
     */
    public ScEmployee getScEmployeeById(int id);
 
    /**
     * Get ScEmployee List
     *
     */
    public List<ScEmployee> getScEmployees();
    
}
