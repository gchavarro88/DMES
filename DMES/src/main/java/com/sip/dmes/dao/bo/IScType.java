/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bo;


import com.sip.dmes.entitys.ScClassType;
import com.sip.dmes.entitys.ScType;
import java.util.List;

public interface IScType 
{
    
    public List<ScType> getAllTypes()throws Exception;
    
    public ScType findByIdType(ScType scType)throws Exception;
    
    public ScType findByType(ScType scType)throws Exception;
    
    public List<ScType> findByIdClassType(ScClassType ScClassType)throws Exception;
    
    public void createType(ScType scType)throws Exception;
    
    public void updateType(ScType scType)throws Exception;
    
    public void deleteTypeById(ScType scType)throws Exception;

}
