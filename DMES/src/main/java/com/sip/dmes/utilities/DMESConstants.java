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
   public static final String TYPES_EXTENTIONS_IMAGES = "png,jpg,jpeg";
   
   //Ruta para imagenes externas
   public static final String PATH_EXTERN_PICTURES = "/dynamic/?file=";
   
   //Nombres de las carpetas reservadas del sistema
   public static final String FILE_PATH_INPUTS_IMG = "inputs_filePath/img";//Insumos imagenes
   public static final String FILE_PATH_INPUTS_DOCS = "inputs_filePath/docs";//Insumos
   
   //Subida de documentos
    public static final String EXTENSION_FILE = "pdf,xls,doc,xlsx,docx,txt,pps,ppt,pptx,ppsx";
    public static final String PATH_FILE = System.getProperty("user.home"); //Obtenemos la ruta del servidor
   
}
 