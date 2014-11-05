/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.beans.security;

import com.sip.dmes.beans.SessionBean;
import com.sip.dmes.dao.bo.IScMails;
import com.sip.dmes.dao.bo.IScPerson;
import com.sip.dmes.dao.bo.IScPersonDocumentationAttached;
import com.sip.dmes.dao.bo.IScPersonObservations;
import com.sip.dmes.dao.bo.IScPersonSpecifications;
import com.sip.dmes.dao.bo.IScPhones;
import com.sip.dmes.entitys.ScMails;
import com.sip.dmes.entitys.ScPerson;
import com.sip.dmes.entitys.ScPersonDocumentationAttached;
import com.sip.dmes.entitys.ScPersonObservations;
import com.sip.dmes.entitys.ScPersonSpecifications;
import com.sip.dmes.entitys.ScPhones;
import com.sip.dmes.utilities.DMESConstants;
import com.sip.dmes.utilities.Utilities;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.log4j.Logger;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author user
 */
public class ScpersonBean
{

    private final static Logger log = Logger.getLogger(ScpersonBean.class);

    // interfacest par los crud basicos..
    private IScPerson scPersonServer;
    private IScPhones scPhonesServer;
    private IScMails scMailsServer;
    private IScPersonDocumentationAttached scPersonDocumentationAttachedServer;
    private IScPersonObservations scPersonObservationsServer;
    private IScPersonSpecifications scPersonSpecificationsServer;
    SessionBean sessionBean;//Variable de sesion
    //Objetos temporales para modificar
    private List<ScPerson> scPersons;
    private List<ScPerson> scPersonsUpdate;
    private List<ScPhones> personPhoneList;
    private List<ScPersonObservations> personObservationsList;
    private List<ScPersonSpecifications> personSpecificationsList;
    private List<ScPersonDocumentationAttached> personDocumentationAttachedsList;
    private List<ScMails> personMailList;
    private List<ScPersonDocumentationAttached> personScPersonDocumentationAttachedList;
    private List<ScPersonObservations> personScPersonObservationsList;
    private List<ScPersonSpecifications> personScPersonSpecifications;
    private ScPerson personAdd;
    private ScPerson personUpdate;
    private ScPhones phoneAdd;
    private ScPerson personSelected;
    private ScMails mailAdd;
    private ScPersonObservations personObservationsAdd;
    private ScPersonSpecifications personSpecificationsAdd;
    private ScPersonDocumentationAttached personDocumentationAttachedAdd;
    private UploadedFile upLoadFile; //Objeto que permite traer un archivo que se copiará
    private int MAX_SIZE_FILE;
    private String EXTENSION_FILE;
    //Constantes
    private final String TAB_BASIC_DATA = "tabBasicDataSave";
    private final String TAB_CONFIRM_SAVE = "tabConfirmSave";
    @PostConstruct
    public void initData()
    {
 
        fillPersonsList();
        cleanValues();

    }

    /**
     * Método encargado de cargar la lista de personas para mostrarse en la
     * tabla inicial
     * <p>
     * @author: Gustavo Chavarro Ortiz
     */
    public void fillPersonsList()
    {
        try
        {
            if (getScPersons() == null)
            {
                setScPersons(scPersonServer.getAllScPersons());
            }
        }
        catch (Exception e)
        {
            log.error("Error al intentar cargar la personas en el método inicial", e);
        }
    }

