/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.beans.maintenance;

import com.sip.dmes.beans.SessionBean;
import com.sip.dmes.dao.bo.IOtMaintenanceCorrective;
import com.sip.dmes.entitys.OtMaintenance;
import com.sip.dmes.entitys.OtMaintenanceCorrective;
import com.sip.dmes.entitys.ScMachine;
import com.sip.dmes.entitys.ScMachinePart;
import com.sip.dmes.entitys.ScMaintenanceClasification;
import com.sip.dmes.entitys.ScMaintenanceDamage;
import com.sip.dmes.entitys.ScPriority;

import com.sip.dmes.utilities.DMESConstants;
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
public class OtmaintenanceCorrectiveBean
{
    
    private SessionBean sessionBean;
    private IOtMaintenanceCorrective otMaintenanceCorrectiveServer;
    private OtMaintenanceCorrective orderSave;
    private OtMaintenanceCorrective orderUpdate;
    private OtMaintenanceCorrective orderSelected;
    private List<ScMachine> listMachines;
    private ScMachine machineSave;
    private ScMachine machineUpdate;
    private List<ScMachinePart> listMachineParts;
    private List<ScMaintenanceClasification> listClasifications;
    private List<ScPriority> listPriority;
    private List<ScMaintenanceDamage> listDamage;
    
//    private IScPerson scPersonServer;
//    private ScPerson personAdd;
//    private ScPerson personUpdate;
//    private ScEmployee employeeAdd;
//    private ScEmployee employeeSelected;
//    private ScEmployee employeeUpdate;
//    private ScWorkExperience workExperienceAdd;
//    private ScWorkExperience workExperienceUpdate;
//    private ScCompetencies competenciesAdd;
//    private ScCompetencies competenciesUpdate;
    
//    private List<ScCompetencies> competenciesListAdd;
//    private List<ScWorkExperience> workExperiencesListAdd;
//    private List<ScPerson> personsList;
//    private List<ScPerson> personsListUpdate;
    private List<OtMaintenanceCorrective> correctiveList;
//    private List<ScTurn> turnList;
    private final static Logger log = Logger.getLogger(OtmaintenanceCorrectiveBean.class);
    
    
    private final String TAB_PERSON_SAVE = "tabPerson";
    private final String TAB_PERSON_UPDATE = "tabPersonUpdate";
    private final String TAB_CONFIRM_SAVE = "tabAcceptSave";
    private final String TAB_CONFIRM_UPDATE = "tabAcceptUpdate";
    private final String TAB_EMPLOYEE_SAVE = "tabEmployee";
    private final String TAB_EMPLOYEE_UPDATE = "tabEmployeeUpdate";
    

    /**
     * Creates a new instance of ScemployeesBean
     */
    public OtmaintenanceCorrectiveBean()
    {
        
    }
    
    
    @PostConstruct
    public void initData()
    {
        fillListCorrectives();
        cleanValues();
        fillListMachines();
        fillListClasification();
        fillListDamages();
        fillListPriorities();
    }   
    
    /**
     * Método encargado de carga la lista inicial de mantenimientos correctivos
     * @author Gustavo Chavarro Ortiz
    */
    public void fillListCorrectives()
    {
        if(getCorrectiveList()== null)
        {
            try
            {
                setCorrectiveList(getOtMaintenanceCorrectiveServer().getAllCorrectives());
            }
            catch (Exception e)
            {
                log.error("Error al intentar consutlar la lista de mantenimientos correctivos para visualizar", e);
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }
        }
    }
    
    /**
     * Método encargado de carga la lista inicial de máquinas
     * @author Gustavo Chavarro Ortiz
     */
    public void fillListMachines()
    {
        if(getListMachines()== null)
        {
            try
            {
                setListMachines(getOtMaintenanceCorrectiveServer().getAllMachines());
            }
            catch (Exception e)
            {
                log.error("Error al intentar consutlar la lista de máquinas para visualizar", e);
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }
        }
    }
    
    /**
     * Método encargado de carga la lista inicial de clasificaciones
     * @author Gustavo Chavarro Ortiz
     */
    public void fillListClasification()
    {
        if(getListClasifications()== null)
        { 
            try
            {
                setListClasifications(getOtMaintenanceCorrectiveServer().getAllClasifications());
            }
            catch (Exception e)
            {
                log.error("Error al intentar consutlar la lista de clasificaciones para visualizar", e);
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }
        }
    }
    
    /**
     * Método encargado de carga la lista inicial de prioridades
     * @author Gustavo Chavarro Ortiz
     */
    public void fillListPriorities()
    {
        if(getListPriority()== null)
        {
            try
            {
                setListPriority(getOtMaintenanceCorrectiveServer().getAllPriorities());
            }
            catch (Exception e)
            {
                log.error("Error al intentar consutlar la lista de prioridades para visualizar", e);
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }
        }
    }
    
