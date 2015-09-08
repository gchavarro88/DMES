/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bo;


import com.sip.dmes.entitys.ScClassType;
import com.sip.dmes.entitys.ScPartsAndConsumables;
import com.sip.dmes.entitys.ScType;
import java.util.List;

public interface IScPartsAndConsumables 
{
    
    public List<ScPartsAndConsumables> getAllPartsAndConsumables()throws Exception;
    
    public ScPartsAndConsumables findByIdPartsAndConsumables(ScPartsAndConsumables scPartsAndConsumables)throws Exception;
    
    public ScPartsAndConsumables findByPartsAndConsumables(ScPartsAndConsumables scPartsAndConsumables)throws Exception;
    
    public List<ScPartsAndConsumables> findByIdType(ScType scType)throws Exception;
    
    public List<ScPartsAndConsumables> findByIdClassType(ScClassType scClassType)throws Exception;
    
    public void createPartsAndConsumables(ScPartsAndConsumables scPartsAndConsumables)throws Exception;
    
    public void updatePartsAndConsumables(ScPartsAndConsumables scPartsAndConsumables)throws Exception;
    
    public void deletePartsAndConsumableslById(ScPartsAndConsumables scPartsAndConsumables)throws Exception;

}
