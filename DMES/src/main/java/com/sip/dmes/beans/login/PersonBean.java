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
import com.sip.dmes.dao.bo.IScPersonDocumentationAttached;
import com.sip.dmes.dao.bo.IScPersonObservations;
import com.sip.dmes.dao.bo.IScPersonSpecifications;
import com.sip.dmes.dao.bo.IScPhones;
import com.sip.dmes.entitys.ScMails;
import com.sip.dmes.entitys.ScPerson;
import com.sip.dmes.entitys.ScPersonDocumentationAttached;
import com.sip.dmes.entitys.ScPersonObservations;
import com.sip.dmes.entitys.ScPersonSpecifications;
import com.sip.dmes.entitys.ScPhones;
import com.sun.corba.se.impl.activation.ServerMain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.log4j.Logger;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.TreeNode;

/**
 *
 * @author user
 */
public class PersonBean {

    private final static Logger log = Logger.getLogger(PersonBean.class);

    // interfacest par los crud basicos..
    private IScPerson scPersonServer;
    private IScPhones scPhonesServer;
    private IScMails scMailsServer;
    private IScPersonDocumentationAttached scPersonDocumentationAttachedServer;
    private IScPersonObservations scPersonObservationsServer;
    private IScPersonSpecifications scPersonSpecificationsServer;
    SessionBean sessionBean;//Variable de sesion
    //Objetos temporales para modificar
    private List<ScPerson> scPersons;
    private List<ScPerson> scPersonsEliminar;
    private List<ScPhones> selectedPersonPhone;
    private List<ScMails> selectedPersonMail;

    private List<ScPersonDocumentationAttached> selectedPersonScPersonDocumentationAttached;
    private List<ScPersonObservations> selectedPersonScPersonObservations;
    private List<ScPersonSpecifications> selectedPersonScPersonSpecifications;

    private ScPerson selectedPerson;

    private ScPerson merPerson;
    private ScPhones merPhone;
    private ScMails merMail;

    private ScPersonDocumentationAttached merScPersonDocumentationAttached;

    private ScPersonObservations merScPersonObservations;

    private ScPersonSpecifications merScPersonSpecifications;

    public void savePerson() {
        log.info("Entro al metodo a guargar la persona " + merPerson.getIdPerson());
        ///Paso los datos para slo persistir los datos basicos

        try {
            if (getMerPerson() != null) {
                log.info("Entro al metodo a guargar la persona " + getMerPerson().getIdPerson());

                getMerPerson().setCreationDate(new Date());
                getMerPerson().setPathPhoto("/");

                getScPersonServer().addScPerson(getMerPerson());
                log.info("Guardo la persona");

                try {

                    for (ScPhones scPhones : getSelectedPersonPhone()) {

                        getScPhonesServer().addScPhones(scPhones);
                    }
                    log.info("Guardo los telefonos de la persona");
                } catch (Exception e) {
                    log.error("Error al guardar los telefonos", e);
                }

                try {

                    for (ScMails scMails : getSelectedPersonMail()) {

                        getScMailsServer().addScMails(scMails);
                    }
                    log.info("Guardo los mail de la persona");
                } catch (Exception e) {
                    log.error("Error al guardar los mail", e);
                }
                try {

                    for (ScPersonObservations scPersonObservations : getSelectedPersonScPersonObservations()) {

                        getScPersonObservationsServer().addScPersonObservations(scPersonObservations);
                    }
                    log.info("Guardo los observaciones de la persona");
                } catch (Exception e) {
                    log.error("Error al guardar los observaciones", e);
                }

                try {

                    for (ScPersonSpecifications scPersonSpecifications : getSelectedPersonScPersonSpecifications()) {

                        getScPersonSpecificationsServer().addScPersonSpecifications(scPersonSpecifications);
                    }
                    log.info("Guardo los especificacion de la persona");
                } catch (Exception e) {
                    log.error("Error al guardar los especificacion", e);
                }
                // si todo funciona bien se agrega ala lista para no tener que volver a traer toda la data.
                getScPersons().add(getMerPerson());
            }
        } catch (Exception e) {
            log.error("Error intentando guardar un nuevo persona:", e);
            ///addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
        }
    }

