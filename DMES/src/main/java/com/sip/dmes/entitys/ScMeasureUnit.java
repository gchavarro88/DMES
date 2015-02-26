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
@Table(name = "sc_measure_unit", schema = "dmes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScMeasureUnit.findAll", query = "SELECT s FROM ScMeasureUnit s"),
    @NamedQuery(name = "ScMeasureUnit.findByIdMeasure", query = "SELECT s FROM ScMeasureUnit s WHERE s.idMeasure = :idMeasure"),
    @NamedQuery(name = "ScMeasureUnit.findByAcronym", query = "SELECT s FROM ScMeasureUnit s WHERE s.acronym = :acronym"),
    @NamedQuery(name = "ScMeasureUnit.findByType", query = "SELECT s FROM ScMeasureUnit s WHERE s.type = :type")})
public class ScMeasureUnit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscmeasure")
    @SequenceGenerator(name = "dmes.sqscmeasure", sequenceName = "dmes.sqscmeasure", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_measure")
    private Long idMeasure;
    @Size(max = 20)
    @Column(name = "acronym")
    private String acronym;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "type")
    private String type;

    public ScMeasureUnit() {
    }

    public ScMeasureUnit(Long idMeasure) {
        this.idMeasure = idMeasure;
    }

    public ScMeasureUnit(Long idMeasure, String type) {
        this.idMeasure = idMeasure;
        this.type = type;
    }

    public Long getIdMeasure() {
        return idMeasure;
    }

    public void setIdMeasure(Long idMeasure) {
        this.idMeasure = idMeasure;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMeasure != null ? idMeasure.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScMeasureUnit)) {
            return false;
        }
        ScMeasureUnit other = (ScMeasureUnit) object;
        if ((this.idMeasure == null && other.idMeasure != null) || (this.idMeasure != null && !this.idMeasure.equals(other.idMeasure))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sip.dmes.entitys.ScMeasureUnit[ idMeasure=" + idMeasure + " ]";
    }
    
}
