/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bo;


import java.util.List;

public interface IScStoreView 
{ 
    
    public List<Object[]> getItemsByStoreRequisition(String namedQuery) throws Exception;
    
    public Object[] getItemByStoreRequisition(String namedQuery) throws Exception;
    
    public List<String> getItemsForAutocomplete(String nameQuery) throws Exception; 
    
    
}
