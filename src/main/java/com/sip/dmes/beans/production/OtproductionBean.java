/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.beans.production;

import com.sip.dmes.beans.SessionBean;
import com.sip.dmes.dao.bo.IOtProduction;
import com.sip.dmes.entitys.OtProductionOrder;
import com.sip.dmes.entitys.OtProductionProduct;
import com.sip.dmes.entitys.ScProcessProduct;
import com.sip.dmes.entitys.ScProductFormulation;
import com.sip.dmes.entitys.ScProductionState;
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
    private List<String> elementsAutocomplete;
    private int hourStart;
    private int minuteStar;
    private int hourEnd;
    private int minuteEnd;
    private String productAdd;
    private Long amount;
    private Date currentDate;
    /**
     * Creates a new instance of OtmaintenanceCorrectiveBean
     */
    public OtproductionBean()
    {
        
    }
    
    
    @PostConstruct
    public void initData()
    {
        cleanValues();
        fillListProductionState();
        fillListProductionOrders();
        fillListProductFormulations();
        fillListListAutocomplete();
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
     * Método encargado de carga la lista de autocompletar productos
     * @author Gustavo Chavarro Ortiz
    */
    public void fillListListAutocomplete()
    {
        if(getListProductFormulations()!= null)
        {
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
     * Método encargado de limpiar los valores iniciales.
     * @author Gustavo Chavarro Ortiz
     */
    public void cleanValues()
    {
        setOrderSave(new OtProductionOrder());
        getOrderSave().setIdProductionState(new ScProductionState());
        getOrderSave().setProductionsOrders(new ArrayList<OtProductionProduct>());
        getOrderSave().setCreationDate(new Date());
        getOrderSave().setStartDate(new Date());
        setCurrentDate(new Date());       
        setOrderSelected(new OtProductionOrder());
        setOrderUpdate(new OtProductionOrder());
        setElementsAutocomplete(new ArrayList<String>());
        setProductAdd("");
        setMinuteEnd(0);
        setMinuteStar(0);
        setHourEnd(0);
        setHourStart(0);
        setAmount(new Long(0));
        setProductAdd("");
    }
    
    
    /**
     * Método encargado de realizar la búsqueda teniendo encuenta los filtros.
     * @author Gustavo Chavarro Ortiz
     */
    public void doSearchWithParameters()
    {
        try
        {   
            setListProductionOrders(getOtProductionServer().getProductionByParameters(getFilterStarDate()
                    , getFilterEndDate(), getFilterOrderNumber(), getFilteState()));
            addInfo(null, DMESConstants.MESSAGE_TITTLE_SUCCES, DMESConstants.MESSAGE_SUCCES);
        }
        catch (Exception e)
        {
            addInfo(null, DMESConstants.MESSAGE_TITTLE_SUCCES, DMESConstants.MESSAGE_SUCCES);
            log.error("Error al intentar consultar las ordenes de producción con parámetros", e);
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
            if (getOrderSave().getProductionsOrders() == null || getOrderSave().getProductionsOrders().isEmpty())
            {
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, "Debe seleccionar al menos un producto");
                return event.getOldStep();
            }   
        }
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
        return event.getNewStep();
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
     * Método encargado de validar la cantidad de los productos
     * @param amountValidate cantidad de producto
     * @param productionProduct producto
     * @author Gustavo Chavarro Ortiz     * 
     */
//    public void validateAmount(Long amountValidate, OtProductionProduct productionProduct)
//    {
//        if(amountValidate == null)
//        {
//            log.error("Valor nulo para la cantidad");
//            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, "Valor nulo para la cantidad");
//            productionProduct.setAmount(1L);
//        }
//        else
//        {
//            if(amountValidate < 0)
//            {
//                log.error("La cantidad debe ser mayor que cero");
//                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, "La cantidad debe ser mayor que cero");
//                productionProduct.setAmount(1L);
//            }
//        }
//    }
    
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
            calendar.setTime(order.getCreationDate());
            if(order.getProductionsOrders() != null && !order.getProductionsOrders().isEmpty())
            {
                for(OtProductionProduct productionProduct: order.getProductionsOrders())
                {
                    if(productionProduct != null && productionProduct.getIdProductFormulation() != null
                            && productionProduct.getIdProductFormulation().getProcessProducts() != null
                            && !productionProduct.getIdProductFormulation().getProcessProducts().isEmpty())
                    {
                        for(ScProcessProduct process: productionProduct.getIdProductFormulation().getProcessProducts())
                        {
                            if(process != null)
                            {
                                calendar.add(Calendar.MINUTE, (int) (process.getTotalTimeProcess()* productionProduct.getAmount()));
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
    public void addProductsToOrder(List<OtProductionProduct> list, OtProductionOrder order)
    {
        OtProductionProduct productionProduct = new OtProductionProduct();
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
                            for(OtProductionProduct production: list)
                            {
                                if(product.getIdProductFormulation().equals(production.getIdProductFormulation().getIdProductFormulation()))
                                {
                                    exists = true;
                                    break;
                                }
                            }
                            if(!exists)
                            {
                                if(getAmount() != null && getAmount() > 0)
                                {
                                    productionProduct.setAmount(getAmount());
                                    productionProduct.setIdProductFormulation(product);
                                    list.add(productionProduct);
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
     * Método encargardo de permitirle al usuario seleccionar una orden de producción para actualizar.
     * @param orderSelected orden de produccón
     * @author Gustavo Chavarro Ortiz
     */
    public void selectedForUpdate(OtProductionOrder orderSelected)
    {
        cleanValues();
        if(orderSelected != null)
        {
            setOrderUpdate(orderSelected);
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
     * Método encargado de reiniciar los valores de los filtros.
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

    public List<String> getElementsAutocomplete()
    {
        return elementsAutocomplete;
    }

    public void setElementsAutocomplete(List<String> elementsAutocomplete)
    {
        this.elementsAutocomplete = elementsAutocomplete;
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
    
    
    
    

}
