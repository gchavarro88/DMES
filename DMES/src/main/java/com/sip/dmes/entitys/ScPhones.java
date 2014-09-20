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
@Table(name = "SC_PHONES")
@NamedQueries({
    @NamedQuery(name = "ScPhones.findAll", query = "SELECT s FROM ScPhones s"),
    @NamedQuery(name = "ScPhones.findByIdPhone", query = "SELECT s FROM ScPhones s WHERE s.idPhone = :idPhone"),
    @NamedQuery(name = "ScPhones.findByNumberPhone", query = "SELECT s FROM ScPhones s WHERE s.numberPhone = :numberPhone"),
    @NamedQuery(name = "ScPhones.findByDescription", query = "SELECT s FROM ScPhones s WHERE s.description = :description")})
public class ScPhones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PHONE")
    private Long idPhone;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMBER_PHONE")
    private long numberPhone;
    @Size(max = 100)
    @Column(name = "DESCRIPTION")
    private String description;
    @JoinColumn(name = "ID_PERSON", referencedColumnName = "ID_PERSON")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ScPerson idPerson;

    public ScPhones() {
    }

    public ScPhones(Long idPhone) {
        this.idPhone = idPhone;
    }

    public ScPhones(Long idPhone, long numberPhone) {
        this.idPhone = idPhone;
        this.numberPhone = numberPhone;
    }

    public Long getIdPhone() {
        return idPhone;
    }

    public void setIdPhone(Long idPhone) {
        this.idPhone = idPhone;
    }

    public long getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(long numberPhone) {
        this.numberPhone = numberPhone;
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
        hash += (idPhone != null ? idPhone.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScPhones)) {
            return false;
        }
        ScPhones other = (ScPhones) object;
        if ((this.idPhone == null && other.idPhone != null) || (this.idPhone != null && !this.idPhone.equals(other.idPhone))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.dmes.entitys.ScPhones[ idPhone=" + idPhone + " ]";
    }
    
}