    public void cleanValues()
    {
        setPhoneAdd(new ScPhones());
        setMailAdd(new ScMails());
        setPersonObservationsAdd(new ScPersonObservations());
        setPersonSpecificationsAdd(new ScPersonSpecifications());
        setPersonDocumentationAttachedAdd(new ScPersonDocumentationAttached());
        setPersonAdd(new ScPerson());
        setPersonObservationsList(new ArrayList<ScPersonObservations>());
        setPersonSpecificationsList(new ArrayList<ScPersonSpecifications>());
        setPersonDocumentationAttachedsList(new ArrayList<ScPersonDocumentationAttached>());
        setPersonPhoneList(new ArrayList<ScPhones>());
        setPersonMailList(new ArrayList<ScMails>());
        setPersonScPersonObservationsList(new ArrayList<ScPersonObservations>());
        setPersonScPersonSpecifications(new ArrayList<ScPersonSpecifications>());
    }
    public void savePerson()
    {
        try
        {
            if(getPersonAdd() != null )
            {
                if(getPersonPhoneList() != null && !getPersonPhoneList().isEmpty())
                {
                    getPersonAdd().setScPhonesList(getPersonPhoneList());
                }
                if(getPersonMailList() != null && !getPersonMailList().isEmpty())
                {
                    getPersonAdd().setScMailsList(getPersonMailList());
                }
                if(getPersonObservationsList() != null && !getPersonObservationsList().isEmpty())
                {
                    getPersonAdd().setScPersonObservationsList(getPersonObservationsList());
                }
                if(getPersonSpecificationsList()!= null && !getPersonSpecificationsList().isEmpty())
                {
                    getPersonAdd().setScPersonSpecificationsList(getPersonSpecificationsList());
                }
                if(getPersonDocumentationAttachedsList()!= null && !getPersonDocumentationAttachedsList().isEmpty())
                {
                    getPersonAdd().setScPersonDocumentationAttachedList(getPersonDocumentationAttachedsList());
                }
                getPersonAdd().setPathPhoto("/");
                getScPersonServer().createScPerson(getPersonAdd());
                addInfo(null, DMESConstants.MESSAGE_TITTLE_SUCCES, DMESConstants.MESSAGE_SUCCES);
                getScPersons().add(getPersonAdd());
                cleanValues();
            }
            else
            {
                log.error("Error al intentar crear un tercero");
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }
        }
        catch (Exception e)
        {
            log.error("Error al intentar crear un tercero", e);
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
        }
    
    }
    
    public String onFlowProcessSavePerson(FlowEvent event)
    {
        if (event.getOldStep().equalsIgnoreCase(TAB_BASIC_DATA))
        {
            if (getPersonAdd().getLastName() == null || getPersonAdd().getLastName().length() < 1)
            {
                addError(null, "Error al crear un tercero", "Debe ingrear apellidos válidos");
                return event.getOldStep();
            }
            else if (getPersonAdd().getFirstName() == null || getPersonAdd().getFirstName().length() < 1)
            {
                addError(null, "Error al crear un tercero", "Debe ingrear nombres válidos");
                return event.getOldStep();
            }
            else if (getPersonAdd().getFirstName() == null || getPersonAdd().getFirstName().length() < 1)
            {
                addError(null, "Error al crear un tercero", "Debe ingrear nombres válidos");
                return event.getOldStep();
            }
            else if (getPersonAdd().getAge() < 18)
            {
                addError(null, "Error al crear un tercero", "Debe ingresar una edad válida");
                return event.getOldStep();
            }
            else if (getPersonAdd().getDomicilie() == null || getPersonAdd().getDomicilie().length() < 1)
            {
                addError(null, "Error al crear un tercero", "Debe ingrear una dirección válida");
                return event.getOldStep();
            }
        }
        else if(event.getNewStep().equalsIgnoreCase(TAB_CONFIRM_SAVE))
        {
            getPersonAdd().setCreationDate(new Date());
        }
        
        return event.getNewStep();
    }

    public void savePhonesByPerson()
    {
        if (getPersonPhoneList() != null)
        {
            if (getPhoneAdd() != null && getPhoneAdd().getNumberPhone() > 0)
            {
                getPhoneAdd().setIdPerson(getPersonAdd());
                getPersonPhoneList().add(getPhoneAdd());
                setPhoneAdd(new ScPhones());
            }
            else
            {
                addError(null, "Error al crear un tercero", "Debe ingresar un número telefónico válido");
                setPhoneAdd(new ScPhones());
            }
        }
    }

    
    public void saveMailsByPerson()
    {
        if (getPersonMailList() != null)
        {
            if (getMailAdd() != null && getMailAdd().getMail().length() > 0)
            {
                if (Utilities.isEmail(getMailAdd().getMail()))
                {
                    getMailAdd().setIdPerson(getPersonAdd());
                    getPersonMailList().add(getMailAdd());
                    setMailAdd(new ScMails());
                }
                else
                {
                    addError(null, "Creación de correo", "El correo no tiene el formato indicado xxxxxxxx@xxxx.xxx");
                }

            }
            else
            {
                addError(null, "Error al crear un tercero", "Debe ingresar un correo válido");
                setMailAdd(new ScMails());
            }
        }
    }
    
    public void saveSpecificationsByPerson()
    {
        if (getPersonSpecificationsList()!= null)
        {
            if (getPersonSpecificationsAdd()!= null && getPersonSpecificationsAdd().getTittle().length() > 0)
            {
                if (getPersonSpecificationsAdd().getSpecification().length() > 0)
                {
                    getPersonSpecificationsAdd().setIdPerson(getPersonAdd());
                    getPersonSpecificationsList().add(getPersonSpecificationsAdd());
                    setPersonSpecificationsAdd(new ScPersonSpecifications());
                }
                else
                {
                    addError(null, "Creación de correo", "Debe ingresar una especificación válida");
                }
            }
            else
            {
                addError(null, "Error al crear un tercero", "Debe ingresar un título de especificación válido");
                setPersonSpecificationsAdd(new ScPersonSpecifications());
            }
        }
    }

