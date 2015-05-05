/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.beans.resources.materials;

import com.sip.dmes.beans.SessionBean;
import com.sip.dmes.dao.bo.IScReplacement;
import com.sip.dmes.dao.bs.ScReplacementDao;
import com.sip.dmes.entitys.ScCostCenter;
import com.sip.dmes.entitys.ScDistributionUnit;
import com.sip.dmes.entitys.ScInputDimension;
import com.sip.dmes.entitys.ScInputStock;
import com.sip.dmes.entitys.ScReplacement;
import com.sip.dmes.entitys.ScLocation;
import com.sip.dmes.entitys.ScMeasureUnit;
import com.sip.dmes.entitys.ScMoney;
import com.sip.dmes.entitys.ScPackingUnit;
import com.sip.dmes.entitys.ScPartner;
import com.sip.dmes.entitys.ScPriority;
import com.sip.dmes.entitys.ScProductAttached;
import com.sip.dmes.entitys.ScReplacement;
import com.sip.dmes.entitys.ScReplacementAttached;
import com.sip.dmes.entitys.ScReplacementDocuments;
import com.sip.dmes.entitys.ScStore;
import com.sip.dmes.entitys.ScTime;
import com.sip.dmes.utilities.DMESConstants;
import com.sip.dmes.utilities.Utilities;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author gchavarr88
 */
public class ScReplacementBean
{

    //Declaración de Variables
    private List<ScReplacement> replacementList;//Lista de repuestos de la tabla
    private ScReplacement replacementSelected; //Repuesto seleccionado para consulta, edición o eliminación
    private ScReplacement replacementSave; //Repuesto seleccionado para agregar
    private ScMeasureUnit measureUnitSave; //Unidad de medida seleccionado para agregar
    private ScMeasureUnit measureUnitSaveHigh; //Unidad de medida seleccionado para agregar
    private ScMeasureUnit measureUnitSaveWidth; //Unidad de medida seleccionado para agregar
    private ScMeasureUnit measureUnitSaveRadio; //Unidad de medida seleccionado para agregar
    private ScMeasureUnit measureUnitSaveLarge; //Unidad de medida seleccionado para agregar
    private ScMeasureUnit measureUnitSaveVolume; //Unidad de medida seleccionado para agregar
    private ScMeasureUnit measureUnitSaveThickness; //Unidad de medida seleccionado para agregar
    private ScMeasureUnit measureUnitSaveWeight; //Unidad de medida seleccionado para agregar

    
    private ScLocation replacementLocationSave; //Localizacion seleccionada para agregar
    private ScLocation replacementLocationSelected; //Localizacion seleccionada para agregar al repuesto
    private ScStore storeSave; //Localizacion seleccionada para agregar
    private ScStore storeSelected; //Localizacion seleccionada para agregar al repuesto
    private ScPriority prioritySave; //Prioridad seleccionada para agregar al repuesto
    private SessionBean sessionBean; //Bean de sesion
    private UploadedFile pictureFile; //Archivo que se copiara para la imagen del repuesto
    private ScCostCenter costCenterSave; //Centro de Costo para agregar
    private ScReplacementAttached replacementAttachedSave;//Especificación a guardar
    private ScReplacementDocuments replaceDocumentsSave;//Documento a guardar
    private ScMoney moneySave;//Moneda a guardar
    private List<ScPartner> partnersList;//Listado de proveedores
    private List<ScCostCenter> costCenterList;//Listado de centros de costo
    private List<ScMeasureUnit> measureUnitsList;//Lista de unidades de medida
    private List<ScLocation> replacementLocationsList;//Lista de localizaciones
    private List<ScStore> storesList;//Lista de almacenes
    private List<ScTime> timeList;//Lista de tiempos
    private List<ScPriority> priorityList;//Lista de prioridades
    private List<ScReplacementAttached> attachedListSave;//Lista de equivalencias a guardar
    private List<ScReplacementDocuments> documentsListSave;//Lista de documentos a guardar
    private List<ScMoney> moneyList;//Lista de monedas
    private UploadedFile fileSave;//Documento a subir
    private UploadedFile fileUpdate;//Documento a actualizar

    //Persistencia
    private IScReplacement scReplacementServer; //Dao de persistencia del repuestos

    private final static Logger log = Logger.getLogger(ScReplacementDao.class);

    //Constantes
    //Tabs
    private final String TAB_GENERAL = "tabGeneral";
    private final String TAB_STOCK = "tabStock";
    private final String TAB_SPECIFICATIONS = "tabSpecifications";
    private final String TAB_FEACTURES = "tabFeactures";
    private final String TAB_OBSERVATIONS = "tabObservations";
    private final String TAB_EQUIVALENCE = "tabEquivalence";
    private final String TAB_CONFIRM_SAVE = "tabConfirmSave";

    //files
    private int MAX_SIZE_FILE = 5;//Tamaño en megas del archivo
    private String EXTENSION_FILE = "pdf,xls,doc,xlsx,docx,txt,pps,ppt,pptx,ppsx";
    private String PATH_FILE = System.getProperty("user.home"); //Obtenemos la ruta del servidor

    /**
     * Creates a new instance of ScReplacementBean
     */
    public ScReplacementBean()
    {

    }

    /**
     * Método encargado de mostrar los datos iniciales.
     */
    @PostConstruct
    public void initData()
    {
        fillListReplacements();
        fillListPartners();
        fillListCostCenter();
        fillListStore();
        fillListPriority();
        fillListMeasure();
        fillListMoney();
        fillListTimes();
        cleanFieldsInit();
        getinitalParameters();
    }

    /**
     * Método encargado de llenar la lista de repuestos.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void fillListReplacements()
    {
        try
        {
            //Se consultan todos los repuestos y se guardan en la lista ordenados por la fecha
            setReplacementList(getScReplacementServer().getAllReplacements());
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar los repuestos de la tabla", e);
        }
    }

    /**
     * Método encargado de llenar la lista de proveedores.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void fillListPartners()
    {
        try
        {
            //Se consultan todos los proveedores existentes
            setPartnersList(getScReplacementServer().getAllPartners());
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar los proveedores para los repuestos", e);
        }
    }
    
    /**
     * Método encargado de llenar la lista de tiempos.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void fillListTimes()
    {
        try
        {
            //Se consultan todos los tiempos existentes
            setTimeList(getScReplacementServer().getAllTimes());
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar los tiempos para los repuestos", e);
        }
    }

    /**
     * Método encargado de llenar la lista de centros de costo.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void fillListCostCenter()
    {
        try
        {
            //Se consultan todos los proveedores existentes
            setCostCenterList(getScReplacementServer().getAllCostCenter());
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar los proveedores para los repuestos", e);
        }
    }


    
    /**
     * Método encargado de llenar la lista de localizaciones.
     *
     * @param store almacen que contiene las localizaciones
     * @author Gustavo Chavarro Ortiz
     */
    public void fillListReplacementLocation(ScStore store)
    {
        try
        {
            //Se consultan todos los proveedores existentes
            setReplacementLocationsList(getScReplacementServer().getAllReplacementLocations(store));
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar las localizaciones para los repuestos", e);
        }
    }

