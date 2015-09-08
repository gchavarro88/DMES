/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bo;


import com.sip.dmes.entitys.ScClassType;
import java.util.List;

public interface IScClassType 
{
    public void createClassType(ScClassType scClassType)throws Exception;
    
    public void updateClassType(ScClassType scClassType)throws Exception;
    
    public void deleteClassTypeById(ScClassType scClassType)throws Exception;
    
    public List<ScClassType> getAllClassTypes()throws Exception;
    
    public ScClassType findByIdClassType(ScClassType scClassType)throws Exception;
    
    public ScClassType findByClassType(ScClassType scClassType)throws Exception;

}
