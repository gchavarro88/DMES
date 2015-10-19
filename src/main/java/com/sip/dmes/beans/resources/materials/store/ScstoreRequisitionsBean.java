/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.beans.resources.materials.store;

import com.sip.dmes.beans.SessionBean;
import com.sip.dmes.dao.bo.IScStoreOrder;
import com.sip.dmes.entitys.ScStoreOrder;
import com.sip.dmes.entitys.ScStoreOrderItem;
import com.sip.dmes.entitys.ScStoreOrderState;
import com.sip.dmes.utilities.DMESConstants;
import com.sip.dmes.utilities.Utilities;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

/**
 *
 * @author gchavarr88
 */
public class ScstoreRequisitionsBean
{

    //Declaración de Variables
    private ScStoreOrder storeOrderAdd;
    private ScStoreOrder storeOrderUpdate;
    private List<ScStoreOrder> storeOrderList;
    private int notificationsNumber;
    private String notificatonsMessage;
    private Long idStoreOrderState;
    private List<ScStoreOrderState> listStoreOrderState;
    private Date initDate;
    private Date endDate;
    private String filterOrderType;
    private String filterOrderState;
    private String filterOrderClass;                        
    private String filterOrderRequired;
    private String itemAdd;
    private ScStoreOrderState orderStateExpired;
    private List<String> elementsAutocomplete;
    private List<String> maintenanceAutocomplete;
    
    //Persistencia
    private IScStoreOrder scStoreOrderServer; //Dao de persistencia                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 del insumos
    private SessionBean sessionBean;//Bean de la sesion del usuario
                                                                
                                
    private final static Logger log = Logger.getLogger(ScstoreRequisitionsBean.class);

    //Constantes
    final Long ONE_MINUTE = 86400000L;
    final String nameQueryInput = "SELECT id_input, description FROM dmes.sc_input ORDER BY description ASC";
    final String nameQueryProduct = "SELECT id_product_formulation, description FROM dmes.sc_product_formulation ORDER BY description ASC";
    final String nameQueryTool = "SELECT id_tool, name FROM dmes.sc_tool ORDER BY name ASC";
    final String nameQueryReplacement = "SELECT id_replacement, name FROM dmes.sc_replacement ORDER BY name ASC";
    final String nameQueryMaintenance = "SELECT 'OM'||to_char(creation_date, 'yyyymmdd')||id_maintenance as id_maintenance FROM dmes.ot_maintenance  WHERE id_maintenance_state  IN (1,2)";
    final String AREA_PRODUCCION = "Producción";
    final String AREA_MANTENIMIENTO = "Mantenimiento";
    final String ORDER_CLASS_TOOL = "Herramientas";
    final String ORDER_CLASS_REPLACEMENT = "Repuestos y Consumibles";
    final String ORDER_CLASS_INPUT = "Insumos";
    final String ORDER_CLASS_PRODUCTS = "Productos";
    
    /**
     * Creates a new instance of ScInputBean
     */
    public ScstoreRequisitionsBean()
    {
        
    }

    /**
     * Método encargado de mostrar los datos iniciales.
     * @author Gustavo Chavarro Ortiz
     */
    @PostConstruct
    public void initData()
    {
        setStoreOrderAdd(new ScStoreOrder());
        fillListStoreOrders();
        fillListStoreOrdersState();
        searchExpiredOrders();
    }

    /**
     * Método encargado de reiniciar los valores de los filtros.
     */
    public void resetData()
    {
        initData();
        setFilterOrderClass("...");
        setFilterOrderRequired("...");
        setFilterOrderState("...");
        setFilterOrderType("Entrega");
        setInitDate(null);
        setEndDate(null);
    }
    
    /**
     * Método encargado de realizar la búsqueda teniendo encuenta los filtros.
     * @author Gustavo Chavarro Ortiz
     */
    public void doSearchWithParameters()
    {
        try
        {   
            setStoreOrderList(getScStoreOrderServer().getStoreOrdersByParameters(getInitDate(), getEndDate(), 
                    getFilterOrderType(), getFilterOrderClass(), getFilterOrderState(), getFilterOrderRequired()));
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar las ordenes con parámetros del almacén de la tabla", e);
        }
    }
    /**
     * Método encargado de consultar las ordenes del almacén pendientes.
     * @author Gustavo Chavarro Ortiz
     */
    public void fillListStoreOrders()
    {
        try
        {   
            //Se consultan todos los insumos y se guardan en la lista ordenados por la fecha
            List<Long> statesStore  = new ArrayList<>();
            statesStore.add(DMESConstants.STATE_PROGRAMMED);
            statesStore.add(DMESConstants.STATE_PROCESS);
            statesStore.add(DMESConstants.STATE_LATE);
            setStoreOrderList(getScStoreOrderServer().getStoreOrdersByStatusAndOrderType(statesStore, "Entrega"));
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar las ordenes del almacén de la tabla", e);
        }
    }
    
