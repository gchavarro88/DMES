/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.beans.resources.materials;

import com.sip.dmes.beans.SessionBean;
import com.sip.dmes.dao.bo.IScProductFormulation;
import com.sip.dmes.dao.bs.ScProductFormulationDao;
import com.sip.dmes.entitys.ScCostCenter;
import com.sip.dmes.entitys.ScDistributionUnit;
import com.sip.dmes.entitys.ScInput;
import com.sip.dmes.entitys.ScInputDimension;
import com.sip.dmes.entitys.ScLocation;

import com.sip.dmes.entitys.ScMeasureUnit;
import com.sip.dmes.entitys.ScMoney;
import com.sip.dmes.entitys.ScPackingUnit;
import com.sip.dmes.entitys.ScPartner;
import com.sip.dmes.entitys.ScPriority;
import com.sip.dmes.entitys.ScProductAttached;
import com.sip.dmes.entitys.ScProductDocuments;
import com.sip.dmes.entitys.ScProductFormulation;
import com.sip.dmes.entitys.ScStore;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
public class ScProductFormulationBean 
{

    //Declaración de Variables
    private List<ScProductFormulation> productList;//Lista de productos de la tabla
    private ScProductFormulation productSelected; //Producto seleccionado para consulta, edición o eliminación
    private ScProductFormulation productSave; //Producto seleccionado para agregar
    private ScMeasureUnit measureUnitSave; //Unidad de medida seleccionado para agregar
    private ScMeasureUnit measureUnitSaveHigh; //Unidad de medida seleccionado para agregar
    private ScMeasureUnit measureUnitSaveWidth; //Unidad de medida seleccionado para agregar
    private ScMeasureUnit measureUnitSaveRadio; //Unidad de medida seleccionado para agregar
    private ScMeasureUnit measureUnitSaveLarge; //Unidad de medida seleccionado para agregar
    private ScMeasureUnit measureUnitSaveVolume; //Unidad de medida seleccionado para agregar
    private ScMeasureUnit measureUnitSaveThickness; //Unidad de medida seleccionado para agregar
    private ScMeasureUnit measureUnitSaveWeight; //Unidad de medida seleccionado para agregar
    
    private ScPackingUnit packingUnitSave; //Unidad de empaque seleccionado para agregar
    
    private ScDistributionUnit distributionUnitSave; //Unidad de empaque seleccionado para agregar
    private ScPackingUnit packingUnitSelected; //Unidad de empaque seleccionado para agregar al producto
    private ScLocation inputLocationSave; //Localizacion seleccionada para agregar
    private ScLocation inputLocationSelected; //Localizacion seleccionada para agregar al producto
    private ScStore storeSave; //Localizacion seleccionada para agregar
    private ScStore storeSelected; //Localizacion seleccionada para agregar al producto
    private ScPriority prioritySave; //Prioridad seleccionada para agregar al producto
    private SessionBean sessionBean; //Bean de sesion
    private UploadedFile pictureFile; //Archivo que se copiara para la imagen del producto
    private ScCostCenter costCenterSave; //Centro de Costo para agregar
    private ScProductAttached SpecificationsSave;//Especificación a guardar
    private ScProductAttached feacturesSave;//Característica a guardar
    private ScProductAttached observationsSave;//Observación a guardar
    private ScProductDocuments documentsSave;//Documento a guardar
    private ScMoney moneySave;//Moneda a guardar
    private List<ScPartner> partnersList;//Listado de proveedores
    private List<ScCostCenter> costCenterList;//Listado de centros de costo
    private List<ScMeasureUnit> measureUnitsList;//Lista de unidades de medida
    private List<ScPackingUnit> packingUnitsList;//Lista de unidades de empaque
    private List<ScDistributionUnit> distributionUnitsList;//Lista de unidades de distribución
    private List<ScLocation> inputLocationsList;//Lista de localizaciones
    private List<ScStore> storesList;//Lista de almacenes
    private List<ScPriority> priorityList;//Lista de prioridades
    private List<ScProductAttached> specificationListSave;//Lista de especificaciones a guardar
    private List<ScProductAttached> feacturesListSave;//Lista de características a guardar
    private List<ScProductAttached> observationListSave;//Lista de observaciones a guardar
    
    private List<ScProductDocuments> documentsListSave;//Lista de documentos a guardar
    private List<ScMoney> moneyList;//Lista de monedas
    private UploadedFile fileSave;//Documento a subir
    private UploadedFile fileUpdate;//Documento a actualizar
    
    //Persistencia
    private IScProductFormulation scProductFormulationServer; //Dao de persistencia del productos
    
    
    private final static Logger log = Logger.getLogger(ScProductFormulationDao.class);
   
    //Constantes
    
    //Tabs
    private final String TAB_GENERAL = "tabGeneral";
    private final String TAB_DIMENSION = "tabDimension";
    private final String TAB_SPECIFICATIONS = "tabSpecifications";
    private final String TAB_FEACTURES = "tabFeactures";
    private final String TAB_OBSERVATIONS = "tabObservations";
    private final String TAB_CONFIRM_SAVE = "tabConfirmSave";
    
    //files
    private int MAX_SIZE_FILE = 5;//Tamaño en megas del archivo
    private String EXTENSION_FILE = "pdf,xls,doc,xlsx,docx,txt,pps,ppt,pptx,ppsx";
    private String PATH_FILE = System.getProperty("user.home"); //Obtenemos la ruta del servidor
    /**
     * Creates a new instance of ScProductFormulationBean
     */
    public ScProductFormulationBean() 
    {
        
    }
    
    /**
     * Método encargado de mostrar los datos iniciales.
     */
    @PostConstruct
    public void initData()
    {
        fillListProducts();
        fillListPartners();
        fillListCostCenter();
        fillListPackingUnit();
        fillListStore();
        fillListPriority();
        fillListMeasure();
        fillListMoney();
        fillListDistribucionUnit();
        cleanFieldsInit();
        getinitalParameters();
    }
    
    /**
     * Método encargado de llenar la lista de productos.
     * @author Gustavo Chavarro Ortiz
     */
    public void fillListProducts()
    {
        try
        { 
            //Se consultan todos los productos y se guardan en la lista ordenados por la fecha
            setProductList(getScProductFormulationServer().getAllProductFormulations());
        }
        catch(Exception e)
        {
            log.error("Error al intentar consultar los productos de la tabla", e);
        }
    }

    /**
     * Método encargado de llenar la lista de proveedores.
     * @author Gustavo Chavarro Ortiz
     */
    public void fillListPartners()
    {
        try
        {
            //Se consultan todos los proveedores existentes
            setPartnersList(getScProductFormulationServer().getAllPartners());
        }
        catch(Exception e)
        {
            log.error("Error al intentar consultar los proveedores para los productos", e);
        }
    }
    
    /**
     * Método encargado de llenar la lista de centros de costo.
     * @author Gustavo Chavarro Ortiz
     */
    public void fillListCostCenter()
    {
        try
        {
            //Se consultan todos los proveedores existentes
            setCostCenterList(getScProductFormulationServer().getAllCostCenter());
        }
        catch(Exception e)
        {
            log.error("Error al intentar consultar los proveedores para los productos", e);
        }
    }
    
    /**
     * Método encargado de llenar la lista de unidades de distribución.
     * @author Gustavo Chavarro Ortiz
     */
    public void fillListDistribucionUnit()
    {
        try
        {
            //Se consultan todos los proveedores existentes
            setDistributionUnitsList(getScProductFormulationServer().getAllDistributionUnits());
        }
        catch(Exception e)
        {
            log.error("Error al intentar consultar las unidades de distribucion para los productos", e);
        }
    }
    
    /**
     * Método encargado de llenar la lista de unidades de empaque.
     * @author Gustavo Chavarro Ortiz
     */
    public void fillListPackingUnit()
    {
        try
        {
            //Se consultan todos los proveedores existentes
            setPackingUnitsList(getScProductFormulationServer().getAllPackingUnits());
        }
        catch(Exception e)
        {
            log.error("Error al intentar consultar las unidades de empaque para los productos", e);
        }
    }
    
