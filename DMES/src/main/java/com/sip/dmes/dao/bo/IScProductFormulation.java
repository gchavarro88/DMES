/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bo;

import com.sip.dmes.entitys.ScCostCenter;
import com.sip.dmes.entitys.ScDistributionUnit;
import com.sip.dmes.entitys.ScLocation;
import com.sip.dmes.entitys.ScProductFormulation;
import com.sip.dmes.entitys.ScMeasureUnit;
import com.sip.dmes.entitys.ScMoney;
import com.sip.dmes.entitys.ScPackingUnit;
import com.sip.dmes.entitys.ScPartner;
import com.sip.dmes.entitys.ScPriority;
import com.sip.dmes.entitys.ScProductFormulation;
import com.sip.dmes.entitys.ScStore;
import java.util.List;

/**
 *
 * @author user
 */
public interface IScProductFormulation 
{
    public List<ScProductFormulation> getAllProductFormulations() throws Exception;
    
    public ScProductFormulation getProductsById(Long idProduct) throws Exception;
    
    public void saveProductFormulation(ScProductFormulation productFormulation) throws Exception;
  
    public void deleteProductFormulation(ScProductFormulation productFormulation) throws Exception;
     
    public void updateProductFormulation(ScProductFormulation productFormulation) throws Exception;
    
    public List<ScPartner> getAllPartners() throws Exception;
    
    public List<ScCostCenter > getAllCostCenter() throws Exception;
    
    public List<ScPackingUnit > getAllPackingUnits() throws Exception;
    
    public List<ScDistributionUnit > getAllDistributionUnits() throws Exception;
    
    public List<ScMeasureUnit> getAllMeasureUnits() throws Exception;
    
    public List<ScLocation> getAllInputLocations(ScStore store) throws Exception;
    
    public List<ScMoney> getAllMoneys() throws Exception;
    
    public List<ScStore> getAllStores() throws Exception;
    
    public List<ScPriority> getAllPrioritys() throws Exception;
    
    public void saveCostCenter(ScCostCenter costCenter) throws Exception;
    
    public void savePackingUnit(ScPackingUnit packingUnit) throws Exception;
    
    public void saveDistributionUnit(ScDistributionUnit distributionUnit) throws Exception;
    
    public void saveLocation(ScLocation location) throws Exception;

    public void saveMeasureUnit(ScMeasureUnit measureUnit) throws Exception;
    
    public Object getInitialParameters() throws Exception;
}
