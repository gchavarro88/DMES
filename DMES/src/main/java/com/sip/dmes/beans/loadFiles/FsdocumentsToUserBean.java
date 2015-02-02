package com.sip.dmes.beans.loadFiles;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.sip.dmes.beans.SessionBean;
import com.sip.dmes.dao.bo.IFsDocuments;
import com.sip.dmes.entitys.ScDocuments;
import com.sip.dmes.entitys.ScUsers;
import com.sip.dmes.utilities.DMESConstants;
import com.sip.dmes.utilities.Utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author gchavarro88
 */
public class FsdocumentsToUserBean
{

    private final static Logger log = Logger.getLogger(FsdocumentsToUserBean.class); //Variable de logger que permite guardar registro de la aplicación
    private SessionBean sessionBean; //Bean de sesion
    private IFsDocuments fsDocumentsServer; //Bean para acceder al DAO de los documentos
    
    private List<ScDocuments> documentList;//Lista de documentos 
    private ScDocuments scDocumentsAdd;//Documento a Agregar
    private ScDocuments scDocumentsSelected; //Documento seleccionado
    private List<ScUsers> usersList;//Lista de usuarios a cargar documento
    private ScUsers userSelected; //Usuario seleccionado
    //Constantes
    private UploadedFile upLoadFile; //Objeto que permite traer un archivo que se copiará
    private int MAX_SIZE_FILE = 5;//Tamaño en megas del archivo
    private String EXTENSION_FILE = "pdf,xls,doc,xlsx,docx,txt,pps,ppt,pptx,ppsx";
    private String PATH_FILE = System.getProperty("user.home"); //Obtenemos la ruta del servidor
    
    
    
    /**
     * Creates a new instance of ScrolesBean
     */
    public FsdocumentsToUserBean()
    {
        
    } 
    /**
     * Método encargado de invocar los procedimientos iniciales para la primera vista 
     * de la página.
     * @author Gustavo Chavarro Ortiz
     */
    @PostConstruct
    public void initData()
    {
        getinitalParameters();
        cleanFieldSave();
        fillAllDocumentsToUser();
        
    }
    
    /**
     * Método encargado de llenar la tabla inicial de los documentos cargados por el usuario.
     * @author Gustavo Chavarro Ortiz
     */
    public void fillAllDocumentsToUser()
    {
        try
        {
            if(getDocumentList() == null)
            {
                setDocumentList(getFsDocumentsServer().getAllDocumentsToUser(getSessionBean().getScUser()));
            }
        }
        catch(Exception e)
        {
            log.error("Error al intentar consultar los documentos para el usuario "+getSessionBean().getScUser(), e);
        }
    }
    