    /** 
     * Método encargado de consultar las ordenes del almacén pendientes.
     * @author Gustavo Chavarro Ortiz
     */ 
    public void fillListStoreOrdersState()
    {
        try
        { 
            //Se consultan todos los insumos y se guardan en la lista ordenados por la fecha
            setListStoreOrderState(getScStoreOrderServer().getAllStoreOrderState());
            if(getListStoreOrderState() != null)
            {
                for(ScStoreOrderState orderState: getListStoreOrderState())
                {
                    if(orderState.getIdState().equals(DMESConstants.STATE_LATE))
                    {
                        setOrderStateExpired(orderState);
                    }
                }
            }
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar las ordenes del almacén de la tabla", e);
        }
    }
    /**
     * Método encargado de validar cada aumento o disminución en la cantidad entregada.
     * @param item elemento a validar
     * @author Gustavo Chavarro
     */
    public void compare(ScStoreOrderItem item)
    {
        if(item != null)
        {
            
            if(item.getAmountDelivery() >= item.getAmountRequired())
            {
                item.setComplete(true);
                if(item.getAmountDelivery() > item.getAmountRequired())
                {
                    addInfo(null, "La cantidad entregada no debe ser mayor a la cantidad requerida", "");
                    item.setAmountDelivery(item.getAmountRequired());
                } 
            }
            if(item.getAmountStore() <= 0)
            {
                addInfo(null, "El Almacén se ha quedado sin stock para  "
                        + "("+item.getStoreOrder().getOrderClass()+" - "+item.getItemDescription()+")", "");
                item.setAmountDelivery(item.getAmountRequired()-item.getAmountPending());
                
            }
            if(item.getAmountDelivery() < item.getAmountRequired())
            {
                item.setComplete(false);
            }
            item.setAmountPending((item.getAmountRequired()-item.getAmountDelivery()));
        }
    }
    
    /**
     * Método encargada de desabilitar los campos mediante el resultado.
     * @param state estado de la orden
     * @return boolean valor que indica si se deshabilita
     * @author Gustavo Chavarro Ortiz
     */
    public boolean isDisable(Long state)
    {
        boolean result = false;
        if(state != null)
        {
            if(state > 3)
            {
                result = true;
            }
        }
        return result;
    }
    
    /**
     * Método encargada de desabilitar los campos mediante el resultado.
     * @param orderItem estado de la orden
     * @param state
     * @return boolean valor que indica si se deshabilita
     * @author Gustavo Chavarro Ortiz
     */
    public boolean isDisableItem(ScStoreOrderItem orderItem, Long state)
    {
        boolean result = false;
        if(orderItem != null)
        {
            if(state > 3 || orderItem.isComplete())
            {
                result= true;
            }
        }
        return result;
    }
    /**
     * Método encargado de guardar una orden del almacén.
     * @param storeOrder orden a guardar
     * @author Gustavo Chavarro Ortiz
     */
    public void saveOrderStore(ScStoreOrder storeOrder)
    {
        boolean delivered = true;
        Long idState = 0L;
        
        if(storeOrder != null)
        {
            for(ScStoreOrderItem orderItem: storeOrder.getStoreOrderItemList())
            {
                if(!orderItem.isComplete())
                {
                    delivered = false;
                    break;
                }
            }
        }
        idState = delivered ? DMESConstants.STATE_DELIVERED:DMESConstants.STATE_PROCESS;
        for(ScStoreOrderState orderState: getListStoreOrderState())
        {
            if(orderState.getIdState().equals(idState))
            {
                storeOrder.setIdState(orderState);
                break;
            } 
        }
        try
        {
            
            
            String update = "";
            String updateBefore="";
            //Validamos de que tipo es la orden y de acuerdo al criterio consultamos
            if(storeOrder.getOrderClass().equalsIgnoreCase(DMESConstants.INPUTS))
            {
                updateBefore = DMESConstants.UPDATE_INPUT;//Tipo Insumos
            }
            else if(storeOrder.getOrderClass().equalsIgnoreCase(DMESConstants.PRODUCTS))
            {
                updateBefore = DMESConstants.UPDATE_PRODUCT;//Tipo Productos
            }
            else if(storeOrder.getOrderClass().equalsIgnoreCase(DMESConstants.REPLACEMENT))
            {
                updateBefore = DMESConstants.UPDATE_REPLACEMENT; //Tipo Repuesto o Consumible
            }
            else if(storeOrder.getOrderClass().equalsIgnoreCase(DMESConstants.TOOLS))
            {
                updateBefore = DMESConstants.UPDATE_TOOL; //Tipo Herramienta
            }
            Long newPending = 0L;
            for(ScStoreOrderItem orderItem: storeOrder.getStoreOrderItemList())
            {
                update = updateBefore;
                newPending = (orderItem.getAmountRequired() - orderItem.getAmountDelivery());
                update = update.replace("OPERATION", "- ("+(orderItem.getAmountPendingHidden()- newPending)+" )");
                update = update.replace("NUMBERID", ""+orderItem.getIdItemClass());
                getScStoreOrderServer().executeUpdate(update);
                orderItem.setAmountPendingHidden(newPending);
            }
            getScStoreOrderServer().setStoreOrder(storeOrder);
            addInfo(null, DMESConstants.MESSAGE_TITTLE_SUCCES, DMESConstants.MESSAGE_SUCCES);
        }
        catch (Exception e)
        {
            log.error("Error al intentar guardar una orden del almacén", e);
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
        }
    
    }
    
