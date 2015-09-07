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
import com.sip.dmes.entitys.ScInput;
import com.sip.dmes.entitys.ScMails;
import com.sip.dmes.entitys.ScPerson;
import com.sip.dmes.entitys.ScPersonDocumentationAttached;
import com.sip.dmes.entitys.ScPersonObservations;
import com.sip.dmes.entitys.ScPersonSpecifications;
import com.sip.dmes.entitys.ScPhones;
import com.sip.dmes.utilities.DMESConstants;
import com.sip.dmes.utilities.Utilities;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
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
    private List<ScPhones> personPhoneListBk;
    private List<ScPersonObservations> personObservationsListBk;
    private List<ScPersonSpecifications> personSpecificationsListBk;
    private UploadedFile pictureFile; //Archivo que se copiara para la imagen del insumo
    private List<ScMails> personMailListBk;
    private ScPerson personAdd;
    private ScPerson personUpdate;
    private ScPhones phoneAdd;
    private ScPerson personSelected;
    private ScMails mailAdd;
    private ScPersonObservations personObservationsAdd;
    private ScPersonSpecifications personSpecificationsAdd;
   
    
    
    //Constantes
    private final String TAB_BASIC_DATA = "tabBasicDataSave";
    private final String TAB_PHONES_SAVE = "tabPhonesSave";
    private final String TAB_MAILS_SAVE = "tabMailsSave";
    private final String TAB_OBSERVATIONS_SAVE = "tabObservationSave";
    private final String TAB_SPECIFICATIONS_SAVE = "tabSpecificationsSave";
    private final String TAB_DOCUMENTATIONS_SAVE = "tabDocumentationsSave";
    private final String TAB_CONFIRM_SAVE = "tabConfirmSave";
    private final String TAB_BASIC_DATA_UPDATE = "tabBasicDataUpdate";
    private final String TAB_PHONES_UPDATE = "tabPhonesUpdate";
    private final String TAB_MAILS_UPDATE = "tabMailsUpdate";
    private final String TAB_OBSERVATIONS_UPDATE = "tabObservationUpdate";
    private final String TAB_SPECIFICATIONS_UPDATE = "tabSpecificationsUpdate";
    private final String TAB_DOCUMENTATIONS_UPDATE = "tabDocumentationsUpdate";
    private final String TAB_CONFIRM_UPDATE = "tabConfirmUpdate";
    //files
    private int MAX_SIZE_FILE = 5;//Tamaño en megas del archivo
    private String EXTENSION_FILE = "pdf,xls,doc,xlsx,docx,txt,pps,ppt,pptx,ppsx";
    private String PATH_FILE = System.getProperty("user.home"); //Obtenemos la ruta del servidor
    
    
     
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
//        setPersonDocumentationAttachedAdd(new ScPersonDocumentationAttached());
        setPersonAdd(new ScPerson());
        setPersonObservationsList(new ArrayList<ScPersonObservations>());
        setPersonSpecificationsList(new ArrayList<ScPersonSpecifications>());
        setPersonDocumentationAttachedsList(new ArrayList<ScPersonDocumentationAttached>());
        setPersonPhoneList(new ArrayList<ScPhones>());
        setPersonMailList(new ArrayList<ScMails>());
        setPersonObservationsList(new ArrayList<ScPersonObservations>());
        setPersonSpecificationsListBk(new ArrayList<ScPersonSpecifications>());