    public void deletePerson() {

        try {
//            if (getSelectedPerson() != null) {
//                log.info("Entro al metodo a borrr la persona " + getSelectedPerson().getIdPerson());
//
//                if (getSelectedPerson().getScPhonesList()!=null) {
//                    try {
//
//                        for (ScPhones scPhones : getSelectedPerson().getScPhonesList()) {
//
//                            getScPhonesServer().deleteScPhones(scPhones);
//                        }
//                        log.info("borro los telefonos de la persona");
//                    } catch (Exception e) {
//                        log.error("Error al borrar los telefonos", e);
//                    }
//                }
//
//                if (getSelectedPerson().getScMailsList()!=null) {
//                    try {
//
//                        for (ScMails scMails : getSelectedPerson().getScMailsList()) {
//
//                            getScMailsServer().deleteScMails(scMails);
//                        }
//                        log.info("Borro los mail de la persona");
//                    } catch (Exception e) {
//                        log.error("Error al Borrar los mail", e);
//                    }
//                }
//
//                if (getSelectedPerson().getScPersonObservationsList()!=null) {
//                    try {
//
//                        for (ScPersonObservations scPersonObservations : getSelectedPerson().getScPersonObservationsList()) {
//
//                            getScPersonObservationsServer().deleteScPersonObservations(scPersonObservations);
//                        }
//                        log.info("Borro los observaciones de la persona");
//                    } catch (Exception e) {
//                        log.error("Error al Borrar los observaciones", e);
//                    }
//                }
//
//                if (getSelectedPerson().getScPersonSpecificationsList()!=null) {
//
//                    try {
//
//                        for (ScPersonSpecifications scPersonSpecifications : getSelectedPerson().getScPersonSpecificationsList()) {
//
//                            getScPersonSpecificationsServer().deleteScPersonSpecifications(scPersonSpecifications);
//                        }
//                        log.info("Borro los especificacion de la persona");
//                    } catch (Exception e) {
//                        log.error("Error al borrar los especificacion", e);
//                    }
//                }
//                // si todo funciona bien se agrega ala lista para no tener que volver a traer toda la data.
//
//            }

            getScPersonServer().deleteScPerson(getSelectedPerson());
            getScPersons().remove(getSelectedPerson());
        } catch (Exception e) {

            log.error("Error al intentar borrar la persona", e);
        }
    }

    public void mer() {
    }

    public void savePhone() {
        log.info("Entramos a agregar un telefono");

        try {

            getSelectedPersonPhone().add(getMerPhone());
        } catch (Exception e) {
            log.error("Error al intertar agregar un telefono al persona", e);
        }
    }

    public void saveMail() {
        log.info("Entramos a agregar un mail");

        try {

            getSelectedPersonMail().add(getMerMail());
        } catch (Exception e) {
            log.error("Error al intertar agregar un mail al persona", e);
        }
    }

    public void saveScPersonDocumentationAttached() {
        log.info("Entramos a agregar un documentacion");

        try {

            getSelectedPersonScPersonDocumentationAttached().add(getMerScPersonDocumentationAttached());
        } catch (Exception e) {
            log.error("Error al intertar agregar un documento ala persona", e);
        }
    }

    public void saveScPersonObservations() {
        log.info("Entramos a agregar un observacion");

        try {

            getSelectedPersonScPersonObservations().add(getMerScPersonObservations());
        } catch (Exception e) {
            log.error("Error al intertar agregar un observacion ala persona", e);
        }
    }

    public void saveScPersonSpecifications() {
        log.info("Entramos a agregar un especificaciones");

        try {

            getSelectedPersonScPersonSpecifications().add(getMerScPersonSpecifications());
        } catch (Exception e) {
            log.error("Error al intertar agregar un especificaciones ala persona", e);
        }
    }

    public void onRowEditMail(RowEditEvent event) {
        log.info("onRowEditMail");
        ///FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancelMail(RowEditEvent event) {
        log.info("onRowCancelMail");
        ///FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCellEditMail(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        log.info("onCellEditMail");

    }

