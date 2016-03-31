/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.beans.production;

import com.sip.dmes.beans.SessionBean;
import com.sip.dmes.dao.bo.IOtProduction;
import com.sip.dmes.entitys.OtLogProduction;
import com.sip.dmes.entitys.OtProductionOrder;
import com.sip.dmes.entitys.ScProccesProductOrder;
import com.sip.dmes.entitys.ScProcessEmployee;
import com.sip.dmes.entitys.ScProcessEmployeeOrder;
import com.sip.dmes.entitys.ScProcessInput;
import com.sip.dmes.entitys.ScProcessInputOrder;
import com.sip.dmes.entitys.ScProcessMachine;
import com.sip.dmes.entitys.ScProcessMachineOrder;
import com.sip.dmes.entitys.ScProcessProduct;
import com.sip.dmes.entitys.ScProductFormulation;
import com.sip.dmes.entitys.ScProductOrder;
import com.sip.dmes.entitys.ScProductionState;
import com.sip.dmes.utilities.DMESConstants;
import com.sip.dmes.utilities.Utilities;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
public class OtLogProductionBean
{
    
    private SessionBean sessionBean;
    private IOtProduction otProductionServer;
    
    private final static Logger log = Logger.getLogger(OtLogProductionBean.class);
    private List<OtLogProduction> listLog;
    
    /**
     * Creates a new instance of OtLogProduction
     */
    public OtLogProductionBean()
    {
        
    }
    
    
    @PostConstruct
    public void initData()
    {
        fillListProductionState();
    }   
    
    /**
     * Método encargado de carga la lista inicial de estados de producción
     * @author Gustavo Chavarro Ortiz
    */
    public void fillListProductionState()
    {
        
            try
            {
                setListLog(getOtProductionServer().getListLogProduction());
            }
            catch (Exception e)
            {
                log.error("Error al intentar consutlar la lista de logs de producción", e);
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
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

    public IOtProduction getOtProductionServer()
    {
        return otProductionServer;
    }

    public void setOtProductionServer(IOtProduction otProductionServer)
    {
        this.otProductionServer = otProductionServer;
    }

    public List<OtLogProduction> getListLog()
    {
        return listLog;
    }

    public void setListLog(List<OtLogProduction> listLog)
    {
        this.listLog = listLog;
    }

    

}
