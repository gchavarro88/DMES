/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Machines | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.beans.resources.materials;

import com.sip.dmes.beans.SessionBean;
import com.sip.dmes.dao.bo.IScMachine;
import com.sip.dmes.entitys.ScCostCenter;
import com.sip.dmes.entitys.ScFactoryLocation;
import com.sip.dmes.entitys.ScInputDimension;
import com.sip.dmes.entitys.ScLocation;
import com.sip.dmes.entitys.ScMachine;
import com.sip.dmes.entitys.ScMachineAttached;
import com.sip.dmes.entitys.ScMachineDocument;
import com.sip.dmes.entitys.ScMeasureUnit;
import com.sip.dmes.entitys.ScMoney;
import com.sip.dmes.entitys.ScPartner;
import com.sip.dmes.entitys.ScPriority;
import com.sip.dmes.entitys.ScStore;
import com.sip.dmes.entitys.ScTime;
import com.sip.dmes.entitys.ScMachine;
import com.sip.dmes.entitys.ScMachineAttached;

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
public class ScMachineBean
{

    private List<ScMachine> machineList; //Lista de máquinas
    private ScMachine machineSave; //Máquina a agregar
    private ScMachine machineUpdate; //Máquina seleccionada
    private ScMachine machineSelected; //Máquina seleccionada
    private ScMachineDocument machineDocumentSave;
    private ScMachineDocument machineDocumentUpdate;
    private ScMachineAttached machineAttachedSave;
    private ScMachineAttached machineAttachedUpdate;
    
    private ScMeasureUnit measureUnitSave; //Unidad de medida seleccionado para agregar
    private ScMeasureUnit measureUnitSaveHigh; //Unidad de medida seleccionado para agregar
    private ScMeasureUnit measureUnitSaveWidth; //Unidad de medida seleccionado para agregar
    private ScMeasureUnit measureUnitSaveRadio; //Unidad de medida seleccionado para agregar
    private ScMeasureUnit measureUnitSaveLarge; //Unidad de medida seleccionado para agregar
    private ScMeasureUnit measureUnitSaveVolume; //Unidad de medida seleccionado para agregar
    private ScMeasureUnit measureUnitSaveThickness; //Unidad de medida seleccionado para agregar
    private ScMeasureUnit measureUnitSaveWeight; //Unidad de medida seleccionado para agregar
    
    private ScMoney moneySave;//Moneda a guardar
    private List<ScPartner> partnersList;//Listado de proveedores
    private List<ScCostCenter> costCenterList;//Listado de centros de costo
    private List<ScMeasureUnit> measureUnitsList;//Lista de unidades de medida
    private List<ScFactoryLocation> factoryLocationsList;//Lista de localizaciones
    private List<ScTime> timeList;//Lista de tiempos
    private List<ScPriority> priorityList;//Lista de prioridades
    private List<ScMachineDocument> listMachineDocument;//Lista de documentos a guardar
    private List<ScMachineAttached> listMachineAttached;//Lista de adjuntos a guardar
    private List<ScMoney> moneyList;//Lista de monedas
    private UploadedFile fileSave;//Documento a subir
    private UploadedFile fileUpdate;//Documento a actualizar
    
    
    private ScFactoryLocation factoryLocationSave; //Localizacion seleccionada para agregar
    private ScFactoryLocation factoryLocationSelected; //Localizacion seleccionada para agregar al repuesto
    private ScPriority prioritySave; //Prioridad seleccionada para agregar al repuesto
    private UploadedFile pictureFile; //Archivo que se copiara para la imagen del repuesto
    private ScCostCenter costCenterSave; //Centro de Costo para agregar
    private SessionBean sessionBean; //Bean de sesion
    private IScMachine scMachineServer;
    
    private final static Logger log = Logger.getLogger(ScMachineBean.class);
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

    
    @PostConstruct
    public void init()
    {
        fillListMachine();
        fillListPartners();
        fillListCostCenter();
        fillListPriority();
        fillListMeasure();
        fillListMoney();
        fillListTimes();
        fillListFactoryLocation();
        cleanFieldsInit();
        getinitalParameters();
        
    }
    
