/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.beans.security;

import com.sip.dmes.beans.SessionBean;
import com.sip.dmes.dao.bo.IScRoles;
import com.sip.dmes.entitys.ScRoles;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;

/**
 *
 * @author gchavarro88
 */
public class ScrolesBean
{

    private final static Logger log = Logger.getLogger(ScrolesBean.class); //Variable de logger que permite guardar registro de la aplicación
    private IScRoles scRolesServer; //Interfaz de la persistencia de roles
    private SessionBean sessionBean; //Bean de sesion
    private List<ScRoles> rolesList;
    /**
     * Creates a new instance of ScrolesBean
     */
    public ScrolesBean()
    {
        
    } 
    @PostConstruct
    public void initData()
    {
        fillAllRoles();
    }
    
    public void fillAllRoles()
    {
        try
        {
            if(getRolesList() == null)
            {
                setRolesList(getScRolesServer().getAllRoles());
            }
        }
        catch(Exception e)
        {
            log.error("Error al intentar consultar los roles", e);
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
    
    
}
