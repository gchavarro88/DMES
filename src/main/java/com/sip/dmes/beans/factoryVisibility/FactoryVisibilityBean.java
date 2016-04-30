package com.sip.dmes.beans.factoryVisibility;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.sip.dmes.beans.SessionBean;
import com.sip.dmes.dao.bo.IFactoryVisibility;
import com.sip.dmes.entitys.ScFactoryLocation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;


/**
 *
 * @author gchavarro88
 */
public class FactoryVisibilityBean
{

    private final static Logger log = Logger.getLogger(FactoryVisibilityBean.class); //Variable de logger que permite guardar registro de la aplicación
    private SessionBean sessionBean; //Bean de sesion
    private IFactoryVisibility factoryVisibilityServer ; //Bean para acceder al DAO
    private List<ScFactoryLocation> factoryLocations;
    
    
    
    
    
    /**
     * Creates a new instance of ScrolesBean
     */
    public FactoryVisibilityBean()
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
        
    }
    
    public List<ScFactoryLocation> findLocations()
    {
        if(getFactoryLocations() == null || getFactoryLocations().isEmpty())
        {
            setFactoryLocations(getFactoryVisibilityServer().getFactoryLocations());
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext();
            session.setAttribute("locations", getFactoryLocations());
            RequestContext.getCurrentInstance().execute("getFactoryLocations();");
        }
        return getFactoryLocations();
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

    public IFactoryVisibility getFactoryVisibilityServer()
    {
        return factoryVisibilityServer;
    }

    public void setFactoryVisibilityServer(IFactoryVisibility factoryVisibilityServer)
    {
        this.factoryVisibilityServer = factoryVisibilityServer;
    }

    public List<ScFactoryLocation> getFactoryLocations()
    {
        return factoryLocations;
    }

    public void setFactoryLocations(List<ScFactoryLocation> factoryLocations)
    {
        this.factoryLocations = factoryLocations;
    }

    
    
}
