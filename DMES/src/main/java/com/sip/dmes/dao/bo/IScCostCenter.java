/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bo;



import com.sip.dmes.entitys.ScCostCenter;
import java.util.List;

public interface IScCostCenter 
{
    
    public List<ScCostCenter> getAllCostCenter()throws Exception;
    
    public ScCostCenter findByIdCostCenter(ScCostCenter scCostCenter)throws Exception;
    
    public ScCostCenter findByCostCenter(ScCostCenter scCostCenter)throws Exception;
    
    public ScCostCenter findByNit(ScCostCenter scCostCenter)throws Exception;
    
    public void createCostCenter(ScCostCenter scCostCenter)throws Exception;
    
    public void updateCostCenter(ScCostCenter scCostCenter)throws Exception;
    
    public void deleteCostCenterById(ScCostCenter scCostCenter)throws Exception;

}
