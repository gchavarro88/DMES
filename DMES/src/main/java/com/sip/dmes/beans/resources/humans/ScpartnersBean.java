/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.beans.resources.humans;

import com.sip.dmes.beans.SessionBean;
import com.sip.dmes.dao.bo.IScPartner;
import com.sip.dmes.dao.bo.IScPerson;
import com.sip.dmes.entitys.ScPartner;
import com.sip.dmes.entitys.ScPerson;
import com.sip.dmes.entitys.ScServicesOrProducts;
import com.sip.dmes.utilities.DMESConstants;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.log4j.Logger;

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
    private List<ScServicesOrProducts> competenciesListAdd;
    private List<ScPerson> personsList;
    private List<ScPerson> personsListUpdate;
    private List<ScPartner> partnerList;
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
//        cleanValues();
//        fillListEmployee();
//        fillListPersons();
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

    public List<ScServicesOrProducts> getCompetenciesListAdd()
    {
        return competenciesListAdd;
    }

    public void setCompetenciesListAdd(List<ScServicesOrProducts> competenciesListAdd)
    {
        this.competenciesListAdd = competenciesListAdd;
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
