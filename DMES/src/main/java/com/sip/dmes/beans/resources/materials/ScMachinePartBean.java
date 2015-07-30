/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.beans.resources.materials;

import com.sip.dmes.beans.SessionBean;
import com.sip.dmes.dao.bo.IScMachinePart;
import com.sip.dmes.entitys.ScCostCenter;
import com.sip.dmes.entitys.ScDistributionUnit;
import com.sip.dmes.entitys.ScMachinePart;
import com.sip.dmes.entitys.ScMachineLocation;
import com.sip.dmes.entitys.ScMachine;
import com.sip.dmes.entitys.ScMachinePartAttached;
import com.sip.dmes.entitys.ScMachinePartDocument;
import com.sip.dmes.entitys.ScMeasureUnit;
import com.sip.dmes.entitys.ScMoney;
import com.sip.dmes.entitys.ScPackingUnit;
import com.sip.dmes.entitys.ScPartner;
import com.sip.dmes.entitys.ScPriority;
import com.sip.dmes.entitys.ScMachine;
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
public class ScMachinePartBean
{

    //Declaración de Variables
    private List<ScMachinePart> machinePartList;//Lista de partes de máquina de la tabla
    private ScMachinePart machinePartSelected; //Insumo seleccionado para consulta, edición o eliminación
    private ScMachinePart machinePartSave; //Insumo seleccionado para agregar
    private ScMeasureUnit measureUnitSave; //Unidad de medida seleccionado para agregar
    private ScMeasureUnit measureUnitSaveHigh; //Unidad de medida seleccionado para agregar
    private ScMeasureUnit measureUnitSaveWidth; //Unidad de medida seleccionado para agregar
    private ScMeasureUnit measureUnitSaveRadio; //Unidad de medida seleccionado para agregar
    private ScMeasureUnit measureUnitSaveLarge; //Unidad de medida seleccionado para agregar
    private ScMeasureUnit measureUnitSaveVolume; //Unidad de medida seleccionado para agregar
    private ScMeasureUnit measureUnitSaveThickness; //Unidad de medida seleccionado para agregar
    private ScMeasureUnit measureUnitSaveWeight; //Unidad de medida seleccionado para agregar

    private ScPackingUnit packingUnitSave; //Unidad de empaque seleccionado para agregar
    private ScDistributionUnit distributionUnitSave; //UnidaScMachinePartBeand de empaque seleccionado para agregar
    private ScPackingUnit packingUnitSelected; //Unidad de empaque seleccionado para agregar al insumo
    private ScMachineLocation locaionSave; //Localizacion seleccionada para agregar
    private ScMachineLocation loationSelected; //Localizacion seleccionada para agregar al insumo
    private ScMachine machineSave; //Localizacion seleccionada para agregar
    private ScMachine machineSelected; //Localizacion seleccionada para agregar al insumo
    private ScPriority prioritySave; //Prioridad seleccionada para agregar al insumo
    private SessionBean sessionBean; //Bean de sesion
    private UploadedFile pictureFile; //Archivo que se copiara para la imagen del insumo
    private ScCostCenter costCenterSave; //Centro de Costo para agregar
    private ScMachinePartAttached SpecificationsSave;//Especificación a guardar
    private ScMachinePartAttached feacturesSave;//Característica a guardar
    private ScMachinePartAttached observationsSave;//Observación a guardar
    private ScMachinePartDocument documentsSave;//Documento a guardar
    private ScMoney moneySave;//Moneda a guardar
    private List<ScPartner> partnersList;//Listado de proveedores
    private List<ScCostCenter> costCenterList;//Listado de centros de costo
    private List<ScMeasureUnit> measureUnitsList;//Lista de unidades de medida
    private List<ScPackingUnit> packingUnitsList;//Lista de unidades de empaque
    private List<ScDistributionUnit> distributionUnitsList;//Lista de unidades de distribución
    private List<ScMachineLocation> machineLocationsList;//Lista de localizaciones
    private List<ScMachine> machinesList;//Lista de almacenes
    private List<ScPriority> priorityList;//Lista de prioridades
    private List<ScMachinePartAttached> specificationListSave;//Lista de especificaciones a guardar
    private List<ScMachinePartAttached> feacturesListSave;//Lista de características a guardar
    private List<ScMachinePartAttached> observationListSave;//Lista de observaciones a guardar
    private List<ScMachinePartDocument> documentsListSave;//Lista de documentos a guardar
    private List<ScMoney> moneyList;//Lista de monedas
    private UploadedFile fileSave;//Documento a subir
    private UploadedFile fileUpdate;//Documento a actualizar

    //Persistencia
    private IScMachinePart scMachinePartServer; //Dao de persistencia del partes de máquina

    private final static Logger log = Logger.getLogger(ScMachinePartBean.class);

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
     * Creates a new instance of ScMachinePartBean
     */
    public ScMachinePartBean()
    {

    }

    /**
     * Método encargado de mostrar los datos iniciales.
     */
    @PostConstruct
    public void initData()
    {
        fillListMachineParts();
        fillListPartners();
        fillListCostCenter();
        //fillListPackingUnit();
        fillListMachine();
        fillListPriority();
        fillListMeasure();
        fillListMoney();
        //fillListDistribucionUnit();
        cleanFieldsInit();
        getinitalParameters();
    }

    /**
     * Método encargado de llenar la lista de partes de máquina.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void fillListMachineParts()
    {
        try
        {
            //Se consultan todos las partes de una máquina y se guardan en la lista ordenados por la fecha
            setMachinePartList(getScMachinePartServer().getAllMachineParts());
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar las partes de una máquina", e);
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
            setPartnersList(getScMachinePartServer().getAllPartners());
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar los proveedores para las partes de una máquina", e);
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
            setCostCenterList(getScMachinePartServer().getAllCostCenter());
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar los proveedores para las partes de una máquina", e);
        }
    }

    /**
     * Método encargado de llenar la lista de unidades de distribución.
     *
     * @author Gustavo Chavarro Ortiz
     */
//    public void fillListDistribucionUnit()
//    {
//        try
//        {
//            //Se consultan todos los proveedores existentes
//            setDistributionUnitsList(getScMachinePartServer().getAllDistributionUnits());
//        }
//        catch (Exception e)
//        {
//            log.error("Error al intentar consultar las unidades de distribucion para las partes de una máquina", e);
//        }
//    }