    /**
     * Método encargado de llenar la lista de almacenes.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void fillListStore()
    {
        try
        {
            //Se consultan todos almacenes disponibles
            setStoresList(getScReplacementServer().getAllStores());
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar los almacenes para los repuestos", e);
        }
    }

    /**
     * Método encargado de llenar la lista de prioridades.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void fillListPriority()
    {
        try
        {
            //Se consultan todos almacenes disponibles
            setPriorityList(getScReplacementServer().getAllPrioritys());
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar las prioridades para los repuestos", e);
        }
    }

    /**
     * Método encargado de llenar la lista de medidas.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void fillListMeasure()
    {
        try
        {
            //Se consultan todos almacenes disponibles
            setMeasureUnitsList(getScReplacementServer().getAllMeasureUnits());
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar las medidas para los repuestos", e);
        }
    }

    /**
     * Método encargado de llenar la lista de medidas.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void fillListMoney()
    {
        try
        {
            //Se consultan todas las monedas disponibles
            setMoneyList(getScReplacementServer().getAllMoneys());
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar las monedas para los repuestos", e);
        }
    }

    /**
     * Método encargado de vaciar los objetos.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void cleanFieldsInit()
    {

        setReplacementSelected(new ScReplacement());
        setCostCenterSave(new ScCostCenter());
        setReplacementLocationSave(new ScLocation());
        setMeasureUnitSave(new ScMeasureUnit());
        cleanDocumentSave();
        cleanReplacementSave();
        setDocumentsListSave(new ArrayList<ScReplacementDocuments>());
    }

    public void cleansTypesMeasures()
    {
        setMeasureUnitSaveHigh(new ScMeasureUnit());
        setMeasureUnitSaveWidth(new ScMeasureUnit());
        setMeasureUnitSaveRadio(new ScMeasureUnit());
        setMeasureUnitSaveVolume(new ScMeasureUnit());
        setMeasureUnitSaveLarge(new ScMeasureUnit());
        setMeasureUnitSaveThickness(new ScMeasureUnit());
        setMeasureUnitSaveWeight(new ScMeasureUnit());
        setMoneySave(new ScMoney());
    }

    public void cleanReplacementSave()
    {
        setReplacementSave(new ScReplacement());
        //Creamos un stock para la siguiente pestaña
        getReplacementSave().setStock(new ScInputStock());
        //Creamos el objeto de dimension para la segunda pestaña
        getReplacementSave().setDimension(new ScInputDimension());
        cleanListSaves();
        setReplacementAttachedSave(new ScReplacementAttached());
        setReplaceDocumentsSave(new ScReplacementDocuments());
        cleansTypesMeasures();
    }

    /**
     * Método encargado de vaciar los objetos.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void cleanFieldsCostCenter()
    {
        setCostCenterSave(new ScCostCenter());
    }

    /**
     * Método encargado de vaciar los objetos.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void cleanFieldsMeasure()
    {
        setMeasureUnitSave(new ScMeasureUnit());
    }

    /**
     * Método encargado de vaciar los objetos.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void cleanFieldsStores()
    {
        setStoreSave(new ScStore());
    }

    /**
     * Método encargado de vaciar los objetos.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void cleanFieldsPriority()
    {
        setPrioritySave(new ScPriority());
    }

    /**
     * Método encargado de vaciar los objetos.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void cleanFieldsLocationReplacement()
    {
        setReplacementLocationSave(new ScLocation());
    }

    /**
     * Método encargado de agregar un centro de costos.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void addCostCenter()
    {
        try
        {
            if (getCostCenterSave() != null)
            {
                if (Utilities.isNumeric(getCostCenterSave().getCostCenter()))
                {
                    getCostCenterSave().setCreationDate(new Date());
                    getScReplacementServer().saveCostCenter(getCostCenterSave());
                    if (getCostCenterList() == null)
                    {
                        setCostCenterList(new ArrayList<ScCostCenter>());
                    }
                    getCostCenterList().add(getCostCenterSave());
                    cleanFieldsCostCenter();
                }
                else
                {
                    log.error("Error al intentar crear el centro de costos desde repuestos");
                    addError(null, "Error al crear un centro de costos", "Debe ingresar solo números para el campo código del centro de costo");
                }
            }
            else
            {
                log.error("Error al intentar crear el centro de costos desde repuestos");
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }

        }
        catch (Exception e)
        {
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            log.error("Error al intentar agregar un centro de costos desde repuestos", e);
        }

    }

    /**
     * Método encargado de agregar una medida.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void addMeasure()
    {
        try
        {
            if (getMeasureUnitSave() != null)
            {
                getScReplacementServer().saveMeasureUnit(getMeasureUnitSave());
                if (getMeasureUnitsList() == null)
                {
                    setMeasureUnitsList(new ArrayList<ScMeasureUnit>());
                }
                getMeasureUnitsList().add(getMeasureUnitSave());
                cleanFieldsMeasure();
            }
            else
            {
                log.error("Error al intentar crear la unidad de medida para repuestos");
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }

        }
        catch (Exception e)
        {
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            log.error("Error al intentar agregar una unidad de medida desde repuestos", e);
        }

    }


    /**
     * Método encargado de verificar si se ha seleccionado un almacen
     *
     * @param store almacen que contiene las localizaciones
     * @return boolean parametro que dice si tiene o no un almacen seleccionado
     * @author Gustavo Chavarro Ortiz
     */
    public boolean withOutStore(ScStore store)
    {
        boolean result = false;
        if (store == null || Utilities.isEmpty(store.getName()))
        {
            result = true;
        }
        else
        {
            fillListReplacementLocation(store);
        }
        return result;
    }