    /**
     * Método encargado de cancelar la orden dependiendo del área que la 
     * solicitó.
     * @param storeOrder orden a ser cancelada
     * @author Gustavo Chavarro Ortiz
     */
    public void cancelOrderStore(ScStoreOrder storeOrder)
    {
        if(!Utilities.isEmpty(storeOrder.getReasonCancellation()))
        {
            //Modificamos el area que solicita la orden
            Long idState = 0L;
            if(storeOrder.getRequiredBy().equals(AREA_MANTENIMIENTO))
            {
                idState = DMESConstants.STATE_CANCEL_MAINTENANCE; //A Mantenimiento
            }
            else if(storeOrder.getRequiredBy().equals(AREA_PRODUCCION))
            {
                idState = DMESConstants.STATE_CANCEL_PRODUCTION; //A Producción
            }
            for(ScStoreOrderState orderState: getListStoreOrderState())
            {
                if(orderState.getIdState().equals(idState))
                {
                    //Colocamos el estado de acuerdo a quien lo cancela
                    storeOrder.setIdState(orderState);
                    break;
                } 
            }
            try
            {
                String update = "";
                String updateBefore = "";
                //Validamos de que tipo es la orden y de acuerdo al criterio consultamos
                if(storeOrder.getOrderClass().equalsIgnoreCase(DMESConstants.INPUTS))
                {
                    updateBefore = DMESConstants.UPDATE_INPUT;//Tipo Insumos
                }
                else if(storeOrder.getOrderClass().equalsIgnoreCase(DMESConstants.PRODUCTS))
                {
                    updateBefore = DMESConstants.UPDATE_PRODUCT;//Tipo Productos
                }
                else if(storeOrder.getOrderClass().equalsIgnoreCase(DMESConstants.REPLACEMENT))
                {
                    updateBefore = DMESConstants.UPDATE_REPLACEMENT; //Tipo Repuesto o Consumible
                }
                else if(storeOrder.getOrderClass().equalsIgnoreCase(DMESConstants.TOOLS))
                {
                    updateBefore = DMESConstants.UPDATE_TOOL; //Tipo Herramienta
                }
                for(ScStoreOrderItem orderItem: storeOrder.getStoreOrderItemList())
                {
                    update = updateBefore;
                    update = update.replace("OPERATION", "+ ("+(orderItem.getAmountRequired()- orderItem.getAmountPendingHidden())+" )");
                    update = update.replace("NUMBERID", ""+orderItem.getIdItemClass());
                    getScStoreOrderServer().executeUpdate(update);
                    orderItem.setAmountPendingHidden(orderItem.getAmountRequired());
                    orderItem.setAmountDelivery(0);
                }
                getScStoreOrderServer().setStoreOrder(storeOrder);
                addInfo(null, DMESConstants.MESSAGE_TITTLE_SUCCES, DMESConstants.MESSAGE_SUCCES);
            }
            catch (Exception e)
            {
                log.error("Error al intentar cancelar una orden del almacén", e);
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }
        }
        else
        {
            addError(null, "Debe ingresar una razón de cancelación","");
            RequestContext.getCurrentInstance().execute("PF('dialogStoreOrder').show()");
        }
    }
    /**
     * Método encargado de visualizar un item dentro del combo box de agregar un item del almacén.
     * @param classType tipo de orden a evaluar
     * @param area area al que pertenece el tipo de orden
     * @return boolean valor que indica si el tipo de clase se visualizará en el combo box
     * @author Gustavo Chavarro Ortiz
     */
    public boolean renderClass(String classType, String area)
    {
        boolean result = true;
        if(area.equals(AREA_MANTENIMIENTO) && (classType.equals(ORDER_CLASS_REPLACEMENT)
                || classType.equals(ORDER_CLASS_TOOL)))
        {
            result = false;
        }
        else if(area.equals(AREA_PRODUCCION) && (classType.equals(ORDER_CLASS_INPUT)
                || classType.equals(ORDER_CLASS_PRODUCTS)))
        {
            result = false;
        }
        return result;
    }
    
