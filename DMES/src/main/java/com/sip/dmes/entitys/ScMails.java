/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sip.dmes.entitys;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author gustavo
 */
@Entity
@Table(name = "SC_MAILS")
@NamedQueries({
    @NamedQuery(name = "ScMails.findAll", query = "SELECT s FROM ScMails s"),
    @NamedQuery(name = "ScMails.findByIdMail", query = "SELECT s FROM ScMails s WHERE s.idMail = :idMail"),
    @NamedQuery(name = "ScMails.findByMail", query = "SELECT s FROM ScMails s WHERE s.mail = :mail"),
    @NamedQuery(name = "ScMails.findByDescription", query = "SELECT s FROM ScMails s WHERE s.description = :description")})
public class ScMails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_MAIL")
    private Long idMail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "MAIL")
    private String mail;
    @Size(max = 100)
    @Column(name = "DESCRIPTION")
    private String description;
    @JoinColumn(name = "ID_PERSON", referencedColumnName = "ID_PERSON")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ScPerson idPerson;

    public ScMails() {
    }

    public ScMails(Long idMail) {
        this.idMail = idMail;
    }

    public ScMails(Long idMail, String mail) {
        this.idMail = idMail;
        this.mail = mail;
    }

    public Long getIdMail() {
        return idMail;
    }

    public void setIdMail(Long idMail) {
        this.idMail = idMail;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ScPerson getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(ScPerson idPerson) {
        this.idPerson = idPerson;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMail != null ? idMail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScMails)) {
            return false;
        }
        ScMails other = (ScMails) object;
        if ((this.idMail == null && other.idMail != null) || (this.idMail != null && !this.idMail.equals(other.idMail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.dmes.entitys.ScMails[ idMail=" + idMail + " ]";
    }
    
}
