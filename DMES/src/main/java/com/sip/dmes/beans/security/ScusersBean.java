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
import com.sip.dmes.entitys.ScModulePermission;
import com.sip.dmes.entitys.ScModulePermissionByRole;
import com.sip.dmes.entitys.ScPerson;
import com.sip.dmes.entitys.ScRoles;
import com.sip.dmes.entitys.ScUsers;
import com.sip.dmes.utilities.DMESConstants;
import com.sip.dmes.utilities.Utilities;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.log4j.Logger;
import org.primefaces.event.FlowEvent;

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
    private List<ScPerson> personsListUpdate;
    private List<ScUsers> usersList;
    private ScUsers userAdd;
    private ScUsers userUpdate;
    private ScUsers userSelected;
    private ScPerson personSelectedAdd;
    private ScRoles roleSelectedAdd;
    private ScRoles roleSelectedUpdate;
    private IScUsers scUsersServer;
    private IScPerson scPersonServer;
    private String password2;
    private String updatePassword2;
    private String lastUser;
    private String lastPassword;
    private final String TAB_PERSONS = "tabPerson";
    private final String TAB_USERS = "tabUser";
    private final String TAB_ROLES = "tabRoles";
    private final String TAB_CONFIRM = "tabAccept";
    private final String TAB_PERSONS_UPDATE = "tabPersonUpdate";
    private final String TAB_USERS_UPDATE = "tabUserUpdate";
    private final String TAB_ROLES_UPDATE = "tabRolesUpdate";
    private final String TAB_CONFIRM_UPDATE = "tabAcceptUpdate";
    
    
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
        setPersonSelectedAdd(new ScPerson());
        setRoleSelectedAdd(new ScRoles());
        setUserAdd(new ScUsers());
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
                List personsWithOutUser = getScPersonServer().findPersonWithOutUser();
                if(personsWithOutUser != null)
                {
                    setPersonsList(new ArrayList<ScPerson>());
                    for (Object object : personsWithOutUser)
                    {
                        getPersonsList().add(ObjectToScPerson(object));
                    }
                }
            }
        }
        catch (Exception e)
        { 
            log.error("Error al intentar consultar las personas que no cuentan con un usuario",e);
        }
    }
    
    
    public ScPerson ObjectToScPerson(Object object)
    {
        Object[] objectList = ((Object[]) object);
        ScPerson newPerson = new ScPerson();
        newPerson.setIdPerson(Long.parseLong(objectList[0].toString()));
        newPerson.setFirstName(objectList[1].toString());
        newPerson.setLastName(objectList[2].toString());
        return newPerson;
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
    
    public String onFlowProcessSaveUser(FlowEvent event) 
    {    
        if(event.getOldStep().equals(TAB_USERS))
        {
            
            if(!Utilities.isAlphaNumeric(getUserAdd().getPassword()))
            {
                addError(null, "Error al crear el usuario", "El password no debe contener carácteres especiales");
                return TAB_USERS;
            }
            else if(getUserAdd().getPassword().length() < 4)
            {
                addError(null, "Error al crear el usuario", "El password debe tener un mínimo de (4) cuatro carácteres ");
                return TAB_USERS;
            }
            for(ScUsers users: getUsersList())
            {
                if(getUserAdd().getLogin().equalsIgnoreCase(users.getLogin()))
                {
                    addError(null, "Error al crear el usuario", "El nombre de usuario ya existe!");
                    return TAB_USERS;
                }
            }
        }
        else if(event.getOldStep().equals(TAB_PERSONS))
        {
            if(getPersonSelectedAdd() == null || getPersonSelectedAdd().getIdPerson() == null)
            {
                addError(null, "Error al crear el usuario", "Debe seleccionar una persona");
                return TAB_PERSONS;
            }
        }
        else if(event.getOldStep().equals(TAB_ROLES))
        {
            if(getRoleSelectedAdd() == null || getRoleSelectedAdd().getIdRole() == null)
            {
                addError(null, "Error al crear el usuario", "Debe seleccionar un grupo o rol");
                return TAB_ROLES; 
            }
        }
        
        
            return event.getNewStep(); 
    }
     
    public String onFlowProcessUpdateUser(FlowEvent event) 
    {   
        if(event.getOldStep().equals(TAB_USERS_UPDATE))
        {
            if(getUserUpdate().getPassword() != null && getUserUpdate().getPassword().length() > 0)
            {
                
                if(!Utilities.isAlphaNumeric(getUserUpdate().getPassword()))
                {
                    addError(null, "Error al actualizar el usuario", "El password no debe contener carácteres especiales");
                    return TAB_USERS_UPDATE;
                }
                else if(getUserUpdate().getPassword() != null && getUserUpdate().getPassword().length() < 4)
                {
                    addError(null, "Error al actualizar el usuario", "El password debe tener un mínimo de (4) cuatro carácteres ");
                    return TAB_USERS_UPDATE;
                }
            }
            else
            {
                getUserUpdate().setPassword(lastPassword);
            }
            for(ScUsers users: getUsersList())
            {
                if(getUserUpdate().getLogin().equalsIgnoreCase(users.getLogin()) &&
                        !getUserUpdate().getLogin().equalsIgnoreCase(getLastUser()))
                {
                    addError(null, "Error al actualizar el usuario", "El nombre de usuario ya existe!");
                    return TAB_USERS_UPDATE;
                }
            }
        }
        else if(event.getOldStep().equals(TAB_PERSONS_UPDATE))
        {
            if(getUserUpdate() == null || getUserUpdate().getIdPerson() == null)
            {
                addError(null, "Error al crear el usuario", "Debe seleccionar una persona");
                return TAB_PERSONS_UPDATE;
            }
        }
        else if(event.getOldStep().equals(TAB_ROLES_UPDATE))
        {
            if(getUserUpdate() == null || getUserUpdate().getIdRole() == null)
            {
                addError(null, "Error al crear el usuario", "Debe seleccionar un grupo o rol");
                return TAB_ROLES_UPDATE;
            }
        }
        return event.getNewStep(); 
    }
    
    public void cleanFields()
    {
        setPersonSelectedAdd(new ScPerson());
        setRoleSelectedAdd(new ScRoles());
        setUserAdd(new ScUsers());
    }
    public void saveUser()
    {
        log.info("Se iniciará la creación de un nuevo usuario");
        try
        {
            if(getUserAdd() != null)
            {
                getUserAdd().setCreationDate(new Date());
                getUserAdd().setIdPerson(getPersonSelectedAdd());
                getUserAdd().setIdRole(getRoleSelectedAdd());
                getUserAdd().setPassword(Utilities.encriptaEnMD5(getUserAdd().getPassword()));
                getScUsersServer().createUser(getUserAdd());
                addInfo(null, DMESConstants.MESSAGE_TITTLE_SUCCES, DMESConstants.MESSAGE_SUCCES);
                getUsersList().add(getUserAdd());
                cleanFields();
                log.info("Se guardo el usuario con total exito");
            }
        }catch(Exception e) 
        {
            log.error("Error al crear el usuario",e);
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
        }
          
    
    } 
    
    
    public void updateUser()
    {
        log.info("Se iniciará la actualización de un usuario");
        try
        {
            if(getUserUpdate()!= null)
            {   
                getUserUpdate().setModifyDate(new Date());
                if(!getUserUpdate().getPassword().equals(lastPassword))
                {
                    getUserUpdate().setPassword(Utilities.encriptaEnMD5(getUserUpdate().getPassword()));
                }
                getScUsersServer().updateUser(getUserUpdate());
                addInfo(null, DMESConstants.MESSAGE_TITTLE_SUCCES, DMESConstants.MESSAGE_SUCCES);
                log.info("Se actualizó el usuario con total exito");
            }
        }catch(Exception e) 
        {
            log.error("Error al actualizar el usuario",e);
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
        }
    }
    
    public void getUserByDataTableUpdate(ScUsers userSelected)
    {
        try
        {
            if(userSelected != null)
            {
                setUserUpdate(userSelected);
                setUpdatePassword2(userSelected.getPassword());
                setLastUser(userSelected.getLogin());
                setLastPassword(userSelected.getPassword());
                if(getPersonsList() != null)
                {   
                    setPersonsListUpdate(new ArrayList<ScPerson>());
                    for(ScPerson person: getPersonsList())
                    {
                        getPersonsListUpdate().add(person);
                    }
                    getPersonsListUpdate().add(userSelected.getIdPerson());
                }
            }
            
        }
        catch (Exception e)
        {
            log.error("Error intentando asignar el usuario seleccionado para operaciones de CRUD",e);
        }
    }
    
    public void getUserByDataTable(ScUsers userSelected)
    {
        try
        {
            if(userSelected != null)
            {
                setUserSelected(userSelected);
            }
        }
        catch(Exception e)
        {
            log.error("Error intentando asignar el usuario seleccionado para operaciones de CRUD",e);
        }
    }
    
    public void deleteUser()
    {
        try
        {
            if(getUserSelected()!= null)
            {
                getScUsersServer().deleteUserById(getUserSelected());
                addInfo(null, DMESConstants.MESSAGE_TITTLE_SUCCES, DMESConstants.MESSAGE_SUCCES);
                for(ScUsers users: getUsersList())
                {
                    if(users.getIdUser() == getUserSelected().getIdUser())
                    {
                        getUsersList().remove(getUserSelected());
                        break;
                    }
                }
                setPersonsList(null);
                fillListPersonsAvailables();
            }
        }
        catch (Exception e)
        {
            log.error("Error intentando eliminar un rol", e);
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
        }
    }
    
    
    public String getCurrentDate()
    {
        
        String result = "";
        String patron = "dd-MM-yyyy HH:mm:ss";
        result = getFormatDateGlobal(patron, new Date());
        return result;
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

    public ScUsers getUserAdd()
    {
        return userAdd;
    }

    public void setUserAdd(ScUsers userAdd)
    {
        this.userAdd = userAdd;
    }

    public ScUsers getUserUpdate()
    {
        return userUpdate;
    }

    public void setUserUpdate(ScUsers userUpdate)
    {
        this.userUpdate = userUpdate;
    }

    public String getPassword2()
    {
        return password2;
    }

    public void setPassword2(String password2)
    {
        this.password2 = password2;
    }

    public ScPerson getPersonSelectedAdd()
    {
        return personSelectedAdd;
    }

    public void setPersonSelectedAdd(ScPerson personSelectedAdd)
    {
        this.personSelectedAdd = personSelectedAdd;
    }

    public ScRoles getRoleSelectedAdd()
    {
        return roleSelectedAdd;
    }

    public void setRoleSelectedAdd(ScRoles roleSelectedAdd)
    {
        this.roleSelectedAdd = roleSelectedAdd;
    }

    public ScRoles getRoleSelectedUpdate()
    {
        return roleSelectedUpdate;
    }

    public void setRoleSelectedUpdate(ScRoles roleSelectedUpdate)
    {
        this.roleSelectedUpdate = roleSelectedUpdate;
    }

    public ScUsers getUserSelected() {
        return userSelected;
    }

    public void setUserSelected(ScUsers userSelected) {
        this.userSelected = userSelected;
    }

    public String getUpdatePassword2()
    {
        return updatePassword2;
    }

    public void setUpdatePassword2(String updatePassword2)
    {
        this.updatePassword2 = updatePassword2;
    }

    public List<ScPerson> getPersonsListUpdate()
    {
        return personsListUpdate;
    }

    public void setPersonsListUpdate(List<ScPerson> personsListUpdate)
    {
        this.personsListUpdate = personsListUpdate;
    }

    public String getLastUser() {
        return lastUser;
    }

    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }

    public String getLastPassword() {
        return lastPassword;
    }

    public void setLastPassword(String lastPassword) {
        this.lastPassword = lastPassword;
    }
    
    
    
}
