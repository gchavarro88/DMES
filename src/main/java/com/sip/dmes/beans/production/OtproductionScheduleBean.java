/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.beans.production;

import com.sip.dmes.beans.SessionBean;
import com.sip.dmes.dao.bo.IOtProduction;
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
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author gchavarro88
 */
public class OtproductionScheduleBean
{
    
    private SessionBean sessionBean;
    private IOtProduction otProductionServer;
    
    private ScheduleModel productionSchedule;
    private ScheduleEvent event;
    private final static Logger log = Logger.getLogger(OtproductionScheduleBean.class);
    private final String TAB_GENERAL = "tabGeneral";
    private final String TAB_ACTIVITIES = "tabActivities";
    private final String TAB_EMPLOYEES = "tabEmployees";
    private final String TAB_REPLACEMENTS = "tabReplacements";
    private final String TAB_TOOLS = "tabTools";
    
       
    private Date filterStarDate;
    private Date filterEndDate;
    private String filterOrderNumber;
    private ScProductionState filteState;
    private List<ScProductionState> listState;
    private List<OtProductionOrder> listProductionOrders;
    private OtProductionOrder orderSave;
    private OtProductionOrder orderUpdate;
    private OtProductionOrder orderSelected;
    private List<ScProductFormulation> listProductFormulations;
    private List<String> elementsAutocomplete;
    private int hourStart;
    private int minuteStar;
    private int hourEnd;
    private int minuteEnd;
    private String productAdd;
    private Long amount;
    private Date currentDate;
    private ScProductOrder productFormulationSelected;
    private ScProccesProductOrder processProductSelected;
    private ScProcessEmployee processEmployeeSelected;
    private ScProcessInput processInputSelected;
    private ScProcessMachine processMachineSelected;
    private List<ScProccesProductOrder> listProcessProduct;
    private List<ScProcessEmployeeOrder> listProcessEmployee;
    private List<ScProcessInputOrder> listProcessInput;
    private List<ScProcessMachineOrder> listProcessMachine;

