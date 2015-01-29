package com.sip.dmes.beans.loadFiles;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.sip.dmes.beans.SessionBean;
import com.sip.dmes.dao.bo.IFsDocuments;
import com.sip.dmes.dao.bo.IScModulePermission;
import com.sip.dmes.dao.bo.IScModulePermissionByRole;
import com.sip.dmes.dao.bo.IScRoles;
import com.sip.dmes.dao.bo.IScUsers;
import com.sip.dmes.entitys.ScDocuments;
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
import org.primefaces.model.UploadedFile;

/**
 *
 * @author gchavarro88
 */
public class FsdocumentsByUserBean
{

    private final static Logger log = Logger.getLogger(FsdocumentsByUserBean.class); //Variable de logger que permite guardar registro de la aplicación
    private SessionBean sessionBean; //Bean de sesion
    private IFsDocuments fsDocumentsServer; //Bean para acceder al DAO de los documentos
    
    private List<ScDocuments> documentList;//Lista de documentos 
    private ScDocuments scDocumentsAdd;//Documento a Agregar
    private ScDocuments scDocumentsSelected; //Documento seleccionado
    
    
    
    //Constantes
    private UploadedFile upLoadFile; //Objeto que permite traer un archivo que se copiará
    private int MAX_SIZE_FILE = 5;//Tamaño en megas del archivo
    private String EXTENSION_FILE = "txt,docx,xml,doc,xls,xlsx,pdf,ppt,pptx,pps,ppsx,gif,jpeg,jpg,png";
    
    
    
    
    /**
     * Creates a new instance of ScrolesBean
     */
    public FsdocumentsByUserBean()
    {
        
    } 
    /**
     * Método encargado de invocar los procedimientos iniciales para la primera vista 
     * de la página.
     * @author Gustavo Chavarro Ortiz
     */
    @PostConstruct
    public void initData()
    {
        fillAllDocumentsByUser();
    }
    
    /**
     * Método encargado de llenar la tabla inicial de los documentos cargados por el usuario.
     * @author Gustavo Chavarro Ortiz
     */
    public void fillAllDocumentsByUser()
    {
        try
        {
            if(getDocumentList() == null)
            {
                setDocumentList(getFsDocumentsServer().getAllDocumentsByUser(getSessionBean().getScUser()));
            }
        }
        catch(Exception e)
        {
            log.error("Error al intentar consultar los documentos para el usuario "+getSessionBean().getScUser(), e);
        }
    }
    
