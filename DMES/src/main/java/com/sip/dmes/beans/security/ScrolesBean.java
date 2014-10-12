/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.beans.security;

import com.sip.dmes.beans.SessionBean;
import com.sip.dmes.dao.bo.IScModulePermission;
import com.sip.dmes.dao.bo.IScModulePermissionByRole;
import com.sip.dmes.dao.bo.IScRoles;
import com.sip.dmes.entitys.ScModulePermission;
import com.sip.dmes.entitys.ScModulePermissionByRole;
import com.sip.dmes.entitys.ScRoles;
import com.sip.dmes.utilities.DMESConstants;
import com.sip.dmes.utilities.Utilities;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.log4j.Logger;
import org.primefaces.model.DualListModel;

/**
 *
 * @author gchavarro88
 */
public class ScrolesBean
{

    private final static Logger log = Logger.getLogger(ScrolesBean.class); //Variable de logger que permite guardar registro de la aplicación
    private IScRoles scRolesServer; //Interfaz de la persistencia de roles
    private IScModulePermission scModulePermissionServer;
    private SessionBean sessionBean; //Bean de sesion
    private List<ScRoles> rolesList;
    private List<ScModulePermission> listPermissionsAdd;
    private List<ScModulePermission> listAllPermissions;
    private ScRoles scRolesAdd;
    private ScRoles scRolesSelected;
    private IScModulePermissionByRole scModulePermissionByRoleServer; //Interfaz de la persistencia de permisos de modulo por rol
    private final String TYPE_PERMISSION = "Item";
   
    
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
        fillAllModulesPermission();
        setScRolesAdd(new ScRoles());
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
    
    public void fillAllModulesPermission()
    {
        try
        {
            if(getListAllPermissions()== null)
            {
                setListAllPermissions(getScModulePermissionServer().findAllModulesPermission(TYPE_PERMISSION));
            }
        }
        catch(Exception e)
        {
            log.error("Error al intentar consultar los permisos de cada módulos", e);
        }
    }
    
    public void cleanFields()
    {
        setScRolesAdd(new ScRoles());
        setListPermissionsAdd(new ArrayList<ScModulePermission>());
    }
    
    public void saveRole()
    {
        try
        {
            if(getScRolesAdd() != null)
            {
                if(getScRolesAdd().getName() != null && getScRolesAdd().getName().length() > 0)
                {
                    if(getListPermissionsAdd() != null && getListPermissionsAdd().size() > 0)
                    {
                        for(ScModulePermission modulePermission: getListPermissionsAdd())
                        {
                            ScModulePermissionByRole modulePermissionByRole = new ScModulePermissionByRole();
                            modulePermissionByRole.setIdModulePermission(modulePermission);
                            modulePermissionByRole.setIdRole(getScRolesAdd());
                            modulePermissionByRole.setIdType("CRUD");
                            getScModulePermissionByRoleServer().createModulePermissionByRole(modulePermissionByRole);
                            
                        }
                        addInfo(null, DMESConstants.MESSAGE_TITTLE_SUCCES, DMESConstants.MESSAGE_SUCCES);
                        setScRolesAdd(new ScRoles());
                    }
                    else
                    {
                        addError(null, "Error de permisos por grupo", "Debe seleccionar al menos un permiso para crear el rol");
                    }
                }
                else
                {
                    addError(null, "Error del nombre del grupo", "Debe ingresar un nombre válido para el grupo o rol");
                }
            }
        }
        catch (Exception e)
        {
            log.error("Error intentando guardar un nuevo rol", e);
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
        }
    
    }

    
     
    /**
     * Método encargado de visualizar un  mensaje en la pantalla de tipo informativo
     * @param actionEvent Evento de donde es llamado
     * @param tittle Título del mensaje
     * @param message cuerpo del mensaje
     * @author: Gustavo Adolfo Chavarro Ortiz
     */
    public void addInfo(ActionEvent actionEvent, String tittle, String message)
    {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, tittle, message));
    }

    /**
     * Método encargado de visualizar un  mensaje en la pantalla de tipo Advertencia
     * @param actionEvent Evento de donde es llamado
     * @param tittle Título del mensaje
     * @param message cuerpo del mensaje
     * @author: Gustavo Adolfo Chavarro Ortiz
     */
    public void addWarn(ActionEvent actionEvent, String tittle, String message)
    {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, tittle, message));
    }

    /**
     * Método encargado de visualizar un  mensaje en la pantalla de tipo Error
     * @param actionEvent Evento de donde es llamado
     * @param tittle Título del mensaje
     * @param message cuerpo del mensaje
     * @author: Gustavo Adolfo Chavarro Ortiz
     */
    public void addError(ActionEvent actionEvent, String tittle, String message)
    {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, tittle, message));
    }

    /**
     * Método encargado de visualizar un  mensaje en la pantalla de tipo Fatal
     * @param actionEvent Evento de donde es llamado
     * @param tittle Título del mensaje
     * @param message cuerpo del mensaje
     * @author: Gustavo Adolfo Chavarro Ortiz
     */
    public void addFatal(ActionEvent actionEvent, String tittle, String message)
    {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, tittle, message));
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

    public List<ScModulePermission> getListPermissionsAdd()
    {
        return listPermissionsAdd;
    }

    public void setListPermissionsAdd(List<ScModulePermission> listPermissionsAdd)
    {
        this.listPermissionsAdd = listPermissionsAdd;
    }

    public List<ScModulePermission> getListAllPermissions()
    {
        return listAllPermissions;
    }

    public void setListAllPermissions(List<ScModulePermission> listAllPermissions)
    {
        this.listAllPermissions = listAllPermissions;
    }

    public IScModulePermission getScModulePermissionServer()
    {
        return scModulePermissionServer;
    }

    public void setScModulePermissionServer(IScModulePermission scModulePermissionServer)
    {
        this.scModulePermissionServer = scModulePermissionServer;
    }

    public ScRoles getScRolesAdd()
    {
        return scRolesAdd;
    }

    public void setScRolesAdd(ScRoles scRolesAdd)
    {
        this.scRolesAdd = scRolesAdd;
    }

    public ScRoles getScRolesSelected()
    {
        return scRolesSelected;
    }

    public void setScRolesSelected(ScRoles scRolesSelected)
    {
        this.scRolesSelected = scRolesSelected;
    }

    public IScModulePermissionByRole getScModulePermissionByRoleServer()
    {
        return scModulePermissionByRoleServer;
    }

    public void setScModulePermissionByRoleServer(IScModulePermissionByRole scModulePermissionByRoleServer)
    {
        this.scModulePermissionByRoleServer = scModulePermissionByRoleServer;
    }

    
}
