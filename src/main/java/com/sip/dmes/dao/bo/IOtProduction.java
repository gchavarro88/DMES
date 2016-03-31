/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bo;

import com.sip.dmes.entitys.OtLogProduction;
import com.sip.dmes.entitys.OtProductionOrder;
import com.sip.dmes.entitys.ScProductFormulation;
import com.sip.dmes.entitys.ScProductionState;
import java.util.Date;
import java.util.List;


public interface IOtProduction 
{
    
    public List<ScProductionState> getListStates()throws Exception;
    
    public OtProductionOrder getProductionOrderById(Long idProductionOrder)throws Exception;
    
    public List<OtProductionOrder> getListProductionOrders()throws Exception;
    
    public List<ScProductFormulation> getListProductFormulations()throws Exception;
    
    public List<OtProductionOrder> getProductionByParameters(Date initDate, 
            Date endDate, String orderNumber, ScProductionState state) throws Exception;
    
    public void saveOrderProduction(OtProductionOrder productionOrder)throws Exception;
    
    public void updateOrderProduction(OtProductionOrder productionOrder)throws Exception;
    
    public void deleteOrderProduction(OtProductionOrder productionOrder)throws Exception;
    
    public List<OtLogProduction> getListLogProduction()throws Exception;
    
}
