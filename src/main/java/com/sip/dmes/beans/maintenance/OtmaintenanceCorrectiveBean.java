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
    private int months;
    private int days;
    private int hours;
    private int minutes;
    private ScMaintenanceActivity activitySave;
    private ScMaintenanceActivity activityUpdate;
    private List<OtMaintenanceCorrective> correctiveList;
    private List<ScEmployee> employeesList;
    private List<ScTool> listTools;
    private List<ScReplacement> listReplacements;
    private Date endDate;
    private String itemAdd;
    private int startHour;
    private int startMinutes;
    
    private final static Logger log = Logger.getLogger(OtmaintenanceCorrectiveBean.class);
    private final String TAB_GENERAL = "tabGeneral";
    private final String TAB_ACTIVITIES = "tabActivities";
    private final String TAB_EMPLOYEES = "tabEmployees";
    private final String TAB_REPLACEMENTS = "tabReplacements";
    private final String TAB_TOOLS = "tabTools";
    

    /**
     * Creates a new instance of OtmaintenanceCorrectiveBean
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
        fillListEmployees();
        fillListReplacements();
        fillListTools();
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
     * Método encargado de carga la lista inicial de empleados
     * @author Gustavo Chavarro Ortiz
     */
    public void fillListEmployees()
    {
        if(getEmployeesList()== null)
        {
            try
            {
                setEmployeesList(getOtMaintenanceCorrectiveServer().getAllEmployees());
            }
            catch (Exception e)
            {
                log.error("Error al intentar consutlar la lista de empleados para visualizar", e);
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }
        }
    }
    
    /**
     * Método encargado de carga la lista inicial de repuestos y consumibles
     * @author Gustavo Chavarro Ortiz
     */
    public void fillListReplacements()
    {
        if(getListReplacements()== null)
        {
            try
            {
                setListReplacements(getOtMaintenanceCorrectiveServer().getAllReplacements());
            }
            catch (Exception e)
            {
                log.error("Error al intentar consutlar la lista de repuestos y consumibles para visualizar", e);
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }
        }
    }
    
    /**
     * Método encargado de carga la lista inicial de herramientas
     * @author Gustavo Chavarro Ortiz
     */
    public void fillListTools()
    {
        if(getListTools()== null)
        {
            try
            {
                setListTools(getOtMaintenanceCorrectiveServer().getAllTools());
            }
            catch (Exception e)
            {
                log.error("Error al intentar consutlar la lista de herramientas para visualizar", e);
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }
        }
    }
    
    
    /**
     * Método encargado de carga una lista de máquinas dependiendo de la máquina
     * que se ha seleccionado.
     * @param machine máquina a la que se le van a consultar las partes
     * @author Gustavo Chavarro Ortiz
     * @param orderSave order a modificar el nombre
     */
    public void fillListMachinePart(ScMachine machine, OtMaintenanceCorrective orderSave)
    {
        orderSave.setName("Correctivo");
        orderSave.getIdMaintenance().setIdMachinePart(new ScMachinePart());
        if(machine != null)
        {
            try
            {
                setListMachineParts(getOtMaintenanceCorrectiveServer().getAllMachinePartByMachine(machine));
                orderSave.setName(orderSave.getName()+"_"+machine.getName());
            }
            catch (Exception e)
            {
                log.error("Error al intentar consutlar la lista de partes de máquinas para visualizar", e);
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }
        }        
    }
    
    /**
     * Método encargado de modificar el nombre del mantenimiento.
     * @param machinePart parte de máquina a la que se le van a consultar las partes
     * @author Gustavo Chavarro Ortiz
     * @param orderSave order a modificar el nombre
     */
    public void addPartMachineToName(ScMachinePart machinePart, OtMaintenanceCorrective orderSave)
    {
        orderSave.setName("Correctivo"+"_"+getMachineSave().getName());
        if(machinePart != null)
        {
            orderSave.setName(orderSave.getName()+"_"+machinePart.getName()+getFormatDateGlobal("yyyyMMddHHmmss", new Date()));
        }
    }
    
    
    /**
     * Método encargado de añadir los items seleccionados a la lista de la orden de mantenimiento.
     * @param item item que será agregado
     * @param replacementList lista de repuestos
     * @param maintenance orden de mantenimiento a la que se le añada el repuesto
     * @author Gustavo Chavarro Ortiz
     */
