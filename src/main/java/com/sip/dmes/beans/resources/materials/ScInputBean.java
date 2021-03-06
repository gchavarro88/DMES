/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.beans.resources.materials;

import com.sip.dmes.beans.SessionBean;
import com.sip.dmes.dao.bo.IScInput;
import com.sip.dmes.dao.bs.ScInputDao;
import com.sip.dmes.entitys.ScCostCenter;
import com.sip.dmes.entitys.ScDistributionUnit;
import com.sip.dmes.entitys.ScInput;
import com.sip.dmes.entitys.ScInputDimension;
import com.sip.dmes.entitys.ScInputDocuments;
import com.sip.dmes.entitys.ScInputEquivalence;
import com.sip.dmes.entitys.ScInputFeactures;
import com.sip.dmes.entitys.ScLocation;
import com.sip.dmes.entitys.ScInputObservations;
import com.sip.dmes.entitys.ScInputSpecifications;
import com.sip.dmes.entitys.ScInputStock;
import com.sip.dmes.entitys.ScMeasureUnit;
import com.sip.dmes.entitys.ScMoney;
import com.sip.dmes.entitys.ScPackingUnit;
import com.sip.dmes.entitys.ScPartner;
import com.sip.dmes.entitys.ScPriority;
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
public class ScInputBean
{

    //Declaración de Variables
    private List<ScInput> inputList;//Lista de insumos de la tabla
    private ScInput inputSelected; //Insumo seleccionado para consulta, edición o eliminación
    private ScInput inputSave; //Insumo seleccionado para agregar
    private ScMeasureUnit measureUnitSave; //Unidad de medida seleccionado para agregar
    private ScMeasureUnit measureUnitSaveHigh; //Unidad de medida seleccionado para agregar
    private ScMeasureUnit measureUnitSaveWidth; //Unidad de medida seleccionado para agregar
    private ScMeasureUnit measureUnitSaveRadio; //Unidad de medida seleccionado para agregar
    private ScMeasureUnit measureUnitSaveLarge; //Unidad de medida seleccionado para agregar
    private ScMeasureUnit measureUnitSaveVolume; //Unidad de medida seleccionado para agregar
    private ScMeasureUnit measureUnitSaveThickness; //Unidad de medida seleccionado para agregar
    private ScMeasureUnit measureUnitSaveWeight; //Unidad de medida seleccionado para agregar

    private ScPackingUnit packingUnitSave; //Unidad de empaque seleccionado para agregar
    private ScDistributionUnit distributionUnitSave; //UnidaScInputBeand de empaque seleccionado para agregar
    private ScPackingUnit packingUnitSelected; //Unidad de empaque seleccionado para agregar al insumo
    private ScLocation inputLocationSave; //Localizacion seleccionada para agregar
    private ScLocation inputLocationSelected; //Localizacion seleccionada para agregar al insumo
    private ScStore storeSave; //Localizacion seleccionada para agregar
    private ScStore storeSelected; //Localizacion seleccionada para agregar al insumo
    private ScPriority prioritySave; //Prioridad seleccionada para agregar al insumo
    private SessionBean sessionBean; //Bean de sesion
    private UploadedFile pictureFile; //Archivo que se copiara para la imagen del insumo
    private ScCostCenter costCenterSave; //Centro de Costo para agregar
    private ScInputSpecifications SpecificationsSave;//Especificación a guardar
    private ScInputFeactures feacturesSave;//Característica a guardar
    private ScInputObservations observationsSave;//Observación a guardar
    private ScInputDocuments documentsSave;//Documento a guardar
    private ScMoney moneySave;//Moneda a guardar
    private List<ScPartner> partnersList;//Listado de proveedores
    private List<ScCostCenter> costCenterList;//Listado de centros de costo
    private List<ScMeasureUnit> measureUnitsList;//Lista de unidades de medida
    private List<ScPackingUnit> packingUnitsList;//Lista de unidades de empaque
    private List<ScDistributionUnit> distributionUnitsList;//Lista de unidades de distribución
    private List<ScLocation> inputLocationsList;//Lista de localizaciones
    private List<ScStore> storesList;//Lista de almacenes
    private List<ScPriority> priorityList;//Lista de prioridades
    private List<ScInputSpecifications> specificationListSave;//Lista de especificaciones a guardar
    private List<ScInputFeactures> feacturesListSave;//Lista de características a guardar
    private List<ScInputObservations> observationListSave;//Lista de observaciones a guardar
    private List<ScInputEquivalence> equivalenceListSave;//Lista de equivalencias a guardar
    private List<ScInputDocuments> documentsListSave;//Lista de documentos a guardar
    private List<ScMoney> moneyList;//Lista de monedas
    private UploadedFile fileSave;//Documento a subir
    private UploadedFile fileUpdate;//Documento a actualizar

    //Persistencia
    private IScInput scInputServer; //Dao de persistencia del insumos

    private final static Logger log = Logger.getLogger(ScInputBean.class);

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
     * Creates a new instance of ScInputBean
     */
    public ScInputBean()
    {

    }