    /**
     * Creates a new instance of OtmaintenanceCorrectiveBean
     */
    public OtproductionScheduleBean()
    {
        
    }
    
    
    @PostConstruct
    public void initData()
    {
        cleanValues();
        fillListProductionState();
        fillListProductFormulations();
        fillListListAutocomplete();
        
        setEvent(new DefaultScheduleEvent());
        productionSchedule = new LazyScheduleModel() 
        {
             
            @Override
            public void loadEvents(Date start, Date end) 
            {
                try
                {
                    List<OtProductionOrder> listDates = getOtProductionServer().getProductionByParameters(start, end, null, null);
                    if(listDates != null && !listDates.isEmpty())
                    {
                        for(OtProductionOrder productionOrder: listDates)
                        {
                            if(productionOrder != null)
                            {
                                addEvent(new DefaultScheduleEvent("OP"+getFormatDateGlobal
                                ("yyyyMMddHHmmss", productionOrder.getStartDate())+productionOrder.getIdProductionOrder(), 
                                        productionOrder.getStartDate(), productionOrder.getEndDate()));
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
        setOrderSave(new OtProductionOrder());
        getOrderSave().setIdProductionState(new ScProductionState(1L));
        getOrderSave().setScProductOrderList(new ArrayList<ScProductOrder>());
        getOrderSave().setCreationDate(new Date());
        getOrderSave().setStartDate(new Date());
        setCurrentDate(new Date());       
        setOrderSelected(new OtProductionOrder());
        setOrderUpdate(new OtProductionOrder());
        setProductAdd("");
        setMinuteEnd(0);
        setMinuteStar(0);
        setHourEnd(0);
        setHourStart(0);
        setAmount(new Long(0));
        setProductAdd("");
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
     * Método encargado de carga la lista de autocompletar productos
     * @author Gustavo Chavarro Ortiz
    */
    public void fillListListAutocomplete()
    {
        if(getListProductFormulations()!= null)
        {
            setElementsAutocomplete(new ArrayList<String>());
            try
            { 
                if(getListProductFormulations() != null && !getListProductFormulations().isEmpty())
                {
                    for(ScProductFormulation product: getListProductFormulations())
                    {
                        if(product != null && product.getDescription().length() > 0)
                        {
                            getElementsAutocomplete().add(product.getIdProductFormulation()+"-"+product.getDescription());
                        }
                    }
                }
                
            }
            catch (Exception e)
            {
                log.error("Error al intentar cargar la lista de productos", e);
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }
        }
    }
    
    /**
     * Método encargado de llevar el flujo al guardar una orden.
     * @param event evento en el cual se encuentra el asistente para crear
     * ordenes de producción
     * @return String al final retorna el nombre de la siguiente pestaña del
     * asistente
     * @author Gustavo Chavarro Ortiz
     */
    public String onFlowProcessSaveProduction(FlowEvent event)
    {
        if (event.getOldStep().equals(TAB_GENERAL))
        {
            if (validateFields("Nombre Orden", getOrderSave().getName(), 3)) 
            {
                return event.getOldStep();
            }   
            if (getOrderSave().getScProductOrderList() == null || getOrderSave().getScProductOrderList().isEmpty())
            {
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, "Debe seleccionar al menos un producto");
                return event.getOldStep();
            }   
        }
        return event.getNewStep();
    }
    
    /**
     * Método encargado de llevar el flujo al actualizar una orden.
     * @param event evento en el cual se encuentra el asistente para crear
     * ordenes de producción
     * @return String al final retorna el nombre de la siguiente pestaña del
     * asistente
     * @author Gustavo Chavarro Ortiz
     */
    public String onFlowProcessUpdateProduction(FlowEvent event)
    {
        if (event.getOldStep().equals(TAB_GENERAL))
        {
            if (validateFields("Nombre Orden", getOrderUpdate().getName(), 3)) 
            {
                return event.getOldStep();
            }   
            if (getOrderUpdate().getScProductOrderList() == null || getOrderUpdate().getScProductOrderList().isEmpty())
            {
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, "Debe seleccionar al menos un producto");
                return event.getOldStep();
            }   
        }
        return event.getNewStep();
    }
    
    /**
     * Método encargado de visualizar la imagen de un elemento.
     * @return String cadena con la ruta de la imagen
     * @param pathPicture  producto al que se le consultará la imagen
     * @author Gustavo Chavarro Ortiz
     */
    public String searchImage(String pathPicture)
    {
        try
        {
            
            if(!Utilities.isEmpty(pathPicture))
            {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(pathPicture)));
                //la constante me permite mapear imagenes externas
                return DMESConstants.PATH_EXTERN_PICTURES+pathPicture;
            }

        }
        catch (Exception e)
        {
            addError(null, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR, "La imagen no existe");
        }
        return DMESConstants.PATH_IMAGE_DEFAULT;
    }
    
    /************************************************************************
     *                 Seleccionar para los diferentes eventos              *
     ************************************************************************/
    
    /**
     * Método encargado de seleccionar los procesos de un producto.
     * @param product formulación de producto seleccionada por el usuario
     * @author Gustavo Chavarro Ortiz
     */
    public void selectProcessByProduct(ScProductOrder product)
    {
        if(product != null) 
        {
            
                if(product.getScProccesProductOrderList() != null)
                {
                    setListProcessProduct(product.getScProccesProductOrderList());
                    setProductFormulationSelected(product);
                }
        }
    }
    /**
     * Método encargado de seleccionar los procesos de un proceso de producto seleccionado.
     * @param process proceso de producto seleccionado por el usuario.
     * @author Gustavo Chavarro Ortiz
     */
    public void selectProcessByProcess(ScProccesProductOrder process)
    {
        if(process != null)
        {
            setListProcessEmployee(process.getScProcessEmployeeOrderList());
            setListProcessInput(process.getScProcessInputOrderList());
            setListProcessMachine(process.getScProcessMachineOrderList());
            setProcessProductSelected(process);
        }
    }
    
    /**
     * Método encargado de seleccionar un empleado de un proceso de empleados.
     * @param employee empleado seleccionado por el usuario desde un proceso
     * @author Gustavo Chavarro Ortiz
     */
    public void selectProcessEmployeeByProcess(ScProcessEmployee employee)
    {
        if(employee != null)
        {
            setProcessEmployeeSelected(employee);
        }
    }
    
    /**
     * Método encargado de seleccionar un insumo de un proceso de insumos.
     * @param input insumo seleccionado por el usuario desde un proceso
     * @author Gustavo Chavarro Ortiz
     */
    public void selectProcessInputByProcess(ScProcessInput input)
    {
        if(input != null)
        {
            setProcessInputSelected(input);
        }
    }
    
    /**
     * Método encargado de seleccionar una máquina de un proceso de máquinas.
     * @param machine máquina seleccionado por el usuario desde un proceso
     * @author Gustavo Chavarro Ortiz
     */
    public void selectProcessMachineByProcess(ScProcessMachine machine)
    {
        if(machine != null)
        {
            setProcessMachineSelected(machine);
        }
    }
    
    /**
     * Método encargado de completar el item que se busca.
     * @param query palabra o indicio con cual buscar
     * @return List<String> lista de posibles opciones
     * @author Gustavo Chavarro Ortiz
     */
    public List<String> autocompleteMetod(String query)
    {
        List<String> result = new ArrayList<>();
        if(getElementsAutocomplete() != null && !getElementsAutocomplete().isEmpty())
        {
            for(String word: getElementsAutocomplete())
            {
                if(word.contains(query))
                {
                    result.add(word);
                }
            }
        }
        return result;
    }
  
    /**
     * Método encargado de recalcular la fecha de finalización.
     * @param order orden de producción
     * @author Gustavo Chavarro Ortiz
     */
    public void calculateEndDate(OtProductionOrder order)
    {
        Calendar calendar = Calendar.getInstance();
        
        if(order != null)
        {
            calendar.setTime(order.getStartDate());
            if(order.getScProductOrderList() != null && !order.getScProductOrderList().isEmpty())
            {
                for(ScProductOrder productOrder: order.getScProductOrderList())
                {
                    if(productOrder != null
                            && productOrder.getScProccesProductOrderList() != null
                            && !productOrder.getScProccesProductOrderList().isEmpty())
                    {
                        for(ScProccesProductOrder process: productOrder.getScProccesProductOrderList())
                        {
                            if(process != null)
                            {
                                calendar.add(Calendar.MINUTE, (int) (process.getTotalTimeProcess() 
                                        * productOrder.getAmountRequired()));
                            }
                        }
                        
                    }
                }
            }
            order.setEndDate(calendar.getTime());
        }
    }
    
    /**
     * Método encargado de añadir los items seleccionados a la lista de la requisición.
     * @param list lista de productos que se agregaran a al orden
     * @param order orden a guardar
     * @author Gustavo Chavarro Ortiz
     */
    public void addProductsToOrder(List<ScProductOrder> list, OtProductionOrder order)
    {
        //Validamos que el item no sea nulo
        if(!Utilities.isEmpty(getProductAdd()) && !Utilities.isEmpty(getAmount()+""))
        {
            //Validamos que exista una lista de items 
            if(list != null)
            {
                String fields[] = getProductAdd().split("-"); //Separamos el id del item
                if(fields[0] != null && fields[0].length() > 0)
                {
                    Long idProduct = new Long(fields[0]);
                    for(ScProductFormulation product: getListProductFormulations())
                    {
                        if(idProduct.equals(product.getIdProductFormulation()))
                        {
                            boolean exists = false;
                            for(ScProductOrder productOrder: list)
                            {
                                if(product.getIdProductFormulation().equals(productOrder.getIdProductFormulation()))
                                {
                                    exists = true;
                                    break;
                                }
                            }
                            if(!exists)
                            {
                                if(getAmount() != null && getAmount() > 0)
                                {
                                    ScProductOrder newProduct = new ScProductOrder();
                                    newProduct = copyProductInOrder(newProduct, product, order, getAmount());
                                    list.add(newProduct);
                                    calculateEndDate(order);
                                    break;
                                }
                                else
                                {
                                    log.error("La cantidad debe ser mayor que cero");
                                    addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, "La cantidad debe ser mayor que cero");
                                }
                            }
                            else
                            {
                                    log.error("El producto ya fue agregado a la lista");
                                    addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, "El producto ya fue agregado a la lista");
                            }
                        }
                    }
                }
                //Reseteamos el item a pedir
                setProductAdd("");
                setAmount(0L);
                
            }
            else
            {
                log.error("Error la lista de productos es nula");
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }
        }
        else
        {
            log.error("Error producto no seleccionado");
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR,"Debe seleccionar un producto");
        }
    }
    