    /**
     * Método encargado de limpiar todas las variables temporales.
     * @author Gustavo Chavarro Ortiz
     */
    public void cleanFieldSave()
    {
        setScDocumentsAdd(new ScDocuments());
    }
    
    
//    
//    public void fillAllModulesPermission()
//    {
//        try
//        {
//            if(getListAllPermissions()== null)
//            {
//                setListAllPermissions(getScModulePermissionServer().findAllModulesPermissionByType(TYPE_PERMISSION));
//                setListGlobalPermissions(getScModulePermissionServer().findAllModulesPermission());
//                setMapsModulesPermission(new HashMap<Long, ScModulePermission>());
//                for(ScModulePermission modulePermission: getListGlobalPermissions())
//                {
//                    mapsModulesPermission.put(modulePermission.getIdModulePermission(), modulePermission);
//                }
//            }
//        }
//        catch(Exception e)
//        {
//            log.error("Error al intentar consultar los permisos de cada módulos", e);
//        }
//    }
//    
//    public void fillMapRoles()
//    {
//        setMapRoles(new HashMap<String, ScRoles>());
//        for(ScRoles roles: getRolesList())
//        {
//            getMapRoles().put(roles.getName(), roles);
//        }
//    }
//    public void cleanFieldsSave()
//    {
//        setScRolesAdd(new ScRoles());
//        setListPermissionsAdd(new ArrayList<ScModulePermission>());
//    }
//    
//    public void saveRole()
//    {
//        try
//        {
//            if(getScRolesAdd() != null)
//            {
//                getScRolesAdd().setName(getScRolesAdd().getName().trim());
//                getScRolesAdd().setName(getScRolesAdd().getName().toUpperCase());
//                if(getScRolesAdd().getName() != null && getScRolesAdd().getName().length() > 0)
//                {
//                    if((!getMapRoles().containsKey(getScRolesAdd().getName())))
//                    {
//                        if(getListPermissionsAdd() != null && getListPermissionsAdd().size() > 0)
//                        {
//                            getScRolesAdd().setCreationDate(new Date());
//                            getScRolesServer().createRole(getScRolesAdd());
//                            getRolesList().add(getScRolesAdd());
//                            for(ScModulePermission modulePermission: getListPermissionsAdd())
//                            {
//
//                                ScModulePermissionByRole modulePermissionByRole = new ScModulePermissionByRole();
//                                modulePermissionByRole.setIdModulePermission(modulePermission);
//                                modulePermissionByRole.setIdRole(getScRolesAdd());
//                                modulePermissionByRole.setIdType("CRUD");
//                                insertFathersModulesPermission(modulePermission, getScRolesAdd());
//                                getScModulePermissionByRoleServer().createModulePermissionByRole(modulePermissionByRole);
//
//                            }
//                            addInfo(null, DMESConstants.MESSAGE_TITTLE_SUCCES, DMESConstants.MESSAGE_SUCCES);
//                            fillMapRoles();
//                            cleanFieldsSave();
//                        }
//                        else
//                        {
//                            addError(null, "Error de permisos por grupo", "Debe seleccionar al menos un permiso para crear el rol");
//                        }
//                    }
//                    else
//                    {
//                        addError(null, "Error nombre de grupo", "El nombre de grupo o rol ya existe");
//                    }
//                }
//                else
//                {
//                    addError(null, "Error del nombre del grupo", "Debe ingresar un nombre válido para el grupo o rol");
//                }
//            }
//        }
//        catch (Exception e)
//        {
//            log.error("Error intentando guardar un nuevo rol", e);
//            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
//        }
//    
//    }
//    
//    public void updateRole()
//    {
//        try
//        {
//            if(getScRolesSelected()!= null)
//            {
//                getScRolesSelected().setName(getScRolesSelected().getName().trim());
//                getScRolesSelected().setName(getScRolesSelected().getName().toUpperCase());
//                if(getScRolesSelected().getName() != null && getScRolesSelected().getName().length() > 0)
//                {
//                    
//                    if(getListPermissionsUpdate()!= null && getListPermissionsUpdate().size() > 0)
//                    {
//
//                        getScModulePermissionByRoleServer().deleteModulePermissionByRole(getScRolesSelected());
//                        getScRolesSelected().setModifyDate(new Date());
//                        getScRolesServer().updateRole(getScRolesSelected());
//                        for(ScModulePermission modulePermission: getListPermissionsUpdate())
//                        {
//
//                            ScModulePermissionByRole modulePermissionByRole = new ScModulePermissionByRole();
//                            modulePermissionByRole.setIdModulePermission(modulePermission);
//                            modulePermissionByRole.setIdRole(getScRolesSelected());
//                            modulePermissionByRole.setIdType("CRUD");
//                            insertFathersModulesPermission(modulePermission, getScRolesSelected());
//                            getScModulePermissionByRoleServer().createModulePermissionByRole(modulePermissionByRole);
//
//                        }
//                        fillMapRoles();
//                        addInfo(null, DMESConstants.MESSAGE_TITTLE_SUCCES, DMESConstants.MESSAGE_SUCCES);
//                    }
//                    else
//                    {
//                        addError(null, "Error de permisos por grupo", "Debe seleccionar al menos un permiso para actualizar el rol");
//                    }
//                }
//                else
//                {
//                    addError(null, "Error del nombre del grupo", "Debe ingresar un nombre válido para el grupo o rol");
//                }
//                
//            }
//        }
//        catch (Exception e)
//        {
//            log.error("Error intentando guardar un nuevo rol", e);
//            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
//        }
//        setScRolesSelected(new ScRoles());
//        setListPermissionsUpdate(new ArrayList<ScModulePermission>());
//        setRolesList(null);
//        fillAllRoles();
//    }
//
//    public void insertFathersModulesPermission(ScModulePermission modulePermission, ScRoles scRole)
//    {
//        try
//        {
//            if(modulePermission != null)
//            {
//                if(getMapsModulesPermission().containsKey(modulePermission.getFather()))
//                {
//                    ScModulePermission modulePermissionFather = getMapsModulesPermission().get(modulePermission.getFather());
//                    ScModulePermissionByRole modulePermissionByRoleFather = new ScModulePermissionByRole();
//                    modulePermissionByRoleFather.setIdModulePermission(modulePermissionFather);
//                    modulePermissionByRoleFather.setIdRole(scRole);
//                    modulePermissionByRoleFather.setIdType("CRUD");
//                    
//                    if(getMapsModulesPermission().containsKey(modulePermissionFather.getFather()))
//                    {
//                        insertFathersModulesPermission(modulePermissionFather, scRole);
//                    }
//                    getScModulePermissionByRoleServer().createModulePermissionByRole(modulePermissionByRoleFather);
//                }
//            }
//        }
//        catch (Exception e)
//        {
//            log.error("Error intentando insertar un modulo de permiso padre",e);
//        }
//    }
//    
//    
//    
//    public void getModulePermissionsByRoleSelected(ScRoles roleSelected)
//    {
//        try
//        {
//            if(roleSelected != null)
//            {
//                setScRolesSelected(roleSelected);
//                setListPermissionsUpdate(new ArrayList<ScModulePermission>());
//                for(ScModulePermissionByRole modulePermissionByRole: getScModulePermissionByRoleServer()
//                        .getAllIScModulePermissionsByRole(roleSelected))
//                {
//                    getListPermissionsUpdate().add(modulePermissionByRole.getIdModulePermission());
//                }
//            }
//        }
//        catch(Exception e)
//        {
//            log.error("Error intentando consultar los permisos de modulo por rol",e);
//        }
//    }
//    
//    public void getModulePermissionsByRoleSelectedView()
//    {
//        try
//        {
//            if(getScRolesSelected() != null)
//            {
//                setScRolesSelected(getScRolesSelected());
//                setListPermissionsUpdate(new ArrayList<ScModulePermission>());
//                for(ScModulePermissionByRole modulePermissionByRole: getScModulePermissionByRoleServer()
//                        .getAllIScModulePermissionsByRole(getScRolesSelected()))
//                {
//                    getListPermissionsUpdate().add(modulePermissionByRole.getIdModulePermission());
//                }
//            }
//        }
//        catch(Exception e)
//        {
//            log.error("Error intentando consultar los permisos de modulo por rol",e);
//        }
//    }
//    
//    public void deleteRole()
//    {
//        try
//        {
//            if(getScRolesSelected()!= null)
//            {
//                getScModulePermissionByRoleServer().deleteModulePermissionByRole(getScRolesSelected());
//                getScUsersServer().deleteUsersByRole(getScRolesSelected());
//                getScRolesServer().deleteteRoleById(getScRolesSelected());
//                for(ScRoles roles: getRolesList())
//                {
//                    if(roles.getIdRole() == getScRolesSelected().getIdRole())
//                    {
//                        getRolesList().remove(roles);
//                        break;
//                    }
//                }
//                
//                fillMapRoles();
//                addInfo(null, DMESConstants.MESSAGE_TITTLE_SUCCES, DMESConstants.MESSAGE_SUCCES);
//            }
//        }
//        catch (Exception e)
//        {
//            log.error("Error intentando eliminar un rol", e);
//            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
//        }
//    }
    