//        setPersonDocumentationAttachedsListBk(new ArrayList<ScPersonDocumentationAttached>());
        setPersonPhoneListBk(new ArrayList<ScPhones>());
        setPersonMailListBk(new ArrayList<ScMails>());

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
            if (getPersonAdd().getIdentification() <= 0 || (getPersonAdd().getIdentification()+"").contains("."))
            {
                addError(null, "Error al crear un tercero", "Debe ingresar un número de cédula válido. Ejemplo: 16343434");
                return event.getOldStep();
            }
            else if (getPersonAdd().getLastName() == null || getPersonAdd().getLastName().length() < 1)
            {
                addError(null, "Error al crear un tercero", "Debe ingresar apellidos válidos");
                return event.getOldStep();
            }
            else if (getPersonAdd().getFirstName() == null || getPersonAdd().getFirstName().length() < 1)
            {
                addError(null, "Error al crear un tercero", "Debe ingresar nombres válidos");
                return event.getOldStep();
            }
            else if (getPersonAdd().getFirstName() == null || getPersonAdd().getFirstName().length() < 1)
            {
                addError(null, "Error al crear un tercero", "Debe ingresar nombres válidos");
                return event.getOldStep();
            }
            else if (getPersonAdd().getAge() < 18)
            {
                addError(null, "Error al crear un tercero", "Debe ingresar una edad válida");
                return event.getOldStep();
            }
            else if (getPersonAdd().getDomicilie() == null || getPersonAdd().getDomicilie().length() < 1)
            {
                addError(null, "Error al crear un tercero", "Debe ingresar una dirección válida");
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
            if (getPhoneAdd() != null && getPhoneAdd().getNumberPhone() > 0 && (getPhoneAdd().getNumberPhone()+"").length() >= 7 )
            {
                getPhoneAdd().setIdPerson(getPersonAdd());
                getPersonPhoneList().add(getPhoneAdd());
                setPhoneAdd(new ScPhones());
                ScPhones data = new ScPhones();
                data.setIdPerson(getPersonAdd());
                data.setDescription(getPhoneAdd().getDescription());
                data.setNumberPhone(getPhoneAdd().getNumberPhone());
                getPersonPhoneListBk().add(data);
                
            }
            else
            {
                addError(null, "Error al crear un tercero", "Debe ingresar un número telefónico válido, ejemplo: 3017001704");
                setPhoneAdd(new ScPhones());
            }
        }
    }
 
    /** 
     * Método encargado de visualizar la imagen de un elemento.
     *
     * @return String cadena con la ruta de la imagen
     * @param input insumo al que se le consultará la imagen
     * @author Gustavo Chavarro Ortiz
     */
    public String searchImage(ScPerson person)
    {
        try
        {
            if (person != null)
            {
                if (!Utilities.isEmpty(person.getPathPhoto())) 
                {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(person.getPathPhoto())));
                    //la constante me permite mapear imagenes externas
                    return DMESConstants.PATH_EXTERN_PICTURES + person.getPathPhoto();
                }
            }
        }
        catch (Exception e)
        {
            addError(null, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR, "La imagen no existe");
        }
        return DMESConstants.PATH_IMAGE_DEFAULT;
    }
    
    
    public void removePhonesByPerson(ScPhones phones)
    {
        int i=0;
        if(getPersonPhoneList() != null)
        {
            for(ScPhones phonesSelected: getPersonPhoneList())
            {
                    if(phonesSelected.getNumberPhone() == phones.getNumberPhone())
                    {
                        getPersonPhoneList().remove(i);
                        addInfo(null, "Teléfono Eliminado", "Se eliminó el número telefónico con éxito");
                        break;
                    }
                    i++;
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
                    getPersonMailListBk().add(getMailAdd());
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
    
    public void removeMailsByPerson(ScMails mails)
    {
        int i=0;
        if(getPersonMailList() != null)
        {
            for(ScMails mailsSelected: getPersonMailList())
            {
                    if(mailsSelected.getMail().equals(mails.getMail()))
                    {
                        getPersonMailList().remove(i);
                        addInfo(null, "Teléfono Eliminado", "Se eliminó el mail con éxito");
                        break;
                    }
                    i++;
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
                    getPersonSpecificationsListBk().add(getPersonSpecificationsAdd());
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

    public void removeSpecificationsByPerson(ScPersonSpecifications personSpecifications)
    {
        int i=0;
        if(getPersonSpecificationsList() != null)
        {
            for(ScPersonSpecifications personSpecificationsSelected: getPersonSpecificationsList())
            {
                    if(personSpecificationsSelected.getSpecification().equals(personSpecifications.getSpecification()))
                    {
                        getPersonSpecificationsList().remove(i);
                        addInfo(null, "Especificación Eliminado", "Se eliminó la especificación con éxito");
                        break;
                    }
                    i++;
            }
        }
    }
    
    
    
        
    /**
     * Método encargado de recibir una entrada de datos y un archivo para posteriormente
     * escribir los datos en el archivo.
     * @param dataIn entrada de datos a escribir
     * @param newFile archivo nuevo en el que se escribiran los datos
     * @return valor booleano indicando si el proceso de escritura se realizó satisfactoriamente
     * @author: Gustavo Adolfo Chavarro Ortiz
     */
    public boolean writeFile(InputStream dataIn, File newFile) throws IOException
    {
        boolean result = false;
        try
        {
            OutputStream outputStream = new FileOutputStream(newFile);
            byte[] buffer = new byte[1024];
            int len;
            while((len = dataIn.read(buffer)) > 0)
            { 
                outputStream.write(buffer, 0, len);
            }
            outputStream.flush();
            outputStream.close();
            dataIn.close();
            result = true;
        }
        catch(IOException e)
        {
            
            e.printStackTrace();
            throw e;
        }
        return result;
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
                    setPersonObservationsListBk(new ArrayList<ScPersonObservations>(getPersonObservationsList()));
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
    
    public void removeObservationsByPerson(ScPersonObservations personObservations)
    {
        int i=0;
        if(getPersonSpecificationsList() != null)
        {
            for(ScPersonObservations personObservationsSelected: getPersonObservationsList())
            {
                    if(personObservationsSelected.getObservation().equals(personObservations.getObservation()))
                    {
                        getPersonObservationsList().remove(i);
                        addInfo(null, "Observación Eliminada", "Se eliminó la observación con éxito");
                        break;
                    }
                    i++;
            }
        }
    }
    public String onFlowProcessUpdatePerson(FlowEvent event)
    {
        if (event.getOldStep().equalsIgnoreCase(TAB_BASIC_DATA_UPDATE))
        {
            if (getPersonUpdate().getLastName() == null || getPersonUpdate().getLastName().length() < 1)
            {
                addError(null, "Error al actualizar un tercero", "Debe ingresar apellidos válidos");
                return event.getOldStep();
            }
            else if (getPersonUpdate().getFirstName() == null || getPersonUpdate().getFirstName().length() < 1)
            {
                addError(null, "Error al actualizar un tercero", "Debe ingresar nombres válidos");
                return event.getOldStep();
            }
            else if (getPersonUpdate().getFirstName() == null || getPersonUpdate().getFirstName().length() < 1)
            {
                addError(null, "Error al actualizar un tercero", "Debe ingresar nombres válidos");
                return event.getOldStep();
            }
            else if (getPersonUpdate().getAge() < 18)
            {
                addError(null, "Error al actualizar un tercero", "Debe ingresar una edad válida");
                return event.getOldStep();
            }
            else if (getPersonUpdate().getDomicilie() == null || getPersonUpdate().getDomicilie().length() < 1)
            {
                addError(null, "Error al actualizar un tercero", "Debe ingresar una dirección válida");
                return event.getOldStep();
            }
        }
        else if(event.getNewStep().equalsIgnoreCase(TAB_CONFIRM_UPDATE))
        {
            getPersonUpdate().setModifyDate(new Date());
        }
        
        return event.getNewStep();
    }
    
    public void updatePhonesByPerson()
    {
        if (getPhoneAdd() !=  null && getPersonUpdate().getScPhonesList() != null)
        {
            if (getPhoneAdd() != null && getPhoneAdd().getNumberPhone() > 0 && (getPhoneAdd().getNumberPhone()+"").length() >= 7 )
            {
                getPhoneAdd().setIdPerson(getPersonUpdate());
                getPersonUpdate().getScPhonesList().add(getPhoneAdd());
                setPhoneAdd(new ScPhones());
                ScPhones data = new ScPhones();
                data.setIdPerson(getPersonUpdate());
                data.setDescription(getPhoneAdd().getDescription());
                data.setNumberPhone(getPhoneAdd().getNumberPhone());
                getPersonPhoneListBk().add(data);
                
            }
            else
            {
                addError(null, "Error al crear un tercero", "Debe ingresar un número telefónico válido. Ejemplo 3017001704");
                setPhoneAdd(new ScPhones());
            }
        }
    }
    
    public void removePhonesByPersonUpdate(ScPhones phones)
    {
        int i=0;
        if(getPersonUpdate().getScPhonesList() != null)
        {
            for(ScPhones phonesSelected: getPersonUpdate().getScPhonesList())
            {
                    if(phonesSelected.getNumberPhone() == phones.getNumberPhone())
                    {
                        getPersonUpdate().getScPhonesList().remove(i);
                        addInfo(null, "Teléfono Eliminado", "Se eliminó el número telefónico con éxito");
                        break;
                    }
                    i++;
            }
        }
    }
    
    public void updateMailsByPerson()
    {
        if (getMailAdd()!= null && getPersonUpdate().getScMailsList() != null )
        {
            if (getMailAdd() != null && getMailAdd().getMail().length() > 0)
            {
                if (Utilities.isEmail(getMailAdd().getMail()))
                {
                    getMailAdd().setIdPerson(getPersonUpdate());
                    getPersonUpdate().getScMailsList().add(getMailAdd());
                    setMailAdd(new ScMails());
                    getPersonMailListBk().add(getMailAdd());
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
    
    public void removeMailsByPersonUpdate(ScMails mails)
    {
        int i=0;
        if(getPersonUpdate().getScMailsList()!= null)
        {
            for(ScMails mailsSelected: getPersonUpdate().getScMailsList())
            {
                    if(mailsSelected.getMail().equals(mails.getMail()))
                    {
                        getPersonUpdate().getScMailsList().remove(i);
                        addInfo(null, "Teléfono Eliminado", "Se eliminó el mail con éxito");
                        break;
                    }
                    i++;
            }
        }
    }
    
    public void updateSpecificationsByPerson()
    {
        if (getPersonUpdate().getScPersonSpecificationsList() != null && getPersonSpecificationsAdd() != null)
        {
            if (getPersonSpecificationsAdd()!= null && getPersonSpecificationsAdd().getTittle().length() > 0)
            {
                if (getPersonSpecificationsAdd().getSpecification().length() > 0)
                {
                    getPersonSpecificationsAdd().setIdPerson(getPersonUpdate());
                    getPersonUpdate().getScPersonSpecificationsList().add(getPersonSpecificationsAdd());
                    setPersonSpecificationsAdd(new ScPersonSpecifications());
                    getPersonSpecificationsListBk().add(getPersonSpecificationsAdd());
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
    
    public void removeSpecificationsByPersonUpdate(ScPersonSpecifications personSpecifications)
    {
        int i=0;
        if(getPersonUpdate().getScPersonSpecificationsList() != null)
        {
            for(ScPersonSpecifications personSpecificationsSelected: getPersonUpdate().getScPersonSpecificationsList())
            {
                    if(personSpecificationsSelected.getSpecification().equals(personSpecifications.getSpecification()))
                    {
                        getPersonUpdate().getScPersonSpecificationsList().remove(i);
                        addInfo(null, "Especificación Eliminado", "Se eliminó la especificación con éxito");
                        break;
                    }
                    i++;
            }
        }
    }
    
    public void updateObservationsByPerson()
    {
        if (getPersonUpdate().getScPersonObservationsList() != null && getPersonObservationsAdd()!= null)
        {
            if (getPersonObservationsAdd()!= null && getPersonObservationsAdd().getTittle().length() > 0)
            {
                if (getPersonObservationsAdd().getObservation().length() > 0)
                {
                    getPersonObservationsAdd().setIdPerson(getPersonUpdate());
                    getPersonUpdate().getScPersonObservationsList().add(getPersonObservationsAdd());
                    setPersonObservationsAdd(new ScPersonObservations());
                    setPersonObservationsListBk(new ArrayList<ScPersonObservations>(getPersonObservationsList()));
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
    
    public void removeObservationsByPersonUpdate(ScPersonObservations personObservations)
    {
        int i=0;
        if(getPersonUpdate().getScPersonObservationsList() != null)
        {
            for(ScPersonObservations personObservationsSelected: getPersonUpdate().getScPersonObservationsList())
            {
                    if(personObservationsSelected.getObservation().equals(personObservations.getObservation()))
                    {
                        getPersonUpdate().getScPersonObservationsList().remove(i);
                        addInfo(null, "Observación Eliminada", "Se eliminó la observación con éxito");
                        break;
                    }
                    i++;
            }
        }
    }
    
    public void updatePerson()
    {
        try
        {
            if(getPersonUpdate() != null )
            {
                getScPersonServer().updateScPerson(getPersonUpdate());
                addInfo(null, DMESConstants.MESSAGE_TITTLE_SUCCES, DMESConstants.MESSAGE_SUCCES);
                for(ScPerson person: getScPersons())
                {
                    if(person.getIdPerson()== getPersonUpdate().getIdPerson())
                    {
                        person = getPersonUpdate();
                        break;
                    }
                }
                cleanValues();
                
            }
            else
            {
                log.error("Error al intentar actualizar un tercero");
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }
        }
        catch (Exception e)
        {
            log.error("Error al intentar actualizar un tercero", e);
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
        }
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
    
    public void getPersonByUpdateDataTable(ScPerson scPersonSelected)
    {
        try
        { 
            if(scPersonSelected != null)
            {
                setPersonUpdate(scPersonSelected);
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
    
    /**
     * Método encargado de realizar la copia del archivo que se desea cargar.
     *
     * @param option se escoge la opción entre guardar y actualizar
     * @author: Gustavo Adolfo Chavarro Ortiz
     */
    public void handleFileUpload(int option)
    {
        //Validamos que el evento de copiado no sea nulo
        switch (option)
        {
            case 1://opción para guardar
                RequestContext.getCurrentInstance().execute("PF('pictureSave').hide()");
                break;
            case 2://opción para actualizar
                RequestContext.getCurrentInstance().execute("PF('pictureUpdate').hide()");
                break;
            default:
                break;
        }
        if (getPictureFile() != null)
        {

            String fileName = getPictureFile().getFileName(); //Extraemos el nombre del archivo
            long fileSize = getPictureFile().getSize(); //Extraemos el tamaño del archivo
            int positionLimitName = fileName.indexOf("."); //Extraemos la posicion del delimitar del tipo del archivo
            String fileType = fileName.substring(positionLimitName + 1, fileName.length()); //Extraemos el tipo del archivo
            //Validamos que el archivo contenga los tipos permitidos
            if (DMESConstants.TYPES_EXTENTIONS_IMAGES.contains(fileType))
            {
                String folderName = DMESConstants.FILE_PATH_INPUTS_IMG;
                //Creamos el folder
                File folder = new File(PATH_FILE + "/" + folderName);
                folder.mkdirs();
                //Creamos el archivo con la ruta y el nombre de la carpeta
                File file = new File(folder + "/" + fileName);
                try
                {
                    //Creamos el archivo y lo enviamos al metodo que lo escribe
                    if (writeFile(getPictureFile().getInputstream(), file))
                    {
                        switch (option)
                        {
                            case 1://opción para guardar
                                getPersonAdd().setPathPhoto(file.getAbsolutePath());
                                break;
                            case 2://opción para actualizar
                                getPersonUpdate().setPathPhoto(file.getAbsolutePath());
                                break;
                            default:
                                break;
                        }
                        //addInfo(null, DMESConstants.MESSAGE_TITTLE_SUCCES, DMESConstants.MESSAGE_SUCCES);
                    }
                    //Si sucede un error al escribir el archivo
                    else
                    {
                        addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
                    }
                }
                catch (Exception e)
                {
                    //Excepción de escritura
                    addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
                    log.error("Error al intentar guardar la imagen", e);
                }
            }
            //El tipo no pertenece
            else
            {
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, "El archivo no pertenece a los tipos permitidos " + DMESConstants.TYPES_EXTENTIONS_IMAGES);
            }
        }
        else
        {
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, "El archivo se encuentra vacio");
        }
        switch (option)
        {
            case 1://opción para guardar
                RequestContext.getCurrentInstance().execute("PF('dialogPersonSave').show()");
                break;
            case 2://opción para actualizar
                RequestContext.getCurrentInstance().execute("PF('dialogPersonUpdate').show()");
                break;
            default:
                break;
        }
    }
    
    
    
    
    
    
    public void onRowEditPhones(RowEditEvent event)
    {
        ScPhones phoneEdit = (ScPhones)event.getObject();
        if(phoneEdit != null)
        {
            if(phoneEdit.getNumberPhone() > 0 && (phoneEdit.getNumberPhone()+"").length() == 10)
            {
                log.info("Se editó un teléfono de la lista para terceros a crear");
                addInfo(null, "Crear Teléfono", "Se editó satisfactoriamente el teléfono");
                setPersonPhoneListBk(new ArrayList<ScPhones>());
            }
            else
            {
                log.error("Error al intentar editar un teléfono");
                addError(null, "Editar Teléfono", "Debe ingresar un número valido");
            }
        
        }
        
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
        ScMails mailEdit = (ScMails) event.getObject();
        if(mailEdit != null)
        {
            if(mailEdit.getMail().length() > 0 && Utilities.isEmail(mailEdit.getMail()))
            {
                log.info("Se editó el correo de la lista para terceros a crear");
                addInfo(null, "Crear Correo", "Se editó satisfactoriamente el correo");
            }
            else
            {
                log.error("Error al intentar actualizar un correo");
                addError(null, "Error al modificar el correo", "Debe ingresar un correo válido");
                mailEdit.setMail("");
            }
        }
    }

    public void onRowCancelMails(RowEditEvent event)
    {
        log.info("Se canceló la edición de un correo de la lista para terceros a crear");
        addWarn(null, "Crear Correo", "Se canceló la edición del correo");
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


    public ScPerson getPersonSelected()
    {
        return personSelected;
    }

    public void setPersonSelected(ScPerson personSelected)
    {
        this.personSelected = personSelected;
    }

    public List<ScPhones> getPersonPhoneListBk()
    {
        return personPhoneListBk;
    }

    public void setPersonPhoneListBk(List<ScPhones> personPhoneListBk)
    {
        this.personPhoneListBk = personPhoneListBk;
    }

    public List<ScPersonObservations> getPersonObservationsListBk()
    {
        return personObservationsListBk;
    }

    public void setPersonObservationsListBk(List<ScPersonObservations> personObservationsListBk)
    {
        this.personObservationsListBk = personObservationsListBk;
    }

    public List<ScPersonSpecifications> getPersonSpecificationsListBk()
    {
        return personSpecificationsListBk;
    }

    public void setPersonSpecificationsListBk(List<ScPersonSpecifications> personSpecificationsListBk)
    {
        this.personSpecificationsListBk = personSpecificationsListBk;
    }


    public void setPersonMailListBk(List<ScMails> personMailListBk)
    {
        this.personMailListBk = personMailListBk;
    }

    public List<ScMails> getPersonMailListBk()
    {
        return personMailListBk;
    }

    public UploadedFile getPictureFile()
    {
        return pictureFile;
    }

    public void setPictureFile(UploadedFile pictureFile)
    {
        this.pictureFile = pictureFile;
    }

   
}
