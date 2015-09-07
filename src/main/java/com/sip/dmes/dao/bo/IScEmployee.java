/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bo;

import com.sip.dmes.entitys.ScEmployee;
import com.sip.dmes.entitys.ScTurn;
import java.util.List;

public interface IScEmployee 
{
    public void createEmployee(ScEmployee employee)throws Exception;
    
    public void updateEmployee(ScEmployee employee)throws Exception;
    
    public void deleteteEmployeeById(ScEmployee employee)throws Exception;
    
    public List<ScEmployee> getAllEmployees()throws Exception;

    public List<ScTurn> getAllTurns() throws Exception;
}