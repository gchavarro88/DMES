/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.beans.production;

import com.sip.dmes.beans.SessionBean;
import com.sip.dmes.dao.bo.IOtProduction;
import com.sip.dmes.entitys.OtProductionOrder;
import com.sip.dmes.entitys.ScProductFormulation;
import com.sip.dmes.entitys.ScProductionState;
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

/**
 *
 * @author gchavarro88
 */
public class OtproductionBean
{
    
    private SessionBean sessionBean;
    private IOtProduction otProductionServer;
    
    private final static Logger log = Logger.getLogger(OtproductionBean.class);
    private final String TAB_GENERAL = "tabGeneral";
    private final String TAB_ACTIVITIES = "tabActivities";
    private final String TAB_EMPLOYEES = "tabEmployees";
    private final String TAB_REPLACEMENTS = "tabReplacements";
    private final String TAB_TOOLS = "tabTools";
    
    private Date filterStarDate;
    private Date filterEndDate;
    private Long filterOrderNumber;
    private ScProductionState filteState;
    private List<ScProductionState> listState;
    private List<OtProductionOrder> listProductionOrders;
    private OtProductionOrder orderSave;
    private OtProductionOrder orderUpdate;
    private OtProductionOrder orderSelected;
    private List<ScProductFormulation> listProductFormulations;
    
    /**
     * Creates a new instance of OtmaintenanceCorrectiveBean
     */
    public OtproductionBean()
    {
        
    }
    
    
    @PostConstruct
    public void initData()
    {
        fillListProductionState();
        fillListProductionOrders();
        fillListProductFormulations();
    }   
    
    /**
     * Método encargado de carga la lista inicial de estados de producción
     * @author Gustavo Chavarro Ortiz
    */
    public void fillListProductionState()
    {
        if(getListState()== null)
        {
            try
            {
                setListState(getOtProductionServer().getListStates());
            }
            catch (Exception e)
            {
                log.error("Error al intentar consutlar la lista de estados de producción", e);
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }
        }
    }
    
    
    /**
     * Método encargado de carga la lista inicial de ordenes de producción
     * @author Gustavo Chavarro Ortiz
    */
    public void fillListProductionOrders()
    {
        if(getListProductionOrders()== null)
        {
            try
            {
                setListProductionOrders(getOtProductionServer().getListProductionOrders());
            }
            catch (Exception e)
            {
                log.error("Error al intentar consutlar la lista de ordenes de producción", e);
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }
        }
    }
    
    /**
     * Método encargado de carga la lista inicial de productos
     * @author Gustavo Chavarro Ortiz
    */
    public void fillListProductFormulations()
    {
        if(getListProductFormulations()== null)
        {
            try
            {
                setListProductFormulations(getOtProductionServer().getListProductFormulations());
            }
            catch (Exception e)
            {
                log.error("Error al intentar consutlar la lista de productos", e);
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }
        }
    }
    
