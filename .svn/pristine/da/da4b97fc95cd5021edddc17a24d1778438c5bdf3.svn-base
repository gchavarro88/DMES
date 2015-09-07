/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bo;

import com.sip.dmes.entitys.ScCostCenter;
import com.sip.dmes.entitys.ScMachinePart;
import com.sip.dmes.entitys.ScLocation;
import com.sip.dmes.entitys.ScMachine;

import com.sip.dmes.entitys.ScMeasureUnit;
import com.sip.dmes.entitys.ScMoney;
import com.sip.dmes.entitys.ScPartner;
import com.sip.dmes.entitys.ScPriority;
import com.sip.dmes.entitys.ScTime;
import java.util.List;

/**
 *
 * @author user
 */
public interface IScMachinePart 
{
    public List<ScMachinePart> getAllMachineParts() throws Exception;
    
    public ScMachinePart getMachinePartsById(Long idMachinePart) throws Exception;
    
    public void saveMachinePart(ScMachinePart input) throws Exception;
  
    public void deleteMachinePart(ScMachinePart input) throws Exception;
     
    public void updateMachinePart(ScMachinePart input) throws Exception;
    
    public List<ScPartner> getAllPartners() throws Exception;
    
    public List<ScCostCenter> getAllCostCenter() throws Exception;
    
//    public List<ScPackingUnit > getAllPackingUnits() throws Exception;
    
//    public List<ScDistributionUnit > getAllDistributionUnits() throws Exception;
    
    
    public List<ScMeasureUnit> getAllMeasureUnits() throws Exception;
    
    public List<ScMoney> getAllMoneys() throws Exception;
    
    public List<ScMachine> getAllMachines() throws Exception;
    
    public List<ScPriority> getAllPrioritys() throws Exception;
    
    public void saveCostCenter(ScCostCenter costCenter) throws Exception;
    
    public List<ScTime> getAllTimes() throws Exception;
    
//    public void savePackingUnit(ScPackingUnit packingUnit) throws Exception;
    
//    public void saveDistributionUnit(ScDistributionUnit distributionUnit) throws Exception;

    public void saveMeasureUnit(ScMeasureUnit measureUnit) throws Exception;
    
    public Object getInitialParameters() throws Exception;
}
