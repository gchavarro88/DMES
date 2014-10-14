/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.beans.login;

import com.sip.dmes.beans.SessionBean;
import com.sip.dmes.beans.template.TemplateBean;
import com.sip.dmes.dao.bo.IScMails;
import com.sip.dmes.dao.bo.IScModulePermissionByRole;
import com.sip.dmes.dao.bo.IScPerson;
import com.sip.dmes.dao.bo.IScPhones;
import com.sip.dmes.entitys.ScMails;
import com.sip.dmes.entitys.ScPerson;
import com.sip.dmes.entitys.ScPhones;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.log4j.Logger;
import org.primefaces.component.tabview.TabView;
import org.primefaces.model.TreeNode;

/**
 *
 * @author user
 */
public class PersonBean implements Serializable {
    
    private final static Logger log = Logger.getLogger(PersonBean.class);

    // interfacest par los crud basicos..
    private IScPerson scPersonServer;
    private ScPhones scPhones;
    private ScMails scMails;
    SessionBean sessionBean;//Variable de sesion
    //Objetos temporales para modificar
    private List<ScPerson> scPersons;
    private List<ScPerson> scPersonsEliminar;
    private ScPerson selectedPerson;
    private ScPerson merPerson;
    
  
    
    public void app() {
        log.info("Entro al metodo a guargar la persona " + merPerson.getIdPerson());
        
        try {
            if (getMerPerson() != null) {
                log.info("Entro al metodo a guargar la persona " + getMerPerson().getIdPerson());
                ////Estos campos son temporales mientras 
                getMerPerson().setCreationDate(new Date());
                getMerPerson().setPathPhoto("/");
                getScPersonServer().addScPerson(getMerPerson());
                // si todo funciona bien se agrega ala lista para no tener que volver a traer toda la data.
                getScPersons().add(getMerPerson());
            }
        } catch (Exception e) {
            log.error("Error intentando guardar un nuevo persona:", e);
            ///addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
        }
    }
    
    public void delete() {
    }
    
    public void mer() {
    }
    
    public void appTelefono() {
    }
    
    public void appEmail() {
    }
    
    public void appEspecificacaciones() {
    }
    
    public void cleanFields() {
        setMerPerson(new ScPerson());
        
    }
    
    @PostConstruct
    public void initData() {
        
        loadData();
        setMerPerson(new ScPerson());
    }
    
    public void loadData() {
        
        try {
            setScPersons(scPersonServer.getScPersons());
        } catch (Exception e) {
            log.error("Error al cargar la personas", e);
        }
        
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
    public void addInfo(ActionEvent actionEvent, String tittle, String message) {
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
    public void addWarn(ActionEvent actionEvent, String tittle, String message) {
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
    public void addError(ActionEvent actionEvent, String tittle, String message) {
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
    public void addFatal(ActionEvent actionEvent, String tittle, String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, tittle, message));
    }
    
    public IScPerson getScPersonServer() {
        return scPersonServer;
    }
    
    public void setScPersonServer(IScPerson scPersonServer) {
        this.scPersonServer = scPersonServer;
    }
    
    public List<ScPerson> getScPersons() {
        return scPersons;
    }
    
    public void setScPersons(List<ScPerson> scPersons) {
        this.scPersons = scPersons;
    }
    
    public ScPhones getScPhones() {
        return scPhones;
    }
    
    public void setScPhones(ScPhones scPhones) {
        this.scPhones = scPhones;
    }
    
    public ScMails getScMails() {
        return scMails;
    }
    
    public void setScMails(ScMails scMails) {
        this.scMails = scMails;
    }
    
    public List<ScPerson> getScPersonsEliminar() {
        return scPersonsEliminar;
    }
    
    public void setScPersonsEliminar(List<ScPerson> scPersonsEliminar) {
        this.scPersonsEliminar = scPersonsEliminar;
    }
    
    public ScPerson getSelectedPerson() {
        return selectedPerson;
    }
    
    public void setSelectedPerson(ScPerson selectedPerson) {
        this.selectedPerson = selectedPerson;
    }

    public ScPerson getMerPerson() {
        return merPerson;
    }

    public void setMerPerson(ScPerson merPerson) {
        this.merPerson = merPerson;
    }
    
  
    public SessionBean getSessionBean() {
        return sessionBean;
    }
    
    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }
    
}