    /**
     * Método encargado de carga la lista inicial de daños
     * @author Gustavo Chavarro Ortiz
     */
    public void fillListDamages()
    {
        if(getListDamage()== null)
        {
            try
            {
                setListDamage(getOtMaintenanceCorrectiveServer().getAllDamage());
            }
            catch (Exception e)
            {
                log.error("Error al intentar consutlar la lista de daños para visualizar", e);
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }
        }
    }
    
    
    
    /**
     * Método encargado de carga una lista de máquinas dependiendo de la máquina
     * que se ha seleccionado.
     * @param machine máquina a la que se le van a consultar las partes
     * @author Gustavo Chavarro Ortiz
     */
    public void fillListMachinePart(ScMachine machine)
    {
        try
        {
            setListMachineParts(getOtMaintenanceCorrectiveServer().getAllMachinePartByMachine(machine));
        }
        catch (Exception e)
        {
            log.error("Error al intentar consutlar la lista de partes de máquinas para visualizar", e);
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
        }
    }
    
    /**
     * Método encargado de limpiar los valores iniciales.
     * @author Gustavo Chavarro Ortiz
     */
    public void cleanValues()
    {
        setOrderSave(new OtMaintenanceCorrective());
        getOrderSave().setIdMaintenance(new OtMaintenance());
        getOrderSave().getIdMaintenance().setIdMachinePart(new ScMachinePart());
        
    }
    
    
    /**
     * Método encargado de habilitar o deshabilitar el combo para partes de máquina
     * @param machine que se evalua para ver si habilita el combo de partes de máquina
     * @return boolean variable que indica si deshabilita el combo box
     * @author Gustavo Chavarro Ortiz
     */
    public boolean isComboDisable(ScMachine machine)
    {
        boolean result = true;
        if(machine != null && machine.getName().length() > 0)
        {
            result = false;
        }
        return result;
    }
    
    /**
     * Método encargado de llevar el flujo al guardar un mantenimiento.
     *
     * @param event evento en el cual se encuentra el asistente para crear
     * herramientas
     * @return String al final retorna el nombre de la siguiente pestaña del
     * asistente
     * @author Gustavo Chavarro Ortiz
     */
    public String onFlowProcessSaveMaintenance(FlowEvent event)
    {
        
        
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

    public IOtMaintenanceCorrective getOtMaintenanceCorrectiveServer()
    {
        return otMaintenanceCorrectiveServer;
    }

    public void setOtMaintenanceCorrectiveServer(IOtMaintenanceCorrective otMaintenanceCorrectiveServer)
    {
        this.otMaintenanceCorrectiveServer = otMaintenanceCorrectiveServer;
    }

    public List<OtMaintenanceCorrective> getCorrectiveList()
    {
        return correctiveList;
    }

    public void setCorrectiveList(List<OtMaintenanceCorrective> correctiveList)
    {
        this.correctiveList = correctiveList;
    }

    public OtMaintenanceCorrective getOrderSave()
    {
        return orderSave;
    }

    public void setOrderSave(OtMaintenanceCorrective orderSave)
    {
        this.orderSave = orderSave;
    }

    public OtMaintenanceCorrective getOrderUpdate()
    {
        return orderUpdate;
    }

    public void setOrderUpdate(OtMaintenanceCorrective orderUpdate)
    {
        this.orderUpdate = orderUpdate;
    }

    public OtMaintenanceCorrective getOrderSelected()
    {
        return orderSelected;
    }

    public void setOrderSelected(OtMaintenanceCorrective orderSelected)
    {
        this.orderSelected = orderSelected;
    }

    public List<ScMachine> getListMachines()
    {
        return listMachines;
    }

    public void setListMachines(List<ScMachine> listMachines)
    {
        this.listMachines = listMachines;
    }

    public ScMachine getMachineSave()
    {
        return machineSave;
    }

    public void setMachineSave(ScMachine machineSave)
    {
        this.machineSave = machineSave;
    }

    public ScMachine getMachineUpdate()
    {
        return machineUpdate;
    }

    public void setMachineUpdate(ScMachine machineUpdate)
    {
        this.machineUpdate = machineUpdate;
    }

    public List<ScMachinePart> getListMachineParts()
    {
        return listMachineParts;
    }

    public void setListMachineParts(List<ScMachinePart> listMachineParts)
    {
        this.listMachineParts = listMachineParts;
    }

    public List<ScMaintenanceClasification> getListClasifications()
    {
        return listClasifications;
    }

    public void setListClasifications(List<ScMaintenanceClasification> listClasifications)
    {
        this.listClasifications = listClasifications;
    }

    public List<ScPriority> getListPriority()
    {
        return listPriority;
    }

    public void setListPriority(List<ScPriority> listPriority)
    {
        this.listPriority = listPriority;
    }

    public List<ScMaintenanceDamage> getListDamage()
    {
        return listDamage;
    }

    public void setListDamage(List<ScMaintenanceDamage> listDamage)
    {
        this.listDamage = listDamage;
    }

    
    
}
