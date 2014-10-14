/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.beans.security;

import com.sip.dmes.beans.SessionBean;
import com.sip.dmes.dao.bo.IScRoles;
import com.sip.dmes.dao.bo.IScUsers;
import com.sip.dmes.entitys.ScRoles;
import com.sip.dmes.entitys.ScUsers;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;

/**
 *
 * @author gchavarro88
 */
public class ScusersBean
{
    private final static Logger log = Logger.getLogger(ScusersBean.class); //Variable de logger que permite guardar registro de la aplicación
    private IScRoles scRolesServer; //Interfaz de la persistencia de roles
    private SessionBean sessionBean; //Bean de sesion
    private List<ScRoles> rolesList;
    private List<ScUsers> usersList;
    private IScUsers scUsersServer;
    
    /**
     * Creates a new instance of ScusersBean
     */
    public ScusersBean()
    {
        
    }
    
    @PostConstruct
    public void initData()
    {
        fillListUsers();
    }
    
    public void fillListUsers()
    {
        try
        {
            if(getUsersList() == null)
            {
                setUsersList(getScUsersServer().findAll());
            }
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar todos los usuarios ",e);
        }
        
    }

    public IScRoles getScRolesServer()
    {
        return scRolesServer;
    }

    public void setScRolesServer(IScRoles scRolesServer)
    {
        this.scRolesServer = scRolesServer;
    }

    public SessionBean getSessionBean()
    {
        return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean)
    {
        this.sessionBean = sessionBean;
    }

    public List<ScRoles> getRolesList()
    {
        return rolesList;
    }

    public void setRolesList(List<ScRoles> rolesList)
    {
        this.rolesList = rolesList;
    }

    public List<ScUsers> getUsersList()
    {
        return usersList;
    }

    public void setUsersList(List<ScUsers> usersList)
    {
        this.usersList = usersList;
    }

    public IScUsers getScUsersServer()
    {
        return scUsersServer;
    }

    public void setScUsersServer(IScUsers scUsersServer)
    {
        this.scUsersServer = scUsersServer;
    }
    
    
    
    /**
     * Métodos Getters And Setters.
     */
    
}