    /**
     * Método encargado de agregar una unidad de localizacion
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void addReplacementLocations()
    {
        try
        {
            if (getReplacementLocationSave() != null)
            {
                ScStore storeLocation = null;
                //Agregamos el almacen de la localizacion
                if (getReplacementSave().getStock().getIdStore() != null)
                {
                    storeLocation = getReplacementSave().getStock().getIdStore();
                }
                //Para una actualizacion de datos
                else
                {
                    storeLocation = getReplacementSelected().getStock().getIdStore();
                }
                getReplacementLocationSave().setStore(storeLocation);
                //Se realiza la persistencia de la localizacion
                getScReplacementServer().saveLocationReplacement(getReplacementLocationSave());
                if (getReplacementLocationsList() == null)
                {
                    setReplacementLocationsList(new ArrayList<ScLocation>());
                }
                getReplacementLocationsList().add(getReplacementLocationSave());
                cleanFieldsLocationReplacement();
            }
            else
            {
                log.error("Error al intentar crear la localización desde repuestos");
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }

        }
        catch (Exception e)
        {
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            log.error("Error al intentar crear la localización desde repuestos", e);
        }
    }


    
    /**
     * Método encargado de borrar un documento agregada a la lista para guardar
     * un inusmo.
     *
     * @param documents documento a borrar
     * @author Gustavo Chavarro Ortiz
     */
    public void deleteDocument(ScReplacementDocuments documents)
    {
        if (getDocumentsListSave() != null && !getDocumentsListSave().isEmpty())
        {
            int index = 0; //Posición del objeto que se eliminará
            for (ScReplacementDocuments iterator : getDocumentsListSave())
            {
                if (iterator.getDocumentTittle().equals(documents.getDocumentTittle())
                        && iterator.getDocumentPath().equals(documents.getDocumentPath()))
                {
                    break;//Rompempos el ciclo
                }
                index++;//Aumentamos la posición
            }
            getDocumentsListSave().remove(index);//Removemos el elemento en la posición hallada
        }
        else
        {
            addInfo(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            log.error(DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR + ", " + DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
        }
    }

    /**
     * Método encargado de borrar un documento agregada a la lista para guardar
     * un inusmo.
     *
     * @param documents documento a borrar
     * @author Gustavo Chavarro Ortiz
     */
    public void deleteDocumentUpdate(ScReplacementDocuments documents)
    {
        if (getReplacementSelected().getReplacementDocuments() != null && !getReplacementSelected().getReplacementDocuments().isEmpty())
        {
            int index = 0; //Posición del objeto que se eliminará
            for (ScReplacementDocuments iterator : getReplacementSelected().getReplacementDocuments())
            {
                if (iterator.getDocumentTittle().equals(documents.getDocumentTittle())
                        && iterator.getDocumentPath().equals(documents.getDocumentPath()))
                {
                    break;//Rompempos el ciclo
                }
                index++;//Aumentamos la posición
            }
            getReplacementSelected().getReplacementDocuments().remove(index);//Removemos el elemento en la posición hallada
        }
        else
        {
            addInfo(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            log.error(DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR + ", " + DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
        }
    }

    /**
     * Método encargado de llevar el flujo al guardar un repuesto.
     *
     * @param event evento en el cual se encuentra el asistente para crear
     * repuestos
     * @return String al final retorna el nombre de la siguiente pestaña del
     * asistente
     * @author Gustavo Chavarro Ortiz
     */
    public String onFlowProcessSaveReplacement(FlowEvent event)
    {
        int packingUnit = -1;
        if (event.getNewStep().equals(TAB_GENERAL))
        {
            return TAB_GENERAL;
        }
        if (event.getOldStep().equals(TAB_GENERAL))
        {
            if (validateFields("Nombre Repuesto", getReplacementSave().getName(), 3))
            {
                return event.getOldStep();
            }   
            if (validateFields("Vida Útil", getReplacementSave().getUsefulLife()+ "", 2))
            {
                return event.getOldStep();
            }
            if (validateFields("Unidad de Tiempo", getReplacementSave().getTime(), 4))
            {
                return event.getOldStep();
            }
            if (validateFields("Tipo de Repuesto", getReplacementSave().getTypeReplacement(), 3))
            {
                return event.getOldStep();
            }
            //Validamos que el valor sea mayor que cero
            if (validateFields("Valor", getReplacementSave().getValue() + "", 1))
            {
                return event.getOldStep();
            }
            if (validateFields("Moneda", getReplacementSave().getMoney(), 4))
            {
                return event.getOldStep();
            }
            //modificamos el precio por unidad
            getReplacementSave().getStock().setPriceUnit(getReplacementSave().getValue());
            //modificamos el precio total igual al precio por unidad * el stock actual
            getReplacementSave().getStock().setTotalValue(getReplacementSave().getValue()
                    * getReplacementSave().getStock().getCurrentStock());

            if (validateFields("Marca", getReplacementSave().getMark(), 3))
            {
                return event.getOldStep();
            }

            if (Utilities.isEmpty(getReplacementSave().getPathPicture()))
            {
                getReplacementSave().setPathPicture(" ");//Setteamos la ruta de la imagen
            }
            if (validateFields("Serie", getReplacementSave().getSerie(), 3))
            {
                return event.getOldStep();
            }

            //Validamos los campos seleccionables
            if (validateFields("Proveedor y Garantía", getReplacementSave().getSupplierGuarantee(), 4))
            {
                return event.getOldStep();
            }
            if (validateFields("Centro de Costos", getReplacementSave().getCostCenter(), 4))
            {
                return event.getOldStep();
            }
            if (validateFields("Almacen", getReplacementSave().getStock().getIdStore(), 4))
            {
                return event.getOldStep();
            }
            if (validateFields("Localización", getReplacementSave().getInputLocation(), 4))
            {
                return event.getOldStep();
            }
            if (validateFields("Prioridad", getReplacementSave().getPriority(), 4))
            {
                return event.getOldStep();
            }
            for(ScTime time: getTimeList())
            {
                if(time.getIdTime().equals(getReplacementSave().getTime().getIdTime()))
                {
                    getReplacementSave().setTime(time);
                }
            }
            //Agregamos la fecha de creación del repuesto
            getReplacementSave().setCreationDate(new Date());
            getReplacementSave().setValueMinutes((getReplacementSave().getUsefulLife() * getReplacementSave().getTime().getMinutes()));
        }
        //Si pasamos de la pestaña de datos generales a stock y dimensiones
        else if (event.getOldStep().equals(TAB_STOCK))
        {
            //Validamos que ninguno de los campos del stock sea vacio o negativo
            if (validateFields("Stock Máximo", getReplacementSave().getStock().getMaximeStock(), 2))
            {
                return event.getOldStep();
            }
            if (validateFields("Stocguschaork Mínimo", getReplacementSave().getStock().getMinimeStock(), 2))
            {
                return event.getOldStep();
            }
            if (getReplacementSave().getStock().getMaximeStock() <= getReplacementSave().getStock().getMinimeStock())
            {
                addError(null, "Error en el Stock del Repuesto", "El Stock Máximo debe ser mayor que el Stock Mínimo");
                return event.getOldStep();
            }
            if (validateFields("Stock Real", getReplacementSave().getStock().getCurrentStock(), 2))
            {
                return event.getOldStep();
            }

            if (validateFields("Stock Óptimo", getReplacementSave().getStock().getOptimeStock(), 2))
            {
                return event.getOldStep();
            }
            else
            {
                if (getReplacementSave().getStock().getMaximeStock() < getReplacementSave().getStock().getOptimeStock())
                {
                    addError(null, "Error en el Stock del Repuesto", "El Stock Máximo debe ser mayor que el Stock Óptimo");
                    return event.getOldStep();
                }
                if (getReplacementSave().getStock().getMinimeStock() > getReplacementSave().getStock().getOptimeStock())
                {
                    addError(null, "Error en el Stock del Repuesto", "El Stock Mínimo debe ser menor que el Stock Óptimo");
                    return event.getOldStep();
                }
            }

            if (validateFields("Altura", getReplacementSave().getDimension().getHight(), 1))
            {
                return event.getOldStep();
            }
            else if (getMeasureUnitSaveHigh() == null)
            {
                addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para la Altura");
                return event.getOldStep();
            }
            if (validateFields("Ancho", getReplacementSave().getDimension().getWidth(), 1))
            {
                return event.getOldStep();
            }
            else if (getMeasureUnitSaveWidth() == null)
            {
                addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Ancho");
                return event.getOldStep();
            }
            if (validateFields("Largo", getReplacementSave().getDimension().getLarge(), 1))
            {
                return event.getOldStep();
            }
            else if (getMeasureUnitSaveLarge() == null)
            {
                addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Largo");
                return event.getOldStep();
            }
            if (validateFields("Peso", getReplacementSave().getDimension().getWeight(), 1))
            {
                return event.getOldStep();
            }
            else if (getMeasureUnitSaveWeight() == null)
            {
                addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Peso");
                return event.getOldStep();
            }
            if (!Utilities.isEmpty(getReplacementSave().getDimension().getVolume()))
            {
                if (validateFields("Volumen", getReplacementSave().getDimension().getVolume(), 1))
                {
                    return event.getOldStep();
                }
                else if (getMeasureUnitSaveVolume() == null)
                {
                    addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Volumen");
                    return event.getOldStep();
                }
            }
            if (!Utilities.isEmpty(getReplacementSave().getDimension().getThickness()))
            {
                if (validateFields("Grosor", getReplacementSave().getDimension().getThickness(), 1))
                {
                    return event.getOldStep();
                }
                else if (getMeasureUnitSaveThickness() == null)
                {
                    addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Grosor");
                    return event.getOldStep();
                }
            }
            if (!Utilities.isEmpty(getReplacementSave().getDimension().getRadio()))
            {
                if (validateFields("Radio", getReplacementSave().getDimension().getRadio(), 1))
                {
                    return event.getOldStep();
                }
                else if (getMeasureUnitSaveRadio() == null)
                {
                    addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Radio");
                    return event.getOldStep();
                }
            }
            if (!Utilities.isEmpty(getReplacementSave().getDimension().getWeight()))
            {
                if (validateFields("Peso", getReplacementSave().getDimension().getWeight(), 1))
                {
                    return event.getOldStep();
                }
                else if (getMeasureUnitSaveWeight() == null)
                {
                    addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Peso");
                    return event.getOldStep();
                }
            }
        }

        return event.getNewStep();
    }

    /**
     * Método encargado de permitir mostrar un combo box.
     *
     * @param value valor del replacement que permite habilitar el combo box
     * @param option valor del tipo de medida
     * @return boolean valor que permite mostrar u ocultar un combo box
     * @author Gustavo Chavarro Ortiz
     */
    public boolean showItem(String value, int option)
    {
        boolean result = false;
        if (!Utilities.isEmpty(value))
        {
            try
            {
                Double number = Double.parseDouble(value);
                if (number > 0)
                {
                    result = true;
                }
            }
            catch (Exception e)
            {
                //No se realiza ninguna acción
            }
        }
        if (!result)
        {
            switch (option)
            {
                case 1: //Alto
                    setMeasureUnitSaveHigh(null);
                    break;
                case 2://Ancho
                    setMeasureUnitSaveWidth(null);
                    break;
                case 3://Largo
                    setMeasureUnitSaveLarge(null);
                    break;
                case 4://Volumen
                    setMeasureUnitSaveVolume(null);
                    break;
                case 5://Grosor
                    setMeasureUnitSaveThickness(null);
                    break;
                case 6://Radio
                    setMeasureUnitSaveRadio(null);
                    break;
                case 7://Peso
                    setMeasureUnitSaveWeight(null);
                    break;
                default:
                    break;
            }
        }

        return result;
    }

    /**
     * Método encargado de calcular el precio total de un repuesto.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void calcuteTotalPrice()
    {
        getReplacementSave().getStock().setTotalValue(getReplacementSave().getValue()
                * getReplacementSave().getStock().getCurrentStock());
    }

    /**
     * Método encargado de calcular el precio total de un repuesto.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void calcuteTotalPriceUpdate()
    {
        getReplacementSelected().getStock().setTotalValue(getReplacementSelected().getValue()
                * getReplacementSelected().getStock().getCurrentStock());
    }    

    
    
    
    
    /**
     * Método encargado de llevar el flujo al actualizar un repuesto.
     *
     * @param event evento en el cual se encuentra el asistente para actualizar
     * repuestos
     * @return String al final retorna el nombre de la siguiente pestaña del
     * asistente
     * @author Gustavo Chavarro Ortiz
     */
    public String onFlowProcessUpdateReplacement(FlowEvent event)
    {
        if (event.getNewStep().equals(TAB_GENERAL))
        {
            return TAB_GENERAL;
        }
        if (event.getOldStep().equals(TAB_GENERAL))
        {
            if (validateFields("Nombre Repuesto", getReplacementSelected().getName(), 3))
            {
                return event.getOldStep();
            }   
            if (validateFields("Vida Útil", getReplacementSelected().getUsefulLife()+ "", 2))
            {
                return event.getOldStep();
            }
            if (validateFields("Unidad de Tiempo", getReplacementSelected().getTime(), 4))
            {
                return event.getOldStep();
            }
            if (validateFields("Tipo de Repuesto", getReplacementSelected().getTypeReplacement(), 3))
            {
                return event.getOldStep();
            }
            //Validamos que el valor sea mayor que cero
            if (validateFields("Valor", getReplacementSelected().getValue() + "", 1))
            {
                return event.getOldStep();
            }
            if (validateFields("Moneda", getReplacementSelected().getMoney(), 4))
            {
                return event.getOldStep();
            }
            //modificamos el precio por unidad
            getReplacementSelected().getStock().setPriceUnit(getReplacementSelected().getValue());
            //modificamos el precio total igual al precio por unidad * el stock actual
            getReplacementSelected().getStock().setTotalValue(getReplacementSelected().getValue()
                    * getReplacementSelected().getStock().getCurrentStock());

            if (validateFields("Marca", getReplacementSelected().getMark(), 3))
            {
                return event.getOldStep();
            }

            if (Utilities.isEmpty(getReplacementSelected().getPathPicture()))
            {
                getReplacementSelected().setPathPicture(" ");//Setteamos la ruta de la imagen
            }
            if (validateFields("Serie", getReplacementSelected().getSerie(), 3))
            {
                return event.getOldStep();
            }

            //Validamos los campos seleccionables
            if (validateFields("Proveedor y Garantía", getReplacementSelected().getSupplierGuarantee(), 4))
            {
                return event.getOldStep();
            }
            if (validateFields("Centro de Costos", getReplacementSelected().getCostCenter(), 4))
            {
                return event.getOldStep();
            }
            if (validateFields("Almacen", getReplacementSelected().getStock().getIdStore(), 4))
            {
                return event.getOldStep();
            }
            if (validateFields("Localización", getReplacementSelected().getInputLocation(), 4))
            {
                return event.getOldStep();
            }
            if (validateFields("Prioridad", getReplacementSelected().getPriority(), 4))
            {
                return event.getOldStep();
            }
            for(ScTime time: getTimeList())
            {
                if(time.getIdTime().equals(getReplacementSelected().getTime().getIdTime()))
                {
                    getReplacementSelected().setTime(time);
                }
            }
            getReplacementSelected().setValueMinutes((getReplacementSelected().getUsefulLife() * getReplacementSelected().getTime().getMinutes()));
        }
        //Si pasamos de la pestaña de datos generales a stock y dimensiones
        else if (event.getOldStep().equals(TAB_STOCK))
        {
            //Validamos que ninguno de los campos del stock sea vacio o negativo
            if (validateFields("Stock Máximo", getReplacementSelected().getStock().getMaximeStock(), 2))
            {
                return event.getOldStep();
            }
            if (validateFields("Stocguschaork Mínimo", getReplacementSelected().getStock().getMinimeStock(), 2))
            {
                return event.getOldStep();
            }
            if (getReplacementSelected().getStock().getMaximeStock() <= getReplacementSelected().getStock().getMinimeStock())
            {
                addError(null, "Error en el Stock del Repuesto", "El Stock Máximo debe ser mayor que el Stock Mínimo");
                return event.getOldStep();
            }
            if (validateFields("Stock Real", getReplacementSelected().getStock().getCurrentStock(), 2))
            {
                return event.getOldStep();
            }

            if (validateFields("Stock Óptimo", getReplacementSelected().getStock().getOptimeStock(), 2))
            {
                return event.getOldStep();
            }
            else
            {
                if (getReplacementSelected().getStock().getMaximeStock() < getReplacementSelected().getStock().getOptimeStock())
                {
                    addError(null, "Error en el Stock del Repuesto", "El Stock Máximo debe ser mayor que el Stock Óptimo");
                    return event.getOldStep();
                }
                if (getReplacementSelected().getStock().getMinimeStock() > getReplacementSelected().getStock().getOptimeStock())
                {
                    addError(null, "Error en el Stock del Repuesto", "El Stock Mínimo debe ser menor que el Stock Óptimo");
                    return event.getOldStep();
                }
            }

            if (validateFields("Altura", getReplacementSelected().getDimension().getHight(), 1))
            {
                return event.getOldStep();
            }
            else if (getMeasureUnitSaveHigh() == null)
            {
                addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para la Altura");
                return event.getOldStep();
            }
            if (validateFields("Ancho", getReplacementSelected().getDimension().getWidth(), 1))
            {
                return event.getOldStep();
            }
            else if (getMeasureUnitSaveWidth() == null)
            {
                addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Ancho");
                return event.getOldStep();
            }
            if (validateFields("Largo", getReplacementSelected().getDimension().getLarge(), 1))
            {
                return event.getOldStep();
            }
            else if (getMeasureUnitSaveLarge() == null)
            {
                addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Largo");
                return event.getOldStep();
            }
            if (validateFields("Peso", getReplacementSelected().getDimension().getWeight(), 1))
            {
                return event.getOldStep();
            }
            else if (getMeasureUnitSaveWeight() == null)
            {
                addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Peso");
                return event.getOldStep();
            }
            if (!Utilities.isEmpty(getReplacementSelected().getDimension().getVolume()))
            {
                if (validateFields("Volumen", getReplacementSelected().getDimension().getVolume(), 1))
                {
                    return event.getOldStep();
                }
                else if (getMeasureUnitSaveVolume() == null)
                {
                    addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Volumen");
                    return event.getOldStep();
                }
            }
            if (!Utilities.isEmpty(getReplacementSelected().getDimension().getThickness()))
            {
                if (validateFields("Grosor", getReplacementSelected().getDimension().getThickness(), 1))
                {
                    return event.getOldStep();
                }
                else if (getMeasureUnitSaveThickness() == null)
                {
                    addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Grosor");
                    return event.getOldStep();
                }
            }
            if (!Utilities.isEmpty(getReplacementSelected().getDimension().getRadio()))
            {
                if (validateFields("Radio", getReplacementSelected().getDimension().getRadio(), 1))
                {
                    return event.getOldStep();
                }
                else if (getMeasureUnitSaveRadio() == null)
                {
                    addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Radio");
                    return event.getOldStep();
                }
            }
            if (!Utilities.isEmpty(getReplacementSelected().getDimension().getWeight()))
            {
                if (validateFields("Peso", getReplacementSelected().getDimension().getWeight(), 1))
                {
                    return event.getOldStep();
                }
                else if (getMeasureUnitSaveWeight() == null)
                {
                    addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Peso");
                    return event.getOldStep();
                }
            }
        }
        return event.getNewStep();
    }
 
    /**
     * Método encargado de guardar temporalmente un adjunto.
     * @param replacementAttached adjunto que sera guardado
     * @param attachedListSave lista de adjuntos a la que será agregado el adjunto en cuestión
     * @param type tipo del adjunto, puede ser una observación, especificación o característica
     * @param replacement repuesto o consumible al que pertenece el adjunto
     * @author Gustavo Chavarro Ortiz
     */
    public void saveAttached(ScReplacementAttached replacementAttached, ScReplacement replacement ,
            List<ScReplacementAttached> attachedListSave,String type)
    {
        if(replacementAttached != null)
        {
            if(!Utilities.isEmpty(replacementAttached.getTittle()) && 
                    !Utilities.isEmpty(replacementAttached.getDescription())
                    && !Utilities.isEmpty(type))
            {
                if(attachedListSave != null)
                {
                    //Guardamos exitosamente el adjunto
                    replacementAttached.setReplacement(replacement);
                    replacementAttached.setType(type);
                    attachedListSave.add(replacementAttached);
                    setReplacementAttachedSave(new ScReplacementAttached());
                }
                else
                {
                    addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
                    log.error(DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR+", "+DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
                }
             
            }
            else
            {
                addError(null, "Error al intentar guardar una especificación", 
                            "Debe ingresar los campos Título y Descripción de la especificación");
                    log.error("Error al intentar guardar una especificación, "
                            + "Debe ingresar los campos Título y Descripción de la especificación");
            }
        }
        else
        {
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            log.error(DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR+", "+DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
        }
    }
    
    /**
     * Método encargado de retornar la lista de adjuntos de acuerdo a un tipo
     * @param attachedListSave lista de donde serán extraidos los adjuntos
     * @param type tipo del adjunto extraido
     * @return List<ScReplacementAttached> tipo del adjunto a devolver
     * @author Gustavo Chavarro Ortiz
     */
    public List<ScReplacementAttached> getAttachedList(List<ScReplacementAttached> attachedListSave, String type)
    {
        List<ScReplacementAttached> result = new ArrayList<ScReplacementAttached>();
        if(attachedListSave != null && !attachedListSave.isEmpty())
        {
            for(ScReplacementAttached attached: attachedListSave)
            {
                if(attached.getType().equals(type))
                {
                    result.add(attached);
                }
            }
        }
        return result;
    }
    
    
    /**
     * Método encargado de eliminar un adjunto.
     * @param replacementAttached adjunto que sera guardado
     * @param attachedListSave lista de adjuntos a la que será agregado el adjunto en cuestión
     * @author Gustavo Chavarro Ortiz
     */
    public void deleteAttached(List<ScReplacementAttached> attachedListSave, ScReplacementAttached replacementAttached)
    {
        int index = 0;
        if(replacementAttached != null)
        {
            if(attachedListSave != null && !attachedListSave.isEmpty())
            {
                for(ScReplacementAttached attached: attachedListSave)
                {
                    if(attached.getType().equals(replacementAttached.getType()) && attached.getTittle()
                            .equals(replacementAttached.getTittle()) && attached.getDescription()
                            .equals(replacementAttached.getDescription()))
                    {
                        break;
                    }
                    index++;
                }
                if(index < attachedListSave.size())
                {
                    attachedListSave.remove(index);
                }
            }
            else
            {
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
                log.error(DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR+", "+DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }
        }
        else
        {
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            log.error(DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR+", "+DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
        }
    }
    
    
    /**
     * Método encargado de llevar el flujo al actualizar un repuesto.
     *
     * @param event evento en el cual se encuentra el asistente para actualizar
     * repuestos
     * @return String al final retorna el nombre de la siguiente pestaña del
     * asistente
     * @author Gustavo Chavarro Ortiz
     */
    public String onFlowProcessViewReplacement(FlowEvent event)
    {

        return event.getNewStep();
    }

    /**
     * Método encargado de realizar la persistencia de un repuesto, guardando
     * listas y objetos incluidos en el.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void saveReplacement()
    {
        //Valido que el repuesto no sea nulo
        if (getReplacementSave() != null)
        {
            if (getReplacementAttachedSave()!= null)
            {
                //Le agrego la lista de especificaciones
                getReplacementSave().setReplacementAttacheds(getAttachedListSave());
            }
            if (getDocumentsListSave() != null)
            {
                //Le agrego la lista de documentos
                getReplacementSave().setReplacementDocuments(getDocumentsListSave());
            }
            //Almacenamos el repuesto
            try
            {
                if (getMeasureUnitSaveHigh() != null)
                {
                    getReplacementSave().getDimension().setHight(getReplacementSave().getDimension().getHight() + "-" + getMeasureUnitSaveHigh().getAcronym());
                }
                if (getMeasureUnitSaveWidth() != null)
                {
                    getReplacementSave().getDimension().setWidth(getReplacementSave().getDimension().getWidth() + "-" + getMeasureUnitSaveWidth().getAcronym());
                }
                if (getMeasureUnitSaveLarge() != null)
                {
                    getReplacementSave().getDimension().setLarge(getReplacementSave().getDimension().getLarge() + "-" + getMeasureUnitSaveLarge().getAcronym());
                }
                if (getMeasureUnitSaveWeight() != null)
                {
                    getReplacementSave().getDimension().setWeight(getReplacementSave().getDimension().getWeight() + "-" + getMeasureUnitSaveWeight().getAcronym());
                }
                if (getMeasureUnitSaveVolume() != null)
                {
                    getReplacementSave().getDimension().setVolume(getReplacementSave().getDimension().getVolume() + "-" + getMeasureUnitSaveVolume().getAcronym());
                }
                if (getMeasureUnitSaveThickness() != null)
                {
                    getReplacementSave().getDimension().setThickness(getReplacementSave().getDimension().getThickness() + "-" + getMeasureUnitSaveThickness().getAcronym());
                }
                if (getMeasureUnitSaveRadio() != null)
                {
                    getReplacementSave().getDimension().setRadio(getReplacementSave().getDimension().getRadio() + "-" + getMeasureUnitSaveRadio().getAcronym());
                }
                getScReplacementServer().saveReplacement(getReplacementSave());
                getReplacementList().add(getReplacementSave());
                cleanReplacementSave();
            }
            catch (Exception e)
            {
                log.error("Error almacenando el repuesto", e);
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
                cleanReplacementSave();
            }

        }

    }
    /**
     * Método encargado de unir las columnas para las dimensiones
     * @author Gustavo Chavarro Ortiz
     */
    public void joinColumnsUpdate()
    {
        //Valido que el repuesto no sea nulo
        if (getReplacementSelected() != null)
        {
        //Almacenamos el repuesto

            if (getMeasureUnitSaveHigh() != null)
            {
                getReplacementSelected().getDimension().setHight(getReplacementSelected().getDimension().getHight() + "-" + getMeasureUnitSaveHigh().getAcronym());
            }
            if (getMeasureUnitSaveWidth() != null)
            {
                getReplacementSelected().getDimension().setWidth(getReplacementSelected().getDimension().getWidth() + "-" + getMeasureUnitSaveWidth().getAcronym());
            }
            if (getMeasureUnitSaveLarge() != null)
            {
                getReplacementSelected().getDimension().setLarge(getReplacementSelected().getDimension().getLarge() + "-" + getMeasureUnitSaveLarge().getAcronym());
            }
            if (getMeasureUnitSaveWeight() != null)
            {
                getReplacementSelected().getDimension().setWeight(getReplacementSelected().getDimension().getWeight() + "-" + getMeasureUnitSaveWeight().getAcronym());
            }
            if (getMeasureUnitSaveVolume() != null)
            {
                getReplacementSelected().getDimension().setVolume(getReplacementSelected().getDimension().getVolume() + "-" + getMeasureUnitSaveVolume().getAcronym());
            }
            if (getMeasureUnitSaveThickness() != null)
            {
                getReplacementSelected().getDimension().setThickness(getReplacementSelected().getDimension().getThickness() + "-" + getMeasureUnitSaveThickness().getAcronym());
            }
            if (getMeasureUnitSaveRadio() != null)
            {
                getReplacementSelected().getDimension().setRadio(getReplacementSelected().getDimension().getRadio() + "-" + getMeasureUnitSaveRadio().getAcronym());
            }
        }
    }

    /**
     * Método encargado de realizar la persistencia de un repuesto, actualizando
     * listas y objetos incluidos en el.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void updateReplacement()
    {
        //Valido que el repuesto no sea nulo
        if (getReplacementSelected() != null)
        {
            joinColumnsUpdate();
            try
            {
                getScReplacementServer().updateReplacement(getReplacementSelected());
                int index = 0;
                for (ScReplacement replacement : getReplacementList())
                {
                    if (replacement.getIdReplacement().equals(getReplacementSelected().getIdReplacement()))
                    {
                        break;
                    }
                    index++;
                }
                if (index < getReplacementList().size())
                {
                    getReplacementList().set(index, getReplacementSelected());
                }
                cleanReplacementSave();
            }
            catch (Exception e)
            {
                log.error("Error actualizando el repuesto", e);
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
                cleanReplacementSave();
            }

        }

    }

    /**
     * Método encargado de eliminar un repuesto.
     *
     * @autor Gustavo Chavarro Ortiz
     */
    public void deleteReplacement()
    {
        if (getReplacementSelected() != null)
        {
            try
            {
                getScReplacementServer().deleteReplacement(getReplacementSelected());
                getReplacementList().remove(getReplacementSelected());
            }
            catch (Exception e)
            {
                log.error("Error eliminando el repuesto", e);
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
                cleanReplacementSave();
            }
        }
    }

    /**
     * Método encargado de inicializar todas las listas para crear un repuesto.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void cleanListSaves()
    {
        setAttachedListSave(new ArrayList<ScReplacementAttached>());
        setDocumentsListSave(new ArrayList<ScReplacementDocuments>());
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
     * Método encargado de realizar la copia del archivo que se desea cargar.
     *
     * @param option se escoge la opción entre guardar y actualizar
     * @author: Gustavo Adolfo Chavarro Ortiz
     */
    public void handleFileUpload(int option)
    {
        //Validamos que el evento de copiado no sea nulo
        if (getPictureFile() != null)
        {

            String fileName = getPictureFile().getFileName(); //Extraemos el nombre del archivo
            long fileSize = getPictureFile().getSize(); //Extraemos el tamaño del archivo
            int positionLimitName = fileName.indexOf("."); //Extraemos la posicion del delimitar del tipo del archivo
            String fileType = fileName.substring(positionLimitName + 1, fileName.length()); //Extraemos el tipo del archivo
            //Validamos que el archivo contenga los tipos permitidos
            if (DMESConstants.TYPES_EXTENTIONS_IMAGES.contains(fileType))
            {
                String folderName = DMESConstants.FILE_PATH_INPUTS_IMG;
                //Creamos el folder
                File folder = new File(PATH_FILE + "/" + folderName);
                folder.mkdirs();
                //Creamos el archivo con la ruta y el nombre de la carpeta
                File file = new File(folder + "/" + fileName);
                try
                {
                    //Creamos el archivo y lo enviamos al metodo que lo escribe
                    if (writeFile(getPictureFile().getInputstream(), file))
                    {
                        switch (option)
                        {
                            case 1://opción para guardar
                                getReplacementSave().setPathPicture(file.getAbsolutePath());
                                break;
                            case 2://opción para actualizar
                                getReplacementSelected().setPathPicture(file.getAbsolutePath());
                                break;
                            default:
                                break;
                        }
                        //addInfo(null, DMESConstants.MESSAGE_TITTLE_SUCCES, DMESConstants.MESSAGE_SUCCES);
                    }
                    //Si sucede un error al escribir el archivo
                    else
                    {
                        addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
                    }
                }
                catch (Exception e)
                {
                    //Excepción de escritura
                    addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
                    log.error("Error al intentar guardar la imagen", e);
                }
            }
            //El tipo no pertenece
            else
            {
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, "El archivo no pertenece a los tipos permitidos " + DMESConstants.TYPES_EXTENTIONS_IMAGES);
            }
        }
        else
        {
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, "El archivo se encuentra vacio");
        }
        switch (option)
        {
            case 1://opción para guardar
                RequestContext.getCurrentInstance().execute("PF('pictureSave').hide()");
                RequestContext.getCurrentInstance().execute("PF('dialogReplacementSave').show()");
                break;
            case 2://opción para actualizar
                RequestContext.getCurrentInstance().execute("PF('pictureUpdate').hide()");
                RequestContext.getCurrentInstance().execute("PF('dialogReplacementUpdate').show()");
                break;
            default:
                break;
        }
    }

    /** 
     * Método encargado de visualizar la imagen de un elemento.
     *
     * @return String cadena con la ruta de la imagen
     * @param replacement repuesto al que se le consultará la imagen
     * @author Gustavo Chavarro Ortiz
     */
    public String searchImage(ScReplacement replacement)
    {
        try
        {
            if (replacement != null)
            {
                if (!Utilities.isEmpty(replacement.getPathPicture())) 
                {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(replacement.getPathPicture())));
                    //la constante me permite mapear imagenes externas
                    return DMESConstants.PATH_EXTERN_PICTURES + replacement.getPathPicture();
                }
            }
        }
        catch (Exception e)
        {
            addError(null, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR, "La imagen no existe");
        }
        return DMESConstants.PATH_IMAGE_DEFAULT;
    }

    /**
     * Método encargado de limpiar los campos para eliminar un repuesto
     *
     * @param replacement repuesto a eliminar
     * @author Gustavo Chavarro Ortiz
     */
    public void selectedForDelete(ScReplacement replacement)
    {
        cleansTypesMeasures();
        setReplacementSelected(replacement);
    }

    /**
     * Método encargado de limpiar los campos para actualizar un repuesto
     *
     * @param replacement repuesto a actualizarlo
     * @author Gustavo Chavarro Ortiz
     */
    public void selectedForUpdate(ScReplacement replacement)
    {
        cleansTypesMeasures();

        try
        {
            //setReplacementSelected((ScReplacement) replacement.clone());
            setReplacementSelected(replacement);
//            setReplacementSelected(getScReplacementServer().getReplacementsById(replacement.getIdReplacement()));
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar el repuesto a actualizar", e);
        }

        fillListReplacementLocation(replacement.getStock().getIdStore());
        if (!Utilities.isEmpty(getReplacementSelected().getDimension().getHight()))
        {
            valueToList(getReplacementSelected().getDimension().getHight(), 1);
        }
        if (!Utilities.isEmpty(getReplacementSelected().getDimension().getWidth()))
        {
            valueToList(getReplacementSelected().getDimension().getWidth(), 2);
        }
        if (!Utilities.isEmpty(getReplacementSelected().getDimension().getLarge()))
        {
            valueToList(getReplacementSelected().getDimension().getLarge(), 3);
        }
        if (!Utilities.isEmpty(getReplacementSelected().getDimension().getWeight()))
        {
            valueToList(getReplacementSelected().getDimension().getWeight(), 4);
        }
        if (!Utilities.isEmpty(getReplacementSelected().getDimension().getVolume()))
        {
            valueToList(getReplacementSelected().getDimension().getVolume(), 5);
        }
        if (!Utilities.isEmpty(getReplacementSelected().getDimension().getThickness()))
        {
            valueToList(getReplacementSelected().getDimension().getThickness(), 6);
        }
        if (!Utilities.isEmpty(getReplacementSelected().getDimension().getRadio()))
        {
            valueToList(getReplacementSelected().getDimension().getRadio(), 7);
        }
    }

    /**
     * Método encargado de tomar un valor de un combo box unido por una coma (,)
     * y ponerlo en los dos campos que aplican número y tipo de valor.
     *
     * @param value valor en cadena de texto a convertir
     * @param option opción por la cual se hará la conversión dependiendo del
     * campo
     * @author Gustavo Chavarro Ortiz
     */
    public void valueToList(String value, int option)
    {
        String fields[] = value.split("-"); //Dividimos el valor y su unidad

        switch (option)//Seleccionamos la opción
        {

            case 1://Ajustamos la altura

                getReplacementSelected().getDimension().setHight(fields[0]);
                for (ScMeasureUnit measureUnit : getMeasureUnitsList())
                {
                    if (measureUnit.getAcronym().equals(fields[1]))
                    {
                        setMeasureUnitSaveHigh(measureUnit);
                        break;
                    }
                }
                break;
            case 2://Ajustamos el ancho

                getReplacementSelected().getDimension().setWidth(fields[0]);
                for (ScMeasureUnit measureUnit : getMeasureUnitsList())
                {
                    if (measureUnit.getAcronym().equals(fields[1]))
                    {
                        setMeasureUnitSaveWidth(measureUnit);
                        break;
                    }
                }
                break;
            case 3://Ajustamos el largo

                getReplacementSelected().getDimension().setLarge(fields[0]);
                for (ScMeasureUnit measureUnit : getMeasureUnitsList())
                {
                    if (measureUnit.getAcronym().equals(fields[1]))
                    {
                        setMeasureUnitSaveLarge(measureUnit);
                        break;
                    }
                }
                break;
            case 4://Ajustamos el Peso

                getReplacementSelected().getDimension().setWeight(fields[0]);
                for (ScMeasureUnit measureUnit : getMeasureUnitsList())
                {
                    if (measureUnit.getAcronym().equals(fields[1]))
                    {
                        setMeasureUnitSaveWeight(measureUnit);
                        break;
                    }
                }
                break;
            case 5://Ajustamos el Volumen

                getReplacementSelected().getDimension().setVolume(fields[0]);
                for (ScMeasureUnit measureUnit : getMeasureUnitsList())
                {
                    if (measureUnit.getAcronym().equals(fields[1]))
                    {
                        setMeasureUnitSaveVolume(measureUnit);
                        break;
                    }
                }
                break;
            case 6://Ajustamos el Grosor

                getReplacementSelected().getDimension().setThickness(fields[0]);
                for (ScMeasureUnit measureUnit : getMeasureUnitsList())
                {
                    if (measureUnit.getAcronym().equals(fields[1]))
                    {
                        setMeasureUnitSaveThickness(measureUnit);
                        break;
                    }
                }
                break;
            case 7://Ajustamos el Radio

                getReplacementSelected().getDimension().setRadio(fields[0]);
                for (ScMeasureUnit measureUnit : getMeasureUnitsList())
                {
                    if (measureUnit.getAcronym().equals(fields[1]))
                    {
                        setMeasureUnitSaveRadio(measureUnit);
                        break;
                    }
                }
                break;
            default://Caso por defecto
                break;
        }

    }


    /**
     * Método encargado de realizar la copia del archivo que se desea cargar.
     *
     * @param option Evento que trae el archvio cargado al servidor
     * @author: Gustavo Adolfo Chavarro Ortiz
     */
    public void handleDocumentUpload(int option)
    {
        //Validamos que el evento de copiado no sea nulo
        int bytesToMegabytes = 10485760; //Valor de representación de 1megabytes a bytes
        if (!Utilities.isEmpty(getReplaceDocumentsSave().getDocumentTittle()))
        {
            if (getFileSave() != null)
            {
                String fileName = getFileSave().getFileName(); //Extraemos el nombre del archivo
                long fileSize = getFileSave().getSize(); //Extraemos el tamaño del archivo
                int positionLimitName = fileName.indexOf("."); //Extraemos la posicion del delimitar del tipo del archivo
                String fileType = fileName.substring(positionLimitName + 1, fileName.length()); //Extraemos el tipo del archivo
                if (fileSize > 0)
                {
                    //Validamos que el archivo cumpla con el tamaño permitido
                    if (fileSize <= (MAX_SIZE_FILE * bytesToMegabytes))
                    {
                        //Validamos que el archivo contenga los tipos permitidos
                        if (EXTENSION_FILE.contains(fileType))
                        {
                            String firstName = getSessionBean().getScUser().getIdPerson().getFirstName().replaceAll(" ", "_");
                            String lastName = getSessionBean().getScUser().getIdPerson().getLastName().replaceAll(" ", "_");
                            String folderName = DMESConstants.FILE_PATH_INPUTS_DOCS;
                            //Creamos el folder
                            File folder = new File(PATH_FILE + "/" + folderName);
                            folder.mkdirs();
                            //Creamos el archivo con la ruta y el nombre de la carpeta
                            File file = new File(folder + "/" + fileName);
                            try
                            {
                                //Creamos el archivo y lo enviamos al metodo que lo escribe
                                if (writeFile(getFileSave().getInputstream(), file))
                                {
                                    getReplaceDocumentsSave().setDocumentName(fileName);
                                    getReplaceDocumentsSave().setDocumentPath(folder.toString());
                                    getReplaceDocumentsSave().setTypeDocument(getFileSave().getContentType());
                                    getReplaceDocumentsSave().setUploadBy(getSessionBean().getScUser().getLogin());

                                    getReplaceDocumentsSave().setCreationDate(new Date());
                                    switch (option)
                                    {
                                        case 1://opción para guardar
                                            getReplaceDocumentsSave().setReplacement(getReplacementSave());
                                            getDocumentsListSave().add(getReplaceDocumentsSave());

                                            break;
                                        case 2://opción para actualizar
                                            getReplaceDocumentsSave().setReplacement(getReplacementSelected());
                                            getReplacementSelected().getReplacementDocuments().add(getReplaceDocumentsSave());

                                            break;
                                        default:
                                            break;
                                    }
                                }
                                //Si sucede un error al escribir el archivo
                                else
                                {
                                    addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
                                }
                            }
                            catch (Exception e)
                            {
                                //Excepción de escritura
                                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
                                log.error("Error al itnentar crear un nuevo archivo", e);
                            }
                        }
                        //El tipo no pertenece
                        else
                        {
                            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, "El archivo no pertenece a los tipos permitidos " + EXTENSION_FILE);
                        }
                    }
                    else
                    {
                        addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, "El archivo supera el límite de tamaño permitido " + MAX_SIZE_FILE + " MB");
                    }
                }
                else
                {
                    addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, "El archivo se encuentra vacio");
                }
            }
        }
        else
        {
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, "Debe ingresar un título para el documento");
        }
        switch (option)
        {
            case 1://opción para guardar
                RequestContext.getCurrentInstance().execute("PF('documentSave').hide()");
                RequestContext.getCurrentInstance().execute("PF('dialogReplacementSave').show()");
                break;
            case 2://opción para actualizar
                RequestContext.getCurrentInstance().execute("PF('documentUpdate').hide()");
                RequestContext.getCurrentInstance().execute("PF('dialogReplacementUpdate').show()");
                break;
            default:
                break;
        }
        cleanDocumentSave();
    }