    /**
     * Método encargado de subir el archivo y copiarlo al servidor, para posteriormente
     * dejar un registro en la base de datos.
     * @param option permite decidir si se hará un copiado sencillo o especial
     * @author: Gustavo Adolfo Chavarro Ortiz
     * @throws java.io.IOException
     */
    public void uploadFile() throws Exception
    {
       long MegabytesChangeToBytes = ((1024)*(1024));
       
    }
    
    public void saveObservationsByPerson()
    {
        if (getPersonObservationsList()!= null)
        {
            if (getPersonObservationsAdd()!= null && getPersonObservationsAdd().getTittle().length() > 0)
            {
                if (getPersonObservationsAdd().getObservation().length() > 0)
                {
                    getPersonObservationsAdd().setIdPerson(getPersonAdd());
                    getPersonObservationsList().add(getPersonObservationsAdd());
                    setPersonObservationsAdd(new ScPersonObservations());
                }
                else
                {
                    addError(null, "Creación de correo", "Debe ingresar una observación válida");
                }
            }
            else
            {
                addError(null, "Error al crear un tercero", "Debe ingresar un título de observación válido");
                setPersonObservationsAdd(new ScPersonObservations());
            }
        }
    }
    
    
    public String onFlowProcessUpdatePerson(FlowEvent event)
    {
        if (event.getOldStep().equalsIgnoreCase(TAB_BASIC_DATA))
        {
            if (getPersonAdd().getLastName() == null || getPersonAdd().getLastName().length() < 1)
            {
                addError(null, "Error al crear un tercero", "Debe ingrear apellidos válidos");
                return event.getOldStep();
            }
            else if (getPersonAdd().getFirstName() == null || getPersonAdd().getFirstName().length() < 1)
            {
                addError(null, "Error al crear un tercero", "Debe ingrear nombres válidos");
                return event.getOldStep();
            }
            else if (getPersonAdd().getFirstName() == null || getPersonAdd().getFirstName().length() < 1)
            {
                addError(null, "Error al crear un tercero", "Debe ingrear nombres válidos");
                return event.getOldStep();
            }
            else if (getPersonAdd().getAge() < 18)
            {
                addError(null, "Error al crear un tercero", "Debe ingresar una edad válida");
                return event.getOldStep();
            }
            else if (getPersonAdd().getDomicilie() == null || getPersonAdd().getDomicilie().length() < 1)
            {
                addError(null, "Error al crear un tercero", "Debe ingrear una dirección válida");
                return event.getOldStep();
            }
        }
        else if(event.getNewStep().equalsIgnoreCase(TAB_CONFIRM_SAVE))
        {
            getPersonAdd().setCreationDate(new Date());
        }
        
        return event.getNewStep();
    }
    
    public void deletePerson()
    {
        if(getPersonSelected() != null)
        {
            try
            {
                getScPersonServer().deleteScPerson(getPersonSelected());
                addInfo(null, DMESConstants.MESSAGE_TITTLE_SUCCES, DMESConstants.MESSAGE_SUCCES);
                for(ScPerson personDelete: getScPersons())
                {
                    if(personDelete.getIdPerson() == getPersonSelected().getIdPerson())
                    {
                        getScPersons().remove(personDelete);
                        break;
                    }
                }
            }
            catch (Exception e)
            {
                log.info("Error intentando eliminar a una persona", e);
                addError(null, "Error al intentar eliminar un tercero", 
                        "Debe revisar si el terceron no tiene dependencias de Empleados, Usuarios, o Asociados");
            }
        }
        else
        {
            log.info("Error intentando eliminar a una persona");
            addError(null, "Error al intentar eliminar un tercero", "Debe revisar si el terceron no tiene dependencias de Empleados, Usuarios, o Asociados");
        }
    }
    
    
    public void getPersonByDataTable(ScPerson scPersonSelected)
    {
        try
        {
            if(scPersonSelected != null)
            {
                setPersonSelected(scPersonSelected);
            }
            else
            {
                log.error("Error intentando asignar el tercero seleccionado para operaciones de CRUD");
                addError(null, "Error al intentar seleccionar un tercero", "No se ha seleccionado un tercero para operaciones de crud");
            }
        }
        catch (Exception e)
        {
            log.error("Error intentando asignar el tercero seleccionado para operaciones de CRUD",e);
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
        }
    }
    
    
    
    
    
    
    
    
    
