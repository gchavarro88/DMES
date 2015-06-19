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
   public static final Long scClassTypeClassification=1L;
   public static final Long scClassTypePriority=2L;
   public static final Long scClassTypeLifeSpan=3L;
   public static final Long scClassTypeCost=4L;
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
   public static final String FILE_PATH_MACHINE_IMG = "machine_filePath/img";//Insumos imagenes
   public static final String FILE_PATH_MACHINE_DOCS = "machine_filePath/docs";//Insumos
   //Nombres de las carpetas reservadas del sistema
   public static final String FILE_PATH_PARTOFMACHINE_IMG = "partofmachine_filePath/img";//Productos imagenes
   public static final String FILE_PATH_PARTOFMACHINE_DOCS = "partofmachine_filePath/docs";//Productos
   
   //Subida de documentos
    public static final String EXTENSION_FILE = "pdf,xls,doc,xlsx,docx,txt,pps,ppt,pptx,ppsx";
    public static final String PATH_FILE = System.getProperty("user.home"); //Obtenemos la ruta del servidor
   
    //Módulos dentro del almacén
    public static String inputs = "Insumos";
    public static String products = "Productos";
    public static String replacement = "Repuestos y Consumibles";
    public static String tools = "Herramientas";
    
    
    //Dias de vencimiento para almacén
    public static Long DAY_WARNING = 1L;
    public static Long DAY_EXPIRED = 3L;
    public static Long DAY_FATAL   = 5L;
    
    //Estados de las ordenes del almacén
    public static Long STATE_PROGRAMMED = 1L;
    public static Long STATE_PROCESS    = 2L;
    public static Long STATE_LATE    = 3L;
    
    
    //Querys para consultar los items del almacén
    public static String queryInput = "SELECT I.ID_INPUT, I.DESCRIPTION, S.CURRENT_STOCK  FROM DMES.SC_INPUT I, DMES.SC_STOCK S\n" +
    "WHERE I.ID_STOCK = S.ID_STOCK AND I.ID_INPUT IN (";
    
    public static String queryProduct = "SELECT P.ID_PRODUCT_FORMULATION, P.DESCRIPTION, S.CURRENT_STOCK  FROM DMES.SC_PRODUCT_FORMULATION P, DMES.SC_STOCK S\n" +
    "WHERE P.ID_STOCK = S.ID_STOCK AND P.ID_PRODUCT_FORMULATION IN (";
    
    public static String queryReplacement = "SELECT R.ID_REPLACEMENT, R.NAME, S.CURRENT_STOCK  FROM DMES.SC_REPLACEMENT R, DMES.SC_STOCK S\n" +
    "WHERE R.ID_STOCK = S.ID_STOCK AND R.ID_REPLACEMENT IN (";
    
    public static String queryTool = "SELECT T.ID_TOOL, T.NAME, S.CURRENT_STOCK  FROM DMES.SC_TOOL T, DMES.SC_STOCK S\n" +
"WHERE T.ID_STOCK = S.ID_STOCK AND T.ID_TOOL IN (";
}
 