    /**
     * Método encargado de llenar la lista de unidades de empaque.
     *
     * @author Gustavo Chavarro Ortiz
     */
//    public void fillListPackingUnit()
//    {
//        try
//        {
//            //Se consultan todos los proveedores existentes
//            setPackingUnitsList(getScMachinePartServer().getAllPackingUnits());
//        }
//        catch (Exception e)
//        {
//            log.error("Error al intentar consultar las unidades de empaque para las partes de una máquina", e);
//        }
//    }

    /**
     * Método encargado de llenar la lista de localizaciones.
     *
     * @param machine almacen que contiene las localizaciones
     * @author Gustavo Chavarro Ortiz
     */
    public void fillListMachinePartLocation(ScMachine machine)
    {
        try
        {
            //Se consultan todos los proveedores existentes
            setMachinePartLocationsList(getScMachinePartServer().getAllMachinePartLocations(machine));
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar las localizaciones para las partes de una máquina", e);
        }
    }

    /**
     * Método encargado de llenar la lista de almacenes.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void fillListMachine()
    {
        try
        {
            //Se consultan todos almacenes disponibles
            setMachinesList(getScMachinePartServer().getAllMachines());
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar las máquinas para las partes de una máquina", e);
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
            setPriorityList(getScMachinePartServer().getAllPrioritys());
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar las prioridades para las partes de una máquina", e);
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
            setMeasureUnitsList(getScMachinePartServer().getAllMeasureUnits());
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar las medidas para las partes de una máquina", e);
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
            setMoneyList(getScMachinePartServer().getAllMoneys());
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar las monedas para las partes de una máquina", e);
        }
    }

    /**
     * Método encargado de vaciar los objetos.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void cleanFieldsInit()
    {

        setMachinePartSelected(new ScMachinePart());
        setCostCenterSave(new ScCostCenter());
        setPackingUnitSave(new ScPackingUnit());
        setDistributionUnitSave(new ScDistributionUnit());
        setMachinePartLocationSave(new ScMachineLocation());
        setMeasureUnitSave(new ScMeasureUnit());
        cleanDocumentSave();
        cleanMachinePartSave();
        setDocumentsListSave(new ArrayList<ScMachinePartDocument>());
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

    public void cleanMachinePartSave()
    {
        setMachinePartSave(new ScMachinePart());
        //Creamos un stock para la siguiente pestaña
        //getMachinePartSave().setMachinePartStock(new ScMachinePartStock());
        //Creamos el objeto de dimension para la segunda pestaña
        //getMachinePartSave().setDimension(new ScMachinePartDimension());
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
    public void cleanFieldsMachines()
    {
        setMachineSave(new ScMachine());
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
    public void cleanFieldsLocationMachinePart()
    {
        setMachinePartLocationSave(new ScMachineLocation());
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
                    getScMachinePartServer().saveCostCenter(getCostCenterSave());
                    if (getCostCenterList() == null)
                    {
                        setCostCenterList(new ArrayList<ScCostCenter>());
                    }
                    getCostCenterList().add(getCostCenterSave());
                    cleanFieldsCostCenter();
                }
                else
                {
                    log.error("Error al intentar crear el centro de costos desde partes de máquina");
                    addError(null, "Error al crear un centro de costos", "Debe ingresar solo números para el campo código del centro de costo");
                }
            }
            else
            {
                log.error("Error al intentar crear el centro de costos desde partes de máquina");
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }

        }
        catch (Exception e)
        {
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            log.error("Error al intentar agregar un centro de costos desde partes de máquina", e);
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
                getScMachinePartServer().saveMeasureUnit(getMeasureUnitSave());
                if (getMeasureUnitsList() == null)
                {
                    setMeasureUnitsList(new ArrayList<ScMeasureUnit>());
                }
                getMeasureUnitsList().add(getMeasureUnitSave());
                cleanFieldsMeasure();
            }
            else
            {
                log.error("Error al intentar crear la unidad de medida para partes de máquina");
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }

        }
        catch (Exception e)
        {
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            log.error("Error al intentar agregar una unidad de medida desde partes de máquina", e);
        }

    }

//    /**
//     * Método encargado de agregar una unidad de empaque
//     *
//     * @author Gustavo Chavarro Ortiz
//     */
//    public void addPackingUnit()
//    {
//        try
//        {
//            if (getPackingUnitSave() != null)
//            {
//
//                getScMachinePartServer().savePackingUnit(getPackingUnitSave());
//                if (getPackingUnitsList() == null)
//                {
//                    setPackingUnitsList(new ArrayList<ScPackingUnit>());
//                }
//                getPackingUnitsList().add(getPackingUnitSave());
//                cleanFieldsPackingUnit();
//
//            }
//            else
//            {
//                log.error("Error al intentar crear la unidad de empaque desde partes de máquina");
//                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
//            }
//
//        }
//        catch (Exception e)
//        {
//            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
//            log.error("Error al intentar crear la unidad de empaque desde partes de máquina", e);
//        }
//
//    }

    /**
     * Método encargado de verificar si se ha seleccionado una máquina
     *
     * @param machine almacen que contiene las localizaciones
     * @return boolean parametro que dice si tiene o no una máquina seleccionado
     * @author Gustavo Chavarro Ortiz
     */
    public boolean withOutMachine(ScMachine machine)
    {
        boolean result = false;
        if (machine == null || Utilities.isEmpty(machine.getName()))
        {
            result = true;
        }
        else
        {
            fillListMachinePartLocation(machine);
        }
        return result;
    }