//    public void addItemToListReplacement(String item, List<ScMaintenanceReplacement> replacementList, OtMaintenance maintenance)
//    {
//        ScMaintenanceReplacement maintenanceReplacement = new ScMaintenanceReplacement();
//        //Validamos que el item no sea nulo
//        if(!Utilities.isEmpty(item))
//        {
//            //Obtengo el id y el nombre del item
//            int startPosition = item.indexOf("[");
//            String id = item.substring((startPosition+1), item.length()-1); //Id del repuesto
//            Long idReplacement = new Long(id);
//            String nameReplacement = item.substring(0, (startPosition-3)); //Nombre del repuesto
//            boolean exist = false;
//            
//            for(ScMaintenanceReplacement maintenanceReplacementItem: replacementList)
//            {
//                if(maintenanceReplacementItem.getIdReplacement().getIdReplacement().equals(idReplacement))
//                {
//                    exist = true;
//                    break;
//                }
//            }
//            if(!exist)
//            {
//                //Validamos que exista una lista de items
//                if(getListReplacements()!= null)
//                {
//                    for(ScReplacement replacement: getListReplacements())
//                    {
//                        if(replacement.getIdReplacement().equals(idReplacement))
//                        {
//                            maintenanceReplacement.setAmount(1L);
//                            maintenanceReplacement.setIdMaintenance(maintenance);
//                            maintenanceReplacement.setIdReplacement(replacement);
//                            replacementList.add(maintenanceReplacement);
//                            break;
//                        }
//                    }
//                }
//            }
//            else
//            {
//                log.error("Error el repuesto ya existe en la lista");
//                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, "Error el repuesto ya existe en la lista");
//            }
//        }
//        setItemAdd("");
//    }
    /**
     * Método encargado de eliminar un repuesto de la lista.
     * @param replacementList lista de repuestos
     * @param itemDelete repuesto a eliminar de la lista
     * @author Gustavo Chavarro
     */
//    public void deleteItemListReplacement(List<ScMaintenanceReplacement> replacementList, ScMaintenanceReplacement itemDelete)
//    {
//        if(replacementList != null && itemDelete != null)
//        {
//            if(itemDelete.getIdReplacement().getIdReplacement() != null)
//            {
//                int position =0;
//                for(ScMaintenanceReplacement maintenanceReplacement: replacementList)
//                {
//                    if(maintenanceReplacement.getIdReplacement().getIdReplacement().equals(itemDelete.getIdReplacement().getIdReplacement()))
//                    {
//                        break;
//                    }
//                    position++;
//                }
//                if(position < replacementList.size())
//                {
//                    replacementList.remove(position);
//                }
//            }
//            else
//            {
//                log.error("Error en el id del repuesto a eliminar");
//                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, "Error en el id del repuesto a eliminar");
//            }
//        }
//        else
//        {
//            log.error("El repuesto o la lista no pueden estar vacíos");
//            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
//        }
//    }
    
    /**
     * Método encargado de añadir los items seleccionados a la lista de la orden de mantenimiento.
     * @param item item que será agregado
     * @param toolList lista de herramientas
     * @param maintenance orden de mantenimiento a la que se le añada el herramienta
     * @author Gustavo Chavarro Ortiz
     */