    public void onRowEditPhone(RowEditEvent event) {
        log.info("onRowEditPhone");
        ///FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancelPhone(RowEditEvent event) {
        log.info("onRowCancelPhone");
        ///FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCellEditPhone(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        log.info("onCellEditPhone");

    }

    public void onRowEditScPersonDocumentationAttached(RowEditEvent event) {
        log.info("onRowEditScPersonDocumentationAttached");
        ///FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancelScPersonDocumentationAttached(RowEditEvent event) {
        log.info("onRowCancelScPersonDocumentationAttached");
        ///FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCellEditScPersonDocumentationAttached(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        log.info("onCellEditScPersonDocumentationAttached");

    }

    public void onRowEditScPersonObservations(RowEditEvent event) {
        log.info("onRowEditScPersonObservations");
        ///FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancelScPersonObservations(RowEditEvent event) {
        log.info("onRowCancelScPersonObservations");
        ///FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCellEditScPersonObservations(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        log.info("onCellEditScPersonObservations");

    }

    public void onRowEditScPersonSpecifications(RowEditEvent event) {
        log.info("onRowEditScPersonSpecifications");
        ///FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancelScPersonSpecifications(RowEditEvent event) {
        log.info("onRowCancelScPersonSpecifications");
        ///FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCellEditScPersonSpecifications(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        log.info("onCellEditScPersonSpecifications");

    }

    public void resetDateContact() {
        merPhone = new ScPhones();
        merMail = new ScMails();
        getMerMail().setIdPerson(getMerPerson());
        getMerPhone().setIdPerson(getMerPerson());
    }

    public void cleanFields() {
        setMerPerson(new ScPerson());

    }

    @PostConstruct
    public void initData() {

        loadData();
        setMerPerson(new ScPerson());
        setSelectedPersonPhone(new ArrayList<ScPhones>());
        setSelectedPersonMail(new ArrayList<ScMails>());
        setSelectedPersonPhone(new ArrayList<ScPhones>());
        setSelectedPersonScPersonObservations(new ArrayList<ScPersonObservations>());
        setSelectedPersonScPersonSpecifications(new ArrayList<ScPersonSpecifications>());

    }

    /**
     * Método encargado de }guardar los telefonos de cliente objetos para el
     * mismo informativo
     *
     * @author: Cristian Chaparro
     */
    public void loadData() {

        try {
            if (getScPersons() == null) {
                setScPersons(scPersonServer.getScPersons());
            }
        } catch (Exception e) {
            log.error("Error al cargar la personas", e);
        }

    }