    public void onRowEditPhones(RowEditEvent event)
    {
        log.info("Se editó un teléfono de la lista para terceros a crear");
        addInfo(null, "Crear Teléfono", "Se editó satisfactoriamente el teléfono");
    }

    public void onRowCancelPhones(RowEditEvent event)
    {
        log.info("Se canceló la edición de un teléfono de la lista para terceros a crear");
        addWarn(null, "Crear Teléfono", "Se canceló la edición del teléfono");
    }
    public void onRowEditObservations(RowEditEvent event)
    {
        log.info("Se editó una observación de la lista para terceros a crear");
        addInfo(null, "Crear Observación", "Se editó satisfactoriamente la observación");
    }

    public void onRowCancelObservations(RowEditEvent event)
    {
        log.info("Se canceló la edición de una observación de la lista para terceros a crear");
        addWarn(null, "Crear Observación", "Se canceló la edición de la observación");
    }
    
    public void onRowEditSpecifications(RowEditEvent event)
    {
        log.info("Se editó una especificación de la lista para terceros a crear");
        addInfo(null, "Crear Especificación", "Se editó satisfactoriamente la especificación");
    }

    public void onRowCancelSpecifications(RowEditEvent event)
    {
        log.info("Se canceló la edición de una especificación de la lista para terceros a crear");
        addWarn(null, "Crear Especificación", "Se canceló la edición de la especificación");
    }
    
    public void onRowEditDocumentations(RowEditEvent event)
    {
        log.info("Se editó un documento de la lista para terceros a crear");
        addInfo(null, "Crear Documento", "Se editó satisfactoriamente el documento");
    }

    public void onRowCancelDocumentations(RowEditEvent event)
    {
        log.info("Se canceló la edición del documento de la lista para terceros a crear");
        addWarn(null, "Crear Documento", "Se canceló la edición del documento");
    }
    
    public void onRowEditMails(RowEditEvent event)
    {
        log.info("Se editó el correo de la lista para terceros a crear");
        addInfo(null, "Crear Correo", "Se editó satisfactoriamente el correo");
    }

    public void onRowCancelMails(RowEditEvent event)
    {
        log.info("Se canceló la edición de un correo de la lista para terceros a crear");
        addWarn(null, "Crear Correo", "Se canceló la edición del correo");
    }

    
    /**
     * Método encargado de visualizar un mensaje en la pantalla de tipo
     * informativo
     * <p>
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
     * Método encargado de visualizar un mensaje en la pantalla de tipo
     * Advertencia
     * <p>
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
     * Método encargado de visualizar un mensaje en la pantalla de tipo Error
     * <p>
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
     * Método encargado de visualizar un mensaje en la pantalla de tipo Fatal
     * <p>
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
     *
     */
    public IScPerson getScPersonServer()
    {
        return scPersonServer;
    }

    public void setScPersonServer(IScPerson scPersonServer)
    {
        this.scPersonServer = scPersonServer;
    }

    public IScPhones getScPhonesServer()
    {
        return scPhonesServer;
    }

    public void setScPhonesServer(IScPhones scPhonesServer)
    {
        this.scPhonesServer = scPhonesServer;
    }

    public IScMails getScMailsServer()
    {
        return scMailsServer;
    }

    public void setScMailsServer(IScMails scMailsServer)
    {
        this.scMailsServer = scMailsServer;
    }

    public IScPersonDocumentationAttached getScPersonDocumentationAttachedServer()
    {
        return scPersonDocumentationAttachedServer;
    }

    public void setScPersonDocumentationAttachedServer(IScPersonDocumentationAttached scPersonDocumentationAttachedServer)
    {
        this.scPersonDocumentationAttachedServer = scPersonDocumentationAttachedServer;
    }

    public IScPersonObservations getScPersonObservationsServer()
    {
        return scPersonObservationsServer;
    }

    public void setScPersonObservationsServer(IScPersonObservations scPersonObservationsServer)
    {
        this.scPersonObservationsServer = scPersonObservationsServer;
    }

    public IScPersonSpecifications getScPersonSpecificationsServer()
    {
        return scPersonSpecificationsServer;
    }

    public void setScPersonSpecificationsServer(IScPersonSpecifications scPersonSpecificationsServer)
    {
        this.scPersonSpecificationsServer = scPersonSpecificationsServer;
    }

    public SessionBean getSessionBean()
    {
        return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean)
    {
        this.sessionBean = sessionBean;
    }

    public List<ScPerson> getScPersons()
    {
        return scPersons;
    }

