/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.beans.security;

import com.sip.dmes.beans.SessionBean;
import com.sip.dmes.dao.bo.IScPerson;
import com.sip.dmes.dao.bo.IScRoles;
import com.sip.dmes.dao.bo.IScUsers;
import com.sip.dmes.entitys.ScPerson;
import com.sip.dmes.entitys.ScRoles;
import com.sip.dmes.entitys.ScUsers;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private List<ScPerson> personsList;
    private List<ScUsers> usersList;
    private IScUsers scUsersServer;
    private IScPerson scPersonServer;
    
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
        fillListRoles();
        fillListPersonsAvailables();
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
    
    
    public void fillListPersonsAvailables()
    {
        try
        {
            if(getPersonsList() == null)
            {
                setPersonsList(getScPersonServer().findPersonWithOutUser());
            }
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar las personas que no cuentan con un usuario",e);
        }
    }
    
    public void fillListRoles()
    {
        try
        {
            if(getRolesList() == null)
            {
                setRolesList(getScRolesServer().getAllRoles());
            }
        }
        catch (Exception e)
        {
        }
    }
    
    public String getFormatDate(Date date)
    {
        String result = "";
        String patron = "dd-MM-yyyy";
        result = getFormatDateGlobal(patron, date);
        return result;
    }
    
    /**
     * Método que se encarga de recibir un patrón y una fecha de tipo Date, y
     * deberá retornar una cadena de carácteres de la fecha siguiendo el patrón
     * recibido
     * <p>
     * @param pattern patrón del formato de la fecha
     * @param date fecha a visualizar
     * <p>
     * @return valor de la fecha en el formato indicado por el patrón de tipo
     * String
     * <p>
     * @author: Gustavo Adolfo Chavarro Ortiz
     */
    public String getFormatDateGlobal(String pattern, Date date)
    {
        String result = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        if (date != null)
        {
            result = simpleDateFormat.format(date);
        }
        return result;
    } 
    
    /**
     * Métodos Getters And Setters.
     */
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

    public IScPerson getScPersonServer()
    {
        return scPersonServer;
    }

    public void setScPersonServer(IScPerson scPersonServer)
    {
        this.scPersonServer = scPersonServer;
    }

    public List<ScPerson> getPersonsList()
    {
        return personsList;
    }

    public void setPersonsList(List<ScPerson> personsList)
    {
        this.personsList = personsList;
    }
    
    
    
}