//    public void addItemToListTool(String item, List<ScMaintenanceTool> toolList, OtMaintenance maintenance)
//    {
//        ScMaintenanceTool maintenanceTool = new ScMaintenanceTool();
//        //Validamos que el item no sea nulo
//        if(!Utilities.isEmpty(item))
//        {
//            //Obtengo el id y el nombre del item
//            int startPosition = item.indexOf("[");
//            String id = item.substring((startPosition+1), item.length()-1); //Id del herramienta
//            Long idTool = new Long(id);
//            String nameTool = item.substring(0, (startPosition-3)); //Nombre del herramienta
//            boolean exist = false;
//            
//            for(ScMaintenanceTool maintenanceToolItem: toolList)
//            {
//                if(maintenanceToolItem.getIdTool().getIdTool().equals(idTool))
//                {
//                    exist = true;
//                    break;
//                }
//            }
//            if(!exist)
//            {
//                //Validamos que exista una lista de items
//                if(getListTools()!= null)
//                {
//                    for(ScTool tool: getListTools())
//                    {
//                        if(tool.getIdTool().equals(idTool))
//                        {
//                            maintenanceTool.setAmount(1L);
//                            maintenanceTool.setIdMaintenance(maintenance);
//                            maintenanceTool.setIdTool(tool);
//                            toolList.add(maintenanceTool);
//                            break;
//                        }
//                    }
//                }
//            }
//            else
//            {
//                log.error("Error la herramienta ya existe en la lista");
//                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, "Error la herramienta ya existe en la lista");
//            }
//        }
//        setItemAdd("");
//    }
//    
    /**
     * Método encargado de eliminar una herramienta de la lista.
     * @param toolList lista herramientas
     * @param itemDelete herramienta a eliminar de la lista
     * @author Gustavo Chavarro
     */