    /**
     * Método encargado de mostrar los datos iniciales.
     */
    @PostConstruct
    public void initData()
    {
        fillListInputs();
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
     * Método encargado de llenar la lista de insumos.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void fillListInputs()
    {
        try
        {
            //Se consultan todos los insumos y se guardan en la lista ordenados por la fecha
            setInputList(getScInputServer().getAllInputs());
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar los insumos de la tabla", e);
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
            setPartnersList(getScInputServer().getAllPartners());
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar los proveedores para los insumos", e);
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
            setCostCenterList(getScInputServer().getAllCostCenter());
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar los proveedores para los insumos", e);
        }
    }

    /**
     * Método encargado de llenar la lista de unidades de distribución.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void fillListDistribucionUnit()
    {
        try
        {
            //Se consultan todos los proveedores existentes
            setDistributionUnitsList(getScInputServer().getAllDistributionUnits());
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar las unidades de distribucion para los insumos", e);
        }
    }

    /**
     * Método encargado de llenar la lista de unidades de empaque.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void fillListPackingUnit()
    {
        try
        {
            //Se consultan todos los proveedores existentes
            setPackingUnitsList(getScInputServer().getAllPackingUnits());
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar las unidades de empaque para los insumos", e);
        }
    }

    /**
     * Método encargado de llenar la lista de localizaciones.
     *
     * @param store almacen que contiene las localizaciones
     * @author Gustavo Chavarro Ortiz
     */
    public void fillListInputLocation(ScStore store)
    {
        try
        {
            //Se consultan todos los proveedores existentes
            setInputLocationsList(getScInputServer().getAllInputLocations(store));
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar las localizaciones para los insumos", e);
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
            setStoresList(getScInputServer().getAllStores());
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar los almacenes para los insumos", e);
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
            setPriorityList(getScInputServer().getAllPrioritys());
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar las prioridades para los insumos", e);
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
            setMeasureUnitsList(getScInputServer().getAllMeasureUnits());
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar las medidas para los insumos", e);
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
            setMoneyList(getScInputServer().getAllMoneys());
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar las monedas para los insumos", e);
        }
    }

    /**
     * Método encargado de vaciar los objetos.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void cleanFieldsInit()
    {

        setInputSelected(new ScInput());
        setCostCenterSave(new ScCostCenter());
        setPackingUnitSave(new ScPackingUnit());
        setDistributionUnitSave(new ScDistributionUnit());
        setInputLocationSave(new ScLocation());
        setMeasureUnitSave(new ScMeasureUnit());
        cleanDocumentSave();
        cleanInputSave();
        setDocumentsListSave(new ArrayList<ScInputDocuments>());
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

    public void cleanInputSave()
    {
        setInputSave(new ScInput());
        //Creamos un stock para la siguiente pestaña
        getInputSave().setInputStock(new ScInputStock());
        //Creamos el objeto de dimension para la segunda pestaña
        getInputSave().setDimension(new ScInputDimension());
        cleanListSaves();
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
    public void cleanFieldsPackingUnit()
    {
        setPackingUnitSave(new ScPackingUnit());
    }

    /**
     * Método encargado de vaciar los objetos.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void cleanFieldsDistributionUnit()
    {
        setDistributionUnitSave(new ScDistributionUnit());
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
    public void cleanFieldsLocationInput()
    {
        setInputLocationSave(new ScLocation());
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
                    getScInputServer().saveCostCenter(getCostCenterSave());
                    if (getCostCenterList() == null)
                    {
                        setCostCenterList(new ArrayList<ScCostCenter>());
                    }
                    getCostCenterList().add(getCostCenterSave());
                    cleanFieldsCostCenter();
                }
                else
                {
                    log.error("Error al intentar crear el centro de costos desde insumos");
                    addError(null, "Error al crear un centro de costos", "Debe ingresar solo números para el campo código del centro de costo");
                }
            }
            else
            {
                log.error("Error al intentar crear el centro de costos desde insumos");
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }

        }
        catch (Exception e)
        {
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            log.error("Error al intentar agregar un centro de costos desde insumos", e);
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
                getScInputServer().saveMeasureUnit(getMeasureUnitSave());
                if (getMeasureUnitsList() == null)
                {
                    setMeasureUnitsList(new ArrayList<ScMeasureUnit>());
                }
                getMeasureUnitsList().add(getMeasureUnitSave());
                cleanFieldsMeasure();
            }
            else
            {
                log.error("Error al intentar crear la unidad de medida para insumos");
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }

        }
        catch (Exception e)
        {
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            log.error("Error al intentar agregar una unidad de medida desde insumos", e);
        }

    }

    /**
     * Método encargado de agregar una unidad de empaque
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void addPackingUnit()
    {
        try
        {
            if (getPackingUnitSave() != null)
            {

                getScInputServer().savePackingUnit(getPackingUnitSave());
                if (getPackingUnitsList() == null)
                {
                    setPackingUnitsList(new ArrayList<ScPackingUnit>());
                }
                getPackingUnitsList().add(getPackingUnitSave());
                cleanFieldsPackingUnit();

            }
            else
            {
                log.error("Error al intentar crear la unidad de empaque desde insumos");
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }

        }
        catch (Exception e)
        {
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            log.error("Error al intentar crear la unidad de empaque desde insumos", e);
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
            fillListInputLocation(store);
        }
        return result;
    }

    /**
     * Método encargado de agregar una unidad de localizacion
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void addInputLocations()
    {
        try
        {
            if (getInputLocationSave() != null)
            {
                ScStore storeLocation = null;
                //Agregamos el almacen de la localizacion
                if (getInputSave().getInputStock().getIdStore() != null)
                {
                    storeLocation = getInputSave().getInputStock().getIdStore();
                }
                //Para una actualizacion de datos
                else
                {
                    storeLocation = getInputSelected().getInputStock().getIdStore();
                }
                getInputLocationSave().setStore(storeLocation);
                //Se realiza la persistencia de la localizacion
                getScInputServer().saveLocationInput(getInputLocationSave());
                if (getInputLocationsList() == null)
                {
                    setInputLocationsList(new ArrayList<ScLocation>());
                }
                getInputLocationsList().add(getInputLocationSave());
                cleanFieldsLocationInput();
            }
            else
            {
                log.error("Error al intentar crear la localización desde insumos");
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }

        }
        catch (Exception e)
        {
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            log.error("Error al intentar crear la localización desde insumos", e);
        }
    }

    /**
     * Método encargado de agregar una unidad de distribución
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void addDistributionUnit()
    {
        try
        {
            if (getDistributionUnitSave() != null)
            {

                getScInputServer().saveDistributionUnit(getDistributionUnitSave());
                if (getDistributionUnitsList() == null)
                {
                    setDistributionUnitsList(new ArrayList<ScDistributionUnit>());
                }
                getDistributionUnitsList().add(getDistributionUnitSave());
                cleanFieldsDistributionUnit();

            }
            else
            {
                log.error("Error al intentar crear la unidad de distribución desde insumos");
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }

        }
        catch (Exception e)
        {
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            log.error("Error al intentar crear la unidad de distribución desde insumos", e);
        }

    }

    /**
     * Método encargado de guardar temporalmente una especificación.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void saveSpecification()
    {
        if (getSpecificationsSave() != null)
        {
            if (!Utilities.isEmpty(getSpecificationsSave().getTittle())
                    && !Utilities.isEmpty(getSpecificationsSave().getDescription()))
            {
                if (getSpecificationListSave() != null)
                {
                    //Guardamos exitosamente la especificación
                    getSpecificationsSave().setCreationDate(new Date());
                    getSpecificationsSave().setIdInput(getInputSave());
                    getSpecificationListSave().add(getSpecificationsSave());
                    setSpecificationsSave(new ScInputSpecifications());
                }
                else
                {
                    addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
                    log.error(DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR + ", " + DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
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
            log.error(DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR + ", " + DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
        }
    }

    /**
     * Método encargado de borrar una especificación agregada a la lista para
     * guardar un inusmo.
     *
     * @param inputSpecifications especificación a borrar
     * @author Gustavo Chavarro Ortiz
     */
    public void deleteSpecifications(ScInputSpecifications inputSpecifications)
    {
        if (getSpecificationListSave() != null && !getSpecificationListSave().isEmpty())
        {
            int index = 0; //Posición del objeto que se eliminará
            for (ScInputSpecifications iterator : getSpecificationListSave())
            {
                if (iterator.getTittle().equals(inputSpecifications.getTittle())
                        && iterator.getDescription().equals(inputSpecifications.getDescription()))
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
            log.error(DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR + ", " + DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
        }
    }

    /**
     * Método encargado de guardar temporalmente una característica.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void saveFeacture()
    {
        if (getFeacturesSave() != null)
        {
            if (!Utilities.isEmpty(getFeacturesSave().getTittle())
                    && !Utilities.isEmpty(getFeacturesSave().getDescription()))
            {
                if (getFeacturesListSave() != null)
                {
                    //Guardamos exitosamente la especificación
                    getFeacturesSave().setIdInput(getInputSave());
                    getFeacturesListSave().add(getFeacturesSave());
                    setFeacturesSave(new ScInputFeactures());
                }
                else
                {
                    addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
                    log.error(DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR + ", " + DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
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
            log.error(DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR + ", " + DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
        }
    }

    /**
     * Método encargado de borrar una característica agregada a la lista para
     * guardar un inusmo.
     *
     * @param feacture característica a borrar
     * @author Gustavo Chavarro Ortiz
     */
    public void deleteFeactures(ScInputFeactures feacture)
    {
        if (getFeacturesListSave() != null && !getFeacturesListSave().isEmpty())
        {
            int index = 0; //Posición del objeto que se eliminará
            for (ScInputFeactures iterator : getFeacturesListSave())
            {
                if (iterator.getTittle().equals(feacture.getTittle())
                        && iterator.getDescription().equals(feacture.getDescription()))
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
            log.error(DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR + ", " + DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
        }
    }

    /**
     * Método encargado de guardar temporalmente una observación.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void saveObservation()
    {
        if (getObservationsSave() != null)
        {
            if (!Utilities.isEmpty(getObservationsSave().getTittle())
                    && !Utilities.isEmpty(getObservationsSave().getDescription()))
            {
                if (getFeacturesListSave() != null)
                {
                    //Guardamos exitosamente la observación
                    getObservationsSave().setIdInput(getInputSave());
                    getObservationListSave().add(getObservationsSave());
                    setObservationsSave(new ScInputObservations());
                }
                else
                {
                    addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
                    log.error(DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR + ", " + DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
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
            log.error(DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR + ", " + DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
        }
    }

    /**
     * Método encargado de borrar una observación agregada a la lista para
     * guardar un inusmo.
     *
     * @param observations observación a borrar
     * @author Gustavo Chavarro Ortiz
     */
    public void deleteObservation(ScInputObservations observations)
    {
        if (getObservationListSave() != null && !getObservationListSave().isEmpty())
        {
            int index = 0; //Posición del objeto que se eliminará
            for (ScInputObservations iterator : getObservationListSave())
            {
                if (iterator.getTittle().equals(observations.getTittle())
                        && iterator.getDescription().equals(observations.getDescription()))
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
    public void deleteDocument(ScInputDocuments documents)
    {
        if (getDocumentsListSave() != null && !getDocumentsListSave().isEmpty())
        {
            int index = 0; //Posición del objeto que se eliminará
            for (ScInputDocuments iterator : getDocumentsListSave())
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
    public void deleteDocumentUpdate(ScInputDocuments documents)
    {
        if (getInputSelected().getScInputDocuments() != null && !getInputSelected().getScInputDocuments().isEmpty())
        {
            int index = 0; //Posición del objeto que se eliminará
            for (ScInputDocuments iterator : getInputSelected().getScInputDocuments())
            {
                if (iterator.getDocumentTittle().equals(documents.getDocumentTittle())
                        && iterator.getDocumentPath().equals(documents.getDocumentPath()))
                {
                    break;//Rompempos el ciclo
                }
                index++;//Aumentamos la posición
            }
            getInputSelected().getScInputDocuments().remove(index);//Removemos el elemento en la posición hallada
        }
        else
        {
            addInfo(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            log.error(DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR + ", " + DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
        }
    }

    /**
     * Método encargado de llevar el flujo al guardar un insumo.
     *
     * @param event evento en el cual se encuentra el asistente para crear
     * insumos
     * @return String al final retorna el nombre de la siguiente pestaña del
     * asistente
     * @author Gustavo Chavarro Ortiz
     */
    public String onFlowProcessSaveInput(FlowEvent event)
    {
        int packingUnit = -1;
        if (event.getNewStep().equals(TAB_GENERAL))
        {
            return TAB_GENERAL;
        }
        if (event.getOldStep().equals(TAB_GENERAL))
        {
            if (validateFields("Nombre Insumo", getInputSave().getDescription(), 3))
            {
                return event.getOldStep();
            }
            if (validateFields("Tipo de Material", getInputSave().getTypeMaterial(), 3))
            {
                return event.getOldStep();
            }
            //Validamos que el valor sea mayor que cero
            if (validateFields("Valor", getInputSave().getValue() + "", 2))
            {
                return event.getOldStep();
            }
            if (validateFields("Moneda", getInputSave().getMoney(), 4))
            {
                return event.getOldStep();
            }
            //modificamos el precio por unidad
            getInputSave().getInputStock().setPriceUnit((double) getInputSave().getValue());
            //modificamos el precio total igual al precio por unidad * el stock actual
            getInputSave().getInputStock().setTotalValue((double) getInputSave().getValue()
                    * getInputSave().getInputStock().getCurrentStock());

            if (validateFields("Marca", getInputSave().getMark(), 3))
            {
                return event.getOldStep();
            }

            if (Utilities.isEmpty(getInputSave().getPathPicture()))
            {
                getInputSave().setPathPicture(" ");//Setteamos la ruta de la imagen
            }
            if (validateFields("Serie", getInputSave().getSerie(), 3))
            {
                return event.getOldStep();
            }

            //Validamos los campos seleccionables
            if (validateFields("Proveedor y Garantía", getInputSave().getSupplierGuarantee(), 4))
            {
                return event.getOldStep();
            }
            if (validateFields("Centro de Costos", getInputSave().getCostCenter(), 4))
            {
                return event.getOldStep();
            }
            if (validateFields("Almacen", getInputSave().getInputStock().getIdStore(), 4))
            {
                return event.getOldStep();
            }
            if (validateFields("Localización", getInputSave().getInputLocation(), 4))
            {
                return event.getOldStep();
            }
            if (validateFields("Prioridad", getInputSave().getPriority(), 4))
            {
                return event.getOldStep();
            }
            if (validateFields("Unidad de Empaque", getInputSave().getPackingUnit(), 4))
            {
                return event.getOldStep();
            }
            if (validateFields("Cantidad de Distribución", getInputSave().getDistributionAmount(), 2))
            {
                return event.getOldStep();
            }
            //Obtenemos el precio de unidad de unidades de distribución
            getInputSave().setDistributionValue(Utilities.Redondear((getInputSave().getInputStock().
                    getPriceUnit() / getInputSave().getDistributionAmount()), 2));
            if (validateFields("Unidad de Distribución", getInputSave().getDistributionUnit(), 4))
            {
                return event.getOldStep();
            }

            //Agregamos la fecha de creación del insumo
            getInputSave().setCreationDate(new Date());

            //Validamos que la fecha de expiracion sea mayor que la fecha de creacion
            if (getInputSave().getExpiryDate() != null && getInputSave().getExpiryDate().before(getInputSave().getCreationDate()))
            {
                addError(null, "Error en el campo Fecha de Expiración", "La Fecha de Expiración debe ser mayor que la fecha actual");
                log.error("Error en el campo Unidad de Empaque, El Valor Unidad de Empaque debe ser un número mayor a cero");
                return event.getOldStep();
            }

        }
        //Si pasamos de la pestaña de datos generales a stock y dimensiones
        else if (event.getOldStep().equals(TAB_STOCK))
        {
            //Validamos que ninguno de los campos del stock sea vacio o negativo
            if (validateFields("Stock Máximo", getInputSave().getInputStock().getMaximeStock(), 2))
            {
                return event.getOldStep();
            }
            if (validateFields("Stocguschaork Mínimo", getInputSave().getInputStock().getMinimeStock(), 2))
            {
                return event.getOldStep();
            }
            if (getInputSave().getInputStock().getMaximeStock() <= getInputSave().getInputStock().getMinimeStock())
            {
                addError(null, "Error en el Stock del Insumo", "El Stock Máximo debe ser mayor que el Stock Mínimo");
                return event.getOldStep();
            }
            if (validateFields("Stock Real", getInputSave().getInputStock().getCurrentStock(), 2))
            {
                return event.getOldStep();
            }

            if (validateFields("Stock Óptimo", getInputSave().getInputStock().getOptimeStock(), 2))
            {
                return event.getOldStep();
            }
            else
            {
                if (getInputSave().getInputStock().getMaximeStock() < getInputSave().getInputStock().getOptimeStock())
                {
                    addError(null, "Error en el Stock del Insumo", "El Stock Máximo debe ser mayor que el Stock Óptimo");
                    return event.getOldStep();
                }
                if (getInputSave().getInputStock().getMinimeStock() > getInputSave().getInputStock().getOptimeStock())
                {
                    addError(null, "Error en el Stock del Insumo", "El Stock Mínimo debe ser menor que el Stock Óptimo");
                    return event.getOldStep();
                }
            }

            if (validateFields("Altura", getInputSave().getDimension().getHight(), 1))
            {
                return event.getOldStep();
            }
            else if (getMeasureUnitSaveHigh() == null)
            {
                addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para la Altura");
                return event.getOldStep();
            }
            if (validateFields("Ancho", getInputSave().getDimension().getWidth(), 1))
            {
                return event.getOldStep();
            }
            else if (getMeasureUnitSaveWidth() == null)
            {
                addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Ancho");
                return event.getOldStep();
            }
            if (validateFields("Largo", getInputSave().getDimension().getLarge(), 1))
            {
                return event.getOldStep();
            }
            else if (getMeasureUnitSaveLarge() == null)
            {
                addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Largo");
                return event.getOldStep();
            }
            if (validateFields("Peso", getInputSave().getDimension().getWeight(), 1))
            {
                return event.getOldStep();
            }
            else if (getMeasureUnitSaveWeight() == null)
            {
                addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Peso");
                return event.getOldStep();
            }
            if (!Utilities.isEmpty(getInputSave().getDimension().getVolume()))
            {
                if (validateFields("Volumen", getInputSave().getDimension().getVolume(), 1))
                {
                    return event.getOldStep();
                }
                else if (getMeasureUnitSaveVolume() == null)
                {
                    addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Volumen");
                    return event.getOldStep();
                }
            }
            if (!Utilities.isEmpty(getInputSave().getDimension().getThickness()))
            {
                if (validateFields("Grosor", getInputSave().getDimension().getThickness(), 1))
                {
                    return event.getOldStep();
                }
                else if (getMeasureUnitSaveThickness() == null)
                {
                    addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Grosor");
                    return event.getOldStep();
                }
            }
            if (!Utilities.isEmpty(getInputSave().getDimension().getRadio()))
            {
                if (validateFields("Radio", getInputSave().getDimension().getRadio(), 1))
                {
                    return event.getOldStep();
                }
                else if (getMeasureUnitSaveRadio() == null)
                {
                    addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Radio");
                    return event.getOldStep();
                }
            }
            if (!Utilities.isEmpty(getInputSave().getDimension().getWeight()))
            {
                if (validateFields("Peso", getInputSave().getDimension().getWeight(), 1))
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
     * @param value valor del input que permite habilitar el combo box
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
     * Método encargado de calcular el precio total de un insumo.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void calcuteTotalPrice()
    {
        getInputSave().getInputStock().setTotalValue((double) getInputSave().getValue()
                * getInputSave().getInputStock().getCurrentStock());
        //Obtenemos la cantidad total de unidades de distribución = stock actual * cantidad de distribución
        getInputSave().setTotalAmountDistribution(getInputSave().
                getInputStock().getCurrentStock() * getInputSave().getDistributionAmount());
        //Obtenemos el precio de unidad de unidades de distribución
        getInputSave().setDistributionValue(Utilities.Redondear((getInputSave().getInputStock().
                getPriceUnit() / getInputSave().getDistributionAmount()), 2));

    }

    /**
     * Método encargado de calcular el precio total de un insumo.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void calcuteTotalPriceUpdate()
    {
        getInputSelected().getInputStock().setTotalValue((double) getInputSelected().getValue()
                * getInputSelected().getInputStock().getCurrentStock());
        //Obtenemos la cantidad total de unidades de distribución = stock actual * cantidad de distribución
        getInputSelected().setTotalAmountDistribution(getInputSelected().
                getInputStock().getCurrentStock() * getInputSelected().getDistributionAmount());
        //Obtenemos el precio de unidad de unidades de distribución
        getInputSelected().setDistributionValue(Utilities.Redondear((getInputSelected().getInputStock().
                getPriceUnit() / getInputSelected().getDistributionAmount()), 2));
    }

    /**
     * Método encargado de llevar el flujo al actualizar un insumo.
     *
     * @param event evento en el cual se encuentra el asistente para actualizar
     * insumos
     * @return String al final retorna el nombre de la siguiente pestaña del
     * asistente
     * @author Gustavo Chavarro Ortiz
     */
    public String onFlowProcessUpdateInput(FlowEvent event)
    {
        int packingUnit = -1;
        if (event.getNewStep().equals(TAB_GENERAL))
        {
            return TAB_GENERAL;
        }
        if (event.getOldStep().equals(TAB_GENERAL))
        {
            if (validateFields("Nombre Insumo", getInputSelected().getDescription(), 3))
            {
                return event.getOldStep();
            }
            if (validateFields("Tipo de Material", getInputSelected().getTypeMaterial(), 3))
            {
                return event.getOldStep();
            }
            //Validamos que el valor sea mayor que cero
            if (validateFields("Valor", getInputSelected().getValue() + "", 2))
            {
                return event.getOldStep();
            }
            if (validateFields("Moneda", getInputSelected().getMoney(), 4))
            {
                return event.getOldStep();
            }
            //modificamos el precio por unidad
            getInputSelected().getInputStock().setPriceUnit((double) getInputSelected().getValue());
            //modificamos el precio total igual al precio por unidad * el stock actual
            getInputSelected().getInputStock().setTotalValue((double) getInputSelected().getValue()
                    * getInputSelected().getInputStock().getCurrentStock());

            if (validateFields("Marca", getInputSelected().getMark(), 3))
            {
                return event.getOldStep();
            }

            if (Utilities.isEmpty(getInputSelected().getPathPicture()))
            {
                getInputSelected().setPathPicture(" ");//Setteamos la ruta de la imagen
            }
            if (validateFields("Serie", getInputSelected().getSerie(), 3))
            {
                return event.getOldStep();
            }

            //Validamos los campos seleccionables
            if (validateFields("Proveedor y Garantía", getInputSelected().getSupplierGuarantee(), 4))
            {
                return event.getOldStep();
            }
            if (validateFields("Centro de Costos", getInputSelected().getCostCenter(), 4))
            {
                return event.getOldStep();
            }
            if (validateFields("Almacen", getInputSelected().getInputStock().getIdStore(), 4))
            {
                return event.getOldStep();
            }
            if (validateFields("Localización", getInputSelected().getInputLocation(), 4))
            {
                return event.getOldStep();
            }
            if (validateFields("Prioridad", getInputSelected().getPriority(), 4))
            {
                return event.getOldStep();
            }
            if (validateFields("Unidad de Empaque", getInputSelected().getPackingUnit(), 4))
            {
                return event.getOldStep();
            }
            if (validateFields("Cantidad de Distribución", getInputSelected().getDistributionAmount(), 2))
            {
                return event.getOldStep();
            }
            //Obtenemos el precio de unidad de unidades de distribución
            getInputSelected().setDistributionValue(Utilities.Redondear((getInputSelected().getInputStock().
                    getPriceUnit() / getInputSelected().getDistributionAmount()), 2));
            if (validateFields("Unidad de Distribución", getInputSelected().getDistributionUnit(), 4))
            {
                return event.getOldStep();
            }

            //Validamos que la fecha de expiracion sea mayor que la fecha de creacion
            if (getInputSelected().getExpiryDate() != null && getInputSelected().getExpiryDate().before(getInputSelected().getCreationDate()))
            {
                addError(null, "Error en el campo Fecha de Expiración", "La Fecha de Expiración debe ser mayor que la fecha actual");
                log.error("Error en el campo Unidad de Empaque, El Valor Unidad de Empaque debe ser un número mayor a cero");
                return event.getOldStep();
            }
        }
        //Si pasamos de la pestaña de datos generales a stock y dimensiones
        else if (event.getOldStep().equals(TAB_STOCK))
        {
            //Validamos que ninguno de los campos del stock sea vacio o negativo
            if (validateFields("Stock Máximo", getInputSelected().getInputStock().getMaximeStock(), 2))
            {
                return event.getOldStep();
            }
            if (validateFields("Stock Mínimo", getInputSelected().getInputStock().getMinimeStock(), 2))
            {
                return event.getOldStep();
            }
            if (getInputSelected().getInputStock().getMaximeStock() <= getInputSelected().getInputStock().getMinimeStock())
            {
                addError(null, "Error en el Stock del Insumo", "El Stock Máximo debe ser mayor que el Stock Mínimo");
                return event.getOldStep();
            }
            if (validateFields("Stock Real", getInputSelected().getInputStock().getCurrentStock(), 2))
            {
                return event.getOldStep();
            }
            if (validateFields("Stock Óptimo", getInputSelected().getInputStock().getOptimeStock(), 2))
            {
                return event.getOldStep();
            }
            else
            {
                if (getInputSelected().getInputStock().getMaximeStock() < getInputSelected().getInputStock().getOptimeStock())
                {
                    addError(null, "Error en el Stock del Insumo", "El Stock Máximo debe ser mayor que el Stock Óptimo");
                    return event.getOldStep();
                }
                if (getInputSelected().getInputStock().getMinimeStock() > getInputSelected().getInputStock().getOptimeStock())
                {
                    addError(null, "Error en el Stock del Insumo", "El Stock Mínimo debe ser menor que el Stock Óptimo");
                    return event.getOldStep();
                }
            }

            if (validateFields("Altura", getInputSelected().getDimension().getHight(), 1))
            {
                return event.getOldStep();
            }
            else if (getMeasureUnitSaveHigh() == null)
            {
                addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para la Altura");
                return event.getOldStep();
            }
            if (validateFields("Ancho", getInputSelected().getDimension().getWidth(), 1))
            {
                return event.getOldStep();
            }
            else if (getMeasureUnitSaveWidth() == null)
            {
                addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Ancho");
                return event.getOldStep();
            }
            if (validateFields("Largo", getInputSelected().getDimension().getLarge(), 1))
            {
                return event.getOldStep();
            }
            else if (getMeasureUnitSaveLarge() == null)
            {
                addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Largo");
                return event.getOldStep();
            }
            if (validateFields("Peso", getInputSelected().getDimension().getWeight(), 1))
            {
                return event.getOldStep();
            }
            else if (getMeasureUnitSaveWeight() == null)
            {
                addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Peso");
                return event.getOldStep();
            }

            if (!Utilities.isEmpty(getInputSelected().getDimension().getVolume()))
            {
                if (validateFields("Volumen", getInputSelected().getDimension().getVolume(), 1))
                {
                    return event.getOldStep();
                }
                else if (getMeasureUnitSaveVolume() == null)
                {
                    addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Volumen");
                    return event.getOldStep();
                }
            }
            if (!Utilities.isEmpty(getInputSelected().getDimension().getThickness()))
            {
                if (validateFields("Grosor", getInputSelected().getDimension().getThickness(), 1))
                {
                    return event.getOldStep();
                }
                else if (getMeasureUnitSaveThickness() == null)
                {
                    addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Grosor");
                    return event.getOldStep();
                }
            }
            if (!Utilities.isEmpty(getInputSelected().getDimension().getRadio()))
            {
                if (validateFields("Radio", getInputSelected().getDimension().getRadio(), 1))
                {
                    return event.getOldStep();
                }
                else if (getMeasureUnitSaveRadio() == null)
                {
                    addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Radio");
                    return event.getOldStep();
                }
            }

        }
        return event.getNewStep();
    }

    /**
     * Método encargado de llevar el flujo al actualizar un insumo.
     *
     * @param event evento en el cual se encuentra el asistente para actualizar
     * insumos
     * @return String al final retorna el nombre de la siguiente pestaña del
     * asistente
     * @author Gustavo Chavarro Ortiz
     */
    public String onFlowProcessViewInput(FlowEvent event)
    {

        return event.getNewStep();
    }

    /**
     * Método encargado de realizar la persistencia de un insumo, guardando
     * listas y objetos incluidos en el.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void saveInput()
    {
        //Valido que el insumo no sea nulo
        if (getInputSave() != null)
        {
            if (getSpecificationListSave() != null)
            {
                //Le agrego la lista de especificaciones
                getInputSave().setScInputSpecifications(getSpecificationListSave());
            }
            if (getFeacturesListSave() != null)
            {
                //Le agrego la lista de características
                getInputSave().setScInputFeacturesList(getFeacturesListSave());
            }
            if (getObservationListSave() != null)
            {
                //Le agrego la lista de observaciones
                getInputSave().setScInputObservationsList(getObservationListSave());
            }
            if (getDocumentsListSave() != null)
            {
                //Le agrego la lista de observaciones
                getInputSave().setScInputDocuments(getDocumentsListSave());
            }
            //Almacenamos el insumo
            try
            {
                if (getMeasureUnitSaveHigh() != null)
                {
                    getInputSave().getDimension().setHight(getInputSave().getDimension().getHight() + "-" + getMeasureUnitSaveHigh().getAcronym());
                }
                if (getMeasureUnitSaveWidth() != null)
                {
                    getInputSave().getDimension().setWidth(getInputSave().getDimension().getWidth() + "-" + getMeasureUnitSaveWidth().getAcronym());
                }
                if (getMeasureUnitSaveLarge() != null)
                {
                    getInputSave().getDimension().setLarge(getInputSave().getDimension().getLarge() + "-" + getMeasureUnitSaveLarge().getAcronym());
                }
                if (getMeasureUnitSaveWeight() != null)
                {
                    getInputSave().getDimension().setWeight(getInputSave().getDimension().getWeight() + "-" + getMeasureUnitSaveWeight().getAcronym());
                }
                if (getMeasureUnitSaveVolume() != null)
                {
                    getInputSave().getDimension().setVolume(getInputSave().getDimension().getVolume() + "-" + getMeasureUnitSaveVolume().getAcronym());
                }
                if (getMeasureUnitSaveThickness() != null)
                {
                    getInputSave().getDimension().setThickness(getInputSave().getDimension().getThickness() + "-" + getMeasureUnitSaveThickness().getAcronym());
                }
                if (getMeasureUnitSaveRadio() != null)
                {
                    getInputSave().getDimension().setRadio(getInputSave().getDimension().getRadio() + "-" + getMeasureUnitSaveRadio().getAcronym());
                }
                getScInputServer().saveInput(getInputSave());
                getInputList().add(getInputSave());
                cleanInputSave();
                addInfo(null, DMESConstants.MESSAGE_TITTLE_SUCCES, DMESConstants.MESSAGE_SUCCES);
            }
            catch (Exception e)
            {
                log.error("Error almacenando el insumo", e);
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
                cleanInputSave();
            }

        }

    }
    /**
     * Método encargado de unir las columnas para las dimensiones
     * @author Gustavo Chavarro Ortiz
     */
    public void joinColumnsUpdate()
    {
        //Valido que el insumo no sea nulo
        if (getInputSelected() != null)
        {
        //Almacenamos el insumo

            if (getMeasureUnitSaveHigh() != null)
            {
                getInputSelected().getDimension().setHight(getInputSelected().getDimension().getHight() + "-" + getMeasureUnitSaveHigh().getAcronym());
            }
            if (getMeasureUnitSaveWidth() != null)
            {
                getInputSelected().getDimension().setWidth(getInputSelected().getDimension().getWidth() + "-" + getMeasureUnitSaveWidth().getAcronym());
            }
            if (getMeasureUnitSaveLarge() != null)
            {
                getInputSelected().getDimension().setLarge(getInputSelected().getDimension().getLarge() + "-" + getMeasureUnitSaveLarge().getAcronym());
            }
            if (getMeasureUnitSaveWeight() != null)
            {
                getInputSelected().getDimension().setWeight(getInputSelected().getDimension().getWeight() + "-" + getMeasureUnitSaveWeight().getAcronym());
            }
            if (getMeasureUnitSaveVolume() != null)
            {
                getInputSelected().getDimension().setVolume(getInputSelected().getDimension().getVolume() + "-" + getMeasureUnitSaveVolume().getAcronym());
            }
            if (getMeasureUnitSaveThickness() != null)
            {
                getInputSelected().getDimension().setThickness(getInputSelected().getDimension().getThickness() + "-" + getMeasureUnitSaveThickness().getAcronym());
            }
            if (getMeasureUnitSaveRadio() != null)
            {
                getInputSelected().getDimension().setRadio(getInputSelected().getDimension().getRadio() + "-" + getMeasureUnitSaveRadio().getAcronym());
            }
        }
    }

    /**
     * Método encargado de realizar la persistencia de un insumo, actualizando
     * listas y objetos incluidos en el.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void updateInput()
    {
        //Valido que el insumo no sea nulo
        if (getInputSelected() != null)
        {
            joinColumnsUpdate();
            try
            {
                getScInputServer().updateInput(getInputSelected());
                int index = 0;
                for (ScInput input : getInputList())
                {
                    if (input.getIdInput().equals(getInputSelected().getIdInput()))
                    {
                        break;
                    }
                    index++;
                }
                if (index < getInputList().size())
                {
                    getInputList().set(index, getInputSelected());
                }
                cleanInputSave();
                addInfo(null, DMESConstants.MESSAGE_TITTLE_SUCCES, DMESConstants.MESSAGE_SUCCES);
            }
            catch (Exception e)
            {
                log.error("Error actualizando el insumo", e);
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
                cleanInputSave();
            }

        }

    }

    /**
     * Método encargado de eliminar un insumo.
     *
     * @autor Gustavo Chavarro Ortiz
     */
    public void deleteInput()
    {
        if (getInputSelected() != null)
        {
            try
            {
                getScInputServer().deleteInput(getInputSelected());
                getInputList().remove(getInputSelected());
            }
            catch (Exception e)
            {
                log.error("Error eliminando el insumo", e);
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
                cleanInputSave();
            }
        }
    }

    /**
     * Método encargado de inicializar todas las listas para crear un insumo.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void cleanListSaves()
    {
        setSpecificationsSave(new ScInputSpecifications());
        setFeacturesSave(new ScInputFeactures());
        setObservationsSave(new ScInputObservations());

        if (getSpecificationListSave() == null)
        {
            setSpecificationListSave(new ArrayList<ScInputSpecifications>());
        }
        if (getFeacturesListSave() == null)
        {
            setFeacturesListSave(new ArrayList<ScInputFeactures>());
        }
        if (getObservationListSave() == null)
        {
            setObservationListSave(new ArrayList<ScInputObservations>());
        }
        if (getEquivalenceListSave() == null)
        {
            setEquivalenceListSave(new ArrayList<ScInputEquivalence>());
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
     * Método encargado de realizar la copia del archivo que se desea cargar.
     *
     * @param option se escoge la opción entre guardar y actualizar
     * @author: Gustavo Adolfo Chavarro Ortiz
     */
    public void handleFileUpload(int option)
    {
        //Validamos que el evento de copiado no sea nulo
        switch (option)
        {
            case 1://opción para guardar
                RequestContext.getCurrentInstance().execute("PF('pictureSave').hide()");
                break;
            case 2://opción para actualizar
                RequestContext.getCurrentInstance().execute("PF('pictureUpdate').hide()");
                break;
            default:
                break;
        }
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
                                getInputSave().setPathPicture(file.getAbsolutePath());
                                break;
                            case 2://opción para actualizar
                                getInputSelected().setPathPicture(file.getAbsolutePath());
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
                RequestContext.getCurrentInstance().execute("PF('dialogInputSave').show()");
                break;
            case 2://opción para actualizar
                RequestContext.getCurrentInstance().execute("PF('dialogInputUpdate').show()");
                break;
            default:
                break;
        }
    }

    /** 
     * Método encargado de visualizar la imagen de un elemento.
     *
     * @return String cadena con la ruta de la imagen
     * @param input insumo al que se le consultará la imagen
     * @author Gustavo Chavarro Ortiz
     */
    public String searchImage(ScInput input)
    {
        try
        {
            if (input != null)
            {
                if (!Utilities.isEmpty(input.getPathPicture())) 
                {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(input.getPathPicture())));
                    //la constante me permite mapear imagenes externas
                    return DMESConstants.PATH_EXTERN_PICTURES + input.getPathPicture();
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
     * Método encargado de limpiar los campos para eliminar un insumo
     *
     * @param input insumo a eliminar
     * @author Gustavo Chavarro Ortiz
     */
    public void selectedForDelete(ScInput input)
    {
        cleansTypesMeasures();
        setInputSelected(input);
    }

    /**
     * Método encargado de limpiar los campos para visualizar un insumo
     *
     * @param input insumo a visualizar
     * @author Gustavo Chavarro Ortiz
     */
    public void selectedForView(ScInput input)
    {
        setInputSelected(input);
    }
    
    /**
     * Método encargado de limpiar los campos para actualizar un insumo
     *
     * @param input insumo a actualizarlo
     * @author Gustavo Chavarro Ortiz
     */
    public void selectedForUpdate(ScInput input)
    {
        cleansTypesMeasures();

        try
        {
            //setInputSelected((ScInput) input.clone());
            setInputSelected(input);
//            setInputSelected(getScInputServer().getInputsById(input.getIdInput()));
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar el insumo a actualizar", e);
        }

        fillListInputLocation(input.getInputStock().getIdStore());
        if (!Utilities.isEmpty(getInputSelected().getDimension().getHight()))
        {
            valueToList(getInputSelected().getDimension().getHight(), 1);
        }
        if (!Utilities.isEmpty(getInputSelected().getDimension().getWidth()))
        {
            valueToList(getInputSelected().getDimension().getWidth(), 2);
        }
        if (!Utilities.isEmpty(getInputSelected().getDimension().getLarge()))
        {
            valueToList(getInputSelected().getDimension().getLarge(), 3);
        }
        if (!Utilities.isEmpty(getInputSelected().getDimension().getWeight()))
        {
            valueToList(getInputSelected().getDimension().getWeight(), 4);
        }
        if (!Utilities.isEmpty(getInputSelected().getDimension().getVolume()))
        {
            valueToList(getInputSelected().getDimension().getVolume(), 5);
        }
        if (!Utilities.isEmpty(getInputSelected().getDimension().getThickness()))
        {
            valueToList(getInputSelected().getDimension().getThickness(), 6);
        }
        if (!Utilities.isEmpty(getInputSelected().getDimension().getRadio()))
        {
            valueToList(getInputSelected().getDimension().getRadio(), 7);
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

                getInputSelected().getDimension().setHight(fields[0]);
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

                getInputSelected().getDimension().setWidth(fields[0]);
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

                getInputSelected().getDimension().setLarge(fields[0]);
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

                getInputSelected().getDimension().setWeight(fields[0]);
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

                getInputSelected().getDimension().setVolume(fields[0]);
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

                getInputSelected().getDimension().setThickness(fields[0]);
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

                getInputSelected().getDimension().setRadio(fields[0]);
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
     * Método encargado de guardar temporalmente una especificación en un insumo
     * existente.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void updateSpecification()
    {
        if (getSpecificationsSave() != null)
        {
            if (!Utilities.isEmpty(getSpecificationsSave().getTittle())
                    && !Utilities.isEmpty(getSpecificationsSave().getDescription()))
            {
                if (getInputSelected().getScInputSpecifications() != null)
                {
                    //Guardamos exitosamente la especificación
                    getSpecificationsSave().setCreationDate(new Date());
                    getSpecificationsSave().setIdInput(getInputSelected());
                    getInputSelected().getScInputSpecifications().add(getSpecificationsSave());
                    setSpecificationsSave(new ScInputSpecifications());
                }
                else
                {
                    addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
                    log.error(DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR + ", " + DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
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
            log.error(DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR + ", " + DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
        }
    }

    /**
     * Método encargado de guardar temporalmente una característica en un insumo
     * existente.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void updateFeacture()
    {
        if (getFeacturesSave() != null)
        {
            if (!Utilities.isEmpty(getFeacturesSave().getTittle())
                    && !Utilities.isEmpty(getFeacturesSave().getDescription()))
            {
                if (getInputSelected().getScInputFeacturesList() != null)
                {
                    //Guardamos exitosamente la especificación
                    getFeacturesSave().setIdInput(getInputSelected());
                    getInputSelected().getScInputFeacturesList().add(getFeacturesSave());
                    setFeacturesSave(new ScInputFeactures());
                }
                else
                {
                    addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
                    log.error(DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR + ", " + DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
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
            log.error(DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR + ", " + DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
        }
    }

    /**
     * Método encargado de guardar temporalmente una observación en un insumo
     * existente.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void updateObservation()
    {
        if (getObservationsSave() != null)
        {
            if (!Utilities.isEmpty(getObservationsSave().getTittle())
                    && !Utilities.isEmpty(getObservationsSave().getDescription()))
            {
                if (getInputSelected().getScInputObservationsList() != null)
                {
                    //Guardamos exitosamente la observación
                    getObservationsSave().setIdInput(getInputSave());
                    getInputSelected().getScInputObservationsList().add(getObservationsSave());
                    setObservationsSave(new ScInputObservations());
                }
                else
                {
                    addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
                    log.error(DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR + ", " + DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
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
            log.error(DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR + ", " + DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
        }
    }

    /**
     * Método encargado de borrar una especificación agregada a la lista para
     * actualizar un inusmo.
     *
     * @param inputSpecifications especificación a borrar
     * @author Gustavo Chavarro Ortiz
     */
    public void deleteUpdateSpecifications(ScInputSpecifications inputSpecifications)
    {
        if (getInputSelected().getScInputSpecifications() != null && !getInputSelected().getScInputSpecifications().isEmpty())
        {
            int index = 0; //Posición del objeto que se eliminará
            for (ScInputSpecifications iterator : getInputSelected().getScInputSpecifications())
            {
                if (iterator.getTittle().equals(inputSpecifications.getTittle())
                        && iterator.getDescription().equals(inputSpecifications.getDescription()))
                {
                    break;//Rompempos el ciclo
                }
                index++;//Aumentamos la posición
            }
            getInputSelected().getScInputSpecifications().remove(index);//Removemos el elemento en la posición hallada
        }
        else
        {
            addInfo(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            log.error(DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR + ", " + DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
        }
    }

    /**
     * Método encargado de borrar una característica agregada a la lista para
     * actualizar un inusmo.
     *
     * @param feacture característica a borrar
     * @author Gustavo Chavarro Ortiz
     */
    public void deleteUpdateFeactures(ScInputFeactures feacture)
    {
        if (getInputSelected().getScInputFeacturesList() != null && !getInputSelected().getScInputFeacturesList().isEmpty())
        {
            int index = 0; //Posición del objeto que se eliminará
            for (ScInputFeactures iterator : getInputSelected().getScInputFeacturesList())
            {
                if (iterator.getTittle().equals(feacture.getTittle())
                        && iterator.getDescription().equals(feacture.getDescription()))
                {
                    break;//Rompempos el ciclo
                }
                index++;//Aumentamos la posición
            }
            getInputSelected().getScInputFeacturesList().remove(index);//Removemos el elemento en la posición hallada
        }
        else
        {
            addInfo(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            log.error(DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR + ", " + DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
        }
    }

    /**
     * Método encargado de borrar una observación agregada a la lista para
     * actualizar un inusmo.
     *
     * @param observations observación a borrar
     * @author Gustavo Chavarro Ortiz
     */
    public void deleteUpdateObservation(ScInputObservations observations)
    {
        if (getInputSelected().getScInputObservationsList() != null && !getInputSelected().getScInputObservationsList().isEmpty())
        {
            int index = 0; //Posición del objeto que se eliminará
            for (ScInputObservations iterator : getInputSelected().getScInputObservationsList())
            {
                if (iterator.getTittle().equals(observations.getTittle())
                        && iterator.getDescription().equals(observations.getDescription()))
                {
                    break;//Rompempos el ciclo
                }
                index++;//Aumentamos la posición
            }
            getInputSelected().getScInputObservationsList().remove(index);//Removemos el elemento en la posición hallada
        }
        else
        {
            addInfo(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            log.error(DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR + ", " + DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
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
        switch (option)
        {
            case 1://opción para guardar
                RequestContext.getCurrentInstance().execute("PF('documentSave').hide()");
                break;
            case 2://opción para actualizar
                RequestContext.getCurrentInstance().execute("PF('documentUpdate').hide()");
                break;
            default:
                break;
        }
        //Validamos que el evento de copiado no sea nulo
        int bytesToMegabytes = 10485760; //Valor de representación de 1megabytes a bytes
        if (!Utilities.isEmpty(getDocumentsSave().getDocumentTittle()))
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
                                    getDocumentsSave().setDocumentName(fileName);
                                    getDocumentsSave().setDocumentPath(folder.toString());
                                    getDocumentsSave().setTypeDocument(getFileSave().getContentType());
                                    getDocumentsSave().setUploadBy(getSessionBean().getScUser().getLogin());

                                    getDocumentsSave().setCreationDate(new Date());
                                    switch (option)
                                    {
                                        case 1://opción para guardar
                                            getDocumentsSave().setIdInput(getInputSave());
                                            getDocumentsListSave().add(getDocumentsSave());

                                            break;
                                        case 2://opción para actualizar
                                            getDocumentsSave().setIdInput(getInputSelected());
                                            getInputSelected().getScInputDocuments().add(getDocumentsSave());

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
                RequestContext.getCurrentInstance().execute("PF('dialogInputSave').show()");
                break;
            case 2://opción para actualizar
                RequestContext.getCurrentInstance().execute("PF('dialogInputUpdate').show()");
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
        setDocumentsSave(new ScInputDocuments());
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
            Object object = getScInputServer().getInitialParameters();
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
    public void downloadDocument(ScInputDocuments scDocumentsSelected)
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

    public List<ScInput> getInputList()
    {
        return inputList;
    }

    public void setInputList(List<ScInput> inputList)
    {
        this.inputList = inputList;
    }

    public SessionBean getSessionBean()
    {
        return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean)
    {
        this.sessionBean = sessionBean;
    }

    public IScInput getScInputServer()
    {
        return scInputServer;
    }

    public void setScInputServer(IScInput scInputServer)
    {
        this.scInputServer = scInputServer;
    }

    public ScInput getInputSelected()
    {
        return inputSelected;
    }

    public void setInputSelected(ScInput inputSelected)
    {
        this.inputSelected = inputSelected;
    }

    public ScInput getInputSave()
    {
        return inputSave;
    }

    public void setInputSave(ScInput inputSave)
    {
        this.inputSave = inputSave;
    }

    public UploadedFile getPictureFile()
    {
        return pictureFile;
    }

    public void setPictureFile(UploadedFile pictureFile)
    {
        this.pictureFile = pictureFile;
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

    public ScCostCenter getCostCenterSave()
    {
        return costCenterSave;
    }

    public void setCostCenterSave(ScCostCenter costCenterSave)
    {
        this.costCenterSave = costCenterSave;
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

    public ScMeasureUnit getMeasureUnitSaveHigh()
    {
        return measureUnitSaveHigh;
    }

    public void setMeasureUnitSaveHigh(ScMeasureUnit measureUnitSaveHigh)
    {
        this.measureUnitSaveHigh = measureUnitSaveHigh;
    }

    public ScMeasureUnit getMeasureUnitSaveThickness()
    {
        return measureUnitSaveThickness;
    }

    public void setMeasureUnitSaveThickness(ScMeasureUnit measureUnitSaveThickness)
    {
        this.measureUnitSaveThickness = measureUnitSaveThickness;
    }

    public List<ScMeasureUnit> getMeasureUnitsList()
    {
        return measureUnitsList;
    }

    public void setMeasureUnitsList(List<ScMeasureUnit> measureUnitsList)
    {
        this.measureUnitsList = measureUnitsList;
    }

    public ScPackingUnit getPackingUnitSave()
    {
        return packingUnitSave;
    }

    public void setPackingUnitSave(ScPackingUnit packingUnitSave)
    {
        this.packingUnitSave = packingUnitSave;
    }

    public ScPackingUnit getPackingUnitSelected()
    {
        return packingUnitSelected;
    }

    public void setPackingUnitSelected(ScPackingUnit packingUnitSelected)
    {
        this.packingUnitSelected = packingUnitSelected;
    }

    public List<ScPackingUnit> getPackingUnitsList()
    {
        return packingUnitsList;
    }

    public void setPackingUnitsList(List<ScPackingUnit> packingUnitsList)
    {
        this.packingUnitsList = packingUnitsList;
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

    public List<ScPriority> getPriorityList()
    {
        return priorityList;
    }

    public void setPriorityList(List<ScPriority> priorityList)
    {
        this.priorityList = priorityList;
    }

    public List<ScInputSpecifications> getSpecificationListSave()
    {
        return specificationListSave;
    }

    public void setSpecificationListSave(List<ScInputSpecifications> specificationListSave)
    {
        this.specificationListSave = specificationListSave;
    }

    public List<ScInputFeactures> getFeacturesListSave()
    {
        return feacturesListSave;
    }

    public void setFeacturesListSave(List<ScInputFeactures> feacturesListSave)
    {
        this.feacturesListSave = feacturesListSave;
    }

    public List<ScInputObservations> getObservationListSave()
    {
        return observationListSave;
    }

    public void setObservationListSave(List<ScInputObservations> observationListSave)
    {
        this.observationListSave = observationListSave;
    }

    public List<ScInputEquivalence> getEquivalenceListSave()
    {
        return equivalenceListSave;
    }

    public void setEquivalenceListSave(List<ScInputEquivalence> equivalenceListSave)
    {
        this.equivalenceListSave = equivalenceListSave;
    }

    public ScInputSpecifications getSpecificationsSave()
    {
        return SpecificationsSave;
    }

    public void setSpecificationsSave(ScInputSpecifications SpecificationsSave)
    {
        this.SpecificationsSave = SpecificationsSave;
    }

    public ScInputFeactures getFeacturesSave()
    {
        return feacturesSave;
    }

    public void setFeacturesSave(ScInputFeactures feacturesSave)
    {
        this.feacturesSave = feacturesSave;
    }

    public ScInputObservations getObservationsSave()
    {
        return observationsSave;
    }

    public void setObservationsSave(ScInputObservations observationsSave)
    {
        this.observationsSave = observationsSave;
    }

    public ScMeasureUnit getMeasureUnitSave()
    {
        return measureUnitSave;
    }

    public void setMeasureUnitSave(ScMeasureUnit measureUnitSave)
    {
        this.measureUnitSave = measureUnitSave;
    }

    public ScMeasureUnit getMeasureUnitSaveWeight()
    {
        return measureUnitSaveWeight;
    }

    public void setMeasureUnitSaveWeight(ScMeasureUnit measureUnitSaveWeight)
    {
        this.measureUnitSaveWeight = measureUnitSaveWeight;
    }

    public ScInputDocuments getDocumentsSave()
    {
        return documentsSave;
    }

    public void setDocumentsSave(ScInputDocuments documentsSave)
    {
        this.documentsSave = documentsSave;
    }

    public List<ScInputDocuments> getDocumentsListSave()
    {
        return documentsListSave;
    }

    public void setDocumentsListSave(List<ScInputDocuments> documentsListSave)
    {
        this.documentsListSave = documentsListSave;
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

    public ScMoney getMoneySave()
    {
        return moneySave;
    }

    public void setMoneySave(ScMoney moneySave)
    {
        this.moneySave = moneySave;
    }

    public List<ScMoney> getMoneyList()
    {
        return moneyList;
    }

    public void setMoneyList(List<ScMoney> moneyList)
    {
        this.moneyList = moneyList;
    }

    public ScDistributionUnit getDistributionUnitSave()
    {
        return distributionUnitSave;
    }

    public void setDistributionUnitSave(ScDistributionUnit distributionUnitSave)
    {
        this.distributionUnitSave = distributionUnitSave;
    }

    public List<ScDistributionUnit> getDistributionUnitsList()
    {
        return distributionUnitsList;
    }

    public void setDistributionUnitsList(List<ScDistributionUnit> distributionUnitsList)
    {
        this.distributionUnitsList = distributionUnitsList;
    }

}