    /**
     * Método encargado de limpiar todas las variables temporales.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void cleanDocumentSave()
    {
        setReplaceDocumentsSave(new ScReplacementDocuments());
    }

    /**
     * Método encargado de consultar los parámetros iniciales, para cargar
     * archivos.
     *
     * @author: Gustavo Adolfo Chavarro Ortiz
     */
    public void getinitalParameters()
    {
        try
        {
            //Consultamos la tabla de parámetros iniciales
            Object object = getScReplacementServer().getInitialParameters();
            //Extraemos la información en un arreglo
            Object[] data = (Object[]) object;
            if (data != null)
            {
                //Extraemos el valor por defecto del tamaño de los archivos
                if (data.length > 0 && data[0] != null && !Utilities.isEmpty(data[0].toString()))
                {
                    MAX_SIZE_FILE = Integer.parseInt(data[0].toString());
                }
                //Extraemos el valor por defecto de los tipos permitidos
                if (data.length > 1 && data[1] != null && !Utilities.isEmpty(data[1].toString()))
                {
                    EXTENSION_FILE = data[1].toString();
                }
                //Extraemos el valor por defecto la ruta donde se guardarán
                if (data.length > 2 && data[2] != null && !Utilities.isEmpty(data[2].toString()))
                {
                    PATH_FILE = data[2].toString();
                }
            }
        }
        catch (Exception ex)
        {
            log.error("Error al intentar consultar los parámetros iniciales", ex);
        }
    }

