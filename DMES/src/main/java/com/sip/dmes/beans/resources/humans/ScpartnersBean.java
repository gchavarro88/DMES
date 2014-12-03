/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */  
package com.sip.dmes.beans.resources.humans;

import com.sip.dmes.beans.SessionBean;
import com.sip.dmes.dao.bo.IScPartner;
import com.sip.dmes.dao.bo.IScPerson;
import com.sip.dmes.entitys.ScCompetencies;
import com.sip.dmes.entitys.ScEmployee;
import com.sip.dmes.entitys.ScPartner; 
import com.sip.dmes.entitys.ScPerson;
import com.sip.dmes.entitys.ScServicesOrProducts;
import com.sip.dmes.entitys.ScWorkExperience;
import com.sip.dmes.utilities.DMESConstants;
import com.sip.dmes.utilities.Utilities;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.log4j.Logger;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author guschavor 
 */
public class ScpartnersBean
{

    private SessionBean sessionBean;
    private IScPartner scPartnerServer;
    private IScPerson scPersonServer;
    private ScPerson personAdd;
    private ScPerson personUpdate;
    private ScPartner partnerAdd;
    private ScPartner partnerSelected;
    private ScPartner partnerUpdate;
    private ScServicesOrProducts servicesOrProductsAdd;
    private ScServicesOrProducts servicesOrProductsUpdate;
    private List<ScServicesOrProducts> servicesOrProductsList;
    private List<ScPerson> personsList;
    private List<ScPerson> personsListUpdate;
    private List<ScPartner> partnerList;
    private final String TAB_CONFIRM_SAVE = "tabAcceptSave";
    private final String TAB_CONFIRM_UPDATE = "tabAcceptUpdate";
    private final String TAB_PERSON_SAVE = "tabPerson";
    private final String TAB_PERSON_UPDATE = "tabPersonUpdate";
    private final static Logger log = Logger.getLogger(ScpartnersBean.class);
    /**
     * Creates a new instance of ScpartnersBean
     */
    public ScpartnersBean()
    {
    }
    
    @PostConstruct
    public void initData()
    {
        fillListPartners();
        fillListPersons();
        cleanValues();
    }   
    
     
    public void fillListPartners()
    {
        if(getPartnerList()== null)
        {
            try
            {
                setPartnerList(getScPartnerServer().findAllPartners());
            }
            catch (Exception e)
            {
                log.error("Error al intentar consutlar la lista de proveedores para visualizar", e);
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }
        }
    }
    
