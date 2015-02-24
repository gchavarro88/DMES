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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author guschaor
 */
@Entity
@Table(name = "sc_input_location", schema = "dmes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScInputLocation.findAll", query = "SELECT s FROM ScInputLocation s"),
    @NamedQuery(name = "ScInputLocation.findByIdInputLocation", query = "SELECT s FROM ScInputLocation s WHERE s.idInputLocation = :idInputLocation"),
    @NamedQuery(name = "ScInputLocation.findByLocation", query = "SELECT s FROM ScInputLocation s WHERE s.location = :location"),
    @NamedQuery(name = "ScInputLocation.findByDescription", query = "SELECT s FROM ScInputLocation s WHERE s.description = :description")})
public class ScInputLocation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_input_location")
    private Long idInputLocation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "location")
    private String location;
    @Size(max = 2000)
    @Column(name = "description")
    private String description;

    public ScInputLocation() {
    }

    public ScInputLocation(Long idInputLocation) {
        this.idInputLocation = idInputLocation;
    }

    public ScInputLocation(Long idInputLocation, String location) {
        this.idInputLocation = idInputLocation;
        this.location = location;
    }

    public Long getIdInputLocation() {
        return idInputLocation;
    }

    public void setIdInputLocation(Long idInputLocation) {
        this.idInputLocation = idInputLocation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInputLocation != null ? idInputLocation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScInputLocation)) {
            return false;
        }
        ScInputLocation other = (ScInputLocation) object;
        if ((this.idInputLocation == null && other.idInputLocation != null) || (this.idInputLocation != null && !this.idInputLocation.equals(other.idInputLocation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sip.dmes.entitys.ScInputLocation[ idInputLocation=" + idInputLocation + " ]";
    }
    
}