    /**
     * Método encargado de llenar la lista de máquinas.
     * @author Gustavo Chavarro Ortiz
     */
    public void fillListMachine()
    {
        try
        {
            setMachineList(getScMachineServer().getAllMachine());
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar las máquinas");
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
            setPartnersList(getScMachineServer().getAllPartners());
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar los proveedores para las máquinas", e);
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
            setTimeList(getScMachineServer().getAllTimes());
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar los tiempos para las máquinas", e);
        }
    }
    
    /**
     * Método encargado de llenar la lista de tiempos.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void fillListFactoryLocation()
    {
        try
        {
            //Se consultan todos los tiempos existentes
            setFactoryLocationsList(getScMachineServer().getAllFactoryLocations());
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar las localizaciones de fábrica para las máquinas", e);
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
            setCostCenterList(getScMachineServer().getAllCostCenter());
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar los proveedores para las máquinas", e);
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
            setPriorityList(getScMachineServer().getAllPrioritys());
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar las prioridades para las máquinas", e);
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
            setMeasureUnitsList(getScMachineServer().getAllMeasureUnits());
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar las medidas para las máquinas", e);
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
            setMoneyList(getScMachineServer().getAllMoneys());
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar las monedas para las máquinas", e);
        }
    }

    /**
     * Método encargado de vaciar los objetos.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void cleanFieldsInit()
    {

        setMachineSelected(new ScMachine());
        setCostCenterSave(new ScCostCenter());
        setMeasureUnitSave(new ScMeasureUnit());
        cleanDocumentSave();
        cleanMachineSave();
        setListMachineDocument(new ArrayList<ScMachineDocument>());
        setListMachineAttached(new ArrayList<ScMachineAttached>());
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
                getScMachineServer().saveMeasureUnit(getMeasureUnitSave());
                if (getMeasureUnitsList() == null)
                {
                    setMeasureUnitsList(new ArrayList<ScMeasureUnit>());
                }
                getMeasureUnitsList().add(getMeasureUnitSave());
                cleanFieldsMeasure();
            }
            else
            {
                log.error("Error al intentar crear la unidad de medida para máquinas");
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }

        }
        catch (Exception e)
        {
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            log.error("Error al intentar agregar una unidad de medida desde máquinas", e);
        }

    }
    
    
    /**
     * Método encargado de inicializar todas las listas para crear un máquina.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void cleanListSaves()
    {
        setListMachineAttached(new ArrayList<ScMachineAttached>());
        setListMachineDocument(new ArrayList<ScMachineDocument>());
    }
    
    public void cleanMachineSave()
    {
        setMachineSave(new ScMachine());
        
        //Creamos el objeto de dimension para la segunda pestaña
        getMachineSave().setIdDimension(new ScInputDimension());
        cleanListSaves();
        setMachineAttachedSave(new ScMachineAttached());
        setMachineDocumentSave(new ScMachineDocument());
        cleansTypesMeasures();
    }
    
    /**
     * Método encargado de limpiar los campos para eliminar un máquina
     *
     * @param machine máquina a eliminar
     * @author Gustavo Chavarro Ortiz
     */
    public void selectedForDelete(ScMachine machine)
    {
        cleansTypesMeasures();
        setMachineSelected(machine);
    }
    
    
    