    /**
     * Método encargado de llenar la lista de localizaciones.
     * @param store almacen que contiene las localizaciones
     * @author Gustavo Chavarro Ortiz
     */ 
    public void fillListInputLocation(ScStore store)
    {
        try
        {
            //Se consultan todos los proveedores existentes
            setInputLocationsList(getScProductFormulationServer().getAllInputLocations(store));
        }
        catch(Exception e)
        {
            log.error("Error al intentar consultar las localizaciones para los productos", e);
        }
    }
    
    /**
     * Método encargado de llenar la lista de almacenes.
     * @author Gustavo Chavarro Ortiz
     */
    public void fillListStore()
    {
        try
        {
            //Se consultan todos almacenes disponibles
            setStoresList(getScProductFormulationServer().getAllStores());
        }
        catch(Exception e)
        {
            log.error("Error al intentar consultar los almacenes para los productos", e);
        }
    }
    
    /**
     * Método encargado de llenar la lista de prioridades.
     * @author Gustavo Chavarro Ortiz
     */
    public void fillListPriority()
    {
        try
        {
            //Se consultan todos almacenes disponibles
            setPriorityList(getScProductFormulationServer().getAllPrioritys());
        }
        catch(Exception e)
        {
            log.error("Error al intentar consultar las prioridades para los productos", e);
        }
    }
    
    /**
     * Método encargado de llenar la lista de medidas.
     * @author Gustavo Chavarro Ortiz
     */
    public void fillListMeasure()
    {
        try
        {
            //Se consultan todos almacenes disponibles
            setMeasureUnitsList(getScProductFormulationServer().getAllMeasureUnits());
        }
        catch(Exception e)
        {
            log.error("Error al intentar consultar las medidas para los productos", e);
        }
    }
    
    /**
     * Método encargado de llenar la lista de medidas.
     * @author Gustavo Chavarro Ortiz
     */
    public void fillListMoney()
    {
        try
        {
            //Se consultan todas las monedas disponibles
            setMoneyList(getScProductFormulationServer().getAllMoneys());
        }
        catch(Exception e)
        {
            log.error("Error al intentar consultar las monedas para los productos", e);
        }
    }
    