    /**
     * Método encargado de copiar un producto en una orden.
     * @param newProduct 
     * @param product
     * @param order
     * @param amountProcess
     * @return ScProductOrder
     * @author Gustavo Chavarro Ortiz
     */
    public ScProductOrder copyProductInOrder(ScProductOrder newProduct, ScProductFormulation product,
            OtProductionOrder order, Long amountProcess)
    {
        newProduct.setIdProductFormulation(product.getIdProductFormulation());
        newProduct.setAmountRequired(getAmount());
        newProduct.setCreationDate(product.getCreationDate());
        newProduct.setDescription(product.getDescription());
        newProduct.setExpiryDate(product.getExpiryDate());
        newProduct.setIdCostCenter(product.getCostCenter());
        newProduct.setIdLocation(product.getLocation());
        newProduct.setIdMoney(product.getMoney());
        newProduct.setIdOrder(order);
        newProduct.setIdPacking(product.getPackingUnit());
        newProduct.setIdPriority(product.getPriority());
        newProduct.setIdProductDimension(product.getDimension());
        newProduct.setManufacturingTime(product.getManufacturingTime());
        newProduct.setMark(product.getMark());
        newProduct.setPathPicture(product.getPathPicture());
        newProduct.setSerie(product.getSerie());
        newProduct.setTypeMaterial(product.getTypeMaterial());
        newProduct.setValue(product.getValue());
        
        //Copiamos los procesos
        List<ScProccesProductOrder> processProductOrderList = new ArrayList<>();
        for(ScProcessProduct processProduct: product.getProcessProducts())
        {
            ScProccesProductOrder proccesProductOrder = new ScProccesProductOrder();
            List<ScProcessMachineOrder> processMachineOrderList = new ArrayList<>();
            //Primero copiamos los procesos maquina
            for(ScProcessMachine processMachine: processProduct.getProcessMachines())
            {
                ScProcessMachineOrder processMachineOrder = new ScProcessMachineOrder();
                processMachineOrder.setDescriptionOtherExpenses(processMachine.getDescriptionOtherExpenses());
                processMachineOrder.setIdMachine(processMachine.getMachine());
                processMachineOrder.setIdProcessOrder(proccesProductOrder);
                processMachineOrder.setOtherExpenses(processMachine.getOtherExpenses());
                processMachineOrder.setTimeUse(processMachine.getTimeUse());
                processMachineOrder.setTotalValueMachine(processMachine.getTotalValueMachine());
                processMachineOrderList.add(processMachineOrder);
            }
            List<ScProcessInputOrder> processInputOrderList = new ArrayList<>();
            //Segundo copiamos los procesos de insumo
            for(ScProcessInput processInput: processProduct.getProcessInputs())
            {
                ScProcessInputOrder processInputOrder = new ScProcessInputOrder();
                processInputOrder.setAmountDistribution(processInput.getAmountDistribution());
                processInputOrder.setIdInput(processInput.getInput());
                processInputOrder.setIdProcessOrder(proccesProductOrder);
                processInputOrder.setPercentageResidue(processInput.getPercentageResidue());
                processInputOrder.setTotalValueInput(processInput.getTotalValueInput());
                processInputOrderList.add(processInputOrder);
            }
            List<ScProcessEmployeeOrder> processEmployeeOrderList = new ArrayList<>();
            //Tercero copiamos los procesos de empleados
            for(ScProcessEmployee processEmployee: processProduct.getProcessEmployees())
            {
                ScProcessEmployeeOrder processEmployeeOrder = new ScProcessEmployeeOrder();
                processEmployeeOrder.setDescriptionOtherExpenses(processEmployee.getDescriptionOtherExpenses());
                processEmployeeOrder.setIdEmployee(processEmployee.getEmployee());
                processEmployeeOrder.setIdProcessOrder(proccesProductOrder);
                processEmployeeOrder.setLaborDescription(processEmployee.getLaborDescription());
                processEmployeeOrder.setOtherExpenses(processEmployee.getOtherExpenses());
                processEmployeeOrder.setTimeUse(processEmployee.getTimeUse());
                processEmployeeOrder.setTotalValueEmployee(processEmployee.getTotalValueEmployee());
                processEmployeeOrderList.add(processEmployeeOrder);
            }
            //Copiamos las caracteristicas del proceso
            proccesProductOrder.setAmountProduced(amountProcess);
            proccesProductOrder.setDescription(processProduct.getDescription());
            proccesProductOrder.setIdProcessState(new ScProductionState(1L));
            proccesProductOrder.setIdProcessType(processProduct.getProcessType());
            proccesProductOrder.setIdProductOrder(newProduct);
            proccesProductOrder.setName(processProduct.getName());
            proccesProductOrder.setScProcessEmployeeOrderList(processEmployeeOrderList);
            proccesProductOrder.setScProcessInputOrderList(processInputOrderList);
            proccesProductOrder.setScProcessMachineOrderList(processMachineOrderList);
            proccesProductOrder.setTotalTimeEmployee(processProduct.getTotalTimeEmployee());
            proccesProductOrder.setTotalTimeMachine(processProduct.getTotalTimeMachine());
            proccesProductOrder.setTotalTimeProcess(processProduct.getTotalTimeProcess());
            proccesProductOrder.setTotalValueEmployee(processProduct.getTotalValueEmployee());
            proccesProductOrder.setTotalValueInput(processProduct.getTotalValueInput());
            proccesProductOrder.setTotalValueMachine(processProduct.getTotalValueMachine());
            proccesProductOrder.setTotalValueProcess(processProduct.getTotalValueProcess());
            processProductOrderList.add(proccesProductOrder);
        }
        newProduct.setScProccesProductOrderList(processProductOrderList);
        return newProduct;
    }
    
