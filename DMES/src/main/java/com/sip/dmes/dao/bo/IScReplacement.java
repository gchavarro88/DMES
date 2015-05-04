/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bo;

import com.sip.dmes.entitys.ScCostCenter;
import com.sip.dmes.entitys.ScDistributionUnit;
import com.sip.dmes.entitys.ScReplacement;
import com.sip.dmes.entitys.ScLocation;
import com.sip.dmes.entitys.ScMeasureUnit;
import com.sip.dmes.entitys.ScMoney;
import com.sip.dmes.entitys.ScPackingUnit;
import com.sip.dmes.entitys.ScPartner;
import com.sip.dmes.entitys.ScPriority;
import com.sip.dmes.entitys.ScStore;
import com.sip.dmes.entitys.ScTime;
import java.util.List;

/**
 *
 * @author user
 */
public interface IScReplacement 
{
    public List<ScReplacement> getAllReplacements() throws Exception;
    
    public ScReplacement getReplacementsById(Long idReplacement) throws Exception;
    
    public void saveReplacement(ScReplacement replacement) throws Exception;
  
    public void deleteReplacement(ScReplacement replacement) throws Exception;
     
    public void updateReplacement(ScReplacement replacement) throws Exception;
    
    public List<ScPartner> getAllPartners() throws Exception;
    
    public List<ScCostCenter > getAllCostCenter() throws Exception;
    
    public List<ScTime> getAllTimes() throws Exception;
    
    public List<ScLocation > getAllReplacementLocations(ScStore store) throws Exception;
    
    public List<ScMeasureUnit> getAllMeasureUnits() throws Exception;
    
    public List<ScMoney> getAllMoneys() throws Exception;
    
    public List<ScStore> getAllStores() throws Exception;
    
    public List<ScPriority> getAllPrioritys() throws Exception;
    
    public void saveCostCenter(ScCostCenter costCenter) throws Exception;
    
    public void saveLocationReplacement(ScLocation replacementLocation) throws Exception;

    public void saveMeasureUnit(ScMeasureUnit measureUnit) throws Exception;
    
    public Object getInitialParameters() throws Exception;
}