//    public void deleteItemListTool(List<ScMaintenanceTool> toolList, ScMaintenanceTool itemDelete)
//    {
//        if(toolList != null && itemDelete != null)
//        {
//            if(itemDelete.getIdTool().getIdTool() != null)
//            {
//                int position =0;
//                for(ScMaintenanceTool maintenanceTool: toolList)
//                {
//                    if(maintenanceTool.getIdTool().getIdTool().equals(itemDelete.getIdTool().getIdTool()))
//                    {
//                        break;
//                    }
//                    position++;
//                }
//                if(position < toolList.size())
//                {
//                    toolList.remove(position);
//                }
//            }
//            else
//            {
//                log.error("Error en el id de la herramienta a eliminar");
//                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, "Error en el id de la herramienta a eliminar");
//            }
//        }
//        else
//        {
//            log.error("La herramienta o la lista no pueden estar vacíos");
//            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
//        }
//    }
    
    /**
     * Método encargado de limpiar los valores iniciales.
     * @author Gustavo Chavarro Ortiz
     */
    public void cleanValues()
    {
        setOrderSave(new OtMaintenanceCorrective());
        getOrderSave().setIdMaintenance(new OtMaintenance());
        getOrderSave().getIdMaintenance().setCreationDate(new Date());
        getOrderSave().getIdMaintenance().setIdMaintenanceState(new ScMaintenanceState(1L));
        getOrderSave().getIdMaintenance().setScMaintenanceActivityList(new ArrayList<ScMaintenanceActivity>());
        getOrderSave().getIdMaintenance().setIdMachinePart(new ScMachinePart());
        getOrderSave().getIdMaintenance().setIdWorkforce(new ScWorkforce());
        //getOrderSave().getIdMaintenance().setScMaintenanceReplacementList(new ArrayList<ScMaintenanceReplacement>());
        //getOrderSave().getIdMaintenance().setScMaintenanceToolList(new ArrayList<ScMaintenanceTool>());
        setMonths(0); setDays(0); setHours(0); setMinutes(0); setStartHour(0); setStartMinutes(0);
        setEndDate(null);
        setItemAdd("");
        setMachineSave(new ScMachine());
        setMachineUpdate(new ScMachine());
        setActivitySave(new ScMaintenanceActivity());
        setActivityUpdate(new ScMaintenanceActivity());
        
    }
    
    public void saveMaintenance()
    {
        try 
        {
            getOrderSave().getIdMaintenance().setDescription(getOrderSave().getDescription());
            getOtMaintenanceCorrectiveServer().saveMaintenance(getOrderSave(), getEndDate());
            getOrderSave().getIdMaintenance().getIdMachinePart().setIdMachine(getMachineSave());
            getCorrectiveList().add(getOrderSave());
            cleanValues();
            addInfo(null, DMESConstants.MESSAGE_TITTLE_SUCCES, DMESConstants.MESSAGE_SUCCES);
        }
        catch (Exception e)
        {
            log.error("Error intentando crear una nueva orden de mantenimiento",e);
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
        }
        
    
    }
    
    /**
     * Método encargardo de permitirle al usuario seleccionar un mantenimiento para visualizar
     * o para eliminar.
     * @param orderSelected mantenimiento a cargar
     * @author Gustavo Chavarro Ortiz
     */
    public void selectedForViewOrDelete(OtMaintenanceCorrective orderSelected)
    {
        if(orderSelected != null)
        {
            setOrderSelected(orderSelected);
        }
    }
    
    public void deleteMaintenance()
    {
        try 
        {
            
            getOtMaintenanceCorrectiveServer().deleteMaintenance(getOrderSelected());
            int index = 0;
            for(OtMaintenanceCorrective ot: getCorrectiveList())
            {
                if(ot.getIdMaintenanceCorrective().equals(getOrderSelected().getIdMaintenanceCorrective()))
                {
                    break;
                }
                index++;
            }
            if(index < getCorrectiveList().size())
            {
                getCorrectiveList().remove(index);
            }
            cleanValues();
            addInfo(null, DMESConstants.MESSAGE_TITTLE_SUCCES, DMESConstants.MESSAGE_SUCCES);
        }
        catch (Exception e)
        {
            log.error("Error intentando eliminar una orden de mantenimiento",e);
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
        }
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
//        && machine.getName().length() > 0
        if(machine != null )
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
        if (event.getOldStep().equals(TAB_GENERAL))
        {
            if (validateFields("Nombre Mantenimiento", getOrderSave().getName(), 3)) 
            {
                return event.getOldStep();
            }   
            if (validateFields("Máquina", getMachineSave(), 4))
            {
                return event.getOldStep();
            }   
            if (validateFields("Parte de Máquina", getOrderSave().getIdMaintenance().getIdMachinePart(), 4))
            {
                return event.getOldStep();
            }   
            if (validateFields("Clasificación", getOrderSave().getIdMaintenance().getIdMaintenanceClasification(), 4))
            {
                return event.getOldStep();
            }   
            if (validateFields("Prioridad", getOrderSave().getIdMaintenance().getIdPriority(), 4))
            {
                return event.getOldStep();
            }   
            if (validateFields("Daño", getOrderSave().getIdMaintenance().getIdMaintenanceDamage(), 4))
            {
                return event.getOldStep();
            }
            if (getMonths() == 0 && getDays() == 0 && getHours() == 0 && getMinutes() == 0)
            {
                addError(null, "Error en el Campo Duración", "Campo obligatorio, debe ingresar un valor para el campo Duración");
                return event.getOldStep(); 
            }
            if(getEndDate() != null)
            {
                getOrderSave().getIdMaintenance().setEndDate(getEndDate());
            }
        }
        else if(event.getOldStep().equals(TAB_ACTIVITIES))
        {   
            if(getOrderSave().getIdMaintenance().getScMaintenanceActivityList().isEmpty())
            {
                addError(null, "Error en el Campo Actividades", "Debe ingresar por lo menos una actividad");
                return event.getOldStep();
            }
        }
        else if(event.getOldStep().equals(TAB_EMPLOYEES))
        {
            if(validateFields("Empleado", getOrderSave().getIdMaintenance().getIdWorkforce().getIdEmployee(), 4))
            {
                return event.getOldStep();
            }
        }
        return event.getNewStep();
    }
    
    /**
     * Método encargado de adicionar una actividad en la lista de actividades
     * @param listActivities lista de actividades
     * @param activity actividad a incluir en la lista de actividades
     * @param idMaintenance id del mantenimiento asociado
     * @author Gustavo Chavarro Ortiz
     */
    public void saveActivity(List<ScMaintenanceActivity> listActivities, ScMaintenanceActivity activity, OtMaintenance idMaintenance)
    {
        if(listActivities != null && activity != null)
        {
            if(activity.getName() != null && activity.getName().length() > 0)
            {
                activity.setIdMaintenance(idMaintenance);
                listActivities.add(activity);
                setActivitySave(new ScMaintenanceActivity());
                setActivityUpdate(new ScMaintenanceActivity());
            }
            else
            {
                log.error("Debe ingresar el nombre de la actividad");
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, "Debe ingresar el nombre de la actividad");
            }
        }
        else
        {
            log.error("La actividad o la lista de actividades no puede ser nula");
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
        }
    }
    
    public void deleteActivity(List<ScMaintenanceActivity> listActivities, ScMaintenanceActivity activity)
    {
        if(listActivities != null && activity != null)
        {
            if(activity.getName() != null && activity.getName().length() > 0)
            {
                int position =0;
                for(ScMaintenanceActivity activityIndex: listActivities)
                {
                    if(activityIndex.getName().equals(activity.getName())
                            && activityIndex.getDescription().equals(activity.getDescription()))
                    {
                        break;
                    }
                    position++;
                }
                if(position < listActivities.size())
                {
                    listActivities.remove(position);
                }
            }
            else
            {
                log.error("Debe ingresar el nombre de la actividad");
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, "Debe ingresar el nombre de la actividad");
            }
        }
        else
        {
            log.error("La actividad o la lista de actividades no puede ser nula");
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
        }
    }
    
        
    /**
     * Método encargado de validar los campos doubles.
     *
     * @param nameField nombre del campo a evaluar
     * @param value valor del campo a evaluar
     * @param option opción del tipo de dato que se validar
     * @return boolean si se puede validar o no
     * @author Gustavo Chavarro Ortiz
     */
    public boolean validateFields(String nameField, Object value, int option)
    {
        boolean isInvalid = false;
        String message1 = "Error en el campo " + nameField;

        try
        {
            if (value != null && !Utilities.isEmpty(value.toString()))
            {
                switch (option)
                {
                    case 1: //Casos de tipo double 
                        String messageDouble2 = "Debe ingresar un número mayor que cero y usar"
                                + "como separador de decimales el punto, ejemplo: 3.24";
                        try
                        {

                            double parseo = Double.parseDouble((String) value);//parseo
                            if (parseo <= 0)
                            {
                                throw new Exception(nameField + " menor o igual a cero");
                            }
                        }
                        catch (Exception e)
                        {
                            isInvalid = true;
                            addError(null, message1, messageDouble2);
                            log.error(message1 + ", " + messageDouble2);
                        }
                        break;
                    case 2: //Casos de tipo int
                        String messageInt2 = "Debe ingresar un número mayor que cero sin puntos ni comas,"
                                + "ejemplo: 1256786";
                        try
                        {

                            Long parseo = Long.parseLong(value.toString());//parseo
                            if (parseo <= 0)
                            {
                                throw new Exception(nameField + " menor o igual a cero");
                            }
                        }
                        catch (Exception e)
                        {
                            isInvalid = true;
                            addError(null, message1, messageInt2);
                            log.error(message1 + ", " + messageInt2);
                        }
                        break;
                    case 3: //Casos de tipo String
                        String messageString2 = "Campo obligatorio, debe ingresar algún valor";
                        if (Utilities.isEmpty(value.toString()))
                        {
                            isInvalid = true;
                            addError(null, message1 + nameField, messageString2);
                            log.error(message1 + ", " + messageString2);
                        }
                        break;
                    case 4://Casos de campos seleccionables
                        String messageObject2 = "Campo obligatorio, debe seleccionar un valor para este campo";
                        if (value == null)
                        {
                            isInvalid = true;
                            addError(null, message1 + nameField, messageObject2);
                            log.error(message1 + ", " + messageObject2);
                        }
                        break;
                    default:
                        break;
                }
            }
            else
            {
                String messageObject2 = "Campo obligatorio, debe ingresar un valor para el campo " + nameField;
                addError(null, message1, messageObject2);
                log.error(message1 + ", " + messageObject2);
                isInvalid = true;
            }
        }
        catch (Exception e)
        {
            //Excepción no rematada debido que hay campos con tipos diferentes a String
        }
        return isInvalid;
    }
    
    /**
     * Método encargado de completar el item que se busca.
     * @param query palabra o indicio con cual buscar
     * @return List<String> lista de posibles opciones
     * @author Gustavo Chavarro Ortiz
     */
    public List<String> autocompleteMethodReplacement(String query)
    {
        List<String> result = new ArrayList<>();
        if(getListReplacements() != null && !getListReplacements().isEmpty())
        {
            for(ScReplacement replacement: getListReplacements())
            {
                if(replacement.getName().contains(query))
                {
                    result.add(replacement.getName()+" - "+replacement.getMark()+" - "+
                            replacement.getSerie()+" ["+replacement.getIdReplacement()+"]");
                }
            }
        }
        return result;
    }
    
    /**
     * Método encargado de completar el item que se busca.
     * @param query palabra o indicio con cual buscar
     * @return List<String> lista de posibles opciones
     * @author Gustavo Chavarro Ortiz
     */
    public List<String> autocompleteMethodTools(String query)
    {
        List<String> result = new ArrayList<>();
        if(getListTools()!= null && !getListTools().isEmpty())
        {
            for(ScTool tool: getListTools())
            {
                if(tool.getName().contains(query))
                {
                    result.add(tool.getName()+" - "+tool.getMark()+" - "+
                            tool.getSerie()+" ["+tool.getIdTool()+"]");
                }
            }
        }
        return result;
    }
    
    public void addTime(OtMaintenanceCorrective order)
    {
        if(order != null)
        {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.clear(Calendar.MINUTE);
            calendar.clear(Calendar.HOUR_OF_DAY);
            calendar.clear(Calendar.HOUR);
            calendar.clear(Calendar.SECOND);
            calendar.clear(Calendar.AM_PM);
            calendar.add(Calendar.HOUR, getStartHour());
            calendar.add(Calendar.MINUTE, getStartMinutes());
            order.getIdMaintenance().setCreationDate(calendar.getTime()); //Se acomoda la fecha inicial
            
            calendar.add(Calendar.MONTH, getMonths());
            calendar.add(Calendar.DAY_OF_YEAR, getDays());
            calendar.add(Calendar.HOUR, getHours());
            calendar.add(Calendar.MINUTE, getMinutes());
            setEndDate(calendar.getTime()); //Se acomoda la fecha final
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

    public int getMonths()
    {
        return months;
    }

    public void setMonths(int months)
    {
        this.months = months;
    }

    public int getDays()
    {
        return days;
    }

    public void setDays(int days)
    {
        this.days = days;
    }

    public int getHours()
    {
        return hours;
    }

    public void setHours(int hours)
    {
        this.hours = hours;
    }

    public int getMinutes()
    {
        return minutes;
    }

    public void setMinutes(int minutes)
    {
        this.minutes = minutes;
    }

    public ScMaintenanceActivity getActivitySave()
    {
        return activitySave;
    }

    public void setActivitySave(ScMaintenanceActivity activitySave)
    {
        this.activitySave = activitySave;
    }

    public ScMaintenanceActivity getActivityUpdate()
    {
        return activityUpdate;
    }

    public void setActivityUpdate(ScMaintenanceActivity activityUpdate)
    {
        this.activityUpdate = activityUpdate;
    }

    public List<ScEmployee> getEmployeesList()
    {
        return employeesList;
    }

    public void setEmployeesList(List<ScEmployee> employeesList)
    {
        this.employeesList = employeesList;
    }

    public List<ScTool> getListTools()
    {
        return listTools;
    }

    public void setListTools(List<ScTool> listTools)
    {
        this.listTools = listTools;
    }

    public List<ScReplacement> getListReplacements()
    {
        return listReplacements;
    }

    public void setListReplacements(List<ScReplacement> listReplacements)
    {
        this.listReplacements = listReplacements;
    }

    public String getItemAdd()
    {
        return itemAdd;
    }

    public void setItemAdd(String itemAdd)
    {
        this.itemAdd = itemAdd;
    }

    public int getStartHour()
    {
        return startHour;
    }

    public void setStartHour(int startHour)
    {
        this.startHour = startHour;
    }

    public int getStartMinutes()
    {
        return startMinutes;
    }

    public void setStartMinutes(int startMinutes)
    {
        this.startMinutes = startMinutes;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    
    
}
