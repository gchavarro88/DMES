/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.beans.maintenance;

import com.sip.dmes.beans.SessionBean;
import com.sip.dmes.dao.bo.IOtMaintenanceCorrective;
import com.sip.dmes.dao.bo.IOtMaintenancePreventive;
import com.sip.dmes.entitys.OtMaintenance;
import com.sip.dmes.entitys.OtMaintenanceCorrective;
import com.sip.dmes.entitys.OtMaintenanceSchedule;
import com.sip.dmes.entitys.ScEmployee;
import com.sip.dmes.entitys.ScMachine;
import com.sip.dmes.entitys.ScMachinePart;
import com.sip.dmes.entitys.ScMaintenanceActivity;
import com.sip.dmes.entitys.ScMaintenanceClasification;
import com.sip.dmes.entitys.ScMaintenanceDamage;

import com.sip.dmes.entitys.ScMaintenanceState;

import com.sip.dmes.entitys.ScPriority;
import com.sip.dmes.entitys.ScReplacement;
import com.sip.dmes.entitys.ScTool;
import com.sip.dmes.entitys.ScWorkforce;

import com.sip.dmes.utilities.DMESConstants;
import com.sip.dmes.utilities.Utilities;
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
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author gchavarro88
 */
public class OtmaintenanceScheduleBean
{
    
    private SessionBean sessionBean;
    private IOtMaintenancePreventive otMaintenancePreventiveServer;
    private ScheduleModel maintenanceSchedule;
    
    
    private final static Logger log = Logger.getLogger(OtmaintenanceScheduleBean.class);
    private final String TAB_GENERAL = "tabGeneral";
    private final String TAB_ACTIVITIES = "tabActivities";
    private final String TAB_EMPLOYEES = "tabEmployees";
    private final String TAB_REPLACEMENTS = "tabReplacements";
    private final String TAB_TOOLS = "tabTools";
    

    /**
     * Creates a new instance of OtmaintenanceCorrectiveBean
     */
    public OtmaintenanceScheduleBean()
    {
        
    }
    
    
    @PostConstruct
    public void initData()
    {
        
        maintenanceSchedule = new LazyScheduleModel() 
        {
             
            @Override
            public void loadEvents(Date start, Date end) 
            {
                try
                {
                    List<OtMaintenanceSchedule> listDates = getOtMaintenancePreventiveServer().getMaintenancesByMonth(start, end);
                    if(listDates != null && !listDates.isEmpty())
                    {
                        for(OtMaintenanceSchedule maintenanceSchedule: listDates)
                        {
                            if(maintenanceSchedule != null)
                            {
                                addEvent(new DefaultScheduleEvent("OM"+getFormatDateGlobal
                                ("yyyyMMddHHmmss", maintenanceSchedule.getCreationDate())+maintenanceSchedule.getIdMaintenance(), 
                                        maintenanceSchedule.getCreationDate(), maintenanceSchedule.getEndDate()));
                            }
                        }
                    }
                    
                }
                catch (Exception e)
                {
                }
                
            }   
        };
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

    public IOtMaintenancePreventive getOtMaintenancePreventiveServer()
    {
        return otMaintenancePreventiveServer;
    }

    public void setOtMaintenancePreventiveServer(IOtMaintenancePreventive otMaintenancePreventiveServer)
    {
        this.otMaintenancePreventiveServer = otMaintenancePreventiveServer;
    }
    
    

    public ScheduleModel getMaintenanceSchedule()
    {
        return maintenanceSchedule;
    }

    public void setMaintenanceSchedule(ScheduleModel maintenanceSchedule)
    {
        this.maintenanceSchedule = maintenanceSchedule;
    }
    
}
