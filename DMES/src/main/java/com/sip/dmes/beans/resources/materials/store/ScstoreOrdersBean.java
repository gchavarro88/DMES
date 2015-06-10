/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.beans.resources.materials.store;

import com.sip.dmes.beans.SessionBean;
import com.sip.dmes.dao.bo.IScStoreOrder;
import com.sip.dmes.entitys.ScStoreOrder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
public class ScstoreOrdersBean
{

    //Declaración de Variables
    private ScStoreOrder storeOrderAdd;
    private ScStoreOrder storeOrderUpdate;
    private List<ScStoreOrder> storeOrderList;
    private int notificationsNumber;
    private String notificatonsMessage;
    //Persistencia
    private IScStoreOrder scStoreOrderServer; //Dao de persistencia del insumos
    private SessionBean sessionBean;//Bean de la sesion del usuario
    

    private final static Logger log = Logger.getLogger(ScstoreOrdersBean.class);

    
    /**
     * Creates a new instance of ScInputBean
     */
    public ScstoreOrdersBean()
    {

    }

    /**
     * Método encargado de mostrar los datos iniciales.
     */
    @PostConstruct
    public void initData()
    {
        fillListStoreOrders();
        searchExpiredOrders();
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
            setStoreOrderList(getScStoreOrderServer().getAllStoreOrders());
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar las ordenes del almacén de la tabla", e);
        }
    }
    
    /**
     * Método encargado de tomar las ordenes caducadas y mostrarlos
     * en el panel de notificaciones.
     * @author Gustavo Chavarro Ortiz
     */
    public void searchExpiredOrders()
    {
        if(getNotificationsNumber() < 1)
        {
            setNotificatonsMessage("No existen ordenes vencidas");
        }
        else
        {
            setNotificatonsMessage("Existen ordenes vencidas por despachar");
        }
        
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
    
    
}
