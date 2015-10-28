/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.utilities;

/**
 * Proyecto: DMES - Versión: 1.0
 * @author (es): Gustavo Adolfo Chavarro Ortiz 
 * @Funcionalidad: Esta clase está encargada de guardar todos los valores constantes
 * utilizados por la aplicación.
 * @Modificado por:  
 * @Funcionalidad agregada:
 */
public class DMESConstants
{
   //Mensaje que se mostrará en todas las tablas y grillas donde no existan datos
   public static final String  MESSAGE_EMPTY_INFORMATION = "No existe información para visualizar.";
   public static final String  MESSAGE_ERROR_ADMINISTRATOR = "Su solicitud no pudo ser procesada, espere un momento e intente nuevamente, o comuniquese con el administrador para una solución.";
   public static final String  MESSAGE_TITTLE_ERROR_ADMINISTRATOR = "Error de Sistema";
   public static final String  MESSAGE_TITTLE_SUCCES = "Operación Exitosa";
   public static final String  MESSAGE_SUCCES = "Se realizó la operación con total éxito";
   public static final String  MESSAGE_TITTLE_ERROR_IMAGE = "La imagen no se pudo cargar";
   public static final String  MESSAGE__ERROR_IMAGE = "Debe seleccionar una imagen válida, que sea del tipo PNG, JPG o JPEG";
   public static final String PATH_IMAGE_DEFAULT = "/images/imageNotAvailable.png";   
   
   //id clase tipos
  
   public static final String TYPES_EXTENTIONS_IMAGES = "png,jpg,jpeg,gif";
   
   //Ruta para imagenes externas
   public static final String PATH_EXTERN_PICTURES = "/dynamic/?file=";
   
   //Nombres de las carpetas reservadas del sistema
   public static final String FILE_PATH_INPUTS_IMG = "inputs_filePath/img";//Insumos imagenes
   public static final String FILE_PATH_INPUTS_DOCS = "inputs_filePath/docs";//Insumos
   //Nombres de las carpetas reservadas del sistema
   public static final String FILE_PATH_PRODUCTS_IMG = "product_filePath/img";//Productos imagenes
   public static final String FILE_PATH_PRODUCTS_DOCS = "product_filePath/docs";//Productos
   //Nombres de las carpetas reservadas del sistema
   public static final String FILE_PATH_REPLACEMENT_IMG = "replacement_filePath/img";//Insumos imagenes
   public static final String FILE_PATH_REPLACEMENT_DOCS = "replacement_filePath/docs";//Insumos
   //Nombres de las carpetas reservadas del sistema
   public static final String FILE_PATH_TOOLS_IMG = "tools_filePath/img";//Productos imagenes
   public static final String FILE_PATH_TOOLS_DOCS = "tools_filePath/docs";//Productos
   //Nombres de las carpetas reservadas del sistema
   public static final String FILE_PATH_MACHINE_IMG = "machine_filePath/img";//maquina imagenes
   public static final String FILE_PATH_MACHINE_DOCS = "machine_filePath/docs";//maquina
   //Nombres de las carpetas reservadas del sistema
   public static final String FILE_PATH_PARTOFMACHINE_IMG = "partofmachine_filePath/img";//Partes de Maquina imagenes
   public static final String FILE_PATH_PARTOFMACHINE_DOCS = "partofmachine_filePath/docs";//Partes de Maquina
   
   //Subida de documentos
    public static final String EXTENSION_FILE = "pdf,xls,doc,xlsx,docx,txt,pps,ppt,pptx,ppsx";
    public static final String PATH_FILE = System.getProperty("user.home"); //Obtenemos la ruta del servidor
   
    //Módulos dentro del almacén
    public static String INPUTS = "Insumos";
    public static String PRODUCTS = "Productos";
    public static String REPLACEMENT = "Repuestos y Consumibles";
    public static String TOOLS = "Herramientas";
    
    
    //Dias de vencimiento para almacén
    public static Long DAY_WARNING = 1L;
    public static Long DAY_EXPIRED = 3L;
    public static Long DAY_FATAL   = 5L;
    
