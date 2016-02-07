/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Machines | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bo;

import com.sip.dmes.entitys.ScCostCenter;
import com.sip.dmes.entitys.ScFactoryLocation;
import com.sip.dmes.entitys.ScLocation;
import com.sip.dmes.entitys.ScMachine;
import com.sip.dmes.entitys.ScMeasureUnit;
import com.sip.dmes.entitys.ScMoney;
import com.sip.dmes.entitys.ScPartner;
import com.sip.dmes.entitys.ScPriority;
import com.sip.dmes.entitys.ScStore;
import com.sip.dmes.entitys.ScTime;
import java.util.List;

public interface IScMachine 
{
    
    public List<ScMachine> getAllMachine()throws Exception;
    
    public Object getInitialParameters() throws Exception;
    
    public void deleteMachine(ScMachine machine) throws Exception;

    public void saveCostCenter(ScCostCenter costCenter) throws Exception;
    
    public void saveMachine(ScMachine machine) throws Exception;
    
    public void updateMachine(ScMachine machine) throws Exception;
    
    public void saveFactoryLocation(ScFactoryLocation factoryLocation) throws Exception;
    
    public void saveMeasureUnit(ScMeasureUnit measureUnit) throws Exception;
    
    public List<ScPartner> getAllPartners() throws Exception;
    
    public List<ScCostCenter > getAllCostCenter() throws Exception;
    
    public List<ScTime> getAllTimes() throws Exception;
    
    public List<ScMeasureUnit> getAllMeasureUnits() throws Exception;
    
    public List<ScMoney> getAllMoneys() throws Exception;
    
    public List<ScPriority> getAllPrioritys() throws Exception;
    
    public List<ScFactoryLocation> getAllFactoryLocations() throws Exception;
    
    public List<Object[]> getCapacityByIdMachine(Long idMachine) throws Exception;
}
