/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.beans.resources.materials.store;

import com.sip.dmes.beans.SessionBean;
import com.sip.dmes.dao.bo.IScStoreView;
import com.sip.dmes.entitys.ScStoreOrder;
import com.sip.dmes.entitys.ScStoreOrderItem;
import com.sip.dmes.utilities.DMESConstants;
import com.sip.dmes.utilities.Utilities;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.log4j.Logger;

/**
 *
 * @author gchavarr88
 */
public class ScViewStoreBean
{

    //Declaración de Variables
    private List<Object[]> itemList;
    private Object[] itemSave;
    private String classSelected;
    private List<String> elementsAutocomplete;
    private String itemAdd;
    
    

    private final static Logger log = Logger.getLogger(ScViewStoreBean.class);
    private SessionBean sessionBean;//Bean de la sesion del usuario
    private IScStoreView scStoreViewServer;
    
    
    //Constantes
    final Long ONE_MINUTE = 86400000L;
    final String nameQueryInput = "SELECT id_input, description FROM dmes.sc_input ORDER BY description ASC";
    final String nameQueryProduct = "SELECT id_product_formulation, description FROM dmes.sc_product_formulation ORDER BY description ASC";
    final String nameQueryTool = "SELECT id_tool, name FROM dmes.sc_tool ORDER BY name ASC";
    final String nameQueryReplacement = "SELECT id_replacement, name FROM dmes.sc_replacement ORDER BY name ASC";
    
    final String queryInputSelect = "SELECT SI.ID_INPUT, SI.DESCRIPTION, ST.CURRENT_STOCK, ST.MINIME_STOCK,"
            + " ST.MAXIME_STOCK FROM DMES.SC_STOCK ST, DMES.SC_INPUT SI WHERE ST.ID_STOCK = SI.ID_STOCK AND SI.ID_INPUT = ";
    
    final String queryProductSelect = "SELECT SP.ID_PRODUCT_FORMULATION, SP.DESCRIPTION, ST.CURRENT_STOCK, ST.MINIME_STOCK, "
            + "ST.MAXIME_STOCK FROM DMES.SC_STOCK ST, DMES.SC_PRODUCT_FORMULATION SP WHERE ST.ID_STOCK = SP.ID_STOCK AND SP.ID_PRODUCT_FORMULATION = "; 
    
    final String queryReplacementSelect = "SELECT SR.ID_REPLACEMENT, SR.NAME, ST.CURRENT_STOCK, ST.MINIME_STOCK, ST.MAXIME_STOCK FROM "
            + "DMES.SC_STOCK ST, DMES.SC_REPLACEMENT SR WHERE ST.ID_STOCK = SR.ID_STOCK AND SR.ID_REPLACEMENT = ";
    
    final String queryToolSelect = "SELECT SR.ID_TOOL, SR.NAME, ST.CURRENT_STOCK, ST.MINIME_STOCK, ST.MAXIME_STOCK FROM DMES.SC_STOCK ST,"
            + " DMES.SC_TOOL SR WHERE ST.ID_STOCK = SR.ID_STOCK AND SR.ID_TOOL = ";
    
    final String AREA_PRODUCCION = "Producción";
    final String AREA_MANTENIMIENTO = "Manteniemiento";
    
    
    
    
    /**
     * Creates a new instance of ScInputBean
     */
    public ScViewStoreBean()
    {
        
    }

    /**
     * Método encargado de mostrar los datos iniciales.
     * @author Gustavo Chavarro Ortiz
     */
    @PostConstruct
    public void initData()
    {
        setItemList(new ArrayList<Object[]>());
    }

