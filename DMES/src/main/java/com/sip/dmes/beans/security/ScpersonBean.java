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
import com.sip.dmes.utilities.Utilities;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.log4j.Logger;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author user
 */
public class ScpersonBean {

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
    private List<ScMails> personMailList;
    private List<ScPersonDocumentationAttached> personScPersonDocumentationAttachedList;
    private List<ScPersonObservations> personScPersonObservationsList;
    private List<ScPersonSpecifications> personScPersonSpecifications;
    private ScPerson personAdd;
    private ScPerson personUpdate;
    private ScPhones phoneAdd;
    private ScMails mailAdd;
    private ScPersonDocumentationAttached personDocumentationAttached;
    private ScPersonObservations personObservations;
    private ScPersonSpecifications personSpecifications;
    //Datos primitivos
    private String telephon;
    private String telephonDescription;
    private String mail;
    private String MailDescription;
    //Constantes
    private final String TAB_BASIC_DATA = "tabBasicDataSave";

    @PostConstruct
    public void initData()
    {

        fillPersonsList();
        setPhoneAdd(new ScPhones());
        setMailAdd(new ScMails());
        setPersonAdd(new ScPerson());
        setPersonPhoneList(new ArrayList<ScPhones>());
        setPersonMailList(new ArrayList<ScMails>());
        setPersonScPersonObservationsList(new ArrayList<ScPersonObservations>());
        setPersonScPersonSpecifications(new ArrayList<ScPersonSpecifications>());

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
                setScPersons(scPersonServer.getScPersons());
            }
        }
        catch (Exception e)
        {
            log.error("Error al intentar cargar la personas en el método inicial", e);
        }
    }

    public String onFlowProcessSavePerson(FlowEvent event) 
    {
        if(event.getOldStep().equalsIgnoreCase(TAB_BASIC_DATA))
        {
            if(getPersonAdd().getLastName()== null || getPersonAdd().getLastName().length() < 1)
            {
                addError(null, "Error al crear un tercero", "Debe ingrear apellidos válidos");
                return event.getOldStep();
            }
            else if(getPersonAdd().getFirstName()== null || getPersonAdd().getFirstName().length() < 1)
            {
                addError(null, "Error al crear un tercero", "Debe ingrear nombres válidos");
                return event.getOldStep();
            }
            else if(getPersonAdd().getFirstName()== null || getPersonAdd().getFirstName().length() < 1)
            {
                addError(null, "Error al crear un tercero", "Debe ingrear nombres válidos");
                return event.getOldStep();
            }
            else if(getPersonAdd().getAge() < 18)
            {
                addError(null, "Error al crear un tercero", "Debe ingresar una edad válida");
                return event.getOldStep();
            }
            else if(getPersonAdd().getDomicilie()== null || getPersonAdd().getDomicilie().length() < 1)
            {
                addError(null, "Error al crear un tercero", "Debe ingrear una dirección válida");
                return event.getOldStep();
            }
        }
        return event.getNewStep(); 
    }
    
    public void savePhonesByPerson()
    {
        if(getPersonPhoneList() != null)
        {
            if(getPhoneAdd() != null && getPhoneAdd().getNumberPhone() > 0)
            {
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
        if(getPersonMailList()!= null)
        {
            if(getMailAdd()!= null && getMailAdd().getMail().length() > 0)
            {
                if(Utilities.isEmail(getMailAdd().getMail()))
                {
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
     * Método encargado de monitoriar el flujo del wizar y crear los nuevos
     * objetos para el mismo informativo
     *
     * @param actionEvent Evento de donde es llamado
     * @param tittle Título del mensaje
     * @param message cuerpo del mensaje
     * @author: Cristian Chaparro
     */
//    public String onFlowProcess(FlowEvent event) {
//        //log.info("El evento paso" + event.getNewStep());
//
//        if (event.getOldStep().equals("tabPersona")) {
//
//            try {
//
//            } catch (Exception e) {
//            }
//
//        }
//        if (event.getNewStep().equals("tabPhone")) {
//
//            setMerPhone(new ScPhones());
//            getMerPhone().setIdPerson(getMerPerson());
//
//        }
//
//        if (event.getNewStep().equals("tabMail")) {
//            setMerMail(new ScMails());
//            getMerMail().setIdPerson(getMerPerson());
//        }
//
//        if (event.getNewStep().equals("tabObservations")) {
//            setMerScPersonObservations(new ScPersonObservations());
//            getMerScPersonObservations().setIdPerson(getMerPerson());
//        }
//
//        if (event.getNewStep().equals("tabSpecifications")) {
//            setMerScPersonSpecifications(new ScPersonSpecifications());
//            getMerScPersonSpecifications().setIdPerson(getMerPerson());
//        }
//        return event.getNewStep();
//    }

    /**
     * Método encargado de visualizar un mensaje en la pantalla de tipo
     * informativo
     *
     * @param actionEvent Evento de donde es llamado
     * @param tittle Título del mensaje
     * @param message cuerpo del mensaje
     * @author: Gustavo Adolfo Chavarro Ortiz
     */
    public void addInfo(ActionEvent actionEvent, String tittle, String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, tittle, message));
    }

    /**
     * Método encargado de visualizar un mensaje en la pantalla de tipo
     * Advertencia
     *
     * @param actionEvent Evento de donde es llamado
     * @param tittle Título del mensaje
     * @param message cuerpo del mensaje
     * @author: Gustavo Adolfo Chavarro Ortiz
     */
    public void addWarn(ActionEvent actionEvent, String tittle, String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, tittle, message));
    }

    /**
     * Método encargado de visualizar un mensaje en la pantalla de tipo Error
     *
     * @param actionEvent Evento de donde es llamado
     * @param tittle Título del mensaje
     * @param message cuerpo del mensaje
     * @author: Gustavo Adolfo Chavarro Ortiz
     */
    public void addError(ActionEvent actionEvent, String tittle, String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, tittle, message));
    }

    /**
     * Método encargado de visualizar un mensaje en la pantalla de tipo Fatal
     *
     * @param actionEvent Evento de donde es llamado
     * @param tittle Título del mensaje
     * @param message cuerpo del mensaje
     * @author: Gustavo Adolfo Chavarro Ortiz
     */
    public void addFatal(ActionEvent actionEvent, String tittle, String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, tittle, message));
    }

    

    /**
     * Métodos Getters And Setters.
     **/
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

    public ScPersonDocumentationAttached getPersonDocumentationAttached()
    {
        return personDocumentationAttached;
    }

    public void setPersonDocumentationAttached(ScPersonDocumentationAttached personDocumentationAttached)
    {
        this.personDocumentationAttached = personDocumentationAttached;
    }

    public ScPersonObservations getPersonObservations()
    {
        return personObservations;
    }

    public void setPersonObservations(ScPersonObservations personObservations)
    {
        this.personObservations = personObservations;
    }

    public ScPersonSpecifications getPersonSpecifications()
    {
        return personSpecifications;
    }

    public void setPersonSpecifications(ScPersonSpecifications personSpecifications)
    {
        this.personSpecifications = personSpecifications;
    }

    public String getTelephon()
    {
        return telephon;
    }

    public void setTelephon(String telephon)
    {
        this.telephon = telephon;
    }

    public String getTelephonDescription()
    {
        return telephonDescription;
    }

    public void setTelephonDescription(String telephonDescription)
    {
        this.telephonDescription = telephonDescription;
    }

    public String getMail()
    {
        return mail;
    }

    public void setMail(String mail)
    {
        this.mail = mail;
    }

    public String getMailDescription()
    {
        return MailDescription;
    }

    public void setMailDescription(String MailDescription)
    {
        this.MailDescription = MailDescription;
    }
    
    
}
