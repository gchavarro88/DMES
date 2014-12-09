/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bo;


import com.sip.dmes.entitys.ScClassType;
import com.sip.dmes.entitys.ScTools;
import com.sip.dmes.entitys.ScType;
import java.util.List;

public interface IScTools 
{
    
    public List<ScTools> getAllTool()throws Exception;
    
    public ScTools findByIdTool(ScTools scTools)throws Exception;
    
    public ScTools findByTool(ScTools scTools)throws Exception;
    
    public List<ScTools> findByIdType(ScType scType)throws Exception;
    
    public List<ScTools> findByIdClassType(ScClassType scClassType)throws Exception;
    
    public void createTool(ScTools scTools)throws Exception;
    
    public void updateTool(ScTools scTools)throws Exception;
    
    public void deleteToolById(ScTools scTools)throws Exception;

}