    /**
     * Método encargado de realizar la validación de las fechas.
     * @author Gustavo Chavarro Ortiz
     */
    public void compareToDates()
    {
        if((getInitDate() != null && getEndDate()!= null) && (getInitDate().after(getEndDate()))) 
        {        
            addError(null, "Validación de Fechas", "La fecha inicial debe ser menor o igual a la fecha final");
            setEndDate(null);
            setInitDate(null);
        }
    }
    /**
     * Método encargado de consultar los estados de las ordenes
     * @author Gustavo Chavarro Ortiz
     */
    public void searchExpiredOrders()
    {
        int numberPending = 0;
        int numberExpired = 0;
        Date date = new Date();
        date.setTime(date.getTime()- ONE_MINUTE);
        try
        {
            if(getStoreOrderList() != null)
            {
                for(ScStoreOrder storeOrder: getStoreOrderList())
                {
                    
                    if(storeOrder.getIdState().getIdState().equals(DMESConstants.STATE_PROCESS) || 
                            storeOrder.getIdState().getIdState().equals(DMESConstants.STATE_PROGRAMMED)
                            || storeOrder.getIdState().getIdState().equals(DMESConstants.STATE_LATE))
                    {
                        if(storeOrder.getCreationDate().before(date))
                        {
                            numberExpired++;
                        }
                        else
                        {
                            numberPending++;
                        }
                        
                    }
                } 
                if(numberExpired > 0 || numberPending > 0)
                {
                    setNotificatonsMessage("Orden(es) pendiente(s): "+numberPending+"             "+
                             "______________ Orden(es) atrasada(s): "+numberExpired+"____________");
                    setNotificationsNumber(numberPending+numberExpired);
                    RequestContext.getCurrentInstance().execute("hiddenCount()");
                }
            }
            
        }
        catch (Exception ex)
        {
            log.error("Error al intentar consultar los estados ordenes del almacén de la tabla", ex);
        }
    }
    