    /**
     * Método encargado de llenar la tabla inicial de los usuarios.
     * @author Gustavo Chavarro Ortiz
     */
    public void fillAllUser()
    {
        try
        {
            if(getUsersList() == null)
            {
                setUsersList(getFsDocumentsServer().getUsersToDocuments(getSessionBean().getScUser()));
            }
        }
        catch(Exception e)
        {
            log.error("Error al intentar consultar los usuarios "+getSessionBean().getScUser(), e);
        }
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
            Object object = getFsDocumentsServer().getInitialParameters();
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
     * Método encargado de realizar la copia del archivo que se desea cargar.
     * @param event Evento que trae el archvio cargado al servidor
     * @author: Gustavo Adolfo Chavarro Ortiz
     */
    public void handleFileUpload() {
         //Validamos que el evento de copiado no sea nulo
        int bytesToMegabytes = 10485760; //Valor de representación de 1megabytes a bytes
        if(getScDocumentsAdd().getDocumentTittle() != null && getScDocumentsAdd().getDocumentTittle().length() > 0)
        {
            if(getUpLoadFile() != null )
         {
            String fileName = getUpLoadFile().getFileName(); //Extraemos el nombre del archivo
            long fileSize    = getUpLoadFile().getSize(); //Extraemos el tamaño del archivo
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
                        String folderName = lastName+"_"+firstName;
                        //Creamos el folder
                        File folder = new File(PATH_FILE+"/"+folderName);
                        folder.mkdirs();
                        //Creamos el archivo con la ruta y el nombre de la carpeta
                        File file = new File(folder+"/"+fileName);
                        try
                        {
                            //Creamos el archivo y lo enviamos al metodo que lo escribe
                            if(writeFile(getUpLoadFile().getInputstream(), file))
                            {
                                addLogDocument(folder.toString(), fileName, getUpLoadFile().getContentType());
                                getDocumentList().add(getScDocumentsAdd());
                                
                                addInfo(null, DMESConstants.MESSAGE_TITTLE_SUCCES, DMESConstants.MESSAGE_SUCCES);
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
         cleanFieldSave();
    }
    
    
    /**
     * Método encargado de guardar el registro de un archivo subido al sistema.
     * @param path ruta del archivo subido
     * @param fileName nombre del archivo subido
     * @author: Gustavo Adolfo Chavarro Ortiz
     */
    public void addLogDocument(String path, String fileName, String contentType) throws Exception
    {
        getScDocumentsAdd().setDocumentPath(path);
        getScDocumentsAdd().setCreationDate(new Date());
        getScDocumentsAdd().setDocumentName(fileName);
        getScDocumentsAdd().setTypeDocument(contentType);
        getScDocumentsAdd().setIdPerson(getSessionBean().getScUser().getIdPerson());
        getScDocumentsAdd().setUploadBy(getSessionBean().getScUser().getLogin());
        getFsDocumentsServer().createDocument(getScDocumentsAdd());
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
     * Método encargado de permitir descargar los archivos que se encuentran en la 
     * tabla del usuario.
     * @param scDocumentsSelected registro del documento a descargar
     * @author Gustavo Chavarro Ortiz
     */
    public void downloadDocument(ScDocuments scDocumentsSelected)
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
    
    public void deleteDocument(ScDocuments scDocumentsSelected)
    {
        if(scDocumentsSelected != null)
        {
            try
            {
                String fileName = scDocumentsSelected.getDocumentName();
                String path     = scDocumentsSelected.getDocumentPath();
                File file = new File(path+"/"+fileName);
                getFsDocumentsServer().deleteteDocumentById(scDocumentsSelected);
                if(file.delete())
                {
                    getDocumentList().remove(scDocumentsSelected);
                    addInfo(null, DMESConstants.MESSAGE_TITTLE_SUCCES, DMESConstants.MESSAGE_SUCCES);
                }else
                {
                    addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
                    log.error("Error al intentar eliminar el documento");
                }
            }
            catch (Exception e)
            {
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
                log.error("Error al intentar eliminar el documento",e);
            }
        }
        else
        {
            //Excepción de borrado
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            log.error("No se pudo eliminar el documento porque está vacio");
        }
    }
    /**
     * Método encargado de limpiar todas las variables temporales.
     * @author Gustavo Chavarro Ortiz
     */
    public void cleanFieldSave()
    {
        setScDocumentsAdd(new ScDocuments());
        setUserSelected(new ScUsers());
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

    public SessionBean getSessionBean()
    {
        return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean)
    {
        this.sessionBean = sessionBean;
    }

    public List<ScDocuments> getDocumentList()
    {
        return documentList;
    }

    public void setDocumentList(List<ScDocuments> documentList)
    {
        this.documentList = documentList;
    }

    public ScDocuments getScDocumentsAdd()
    {
        return scDocumentsAdd;
    }

    public void setScDocumentsAdd(ScDocuments scDocumentsAdd)
    {
        this.scDocumentsAdd = scDocumentsAdd;
    }

    public ScDocuments getScDocumentsSelected()
    {
        return scDocumentsSelected;
    }

    public void setScDocumentsSelected(ScDocuments scDocumentsSelected)
    {
        this.scDocumentsSelected = scDocumentsSelected;
    }
    /**
     * Métodos Getters And Setters.
     */
    
    
    public UploadedFile getUpLoadFile()
    {
        return upLoadFile;
    }

    public void setUpLoadFile(UploadedFile upLoadFile)
    {
        this.upLoadFile = upLoadFile;
    }

    public int getMAX_SIZE_FILE()
    {
        return MAX_SIZE_FILE;
    }

    public void setMAX_SIZE_FILE(int MAX_SIZE_FILE)
    {
        this.MAX_SIZE_FILE = MAX_SIZE_FILE;
    }

    public String getEXTENSION_FILE()
    {
        return EXTENSION_FILE;
    }

    public void setEXTENSION_FILE(String EXTENSION_FILE)
    {
        this.EXTENSION_FILE = EXTENSION_FILE;
    }

    public IFsDocuments getFsDocumentsServer()
    {
        return fsDocumentsServer;
    }

    public void setFsDocumentsServer(IFsDocuments fsDocumentsServer)
    {
        this.fsDocumentsServer = fsDocumentsServer;
    }

    public List<ScUsers> getUsersList()
    {
        return usersList;
    }

    public void setUsersList(List<ScUsers> usersList)
    {
        this.usersList = usersList;
    }

    public ScUsers getUserSelected()
    {
        return userSelected;
    }

    public void setUserSelected(ScUsers userSelected)
    {
        this.userSelected = userSelected;
    }
    
    
}
