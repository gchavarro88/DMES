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
import com.sip.dmes.dao.bo.IScUsers;
import com.sip.dmes.entitys.ScModulePermission;
import com.sip.dmes.entitys.ScModulePermissionByRole;
import com.sip.dmes.entitys.ScRoles;
import com.sip.dmes.utilities.DMESConstants;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.log4j.Logger;

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
    private List<ScModulePermission> listGlobalPermissions;
    private ScRoles scRolesAdd;
    private ScRoles scRolesSelected;
    private IScModulePermissionByRole scModulePermissionByRoleServer; //Interfaz de la persistencia de permisos de modulo por rol
    private IScUsers scUsersServer;
    private final String TYPE_PERMISSION = "Item";
    private HashMap<Long, ScModulePermission> mapsModulesPermission;
    private List<ScModulePermission> listPermissionsUpdate;
    private HashMap<String, ScRoles> mapRoles;
    
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
                fillMapRoles();
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
                setListAllPermissions(getScModulePermissionServer().findAllModulesPermissionByType(TYPE_PERMISSION));
                setListGlobalPermissions(getScModulePermissionServer().findAllModulesPermission());
                setMapsModulesPermission(new HashMap<Long, ScModulePermission>());
                for(ScModulePermission modulePermission: getListGlobalPermissions())
                {
                    mapsModulesPermission.put(modulePermission.getIdModulePermission(), modulePermission);
                }
            }
        }
        catch(Exception e)
        {
            log.error("Error al intentar consultar los permisos de cada módulos", e);
        }
    }
    
    public void fillMapRoles()
    {
        setMapRoles(new HashMap<String, ScRoles>());
        for(ScRoles roles: getRolesList())
        {
            getMapRoles().put(roles.getName(), roles);
        }
    }
    public void cleanFieldsSave()
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
                getScRolesAdd().setName(getScRolesAdd().getName().trim());
                getScRolesAdd().setName(getScRolesAdd().getName().toUpperCase());
                if(getScRolesAdd().getName() != null && getScRolesAdd().getName().length() > 0)
                {
                    if((!getMapRoles().containsKey(getScRolesAdd().getName())))
                    {
                        if(getListPermissionsAdd() != null && getListPermissionsAdd().size() > 0)
                        {
                            getScRolesAdd().setCreationDate(new Date());
                            getScRolesServer().createRole(getScRolesAdd());
                            getRolesList().add(getScRolesAdd());
                            for(ScModulePermission modulePermission: getListPermissionsAdd())
                            {

                                ScModulePermissionByRole modulePermissionByRole = new ScModulePermissionByRole();
                                modulePermissionByRole.setIdModulePermission(modulePermission);
                                modulePermissionByRole.setIdRole(getScRolesAdd());
                                modulePermissionByRole.setIdType("CRUD");
                                insertFathersModulesPermission(modulePermission, getScRolesAdd());
                                getScModulePermissionByRoleServer().createModulePermissionByRole(modulePermissionByRole);

                            }
                            addInfo(null, DMESConstants.MESSAGE_TITTLE_SUCCES, DMESConstants.MESSAGE_SUCCES);
                            fillMapRoles();
                            cleanFieldsSave();
                        }
                        else
                        {
                            addError(null, "Error de permisos por grupo", "Debe seleccionar al menos un permiso para crear el rol");
                        }
                    }
                    else
                    {
                        addError(null, "Error nombre de grupo", "El nombre de grupo o rol ya existe");
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
    
    public void updateRole()
    {
        try
        {
            if(getScRolesSelected()!= null)
            {
                getScRolesSelected().setName(getScRolesSelected().getName().trim());
                getScRolesSelected().setName(getScRolesSelected().getName().toUpperCase());
                if(getScRolesSelected().getName() != null && getScRolesSelected().getName().length() > 0)
                {
                    
                    if(getListPermissionsUpdate()!= null && getListPermissionsUpdate().size() > 0)
                    {

                        getScModulePermissionByRoleServer().deleteModulePermissionByRole(getScRolesSelected());
                        getScRolesSelected().setModifyDate(new Date());
                        getScRolesServer().updateRole(getScRolesSelected());
                        for(ScModulePermission modulePermission: getListPermissionsUpdate())
                        {

                            ScModulePermissionByRole modulePermissionByRole = new ScModulePermissionByRole();
                            modulePermissionByRole.setIdModulePermission(modulePermission);
                            modulePermissionByRole.setIdRole(getScRolesSelected());
                            modulePermissionByRole.setIdType("CRUD");
                            insertFathersModulesPermission(modulePermission, getScRolesSelected());
                            getScModulePermissionByRoleServer().createModulePermissionByRole(modulePermissionByRole);

                        }
                        fillMapRoles();
                        addInfo(null, DMESConstants.MESSAGE_TITTLE_SUCCES, DMESConstants.MESSAGE_SUCCES);
                    }
                    else
                    {
                        addError(null, "Error de permisos por grupo", "Debe seleccionar al menos un permiso para actualizar el rol");
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
        setScRolesSelected(new ScRoles());
        setListPermissionsUpdate(new ArrayList<ScModulePermission>());
        setRolesList(null);
        fillAllRoles();
    }

    public void insertFathersModulesPermission(ScModulePermission modulePermission, ScRoles scRole)
    {
        try
        {
            if(modulePermission != null)
            {
                if(getMapsModulesPermission().containsKey(modulePermission.getFather()))
                {
                    ScModulePermission modulePermissionFather = getMapsModulesPermission().get(modulePermission.getFather());
                    ScModulePermissionByRole modulePermissionByRoleFather = new ScModulePermissionByRole();
                    modulePermissionByRoleFather.setIdModulePermission(modulePermissionFather);
                    modulePermissionByRoleFather.setIdRole(scRole);
                    modulePermissionByRoleFather.setIdType("CRUD");
                    
                    if(getMapsModulesPermission().containsKey(modulePermissionFather.getFather()))
                    {
                        insertFathersModulesPermission(modulePermissionFather, scRole);
                    }
                    getScModulePermissionByRoleServer().createModulePermissionByRole(modulePermissionByRoleFather);
                }
            }
        }
        catch (Exception e)
        {
            log.error("Error intentando insertar un modulo de permiso padre",e);
        }
    }
    
    
    
    public void getModulePermissionsByRoleSelected(ScRoles roleSelected)
    {
        try
        {
            if(roleSelected != null)
            {
                setScRolesSelected(roleSelected);
                setListPermissionsUpdate(new ArrayList<ScModulePermission>());
                for(ScModulePermissionByRole modulePermissionByRole: getScModulePermissionByRoleServer()
                        .getAllIScModulePermissionsByRole(roleSelected))
                {
                    getListPermissionsUpdate().add(modulePermissionByRole.getIdModulePermission());
                }
            }
        }
        catch(Exception e)
        {
            log.error("Error intentando consultar los permisos de modulo por rol",e);
        }
    }
    
    public void getModulePermissionsByRoleSelectedView()
    {
        try
        {
            if(getScRolesSelected() != null)
            {
                setScRolesSelected(getScRolesSelected());
                setListPermissionsUpdate(new ArrayList<ScModulePermission>());
                for(ScModulePermissionByRole modulePermissionByRole: getScModulePermissionByRoleServer()
                        .getAllIScModulePermissionsByRole(getScRolesSelected()))
                {
                    getListPermissionsUpdate().add(modulePermissionByRole.getIdModulePermission());
                }
            }
        }
        catch(Exception e)
        {
            log.error("Error intentando consultar los permisos de modulo por rol",e);
        }
    }
    
    public void deleteRole()
    {
        try
        {
            if(getScRolesSelected()!= null)
            {
                getScModulePermissionByRoleServer().deleteModulePermissionByRole(getScRolesSelected());
                getScUsersServer().deleteUsersByRole(getScRolesSelected());
                getScRolesServer().deleteteRoleById(getScRolesSelected());
                for(ScRoles roles: getRolesList())
                {
                    if(roles.getIdRole() == getScRolesSelected().getIdRole())
                    {
                        getRolesList().remove(roles);
                        break;
                    }
                }
                
                fillMapRoles();
                addInfo(null, DMESConstants.MESSAGE_TITTLE_SUCCES, DMESConstants.MESSAGE_SUCCES);
            }
        }
        catch (Exception e)
        {
            log.error("Error intentando eliminar un rol", e);
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
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

    public HashMap<Long, ScModulePermission> getMapsModulesPermission()
    {
        return mapsModulesPermission;
    }

    public void setMapsModulesPermission(HashMap<Long, ScModulePermission> mapsModulesPermission)
    {
        this.mapsModulesPermission = mapsModulesPermission;
    }

    public List<ScModulePermission> getListGlobalPermissions()
    {
        return listGlobalPermissions;
    }

    public void setListGlobalPermissions(List<ScModulePermission> listGlobalPermissions)
    {
        this.listGlobalPermissions = listGlobalPermissions;
    }

    public List<ScModulePermission> getListPermissionsUpdate()
    {
        return listPermissionsUpdate;
    }

    public void setListPermissionsUpdate(List<ScModulePermission> listPermissionsUpdate)
    {
        this.listPermissionsUpdate = listPermissionsUpdate;
    }

    public HashMap<String, ScRoles> getMapRoles()
    {
        return mapRoles;
    }

    public void setMapRoles(HashMap<String, ScRoles> mapRoles)
    {
        this.mapRoles = mapRoles;
    }

    public IScUsers getScUsersServer()
    {
        return scUsersServer;
    }

    public void setScUsersServer(IScUsers scUsersServer)
    {
        this.scUsersServer = scUsersServer;
    }
    
}