    /**
     * Método encargado de monitoriar el flujo del wizar y crear los nuevos
     * objetos para el mismo informativo
     *
     * @param actionEvent Evento de donde es llamado
     * @param tittle Título del mensaje
     * @param message cuerpo del mensaje
     * @author: Cristian Chaparro
     */
    public String onFlowProcess(FlowEvent event) {
        //log.info("El evento paso" + event.getNewStep());

        if (event.getOldStep().equals("tabPersona")) {

            try {

            } catch (Exception e) {
            }

        }
        if (event.getNewStep().equals("tabPhone")) {

            setMerPhone(new ScPhones());
            getMerPhone().setIdPerson(getMerPerson());

        }

        if (event.getNewStep().equals("tabMail")) {
            setMerMail(new ScMails());
            getMerMail().setIdPerson(getMerPerson());
        }

        if (event.getNewStep().equals("tabObservations")) {
            setMerScPersonObservations(new ScPersonObservations());
            getMerScPersonObservations().setIdPerson(getMerPerson());
        }

        if (event.getNewStep().equals("tabSpecifications")) {
            setMerScPersonSpecifications(new ScPersonSpecifications());
            getMerScPersonSpecifications().setIdPerson(getMerPerson());
        }
        return event.getNewStep();
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

    public IScPhones getScPhonesServer() {
        return scPhonesServer;
    }

    public void setScPhonesServer(IScPhones scPhonesServer) {
        this.scPhonesServer = scPhonesServer;
    }

    public IScMails getScMailsServer() {
        return scMailsServer;
    }

    public void setScMailsServer(IScMails scMailsServer) {
        this.scMailsServer = scMailsServer;
    }

    public IScPersonDocumentationAttached getScPersonDocumentationAttachedServer() {
        return scPersonDocumentationAttachedServer;
    }

    public void setScPersonDocumentationAttachedServer(IScPersonDocumentationAttached scPersonDocumentationAttachedServer) {
        this.scPersonDocumentationAttachedServer = scPersonDocumentationAttachedServer;
    }

    public IScPersonObservations getScPersonObservationsServer() {
        return scPersonObservationsServer;
    }

    public void setScPersonObservationsServer(IScPersonObservations scPersonObservationsServer) {
        this.scPersonObservationsServer = scPersonObservationsServer;
    }

    public IScPersonSpecifications getScPersonSpecificationsServer() {
        return scPersonSpecificationsServer;
    }

    public void setScPersonSpecificationsServer(IScPersonSpecifications scPersonSpecificationsServer) {
        this.scPersonSpecificationsServer = scPersonSpecificationsServer;
    }

    public SessionBean getSessionBean() {
        return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    public List<ScPerson> getScPersons() {
        return scPersons;
    }

    public void setScPersons(List<ScPerson> scPersons) {
        this.scPersons = scPersons;
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

    public ScPhones getMerPhone() {
        return merPhone;
    }

    public void setMerPhone(ScPhones merPhone) {
        this.merPhone = merPhone;
    }

    public ScMails getMerMail() {
        return merMail;
    }

    public void setMerMail(ScMails merMail) {
        this.merMail = merMail;
    }

    public List<ScPhones> getSelectedPersonPhone() {
        return selectedPersonPhone;
    }

    public void setSelectedPersonPhone(List<ScPhones> selectedPersonPhone) {
        this.selectedPersonPhone = selectedPersonPhone;
    }

    public List<ScMails> getSelectedPersonMail() {
        return selectedPersonMail;
    }

    public void setSelectedPersonMail(List<ScMails> selectedPersonMail) {
        this.selectedPersonMail = selectedPersonMail;
    }

    public List<ScPersonDocumentationAttached> getSelectedPersonScPersonDocumentationAttached() {
        return selectedPersonScPersonDocumentationAttached;
    }

    public void setSelectedPersonScPersonDocumentationAttached(List<ScPersonDocumentationAttached> selectedPersonScPersonDocumentationAttached) {
        this.selectedPersonScPersonDocumentationAttached = selectedPersonScPersonDocumentationAttached;
    }

    public List<ScPersonObservations> getSelectedPersonScPersonObservations() {
        return selectedPersonScPersonObservations;
    }

    public void setSelectedPersonScPersonObservations(List<ScPersonObservations> selectedPersonScPersonObservations) {
        this.selectedPersonScPersonObservations = selectedPersonScPersonObservations;
    }

    public List<ScPersonSpecifications> getSelectedPersonScPersonSpecifications() {
        return selectedPersonScPersonSpecifications;
    }

    public void setSelectedPersonScPersonSpecifications(List<ScPersonSpecifications> selectedPersonScPersonSpecifications) {
        this.selectedPersonScPersonSpecifications = selectedPersonScPersonSpecifications;
    }

    public ScPersonDocumentationAttached getMerScPersonDocumentationAttached() {
        return merScPersonDocumentationAttached;
    }

    public void setMerScPersonDocumentationAttached(ScPersonDocumentationAttached merScPersonDocumentationAttached) {
        this.merScPersonDocumentationAttached = merScPersonDocumentationAttached;
    }

    public ScPersonObservations getMerScPersonObservations() {
        return merScPersonObservations;
    }

    public void setMerScPersonObservations(ScPersonObservations merScPersonObservations) {
        this.merScPersonObservations = merScPersonObservations;
    }

    public ScPersonSpecifications getMerScPersonSpecifications() {
        return merScPersonSpecifications;
    }

    public void setMerScPersonSpecifications(ScPersonSpecifications merScPersonSpecifications) {
        this.merScPersonSpecifications = merScPersonSpecifications;
    }

}
