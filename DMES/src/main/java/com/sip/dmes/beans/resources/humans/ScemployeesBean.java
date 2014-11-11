/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.beans.resources.humans;

import com.sip.dmes.beans.SessionBean;
import com.sip.dmes.dao.bo.IScEmployee;
import com.sip.dmes.dao.bo.IScPerson;
import com.sip.dmes.dao.bs.ScEmployeeDao;
import com.sip.dmes.entitys.ScEmployee;
import com.sip.dmes.entitys.ScPerson;

import com.sip.dmes.utilities.DMESConstants;
import com.sip.dmes.utilities.Utilities;
import java.text.SimpleDateFormat;
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
public class ScemployeesBean
{
    
    private SessionBean sessionBean;
    private IScEmployee scEmployeeServer;
    private IScPerson scPersonServer;
    private ScPerson personAdd;
    private ScPerson personUpdate;
    private ScEmployee employeeAdd;
    private ScEmployee employeeSelected;
    private ScEmployee employeeUpdate;
    private List<ScPerson> personsList;
    private List<ScEmployee> employeesList;
    private final static Logger log = Logger.getLogger(ScemployeesBean.class);
    
    

    /**
     * Creates a new instance of ScemployeesBean
     */
    public ScemployeesBean()
    {
        
    }
    @PostConstruct
    public void initData()
    {
        fillListEmployee();
        
    }
    
    public void fillListEmployee()
    {
        if(getEmployeesList() == null)
        {
            try
            {
                setEmployeesList(getScEmployeeServer().getAllEmployees());
            }
            catch (Exception e)
            {
                log.error("Error al intentar consutlar la lista de empleados para visualizar", e);
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }
        }
    }

    
    

    
    public String onFlowProcessSaveEmployee(FlowEvent event) 
    {    
        if(event.getOldStep().equals(""))
        {
            
            
        }
        
        
            return event.getNewStep(); 
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

    public IScEmployee getScEmployeeServer()
    {
        return scEmployeeServer;
    }

    public void setScEmployeeServer(IScEmployee scEmployeeServer)
    {
        this.scEmployeeServer = scEmployeeServer;
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

    public ScEmployee getEmployeeAdd()
    {
        return employeeAdd;
    }

    public void setEmployeeAdd(ScEmployee employeeAdd)
    {
        this.employeeAdd = employeeAdd;
    }

    public ScEmployee getEmployeeUpdate()
    {
        return employeeUpdate;
    }

    public void setEmployeeUpdate(ScEmployee employeeUpdate)
    {
        this.employeeUpdate = employeeUpdate;
    }

    public List<ScPerson> getPersonsList()
    {
        return personsList;
    }

    public void setPersonsList(List<ScPerson> personsList)
    {
        this.personsList = personsList;
    }

    public List<ScEmployee> getEmployeesList()
    {
        return employeesList;
    }

    public void setEmployeesList(List<ScEmployee> employeesList)
    {
        this.employeesList = employeesList;
    }

    public ScEmployee getEmployeeSelected()
    {
        return employeeSelected;
    }

    public void setEmployeeSelected(ScEmployee employeeSelected)
    {
        this.employeeSelected = employeeSelected;
    }
    
    
}
