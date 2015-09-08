/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.entitys;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author guschaor
 */
@Entity
@Table(name = "sc_priority", schema = "dmes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScPriority.findAll", query = "SELECT s FROM ScPriority s ORDER BY s.idPriority"),
    @NamedQuery(name = "ScPriority.findByIdPriority", query = "SELECT s FROM ScPriority s WHERE s.idPriority = :idPriority"),
    @NamedQuery(name = "ScPriority.findByName", query = "SELECT s FROM ScPriority s WHERE s.name = :name"),
    @NamedQuery(name = "ScPriority.findByDescription", query = "SELECT s FROM ScPriority s WHERE s.description = :description")})

public class ScPriority implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_priority")
    private Long idPriority;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "name")
    private String name;
    @Size(max = 200)
    @Column(name = "description")
    private String description;

    public ScPriority() 
    {
    }

    public ScPriority(Long idPriority) {
        this.idPriority = idPriority;
    }

    public ScPriority(Long idPriority, String name) {
        this.idPriority = idPriority;
        this.name = name;
    }

    public Long getIdPriority() {
        return idPriority;
    }

    public void setIdPriority(Long idPriority) {
        this.idPriority = idPriority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        hash += (idPriority != null ? idPriority.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScPriority)) {
            return false;
        }
        ScPriority other = (ScPriority) object;
        if ((this.idPriority == null && other.idPriority != null) || (this.idPriority != null && !this.idPriority.equals(other.idPriority))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idPriority.toString();
    }

    
}
