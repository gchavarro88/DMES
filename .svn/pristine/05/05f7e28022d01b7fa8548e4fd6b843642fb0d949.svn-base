/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bo;


import com.sip.dmes.entitys.ScEmployee;
import com.sip.dmes.entitys.ScPerson;
import com.sip.dmes.entitys.ScStoreOrder;
import com.sip.dmes.entitys.ScStoreOrderState;
import java.util.Date;
import java.util.List;

public interface IScStoreOrder 
{
    
    public List<ScStoreOrder> getAllStoreOrders() throws Exception;
    
    public List<ScStoreOrder> getStoreOrdersByStatus(List<Long> storeOrdrerStatus) throws Exception;
    
    public List<ScStoreOrder> getStoreOrdersByStatusAndOrderType(List<Long> storeOrdrerStatus, String typeOrder) throws Exception;
    
    public List<ScStoreOrderState> getAllStoreOrderState() throws Exception;
    
    public List<ScStoreOrder> getStoreOrdersByParameters(Date initDate, Date endDate, String filterOrderType,
            String filterOrderClass, String filterOrderState, String filterOrderRequired) throws Exception;
    
    public void setStoreOrder(ScStoreOrder storeOrder) throws Exception;
    
    public List<Object[]> getItemsByStoreOrder(String namedQuery) throws Exception;
    
    public void executeUpdate(String update) throws Exception;
    
    public ScEmployee getEmployeeByPerson(ScPerson person)throws Exception;
    
    public List<String> getItemsForAutocomplete(String nameQuery) throws Exception; 
    
    public void saveStoreOrder(ScStoreOrder storeOrder) throws Exception;
}
