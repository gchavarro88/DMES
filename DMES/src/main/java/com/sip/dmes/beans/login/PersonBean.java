/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.beans.login;

import com.sip.dmes.beans.template.TemplateBean;
import com.sip.dmes.dao.bo.IScMails;
import com.sip.dmes.dao.bo.IScModulePermissionByRole;
import com.sip.dmes.dao.bo.IScPerson;
import com.sip.dmes.dao.bo.IScPhones;
import com.sip.dmes.entitys.ScPerson;
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
    private IScPerson iScPerson;
    private IScPhones iScPhones;
    private IScMails iScMails;
    
    //Objetos temporales para modificar
    
    private List<ScPerson> scPersons;
    private List<ScPerson> scPersonsEliminar;
    private ScPerson nuevoPersona;
    private ScPerson actualizarPessona;

    public void guardar() {
    }

    public void eliminarBarios() {
    }

    public void actualizar() {
    }

    public void eliminar() {
    }

    public void agregarTelefono() {
    }

    public void agregarEmail() {
    }

    public void agregarEspecificacaciones() {
    }

    @PostConstruct
    public void initData() {
        getScPersons();

        log.info("Se cargo el contructor sin problemas con :" + scPersons.size());
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

    public List<ScPerson> getScPersons() {

        scPersons = iScPerson.getScPersons();
        return scPersons;
    }

    public void setScPersons(List<ScPerson> scPersons) {
        this.scPersons = scPersons;
    }

}
