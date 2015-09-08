/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Machines | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bo;

import com.sip.dmes.entitys.ScMachine;
import java.util.List;

public interface IScMachine 
{
    
    public List<ScMachine> getAllMachine()throws Exception;
    
    public Object getInitialParameters() throws Exception;

}