    /**
     * Método encargardo de permitirle al usuario seleccionar una orden de producción para visualizar
     * o para eliminar.
     * @param orderSelected orden de produccón
     * @author Gustavo Chavarro Ortiz
     */
    public void selectedForViewOrDelete(OtProductionOrder orderSelected)
    {
        cleanValues();
        if(orderSelected != null)
        {
            setOrderSelected(orderSelected); 
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
     * Método encargado de guardar una orden de producción.
     * @author Gustavo Chavarro Ortiz
     */
    public void saveOrderProduction()
    {
        if(getOrderSave() != null)
        {
            try
            {
                if(getOrderSave().getScProductOrderList() != null && !getOrderSave().getScProductOrderList().isEmpty())
                {
                    for(ScProductOrder productOrder: getOrderSave().getScProductOrderList())
                    {
                        productOrder.setIdOrder(getOrderSave());
                    }
                }
                getOtProductionServer().saveOrderProduction(getOrderSave());
                for(ScProductionState state: getListState())
                {
                    if(getOrderSave().getIdProductionState().getIdProductionState().equals(state.getIdProductionState()))
                    {
                        getOrderSave().setIdProductionState(state);
                        break;
                    }
                }
                addInfo(null, DMESConstants.MESSAGE_TITTLE_SUCCES, DMESConstants.MESSAGE_SUCCES);
            }
            catch (Exception e)
            {
                log.error("Error al realizar el guardado de la orden de producción", e);
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }
        }
    
    }
    
    /**
     * Método encargado de eliminar una orden de producción.
     * @author Gustavo Chavarro Ortiz
     */
    public void deleteOrderProduction()
    {
        if(getOrderSelected() != null)
        {
            try
            {
                if(getOrderSelected().getIdProductionState().getIdProductionState().equals(1L))
                {
                    getOtProductionServer().deleteOrderProduction(getOrderSelected());
                    int position = 0;
                    for(OtProductionOrder productionOrder: getListProductionOrders())
                    {
                       if(productionOrder.getIdProductionOrder().equals(getOrderSelected().getIdProductionOrder()))
                       {
                           break;
                       } 
                       else
                       {
                           position++;
                       }
                    }
                    if(position < getListProductionOrders().size())
                    {
                        getListProductionOrders().remove(position);
                        addInfo(null, DMESConstants.MESSAGE_TITTLE_SUCCES, DMESConstants.MESSAGE_SUCCES);
                    }
                }
                else
                {
                    log.error("La orden tiene un estado que no se puede actulizar");
                    addError(null, "La Orden de Producción no se puede actualizar", "El Estado debe estar en PENDIENTE");
                }
                
            }
            catch (Exception e)
            {
                log.error("Error al realizar la eliminación de la orden de producción", e);
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }
        }
    
    }
    
    /**
     * Método encargado de guardar una orden de producción.
     * @author Gustavo Chavarro Ortiz
     */
    public void updateOrderProduction()
    {
        if(getOrderUpdate()!= null)
        {
            try
            {
                if(getOrderUpdate().getIdProductionState().getIdProductionState().equals(1L))
                {
                    if(getOrderUpdate().getScProductOrderList() != null && !getOrderUpdate().getScProductOrderList().isEmpty())
                    {
                        for(ScProductOrder productOrder: getOrderUpdate().getScProductOrderList())
                        {
                            productOrder.setIdOrder(getOrderUpdate());
                        }
                    }
                    getOtProductionServer().updateOrderProduction(getOrderUpdate());
                    addInfo(null, DMESConstants.MESSAGE_TITTLE_SUCCES, DMESConstants.MESSAGE_SUCCES);
                }
                else
                {
                    log.error("La orden tiene un estado que no se puede actulizar");
                    addError(null, "La Orden de Producción no se puede actualizar", "El Estado debe estar en PENDIENTE");
                }
            }
            catch (Exception e)
            {
                log.error("Error al realizar la actualización de la orden de producción", e);
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }
        }
    
    }
    
    /**
     * Método encargado de contar el precio de costo total de la orden
     * @param order orden de producción que se le contarán los productos
     * @return double cantidad de productos
     * @author Gustavo Chavarro Ortiz
     */
    public double getCountCostOrdenProduction(OtProductionOrder order)
    {
        double result = 0;
        if(order != null)
        {
            if(order.getScProductOrderList()!= null && !order.getScProductOrderList().isEmpty())
            {
                for(ScProductOrder productOrder: order.getScProductOrderList())
                {
                    if(productOrder != null)
                    {
                        if(productOrder.getScProccesProductOrderList() != null &&
                                !productOrder.getScProccesProductOrderList().isEmpty())
                        {
                            for(ScProccesProductOrder processProduct: productOrder.getScProccesProductOrderList())
                            {
                                result += processProduct.getTotalValueProcess();
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
    
    /**
     * Método encargado de contar el tiempo de fabricación de una orden
     * @param order orden de producción que se le contarán los productos
     * @return int cantidad de productos
     * @author Gustavo Chavarro Ortiz
     */
    public int getCountTimeOrdenProduction(OtProductionOrder order)
    {
        int result = 0;
        if(order != null)
        {
            if(order.getScProductOrderList() != null && !order.getScProductOrderList().isEmpty())
            {
                for(ScProductOrder productOrder: order.getScProductOrderList())
                {
                    if(productOrder != null)
                    {
                        if(productOrder.getScProccesProductOrderList() != null &&
                                !productOrder.getScProccesProductOrderList().isEmpty())
                        {
                            for(ScProccesProductOrder processProduct: productOrder.getScProccesProductOrderList())
                            {
                                result += processProduct.getTotalTimeProcess();
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
    
    /**
     * Método encargado de contar el precio de costo total de un producto
     * @param product orden de producción que se le contarán los productos
     * @return double valor del producto
     * @author Gustavo Chavarro Ortiz
     */
    public double getCountCostProductByOrder(ScProductFormulation product)
    {
        double result = 0;
        if(product != null && product.getProcessProducts() != null && !product.getProcessProducts().isEmpty())
        {
            for(ScProcessProduct processProduct: product.getProcessProducts())
            {
                result += processProduct.getTotalValueProcess();
            }
        }
        return result;
    }
    
    /**
     * Método encargado de contar el tiempo total de un producto
     * @param product orden de producción que se le contarán los productos
     * @return int valor del producto
     * @author Gustavo Chavarro Ortiz
     */
    public int getCountTimeProductByOrder(ScProductFormulation product)
    {
        int result = 0;
        if(product != null && product.getProcessProducts() != null && !product.getProcessProducts().isEmpty())
        {
            for(ScProcessProduct processProduct: product.getProcessProducts())
            {
                result += processProduct.getTotalTimeProcess();
            }
        }
        return result;
    }
    
    /**
     * Método encargado de contar los productos de una orden de producción
     * @param order orden de producción que se le contarán los productos
     * @return int cantidad de productos
     * @author Gustavo Chavarro Ortiz
     */
    public int getCountListProduct(OtProductionOrder order)
    {
        int result = 0;
        if(order != null)
        {
            if(order.getScProductOrderList()!= null && !order.getScProductOrderList().isEmpty())
            {
                result = order.getScProductOrderList().size();
            }
        }
        return result;
    }
    
    
    /**
     * Método encargado de reiniciar los valores de los filtros.
     * @author Gustavo Chavarro Ortiz
     */ 
    public void resetData()
    {
        initData();
        setFilterEndDate(null);
        setFilterStarDate(null);
        setFilteState(null);
        setFilterOrderNumber(null);
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
     * Consulta el mantenimiento y dependiendo del tipo de mantenimiento muestra 
     * un resultado en la pantalla.
     * @param idOrderProduction id del mantenimiento
     * @Author Gustavo Chavarro Ortiz
     */
    public void findProductionOrder(Long idOrderProduction)
    {
        try
        {
            cleanValues();
            setOrderSelected(getOtProductionServer().getProductionOrderById(idOrderProduction));
            if(getOrderSelected() != null)
            {
                //Verificamos que el estado siga siendo pendiente
                if(getOrderSelected().getIdProductionState().getIdProductionState().equals(new Long("1")))
                {
                    setOrderUpdate(getOrderSelected());
                    RequestContext.getCurrentInstance().execute("PF('dialogProductionUpdate').show()");
        
                }
                else
                {
                    RequestContext.getCurrentInstance().execute("PF('dialogProductionView').show()");
                }
            }
            else
            {
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, "No se encontró la orden");
                log.error(DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR+"\n"+DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
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
        RequestContext.getCurrentInstance().execute("PF('dialogProductionUpdate').hide()");
        RequestContext.getCurrentInstance().execute("PF('dialogProductionView').hide()");
        try
        {
            findProductionOrder(Long.parseLong(getEvent().getTitle().substring(16, getEvent().getTitle().length())));
        }
        catch (Exception e)
        {
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR,
                           DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
        }
    }
    
    /**
     * Método encaragado de capturar el evento de click sobre las fechas vacias.
     * @param selectEvent 
     * @Author Gustavo Chavarro Ortiz
     */
    public void onDateSelect(SelectEvent selectEvent)
    {   
        Date yesterday = new Date();
        yesterday.setDate(yesterday.getDate()-1);
        if(selectEvent != null)
        {
            Date selectDate = (Date) selectEvent.getObject();
            if(selectDate.after(yesterday))
            {
                selectDate.setDate(selectDate.getDate()+1);
                getOrderSave().setStartDate(selectDate);
                RequestContext.getCurrentInstance().execute("PF('dialogProductionSave').show()");
            }
            else
            {
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, "Debe seleccionar una fecha igual o posterior al dia actual");
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

    public IOtProduction getOtProductionServer()
    {
        return otProductionServer;
    }

    public void setOtProductionServer(IOtProduction otProductionServer)
    {
        this.otProductionServer = otProductionServer;
    }

    public ScheduleModel getProductionSchedule()
    {
        return productionSchedule;
    }

    public void setProductionSchedule(ScheduleModel productionSchedule)
    {
        this.productionSchedule = productionSchedule;
    }

    public ScheduleEvent getEvent()
    {
        return event;
    }

    public void setEvent(ScheduleEvent event)
    {
        this.event = event;
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

    public String getFilterOrderNumber()
    {
        return filterOrderNumber;
    }

    public void setFilterOrderNumber(String filterOrderNumber)
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

    public List<String> getElementsAutocomplete()
    {
        return elementsAutocomplete;
    }

    public void setElementsAutocomplete(List<String> elementsAutocomplete)
    {
        this.elementsAutocomplete = elementsAutocomplete;
    }

    public int getHourStart()
    {
        return hourStart;
    }

    public void setHourStart(int hourStart)
    {
        this.hourStart = hourStart;
    }

    public int getMinuteStar()
    {
        return minuteStar;
    }

    public void setMinuteStar(int minuteStar)
    {
        this.minuteStar = minuteStar;
    }

    public int getHourEnd()
    {
        return hourEnd;
    }

    public void setHourEnd(int hourEnd)
    {
        this.hourEnd = hourEnd;
    }

    public int getMinuteEnd()
    {
        return minuteEnd;
    }

    public void setMinuteEnd(int minuteEnd)
    {
        this.minuteEnd = minuteEnd;
    }

    public String getProductAdd()
    {
        return productAdd;
    }

    public void setProductAdd(String productAdd)
    {
        this.productAdd = productAdd;
    }

    public Long getAmount()
    {
        return amount;
    }

    public void setAmount(Long amount)
    {
        this.amount = amount;
    }

    public Date getCurrentDate()
    {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate)
    {
        this.currentDate = currentDate;
    }

    public ScProductOrder getProductFormulationSelected()
    {
        return productFormulationSelected;
    }

    public void setProductFormulationSelected(ScProductOrder productFormulationSelected)
    {
        this.productFormulationSelected = productFormulationSelected;
    }

    public ScProccesProductOrder getProcessProductSelected()
    {
        return processProductSelected;
    }

    public void setProcessProductSelected(ScProccesProductOrder processProductSelected)
    {
        this.processProductSelected = processProductSelected;
    }

    public ScProcessEmployee getProcessEmployeeSelected()
    {
        return processEmployeeSelected;
    }

    public void setProcessEmployeeSelected(ScProcessEmployee processEmployeeSelected)
    {
        this.processEmployeeSelected = processEmployeeSelected;
    }

    public ScProcessInput getProcessInputSelected()
    {
        return processInputSelected;
    }

    public void setProcessInputSelected(ScProcessInput processInputSelected)
    {
        this.processInputSelected = processInputSelected;
    }

    public ScProcessMachine getProcessMachineSelected()
    {
        return processMachineSelected;
    }

    public void setProcessMachineSelected(ScProcessMachine processMachineSelected)
    {
        this.processMachineSelected = processMachineSelected;
    }

    public List<ScProccesProductOrder> getListProcessProduct()
    {
        return listProcessProduct;
    }

    public void setListProcessProduct(List<ScProccesProductOrder> listProcessProduct)
    {
        this.listProcessProduct = listProcessProduct;
    }

    public List<ScProcessEmployeeOrder> getListProcessEmployee()
    {
        return listProcessEmployee;
    }

    public void setListProcessEmployee(List<ScProcessEmployeeOrder> listProcessEmployee)
    {
        this.listProcessEmployee = listProcessEmployee;
    }

    public List<ScProcessInputOrder> getListProcessInput()
    {
        return listProcessInput;
    }

    public void setListProcessInput(List<ScProcessInputOrder> listProcessInput)
    {
        this.listProcessInput = listProcessInput;
    }

    public List<ScProcessMachineOrder> getListProcessMachine()
    {
        return listProcessMachine;
    }

    public void setListProcessMachine(List<ScProcessMachineOrder> listProcessMachine)
    {
        this.listProcessMachine = listProcessMachine;
    }

    
    
    
}
