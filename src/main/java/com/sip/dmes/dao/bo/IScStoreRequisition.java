/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bo;


import com.sip.dmes.entitys.ScStoreRequisition;
import com.sip.dmes.entitys.ScStoreRequisitionState;
import java.util.Date;
import java.util.List;

public interface IScStoreRequisition 
{
    
    public List<ScStoreRequisition> getAllStoreRequisitions() throws Exception;
    
    public List<ScStoreRequisition> getStoreRequisitionsByStatus(List<Long> storeOrdrerStatus) throws Exception;
    
    public List<ScStoreRequisitionState> getAllStoreRequisitionState() throws Exception;
    
    public List<ScStoreRequisition> getStoreRequisitionsByParameters(Date initDate, Date endDate, String filterRequisitionType,
            String filterRequisitionClass, String filterRequisitionState, String filterRequisitionRequired) throws Exception;
    
    public void setStoreRequisition(ScStoreRequisition storeRequisition) throws Exception;
    
    public List<Object[]> getItemsByStoreRequisition(String namedQuery) throws Exception;
    
    public void executeUpdate(String update) throws Exception;
}