    /**
     * Método encargado de agregar una unidad de localizacion
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void addMachinePartLocations()
    {
        try
        {
            if (getMachinePartLocationSave() != null)
            {
                ScMachine machineLocation = null;
                //Agregamos el almacen de la localizacion
//                if (getMachinePartSave().getMachinePartStock().getIdMachine() != null)
//                {
//                    machineLocation = getMachinePartSave().getMachinePartStock().getIdMachine();
//                }
                //Para una actualizacion de datos
//                else
//                {
//                    machineLocation = getMachinePartSelected().getMachinePartStock().getIdMachine();
//                }
                getMachinePartLocationSave().setIdMachine(machineLocation);
                //Se realiza la persistencia de la localizacion
                getScMachinePartServer().saveLocationMachinePart(getMachinePartLocationSave());
                if (getMachinePartLocationsList() == null)
                {
                    setMachinePartLocationsList(new ArrayList<ScMachineLocation>());
                }
                getMachinePartLocationsList().add(getMachinePartLocationSave());
                cleanFieldsLocationMachinePart();
            }
            else
            {
                log.error("Error al intentar crear la localización desde partes de máquina");
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }

        }
        catch (Exception e)
        {
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            log.error("Error al intentar crear la localización desde partes de máquina", e);
        }
    }

    

    

    /**
     * Método encargado de borrar un documento agregada a la lista para guardar
     * un inusmo.
     *
     * @param documents documento a borrar
     * @author Gustavo Chavarro Ortiz
     */
    public void deleteDocument(ScMachinePartDocument documents)
    {
        if (getDocumentsListSave() != null && !getDocumentsListSave().isEmpty())
        {
            int index = 0; //Posición del objeto que se eliminará
            for (ScMachinePartDocument iterator : getDocumentsListSave())
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
    public void deleteDocumentUpdate(ScMachinePartDocument documents)
    {
        if (getMachinePartSelected().getScMachinePartDocument() != null && !getMachinePartSelected().getScMachinePartDocument().isEmpty())
        {
            int index = 0; //Posición del objeto que se eliminará
            for (ScMachinePartDocument iterator : getMachinePartSelected().getScMachinePartDocument())
            {
                if (iterator.getDocumentTittle().equals(documents.getDocumentTittle())
                        && iterator.getDocumentPath().equals(documents.getDocumentPath()))
                {
                    break;//Rompempos el ciclo
                }
                index++;//Aumentamos la posición
            }
            getMachinePartSelected().getScMachinePartDocument().remove(index);//Removemos el elemento en la posición hallada
        }
        else
        {
            addInfo(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            log.error(DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR + ", " + DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
        }
    }

    /**
     * Método encargado de llevar el flujo al guardar una  máquina.
     *
     * @param event evento en el cual se encuentra el asistente para crear
     * partes de máquina
     * @return String al final retorna el nombre de la siguiente pestaña del
     * asistente
     * @author Gustavo Chavarro Ortiz
     */
    public String onFlowProcessSaveMachinePart(FlowEvent event)
    {
//        int packingUnit = -1;
//        if (event.getNewStep().equals(TAB_GENERAL))
//        {
//            return TAB_GENERAL;
//        }
//        if (event.getOldStep().equals(TAB_GENERAL))
//        {
//            if (validateFields("Nombre Insumo", getMachinePartSave().getDescription(), 3))
//            {
//                return event.getOldStep();
//            }
//            if (validateFields("Tipo de Material", getMachinePartSave().getTypeMaterial(), 3))
//            {
//                return event.getOldStep();
//            }
//            //Validamos que el valor sea mayor que cero
//            if (validateFields("Valor", getMachinePartSave().getValue() + "", 2))
//            {
//                return event.getOldStep();
//            }
//            if (validateFields("Moneda", getMachinePartSave().getMoney(), 4))
//            {
//                return event.getOldStep();
//            }
//            //modificamos el precio por unidad
//            getMachinePartSave().getMachinePartStock().setPriceUnit((double) getMachinePartSave().getValue());
//            //modificamos el precio total igual al precio por unidad * el stock actual
//            getMachinePartSave().getMachinePartStock().setTotalValue((double) getMachinePartSave().getValue()
//                    * getMachinePartSave().getMachinePartStock().getCurrentStock());
//
//            if (validateFields("Marca", getMachinePartSave().getMark(), 3))
//            {
//                return event.getOldStep();
//            }
//
//            if (Utilities.isEmpty(getMachinePartSave().getPathPicture()))
//            {
//                getMachinePartSave().setPathPicture(" ");//Setteamos la ruta de la imagen
//            }
//            if (validateFields("Serie", getMachinePartSave().getSerie(), 3))
//            {
//                return event.getOldStep();
//            }
//
//            //Validamos los campos seleccionables
//            if (validateFields("Proveedor y Garantía", getMachinePartSave().getSupplierGuarantee(), 4))
//            {
//                return event.getOldStep();
//            }
//            if (validateFields("Centro de Costos", getMachinePartSave().getCostCenter(), 4))
//            {
//                return event.getOldStep();
//            }
//            if (validateFields("Almacen", getMachinePartSave().getMachinePartStock().getIdMachine(), 4))
//            {
//                return event.getOldStep();
//            }
//            if (validateFields("Localización", getMachinePartSave().getMachinePartLocation(), 4))
//            {
//                return event.getOldStep();
//            }
//            if (validateFields("Prioridad", getMachinePartSave().getPriority(), 4))
//            {
//                return event.getOldStep();
//            }
//            if (validateFields("Unidad de Empaque", getMachinePartSave().getPackingUnit(), 4))
//            {
//                return event.getOldStep();
//            }
//            if (validateFields("Cantidad de Distribución", getMachinePartSave().getDistributionAmount(), 2))
//            {
//                return event.getOldStep();
//            }
//            //Obtenemos el precio de unidad de unidades de distribución
//            getMachinePartSave().setDistributionValue(Utilities.Redondear((getMachinePartSave().getMachinePartStock().
//                    getPriceUnit() / getMachinePartSave().getDistributionAmount()), 2));
//            if (validateFields("Unidad de Distribución", getMachinePartSave().getDistributionUnit(), 4))
//            {
//                return event.getOldStep();
//            }
//
//            //Agregamos la fecha de creación del insumo
//            getMachinePartSave().setCreationDate(new Date());
//
//            //Validamos que la fecha de expiracion sea mayor que la fecha de creacion
//            if (getMachinePartSave().getExpiryDate() != null && getMachinePartSave().getExpiryDate().before(getMachinePartSave().getCreationDate()))
//            {
//                addError(null, "Error en el campo Fecha de Expiración", "La Fecha de Expiración debe ser mayor que la fecha actual");
//                log.error("Error en el campo Unidad de Empaque, El Valor Unidad de Empaque debe ser un número mayor a cero");
//                return event.getOldStep();
//            }
//
//        }
//        //Si pasamos de la pestaña de datos generales a stock y dimensiones
//        else if (event.getOldStep().equals(TAB_STOCK))
//        {
//            //Validamos que ninguno de los campos del stock sea vacio o negativo
//            if (validateFields("Stock Máximo", getMachinePartSave().getMachinePartStock().getMaximeStock(), 2))
//            {
//                return event.getOldStep();
//            }
//            if (validateFields("Stocguschaork Mínimo", getMachinePartSave().getMachinePartStock().getMinimeStock(), 2))
//            {
//                return event.getOldStep();
//            }
//            if (getMachinePartSave().getMachinePartStock().getMaximeStock() <= getMachinePartSave().getMachinePartStock().getMinimeStock())
//            {
//                addError(null, "Error en el Stock del Insumo", "El Stock Máximo debe ser mayor que el Stock Mínimo");
//                return event.getOldStep();
//            }
//            if (validateFields("Stock Real", getMachinePartSave().getMachinePartStock().getCurrentStock(), 2))
//            {
//                return event.getOldStep();
//            }
//
//            if (validateFields("Stock Óptimo", getMachinePartSave().getMachinePartStock().getOptimeStock(), 2))
//            {
//                return event.getOldStep();
//            }
//            else
//            {
//                if (getMachinePartSave().getMachinePartStock().getMaximeStock() < getMachinePartSave().getMachinePartStock().getOptimeStock())
//                {
//                    addError(null, "Error en el Stock del Insumo", "El Stock Máximo debe ser mayor que el Stock Óptimo");
//                    return event.getOldStep();
//                }
//                if (getMachinePartSave().getMachinePartStock().getMinimeStock() > getMachinePartSave().getMachinePartStock().getOptimeStock())
//                {
//                    addError(null, "Error en el Stock del Insumo", "El Stock Mínimo debe ser menor que el Stock Óptimo");
//                    return event.getOldStep();
//                }
//            }
//
//            if (validateFields("Altura", getMachinePartSave().getDimension().getHight(), 1))
//            {
//                return event.getOldStep();
//            }
//            else if (getMeasureUnitSaveHigh() == null)
//            {
//                addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para la Altura");
//                return event.getOldStep();
//            }
//            if (validateFields("Ancho", getMachinePartSave().getDimension().getWidth(), 1))
//            {
//                return event.getOldStep();
//            }
//            else if (getMeasureUnitSaveWidth() == null)
//            {
//                addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Ancho");
//                return event.getOldStep();
//            }
//            if (validateFields("Largo", getMachinePartSave().getDimension().getLarge(), 1))
//            {
//                return event.getOldStep();
//            }
//            else if (getMeasureUnitSaveLarge() == null)
//            {
//                addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Largo");
//                return event.getOldStep();
//            }
//            if (validateFields("Peso", getMachinePartSave().getDimension().getWeight(), 1))
//            {
//                return event.getOldStep();
//            }
//            else if (getMeasureUnitSaveWeight() == null)
//            {
//                addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Peso");
//                return event.getOldStep();
//            }
//            if (!Utilities.isEmpty(getMachinePartSave().getDimension().getVolume()))
//            {
//                if (validateFields("Volumen", getMachinePartSave().getDimension().getVolume(), 1))
//                {
//                    return event.getOldStep();
//                }
//                else if (getMeasureUnitSaveVolume() == null)
//                {
//                    addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Volumen");
//                    return event.getOldStep();
//                }
//            }
//            if (!Utilities.isEmpty(getMachinePartSave().getDimension().getThickness()))
//            {
//                if (validateFields("Grosor", getMachinePartSave().getDimension().getThickness(), 1))
//                {
//                    return event.getOldStep();
//                }
//                else if (getMeasureUnitSaveThickness() == null)
//                {
//                    addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Grosor");
//                    return event.getOldStep();
//                }
//            }
//            if (!Utilities.isEmpty(getMachinePartSave().getDimension().getRadio()))
//            {
//                if (validateFields("Radio", getMachinePartSave().getDimension().getRadio(), 1))
//                {
//                    return event.getOldStep();
//                }
//                else if (getMeasureUnitSaveRadio() == null)
//                {
//                    addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Radio");
//                    return event.getOldStep();
//                }
//            }
//            if (!Utilities.isEmpty(getMachinePartSave().getDimension().getWeight()))
//            {
//                if (validateFields("Peso", getMachinePartSave().getDimension().getWeight(), 1))
//                {
//                    return event.getOldStep();
//                }
//                else if (getMeasureUnitSaveWeight() == null)
//                {
//                    addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Peso");
//                    return event.getOldStep();
//                }
//            }
//        }

        return event.getNewStep();
    }

    /**
     * Método encargado de permitir mostrar un combo box.
     *
     * @param value valor del machinePart que permite habilitar el combo box
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
     * Método encargado de llevar el flujo al actualizar una  máquina.
     *
     * @param event evento en el cual se encuentra el asistente para actualizar
     * partes de máquina
     * @return String al final retorna el nombre de la siguiente pestaña del
     * asistente
     * @author Gustavo Chavarro Ortiz
     */
    public String onFlowProcessUpdateMachinePart(FlowEvent event)
    {
//        int packingUnit = -1;
//        if (event.getNewStep().equals(TAB_GENERAL))
//        {
//            return TAB_GENERAL;
//        }
//        if (event.getOldStep().equals(TAB_GENERAL))
//        {
//            if (validateFields("Nombre Insumo", getMachinePartSelected().getDescription(), 3))
//            {
//                return event.getOldStep();
//            }
//            if (validateFields("Tipo de Material", getMachinePartSelected().getTypeMaterial(), 3))
//            {
//                return event.getOldStep();
//            }
//            //Validamos que el valor sea mayor que cero
//            if (validateFields("Valor", getMachinePartSelected().getValue() + "", 2))
//            {
//                return event.getOldStep();
//            }
//            if (validateFields("Moneda", getMachinePartSelected().getMoney(), 4))
//            {
//                return event.getOldStep();
//            }
//            //modificamos el precio por unidad
//            getMachinePartSelected().getMachinePartStock().setPriceUnit((double) getMachinePartSelected().getValue());
//            //modificamos el precio total igual al precio por unidad * el stock actual
//            getMachinePartSelected().getMachinePartStock().setTotalValue((double) getMachinePartSelected().getValue()
//                    * getMachinePartSelected().getMachinePartStock().getCurrentStock());
//
//            if (validateFields("Marca", getMachinePartSelected().getMark(), 3))
//            {
//                return event.getOldStep();
//            }
//
//            if (Utilities.isEmpty(getMachinePartSelected().getPathPicture()))
//            {
//                getMachinePartSelected().setPathPicture(" ");//Setteamos la ruta de la imagen
//            }
//            if (validateFields("Serie", getMachinePartSelected().getSerie(), 3))
//            {
//                return event.getOldStep();
//            }
//
//            //Validamos los campos seleccionables
//            if (validateFields("Proveedor y Garantía", getMachinePartSelected().getSupplierGuarantee(), 4))
//            {
//                return event.getOldStep();
//            }
//            if (validateFields("Centro de Costos", getMachinePartSelected().getCostCenter(), 4))
//            {
//                return event.getOldStep();
//            }
//            if (validateFields("Almacen", getMachinePartSelected().getMachinePartStock().getIdMachine(), 4))
//            {
//                return event.getOldStep();
//            }
//            if (validateFields("Localización", getMachinePartSelected().getMachinePartLocation(), 4))
//            {
//                return event.getOldStep();
//            }
//            if (validateFields("Prioridad", getMachinePartSelected().getPriority(), 4))
//            {
//                return event.getOldStep();
//            }
//            if (validateFields("Unidad de Empaque", getMachinePartSelected().getPackingUnit(), 4))
//            {
//                return event.getOldStep();
//            }
//            if (validateFields("Cantidad de Distribución", getMachinePartSelected().getDistributionAmount(), 2))
//            {
//                return event.getOldStep();
//            }
//            //Obtenemos el precio de unidad de unidades de distribución
//            getMachinePartSelected().setDistributionValue(Utilities.Redondear((getMachinePartSelected().getMachinePartStock().
//                    getPriceUnit() / getMachinePartSelected().getDistributionAmount()), 2));
//            if (validateFields("Unidad de Distribución", getMachinePartSelected().getDistributionUnit(), 4))
//            {
//                return event.getOldStep();
//            }
//
//            //Validamos que la fecha de expiracion sea mayor que la fecha de creacion
//            if (getMachinePartSelected().getExpiryDate() != null && getMachinePartSelected().getExpiryDate().before(getMachinePartSelected().getCreationDate()))
//            {
//                addError(null, "Error en el campo Fecha de Expiración", "La Fecha de Expiración debe ser mayor que la fecha actual");
//                log.error("Error en el campo Unidad de Empaque, El Valor Unidad de Empaque debe ser un número mayor a cero");
//                return event.getOldStep();
//            }
//        }
//        //Si pasamos de la pestaña de datos generales a stock y dimensiones
//        else if (event.getOldStep().equals(TAB_STOCK))
//        {
//            //Validamos que ninguno de los campos del stock sea vacio o negativo
//            if (validateFields("Stock Máximo", getMachinePartSelected().getMachinePartStock().getMaximeStock(), 2))
//            {
//                return event.getOldStep();
//            }
//            if (validateFields("Stock Mínimo", getMachinePartSelected().getMachinePartStock().getMinimeStock(), 2))
//            {
//                return event.getOldStep();
//            }
//            if (getMachinePartSelected().getMachinePartStock().getMaximeStock() <= getMachinePartSelected().getMachinePartStock().getMinimeStock())
//            {
//                addError(null, "Error en el Stock del Insumo", "El Stock Máximo debe ser mayor que el Stock Mínimo");
//                return event.getOldStep();
//            }
//            if (validateFields("Stock Real", getMachinePartSelected().getMachinePartStock().getCurrentStock(), 2))
//            {
//                return event.getOldStep();
//            }
//            if (validateFields("Stock Óptimo", getMachinePartSelected().getMachinePartStock().getOptimeStock(), 2))
//            {
//                return event.getOldStep();
//            }
//            else
//            {
//                if (getMachinePartSelected().getMachinePartStock().getMaximeStock() < getMachinePartSelected().getMachinePartStock().getOptimeStock())
//                {
//                    addError(null, "Error en el Stock del Insumo", "El Stock Máximo debe ser mayor que el Stock Óptimo");
//                    return event.getOldStep();
//                }
//                if (getMachinePartSelected().getMachinePartStock().getMinimeStock() > getMachinePartSelected().getMachinePartStock().getOptimeStock())
//                {
//                    addError(null, "Error en el Stock del Insumo", "El Stock Mínimo debe ser menor que el Stock Óptimo");
//                    return event.getOldStep();
//                }
//            }
//
//            if (validateFields("Altura", getMachinePartSelected().getDimension().getHight(), 1))
//            {
//                return event.getOldStep();
//            }
//            else if (getMeasureUnitSaveHigh() == null)
//            {
//                addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para la Altura");
//                return event.getOldStep();
//            }
//            if (validateFields("Ancho", getMachinePartSelected().getDimension().getWidth(), 1))
//            {
//                return event.getOldStep();
//            }
//            else if (getMeasureUnitSaveWidth() == null)
//            {
//                addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Ancho");
//                return event.getOldStep();
//            }
//            if (validateFields("Largo", getMachinePartSelected().getDimension().getLarge(), 1))
//            {
//                return event.getOldStep();
//            }
//            else if (getMeasureUnitSaveLarge() == null)
//            {
//                addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Largo");
//                return event.getOldStep();
//            }
//            if (validateFields("Peso", getMachinePartSelected().getDimension().getWeight(), 1))
//            {
//                return event.getOldStep();
//            }
//            else if (getMeasureUnitSaveWeight() == null)
//            {
//                addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Peso");
//                return event.getOldStep();
//            }
//
//            if (!Utilities.isEmpty(getMachinePartSelected().getDimension().getVolume()))
//            {
//                if (validateFields("Volumen", getMachinePartSelected().getDimension().getVolume(), 1))
//                {
//                    return event.getOldStep();
//                }
//                else if (getMeasureUnitSaveVolume() == null)
//                {
//                    addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Volumen");
//                    return event.getOldStep();
//                }
//            }
//            if (!Utilities.isEmpty(getMachinePartSelected().getDimension().getThickness()))
//            {
//                if (validateFields("Grosor", getMachinePartSelected().getDimension().getThickness(), 1))
//                {
//                    return event.getOldStep();
//                }
//                else if (getMeasureUnitSaveThickness() == null)
//                {
//                    addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Grosor");
//                    return event.getOldStep();
//                }
//            }
//            if (!Utilities.isEmpty(getMachinePartSelected().getDimension().getRadio()))
//            {
//                if (validateFields("Radio", getMachinePartSelected().getDimension().getRadio(), 1))
//                {
//                    return event.getOldStep();
//                }
//                else if (getMeasureUnitSaveRadio() == null)
//                {
//                    addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Radio");
//                    return event.getOldStep();
//                }
//            }
//
//        }
        return event.getNewStep();
    }

    /**
     * Método encargado de llevar el flujo al actualizar una  máquina.
     *
     * @param event evento en el cual se encuentra el asistente para actualizar
     * partes de máquina
     * @return String al final retorna el nombre de la siguiente pestaña del
     * asistente
     * @author Gustavo Chavarro Ortiz
     */
    public String onFlowProcessViewMachinePart(FlowEvent event)
    {

        return event.getNewStep();
    }

    /**
     * Método encargado de realizar la persistencia de una  máquina, guardando
     * listas y objetos incluidos en el.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void saveMachinePart()
    {
        //Valido que el insumo no sea nulo
        if (getMachinePartSave() != null)
        {
            if (getSpecificationListSave() != null)
            {
                //Le agrego la lista de especificaciones
                getMachinePartSave().setScMachinePartAttachedList(getSpecificationListSave());
            }
            if (getFeacturesListSave() != null)
            {
                //Le agrego la lista de características
                getMachinePartSave().setScMachinePartAttachedList(getFeacturesListSave());
            }
            if (getObservationListSave() != null)
            {
                //Le agrego la lista de observaciones
                getMachinePartSave().setScMachinePartAttachedList(getObservationListSave());
            }
            if (getDocumentsListSave() != null)
            {
                //Le agrego la lista de observaciones
                getMachinePartSave().setScMachinePartDocument(getDocumentsListSave());
            }
            //Almacenamos el insumo
            try
            {
//                if (getMeasureUnitSaveHigh() != null)
//                {
//                    getMachinePartSave().getDimension().setHight(getMachinePartSave().getDimension().getHight() + "-" + getMeasureUnitSaveHigh().getAcronym());
//                }
//                if (getMeasureUnitSaveWidth() != null)
//                {
//                    getMachinePartSave().getDimension().setWidth(getMachinePartSave().getDimension().getWidth() + "-" + getMeasureUnitSaveWidth().getAcronym());
//                }
//                if (getMeasureUnitSaveLarge() != null)
//                {
//                    getMachinePartSave().getDimension().setLarge(getMachinePartSave().getDimension().getLarge() + "-" + getMeasureUnitSaveLarge().getAcronym());
//                }
//                if (getMeasureUnitSaveWeight() != null)
//                {
//                    getMachinePartSave().getDimension().setWeight(getMachinePartSave().getDimension().getWeight() + "-" + getMeasureUnitSaveWeight().getAcronym());
//                }
//                if (getMeasureUnitSaveVolume() != null)
//                {
//                    getMachinePartSave().getDimension().setVolume(getMachinePartSave().getDimension().getVolume() + "-" + getMeasureUnitSaveVolume().getAcronym());
//                }
//                if (getMeasureUnitSaveThickness() != null)
//                {
//                    getMachinePartSave().getDimension().setThickness(getMachinePartSave().getDimension().getThickness() + "-" + getMeasureUnitSaveThickness().getAcronym());
//                }
//                if (getMeasureUnitSaveRadio() != null)
//                {
//                    getMachinePartSave().getDimension().setRadio(getMachinePartSave().getDimension().getRadio() + "-" + getMeasureUnitSaveRadio().getAcronym());
//                }
                getScMachinePartServer().saveMachinePart(getMachinePartSave());
                getMachinePartList().add(getMachinePartSave());
                cleanMachinePartSave();
            }
            catch (Exception e)
            {
                log.error("Error almacenando el insumo", e);
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
                cleanMachinePartSave();
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
        if (getMachinePartSelected() != null)
        {
        //Almacenamos el insumo

//            if (getMeasureUnitSaveHigh() != null)
//            {
//                getMachinePartSelected().getDimension().setHight(getMachinePartSelected().getDimension().getHight() + "-" + getMeasureUnitSaveHigh().getAcronym());
//            }
//            if (getMeasureUnitSaveWidth() != null)
//            {
//                getMachinePartSelected().getDimension().setWidth(getMachinePartSelected().getDimension().getWidth() + "-" + getMeasureUnitSaveWidth().getAcronym());
//            }
//            if (getMeasureUnitSaveLarge() != null)
//            {
//                getMachinePartSelected().getDimension().setLarge(getMachinePartSelected().getDimension().getLarge() + "-" + getMeasureUnitSaveLarge().getAcronym());
//            }
//            if (getMeasureUnitSaveWeight() != null)
//            {
//                getMachinePartSelected().getDimension().setWeight(getMachinePartSelected().getDimension().getWeight() + "-" + getMeasureUnitSaveWeight().getAcronym());
//            }
//            if (getMeasureUnitSaveVolume() != null)
//            {
//                getMachinePartSelected().getDimension().setVolume(getMachinePartSelected().getDimension().getVolume() + "-" + getMeasureUnitSaveVolume().getAcronym());
//            }
//            if (getMeasureUnitSaveThickness() != null)
//            {
//                getMachinePartSelected().getDimension().setThickness(getMachinePartSelected().getDimension().getThickness() + "-" + getMeasureUnitSaveThickness().getAcronym());
//            }
//            if (getMeasureUnitSaveRadio() != null)
//            {
//                getMachinePartSelected().getDimension().setRadio(getMachinePartSelected().getDimension().getRadio() + "-" + getMeasureUnitSaveRadio().getAcronym());
//            }
        }
    }

    /**
     * Método encargado de realizar la persistencia de una  máquina, actualizando
     * listas y objetos incluidos en el.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void updateMachinePart()
    {
        //Valido que el insumo no sea nulo
        if (getMachinePartSelected() != null)
        {
            joinColumnsUpdate();
            try
            {
                getScMachinePartServer().updateMachinePart(getMachinePartSelected());
                int index = 0;
                for (ScMachinePart machinePart : getMachinePartList())
                {
                    if (machinePart.getIdMachinePart().equals(getMachinePartSelected().getIdMachinePart()))
                    {
                        break;
                    }
                    index++;
                }
                if (index < getMachinePartList().size())
                {
                    getMachinePartList().set(index, getMachinePartSelected());
                }
                cleanMachinePartSave();
            }
            catch (Exception e)
            {
                log.error("Error actualizando el insumo", e);
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
                cleanMachinePartSave();
            }

        }

    }

    /**
     * Método encargado de eliminar una  máquina.
     *
     * @autor Gustavo Chavarro Ortiz
     */
    public void deleteMachinePart()
    {
        if (getMachinePartSelected() != null)
        {
            try
            {
                getScMachinePartServer().deleteMachinePart(getMachinePartSelected());
                getMachinePartList().remove(getMachinePartSelected());
            }
            catch (Exception e)
            {
                log.error("Error eliminando el insumo", e);
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
                cleanMachinePartSave();
            }
        }
    }

    /**
     * Método encargado de inicializar todas las listas para crear una  máquina.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void cleanListSaves()
    {
        setSpecificationsSave(new ScMachinePartAttached());
        setFeacturesSave(new ScMachinePartAttached());
        setObservationsSave(new ScMachinePartAttached());

        if (getSpecificationListSave() == null)
        {
            setSpecificationListSave(new ArrayList<ScMachinePartAttached>());
        }
        if (getFeacturesListSave() == null)
        {
            setFeacturesListSave(new ArrayList<ScMachinePartAttached>());
        }
        if (getObservationListSave() == null)
        {
            setObservationListSave(new ArrayList<ScMachinePartAttached>());
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
                                getMachinePartSave().setPathPicture(file.getAbsolutePath());
                                break;
                            case 2://opción para actualizar
                                getMachinePartSelected().setPathPicture(file.getAbsolutePath());
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
                RequestContext.getCurrentInstance().execute("PF('dialogMachinePartSave').show()");
                break;
            case 2://opción para actualizar
                RequestContext.getCurrentInstance().execute("PF('dialogMachinePartUpdate').show()");
                break;
            default:
                break;
        }
    }

    /** 
     * Método encargado de visualizar la imagen de un elemento.
     *
     * @return String cadena con la ruta de la imagen
     * @param machinePart insumo al que se le consultará la imagen
     * @author Gustavo Chavarro Ortiz
     */
    public String searchImage(ScMachinePart machinePart)
    {
        try
        {
            if (machinePart != null)
            {
                if (!Utilities.isEmpty(machinePart.getPathPicture())) 
                {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(machinePart.getPathPicture())));
                    //la constante me permite mapear imagenes externas
                    return DMESConstants.PATH_EXTERN_PICTURES + machinePart.getPathPicture();
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
     * Método encargado de limpiar los campos para eliminar una  máquina
     *
     * @param machinePart insumo a eliminar
     * @author Gustavo Chavarro Ortiz
     */
    public void selectedForDelete(ScMachinePart machinePart)
    {
        cleansTypesMeasures();
        setMachinePartSelected(machinePart);
    }

    /**
     * Método encargado de limpiar los campos para actualizar una  máquina
     *
     * @param machinePart insumo a actualizarlo
     * @author Gustavo Chavarro Ortiz
     */
    public void selectedForUpdate(ScMachinePart machinePart)
    {
        cleansTypesMeasures();

        try
        {
            //setMachinePartSelected((ScMachinePart) machinePart.clone());
            setMachinePartSelected(machinePart);
//            setMachinePartSelected(getScMachinePartServer().getMachinePartsById(machinePart.getIdMachinePart()));
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar el insumo a actualizar", e);
        }

//        fillListMachinePartLocation(machinePart.getMachinePartStock().getIdMachine());
//        if (!Utilities.isEmpty(getMachinePartSelected().getDimension().getHight()))
//        {
//            valueToList(getMachinePartSelected().getDimension().getHight(), 1);
//        }
//        if (!Utilities.isEmpty(getMachinePartSelected().getDimension().getWidth()))
//        {
//            valueToList(getMachinePartSelected().getDimension().getWidth(), 2);
//        }
//        if (!Utilities.isEmpty(getMachinePartSelected().getDimension().getLarge()))
//        {
//            valueToList(getMachinePartSelected().getDimension().getLarge(), 3);
//        }
//        if (!Utilities.isEmpty(getMachinePartSelected().getDimension().getWeight()))
//        {
//            valueToList(getMachinePartSelected().getDimension().getWeight(), 4);
//        }
//        if (!Utilities.isEmpty(getMachinePartSelected().getDimension().getVolume()))
//        {
//            valueToList(getMachinePartSelected().getDimension().getVolume(), 5);
//        }
//        if (!Utilities.isEmpty(getMachinePartSelected().getDimension().getThickness()))
//        {
//            valueToList(getMachinePartSelected().getDimension().getThickness(), 6);
//        }
//        if (!Utilities.isEmpty(getMachinePartSelected().getDimension().getRadio()))
//        {
//            valueToList(getMachinePartSelected().getDimension().getRadio(), 7);
//        }
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

//                getMachinePartSelected().getDimension().setHight(fields[0]);
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

//                getMachinePartSelected().getDimension().setWidth(fields[0]);
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

//                getMachinePartSelected().getDimension().setLarge(fields[0]);
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

//                getMachinePartSelected().getDimension().setWeight(fields[0]);
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

//                getMachinePartSelected().getDimension().setVolume(fields[0]);
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

//                getMachinePartSelected().getDimension().setThickness(fields[0]);
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

//                getMachinePartSelected().getDimension().setRadio(fields[0]);
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
     * Método encargado de borrar una característica agregada a la lista para
     * actualizar un inusmo.
     *
     * @param feacture característica a borrar
     * @author Gustavo Chavarro Ortiz
     */
    public void deleteUpdateFeactures(ScMachinePartAttached feacture)
    {
        if (getMachinePartSelected().getScMachinePartAttachedList() != null && !getMachinePartSelected().getScMachinePartAttachedList().isEmpty())
        {
            int index = 0; //Posición del objeto que se eliminará
            for (ScMachinePartAttached iterator : getMachinePartSelected().getScMachinePartAttachedList())
            {
                if (iterator.getTittle().equals(feacture.getTittle())
                        && iterator.getDescription().equals(feacture.getDescription()))
                {
                    break;//Rompempos el ciclo
                }
                index++;//Aumentamos la posición
            }
            getMachinePartSelected().getScMachinePartAttachedList().remove(index);//Removemos el elemento en la posición hallada
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
    public void deleteUpdateObservation(ScMachinePartAttached observations)
    {
        if (getMachinePartSelected().getScMachinePartAttachedList() != null && !getMachinePartSelected().getScMachinePartAttachedList().isEmpty())
        {
            int index = 0; //Posición del objeto que se eliminará
            for (ScMachinePartAttached iterator : getMachinePartSelected().getScMachinePartAttachedList())
            {
                if (iterator.getTittle().equals(observations.getTittle())
                        && iterator.getDescription().equals(observations.getDescription()))
                {
                    break;//Rompempos el ciclo
                }
                index++;//Aumentamos la posición
            }
            getMachinePartSelected().getScMachinePartAttachedList().remove(index);//Removemos el elemento en la posición hallada
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
                                    getDocumentsSave().setDocumentType(getFileSave().getContentType());
                                    getDocumentsSave().setUploadBy(getSessionBean().getScUser().getLogin());

                                    getDocumentsSave().setCreationDate(new Date());
                                    switch (option)
                                    {
                                        case 1://opción para guardar
                                            getDocumentsSave().setIdMachinePart(getMachinePartSave());
                                            getDocumentsListSave().add(getDocumentsSave());

                                            break;
                                        case 2://opción para actualizar
                                            getDocumentsSave().setIdMachinePart(getMachinePartSelected());
                                            getMachinePartSelected().getScMachinePartDocument().add(getDocumentsSave());

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
                RequestContext.getCurrentInstance().execute("PF('dialogMachinePartSave').show()");
                break;
            case 2://opción para actualizar
                RequestContext.getCurrentInstance().execute("PF('dialogMachinePartUpdate').show()");
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
        setDocumentsSave(new ScMachinePartDocument());
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
            Object object = getScMachinePartServer().getInitialParameters();
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
    public void downloadDocument(ScMachinePartDocument scDocumentsSelected)
    {
        try
        {
            String fileName = scDocumentsSelected.getDocumentName();
            String path = scDocumentsSelected.getDocumentPath();
            int positionLimitName = fileName.indexOf("."); //Extraemos la posicion del delimitar del tipo del archivo
            String fileType = fileName.substring(positionLimitName + 1, fileName.length()); //Extraemos el tipo del archivo
            File fileToDownload = new File(path + "/" + fileName);
            InputStream machinePartStream = new FileInputStream(fileToDownload);
            byte[] buffer = new byte[2048];
            int offset = 0;
            int numRead = 0;
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext().getResponse();
            response.setContentType(scDocumentsSelected.getDocumentType());
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            OutputStream responseOutputStream = response.getOutputStream();

            while ((numRead = machinePartStream.read(buffer)) > 0)
            {
                responseOutputStream.write(buffer, 0, numRead);
            }
            machinePartStream.close();
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

    public List<ScMachinePart> getMachinePartList()
    {
        return machinePartList;
    }

    public void setMachinePartList(List<ScMachinePart> machinePartList)
    {
        this.machinePartList = machinePartList;
    }

    public SessionBean getSessionBean()
    {
        return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean)
    {
        this.sessionBean = sessionBean;
    }

    public IScMachinePart getScMachinePartServer()
    {
        return scMachinePartServer;
    }

    public void setScMachinePartServer(IScMachinePart scMachinePartServer)
    {
        this.scMachinePartServer = scMachinePartServer;
    }

    public ScMachinePart getMachinePartSelected()
    {
        return machinePartSelected;
    }

    public void setMachinePartSelected(ScMachinePart machinePartSelected)
    {
        this.machinePartSelected = machinePartSelected;
    }

    public ScMachinePart getMachinePartSave()
    {
        return machinePartSave;
    }

    public void setMachinePartSave(ScMachinePart machinePartSave)
    {
        this.machinePartSave = machinePartSave;
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

    public ScMachineLocation getMachinePartLocationSave()
    {
        return locaionSave;
    }

    public void setMachinePartLocationSave(ScMachineLocation locaionSave)
    {
        this.locaionSave = locaionSave;
    }

    public ScMachineLocation getMachinePartLocationSelected()
    {
        return loationSelected;
    }

    public void setMachinePartLocationSelected(ScMachineLocation loationSelected)
    {
        this.loationSelected = loationSelected;
    }

    public List<ScMachineLocation> getMachinePartLocationsList()
    {
        return machineLocationsList;
    }

    public void setMachinePartLocationsList(List<ScMachineLocation> machineLocationsList)
    {
        this.machineLocationsList = machineLocationsList;
    }

    public List<ScMachine> getMachinesList()
    {
        return machinesList;
    }

    public void setMachinesList(List<ScMachine> machinesList)
    {
        this.machinesList = machinesList;
    }

    public ScMachine getMachineSave()
    {
        return machineSave;
    }

    public void setMachineSave(ScMachine machineSave)
    {
        this.machineSave = machineSave;
    }

    public ScMachine getMachineSelected()
    {
        return machineSelected;
    }

    public void setMachineSelected(ScMachine machineSelected)
    {
        this.machineSelected = machineSelected;
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

    public List<ScMachinePartAttached> getSpecificationListSave()
    {
        return specificationListSave;
    }

    public void setSpecificationListSave(List<ScMachinePartAttached> specificationListSave)
    {
        this.specificationListSave = specificationListSave;
    }

    public List<ScMachinePartAttached> getFeacturesListSave()
    {
        return feacturesListSave;
    }

    public void setFeacturesListSave(List<ScMachinePartAttached> feacturesListSave)
    {
        this.feacturesListSave = feacturesListSave;
    }

    public List<ScMachinePartAttached> getObservationListSave()
    {
        return observationListSave;
    }

    public void setObservationListSave(List<ScMachinePartAttached> observationListSave)
    {
        this.observationListSave = observationListSave;
    }

    
    public ScMachinePartAttached getSpecificationsSave()
    {
        return SpecificationsSave;
    }

    public void setSpecificationsSave(ScMachinePartAttached SpecificationsSave)
    {
        this.SpecificationsSave = SpecificationsSave;
    }

    public ScMachinePartAttached getFeacturesSave()
    {
        return feacturesSave;
    }

    public void setFeacturesSave(ScMachinePartAttached feacturesSave)
    {
        this.feacturesSave = feacturesSave;
    }

    public ScMachinePartAttached getObservationsSave()
    {
        return observationsSave;
    }

    public void setObservationsSave(ScMachinePartAttached observationsSave)
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

    public ScMachinePartDocument getDocumentsSave()
    {
        return documentsSave;
    }

    public void setDocumentsSave(ScMachinePartDocument documentsSave)
    {
        this.documentsSave = documentsSave;
    }

    public List<ScMachinePartDocument> getDocumentsListSave()
    {
        return documentsListSave;
    }

    public void setDocumentsListSave(List<ScMachinePartDocument> documentsListSave)
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
