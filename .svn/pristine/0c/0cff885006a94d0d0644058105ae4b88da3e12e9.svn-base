/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.beans.login;

import com.sip.dmes.utilities.Utilities;
import com.sip.dmes.beans.SessionBean;
import com.sip.dmes.dao.bo.IScUsers;

import com.sip.dmes.entitys.ScUsers;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.log4j.Logger;

/**
 *
 * @author gchavarro88
 */
public class LoginBean implements Serializable
{

    /**
     * Creates a new instance of LoginBean
     */
    IScUsers scUsersServer;//Objeto de acceso a los datos de la tabla SCUSERS
    ScUsers scUser;// Objeto de tipo ScUsers donde se guardan los datos del usuario
    String login; // Variable donde se guarda el login ingresado por el cliente
    String password; //Variable donde se guarda el password ingresado por el cliente
    SessionBean sessionBean;//Variable de sesion
    private final static Logger log = Logger.getLogger(LoginBean.class);
    
    /**
     * Método constructor.
     */
    public LoginBean()
    { 
        
    } 
    
    /**
     * Método postConstructor el cual inicia cuando la aplicación ya ha cargado.
     */
    @PostConstruct
    public void initData()
    { 
        //Validamos que la sesión ya haya sido iniciada y redireccionamos
        log.info("Verificamos la sesión del usuario se encuentre activa");
        if(getSessionBean().getScUser() != null)
        {
            try 
            {
                log.info("Redireccionamos a la pantalla del menú principal");
                //Redirección al mainFrame
                FacesContext.getCurrentInstance().getExternalContext().dispatch("/SC_main_menu/mainMenu.jsf");
            }
            catch(Exception e)
            {
                log.error("Error la intentar verificar los datos de sesión del usuario", e);
            }
            
        }
    
    }
    /**
     * Metodo en cargado de realizar el inicio de sesion del usuario.
     * @return String variable que indica si el usuario realizó el login satisfactoriamente
     *@Author: Gustavo Chavarro Ortiz 
     */
    public String doLogin()
    {
        log.info("Inicia el proceso de login");
        String result = "";
        if(getLogin() != null && getLogin().length() > 0 && getPassword() != null
                && getPassword().length() > 0)
        {
            try
            {
                log.info("Se consulta el usuario "+getLogin());
                setScUser(scUsersServer.findByLogin(getLogin()));
                if(getScUser() != null && getScUser().getPassword().
                        equals(Utilities.encriptaEnMD5(password)))
                {
                    getSessionBean().setScUser(getScUser());
                    log.info("Login exitoso, se redireccionará a la pantalla principal");
                    result = "loginIn";
                }
                else
                {
                    setPassword("");
                    addError(null, "Error en la autenticación", "El usuario o la contraseña no son válidos");
                    log.info("Error en la autenticación, usuario o contraseñas no válidos");
                }
            }
            catch(Exception e)
            {
                setPassword("");
                addFatal(null, "Error de Sistema", "Su petición no pudo ser procesada, "
                        + "por favor comuniquese con el administrador o intente después");
                log.error("Ocurrió un error al intentar realizar la autenticación", e);
            }
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
     *Métodos Getters And Setters de la Clase.
     */
    public ScUsers getScUser()
    {
        return scUser;
    }

    public void setScUser(ScUsers scUser)
    {
        this.scUser = scUser;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public IScUsers getScUsersServer()
    {
        return scUsersServer;
    }

    public void setScUsersServer(IScUsers scUsersServer)
    {
        this.scUsersServer = scUsersServer;
    }

    public SessionBean getSessionBean()
    {
        return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean)
    {
        this.sessionBean = sessionBean;
    }
    
    
    
    
    
}