    /**
     * Método encargado de subir el archivo y copiarlo al servidor, para posteriormente
     * dejar un registro en la base de datos.
     * @param option permite decidir si se hará un copiado sencillo o especial
     * @author: Gustavo Adolfo Chavarro Ortiz
     * @throws java.io.IOException
     */
    public void uploadFile() throws Exception
    {
//       long MegabytesChangeToBytes = ((1024)*(1024));  
//       if(getUpLoadFile() != null)
//       {
//           if(getUpLoadFile().getSize() <= (MegabytesChangeToBytes*MAX_SIZE_FILE))
//           {
//               int indexExtension = (getUpLoadFile().getFileName().indexOf(".")+1);
//               String extension = getUpLoadFile().getFileName().substring(indexExtension, getUpLoadFile().getFileName().length());
//               if(EXTENSION_FILE.contains(extension))
//               {
//                   String systemOperating = System.getProperty("os.name");
//                   String fileSeparator = System.getProperty("file.separator");
//                   String path ="";
//                   String fileNameFolder = getSessionBean().getScUser().getIdPerson().getLastName()+
//                           "_"+getSessionBean().getScUser().getIdPerson().getFirstName();
//                   if(!fileSeparator.equals("/"))
//                   {
//                       fileSeparator += fileSeparator;
//                   }
//                   path = System.getProperty("user.home")+fileSeparator+fileNameFolder;
//                   File folder = new File(path);
//                   folder.mkdirs();
//                   Date dateFile =  new Date();
//                   fileNameFolder = getUpLoadFile().getFileName()+"_"+getFormatDateGlobal("yyyyMMddHHmmss", dateFile)+"."+extension;
//                   File file = new File(path+fileSeparator+fileNameFolder);
//                   if(writeFile(getUpLoadFile().getInputstream(), file))
//                   {
//                       getPersonDocumentationAttachedAdd().setCreationDate(dateFile);
//                       getPersonDocumentationAttachedAdd().setPath(path+fileSeparator+fileNameFolder);
//                       getPersonDocumentationAttachedAdd().setIdPerson(getPersonAdd());
//                       getPersonDocumentationAttachedsList().add(getPersonDocumentationAttachedAdd());
//                       addInfo(null, "Cargue de Archivos", "Se cargó el archivo con éxito");
//                       setPersonDocumentationAttachedAdd(new ScPersonDocumentationAttached());
//                       
//                   }
//                   else
//                   {
//                       addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
//                       log.error("Error al intentar escribir el archivo");
//                   }
//               }
//               else
//               {
//                   addError(null, "Error al subir un archivo", "La extensión no coincide con las extensiones permitidas: "+EXTENSION_FILE);
//                   log.error("Error al intentar subir un archivo, extensión no incluida en la lista de permitidas");
//               }
//           }
//           else
//           {
//               addError(null, "Error al subir un archivo", "El tamaño sobrepasa el límite puesto de "+MAX_SIZE_FILE+" MB");
//               log.error("Error al intentar subir un archivo, tamaño excedido");
//           }
//       }
//       else
//       {
//           addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
//           log.error("Error al intentar subir un archivo");
//       }
//       RequestContext.getCurrentInstance().execute("PF('dialogPersonSave').show()");
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

    public SessionBean getSessionBean()
    {
        return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean)
    {
        this.sessionBean = sessionBean;
    }

    public List<ScDocuments> getDocumentList()
    {
        return documentList;
    }

    public void setDocumentList(List<ScDocuments> documentList)
    {
        this.documentList = documentList;
    }

    public ScDocuments getScDocumentsAdd()
    {
        return scDocumentsAdd;
    }

    public void setScDocumentsAdd(ScDocuments scDocumentsAdd)
    {
        this.scDocumentsAdd = scDocumentsAdd;
    }

    public ScDocuments getScDocumentsSelected()
    {
        return scDocumentsSelected;
    }

    public void setScDocumentsSelected(ScDocuments scDocumentsSelected)
    {
        this.scDocumentsSelected = scDocumentsSelected;
    }
    /**
     * Métodos Getters And Setters.
     */
    
    
    public UploadedFile getUpLoadFile()
    {
        return upLoadFile;
    }

    public void setUpLoadFile(UploadedFile upLoadFile)
    {
        this.upLoadFile = upLoadFile;
    }

    public int getMAX_SIZE_FILE()
    {
        return MAX_SIZE_FILE;
    }

    public void setMAX_SIZE_FILE(int MAX_SIZE_FILE)
    {
        this.MAX_SIZE_FILE = MAX_SIZE_FILE;
    }

    public String getEXTENSION_FILE()
    {
        return EXTENSION_FILE;
    }

    public void setEXTENSION_FILE(String EXTENSION_FILE)
    {
        this.EXTENSION_FILE = EXTENSION_FILE;
    }

    public IFsDocuments getFsDocumentsServer()
    {
        return fsDocumentsServer;
    }

    public void setFsDocumentsServer(IFsDocuments fsDocumentsServer)
    {
        this.fsDocumentsServer = fsDocumentsServer;
    }
    
    
}