    /**
     * Método encargado de asignar la fila seleccionada a una orden del almacén.
     * @author Gustavo Chavarro Ortiz
     */
    public void updateStoreOrderSelected()
    {
        if(getStoreOrderUpdate() != null)
        {
            findItemsByOrder(getStoreOrderUpdate());   
            for(ScStoreOrderItem orderItem: getStoreOrderUpdate().getStoreOrderItemList())
            {
                if(orderItem.getAmountDelivery() >= orderItem.getAmountRequired())
                {
                    orderItem.setComplete(true); 
                }       
            }
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
                if(word.toUpperCase().contains(query.toUpperCase()))
                {
                    result.add(word);
                }
            }
        }
        return result;
    }
    
    
    /**
     * Método encargado de completar el id de los mantenimientos y de producción.
     * @param query palabra o indicio con cual buscar
     * @return List<String> lista de posibles opciones
     * @author Gustavo Chavarro Ortiz
     */
    public List<String> autocompleteMetodMaintenance(String query)
    {
        List<String> result = new ArrayList<>();
        if(getMaintenanceAutocomplete()!= null && !getMaintenanceAutocomplete().isEmpty())
        {
            for(String word: getMaintenanceAutocomplete())
            {
                if(word.contains(query.toUpperCase()))
                {
                    result.add(word);
                }
            }
        }
        return result;
    }
    
    /**
     * Método encargado de consultar los items para la clase escogida de la requisición.
     * @author Gustavo Chavarro Ortiz
     */
    public void fillAutocompleteList()
    {
        getStoreOrderAdd().setStoreOrderItemList(new ArrayList<ScStoreOrderItem>());
        getStoreOrderAdd().setAmountItems(0);
        try
        {
            if(!Utilities.isEmpty(getStoreOrderAdd().getOrderClass()))
            {
                if(getStoreOrderAdd().getOrderClass().equalsIgnoreCase(DMESConstants.INPUTS))
                {
                    setElementsAutocomplete(getScStoreOrderServer().getItemsForAutocomplete(nameQueryInput)); 
                }
                else if(getStoreOrderAdd().getOrderClass().equalsIgnoreCase(DMESConstants.PRODUCTS))
                {
                    setElementsAutocomplete(getScStoreOrderServer().getItemsForAutocomplete(nameQueryProduct)); 
                }
                else if(getStoreOrderAdd().getOrderClass().equalsIgnoreCase(DMESConstants.REPLACEMENT))
                {
                    setElementsAutocomplete(getScStoreOrderServer().getItemsForAutocomplete(nameQueryReplacement)); 
                }
                else if(getStoreOrderAdd().getOrderClass().equalsIgnoreCase(DMESConstants.TOOLS))
                {
                    setElementsAutocomplete(getScStoreOrderServer().getItemsForAutocomplete(nameQueryTool)); 
                }
            }
        }
        catch (Exception e)
        {
            log.error("Error al intentar obtener los elementos para el item de autocomplementar");
        }
        
    }
    
    /**
     * Método encargado de asignar un estilo para cada fila dependiendo de su estado.
     * @author Gustavo Chavarro Ortiz
     * @param storeOrder orden del almacén
     * @return String estilo que será retornado
     */
    public String getStyleRow(ScStoreOrder storeOrder)
    { 
        String result = "";
        Date date = new Date(); 
        date.setTime(date.getTime()- ONE_MINUTE);
        if(storeOrder != null)
        {
            if((storeOrder.getIdState().getIdState().equals(DMESConstants.STATE_PROCESS) ||
                storeOrder.getIdState().getIdState().equals(DMESConstants.STATE_PROGRAMMED)||
                storeOrder.getIdState().getIdState().equals(DMESConstants.STATE_LATE)) &&
                storeOrder.getCreationDate().before(date))
            {
               storeOrder.setIdState(getOrderStateExpired());
               try
               {
                   getScStoreOrderServer().setStoreOrder(storeOrder);
               }
               catch (Exception ex)
               {
                   log.error("Error al  consultar los estados ordenes"+ex);
               }
            }   
            if(storeOrder.getIdState().getIdState().equals(DMESConstants.STATE_PROCESS))
            {
                result = "rowPending";
            }
             if(storeOrder.getIdState().getIdState().equals(DMESConstants.STATE_PROGRAMMED))
            {
                result = "rowProgrammer";
            }
            if(storeOrder.getIdState().getIdState().equals(DMESConstants.STATE_LATE))
            {
                result = "rowExpired"; 
            }
           
               
        }
        return result;
    }
    
    public void findItemsByOrder(ScStoreOrder storeOrder)
    {
        String query = "";//Query que va a consultar los valores de los items
        //Validamos de que tipo es la orden y de acuerdo al criterio consultamos
        if(storeOrder.getOrderClass().equalsIgnoreCase(DMESConstants.INPUTS))
        {
            query = DMESConstants.QUERY_INPUT;//Tipo Insumos
        }
        else if(storeOrder.getOrderClass().equalsIgnoreCase(DMESConstants.PRODUCTS))
        {
            query = DMESConstants.QUERY_PRODUCT;//Tipo Productos
        }
        else if(storeOrder.getOrderClass().equalsIgnoreCase(DMESConstants.REPLACEMENT))
        {
            query = DMESConstants.QUERY_REPLACEMENT; //Tipo Repuesto o Consumible
        }
        else if(storeOrder.getOrderClass().equalsIgnoreCase(DMESConstants.TOOLS))
        {
            query = DMESConstants.QUERY_TOOL; //Tipo Herramienta
        }
        for(ScStoreOrderItem orderItem: storeOrder.getStoreOrderItemList())
        {
            query += orderItem.getIdItemClass()+",";
        }
        query = query.substring(0, query.length()-1);
        query += ")";
        if(storeOrder.getStoreOrderItemList() != null && !storeOrder.getStoreOrderItemList().isEmpty())
        {
            try
            {
                List<Object []> result = getScStoreOrderServer().getItemsByStoreOrder(query);
                if(result != null)
                {
                    for(Object[] object: result)
                    {
                        long id     = Long.parseLong(object[0].toString());
                        String name = object[1].toString();
                        long stock  = Long.parseLong(object[2].toString());
                        for(ScStoreOrderItem orderItem: storeOrder.getStoreOrderItemList())
                        {
                            if(orderItem.getIdItemClass() == id)
                            {
                                orderItem.setAmountStore(stock);
                                orderItem.setItemDescription(name);
                                break;
                            }
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                log.error("Error al intentar consultar los datos de los items del almacén", ex);
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }
        }
    }
    /**
     * Método encargado de habilitar o deshabilitar el campo donde se escogen los 
     * items.
     * @return boolean variable que inidica si se habilita o no
     * @author Gustavo Chavarro Oritz
     */
    public boolean isActiveAutocomplete()
    {
        if(getStoreOrderAdd() != null)
        {
            if(!Utilities.isEmpty(getStoreOrderAdd().getOrderClass()))
            {
                return false;
            }
        }
        return true;
    }
    
    
    /**
     * Método encargado de eliminar los items que no se van a solicitar
     * @param itemDelete item a eliminar
     * @author Gustavo Chavarro Ortiz
     */
    public void deleteItemToList(ScStoreOrderItem itemDelete)
    {
        int iterator = 0;
        if(getStoreOrderAdd() != null && getStoreOrderAdd().getStoreOrderItemList() != null)
        {
            for(ScStoreOrderItem index: getStoreOrderAdd().getStoreOrderItemList())
            {
                
                if(index.getIdItemClass() == itemDelete.getIdItemClass())
                {
                    break;
                }
                iterator++;
            }
        }
        if(iterator < getStoreOrderAdd().getStoreOrderItemList().size())
        {
            getStoreOrderAdd().getStoreOrderItemList().remove(iterator);
        }
    }
    
    /**
     * Método encargado de añadir los items seleccionados a la lista de la requisición.
     * @author Gustavo Chavarro Ortiz
     */
    public void addItemToList()
    {
        ScStoreOrderItem itemAddList = new ScStoreOrderItem();
        //Validamos que el item no sea nulo
        if(!Utilities.isEmpty(getItemAdd()))
        {
            //Validamos que exista una lista de items
            if(getStoreOrderAdd() != null && getStoreOrderAdd().getStoreOrderItemList() != null)
            {
                String fields[] = getItemAdd().split(" - "); //Separamos el id del item
                itemAddList.setClassItem(getStoreOrderAdd().getOrderClass()); //Tremos la clase del item
                itemAddList.setIdItemClass(Long.parseLong(fields[0])); //Traemos el id de la clase del item
                itemAddList.setItemDescription(fields[1]); //Traemos el nombre del item
                itemAddList.setAmountRequired(1);//Por defecto pedimos una unidad
                itemAddList.setAmountPending(1);//Cantidad pendiente por defecto
                itemAddList.setStoreOrder(getStoreOrderAdd()); //Le asignamos la orden al item
                boolean exist = false;
                //Validamos que ya no exista el item
                for(ScStoreOrderItem index: getStoreOrderAdd().getStoreOrderItemList())
                {
                    if(index.getIdItemClass() == (Long.parseLong(fields[0])))
                    {
                        exist = true;
                        break;
                    }
                }
                if(!exist)
                {
                    //Si no existe lo agregamos a la lista
                    getStoreOrderAdd().getStoreOrderItemList().add(itemAddList);
                    getStoreOrderAdd().setAmountItems(getStoreOrderAdd().getAmountItems()+1);
                }
                //Reseteamos el item a pedir
                setItemAdd("");
            }
        }
    }
    /**
     * Método encargado de cancelar la creación de una requisición.
     * @author Gustavo Chavarro Ortiz
     */
    public void cancelCreateRequisition()
    {
        setStoreOrderAdd(new ScStoreOrder());
        setItemAdd("");
        RequestContext.getCurrentInstance().execute("PF('dialogStoreRequisition').hide()");
    }
    
    
    /**
     * Método encargado de crear la requisición
     * @author Gustavo Chavarro Ortiz
     */
    public void createRequisition()
    {
        try
        {
            //Validamos que la orden no sea null
            if(getStoreOrderAdd() != null)
            { 
                if(!Utilities.isEmpty(getStoreOrderAdd().getOrderClass()))
                {
                    if(!Utilities.isEmpty(getStoreOrderAdd().getRequiredBy()))
                    {
                        if(!Utilities.isEmpty(getStoreOrderAdd().getIdOrderRequest()))
                        {
                            if(getStoreOrderAdd().getStoreOrderItemList().size() > 0)
                            {
                                getStoreOrderAdd().setCreationDate(new Date()); //Añadimos la fecha actual
                                //Añadimos la persona que crea la orden
                                getStoreOrderAdd().setEmployeeCreate(getScStoreOrderServer(). 
                                getEmployeeByPerson(getSessionBean().getScUser().getIdPerson()));
                                //Añadimos la cantidad de items en la orden
                                getStoreOrderAdd().setAmountItems(getStoreOrderAdd().getStoreOrderItemList().size());
                                //Alistmaos las cantidades pendientes de todos los items
                                for(ScStoreOrderItem item : getStoreOrderAdd().getStoreOrderItemList())
                                {
                                    item.setAmountPending(item.getAmountRequired());
                                    item.setAmountPendingHidden(item.getAmountRequired());
                                }
                                //getStoreOrderAdd().setStoreOrderItemList(null);
                                //Inicializamos en el estado programada
                                getStoreOrderAdd().setIdState(new ScStoreOrderState((long) 1));
                                getStoreOrderAdd().getIdState().setDescription("Programada");
                                //Actualizamos el tipo a Entrega
                                getStoreOrderAdd().setOrderType("Entrega");
                                //Guardamos la requisición
                                getScStoreOrderServer().saveStoreOrder(getStoreOrderAdd());
                                //La añadimos a la lista que se visualiza en pantalla
                                getStoreOrderList().add(getStoreOrderAdd());
                                addInfo(null, DMESConstants.MESSAGE_TITTLE_SUCCES, DMESConstants.MESSAGE_SUCCES);
                                setStoreOrderAdd(new ScStoreOrder());
                                setItemAdd("");
                                RequestContext.getCurrentInstance().execute("PF('dialogStoreRequisition').hide()");
                            }
                            else
                            {
                                addError(null, "Error al crear una requisición del Almacén", 
                                        "Debe seleccionar la menos un item para la requisición");
                            }
                        }
                        else
                        {
                            addError(null, "Error al crear una requisición del Almacén", 
                                        "Debe ingresar el No de la Orden de Mantenimiento o Producción");
                        }
                    }
                    else
                    {
                        addError(null, "Error al crear una requisición del Almacén", "Debe ingresar el área de la requisición");
                    }
                }
                else
                {
                    addError(null, "Error al crear una requisición del Almacén", "Debe ingresar la clase de la requisición");
                }
            }
        }
        catch (Exception e)
        {
            log.error("Error al crear la requisición");
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            RequestContext.getCurrentInstance().execute("PF('dialogStoreRequisition').hide()");
        }
    }
    
    /**
     * Método encargado de completar la lista de ordenes dependiendo del área que la 
     * solicite.
     * @param storeOrder orden para verificar si es de mantenimiento o de producción
     */
    public void fillAutocompleteListOrders(ScStoreOrder storeOrder)
    {
        if(storeOrder != null)
        {
            try
            {
                if(storeOrder.getRequiredBy().equals(AREA_PRODUCCION))
                {

                }
                else if(storeOrder.getRequiredBy().equals(AREA_MANTENIMIENTO))
                {
                    setMaintenanceAutocomplete(getScStoreOrderServer().getItemsForAutocompleteMaintenance(nameQueryMaintenance));
                }
            }
            catch (Exception e)
            {
                log.error("Error al intentar consultar los ids para la lista de ordenes",e);
            }
            storeOrder.setOrderClass(null);
        }
    }
    
    
    /**
     * Método encargado de habilitar o deshabilitar el campo del numero de la orden.
     * @param storeOrder orden a guardar
     * @return boolean valor que permite deshabilitar o habilitar (true inhabilita o false habilita)
     * @author Gustavo Chavarro Ortiz
     */
    public boolean requestBy(ScStoreOrder storeOrder)
    {
         boolean result = true;
         if(storeOrder != null)
         {
            if(!Utilities.isEmpty(storeOrder.getRequiredBy()))
            {
                result = false;
            }
         }
         return result;
    }
    
    /**
     * Método encargado de recibir una fecha y devolverla en una cadena de caracteres
     * con el formato dd-MM-yyyy
     * @param date fecha que será convertida al nuevo formato
     * @return String resultado de cadena de caracteres a retornar
     * @author Gustavo Chavarro Ortiz
     */
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
     * Método encargado de visualizar un mensaje en la pantalla de tipo
     * informativo
     *
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
     * Método encargado de visualizar un mensaje en la pantalla de tipo
     * Advertencia
     *
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
     * Método encargado de visualizar un mensaje en la pantalla de tipo Error
     *
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
     * Método encargado de visualizar un mensaje en la pantalla de tipo Fatal
     *
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
     * Application's Setters and Getters
     */
    
    public ScStoreOrder getStoreOrderAdd()
    {
        return storeOrderAdd;
    }

    public void setStoreOrderAdd(ScStoreOrder storeOrderAdd)
    {
        this.storeOrderAdd = storeOrderAdd;
    }

    public ScStoreOrder getStoreOrderUpdate()
    {
        return storeOrderUpdate;
    }

    public void setStoreOrderUpdate(ScStoreOrder storeOrderUpdate)
    {
        this.storeOrderUpdate = storeOrderUpdate;
    }

    public List<ScStoreOrder> getStoreOrderList()
    {
        return storeOrderList;
    }

    public void setStoreOrderList(List<ScStoreOrder> storeOrderList)
    {
        this.storeOrderList = storeOrderList;
    }

    public IScStoreOrder getScStoreOrderServer()
    {
        return scStoreOrderServer;
    }

    public void setScStoreOrderServer(IScStoreOrder scStoreOrderServer)
    {
        this.scStoreOrderServer = scStoreOrderServer;
    }

    

    public SessionBean getSessionBean()
    {
        return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean)
    {
        this.sessionBean = sessionBean;
    }

    public int getNotificationsNumber()
    {
        return notificationsNumber;
    }

    public void setNotificationsNumber(int notificationsNumber)
    {
        this.notificationsNumber = notificationsNumber;
    }

    public String getNotificatonsMessage()
    {
        return notificatonsMessage;
    }

    public void setNotificatonsMessage(String notificatonsMessage)
    {
        this.notificatonsMessage = notificatonsMessage;
    }

    public Long getIdStoreOrderState()
    {
        return idStoreOrderState;
    }

    public void setIdStoreOrderState(Long idStoreOrderState)
    {
        this.idStoreOrderState = idStoreOrderState;
    }

    public List<ScStoreOrderState> getListStoreOrderState()
    {
        return listStoreOrderState;
    }

    public void setListStoreOrderState(List<ScStoreOrderState> listStoreOrderState)
    {
        this.listStoreOrderState = listStoreOrderState;
    }

    public Date getInitDate()
    {
        return initDate;
    }

    public void setInitDate(Date initDate)
    {
        this.initDate = initDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public String getFilterOrderType()
    {
        return filterOrderType;
    }

    public void setFilterOrderType(String filterOrderType)
    {
        this.filterOrderType = filterOrderType;
    }

    public String getFilterOrderState()
    {
        return filterOrderState;
    }

    public void setFilterOrderState(String filterOrderState)
    {
        this.filterOrderState = filterOrderState;
    }

    public String getFilterOrderClass()
    {
        return filterOrderClass;
    }

    public void setFilterOrderClass(String filterOrderClass)
    {
        this.filterOrderClass = filterOrderClass;
    }

    public String getFilterOrderRequired()
    {
        return filterOrderRequired;
    }

    public void setFilterOrderRequired(String filterOrderRequired)
    {
        this.filterOrderRequired = filterOrderRequired;
    }

    public ScStoreOrderState getOrderStateExpired()
    {
        return orderStateExpired;
    }

    public void setOrderStateExpired(ScStoreOrderState orderStateExpired)
    {
        this.orderStateExpired = orderStateExpired;
    }

    public List<String> getElementsAutocomplete()
    {
        return elementsAutocomplete;
    }

    public void setElementsAutocomplete(List<String> elementsAutocomplete)
    {
        this.elementsAutocomplete = elementsAutocomplete;
    }

    public String getItemAdd()
    {
        return itemAdd;
    }

    public void setItemAdd(String itemAdd)
    {
        this.itemAdd = itemAdd;
    }

    public List<String> getMaintenanceAutocomplete()
    {
        return maintenanceAutocomplete;
    }

    public void setMaintenanceAutocomplete(List<String> maintenanceAutocomplete)
    {
        this.maintenanceAutocomplete = maintenanceAutocomplete;
    }
    
    
}
