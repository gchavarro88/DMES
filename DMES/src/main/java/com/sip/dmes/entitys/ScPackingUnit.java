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
@Table(name = "sc_packing_unit", schema = "dmes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScPackingUnit.findAll", query = "SELECT s FROM ScPackingUnit s ORDER BY s.acronym"),
    @NamedQuery(name = "ScPackingUnit.findByIdPacking", query = "SELECT s FROM ScPackingUnit s WHERE s.idPacking = :idPacking"),
    @NamedQuery(name = "ScPackingUnit.findByDescription", query = "SELECT s FROM ScPackingUnit s WHERE s.description = :description"),
    @NamedQuery(name = "ScPackingUnit.findByAcronym", query = "SELECT s FROM ScPackingUnit s WHERE s.acronym = :acronym")})
public class ScPackingUnit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscpackingunit")
    @SequenceGenerator(name = "dmes.sqscpackingunit", sequenceName = "dmes.sqscpackingunit", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_packing")
    private Long idPacking;
    @Size(max = 200)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "acronym")
    private String acronym;

    public ScPackingUnit() {
    }

    public ScPackingUnit(Long idPacking) {
        this.idPacking = idPacking;
    }

    public ScPackingUnit(Long idPacking, String acronym) {
        this.idPacking = idPacking;
        this.acronym = acronym;
    }

    public Long getIdPacking() {
        return idPacking;
    }

    public void setIdPacking(Long idPacking) {
        this.idPacking = idPacking;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPacking != null ? idPacking.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScPackingUnit)) {
            return false;
        }
        ScPackingUnit other = (ScPackingUnit) object;
        if ((this.idPacking == null && other.idPacking != null) || (this.idPacking != null && !this.idPacking.equals(other.idPacking))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idPacking+","+acronym;
    }
    
}
