/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bo;

import com.sip.dmes.entitys.ScCostCenter;
import com.sip.dmes.entitys.ScInput;
import com.sip.dmes.entitys.ScInputLocation;
import com.sip.dmes.entitys.ScPackingUnit;
import com.sip.dmes.entitys.ScPartner;
import com.sip.dmes.entitys.ScPriority;
import com.sip.dmes.entitys.ScStore;
import java.util.List;

/**
 *
 * @author user
 */
public interface IScInput 
{
    public List<ScInput> getAllInputs() throws Exception;
    
    public void saveInput(ScInput input) throws Exception;
  
    public void deleteInput(ScInput input) throws Exception;
     
    public void updateInput(ScInput input) throws Exception;
    
    public List<ScPartner> getAllPartners() throws Exception;
    
    public List<ScCostCenter > getAllCostCenter() throws Exception;
    
    public List<ScPackingUnit > getAllPackingUnits() throws Exception;
    
    public List<ScInputLocation > getAllInputLocations() throws Exception;
    
    public List<ScStore> getAllStores() throws Exception;
    
    public List<ScPriority> getAllPrioritys() throws Exception;
    
    public void saveCostCenter(ScCostCenter costCenter) throws Exception;
    
    public void savePackingUnit(ScPackingUnit packingUnit) throws Exception;
    
    public void saveLocationInput(ScInputLocation inputLocation) throws Exception;

}