    //Estados de las ordenes del almacén
    public static Long STATE_PROGRAMMED = 1L;
    public static Long STATE_PROCESS    = 2L;
    public static Long STATE_LATE    = 3L;
    public static Long STATE_DELIVERED = 4L;
    public static Long STATE_CANCEL_STORE    = 5L;
    public static Long STATE_CANCEL_PRODUCTION    = 6L;
    public static Long STATE_CANCEL_MAINTENANCE    = 7L;
    public static Long DAYS_LABORAL = 25L;
    
    
    //Frecuencias
    public final  static String FREQUENCY_DAILY = "DAILY";
    public final  static String FREQUENCY_WEEKLY = "WEEKLY";
    public final  static String FREQUENCY_MONTHLY = "MONTHLY";
    public final  static String FREQUENCY_QUARTELY = "QUARTELY";
    public final  static String FREQUENCY_BIANNUAL = "BIANNUAL";
    public final  static String FREQUENCY_ANNUAL = "ANNUAL";
    public final  static Long YEARS_FREQUENCY = 5L;
    public final  static Long MONTHS_FREQUENCY = 5L;
    
    //Querys para consultar los items del almacén
    public static String QUERY_INPUT = "SELECT I.ID_INPUT, I.DESCRIPTION, S.CURRENT_STOCK  FROM DMES.SC_INPUT I, DMES.SC_STOCK S\n" +
    "WHERE I.ID_STOCK = S.ID_STOCK AND I.ID_INPUT IN (";
    
    public static String QUERY_PRODUCT = "SELECT P.ID_PRODUCT_FORMULATION, P.DESCRIPTION, S.CURRENT_STOCK  FROM DMES.SC_PRODUCT_FORMULATION P, DMES.SC_STOCK S\n" +
    "WHERE P.ID_STOCK = S.ID_STOCK AND P.ID_PRODUCT_FORMULATION IN (";
    
    public static String QUERY_REPLACEMENT = "SELECT R.ID_REPLACEMENT, R.NAME, S.CURRENT_STOCK  FROM DMES.SC_REPLACEMENT R, DMES.SC_STOCK S\n" +
    "WHERE R.ID_STOCK = S.ID_STOCK AND R.ID_REPLACEMENT IN (";
    
    public static String QUERY_TOOL = "SELECT T.ID_TOOL, T.NAME, S.CURRENT_STOCK  FROM DMES.SC_TOOL T, DMES.SC_STOCK S\n" +
    "WHERE T.ID_STOCK = S.ID_STOCK AND T.ID_TOOL IN (";
    
    public static String UPDATE_INPUT = "UPDATE DMES.SC_STOCK  SET CURRENT_STOCK = (CURRENT_STOCK OPERATION) WHERE ID_STOCK = (SELECT I.ID_STOCK  FROM DMES.SC_INPUT I, DMES.SC_STOCK S\n" +
"    WHERE I.ID_STOCK = S.ID_STOCK AND I.ID_INPUT = NUMBERID )";
    
    public static String UPDATE_PRODUCT = "UPDATE DMES.SC_STOCK  SET CURRENT_STOCK = (CURRENT_STOCK OPERATION)  WHERE ID_STOCK = (SELECT P.ID_STOCK  FROM DMES.SC_PRODUCT_FORMULATION P, DMES.SC_STOCK S\n" +
"    WHERE P.ID_STOCK = S.ID_STOCK AND P.ID_PRODUCT_FORMULATION = NUMBERID )";
    
    public static String UPDATE_REPLACEMENT = "UPDATE DMES.SC_STOCK  SET CURRENT_STOCK = (CURRENT_STOCK OPERATION) WHERE ID_STOCK = (SELECT R.ID_STOCK  FROM DMES.SC_REPLACEMENT R, DMES.SC_STOCK S\n" +
"    WHERE R.ID_STOCK = S.ID_STOCK AND R.ID_REPLACEMENT = NUMBERID )";
    
    public static String UPDATE_TOOL = "UPDATE DMES.SC_STOCK  SET CURRENT_STOCK = (CURRENT_STOCK OPERATION) WHERE ID_STOCK = (SELECT T.ID_STOCK  FROM DMES.SC_TOOL T, DMES.SC_STOCK S\n" +
"    WHERE T.ID_STOCK = S.ID_STOCK AND T.ID_TOOL = NUMBERID )";
}
 