    /**
     * Método encargado de vaciar los objetos.
     * @author Gustavo Chavarro Ortiz
     */
    public void cleanFieldsInit()
    {
        
        setProductSelected(new ScProductFormulation());
        setCostCenterSave(new ScCostCenter());
        setPackingUnitSave(new ScPackingUnit());
        setDistributionUnitSave(new ScDistributionUnit());
        setInputLocationSave(new ScLocation());
        setMeasureUnitSave(new ScMeasureUnit());
        cleanDocumentSave();
        cleanProductSave();
        setDocumentsListSave(new ArrayList<ScProductDocuments>());
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
    public void cleanProductSave()
    {
        setProductSave(new ScProductFormulation());
        
        //Creamos el objeto de dimension para la segunda pestaña
        getProductSave().setLocation(new ScLocation(0L));
        getProductSave().setDimension(new ScInputDimension());
        cleanListSaves();
        cleansTypesMeasures();
    }
    
    
    /**
     * Método encargado de inicializar todas las listas para crear un producto.
     * @author Gustavo Chavarro Ortiz
     */
    public void cleanListSaves()
    {
        setSpecificationsSave(new ScProductAttached());
        setFeacturesSave(new ScProductAttached());
        setObservationsSave(new ScProductAttached());
        
        if(getSpecificationListSave() == null)
        {
            setSpecificationListSave(new ArrayList<ScProductAttached>());
        }
        if(getFeacturesListSave() == null)
        {
            setFeacturesListSave(new ArrayList<ScProductAttached>());
        }
        if(getObservationListSave() == null)
        {
            setObservationListSave(new ArrayList<ScProductAttached>());
        }
        
    }
    /**
     * Método encargado de vaciar los objetos.
     * @author Gustavo Chavarro Ortiz
     */
    public void cleanFieldsCostCenter()
    {
        setCostCenterSave(new ScCostCenter());
    }
    
    /**
     * Método encargado de limpiar todas las variables temporales.
     * @author Gustavo Chavarro Ortiz
     */
    public void cleanDocumentSave()
    {
        setDocumentsSave(new ScProductDocuments());
    }
    
    /**
     * Método encargado de vaciar los objetos.
     * @author Gustavo Chavarro Ortiz
     */
    public void cleanFieldsMeasure()
    {
        setMeasureUnitSave(new ScMeasureUnit());
    }
    
    /**
     * Método encargado de vaciar los objetos.
     * @author Gustavo Chavarro Ortiz
     */
    public void cleanFieldsPackingUnit()
    {
        setPackingUnitSave(new ScPackingUnit());
    }
    
    /**
     * Método encargado de vaciar los objetos.
     * @author Gustavo Chavarro Ortiz
     */
    public void cleanFieldsDistributionUnit()
    {
        setDistributionUnitSave(new ScDistributionUnit());
    }
    
    /**
     * Método encargado de vaciar los objetos.
     * @author Gustavo Chavarro Ortiz
     */
    public void cleanFieldsStores()
    {
        setStoreSave(new ScStore());
    }
    
    /**
     * Método encargado de vaciar los objetos.
     * @author Gustavo Chavarro Ortiz
     */
    public void cleanFieldsPriority()
    {
        setPrioritySave(new ScPriority());
    }
    
    /**
     * Método encargado de vaciar los objetos.
     * @author Gustavo Chavarro Ortiz
     */
    public void cleanFieldsLocationInput()
    {
        setInputLocationSave(new ScLocation());
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * Método encargado de guardar temporalmente una especificación.
     * @author Gustavo Chavarro Ortiz
     */
    public void saveSpecification()
    {
        if(getSpecificationsSave() != null)
        {
            if(!Utilities.isEmpty(getSpecificationsSave().getTittle()) && 
                    !Utilities.isEmpty(getSpecificationsSave().getDescription()))
            {
                if(getSpecificationListSave() != null)
                {
                    //Guardamos exitosamente la especificación
                    getSpecificationsSave().setProductFormulation(getProductSave());
                    getSpecificationsSave().setType("ESPECIFICACION");
                    getSpecificationListSave().add(getSpecificationsSave());
                    setSpecificationsSave(new ScProductAttached());
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
     * Método encargado de borrar una especificación agregada a la lista para 
     * guardar un inusmo.
     * @param productSpecifications especificación a borrar
     * @author Gustavo Chavarro Ortiz
     */
    public void deleteSpecifications(ScProductAttached productSpecifications)
    {
        if(getSpecificationListSave() != null && !getSpecificationListSave().isEmpty())
        {
            int index = 0; //Posición del objeto que se eliminará
            for(ScProductAttached iterator: getSpecificationListSave())
            {
                if(iterator.getTittle().equals(productSpecifications.getTittle()) && 
                        iterator.getType().equals(productSpecifications.getType()) &&
                        iterator.getDescription().equals(productSpecifications.getDescription()))
                {
                    break;//Rompempos el ciclo
                }
                index++;//Aumentamos la posición
            }
            getSpecificationListSave().remove(index);//Removemos el elemento en la posición hallada
        }
        else
        {
            addInfo(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            log.error(DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR+", "+DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
        }
    }
    /**
     * Método encargado de guardar temporalmente una característica.
     * @author Gustavo Chavarro Ortiz
     */
    public void saveFeacture()
    {
        if(getFeacturesSave() != null)
        {
            if(!Utilities.isEmpty(getFeacturesSave().getTittle()) && 
                    !Utilities.isEmpty(getFeacturesSave().getDescription()))
            {
                if(getFeacturesListSave() != null)
                {
                    //Guardamos exitosamente la especificación
                    getFeacturesSave().setProductFormulation(getProductSave());
                    getFeacturesSave().setType("CARACTERISTICA");
                    getFeacturesListSave().add(getFeacturesSave());
                    setFeacturesSave(new ScProductAttached());
                }    
                else
                {
                    addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
                    log.error(DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR+", "+DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
                }
            }
            else
            {
                addError(null, "Error al intentar guardar una característica", 
                            "Debe ingresar los campos Título y Descripción de la característica");
                    log.error("Error al intentar guardar una especificación, "
                            + "Debe ingresar los campos Título y Descripción de la característica");
            }
        }
        else
        {
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            log.error(DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR+", "+DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
        }
    }
    /**
     * Método encargado de borrar una característica agregada a la lista para 
     * guardar un inusmo.
     * @param feacture característica a borrar
     * @author Gustavo Chavarro Ortiz
     */
    public void deleteFeactures(ScProductAttached feacture)
    {
        if(getFeacturesListSave() != null && !getFeacturesListSave().isEmpty())
        {
            int index = 0; //Posición del objeto que se eliminará
            for(ScProductAttached iterator: getFeacturesListSave())
            {
                if(iterator.getTittle().equals(feacture.getTittle()) &&
                        iterator.getType().equals(feacture.getType()) &&
                        iterator.getDescription().equals(feacture.getDescription()))
                {
                    break;//Rompempos el ciclo
                }
                index++;//Aumentamos la posición
            }
            getFeacturesListSave().remove(index);//Removemos el elemento en la posición hallada
        }
        else
        {
            addInfo(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            log.error(DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR+", "+DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
        }
    }
    
    /**
     * Método encargado de guardar temporalmente una observación.
     * @author Gustavo Chavarro Ortiz
     */
    public void saveObservation()
    {
        if(getObservationsSave()!= null)
        {
            if(!Utilities.isEmpty(getObservationsSave().getTittle()) && 
                    !Utilities.isEmpty(getObservationsSave().getDescription()))
            {
                if(getFeacturesListSave() != null)
                {
                    //Guardamos exitosamente la observación
                    getObservationsSave().setProductFormulation(getProductSave());
                    getObservationsSave().setType("OBSERVACION");
                    getObservationListSave().add(getObservationsSave());
                    setObservationsSave(new ScProductAttached());
                }
                else
                {
                    addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
                    log.error(DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR+", "+DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
                }
            }
            else
            {
                addError(null, "Error al intentar guardar una observación", 
                            "Debe ingresar los campos Título y Descripción de la observación");
                    log.error("Error al intentar guardar una observación, "
                            + "Debe ingresar los campos Título y Descripción de la observación");
            }
        }
        else
        {
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            log.error(DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR+", "+DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
        }
    }
    /**
     * Método encargado de borrar una observación agregada a la lista para 
     * guardar un inusmo.
     * @param observations observación a borrar
     * @author Gustavo Chavarro Ortiz
     */
    public void deleteObservation(ScProductAttached observations)
    {
        if(getObservationListSave()!= null && !getObservationListSave().isEmpty())
        {
            int index = 0; //Posición del objeto que se eliminará
            for(ScProductAttached iterator: getObservationListSave())
            {
                if(iterator.getTittle().equals(observations.getTittle()) &&
                        iterator.getType().equals(observations.getType()) &&
                        iterator.getDescription().equals(observations.getDescription()))
                {
                    break;//Rompempos el ciclo
                }
                index++;//Aumentamos la posición
            }
            getObservationListSave().remove(index);//Removemos el elemento en la posición hallada
        }
        else
        {
            addInfo(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            log.error(DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR+", "+DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
        }
    }
    
    /**
     * Método encargado de borrar un documento agregada a la lista para 
     * guardar un inusmo.
     * @param documents documento a borrar
     * @author Gustavo Chavarro Ortiz
     */
    public void deleteDocument(ScProductDocuments documents)
    {
        if(getDocumentsListSave()!= null && !getDocumentsListSave().isEmpty())
        {
            int index = 0; //Posición del objeto que se eliminará
            for(ScProductDocuments iterator: getDocumentsListSave())
            {
                if(iterator.getDocumentTittle().equals(documents.getDocumentTittle()) &&
                        iterator.getDocumentPath().equals(documents.getDocumentPath()))
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
            log.error(DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR+", "+DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
        }
    }
    
    /**
     * Método encargado de borrar un documento agregada a la lista para 
     * guardar un inusmo.
     * @param documents documento a borrar
     * @author Gustavo Chavarro Ortiz
     */
    public void deleteDocumentUpdate(ScProductDocuments documents)
    {
        if(getProductSelected().getScProductDocuments()!= null && !getProductSelected().getScProductDocuments().isEmpty())
        {
            int index = 0; //Posición del objeto que se eliminará
            for(ScProductDocuments iterator: getProductSelected().getScProductDocuments())
            {
                if(iterator.getDocumentTittle().equals(documents.getDocumentTittle()) &&
                        iterator.getDocumentPath().equals(documents.getDocumentPath()))
                {
                    break;//Rompempos el ciclo
                }
                index++;//Aumentamos la posición
            }
            getProductSelected().getScProductDocuments().remove(index);//Removemos el elemento en la posición hallada
        }
        else
        {
            addInfo(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            log.error(DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR+", "+DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
        }
    }
    
    /**
     * Método encargado de agregar un centro de costos.
     * @author Gustavo Chavarro Ortiz
     */
    public void addCostCenter()
    {
        try 
        {
            if(getCostCenterSave() != null)
            {
                if(Utilities.isNumeric(getCostCenterSave().getCostCenter()))
                {
                    getCostCenterSave().setCreationDate(new Date());
                    getScProductFormulationServer().saveCostCenter(getCostCenterSave());
                    if(getCostCenterList() == null)
                    {
                        setCostCenterList(new ArrayList<ScCostCenter>());
                    }
                    getCostCenterList().add(getCostCenterSave());
                    cleanFieldsCostCenter();
                }
                else
                {
                    log.error("Error al intentar crear el centro de costos desde productos");
                    addError(null, "Error al crear un centro de costos", "Debe ingresar solo números para el campo código del centro de costo");
                }
            }
            else
            {
                log.error("Error al intentar crear el centro de costos desde productos");
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }
            
        }
        catch (Exception e)
        {
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            log.error("Error al intentar agregar un centro de costos desde productos",e);
        }
    
    }
    
    /**
     * Método encargado de agregar una medida.
     * @author Gustavo Chavarro Ortiz
     */
    public void addMeasure()
    {
        try 
        {
            if(getMeasureUnitSave() != null)
            {
                getScProductFormulationServer().saveMeasureUnit(getMeasureUnitSave());
                if(getMeasureUnitsList() == null)
                {
                    setMeasureUnitsList(new ArrayList<ScMeasureUnit>());
                }
                getMeasureUnitsList().add(getMeasureUnitSave());
                cleanFieldsMeasure();
            }
            else
            {
                log.error("Error al intentar crear la unidad de medida para productos");
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }
            
        }
        catch (Exception e)
        {
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            log.error("Error al intentar agregar una unidad de medida desde productos",e);
        }
    
    }
    
    /**
     * Método encargado de agregar una unidad de empaque
     * @author Gustavo Chavarro Ortiz
     */
    public void addPackingUnit()
    {
        try 
        {
            if(getPackingUnitSave()!= null)
            {
                
                getScProductFormulationServer().savePackingUnit(getPackingUnitSave());
                if(getPackingUnitsList() == null)
                {
                    setPackingUnitsList(new ArrayList<ScPackingUnit>());
                }
                getPackingUnitsList().add(getPackingUnitSave());
                cleanFieldsPackingUnit();
                
            }
            else
            {
                log.error("Error al intentar crear la unidad de empaque desde productos");
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }
            
        }
        catch (Exception e)
        {
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            log.error("Error al intentar crear la unidad de empaque desde productos",e);
        }
    
    }
    
    
    /**
     * Método encargado de agregar una unidad de localizacion
     * @author Gustavo Chavarro Ortiz
     */
    public void addInputLocations()
    {
        try 
        {
            if(getInputLocationSave()!= null)
            {
                ScStore storeLocation = null;
                //Agregamos el almacen de la localizacion
                if(getProductSave().getLocation().getStore() != null)
                {
                    storeLocation = getProductSave().getLocation().getStore();
                }
                //Para una actualizacion de datos
                else 
                {
                    storeLocation = getProductSelected().getLocation().getStore();
                }
                getInputLocationSave().setStore(storeLocation);
                //Se realiza la persistencia de la localizacion
                getScProductFormulationServer().saveLocation(getInputLocationSave());
                if(getInputLocationsList() == null)
                {
                    setInputLocationsList(new ArrayList<ScLocation>());
                }
                getInputLocationsList().add(getInputLocationSave());
                cleanFieldsLocationInput();
            }
            else
            {
                log.error("Error al intentar crear la localización desde productos");
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }
            
        }
        catch (Exception e)
        {
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            log.error("Error al intentar crear la localización desde productos",e);
        }
    }
    
    /**
     * Método encargado de llevar el flujo al guardar un productos.
     * @param event evento en el cual se encuentra el asistente para crear productos
     * @return String al final retorna el nombre de la siguiente pestaña del asistente
     * @author Gustavo Chavarro Ortiz
     */
    public String onFlowProcessSaveProduct(FlowEvent event) 
    {    
        int packingUnit = -1;
        if(event.getNewStep().equals(TAB_GENERAL))
        {
            return TAB_GENERAL;
        }
        if(event.getOldStep().equals(TAB_GENERAL))
        {
            if(validateFields("Nombre Producto", getProductSave().getDescription(), 3))
            {
                return event.getOldStep();
            }
            if(validateFields("Tiempo de Preparación", getProductSave().getManufacturingTime()+"", 2))
            {
                return event.getOldStep();
            }
            if(validateFields("Tipo de Material", getProductSave().getTypeMaterial(), 3))
            {
                return event.getOldStep();
            }
            //Validamos que el valor sea mayor que cero
            if(validateFields("Valor", getProductSave().getValue()+"", 2))
            {
                return event.getOldStep();
            }
            if(validateFields("Moneda", getProductSave().getMoney(), 4))
            {
                return event.getOldStep();
            }
            
            
            
            if(validateFields("Marca", getProductSave().getMark(), 3))
            {
                return event.getOldStep();
            }
            
            if(Utilities.isEmpty(getProductSave().getPathPicture()))
            { 
                getProductSave().setPathPicture(" ");//Setteamos la ruta de la imagen
            }
            if(validateFields("Serie", getProductSave().getSerie(), 3))
            {
                return event.getOldStep();
            }
            
            //Validamos los campos seleccionables
            if(validateFields("Proveedor y Garantía", getProductSave().getSupplierGuarantee(), 4))
            {
                return event.getOldStep();
            }
            if(validateFields("Centro de Costos", getProductSave().getCostCenter(), 4))
            {
                return event.getOldStep();
            }
            if(validateFields("Almacen", getProductSave().getLocation().getStore(), 4))
            {
                return event.getOldStep();
            }
            if(validateFields("Localización", getProductSave().getLocation(), 4))
            {
                return event.getOldStep();
            }
            if(validateFields("Prioridad", getProductSave().getPriority(), 4))
            {
                return event.getOldStep();
            }
            if(validateFields("Unidad de Empaque", getProductSave().getPackingUnit(), 4))
            {
                return event.getOldStep();
            }
            
                        
            //Agregamos la fecha de creación del producto
            getProductSave().setCreationDate(new Date());
            
            //Validamos que la fecha de expiracion sea mayor que la fecha de creacion
            if(getProductSave().getExpiryDate() != null && getProductSave().getExpiryDate().before(getProductSave().getCreationDate()))
            {
                addError(null, "Error en el campo Fecha de Expiración", "La Fecha de Expiración debe ser mayor que la fecha actual");
                log.error("Error en el campo Unidad de Empaque, El Valor Unidad de Empaque debe ser un número mayor a cero");
                return event.getOldStep();
            }
            
            
        }
        //Si pasamos de la pestaña de dimensiones
        else if(event.getOldStep().equals(TAB_DIMENSION))
        {
            
            
            if(validateFields("Altura", getProductSave().getDimension().getHight(), 1))
            {
                return event.getOldStep();
            }
            else if(getMeasureUnitSaveHigh() == null)
            {
                addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para la Altura");
                return event.getOldStep();
            }
            if(validateFields("Ancho", getProductSave().getDimension().getWidth(), 1))
            {
                return event.getOldStep();
            }
            else if(getMeasureUnitSaveWidth()== null)
            {
                addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Ancho");
                return event.getOldStep();
            }
            if(validateFields("Largo", getProductSave().getDimension().getLarge(), 1))
            {
                return event.getOldStep();
            }
            else if(getMeasureUnitSaveLarge()== null)
            {
                addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Largo");
                return event.getOldStep();
            }
            if(validateFields("Peso", getProductSave().getDimension().getWeight(), 1))
            {
                return event.getOldStep();
            }
            else if(getMeasureUnitSaveWeight()== null)
            {
                addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Peso");
                return event.getOldStep();
            }
            if(!Utilities.isEmpty(getProductSave().getDimension().getVolume()))
            {
                if(validateFields("Volumen", getProductSave().getDimension().getVolume(), 1))
                {
                    return event.getOldStep();
                }
                else if(getMeasureUnitSaveVolume()== null)
                {
                    addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Volumen");
                    return event.getOldStep();
                }
            }
            if(!Utilities.isEmpty(getProductSave().getDimension().getThickness()))
            {
                if(validateFields("Grosor", getProductSave().getDimension().getThickness(), 1))
                {
                    return event.getOldStep();
                }
                else if(getMeasureUnitSaveThickness()== null)
                {
                    addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Grosor");
                    return event.getOldStep();
                }
            }
            if(!Utilities.isEmpty(getProductSave().getDimension().getRadio()))
            {
                if(validateFields("Radio", getProductSave().getDimension().getRadio(), 1))
                {
                    return event.getOldStep();
                }
                else if(getMeasureUnitSaveRadio()== null)
                {
                    addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Radio");
                    return event.getOldStep();
                }
            }
            if(!Utilities.isEmpty(getProductSave().getDimension().getWeight()))
            {
                if(validateFields("Peso", getProductSave().getDimension().getWeight(), 1))
                {
                    return event.getOldStep();
                }
                else if(getMeasureUnitSaveWeight()== null)
                {
                    addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Peso");
                    return event.getOldStep();
                }
            }
        }
        
        return event.getNewStep(); 
    }
    
    
    /**
     * Método encargado de realizar la copia del archivo que se desea cargar.
     * @param option se escoge la opción entre guardar y actualizar
     * @author: Gustavo Adolfo Chavarro Ortiz
     */
    public void handleFileUpload(int option) {
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
                String folderName = DMESConstants.FILE_PATH_PRODUCTS_IMG;
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
                                getProductSave().setPathPicture(file.getAbsolutePath());
                                break;
                            case 2://opción para actualizar
                                getProductSelected().setPathPicture(file.getAbsolutePath());
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
                RequestContext.getCurrentInstance().execute("PF('dialogproductSave').show()");
                break;
            case 2://opción para actualizar
                RequestContext.getCurrentInstance().execute("PF('pictureUpdate').hide()");
                RequestContext.getCurrentInstance().execute("PF('dialogProductUpdate').show()");
                break;
            default:
                break;
        }
    }
    
    /**
     * Método encargado de realizar la copia del archivo que se desea cargar.
     * @param option Evento que trae el archvio cargado al servidor
     * @author: Gustavo Adolfo Chavarro Ortiz
     */
    public void handleDocumentUpload(int option) 
    {
         //Validamos que el evento de copiado no sea nulo
        int bytesToMegabytes = 10485760; //Valor de representación de 1megabytes a bytes
        if(!Utilities.isEmpty(getDocumentsSave().getDocumentTittle()))
        {
            if(getFileSave() != null ) 
         { 
            String fileName = getFileSave().getFileName(); //Extraemos el nombre del archivo
            long fileSize    = getFileSave().getSize(); //Extraemos el tamaño del archivo
            int positionLimitName = fileName.indexOf("."); //Extraemos la posicion del delimitar del tipo del archivo
            String fileType = fileName.substring(positionLimitName+1, fileName.length()); //Extraemos el tipo del archivo
            if(fileSize > 0)
            {
                //Validamos que el archivo cumpla con el tamaño permitido
                if(fileSize <=(MAX_SIZE_FILE*bytesToMegabytes))
                {
                    //Validamos que el archivo contenga los tipos permitidos
                    if(EXTENSION_FILE.contains(fileType))
                    {
                        String firstName = getSessionBean().getScUser().getIdPerson().getFirstName().replaceAll(" ", "_");
                        String lastName = getSessionBean().getScUser().getIdPerson().getLastName().replaceAll(" ", "_");
                        String folderName = DMESConstants.FILE_PATH_PRODUCTS_DOCS;
                        //Creamos el folder
                        File folder = new File(PATH_FILE+"/"+folderName);
                        folder.mkdirs();
                        //Creamos el archivo con la ruta y el nombre de la carpeta
                        File file = new File(folder+"/"+fileName);
                        try
                        {
                            //Creamos el archivo y lo enviamos al metodo que lo escribe
                            if(writeFile(getFileSave().getInputstream(), file))
                            {
                                getDocumentsSave().setDocumentName(fileName);
                                getDocumentsSave().setDocumentPath(folder.toString());
                                getDocumentsSave().setTypeDocument(getFileSave().getContentType());
                                getDocumentsSave().setUploadBy(getSessionBean().getScUser().getLogin());
                                
                                getDocumentsSave().setCreationDate(new Date());
                                switch (option)
                                {
                                    case 1://opción para guardar
                                        getDocumentsSave().setProductFormulation(getProductSave());
                                        getDocumentsListSave().add(getDocumentsSave());
                                        
                                        break;
                                    case 2://opción para actualizar
                                        getDocumentsSave().setProductFormulation(getProductSelected());
                                        getProductSelected().getScProductDocuments().add(getDocumentsSave());
                                        
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
                        addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, "El archivo no pertenece a los tipos permitidos "+EXTENSION_FILE);
                    }
                }
                else
                {
                    addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, "El archivo supera el límite de tamaño permitido "+MAX_SIZE_FILE+" MB");
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
                RequestContext.getCurrentInstance().execute("PF('dialogproductSave').show()");
                break;
            case 2://opción para actualizar
                RequestContext.getCurrentInstance().execute("PF('documentUpdate').hide()");
                RequestContext.getCurrentInstance().execute("PF('dialogProductUpdate').show()");
                break;
            default:
                break;
        }
         cleanDocumentSave();
    }
    
    
    /**
     * Método encargado de visualizar la imagen de un elemento.
     * @return String cadena con la ruta de la imagen
     * @param input producto al que se le consultará la imagen
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
    
    
    
    
    
    
    
    /**
     * Método encargado de realizar la persistencia de un producto, guardando
     * listas y objetos incluidos en el.
     * @author Gustavo Chavarro Ortiz
     */
    public void saveProductFormulation()
    {
        //Valido que el producto no sea nulo
        Set<ScProductAttached> set = new HashSet<ScProductAttached>(); 
        if(getProductSave() != null)
        {
            if(getSpecificationListSave() != null)
            {
                //Le agrego la lista de especificaciones
                set.addAll(getSpecificationListSave());
            }
            if(getFeacturesListSave()!= null)
            {
                //Le agrego la lista de características
                set.addAll(getFeacturesListSave());
            } 
            if(getObservationListSave()!= null)
            {
                //Le agrego la lista de observaciones
                set.addAll(getObservationListSave());
            }
            if(getDocumentsListSave()!= null)
            {
                //Le agrego la lista de observaciones
                getProductSave().setScProductAttached(new ArrayList<>(set));
                getProductSave().setScProductDocuments(getDocumentsListSave());
            }
            //Almacenamos el producto
            try
            {
                if(getMeasureUnitSaveHigh() != null)
                {
                    getProductSave().getDimension().setHight(getProductSave().getDimension().getHight()+"-"+getMeasureUnitSaveHigh().getAcronym());
                }
                if(getMeasureUnitSaveWidth()!= null)
                {
                    getProductSave().getDimension().setWidth(getProductSave().getDimension().getWidth()+"-"+getMeasureUnitSaveWidth().getAcronym());
                }
                if(getMeasureUnitSaveLarge() != null)
                {
                    getProductSave().getDimension().setLarge(getProductSave().getDimension().getLarge()+"-"+getMeasureUnitSaveLarge().getAcronym());
                }
                if(getMeasureUnitSaveWeight()!= null)
                {
                    getProductSave().getDimension().setWeight(getProductSave().getDimension().getWeight()+"-"+getMeasureUnitSaveWeight().getAcronym());
                }
                if(getMeasureUnitSaveVolume()!= null)
                {
                    getProductSave().getDimension().setVolume(getProductSave().getDimension().getVolume()+"-"+getMeasureUnitSaveVolume().getAcronym());
                }
                if(getMeasureUnitSaveThickness()!= null)
                {
                    getProductSave().getDimension().setThickness(getProductSave().getDimension().getThickness()+"-"+getMeasureUnitSaveThickness().getAcronym());
                }
                if(getMeasureUnitSaveRadio()!= null)
                {
                    getProductSave().getDimension().setRadio(getProductSave().getDimension().getRadio()+"-"+getMeasureUnitSaveRadio().getAcronym());
                }
                getScProductFormulationServer().saveProductFormulation(getProductSave());
                getProductList().add(getProductSave());
                cleanProductSave();
            }
            catch (Exception e)
            {
                log.error("Error almacenando el producto", e);
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
                cleanProductSave();
            }
        }
    }
    /**
     * Método encargado de realizar la persistencia de un producto, actualizando
     * listas y objetos incluidos en el.
     * @author Gustavo Chavarro Ortiz
     */
    public void updateProductFormulation()
    {
        //Valido que el producto no sea nulo
        if(getProductSelected()!= null)
        {
            //Almacenamos el producto
            try
            {
                if(getMeasureUnitSaveHigh() != null)
                {
                    getProductSelected().getDimension().setHight(getProductSelected().getDimension().getHight()+"-"+getMeasureUnitSaveHigh().getAcronym());
                }
                if(getMeasureUnitSaveWidth()!= null)
                {
                    getProductSelected().getDimension().setWidth(getProductSelected().getDimension().getWidth()+"-"+getMeasureUnitSaveWidth().getAcronym());
                }
                if(getMeasureUnitSaveLarge() != null)
                {
                    getProductSelected().getDimension().setLarge(getProductSelected().getDimension().getLarge()+"-"+getMeasureUnitSaveLarge().getAcronym());
                }
                if(getMeasureUnitSaveWeight()!= null)
                {
                    getProductSelected().getDimension().setWeight(getProductSelected().getDimension().getWeight()+"-"+getMeasureUnitSaveWeight().getAcronym());
                }
                if(getMeasureUnitSaveVolume()!= null)
                {
                    getProductSelected().getDimension().setVolume(getProductSelected().getDimension().getVolume()+"-"+getMeasureUnitSaveVolume().getAcronym());
                }
                if(getMeasureUnitSaveThickness()!= null)
                {
                    getProductSelected().getDimension().setThickness(getProductSelected().getDimension().getThickness()+"-"+getMeasureUnitSaveThickness().getAcronym());
                }
                if(getMeasureUnitSaveRadio()!= null)
                {
                    getProductSelected().getDimension().setRadio(getProductSelected().getDimension().getRadio()+"-"+getMeasureUnitSaveRadio().getAcronym());
                }
                getScProductFormulationServer().updateProductFormulation(getProductSelected());
                cleanProductSave();
            }
            catch (Exception e)
            {
                log.error("Error actualizando el Producto", e);
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
                cleanProductSave();
            }
            
        }
    
    }
    
    /**
     * Método encargado de llevar el flujo al actualizar un producto.
     * @param event evento en el cual se encuentra el asistente para ver productos
     * @return String al final retorna el nombre de la siguiente pestaña del asistente
     * @author Gustavo Chavarro Ortiz
     */
    public String onFlowProcessViewProduct(FlowEvent event) 
    {    
        
        return event.getNewStep(); 
    }
    /**
     * Método encargado de limpiar los campos para eliminar un producto
     * @param product producto a eliminar
     * @author Gustavo Chavarro Ortiz
     */
    public void selectedForDelete(ScProductFormulation product) 
    {
        cleansTypesMeasures();
        setProductSelected(product);
    }
    
    /**
     * Método encargado de limpiar los campos para actualizar un producto
     * @param product producto a actualizaro 
     * @author Gustavo Chavarro Ortiz
     */
    public void selectedForUpdate(ScProductFormulation product) 
    {
        cleansTypesMeasures();
        setProductSelected(product);
        try
        {
            setProductSelected(getScProductFormulationServer().getProductsById(product.getIdProductFormulation()));
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar el producto a actualizar", e);
        }
        
        fillListInputLocation(product.getLocation().getStore());   
        if(!Utilities.isEmpty(getProductSelected().getDimension().getHight()))
        {
            valueToList(getProductSelected().getDimension().getHight(), 1);
        }
        if(!Utilities.isEmpty(getProductSelected().getDimension().getWidth()))
        {
            valueToList(getProductSelected().getDimension().getWidth(), 2);
        }
        if(!Utilities.isEmpty(getProductSelected().getDimension().getLarge()))
        {
            valueToList(getProductSelected().getDimension().getLarge(), 3);
        }
        if(!Utilities.isEmpty(getProductSelected().getDimension().getWeight()))
        {
            valueToList(getProductSelected().getDimension().getWeight(), 4);
        }
        if(!Utilities.isEmpty(getProductSelected().getDimension().getVolume()))
        {
            valueToList(getProductSelected().getDimension().getVolume(), 5);
        }
        if(!Utilities.isEmpty(getProductSelected().getDimension().getThickness()))
        {
            valueToList(getProductSelected().getDimension().getThickness(), 6);
        }
        if(!Utilities.isEmpty(getProductSelected().getDimension().getRadio()))
        {
            valueToList(getProductSelected().getDimension().getRadio(), 7);
        }
    }
    
       
    /**
     * Método encargado de llevar el flujo al actualizar un producto.
     * @param event evento en el cual se encuentra el asistente para actualizar productos
     * @return String al final retorna el nombre de la siguiente pestaña del asistente
     * @author Gustavo Chavarro Ortiz
     */
    public String onFlowProcessUpdateProduct(FlowEvent event) 
    {    
        int packingUnit = -1;
        if(event.getNewStep().equals(TAB_GENERAL))
        {
            return TAB_GENERAL;
        }
        if(event.getOldStep().equals(TAB_GENERAL))
        {
            if(validateFields("Nombre Producto", getProductSelected().getDescription(), 3))
            {
                return event.getOldStep();
            }
            if(validateFields("Tiempo de Preparación", getProductSelected().getManufacturingTime()+"", 2))
            {
                return event.getOldStep();
            }
            if(validateFields("Tipo de Material", getProductSelected().getTypeMaterial(), 3))
            {
                return event.getOldStep();
            }
            //Validamos que el valor sea mayor que cero
            if(validateFields("Valor", getProductSelected().getValue()+"", 2))
            {
                return event.getOldStep();
            }
            if(validateFields("Moneda", getProductSelected().getMoney(), 4))
            {
                return event.getOldStep();
            }
            
            
            
            if(validateFields("Marca", getProductSelected().getMark(), 3))
            {
                return event.getOldStep();
            }
            
            if(Utilities.isEmpty(getProductSelected().getPathPicture()))
            { 
                getProductSelected().setPathPicture(" ");//Setteamos la ruta de la imagen
            }
            if(validateFields("Serie", getProductSelected().getSerie(), 3))
            {
                return event.getOldStep();
            }
            
            //Validamos los campos seleccionables
            if(validateFields("Proveedor y Garantía", getProductSelected().getSupplierGuarantee(), 4))
            {
                return event.getOldStep();
            }
            if(validateFields("Centro de Costos", getProductSelected().getCostCenter(), 4))
            {
                return event.getOldStep();
            }
            if(validateFields("Almacen", getProductSelected().getLocation().getStore(), 4))
            {
                return event.getOldStep();
            }
            if(validateFields("Localización", getProductSelected().getLocation(), 4))
            {
                return event.getOldStep();
            }
            if(validateFields("Prioridad", getProductSelected().getPriority(), 4))
            {
                return event.getOldStep();
            }
            if(validateFields("Unidad de Empaque", getProductSelected().getPackingUnit(), 4))
            {
                return event.getOldStep();
            }
            
            
            //Validamos que la fecha de expiracion sea mayor que la fecha de creacion
            if(getProductSelected().getExpiryDate() != null && getProductSelected().getExpiryDate().before(getProductSelected().getCreationDate()))
            {
                addError(null, "Error en el campo Fecha de Expiración", "La Fecha de Expiración debe ser mayor que la fecha actual");
                log.error("Error en el campo Unidad de Empaque, El Valor Unidad de Empaque debe ser un número mayor a cero");
                return event.getOldStep();
            }
        }
        //Si pasamos de la pestaña de dimensiones
        else if(event.getOldStep().equals(TAB_DIMENSION))
        {
            
            
            if(validateFields("Altura", getProductSelected().getDimension().getHight(), 1))
            {
                return event.getOldStep();
            }
            else if(getMeasureUnitSaveHigh() == null)
            {
                addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para la Altura");
                return event.getOldStep();
            }
            if(validateFields("Ancho", getProductSelected().getDimension().getWidth(), 1))
            {
                return event.getOldStep();
            }
            else if(getMeasureUnitSaveWidth()== null)
            {
                addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Ancho");
                return event.getOldStep();
            }
            if(validateFields("Largo", getProductSelected().getDimension().getLarge(), 1))
            {
                return event.getOldStep();
            }
            else if(getMeasureUnitSaveLarge()== null)
            {
                addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Largo");
                return event.getOldStep();
            }
            if(validateFields("Peso", getProductSelected().getDimension().getWeight(), 1))
            {
                return event.getOldStep();
            }
            else if(getMeasureUnitSaveWeight()== null)
            {
                addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Peso");
                return event.getOldStep();
            }
            if(!Utilities.isEmpty(getProductSelected().getDimension().getVolume()))
            {
                if(validateFields("Volumen", getProductSelected().getDimension().getVolume(), 1))
                {
                    return event.getOldStep();
                }
                else if(getMeasureUnitSaveVolume()== null)
                {
                    addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Volumen");
                    return event.getOldStep();
                }
            }
            if(!Utilities.isEmpty(getProductSelected().getDimension().getThickness()))
            {
                if(validateFields("Grosor", getProductSelected().getDimension().getThickness(), 1))
                {
                    return event.getOldStep();
                }
                else if(getMeasureUnitSaveThickness()== null)
                {
                    addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Grosor");
                    return event.getOldStep();
                }
            }
            if(!Utilities.isEmpty(getProductSelected().getDimension().getRadio()))
            {
                if(validateFields("Radio", getProductSelected().getDimension().getRadio(), 1))
                {
                    return event.getOldStep();
                }
                else if(getMeasureUnitSaveRadio()== null)
                {
                    addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Radio");
                    return event.getOldStep();
                }
            }
            if(!Utilities.isEmpty(getProductSelected().getDimension().getWeight()))
            {
                if(validateFields("Peso", getProductSelected().getDimension().getWeight(), 1))
                {
                    return event.getOldStep();
                }
                else if(getMeasureUnitSaveWeight()== null)
                {
                    addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Peso");
                    return event.getOldStep();
                }
            }
        }
        
        return event.getNewStep(); 
    }
    
    /**
     * Método encargado de retornar la lista de adjuntos dependiendo del tipo recibido.
     * @param property
     * @param attacheds 
     * @return List<ScProductAttached>
     * @author Gustavo Chavarro Ortiz
     */
    public List<ScProductAttached> getListAttached(String property, List<ScProductAttached> attacheds) 
    {
        List<ScProductAttached> result = new ArrayList<ScProductAttached>();
        for(ScProductAttached attached: attacheds)
        {
            if(attached.getType().equals(property))
            {
                result.add(attached);
            }
        }
        return result;
    }
    
    
    /**
     * Método encargado de guardar un nuevo adjunto
     * @param property
     * @param attached
     * @author Gustavo Chavarro Ortiz
     */
    public void saveAttached(String property, ScProductAttached attached) 
    {
        if(!Utilities.isEmpty(property))
        {
            if(attached != null)
            {
                attached.setType(property);
                if(!Utilities.isEmpty(attached.getTittle()) && !Utilities.isEmpty(attached.getDescription())
                        && !Utilities.isEmpty(attached.getType()))
                {
                    attached.setProductFormulation(getProductSelected());
                    getProductSelected().getScProductAttached().add(attached);
                    cleanListSaves();
                }
                else
                {
                    addError(null, "Error al intentar guardar una especificación", 
                            "Debe ingresar los campos Título y Descripción");
                    log.error("Error al intentar guardar una especificación, "
                            + "Debe ingresar los campos Título y Descripción");
                }
            }
            else
            {
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
                log.error(DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR+", "+DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }
        }
    }
    
    /**
     * Método encargado de eliminar un adjunto
     * @param attached
     * @author Gustavo Chavarro Ortiz
     */
    public void deleteAttached(ScProductAttached attached) 
    {
        if(attached != null)
        {
            for(ScProductAttached productAttached: getProductSelected().getScProductAttached())
            {
                if(attached.getTittle().equals(productAttached.getTittle()) && attached.getDescription().
                        equals(productAttached.getDescription()) && attached.getType().equals(productAttached.getType()))
                {
                    getProductSelected().getScProductAttached().remove(productAttached);
                    break;
                }
            }
        }
        else
        {
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            log.error(DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR+", "+DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
        }
    }
    
    /**
     * Método encargado de eliminar un producto.
     * @autor Gustavo Chavarro Ortiz
     */
    public void deleteProductFormulation()
    {
        if(getProductSelected() != null)
        {
            try
            {
                getScProductFormulationServer().deleteProductFormulation(getProductSelected());
                getProductList().remove(getProductSelected());
            }
            catch (Exception e)
            {
                log.error("Error eliminando el producto", e);
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
                cleanProductSave();
            }
        }
    }
    /**
     * Método encargado de verificar si se ha seleccionado un almacen
     * @param store almacen que contiene las localizaciones
     * @return boolean parametro que dice si tiene o no un almacen seleccionado
     * @author Gustavo Chavarro Ortiz
     */
    public boolean withOutStore(ScStore store)
    {
        boolean result = false;
        if(store == null || Utilities.isEmpty(store.getName()))
        {
            result = true;
        }
        else
        {
            fillListInputLocation(store);
        }
        return result;
    }
    
    /**
     * Método encargado de tomar un valor de un combo box unido por una coma (,)
     * y ponerlo en los dos campos que aplican número y tipo de valor.
     * @param value valor en cadena de texto a convertir
     * @param option opción por la cual se hará la conversión dependiendo del campo
     * @author Gustavo Chavarro Ortiz
     */
    public void valueToList(String value, int option)
    {
        String fields[] = value.split("-"); //Dividimos el valor y su unidad
        
        switch(option)//Seleccionamos la opción
        {
            
            case 1://Ajustamos la altura
                
                getProductSelected().getDimension().setHight(fields[0]);
                for(ScMeasureUnit measureUnit: getMeasureUnitsList())
                {
                    if(measureUnit.getAcronym().equals(fields[1]))
                    {
                        setMeasureUnitSaveHigh(measureUnit);
                        break;
                    }
                }
            break;
            case 2://Ajustamos el ancho
                
                getProductSelected().getDimension().setWidth(fields[0]);
                for(ScMeasureUnit measureUnit: getMeasureUnitsList())
                {
                    if(measureUnit.getAcronym().equals(fields[1]))
                    {
                        setMeasureUnitSaveWidth(measureUnit);
                        break;
                    }
                }
            break;    
            case 3://Ajustamos el largo
                
                getProductSelected().getDimension().setLarge(fields[0]);
                for(ScMeasureUnit measureUnit: getMeasureUnitsList())
                {
                    if(measureUnit.getAcronym().equals(fields[1]))
                    {
                        setMeasureUnitSaveLarge(measureUnit);
                        break;
                    }
                }
            break;
            case 4://Ajustamos el Peso
                
                getProductSelected().getDimension().setWeight(fields[0]);
                for(ScMeasureUnit measureUnit: getMeasureUnitsList())
                {
                    if(measureUnit.getAcronym().equals(fields[1]))
                    {
                        setMeasureUnitSaveWeight(measureUnit);
                        break;
                    }
                }
            break;
            case 5://Ajustamos el Volumen
                
                getProductSelected().getDimension().setVolume(fields[0]);
                for(ScMeasureUnit measureUnit: getMeasureUnitsList())
                {
                    if(measureUnit.getAcronym().equals(fields[1]))
                    {
                        setMeasureUnitSaveVolume(measureUnit);
                        break;
                    }
                }
            break;
            case 6://Ajustamos el Grosor
            
            getProductSelected().getDimension().setThickness(fields[0]);
            for(ScMeasureUnit measureUnit: getMeasureUnitsList())
            {
                if(measureUnit.getAcronym().equals(fields[1]))
                {
                    setMeasureUnitSaveThickness(measureUnit);
                    break;
                }
            }
            break;
            case 7://Ajustamos el Radio
            
            getProductSelected().getDimension().setRadio(fields[0]);
            for(ScMeasureUnit measureUnit: getMeasureUnitsList())
            {
                if(measureUnit.getAcronym().equals(fields[1]))
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
     * Método encargado de validar los campos doubles.
     * @param nameField nombre del campo a evaluar
     * @param value valor del campo a evaluar
     * @param option opción del tipo de dato que se validar
     * @return boolean si se puede validar o no 
     * @author Gustavo Chavarro Ortiz
     */
    public boolean validateFields(String nameField, Object value, int option)
    {
        boolean isInvalid = false;
        String message1 = "Error en el campo "+ nameField;
        
        try
        { 
            if(value != null && !Utilities.isEmpty( value.toString()))
            {
                switch(option) 
                {
                    case 1: //Casos de tipo double 
                    String messageDouble2 = "Debe ingresar un número mayor que cero y usar"
                            + "como separador de decimales el punto, ejemplo: 3.24";
                    try
                    {

                        double parseo = Double.parseDouble((String)value);//parseo
                        if(parseo <= 0)
                        {
                            throw new Exception(nameField+" menor o igual a cero");
                        }
                    }
                    catch (Exception e)
                    { 
                        isInvalid = true;
                        addError(null,message1, messageDouble2);
                        log.error(message1+", "+messageDouble2);
                    }   
                    break;
                    case 2: //Casos de tipo int
                    String messageInt2 = "Debe ingresar un número mayor que cero sin puntos ni comas,"
                            + "ejemplo: 1256786";
                    try
                    {

                        Long parseo = Long.parseLong(value.toString());//parseo
                        if(parseo <= 0)
                        {
                            throw new Exception(nameField+" menor o igual a cero");
                        }
                    }
                    catch (Exception e)
                    {
                        isInvalid = true;
                        addError(null,message1, messageInt2);
                        log.error(message1+", "+messageInt2);
                    }
                    break;
                    case 3: //Casos de tipo String
                    String messageString2 = "Campo obligatorio, debe ingresar algún valor";
                    if(Utilities.isEmpty(value.toString()))
                    {
                        isInvalid = true;
                        addError(null,message1+ nameField, messageString2);
                        log.error(message1+ ", "+messageString2);
                    }
                    break;    
                    case 4://Casos de campos seleccionables
                    String messageObject2 = "Campo obligatorio, debe seleccionar un valor para este campo";
                    if(value == null)
                    {
                        isInvalid = true;
                        addError(null,message1+ nameField, messageObject2);
                        log.error(message1+ ", "+messageObject2);
                    }
                    break;
                    default:
                    break;
                }
            }    
            else
            {
                String messageObject2 = "Campo obligatorio, debe ingresar un valor para el campo "+nameField;
                addError(null, message1, messageObject2);
                log.error(message1+ ", "+messageObject2);
                isInvalid = true;
            }
        }
        catch(Exception e)
        {
            //Excepción no rematada debido que hay campos con tipos diferentes a String
        }
        return isInvalid;
    }
    
    /**
     * Método encargado de permitir mostrar un combo box.
     * @param value valor del input que permite habilitar el combo box
     * @param option valor del tipo de medida
     * @return boolean valor que permite mostrar u ocultar un combo box
     * @author Gustavo Chavarro Ortiz
     */
    public boolean showItem(String value, int option)
    {
        boolean result = false;
        if(!Utilities.isEmpty(value))
        {
            try
            {
                Double number = Double.parseDouble(value);
                if(number > 0)
                {
                    result = true;
                }
            }
            catch (Exception e)
            {
                //No se realiza ninguna acción
            }
        }
        if(!result)
        {
            switch(option)
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
     * Método encargado de consultar los parámetros iniciales, para cargar archivos.
     * @author: Gustavo Adolfo Chavarro Ortiz
     */
    public void getinitalParameters()
    {
        try
        {
            //Consultamos la tabla de parámetros iniciales
            Object object = getScProductFormulationServer().getInitialParameters();
            //Extraemos la información en un arreglo
            Object[] data = (Object[]) object;
            if(data != null)
            {
                //Extraemos el valor por defecto del tamaño de los archivos
                if(data.length > 0 && data[0] != null && !Utilities.isEmpty(data[0].toString()))
                {
                    MAX_SIZE_FILE = Integer.parseInt(data[0].toString());
                }
                //Extraemos el valor por defecto de los tipos permitidos
                if(data.length > 1 && data[1] != null && !Utilities.isEmpty(data[1].toString()))
                {
                    EXTENSION_FILE = data[1].toString();
                }
                //Extraemos el valor por defecto la ruta donde se guardarán
                if(data.length > 2 && data[2] != null &&!Utilities.isEmpty(data[2].toString()))
                {
                    PATH_FILE = data[2].toString();
                }
            }
        }
        catch (Exception ex)
        {
            log.error("Error al intentar consultar los parámetros iniciales",ex);
        }
    }
       
    /**
     * Método encargado de permitir descargar los archivos que se encuentran en la 
     * tabla del usuario.
     * @param scDocumentsSelected registro del documento a descargar
     * @author Gustavo Chavarro Ortiz
     */
    public void downloadDocument(ScProductDocuments scDocumentsSelected)
    {
        try
        {
            String fileName = scDocumentsSelected.getDocumentName();
            String path     = scDocumentsSelected.getDocumentPath();
            int positionLimitName = fileName.indexOf("."); //Extraemos la posicion del delimitar del tipo del archivo
            String fileType = fileName.substring(positionLimitName+1, fileName.length()); //Extraemos el tipo del archivo
            File fileToDownload = new File(path+"/"+fileName);
            InputStream inputStream = new FileInputStream(fileToDownload);
            byte[] buffer = new byte[2048];
            int offset = 0;
            int numRead = 0;
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext().getResponse();
            response.setContentType(scDocumentsSelected.getTypeDocument());
            response.setHeader("Content-Disposition", "attachment;filename="+fileName);
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
        catch(Exception e)
        {
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            log.error("Error al intentar descargar el archivo",e);
        }
        
    
    }
    /**
     * Método encargado de recibir una entrada de datos y un archivo para posteriormente
     * escribir los datos en el archivo.
     * @param dataIn entrada de datos a escribir
     * @param newFile archivo nuevo en el que se escribiran los datos
     * @return valor booleano indicando si el proceso de escritura se realizó satisfactoriamente
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
            while((len = dataIn.read(buffer)) > 0)
            { 
                outputStream.write(buffer, 0, len);
            }
            outputStream.flush();
            outputStream.close();
            dataIn.close();
            result = true;
        }
        catch(IOException e)
        {
            log.error("Error al intentar crear el archivo, metodo writeFile",e);
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
     * Getters and Setters.
     */
    public List<ScProductFormulation> getProductList()
    {
        return productList;
    }

    public void setProductList(List<ScProductFormulation> productList)
    {
        this.productList = productList; 
    }

    public ScProductFormulation getProductSelected()
    {
        return productSelected;
    }

    public void setProductSelected(ScProductFormulation productSelected)
    {
        this.productSelected = productSelected;
    }

    public ScProductFormulation getProductSave()
    {
        return productSave;
    }

    public void setProductSave(ScProductFormulation productSave)
    {
        this.productSave = productSave;
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

    public ScPackingUnit getPackingUnitSave()
    {
        return packingUnitSave;
    }

    public void setPackingUnitSave(ScPackingUnit packingUnitSave)
    {
        this.packingUnitSave = packingUnitSave;
    }

    public ScDistributionUnit getDistributionUnitSave()
    {
        return distributionUnitSave;
    }

    public void setDistributionUnitSave(ScDistributionUnit distributionUnitSave)
    {
        this.distributionUnitSave = distributionUnitSave;
    }

    public ScPackingUnit getPackingUnitSelected()
    {
        return packingUnitSelected;
    }

    public void setPackingUnitSelected(ScPackingUnit packingUnitSelected)
    {
        this.packingUnitSelected = packingUnitSelected;
    }

    public ScLocation getInputLocationSave()
    {
        return inputLocationSave;
    }

    public void setInputLocationSave(ScLocation inputLocationSave)
    {
        this.inputLocationSave = inputLocationSave;
    }

    public ScLocation getInputLocationSelected()
    {
        return inputLocationSelected;
    }

    public void setInputLocationSelected(ScLocation inputLocationSelected)
    {
        this.inputLocationSelected = inputLocationSelected;
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

    public ScProductAttached getSpecificationsSave()
    {
        return SpecificationsSave;
    }

    public void setSpecificationsSave(ScProductAttached SpecificationsSave)
    {
        this.SpecificationsSave = SpecificationsSave;
    }

    public ScProductAttached getFeacturesSave()
    {
        return feacturesSave;
    }

    public void setFeacturesSave(ScProductAttached feacturesSave)
    {
        this.feacturesSave = feacturesSave;
    }

    public ScProductAttached getObservationsSave()
    {
        return observationsSave;
    }

    public void setObservationsSave(ScProductAttached observationsSave)
    {
        this.observationsSave = observationsSave;
    }

    public ScProductDocuments getDocumentsSave()
    {
        return documentsSave;
    }

    public void setDocumentsSave(ScProductDocuments documentsSave)
    {
        this.documentsSave = documentsSave;
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

    public List<ScPackingUnit> getPackingUnitsList()
    {
        return packingUnitsList;
    }

    public void setPackingUnitsList(List<ScPackingUnit> packingUnitsList)
    {
        this.packingUnitsList = packingUnitsList;
    }

    public List<ScDistributionUnit> getDistributionUnitsList()
    {
        return distributionUnitsList;
    }

    public void setDistributionUnitsList(List<ScDistributionUnit> distributionUnitsList)
    {
        this.distributionUnitsList = distributionUnitsList;
    }

    public List<ScLocation> getInputLocationsList()
    {
        return inputLocationsList;
    }

    public void setInputLocationsList(List<ScLocation> inputLocationsList)
    {
        this.inputLocationsList = inputLocationsList;
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

    public List<ScProductAttached> getSpecificationListSave()
    {
        return specificationListSave;
    }

    public void setSpecificationListSave(List<ScProductAttached> specificationListSave)
    {
        this.specificationListSave = specificationListSave;
    }

    public List<ScProductAttached> getFeacturesListSave()
    {
        return feacturesListSave;
    }

    public void setFeacturesListSave(List<ScProductAttached> feacturesListSave)
    {
        this.feacturesListSave = feacturesListSave;
    }

    public List<ScProductAttached> getObservationListSave()
    {
        return observationListSave;
    }

    public void setObservationListSave(List<ScProductAttached> observationListSave)
    {
        this.observationListSave = observationListSave;
    }

    public List<ScProductDocuments> getDocumentsListSave()
    {
        return documentsListSave;
    }

    public void setDocumentsListSave(List<ScProductDocuments> documentsListSave)
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

    public IScProductFormulation getScProductFormulationServer()
    {
        return scProductFormulationServer;
    }

    public void setScProductFormulationServer(IScProductFormulation scProductFormulationServer)
    {
        this.scProductFormulationServer = scProductFormulationServer;
    }

   
    
}
