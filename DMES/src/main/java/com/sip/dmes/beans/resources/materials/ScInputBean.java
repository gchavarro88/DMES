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
import com.sip.dmes.entitys.ScInput;
import com.sip.dmes.entitys.ScInputDimension;
import com.sip.dmes.entitys.ScInputEquivalence;
import com.sip.dmes.entitys.ScInputFeactures;
import com.sip.dmes.entitys.ScInputLocation;
import com.sip.dmes.entitys.ScInputObservations;
import com.sip.dmes.entitys.ScInputSpecifications;
import com.sip.dmes.entitys.ScInputStock;
import com.sip.dmes.entitys.ScMeasureUnit;
import com.sip.dmes.entitys.ScPackingUnit;
import com.sip.dmes.entitys.ScPartner;
import com.sip.dmes.entitys.ScPriority;
import com.sip.dmes.entitys.ScStore;
import com.sip.dmes.utilities.DMESConstants;
import com.sip.dmes.utilities.Utilities;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
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
    private List<ScInputDimension> dimensionInputList;//Lista de dimensiones del insumo
    private List<ScInputEquivalence> equivalenceInputList; //Lista de equivalencias del insumo
    private ScInput inputSelected; //Insumo seleccionado para consulta, edición o eliminación
    private ScInput inputSave; //Insumo seleccionado para agregar
    private ScMeasureUnit measureUnitSave; //Unidad de medida seleccionado para agregar
    private ScMeasureUnit measureUnitSelected; //Unidad de medida seleccionado para agregar al insumo
    private ScPackingUnit packingUnitSave; //Unidad de empaque seleccionado para agregar
    private ScPackingUnit packingUnitSelected; //Unidad de empaque seleccionado para agregar al insumo
    private ScInputLocation inputLocationSave; //Localizacion seleccionada para agregar
    private ScInputLocation inputLocationSelected; //Localizacion seleccionada para agregar al insumo
    private ScStore storeSave; //Localizacion seleccionada para agregar
    private ScStore storeSelected; //Localizacion seleccionada para agregar al insumo
    private ScPriority prioritySave; //Prioridad seleccionada para agregar al insumo
    private SessionBean sessionBean; //Bean de sesion
    private UploadedFile pictureFile; //Archivo que se copiara para la imagen del insumo
    private ScCostCenter costCenterSave; //Centro de Costo para agregar
    private ScInputSpecifications SpecificationsSave;//Especificación a guardar
    private ScInputFeactures feacturesSave;//Característica a guardar
    private ScInputObservations observationsSave;//Observación a guardar
    private List<ScPartner> partnersList;//Listado de proveedores
    private List<ScCostCenter> costCenterList;//Listado de centros de costo
    private List<ScMeasureUnit> measureUnitsList;//Lista de unidades de medida
    private List<ScPackingUnit> packingUnitsList;//Lista de unidades de empaque
    private List<ScInputLocation> inputLocationsList;//Lista de localizaciones
    private List<ScStore> storesList;//Lista de almacenes
    private List<ScPriority> priorityList;//Lista de prioridades
    private List<ScInputSpecifications> specificationListSave;//Lista de especificaciones a guardar
    private List<ScInputFeactures> feacturesListSave;//Lista de características a guardar
    private List<ScInputObservations> observationListSave;//Lista de observaciones a guardar
    private List<ScInputEquivalence> equivalenceListSave;//Lista de equivalencias a guardar
        
    //Persistencia
    private IScInput scInputServer; //Dao de persistencia del insumos
    
    
    private final static Logger log = Logger.getLogger(ScInputDao.class);
   
    //Constantes
    private final String PATH_FILE = System.getProperty("user.home"); //Obtenemos la ruta del servidor
    //Tabs
    private final String TAB_GENERAL = "tabGeneral";
    private final String TAB_STOCK = "tabStock";
    private final String TAB_SPECIFICATIONS = "tabSpecifications";
    private final String TAB_FEACTURES = "tabFeactures";
    private final String TAB_OBSERVATIONS = "tabObservations";
    private final String TAB_EQUIVALENCE = "tabEquivalence";
    
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
        fillListInputLocation();
        fillListStore();
        fillListPriority();
        cleanFieldsInit();
    }
    
    /**
     * Método encargado de llenar la lista de insumos.
     * @author Gustavo Chavarro Ortiz
     */
    public void fillListInputs()
    {
        try
        {
            //Se consultan todos los insumos y se guardan en la lista ordenados por la fecha
            setInputList(getScInputServer().getAllInputs());
        }
        catch(Exception e)
        {
            log.error("Error al intentar consultar los insumos de la tabla", e);
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
            setPartnersList(getScInputServer().getAllPartners());
        }
        catch(Exception e)
        {
            log.error("Error al intentar consultar los proveedores para los insumos", e);
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
            setCostCenterList(getScInputServer().getAllCostCenter());
        }
        catch(Exception e)
        {
            log.error("Error al intentar consultar los proveedores para los insumos", e);
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
            setPackingUnitsList(getScInputServer().getAllPackingUnits());
        }
        catch(Exception e)
        {
            log.error("Error al intentar consultar las unidades de empaque para los insumos", e);
        }
    }
    
    /**
     * Método encargado de llenar la lista de localizaciones.
     * @author Gustavo Chavarro Ortiz
     */
    public void fillListInputLocation()
    {
        try
        {
            //Se consultan todos los proveedores existentes
            setInputLocationsList(getScInputServer().getAllInputLocations());
        }
        catch(Exception e)
        {
            log.error("Error al intentar consultar las localizaciones para los insumos", e);
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
            setStoresList(getScInputServer().getAllStores());
        }
        catch(Exception e)
        {
            log.error("Error al intentar consultar los almacenes para los insumos", e);
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
            setPriorityList(getScInputServer().getAllPrioritys());
        }
        catch(Exception e)
        {
            log.error("Error al intentar consultar las prioridades para los insumos", e);
        }
    }
    /**
     * Método encargado de vaciar los objetos.
     * @author Gustavo Chavarro Ortiz
     */
    public void cleanFieldsInit()
    {
        
        setInputSelected(new ScInput());
        setCostCenterSave(new ScCostCenter());
        setPackingUnitSave(new ScPackingUnit());
        setInputLocationSave(new ScInputLocation());
        setMeasureUnitSave(new ScMeasureUnit());
        cleanInputSave();
    }
    public void cleanInputSave()
    {
        setInputSave(new ScInput());
        //Creamos un stock para la siguiente pestaña
        getInputSave().setInputStock(new ScInputStock());
        //Creamos el objeto de dimension para la segunda pestaña
        getInputSave().setDimension(new ScInputDimension());
        cleanListSaves();
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
        setInputLocationSave(new ScInputLocation());
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
                    getScInputServer().saveCostCenter(getCostCenterSave());
                    if(getCostCenterList() == null)
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
            log.error("Error al intentar agregar un centro de costos desde insumos",e);
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
                
                getScInputServer().savePackingUnit(getPackingUnitSave());
                if(getPackingUnitsList() == null)
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
            log.error("Error al intentar crear la unidad de empaque desde insumos",e);
        }
    
    } 
    /**
     * Método encargado de agregar una unidad de empaque
     * @author Gustavo Chavarro Ortiz
     */
    public void addInputLocations()
    {
        try 
        {
            if(getInputLocationSave()!= null)
            {
                
                getScInputServer().saveLocationInput(getInputLocationSave());
                if(getInputLocationsList() == null)
                {
                    setInputLocationsList(new ArrayList<ScInputLocation>());
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
            log.error("Error al intentar crear la localización desde insumos",e);
        }
    }
    
   
    
    /**
     * Método encargado de llevar el flujo al guardar un insumo.
     * @param event evento en el cual se encuentra el asistente para crear insumos
     * @return String al final retorna el nombre de la siguiente pestaña del asistente
     * @author Gustavo Chavarro Ortiz
     */
    public String onFlowProcessSaveInput(FlowEvent event) 
    {    
        int packingUnit = -1;
        if(event.getOldStep().equals(TAB_GENERAL))
        {
            if(getInputSave() != null)
            {
                //Validamos que el valor sea mayor que cero
                if(getInputSave().getValue() <= 0)
                {
                    addError(null, "Error en el campo Valor del Insumo", "El Valor del Insumo debe ser un número mayor a cero");
                    log.error("Error en el campo Valor del Insumo, El Valor del Insumo debe ser un número mayor a cero");
                    return event.getOldStep();
                }
                //Validamos que el campo unidad de empaque sea numérico y diferente de vacío
                else if(!Utilities.isEmpty(getInputSave().getPackingUnit()) &&
                        Utilities.isNumeric(getInputSave().getPackingUnit()))
                { 
                    try
                    {
                        //Obtenemos el valor de la unidad de empaque
                         packingUnit = Integer.parseInt(getInputSave().getPackingUnit());
                         //Validamos que la unidad de empaque sea mayor a cero
                        if(packingUnit <= 0)
                        {
                            addError(null, "Error en el campo Unidad de Empaque", "El Valor Unidad de Empaque debe ser un número mayor a cero");
                            log.error("Error en el campo Unidad de Empaque, El Valor Unidad de Empaque debe ser un número mayor a cero");
                            return event.getOldStep();
                        }
                    }
                    catch (Exception e)
                    {
                        addError(null, "Error en el campo Unidad de Empaque", "El Valor Unidad de Empaque debe ser un número mayor a cero");
                        log.error("Error en el campo Unidad de Empaque, El Valor Unidad de Empaque debe ser un número mayor a cero");
                        return event.getOldStep();
                    }
                    
                }
                else
                {
                    addError(null, "Error en el campo Unidad de Empaque", "El Valor Unidad de Empaque debe ser un número mayor a cero");
                    log.error("Error en el campo Unidad de Empaque, El Valor Unidad de Empaque debe ser un número mayor a cero");
                    return event.getOldStep();
                }
            }
            if(Utilities.isEmpty(getInputSave().getPathPicture()))
            { 
                getInputSave().setPathPicture(" ");
            }
            //Agregamos la fecha de creación del insumo
            getInputSave().setCreationDate(new Date());
            
            //Modificamos la unidad de empaque con su unidad de medida
//            getInputSave().setPackingUnit(packingUnit + getPackingUnitSelected().getAcronym());
            //Validamos que la fecha de expiracion sea mayor que la fecha de creacion
            if(getInputSave().getExpiryDate().before(getInputSave().getCreationDate()))
            {
                addError(null, "Error en el campo Fecha de Expiración", "La Fecha de Expiración debe ser mayor que la fecha actual");
                log.error("Error en el campo Unidad de Empaque, El Valor Unidad de Empaque debe ser un número mayor a cero");
                return event.getOldStep();
            }
        }
        //Si pasamos de la pestaña de datos generales a stock y dimensiones
        else if(event.getOldStep().equals(TAB_STOCK))
        {
            //Validamos que ninguno de los campos del stock sea vacio o negativo
            if(getInputSave().getInputStock().getMaximeStock() <= 0)
            {
                addError(null, "Error en el campo Stock Máximo", "El Stock Máximo debe ser mayor que cero");
                log.error("Error en el campo Stock Máximo, El Stock Máximo debe ser un número mayor a cero");
                return event.getOldStep();
            }
            else if(getInputSave().getInputStock().getMinimeStock() <= 0)
            {
                addError(null, "Error en el campo Stock Mínimo", "El Stock Mínimo debe ser mayor que cero");
                log.error("Error en el campo Stock Mínimo, El Stock Mínimo debe ser un número mayor a cero");
                return event.getOldStep();
            }
            else if(getInputSave().getInputStock().getCurrentStock() <= 0)
            {
                addError(null, "Error en el campo Stock Real", "El Stock Real debe ser mayor que cero");
                log.error("Error en el campo Stock Real, El Stock Real debe ser un número mayor a cero");
                return event.getOldStep();
            }
            else if(getInputSave().getInputStock().getOptimeStock() <= 0)
            {
                addError(null, "Error en el campo Stock Óptimo", "El Stock Óptimo debe ser mayor que cero");
                log.error("Error en el campo Stock Óptimo, El Stock Óptimo debe ser un número mayor a cero");
                return event.getOldStep();
            }
            else if(getInputSave().getInputStock().getPriceUnit() <= 0)
            {
                addError(null, "Error en el campo Precio de Unidad", "El Precio de Unidad debe ser mayor que cero");
                log.error("Error en el campo Precio de Unidad, El Precio de Unidad debe ser un número mayor a cero");
                return event.getOldStep();
            }
            else if(getInputSave().getInputStock().getTotalValue() <= 0)
            {
                addError(null, "Error en el campo Valor Total", "El Valor Total debe ser mayor que cero");
                log.error("Error en el campo Valor Total, El Valor Total debe ser un número mayor a cero");
                return event.getOldStep();
            }
            //Validamos que las dimensiones sean correctas
            else if(validateFieldsDoubles("Altura", getInputSave().getDimension().getHight()))
            {
                return event.getOldStep();
            }
            else if(validateFieldsDoubles("Ancho", getInputSave().getDimension().getWidth()))
            {
                return event.getOldStep();
            }
            else if(validateFieldsDoubles("Largo", getInputSave().getDimension().getLarge()))
            {
                return event.getOldStep();
            }
            else if(validateFieldsDoubles("Peso", getInputSave().getDimension().getWeight()))
            {
                return event.getOldStep();
            }
            else if(validateFieldsDoubles("Volumen", getInputSave().getDimension().getVolume()))
            {
                return event.getOldStep();
            }
            else if(validateFieldsDoubles("Grosor", getInputSave().getDimension().getThickness()))
            {
                return event.getOldStep();
            }
            else if(validateFieldsDoubles("Radio", getInputSave().getDimension().getRadio()))
            {
                return event.getOldStep();
            }
            //Creamos la lista de especificaciones para la tercer pestaña
            
        }
        return event.getNewStep(); 
    }
    /**
     * Método encargado de inicializar todas las listas para crear un insumo.
     * @author Gustavo Chavarro Ortiz
     */
    public void cleanListSaves()
    {
        setSpecificationsSave(new ScInputSpecifications());
        setFeacturesSave(new ScInputFeactures());
        setObservationsSave(new ScInputObservations());
        
        if(getSpecificationListSave() == null)
        {
            setSpecificationListSave(new ArrayList<ScInputSpecifications>());
        }
        if(getFeacturesListSave() == null)
        {
            setFeacturesListSave(new ArrayList<ScInputFeactures>());
        }
        if(getObservationListSave() == null)
        {
            setObservationListSave(new ArrayList<ScInputObservations>());
        }
        if(getEquivalenceListSave() == null)
        {
            setEquivalenceListSave(new ArrayList<ScInputEquivalence>());
        }
    }
    
    /**
     * Método encargado de validar los campos doubles.
     * @param nameField nombre del campo a evaluar
     * @param value valor del campo a evaluar
     * @return boolean si se puede validar o no 
     * @author Gustavo Chavarro Ortiz
     */
    public boolean validateFieldsDoubles(String nameField, String value)
    {
        boolean isInvalid = false;
        if(!Utilities.isEmpty(value))
            {
                try
                {
                    double dimensionVariable = Double.parseDouble(value);
                    if(dimensionVariable <= 0)
                    {
                        throw new Exception(nameField+" menor o igual a cero");
                    }
                }
                catch (Exception e)
                {
                    isInvalid = true;
                    addError(null, "Error en el campo "+nameField, "Debe ingresar un número mayor que cero y usar"
                            + "como separador de decimales el punto, ejemplo: 3.24");
                    log.error("Error en el campo "+nameField+", Debe ingresar un número mayor que cero y usar"
                            + "como separador de decimales el punto, ejemplo: 3.24");
                    
                }
            }
        return isInvalid;
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
                    getSpecificationsSave().setCreationDate(new Date());
                    getSpecificationsSave().setIdInput(getInputSave());
                    getSpecificationListSave().add(getSpecificationsSave());
                    setSpecificationsSave(new ScInputSpecifications());
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
     * Método encargado de realizar la copia del archivo que se desea cargar.
     * @param event Evento que trae el archvio cargado al servidor
     * @author: Gustavo Adolfo Chavarro Ortiz
     */
    public void handleFileUpload() {
         //Validamos que el evento de copiado no sea nulo
            if(getPictureFile() != null)
            {
    
                String fileName =  getPictureFile().getFileName(); //Extraemos el nombre del archivo
                long fileSize    = getPictureFile().getSize(); //Extraemos el tamaño del archivo
                int positionLimitName = fileName.indexOf("."); //Extraemos la posicion del delimitar del tipo del archivo
                String fileType = fileName.substring(positionLimitName+1, fileName.length()); //Extraemos el tipo del archivo
                    //Validamos que el archivo contenga los tipos permitidos
                    if(DMESConstants.TYPES_EXTENTIONS_IMAGES.contains(fileType))
                    {
                        String folderName = DMESConstants.FILE_PATH_INPUTS;
                        //Creamos el folder
                        File folder = new File(PATH_FILE+"/"+folderName);
                        folder.mkdirs(); 
                        //Creamos el archivo con la ruta y el nombre de la carpeta
                        File file = new File(folder+"/"+fileName);
                        try
                        {
                            //Creamos el archivo y lo enviamos al metodo que lo escribe
                            if(writeFile(getPictureFile().getInputstream(), file))
                            {
                                getInputSave().setPathPicture(file.getAbsolutePath());
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
                        addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, "El archivo no pertenece a los tipos permitidos "+DMESConstants.TYPES_EXTENTIONS_IMAGES);
                    }
                }
                else
                {
                    addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, "El archivo se encuentra vacio");
                }
            RequestContext.getCurrentInstance().execute("PF('pictureSave').hide()");
            RequestContext.getCurrentInstance().execute("PF('dialogInputSave').show()");
    }
    
    /**
     * Método encargado de visualizar la imagen de un elemento.
     * @return String cadena con la ruta de la imagen
     * @param input insumo al que se le consultará la imagen
     * @author Gustavo Chavarro Ortiz
     */
    public String searchImage(ScInput input)
    {
        if(input != null)
        {
            if(!Utilities.isEmpty(input.getPathPicture()))
            {
                //la constante me permite mapear imagenes externas
                return DMESConstants.PATH_EXTERN_PICTURES+input.getPathPicture();
            }
        } 
        return DMESConstants.PATH_IMAGE_DEFAULT;
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
    
    public List<ScInput> getInputList()
    {
        return inputList;
    }

    public void setInputList(List<ScInput> inputList)
    {
        this.inputList = inputList;
    }

    public List<ScInputDimension> getDimensionInputList()
    {
        return dimensionInputList;
    }

    public void setDimensionInputList(List<ScInputDimension> dimensionInputList)
    {
        this.dimensionInputList = dimensionInputList;
    }

    public List<ScInputEquivalence> getEquivalenceInputList()
    {
        return equivalenceInputList;
    }

    public void setEquivalenceInputList(List<ScInputEquivalence> equivalenceInputList)
    {
        this.equivalenceInputList = equivalenceInputList;
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

    public ScMeasureUnit getMeasureUnitSave()
    {
        return measureUnitSave;
    }

    public void setMeasureUnitSave(ScMeasureUnit measureUnitSave)
    {
        this.measureUnitSave = measureUnitSave;
    }

    public List<ScMeasureUnit> getMeasureUnitsList()
    {
        return measureUnitsList;
    }

    public void setMeasureUnitsList(List<ScMeasureUnit> measureUnitsList)
    {
        this.measureUnitsList = measureUnitsList;
    }

    public ScMeasureUnit getMeasureUnitSelected()
    {
        return measureUnitSelected;
    }

    public void setMeasureUnitSelected(ScMeasureUnit measureUnitSelected)
    {
        this.measureUnitSelected = measureUnitSelected;
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

    public ScInputLocation getInputLocationSave()
    {
        return inputLocationSave;
    }

    public void setInputLocationSave(ScInputLocation inputLocationSave)
    {
        this.inputLocationSave = inputLocationSave;
    }

    public ScInputLocation getInputLocationSelected()
    {
        return inputLocationSelected;
    }

    public void setInputLocationSelected(ScInputLocation inputLocationSelected)
    {
        this.inputLocationSelected = inputLocationSelected;
    }

    public List<ScInputLocation> getInputLocationsList()
    {
        return inputLocationsList;
    }

    public void setInputLocationsList(List<ScInputLocation> inputLocationsList)
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
    
    
}
