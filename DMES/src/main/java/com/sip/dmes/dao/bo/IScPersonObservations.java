/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bo;

import com.sip.dmes.entitys.ScPerson;
import com.sip.dmes.entitys.ScPersonObservations;
import java.util.List;

/**
 *
 * @author user
 */
public interface IScPersonObservations {

    /**
     * Add ScPersonObservations
     *
     * @param ScPersonObservations ScPersonObservations
     */
    public void addScPersonObservations(ScPersonObservations ScPersonObservations);

    /**
     * Update ScPersonObservations
     *
     * @param ScPersonObservations ScPersonObservations
     */
    public void updateScPersonObservations(ScPersonObservations ScPersonObservations);

    /**
     * Delete ScPersonObservations
     *
     * @param ScPersonObservations ScPersonObservations
     */
    public void deleteScPersonObservations(ScPersonObservations ScPersonObservations);

    /**
     * Get ScPersonObservations
     *
     * @param int ScPersonObservations Id
     */
    public ScPersonObservations getScPersonObservationsById(int id);

    /**
     * Get ScPersonObservations List
     *
     */
    public List<ScPersonObservations> getScPersonObservations();

    public List<ScPersonObservations> getScPersonObservationsForPerson(ScPerson scPerson);
}