    /**
     * Método encargado de reiniciar los valores de los filtros.
     */
    public void resetData()
    {
        initData();
    }
    
    
    /**
     * Método encargado de consultar los items para la clase escogida de la requisición.
     * @author Gustavo Chavarro Ortiz
     */
    public void fillAutocompleteList()
    {
        try
        {
            if(!Utilities.isEmpty(getClassSelected()))
            {
                if(getClassSelected().equalsIgnoreCase(DMESConstants.INPUTS))
                {
                    setElementsAutocomplete(getScStoreViewServer().getItemsForAutocomplete(nameQueryInput)); 
                }
                else if(getClassSelected().equalsIgnoreCase(DMESConstants.PRODUCTS))
                {
                    setElementsAutocomplete(getScStoreViewServer().getItemsForAutocomplete(nameQueryProduct)); 
                }
                else if(getClassSelected().equalsIgnoreCase(DMESConstants.REPLACEMENT))
                {
                    setElementsAutocomplete(getScStoreViewServer().getItemsForAutocomplete(nameQueryReplacement)); 
                }
                else if(getClassSelected().equalsIgnoreCase(DMESConstants.TOOLS))
                {
                    setElementsAutocomplete(getScStoreViewServer().getItemsForAutocomplete(nameQueryTool)); 
                }
            }
        }
        catch (Exception e)
        {
            log.error("Error al intentar obtener los elementos para el item de autocomplementar");
        }
        
    }
    /**
     * Método encargado de añadir los items seleccionados a la lista de la requisición.
     * @author Gustavo Chavarro Ortiz
     */
    public void addItemToList()
    {
        
        //Validamos que el item no sea nulo
        if(!Utilities.isEmpty(getItemAdd()))
        {
            //Validamos que exista una lista de items
            if(getItemList()!= null && getItemList() != null)
            {
                String query = "";
                String fields[] = getItemAdd().split(" - "); //Separamos el id del item
                boolean exist = false;
                for(Object[] object: getItemList())
                {
                    if(object[1].toString().equals(fields[1]))
                    {
                        exist = true;
                    }
                }
                if(!exist)//Revisamos que el item ya no exista
                {
                    switch (getClassSelected())
                    {
                        case "Herramientas":
                            query = queryToolSelect+fields[0];
                            break;
                        case "Insumos":
                            query = queryInputSelect+fields[0];
                            break;
                        case "Productos":
                            query = queryProductSelect+fields[0];
                            break;
                        case "Repuestos y Consumibles":
                            query = queryReplacementSelect+fields[0];
                            break;
                        default:
                            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
                            break;
                    }
                    try
                    {   
                        Object[] data = getScStoreViewServer().getItemByStoreRequisition(query);
                        Object[] result = new Object[data.length+1];
                        for(int i=0; i<data.length; i++)
                        {
                            result[i] = data[i];
                        }
                        result[data.length] = getClassSelected();
                        getItemList().add(result);
                        addInfo(null, DMESConstants.MESSAGE_TITTLE_SUCCES, DMESConstants.MESSAGE_SUCCES);
                    }
                    catch (Exception e)
                    {
                            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
                        log.error("Error intentando consultar un item", e);
                    }
                }
                else
                {
                    addInfo(null, DMESConstants.MESSAGE_TITTLE_SUCCES, "El item ya se encuentra agregado a la tabla");
                }
                //Reseteamos el item a pedir
                setItemAdd("");
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
                if(word.contains(query))
                {
                    result.add(word);
                }
            }
        }
        return result;
    }
    
    /**
     * Método encargado de habilitar o deshabilitar el campo donde se escogen los 
     * items.
     * @return boolean variable que inidica si se habilita o no
     * @author Gustavo Chavarro Oritz
     */
    public boolean isActiveAutocomplete()
    {
        if(getClassSelected()!= null)
        {
            if(!Utilities.isEmpty(getClassSelected()))
            {
                return false;
            }
        }
        return true;
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

    public List<Object[]> getItemList()
    {
        return itemList;
    }

    public void setItemList(List<Object[]> itemList)
    {
        this.itemList = itemList;
    }

    public Object[] getItemSave()
    {
        return itemSave;
    }

    public void setItemSave(Object[] itemSave)
    {
        this.itemSave = itemSave;
    }

    public IScStoreView getScStoreViewServer()
    {
        return scStoreViewServer;
    }

    public void setScStoreViewServer(IScStoreView scStoreViewServer)
    {
        this.scStoreViewServer = scStoreViewServer;
    }

    public String getClassSelected()
    {
        return classSelected;
    }

    public void setClassSelected(String classSelected)
    {
        this.classSelected = classSelected;
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

    public SessionBean getSessionBean()
    {
        return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean)
    {
        this.sessionBean = sessionBean;
    }

    


    
    
    /**
     * Application's Setters and Getters
     */
    
    
    
}
