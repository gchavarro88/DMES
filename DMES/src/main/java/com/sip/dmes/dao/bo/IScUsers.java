/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bo;

import com.sip.dmes.entitys.ScUsers;

/**
 *
 * @author gchavarro88
 */
public interface IScUsers
{
    public ScUsers findByLogin(String login) throws Exception; 
       
}