    /**
     * Método encargado de limpiar los campos para actualizar un maquina
     *
     * @param machine maquina a actualizarla
     * @author Gustavo Chavarro Ortiz
     */
    public void selectedForUpdate(ScMachine machine)
    {
        cleansTypesMeasures();

        
        setMachineUpdate(machine);
        
        if (!Utilities.isEmpty(getMachineUpdate().getIdDimension().getHight()))
        {
            valueToList(getMachineUpdate().getIdDimension().getHight(), 1);
        }
        if (!Utilities.isEmpty(getMachineUpdate().getIdDimension().getWidth()))
        {
            valueToList(getMachineUpdate().getIdDimension().getWidth(), 2);
        }
        if (!Utilities.isEmpty(getMachineUpdate().getIdDimension().getLarge()))
        {
            valueToList(getMachineUpdate().getIdDimension().getLarge(), 3);
        }
        if (!Utilities.isEmpty(getMachineUpdate().getIdDimension().getWeight()))
        {
            valueToList(getMachineUpdate().getIdDimension().getWeight(), 4);
        }
        if (!Utilities.isEmpty(getMachineUpdate().getIdDimension().getVolume()))
        {
            valueToList(getMachineUpdate().getIdDimension().getVolume(), 5);
        }
        if (!Utilities.isEmpty(getMachineUpdate().getIdDimension().getThickness()))
        {
            valueToList(getMachineUpdate().getIdDimension().getThickness(), 6);
        }
        if (!Utilities.isEmpty(getMachineUpdate().getIdDimension().getRadio()))
        {
            valueToList(getMachineUpdate().getIdDimension().getRadio(), 7);
        }
    }
    
    
    /**
     * Método encargado de retornar la lista de adjuntos de acuerdo a un tipo
     * @param attachedListSave lista de donde serán extraidos los adjuntos
     * @param type tipo del adjunto extraido
     * @return List<ScMachineAttached> tipo del adjunto a devolver
     * @author Gustavo Chavarro Ortiz
     */
    public List<ScMachineAttached> getAttachedList(List<ScMachineAttached> attachedListSave, String type)
    {
        List<ScMachineAttached> result = new ArrayList<ScMachineAttached>();
        if(attachedListSave != null && !attachedListSave.isEmpty())
        {
            for(ScMachineAttached attached: attachedListSave)
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
     * Método encargado de eliminar una máquina.
     *
     * @autor Gustavo Chavarro Ortiz
     */
    public void deleteMachine()
    {
        if (getMachineSelected() != null)
        {
            try
            {
                getScMachineServer().deleteMachine(getMachineSelected());
                getMachineList().remove(getMachineSelected());
            }
            catch (Exception e)
            {
                log.error("Error eliminando el máquina", e);
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
                cleanMachineSave();
            }
        }
    }

    
    /**
     * Método encargado de llevar el flujo al guardar un máquina.
     *
     * @param event evento en el cual se encuentra el asistente para crear
     * máquinas
     * @return String al final retorna el nombre de la siguiente pestaña del
     * asistente
     * @author Gustavo Chavarro Ortiz
     */
    public String onFlowProcessSaveMachine(FlowEvent event)
    {
        int packingUnit = -1;
        if (event.getNewStep().equals(TAB_GENERAL))
        {
            return TAB_GENERAL;
        }
        if (event.getOldStep().equals(TAB_GENERAL))
        {
            if (validateFields("Nombre Máquina", getMachineSave().getName(), 3))
            {
                return event.getOldStep();
            }   
            if (validateFields("Vida Útil", getMachineSave().getUsefulLife()+ "", 2))
            {
                return event.getOldStep();
            }
            if (validateFields("Unidad de Tiempo", getMachineSave().getIdTime(), 4))
            {
                return event.getOldStep();
            }
            if (validateFields("Tipo de Máquina", getMachineSave().getType(), 3))
            {
                return event.getOldStep();
            }
            //Validamos que el valor sea mayor que cero
            if (validateFields("Valor Hora", getMachineSave().getHourValue()+ "", 1))
            {
                return event.getOldStep();
            }
            if (validateFields("Moneda", getMachineSave().getIdMoney(), 4))
            {
                return event.getOldStep();
            }
            if (validateFields("Marca", getMachineSave().getMark(), 3))
            {
                return event.getOldStep();
            }

            if (Utilities.isEmpty(getMachineSave().getPathPicture()))
            {
                getMachineSave().setPathPicture(" ");//Setteamos la ruta de la imagen
            }
            if (validateFields("Serie", getMachineSave().getSerie(), 3))
            {
                return event.getOldStep();
            }

            //Validamos los campos seleccionables
            if (validateFields("Proveedor y Garantía", getMachineSave().getIdPartner(), 4))
            {
                return event.getOldStep();
            }
            if (validateFields("Centro de Costos", getMachineSave().getIdCostCenter(), 4))
            {
                return event.getOldStep();
            }
            if (validateFields("Localización", getMachineSave().getFactoryLocation(), 4))
            {
                return event.getOldStep();
            }
            if (validateFields("Prioridad", getMachineSave().getIdPriority(), 4))
            {
                return event.getOldStep();
            }
            for(ScTime time: getTimeList())
            {
                if(time.getIdTime().equals(getMachineSave().getIdTime().getIdTime()))
                {
                    getMachineSave().setIdTime(time);
                }
            }
            //Agregamos la fecha de creación del máquina
            //getMachineSave().setCreationDate(new Date());
            //getMachineSave().setValueMinutes((getMachineSave().getUsefulLife() * getMachineSave().getIdTime().getMinutes()));
        }
        //Si pasamos de la pestaña de datos generales a stock y dimensiones
        else if (event.getOldStep().equals(TAB_STOCK))
        {
            if (validateFields("Altura", getMachineSave().getIdDimension().getHight(), 1))
            {
                return event.getOldStep();
            }
            else if (getMeasureUnitSaveHigh() == null)
            {
                addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para la Altura");
                return event.getOldStep();
            }
            if (validateFields("Ancho", getMachineSave().getIdDimension().getWidth(), 1))
            {
                return event.getOldStep();
            }
            else if (getMeasureUnitSaveWidth() == null)
            {
                addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Ancho");
                return event.getOldStep();
            }
            if (validateFields("Largo", getMachineSave().getIdDimension().getLarge(), 1))
            {
                return event.getOldStep();
            }
            else if (getMeasureUnitSaveLarge() == null)
            {
                addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Largo");
                return event.getOldStep();
            }
            if (validateFields("Peso", getMachineSave().getIdDimension().getWeight(), 1))
            {
                return event.getOldStep();
            }
            else if (getMeasureUnitSaveWeight() == null)
            {
                addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Peso");
                return event.getOldStep();
            }
            if (!Utilities.isEmpty(getMachineSave().getIdDimension().getVolume()))
            {
                if (validateFields("Volumen", getMachineSave().getIdDimension().getVolume(), 1))
                {
                    return event.getOldStep();
                }
                else if (getMeasureUnitSaveVolume() == null)
                {
                    addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Volumen");
                    return event.getOldStep();
                }
            }
            if (!Utilities.isEmpty(getMachineSave().getIdDimension().getThickness()))
            {
                if (validateFields("Grosor", getMachineSave().getIdDimension().getThickness(), 1))
                {
                    return event.getOldStep();
                }
                else if (getMeasureUnitSaveThickness() == null)
                {
                    addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Grosor");
                    return event.getOldStep();
                }
            }
            if (!Utilities.isEmpty(getMachineSave().getIdDimension().getRadio()))
            {
                if (validateFields("Radio", getMachineSave().getIdDimension().getRadio(), 1))
                {
                    return event.getOldStep();
                }
                else if (getMeasureUnitSaveRadio() == null)
                {
                    addError(null, "Campo obligatorio", "Debe seleccionar una unidad de medida para el Radio");
                    return event.getOldStep();
                }
            }
            if (!Utilities.isEmpty(getMachineSave().getIdDimension().getWeight()))
            {
                if (validateFields("Peso", getMachineSave().getIdDimension().getWeight(), 1))
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
     * Método encargado de eliminar un adjunto.
     * @param machineAttached adjunto que sera guardado
     * @param attachedListSave lista de adjuntos a la que será agregado el adjunto en cuestión
     * @author Gustavo Chavarro Ortiz
     */
    public void deleteAttached(List<ScMachineAttached> attachedListSave, ScMachineAttached machineAttached)
    {
        int index = 0;
        if(machineAttached != null)
        {
            if(attachedListSave != null && !attachedListSave.isEmpty())
            {
                for(ScMachineAttached attached: attachedListSave)
                {
                    if(attached.getType().equals(machineAttached.getType()) && attached.getTittle()
                            .equals(machineAttached.getTittle()) && attached.getDescription()
                            .equals(machineAttached.getDescription()))
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

                getMachineUpdate().getIdDimension().setHight(fields[0]);
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

                getMachineUpdate().getIdDimension().setWidth(fields[0]);
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

                getMachineUpdate().getIdDimension().setLarge(fields[0]);
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

                getMachineUpdate().getIdDimension().setWeight(fields[0]);
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

                getMachineUpdate().getIdDimension().setVolume(fields[0]);
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

                getMachineUpdate().getIdDimension().setThickness(fields[0]);
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

                getMachineUpdate().getIdDimension().setRadio(fields[0]);
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
        if (!Utilities.isEmpty(getMachineDocumentSave().getDocumentTittle()))
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
                            String folderName = DMESConstants.FILE_PATH_MACHINE_DOCS;
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
                                    getMachineDocumentSave().setDocumentName(fileName);
                                    getMachineDocumentSave().setDocumentPath(folder.toString());
                                    getMachineDocumentSave().setDocumentType(getFileSave().getContentType());
                                    getMachineDocumentSave().setUploadBy(getSessionBean().getScUser().getLogin());

                                    getMachineDocumentSave().setCreationDate(new Date());
                                    switch (option)
                                    {
                                        case 1://opción para guardar
                                            getMachineDocumentSave().setIdMachine(getMachineSave());
                                            getListMachineDocument().add(getMachineDocumentSave());

                                            break;
                                        case 2://opción para actualizar
                                            getMachineDocumentSave().setIdMachine(getMachineUpdate());
                                            getMachineUpdate().getScMachineDocumentList().add(getMachineDocumentSave());

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
                RequestContext.getCurrentInstance().execute("PF('dialogMachineSave').show()");
                break;
            case 2://opción para actualizar
                RequestContext.getCurrentInstance().execute("PF('dialogMachineUpdate').show()");
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
        setMachineDocumentSave(new ScMachineDocument());
    }
    
    /**
     * Método encargado de vaciar los objetos.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void cleanFieldsLocationFactory()
    {
        setFactoryLocationSave(new ScFactoryLocation());
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
    public void cleanFieldsPriority()
    {
        setPrioritySave(new ScPriority());
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
                    getScMachineServer().saveCostCenter(getCostCenterSave());
                    if (getCostCenterList() == null)
                    {
                        setCostCenterList(new ArrayList<ScCostCenter>());
                    }
                    getCostCenterList().add(getCostCenterSave());
                    cleanFieldsCostCenter();
                }
                else
                {
                    log.error("Error al intentar crear el centro de costos desde máquinas");
                    addError(null, "Error al crear un centro de costos", "Debe ingresar solo números para el campo código del centro de costo");
                }
            }
            else
            {
                log.error("Error al intentar crear el centro de costos desde máquinas");
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }

        }
        catch (Exception e)
        {
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            log.error("Error al intentar agregar un centro de costos desde máquinas", e);
        }

    }
    
    
    /**
     * Método encargado de agregar un centro de costos.
     *
     * @author Gustavo Chavarro Ortiz
     */
    public void addFactoryLocation()
    {
        try
        {
            if (getFactoryLocationSave()!= null)
            {
                if (!Utilities.isEmpty(getFactoryLocationSave().getLocation()))
                {
                    getScMachineServer().saveFactoryLocation(getFactoryLocationSave());
                    if (getFactoryLocationsList()== null)
                    {
                        setFactoryLocationsList(new ArrayList<ScFactoryLocation>());
                    }
                    getFactoryLocationsList().add(getFactoryLocationSave());
                    cleanFieldsLocationFactory();
                }
                else
                {
                    log.error("Error al intentar crear la localización de máquinas desde máquinas");
                    addError(null, "Error al crear la localización de la fábrica", "Debe ingresar la localización de la fábrica");
                }
            }
            else
            {
                log.error("Error al intentar crear la localización de la fábrica desde máquinas");
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }

        }
        catch (Exception e)
        {
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            log.error("Error al intentar agregar la localización de la fábrica desde máquinas", e);
        }

    }
    
    /**
     * Método encargado de realizar la copia del archivo que se desea cargar.
     *
     * @param option se escoge la opción entre guardar y actualizar
     * @author: Gustavo Adolfo Chavarro Ortiz
     */
    public void handleFileUpload(int option)
    {
        
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
                String folderName = DMESConstants.FILE_PATH_MACHINE_IMG;
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
                                getMachineSave().setPathPicture(file.getAbsolutePath());
                                break;
                            case 2://opción para actualizar
                                getMachineSelected().setPathPicture(file.getAbsolutePath());
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
                RequestContext.getCurrentInstance().execute("PF('dialogMachineSave').show()");
                break;
            case 2://opción para actualizar
                RequestContext.getCurrentInstance().execute("PF('dialogMachineUpdate').show()");
                break;
            default:
                break;
        }
    }
    
    
    
    /** 
     * Método encargado de visualizar la imagen de un elemento.
     *
     * @return String cadena con la ruta de la imagen
     * @param machine maquina al que se le consultará la imagen
     * @author Gustavo Chavarro Ortiz
     */
    public String searchImage(ScMachine machine)
    {
        try
        {
            if (machine != null)
            {
                if (!Utilities.isEmpty(machine.getPathPicture())) 
                {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(machine.getPathPicture())));
                    //la constante me permite mapear imagenes externas
                    return DMESConstants.PATH_EXTERN_PICTURES + machine.getPathPicture();
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
            Object object = getScMachineServer().getInitialParameters();
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
    public void downloadDocument(ScMachineDocument scDocumentsSelected)
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
            response.setContentType(scDocumentsSelected.getDocumentType());
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
    
    /**
     * Getters and Setters
     */
    public List<ScMachine> getMachineList()
    {
        return machineList;
    }

    public void setMachineList(List<ScMachine> machineList)
    {
        this.machineList = machineList;
    }

    public ScMachine getMachineSave()
    {
        return machineSave;
    }

    public void setMachineSave(ScMachine machineSave)
    {
        this.machineSave = machineSave;
    }

    public ScMachine getMachineUpdate()
    {
        return machineUpdate;
    }

    public void setMachineUpdate(ScMachine machineUpdate)
    {
        this.machineUpdate = machineUpdate;
    }

    public IScMachine getScMachineServer()
    {
        return scMachineServer;
    }

    public void setScMachineServer(IScMachine scMachineServer)
    {
        this.scMachineServer = scMachineServer;
    }

    public SessionBean getSessionBean()
    {
        return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean)
    {
        this.sessionBean = sessionBean;
    }

    public ScMachineDocument getMachineDocumentSave()
    {
        return machineDocumentSave;
    }

    public void setMachineDocumentSave(ScMachineDocument machineDocumentSave)
    {
        this.machineDocumentSave = machineDocumentSave;
    }

    public ScMachineDocument getMachineDocumentUpdate()
    {
        return machineDocumentUpdate;
    }

    public void setMachineDocumentUpdate(ScMachineDocument machineDocumentUpdate)
    {
        this.machineDocumentUpdate = machineDocumentUpdate;
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

    public List<ScFactoryLocation> getFactoryLocationsList()
    {
        return factoryLocationsList;
    }

    public void setFactoryLocationsList(List<ScFactoryLocation> factoryLocationsList)
    {
        this.factoryLocationsList = factoryLocationsList;
    }

    public List<ScTime> getTimeList()
    {
        return timeList;
    }

    public void setTimeList(List<ScTime> timeList)
    {
        this.timeList = timeList;
    }

    public List<ScPriority> getPriorityList()
    {
        return priorityList;
    }

    public void setPriorityList(List<ScPriority> priorityList)
    {
        this.priorityList = priorityList;
    }

    public List<ScMachineDocument> getListMachineDocument()
    {
        return listMachineDocument;
    }

    public void setListMachineDocument(List<ScMachineDocument> listMachineDocument)
    {
        this.listMachineDocument = listMachineDocument;
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

    public ScMachine getMachineSelected()
    {
        return machineSelected;
    }

    public void setMachineSelected(ScMachine machineSelected)
    {
        this.machineSelected = machineSelected;
    }

    public List<ScMachineAttached> getListMachineAttached()
    {
        return listMachineAttached;
    }

    public void setListMachineAttached(List<ScMachineAttached> listMachineAttached)
    {
        this.listMachineAttached = listMachineAttached;
    }

    public ScMachineAttached getMachineAttachedSave()
    {
        return machineAttachedSave;
    }

    public void setMachineAttachedSave(ScMachineAttached machineAttachedSave)
    {
        this.machineAttachedSave = machineAttachedSave;
    }

    public ScMachineAttached getMachineAttachedUpdate()
    {
        return machineAttachedUpdate;
    }

    public void setMachineAttachedUpdate(ScMachineAttached machineAttachedUpdate)
    {
        this.machineAttachedUpdate = machineAttachedUpdate;
    }

    public ScFactoryLocation getFactoryLocationSave()
    {
        return factoryLocationSave;
    }

    public void setFactoryLocationSave(ScFactoryLocation factoryLocationSave)
    {
        this.factoryLocationSave = factoryLocationSave;
    }

    public ScFactoryLocation getFactoryLocationSelected()
    {
        return factoryLocationSelected;
    }

    public void setFactoryLocationSelected(ScFactoryLocation factoryLocationSelected)
    {
        this.factoryLocationSelected = factoryLocationSelected;
    }


    public ScPriority getPrioritySave()
    {
        return prioritySave;
    }

    public void setPrioritySave(ScPriority prioritySave)
    {
        this.prioritySave = prioritySave;
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
    
    
    
    
    
    
}