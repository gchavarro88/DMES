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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author guschaor
 */
@Entity
@Table(name = "sc_location", schema = "dmes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScLocation.findAll", query = "SELECT s FROM ScLocation s ORDER BY s.location"),
    @NamedQuery(name = "ScLocation.findByIdInputLocation", query = "SELECT s FROM ScLocation s WHERE s.idLocation = :idLocation"),
    @NamedQuery(name = "ScLocation.findByLocation", query = "SELECT s FROM ScLocation s WHERE s.location = :location"),
    @NamedQuery(name = "ScLocation.findByStore", query = "SELECT s FROM ScLocation s WHERE s.store = :store ORDER BY s.store"),
    @NamedQuery(name = "ScLocation.findByDescription", query = "SELECT s FROM ScLocation s WHERE s.description = :description")})
public class ScLocation implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqsclocation")
    @SequenceGenerator(name = "dmes.sqsclocation", sequenceName = "dmes.sqsclocation", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_location")
    private Long idLocation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "location")
    private String location;
    @Size(max = 2000)
    @Column(name = "description")
    private String description;
    
    @JoinColumn(name = "id_store", referencedColumnName = "id_store")
    @ManyToOne(optional = false)
    private ScStore store;

  

    
    public ScLocation() {
    }

    public ScLocation(Long idLocation) {
        this.idLocation = idLocation;
    }

    public ScLocation(Long idLocation, String location) {
        this.idLocation = idLocation;
        this.location = location;
    }

    public Long getIdLocation()
    {
        return idLocation;
    }

    public void setIdLocation(Long idLocation)
    {
        this.idLocation = idLocation;
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
        hash += (idLocation != null ? idLocation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScLocation)) {
            return false;
        }
        ScLocation other = (ScLocation) object;
        if ((this.idLocation == null && other.idLocation != null) || (this.idLocation != null && !this.idLocation.equals(other.idLocation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return idLocation.toString()+","+location;
    }

    public ScStore getStore()
    {
        return store;
    }

    public void setStore(ScStore store)
    {
        this.store = store;
    }


}