    public void fillListPersons()
    { 
        try
        { 
            if(getPersonsList() == null)
            {
                List personsWithOutEmployee = getScPersonServer().findPersonWithOutPartnerOrEmployee();
                if(personsWithOutEmployee != null)
                {
                    setPersonsList(new ArrayList<ScPerson>());
                    for (Object object : personsWithOutEmployee)
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
    
    public void numericValidation(String field)
    {
        if(!Utilities.isNumeric(field))
        {
            addError(null, "Valor no numérico", "Debe ingresar un valor numérico, "
                    + "de lo contrario no podrá ir al siguiente paso");
        }
    }
    public void cleanValues()
    {
        setPersonAdd(new ScPerson());
        setPersonUpdate(new ScPerson());
        setPartnerAdd(new ScPartner());
        setPartnerSelected(new ScPartner());
        setPartnerUpdate(new ScPartner());
        setServicesOrProductsAdd(new ScServicesOrProducts());
        setServicesOrProductsUpdate(new ScServicesOrProducts());
        setServicesOrProductsList(new ArrayList<ScServicesOrProducts>());
     
    }
    
    public ScPerson ObjectToScPerson(Object object)
    {
        Object[] objectList = ((Object[]) object);
        ScPerson newPerson = new ScPerson();
        newPerson.setIdPerson(Long.parseLong(objectList[0].toString()));
        newPerson.setFirstName(objectList[1].toString());
        newPerson.setLastName(objectList[2].toString());
        newPerson.setAge(Short.parseShort(objectList[3].toString()));
        newPerson.setCountry(objectList[4].toString());
        newPerson.setCity(objectList[5].toString());
        newPerson.setPersonalInformation(objectList[6] !=null ? objectList[6].toString():"");
        newPerson.setDomicilie(objectList[7].toString());
        newPerson.setStudies(objectList[8] !=null ? objectList[8].toString():"");
        newPerson.setDescription(objectList[9] !=null ? objectList[9].toString():"");
        newPerson.setPathPhoto(objectList[10] !=null ? objectList[10].toString():"/");
        newPerson.setIdentification(Long.parseLong(objectList[13].toString()));
        return newPerson;
    }
    public void saveProductOrServiceAdd()
    {
        if(getServicesOrProductsAdd() != null)
        {
            if(getServicesOrProductsAdd().getNameServiceOrProduct() != null 
                    && getServicesOrProductsAdd().getNameServiceOrProduct().length() > 0)
            {
                if(getServicesOrProductsAdd().getCost() != null && getServicesOrProductsAdd().getCost().doubleValue() > 0)
                {
                    if(getServicesOrProductsAdd().getAmount() > 0)
                    {
                        getServicesOrProductsAdd().setIdPartner(getPartnerAdd());
                        getServicesOrProductsList().add(getServicesOrProductsAdd());
                        setServicesOrProductsAdd(new ScServicesOrProducts());
                        addInfo(null, "Producto o Servicio Agregado", "Se agregó el producto o servicio con éxito");
                    }
                    else
                    {
                        addError(null, "Error al Agregar Producto o Servicio", "Debe ingresar una cantidad válida");
                    }
                }
                else
                {
                    addError(null, "Error al Agregar Producto o Servicio", "Debe ingresar un costo válido");
                }
            }
            else
            {
                addError(null, "Error al Agregar Producto o Servicio", "Debe ingresar un nombre de producto válido");
            }
            
        }
        else
        {
            log.error("Error al intentar agregar un producto o servicio");
            addError(null, "Error al Agregar Producto o Servicio ", "No se pudo agregar el producto o servicio");
        }
    }
    
    
    public void removeProductOrServiceSave(ScServicesOrProducts servicesOrProducts)
    {
        int i=0;
        if(getServicesOrProductsList() != null)
        { 
            for(ScServicesOrProducts scServicesOrProducts: getServicesOrProductsList())
            {
                    if(scServicesOrProducts.getNameServiceOrProduct().equals(servicesOrProducts.getNameServiceOrProduct()))
                    {
                        getServicesOrProductsList().remove(i);
                        addInfo(null, "Servicio o Producto Eliminado", "Se eliminó el servicio o producto con éxito");
                        break;
                    }
                    i++;
            }
        } 
    }
    public void savePartner()
    {
        if(getPartnerAdd()!= null)
        {
            if(getPersonAdd() != null)
            {
                getPartnerAdd().setIdPerson(getPersonAdd());
            }
            if(!getServicesOrProductsList().isEmpty())
            {
                getPartnerAdd().setScServicesOrProductsList(getServicesOrProductsList());
            }
            try  
            {
                getPartnerAdd().setActive("Y");
                getScPartnerServer().createPartner(getPartnerAdd());
                getPersonsList().remove(getPersonAdd());
                getPartnerList().add(getPartnerAdd());
                addInfo(null, DMESConstants.MESSAGE_TITTLE_SUCCES, DMESConstants.MESSAGE_SUCCES);
                cleanValues();
            }
            catch(Exception e)
            {
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
                log.error("Error al intentar crear un nuevo proveedor",e);
            } 
        }
    
    }
    public String onFlowProcessSavePartner(FlowEvent event) 
    {    
        if(event.getOldStep().equals(TAB_PERSON_SAVE))
        {
            if(getPersonAdd() == null || getPersonAdd().getLastName().length() < 1)
            {
                addError(null, "Error al intentar crear un nuevo proveedor", "Debe seleccionar un tercero");
                return event.getOldStep();
            }
        }
        if(event.getNewStep().equals(TAB_CONFIRM_SAVE))
        {
            getPartnerAdd().setCreationDate(new Date());
        }
        
        
            return event.getNewStep(); 
    }
    
    public void updateProductOrServiceAdd()
    {
        if(getServicesOrProductsAdd() != null)
        {
            if(getServicesOrProductsAdd().getNameServiceOrProduct() != null 
                    && getServicesOrProductsAdd().getNameServiceOrProduct().length() > 0)
            {
                if(getServicesOrProductsAdd().getCost() != null && getServicesOrProductsAdd().getCost().doubleValue() > 0)
                {
                    if(getServicesOrProductsAdd().getAmount() > 0)
                    {
                        getServicesOrProductsAdd().setIdPartner(getPartnerAdd());
                        getPartnerSelected().getScServicesOrProductsList().add(getServicesOrProductsAdd());
                        setServicesOrProductsAdd(new ScServicesOrProducts());
                        addInfo(null, "Producto o Servicio Agregado", "Se agregó el producto o servicio con éxito");
                    }
                    else
                    {
                        addError(null, "Error al Agregar Producto o Servicio", "Debe ingresar una cantidad válida");
                    }
                }
                else
                {
                    addError(null, "Error al Agregar Producto o Servicio", "Debe ingresar un costo válido");
                }
            }
            else
            {
                addError(null, "Error al Agregar Producto o Servicio", "Debe ingresar un nombre de producto válido");
            }
            
        }
        else
        {
            log.error("Error al intentar agregar un producto o servicio");
            addError(null, "Error al Agregar Producto o Servicio ", "No se pudo agregar el producto o servicio");
        }
    }
    
    public void removeProductOrServiceUpdate(ScServicesOrProducts servicesOrProducts)
    {
        int i=0;
        if(getPartnerSelected() != null)
        { 
            for(ScServicesOrProducts scServicesOrProducts: getPartnerSelected().getScServicesOrProductsList())
            {
                    if(scServicesOrProducts.getNameServiceOrProduct().equals(servicesOrProducts.getNameServiceOrProduct()))
                    {
                        getPartnerSelected().getScServicesOrProductsList().remove(i);
                        addInfo(null, "Servicio o Producto Eliminado", "Se eliminó el servicio o producto con éxito");
                        break;
                    }
                    i++;
            }
        } 
    }
    public void updatePartner()
    {
        if(getPartnerSelected()!= null)
        {
            try  
            {
                getPartnerSelected().setActive("Y");
                getScPartnerServer().updatePartner(getPartnerSelected());
                getPersonsList().remove(getPartnerSelected().getIdPerson());
                addInfo(null, DMESConstants.MESSAGE_TITTLE_SUCCES, DMESConstants.MESSAGE_SUCCES);
                cleanValues();
            }
            catch(Exception e)
            {
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
                log.error("Error al intentar actualizar un nuevo proveedor",e);
            }
        }
    
    }
    
    public String onFlowProcessUpdatePartner(FlowEvent event) 
    {     
        if(event.getOldStep().equals(TAB_PERSON_UPDATE))
        {
            if(getPartnerSelected().getIdPerson()== null || getPartnerSelected().getIdPerson().getLastName().length() < 1)
            {
                addError(null, "Error al intentar actualizar un nuevo proveedor", "Debe seleccionar un tercero");
                return event.getOldStep();
            } 
        }
        if(event.getNewStep().equals(TAB_CONFIRM_UPDATE))
        {
            getPartnerSelected().setModifyDate(new Date());
        }
        
        
            return event.getNewStep(); 
    }
    
    public void getPartnerByDataTableUpdate(ScPartner partnerSelected)
    {
        try
        { 
            if(partnerSelected != null)
            {
                setPersonsList(null);
                fillListPersons();
                setPartnerSelected(partnerSelected);
                setPersonsListUpdate(getPersonsList());
                getPersonsListUpdate().add(partnerSelected.getIdPerson());
            }
        }
        catch (Exception e)
        {
            log.error("Error intentando asignar el proveedor seleccionado para operaciones de CRUD",e);
        }
    }
    
    
    public void getPartnerByDataTable(ScPartner partner)
    {
        try
        {
            if(partner != null)
            {
                setPartnerSelected(partner);
            }
        }
        catch (Exception e)
        {
            log.error("Error intentando asignar el proveedor seleccionado para operaciones de CRUD",e);
        }
    }
    
    public void deletePartner()
    {
        try
        {
            int i=0;
            if(getPartnerSelected()!= null)
            {
                for(ScPartner partner: getPartnerList())
                {
                    if(Objects.equals(partner.getIdPartner(), getPartnerSelected().getIdPartner()))
                    {
                        getScPartnerServer().deletePartnerById(getPartnerSelected());
                        getPartnerList().remove(i);
                        addInfo(null, DMESConstants.MESSAGE_TITTLE_SUCCES, DMESConstants.MESSAGE_SUCCES);
                        getPersonsList().add(getPartnerSelected().getIdPerson());
                        break;
                    }
                    i++;
                }
            }
        }
        catch(Exception e)
        {
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            log.error("Error al intentar eliminar un proveedor", e);
        }
    
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
    
    public String getFormatDate(Date date)
    {
        String result = "";
        String patron = "dd-MM-yyyy";
        result = getFormatDateGlobal(patron, date);
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
    public SessionBean getSessionBean()
    {
        return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean)
    {
        this.sessionBean = sessionBean;
    }

    public IScPartner getScPartnerServer()
    {
        return scPartnerServer;
    }

    public void setScPartnerServer(IScPartner scPartnerServer)
    {
        this.scPartnerServer = scPartnerServer;
    }

    public IScPerson getScPersonServer()
    {
        return scPersonServer;
    }

    public void setScPersonServer(IScPerson scPersonServer)
    {
        this.scPersonServer = scPersonServer;
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

    public ScPartner getPartnerAdd()
    {
        return partnerAdd;
    }

    public void setPartnerAdd(ScPartner partnerAdd)
    {
        this.partnerAdd = partnerAdd;
    }

    public ScPartner getPartnerSelected()
    {
        return partnerSelected;
    }

    public void setPartnerSelected(ScPartner partnerSelected)
    {
        this.partnerSelected = partnerSelected;
    }

    public ScPartner getPartnerUpdate()
    {
        return partnerUpdate;
    }

    public void setPartnerUpdate(ScPartner partnerUpdate)
    {
        this.partnerUpdate = partnerUpdate;
    }

    public ScServicesOrProducts getServicesOrProductsAdd()
    {
        return servicesOrProductsAdd;
    }

    public void setServicesOrProductsAdd(ScServicesOrProducts servicesOrProductsAdd)
    {
        this.servicesOrProductsAdd = servicesOrProductsAdd;
    }

    public ScServicesOrProducts getServicesOrProductsUpdate()
    {
        return servicesOrProductsUpdate;
    }

    public void setServicesOrProductsUpdate(ScServicesOrProducts servicesOrProductsUpdate)
    {
        this.servicesOrProductsUpdate = servicesOrProductsUpdate;
    }

    public List<ScServicesOrProducts> getServicesOrProductsList()
    {
        return servicesOrProductsList;
    }

    public void setServicesOrProductsList(List<ScServicesOrProducts> servicesOrProductsList)
    {
        this.servicesOrProductsList = servicesOrProductsList;
    }


    public List<ScPerson> getPersonsList()
    {
        return personsList;
    }

    public void setPersonsList(List<ScPerson> personsList)
    {
        this.personsList = personsList;
    }

    public List<ScPerson> getPersonsListUpdate()
    {
        return personsListUpdate;
    }

    public void setPersonsListUpdate(List<ScPerson> personsListUpdate)
    {
        this.personsListUpdate = personsListUpdate;
    }

    public List<ScPartner> getPartnerList()
    {
        return partnerList;
    }

    public void setPartnerList(List<ScPartner> employeesList)
    {
        this.partnerList = employeesList;
    }
    
    
    
    
    
}
