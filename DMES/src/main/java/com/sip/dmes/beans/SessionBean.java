/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.beans;

import com.sip.dmes.entitys.ScUsers;

/**
 *
 * @author gchavarro88
 */
public class SessionBean
{

    /**
     * Creates a new instance of SessionBean
     */
    
    private ScUsers scUser;
    
    
    
    public SessionBean()
    {
    }

    public ScUsers getScUser()
    {
        return scUser;
    }

    public void setScUser(ScUsers scUser)
    {
        this.scUser = scUser;
    }
    
    
}
