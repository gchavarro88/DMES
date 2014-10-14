/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bo;

import com.sip.dmes.entitys.ScPerson;
import java.util.List;

/**
 *
 * @author user
 */
public interface IScPerson {

    
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
     * @param idPerson
     * @return ScPerson
     */
    public ScPerson getScPersonById(long idPerson);

    /**
     * Get ScPerson List
     *
     * @return 
     */
    public List<ScPerson> getScPersons();
    
    //Consulta de las personas que aun no tienen Usuario
    public List<ScPerson> findPersonWithOutUser() throws Exception;

}
