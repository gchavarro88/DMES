/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bo;


import com.sip.dmes.entitys.ScCostCenter;
import com.sip.dmes.entitys.ScLocation;
import com.sip.dmes.entitys.ScMeasureUnit;
import com.sip.dmes.entitys.ScMoney;
import com.sip.dmes.entitys.ScPartner;
import com.sip.dmes.entitys.ScPriority;
import com.sip.dmes.entitys.ScTool;
import com.sip.dmes.entitys.ScStore;
import com.sip.dmes.entitys.ScTime;
import java.util.List;

public interface IScTool 
{
    
    public List<ScTool> getAllTools() throws Exception;
    
    public ScTool getToolsById(Long idTool) throws Exception;
    
    public void saveTool(ScTool tool) throws Exception;
  
    public void deleteTool(ScTool tool) throws Exception;
     
    public void updateTool(ScTool tool) throws Exception;
    
    public List<ScPartner> getAllPartners() throws Exception;
    
    public List<ScCostCenter > getAllCostCenter() throws Exception;
    
    public List<ScTime> getAllTimes() throws Exception;
    
    public List<ScLocation > getAllToolLocations(ScStore store) throws Exception;
    
    public List<ScMeasureUnit> getAllMeasureUnits() throws Exception;
    
    public List<ScMoney> getAllMoneys() throws Exception;
    
    public List<ScStore> getAllStores() throws Exception;
    
    public List<ScPriority> getAllPrioritys() throws Exception;
    
    public void saveCostCenter(ScCostCenter costCenter) throws Exception;
    
    public void saveLocationTool(ScLocation toolLocation) throws Exception;

    public void saveMeasureUnit(ScMeasureUnit measureUnit) throws Exception;
    
    public Object getInitialParameters() throws Exception;

}
