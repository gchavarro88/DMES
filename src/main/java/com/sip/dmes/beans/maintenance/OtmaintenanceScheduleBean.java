/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.beans.maintenance;

import com.sip.dmes.beans.SessionBean;
import com.sip.dmes.dao.bo.IOtMaintenanceCorrective;
import com.sip.dmes.dao.bo.IOtMaintenancePreventive;
import com.sip.dmes.entitys.OtMaintenanceCorrective;
import com.sip.dmes.entitys.OtMaintenancePreventive;
import com.sip.dmes.entitys.OtMaintenanceSchedule;
import com.sip.dmes.utilities.DMESConstants;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author gchavarro88
 */
public class OtmaintenanceScheduleBean
{
    
    private SessionBean sessionBean;
    private IOtMaintenancePreventive otMaintenancePreventiveServer;
    private IOtMaintenanceCorrective otMaintenanceCorrectiveServer;
    private ScheduleModel maintenanceSchedule;
    private OtMaintenanceCorrective maintenanceCorrective;
    private OtMaintenancePreventive maintenancePreventive;
    private ScheduleEvent event;
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
        setEvent(new DefaultScheduleEvent());
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
                    addError(null, "Error de Consulta", "Error al intentar consultar las programaciones para el mes");
                    log.error("Error al intentar consultar las programaciones para el mes",e);
                }
                
            }   
        };
    }   
    
    public void cleanValues()
    {
        setMaintenanceCorrective(null);
        setMaintenancePreventive(null);
    }
    /**
     * Consulta el mantenimiento y dependiendo del tipo de mantenimiento muestra 
     * un resultado en la pantalla.
     * @param idMaintenance id del mantenimiento
     * @Author Gustavo Chavarro Ortiz
     */
    public void findMaintenance(Long idMaintenance)
    {
        try
        {
            cleanValues();
            setMaintenanceCorrective(getOtMaintenanceCorrectiveServer().getMaintenanceById(idMaintenance));
            setMaintenancePreventive(getOtMaintenancePreventiveServer().getMaintenanceById(idMaintenance));
            if(getMaintenanceCorrective() != null)
            {
                RequestContext.getCurrentInstance().execute("PF('dialogMaintenanceCorrectiveView').show()");
            }
            else if(getMaintenancePreventive() != null)
            {
                RequestContext.getCurrentInstance().execute("PF('dialogMaintenancePreventiveView').show()");
            }
            else
            {
                addInfo(null, "Resultados Nulos", "No se obtuvieron resultados para la consulta del evento");
            }
        }
        catch (Exception e)
        {
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            log.error(DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR+"\n"+DMESConstants.MESSAGE_ERROR_ADMINISTRATOR,e);
        }
    }
    /**
     * Método encaragado de capturar el evento de click sobre las programaciones.
     * @param selectEvent 
     * @Author Gustavo Chavarro Ortiz
     */
    public void onEventSelect(SelectEvent selectEvent) 
    {
        setEvent((ScheduleEvent) selectEvent.getObject());
        RequestContext.getCurrentInstance().execute("PF('dialogMaintenanceCorrectiveView').hide()");
        RequestContext.getCurrentInstance().execute("PF('dialogMaintenancePreventiveView').hide()");
        try
        {
            findMaintenance(Long.parseLong(getEvent().getTitle().substring(16, getEvent().getTitle().length())));
        }
        catch (Exception e)
        {
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR,
                           DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
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

    public OtMaintenanceCorrective getMaintenanceCorrective()
    {
        return maintenanceCorrective;
    }

    public void setMaintenanceCorrective(OtMaintenanceCorrective maintenanceCorrective)
    {
        this.maintenanceCorrective = maintenanceCorrective;
    }

    public OtMaintenancePreventive getMaintenancePreventive()
    {
        return maintenancePreventive;
    }

    public void setMaintenancePreventive(OtMaintenancePreventive maintenancePreventive)
    {
        this.maintenancePreventive = maintenancePreventive;
    }

    public ScheduleEvent getEvent()
    {
        return event;
    }

    public void setEvent(ScheduleEvent event)
    {
        this.event = event;
    }

    public IOtMaintenanceCorrective getOtMaintenanceCorrectiveServer()
    {
        return otMaintenanceCorrectiveServer;
    }

    public void setOtMaintenanceCorrectiveServer(IOtMaintenanceCorrective otMaintenanceCorrectiveServer)
    {
        this.otMaintenanceCorrectiveServer = otMaintenanceCorrectiveServer;
    }
    
    
    
}
