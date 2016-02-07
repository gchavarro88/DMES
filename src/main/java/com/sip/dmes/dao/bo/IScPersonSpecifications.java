/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bo;

import com.sip.dmes.entitys.ScPerson;
import com.sip.dmes.entitys.ScPersonSpecifications;
import java.util.List;

/**
 *
 * @author user
 */
public interface IScPersonSpecifications {

    /**
     * Add ScPersonSpecifications
     *
     * @param ScPersonSpecifications ScPersonSpecifications
     */
    public void addScPersonSpecifications(ScPersonSpecifications ScPersonSpecifications);

    /**
     * Update ScPersonSpecifications
     *
     * @param ScPersonSpecifications ScPersonSpecifications
     */
    public void updateScPersonSpecifications(ScPersonSpecifications ScPersonSpecifications);

    /**
     * Delete ScPersonSpecifications
     *
     * @param ScPersonSpecifications ScPersonSpecifications
     */
    public void deleteScPersonSpecifications(ScPersonSpecifications ScPersonSpecifications);

    /**
     * Get ScPersonSpecifications
     *
     * @param int ScPersonSpecifications Id
     */
    public ScPersonSpecifications getScPersonSpecificationsById(int id);

    /**
     * Get ScPersonSpecifications List
     *
     */
    public List<ScPersonSpecifications> getScPersonSpecifications();
    
     public List<ScPersonSpecifications> getScPersonSpecificationsForPerson( ScPerson scPerson);

}