    /**
     * Método encargado de limpiar los valores iniciales.
     * @author Gustavo Chavarro Ortiz
     */
    public void cleanValues()
    {
        setOrderSave(new OtProductionOrder());
        setOrderSelected(new OtProductionOrder());
        setOrderUpdate(new OtProductionOrder());
        
    }
    
    
    /**
     * Método encargado de realizar la búsqueda teniendo encuenta los filtros.
     * @author Gustavo Chavarro Ortiz
     */
    public void doSearchWithParameters()
    {
        try
        {   
            setPreventiveList(getOtMaintenancePreventiveServer().getMaintenanceByParameters(getFilterStarDate()
                    , getFilterEndDate(), getClasification(), getMaintenanceState()));
            addInfo(null, DMESConstants.MESSAGE_TITTLE_SUCCES, DMESConstants.MESSAGE_SUCCES);
        }
        catch (Exception e)
        {
            addInfo(null, DMESConstants.MESSAGE_TITTLE_SUCCES, DMESConstants.MESSAGE_SUCCES);
            log.error("Error al intentar consultar las ordenes de mantenimiento con parámetros", e);
        }
    }
    
//    /**
//     * Método encargado de llevar el flujo al guardar un mantenimiento.
//     *
//     * @param event evento en el cual se encuentra el asistente para crear
//     * herramientas
//     * @return String al final retorna el nombre de la siguiente pestaña del
//     * asistente
//     * @author Gustavo Chavarro Ortiz
//     */
//    public String onFlowProcessSaveMaintenance(FlowEvent event)
//    {
//        if (event.getOldStep().equals(TAB_GENERAL))
//        {
//            if (validateFields("Nombre Mantenimiento", getOrderSave().getName(), 3)) 
//            {
//                return event.getOldStep();
//            }   
//            if (validateFields("Máquina", getMachineSave(), 4))
//            {
//                return event.getOldStep();
//            }   
//            if (validateFields("Parte de Máquina", getOrderSave().getIdMaintenance().getIdMachinePart(), 4))
//            {
//                return event.getOldStep();
//            }   
//            if (validateFields("Clasificación", getOrderSave().getIdMaintenance().getIdMaintenanceClasification(), 4))
//            {
//                return event.getOldStep();
//            }   
//            if (validateFields("Prioridad", getOrderSave().getIdMaintenance().getIdPriority(), 4))
//            {
//                return event.getOldStep();
//            }   
//            if (validateFields("Daño", getOrderSave().getIdMaintenance().getIdMaintenanceDamage(), 4))
//            {
//                return event.getOldStep();
//            }
//            if (getMonths() == 0 && getDays() == 0 && getHours() == 0 && getMinutes() == 0)
//            {
//                addError(null, "Error en el Campo Duración", "Campo obligatorio, debe ingresar un valor para el campo Duración");
//                return event.getOldStep(); 
//            }
//            if(getEndDate() != null)
//            {
//                getOrderSave().getIdMaintenance().setEndDate(getEndDate());
//            }
//        }
//        else if(event.getOldStep().equals(TAB_ACTIVITIES))
//        {   
//            if(getOrderSave().getIdMaintenance().getScMaintenanceActivityList().isEmpty())
//            {
//                addError(null, "Error en el Campo Actividades", "Debe ingresar por lo menos una actividad");
//                return event.getOldStep();
//            }
//        }
//        else if(event.getOldStep().equals(TAB_EMPLOYEES))
//        {
//            if(validateFields("Empleado", getOrderSave().getIdMaintenance().getIdWorkforce().getIdEmployee(), 4))
//            {
//                return event.getOldStep();
//            }
//        }
//        return event.getNewStep();
//    }
    
    
        
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
     * Método encargado de reiniciar los valores de los filtros.
     */ 
    public void resetData()
    {
//        setPreventiveList(null);
        initData();
        setFilterEndDate(null);
        setFilterStarDate(null);
//        setClasification(null);
//        setMaintenanceState(null);
    }
    
    /**
     * Método encargado de realizar la validación de las fechas.
     * @author Gustavo Chavarro Ortiz
     */
    public void compareToDates()
    {
        if((getFilterStarDate()!= null && getFilterEndDate()!= null) && (getFilterStarDate().after(getFilterEndDate()))) 
        {        
            addError(null, "Validación de Fechas", "La fecha inicial debe ser menor o igual a la fecha final");
            setFilterStarDate(null);
            setFilterEndDate(null);
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

    public Date getFilterStarDate()
    {
        return filterStarDate;
    }

    public void setFilterStarDate(Date filterStarDate)
    {
        this.filterStarDate = filterStarDate;
    }

    public Date getFilterEndDate()
    {
        return filterEndDate;
    }

    public void setFilterEndDate(Date filterEndDate)
    {
        this.filterEndDate = filterEndDate;
    }

    public Long getFilterOrderNumber()
    {
        return filterOrderNumber;
    }

    public void setFilterOrderNumber(Long filterOrderNumber)
    {
        this.filterOrderNumber = filterOrderNumber;
    }

    public ScProductionState getFilteState()
    {
        return filteState;
    }

    public void setFilteState(ScProductionState filteState)
    {
        this.filteState = filteState;
    }

    public List<ScProductionState> getListState()
    {
        return listState;
    }

    public void setListState(List<ScProductionState> listState)
    {
        this.listState = listState;
    }

    public List<OtProductionOrder> getListProductionOrders()
    {
        return listProductionOrders;
    }

    public void setListProductionOrders(List<OtProductionOrder> listProductionOrders)
    {
        this.listProductionOrders = listProductionOrders;
    }

    public OtProductionOrder getOrderSave()
    {
        return orderSave;
    }

    public void setOrderSave(OtProductionOrder orderSave)
    {
        this.orderSave = orderSave;
    }

    public OtProductionOrder getOrderUpdate()
    {
        return orderUpdate;
    }

    public void setOrderUpdate(OtProductionOrder orderUpdate)
    {
        this.orderUpdate = orderUpdate;
    }

    public OtProductionOrder getOrderSelected()
    {
        return orderSelected;
    }

    public void setOrderSelected(OtProductionOrder orderSelected)
    {
        this.orderSelected = orderSelected;
    }

    public List<ScProductFormulation> getListProductFormulations()
    {
        return listProductFormulations;
    }

    public void setListProductFormulations(List<ScProductFormulation> listProductFormulations)
    {
        this.listProductFormulations = listProductFormulations;
    }
    
    
    
    

}