    public void setScPersons(List<ScPerson> scPersons)
    {
        this.scPersons = scPersons;
    }

    public List<ScPerson> getScPersonsUpdate()
    {
        return scPersonsUpdate;
    }

    public void setScPersonsUpdate(List<ScPerson> scPersonsUpdate)
    {
        this.scPersonsUpdate = scPersonsUpdate;
    }

    public List<ScPhones> getPersonPhoneList()
    {
        return personPhoneList;
    }

    public void setPersonPhoneList(List<ScPhones> personPhoneList)
    {
        this.personPhoneList = personPhoneList;
    }

    public List<ScMails> getPersonMailList()
    {
        return personMailList;
    }

    public void setPersonMailList(List<ScMails> personMailList)
    {
        this.personMailList = personMailList;
    }

    public List<ScPersonDocumentationAttached> getPersonScPersonDocumentationAttachedList()
    {
        return personScPersonDocumentationAttachedList;
    }

    public void setPersonScPersonDocumentationAttachedList(List<ScPersonDocumentationAttached> personScPersonDocumentationAttachedList)
    {
        this.personScPersonDocumentationAttachedList = personScPersonDocumentationAttachedList;
    }

    public List<ScPersonObservations> getPersonScPersonObservationsList()
    {
        return personScPersonObservationsList;
    }

    public void setPersonScPersonObservationsList(List<ScPersonObservations> personScPersonObservationsList)
    {
        this.personScPersonObservationsList = personScPersonObservationsList;
    }

    public List<ScPersonSpecifications> getPersonScPersonSpecifications()
    {
        return personScPersonSpecifications;
    }

    public void setPersonScPersonSpecifications(List<ScPersonSpecifications> personScPersonSpecifications)
    {
        this.personScPersonSpecifications = personScPersonSpecifications;
    }

    public ScPerson getPersonAdd()
    {
        return personAdd;
    }

    public void setPersonAdd(ScPerson personAdd)
    {
        this.personAdd = personAdd;
    }

    public ScPerson getPersonUpdate()
    {
        return personUpdate;
    }

    public void setPersonUpdate(ScPerson personUpdate)
    {
        this.personUpdate = personUpdate;
    }

    public ScPhones getPhoneAdd()
    {
        return phoneAdd;
    }

    public void setPhoneAdd(ScPhones phoneAdd)
    {
        this.phoneAdd = phoneAdd;
    }

    public ScMails getMailAdd()
    {
        return mailAdd;
    }

    public void setMailAdd(ScMails mailAdd)
    {
        this.mailAdd = mailAdd;
    }

    public List<ScPersonObservations> getPersonObservationsList()
    {
        return personObservationsList;
    }

    public void setPersonObservationsList(List<ScPersonObservations> personObservationsList)
    {
        this.personObservationsList = personObservationsList;
    }

    public List<ScPersonSpecifications> getPersonSpecificationsList()
    {
        return personSpecificationsList;
    }

    public void setPersonSpecificationsList(List<ScPersonSpecifications> personSpecificationsList)
    {
        this.personSpecificationsList = personSpecificationsList;
    }

    public List<ScPersonDocumentationAttached> getPersonDocumentationAttachedsList()
    {
        return personDocumentationAttachedsList;
    }

    public void setPersonDocumentationAttachedsList(List<ScPersonDocumentationAttached> personDocumentationAttachedsList)
    {
        this.personDocumentationAttachedsList = personDocumentationAttachedsList;
    }

    public ScPersonObservations getPersonObservationsAdd()
    {
        return personObservationsAdd;
    }

    public void setPersonObservationsAdd(ScPersonObservations personObservationsAdd)
    {
        this.personObservationsAdd = personObservationsAdd;
    }

    public ScPersonSpecifications getPersonSpecificationsAdd()
    {
        return personSpecificationsAdd;
    }

    public void setPersonSpecificationsAdd(ScPersonSpecifications personSpecificationsAdd)
    {
        this.personSpecificationsAdd = personSpecificationsAdd;
    }

    public ScPersonDocumentationAttached getPersonDocumentationAttachedAdd()
    {
        return personDocumentationAttachedAdd;
    }

    public void setPersonDocumentationAttachedAdd(ScPersonDocumentationAttached personDocumentationAttachedAdd)
    {
        this.personDocumentationAttachedAdd = personDocumentationAttachedAdd;
    }

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

    public ScPerson getPersonSelected()
    {
        return personSelected;
    }

    public void setPersonSelected(ScPerson personSelected)
    {
        this.personSelected = personSelected;
    }

    
}
