/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bo;

import com.sip.dmes.entitys.ScRoles;
import com.sip.dmes.entitys.ScUsers;
import java.util.List;

/**
 *
 * @author gchavarro88
 */
public interface IScUsers
{
    public ScUsers findByLogin(String login) throws Exception; 
    
    public void createUser(ScUsers scUsers)throws Exception; 
    
    public void deleteUser(ScUsers scUsers)throws Exception; 
    
    public void deleteUserById(ScUsers scUsers)throws Exception; 
    
    public void deleteUsersByRole(ScRoles scRoles)throws Exception; 
    
    public void updateUser(ScUsers scUsers)throws Exception; 
    
    public List<ScUsers> findAll()throws Exception; 
       
}
