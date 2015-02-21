/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bo;

import com.sip.dmes.entitys.ScInput;
import java.util.List;

/**
 *
 * @author user
 */
public interface IScInput 
{
    public List<ScInput> getAllInputs() throws Exception;
    
    public void saveInput(ScInput input) throws Exception;
  
    public void deleteInput(ScInput input) throws Exception;
     
    public void updateInput(ScInput input) throws Exception;
    
}