    /**
     * Método encargado de permitir descargar los archivos que se encuentran en
     * la tabla del usuario.
     *
     * @param scDocumentsSelected registro del documento a descargar
     * @author Gustavo Chavarro Ortiz
     */
    public void downloadDocument(ScReplacementDocuments scDocumentsSelected)
    {
        try
        {
            String fileName = scDocumentsSelected.getDocumentName();
            String path = scDocumentsSelected.getDocumentPath();
            int positionLimitName = fileName.indexOf("."); //Extraemos la posicion del delimitar del tipo del archivo
            String fileType = fileName.substring(positionLimitName + 1, fileName.length()); //Extraemos el tipo del archivo
            File fileToDownload = new File(path + "/" + fileName);
            InputStream inputStream = new FileInputStream(fileToDownload);
            byte[] buffer = new byte[2048];
            int offset = 0;
            int numRead = 0;
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext().getResponse();
            response.setContentType(scDocumentsSelected.getTypeDocument());
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            OutputStream responseOutputStream = response.getOutputStream();

            while ((numRead = inputStream.read(buffer)) > 0)
            {
                responseOutputStream.write(buffer, 0, numRead);
            }
            inputStream.close();
            response.getOutputStream().flush();
            response.getOutputStream().close();
            FacesContext.getCurrentInstance().responseComplete();
        }
        catch (Exception e)
        {
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            log.error("Error al intentar descargar el archivo", e);
        }

    }

    /**
     * Método encargado de recibir una entrada de datos y un archivo para
     * posteriormente escribir los datos en el archivo.
     *
     * @param dataIn entrada de datos a escribir
     * @param newFile archivo nuevo en el que se escribiran los datos
     * @return valor booleano indicando si el proceso de escritura se realizó
     * satisfactoriamente
     * @author: Gustavo Adolfo Chavarro Ortiz
     */
    public boolean writeFile(InputStream dataIn, File newFile)
    {
        boolean result = false;
        try
        {
            OutputStream outputStream = new FileOutputStream(newFile);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = dataIn.read(buffer)) > 0)
            {
                outputStream.write(buffer, 0, len);
            }
            outputStream.flush();
            outputStream.close();
            dataIn.close();
            result = true;
        }
        catch (IOException e)
        {
            log.error("Error al intentar crear el archivo, metodo writeFile", e);
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

    public List<ScReplacement> getReplacementList()
    {
        return replacementList;
    }

    public void setReplacementList(List<ScReplacement> replacementList)
    {
        this.replacementList = replacementList;
    }

    public ScReplacement getReplacementSelected()
    {
        return replacementSelected;
    }

    public void setReplacementSelected(ScReplacement replacementSelected)
    {
        this.replacementSelected = replacementSelected;
    }

    public ScReplacement getReplacementSave()
    {
        return replacementSave;
    }

    public void setReplacementSave(ScReplacement replacementSave)
    {
        this.replacementSave = replacementSave;
    }

    public ScMeasureUnit getMeasureUnitSave()
    {
        return measureUnitSave;
    }

    public void setMeasureUnitSave(ScMeasureUnit measureUnitSave)
    {
        this.measureUnitSave = measureUnitSave;
    }

    public ScMeasureUnit getMeasureUnitSaveHigh()
    {
        return measureUnitSaveHigh;
    }

    public void setMeasureUnitSaveHigh(ScMeasureUnit measureUnitSaveHigh)
    {
        this.measureUnitSaveHigh = measureUnitSaveHigh;
    }

    public ScMeasureUnit getMeasureUnitSaveWidth()
    {
        return measureUnitSaveWidth;
    }

    public void setMeasureUnitSaveWidth(ScMeasureUnit measureUnitSaveWidth)
    {
        this.measureUnitSaveWidth = measureUnitSaveWidth;
    }

    public ScMeasureUnit getMeasureUnitSaveRadio()
    {
        return measureUnitSaveRadio;
    }

    public void setMeasureUnitSaveRadio(ScMeasureUnit measureUnitSaveRadio)
    {
        this.measureUnitSaveRadio = measureUnitSaveRadio;
    }

    public ScMeasureUnit getMeasureUnitSaveLarge()
    {
        return measureUnitSaveLarge;
    }

    public void setMeasureUnitSaveLarge(ScMeasureUnit measureUnitSaveLarge)
    {
        this.measureUnitSaveLarge = measureUnitSaveLarge;
    }

    public ScMeasureUnit getMeasureUnitSaveVolume()
    {
        return measureUnitSaveVolume;
    }

    public void setMeasureUnitSaveVolume(ScMeasureUnit measureUnitSaveVolume)
    {
        this.measureUnitSaveVolume = measureUnitSaveVolume;
    }

    public ScMeasureUnit getMeasureUnitSaveThickness()
    {
        return measureUnitSaveThickness;
    }

    public void setMeasureUnitSaveThickness(ScMeasureUnit measureUnitSaveThickness)
    {
        this.measureUnitSaveThickness = measureUnitSaveThickness;
    }

    public ScMeasureUnit getMeasureUnitSaveWeight()
    {
        return measureUnitSaveWeight;
    }

    public void setMeasureUnitSaveWeight(ScMeasureUnit measureUnitSaveWeight)
    {
        this.measureUnitSaveWeight = measureUnitSaveWeight;
    }

    public ScLocation getReplacementLocationSave()
    {
        return replacementLocationSave;
    }

    public void setReplacementLocationSave(ScLocation replacementLocationSave)
    {
        this.replacementLocationSave = replacementLocationSave;
    }

    public ScLocation getReplacementLocationSelected()
    {
        return replacementLocationSelected;
    }

    public void setReplacementLocationSelected(ScLocation replacementLocationSelected)
    {
        this.replacementLocationSelected = replacementLocationSelected;
    }

    public ScStore getStoreSave()
    {
        return storeSave;
    }

    public void setStoreSave(ScStore storeSave)
    {
        this.storeSave = storeSave;
    }

    public ScStore getStoreSelected()
    {
        return storeSelected;
    }

    public void setStoreSelected(ScStore storeSelected)
    {
        this.storeSelected = storeSelected;
    }

    public ScPriority getPrioritySave()
    {
        return prioritySave;
    }

    public void setPrioritySave(ScPriority prioritySave)
    {
        this.prioritySave = prioritySave;
    }

    public SessionBean getSessionBean()
    {
        return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean)
    {
        this.sessionBean = sessionBean;
    }

    public UploadedFile getPictureFile()
    {
        return pictureFile;
    }

    public void setPictureFile(UploadedFile pictureFile)
    {
        this.pictureFile = pictureFile;
    }

    public ScCostCenter getCostCenterSave()
    {
        return costCenterSave;
    }

    public void setCostCenterSave(ScCostCenter costCenterSave)
    {
        this.costCenterSave = costCenterSave;
    }

    public ScReplacementAttached getReplacementAttachedSave()
    {
        return replacementAttachedSave;
    }

    public void setReplacementAttachedSave(ScReplacementAttached replacementAttachedSave)
    {
        this.replacementAttachedSave = replacementAttachedSave;
    }

    public ScReplacementDocuments getReplaceDocumentsSave()
    {
        return replaceDocumentsSave;
    }

    public void setReplaceDocumentsSave(ScReplacementDocuments replaceDocumentsSave)
    {
        this.replaceDocumentsSave = replaceDocumentsSave;
    }

    public ScMoney getMoneySave()
    {
        return moneySave;
    }

    public void setMoneySave(ScMoney moneySave)
    {
        this.moneySave = moneySave;
    }

    public List<ScPartner> getPartnersList()
    {
        return partnersList;
    }

    public void setPartnersList(List<ScPartner> partnersList)
    {
        this.partnersList = partnersList;
    }

    public List<ScCostCenter> getCostCenterList()
    {
        return costCenterList;
    }

    public void setCostCenterList(List<ScCostCenter> costCenterList)
    {
        this.costCenterList = costCenterList;
    }

    public List<ScMeasureUnit> getMeasureUnitsList()
    {
        return measureUnitsList;
    }

    public void setMeasureUnitsList(List<ScMeasureUnit> measureUnitsList)
    {
        this.measureUnitsList = measureUnitsList;
    }

    public List<ScLocation> getReplacementLocationsList()
    {
        return replacementLocationsList;
    }

    public void setReplacementLocationsList(List<ScLocation> replacementLocationsList)
    {
        this.replacementLocationsList = replacementLocationsList;
    }

    public List<ScStore> getStoresList()
    {
        return storesList;
    }

    public void setStoresList(List<ScStore> storesList)
    {
        this.storesList = storesList;
    }

    public List<ScPriority> getPriorityList()
    {
        return priorityList;
    }

    public void setPriorityList(List<ScPriority> priorityList)
    {
        this.priorityList = priorityList;
    }

    public List<ScReplacementAttached> getAttachedListSave()
    {
        return attachedListSave;
    }

    public void setAttachedListSave(List<ScReplacementAttached> attachedListSave)
    {
        this.attachedListSave = attachedListSave;
    }

    public List<ScReplacementDocuments> getDocumentsListSave()
    {
        return documentsListSave;
    }

    public void setDocumentsListSave(List<ScReplacementDocuments> documentsListSave)
    {
        this.documentsListSave = documentsListSave;
    }

    public List<ScMoney> getMoneyList()
    {
        return moneyList;
    }

    public void setMoneyList(List<ScMoney> moneyList)
    {
        this.moneyList = moneyList;
    }

    public UploadedFile getFileSave()
    {
        return fileSave;
    }

    public void setFileSave(UploadedFile fileSave)
    {
        this.fileSave = fileSave;
    }

    public UploadedFile getFileUpdate()
    {
        return fileUpdate;
    }

    public void setFileUpdate(UploadedFile fileUpdate)
    {
        this.fileUpdate = fileUpdate;
    }

    public IScReplacement getScReplacementServer()
    {
        return scReplacementServer;
    }

    public void setScReplacementServer(IScReplacement scReplacementServer)
    {
        this.scReplacementServer = scReplacementServer;
    }

    public List<ScTime> getTimeList()
    {
        return timeList;
    }

    public void setTimeList(List<ScTime> timeList)
    {
        this.timeList = timeList;
    }

   
}
