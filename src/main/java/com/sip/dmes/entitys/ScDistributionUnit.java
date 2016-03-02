/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.entitys;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
@Table(name = "sc_distribution_unit", schema = "dmes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScDistributionUnit.findAll", query = "SELECT s FROM ScDistributionUnit s ORDER BY s.acronym"),
    @NamedQuery(name = "ScDistributionUnit.findByIdDistributionUnit", query = "SELECT s FROM ScDistributionUnit s WHERE s.idDistributionUnit = :idDistributionUnit"),
    @NamedQuery(name = "ScDistributionUnit.findByDescription", query = "SELECT s FROM ScDistributionUnit s WHERE s.description = :description"),
    @NamedQuery(name = "ScDistributionUnit.findByAcronym", query = "SELECT s FROM ScDistributionUnit s WHERE s.acronym = :acronym")})
public class ScDistributionUnit implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscdistributionunit")
    @SequenceGenerator(name = "dmes.sqscdistributionunit", sequenceName = "dmes.sqscdistributionunit", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_distribution_unit")
    private Long idDistributionUnit;
    @Size(max = 200)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Size(min = 1, max = 20)
    @Column(name = "acronym")
    private String acronym;

    public ScDistributionUnit() {
    }

    public ScDistributionUnit(Long iidDistributionUnitPacking) {
        this.idDistributionUnit = iidDistributionUnitPacking;
    }

    public ScDistributionUnit(Long idDistributionUnit, String acronym) {
        this.idDistributionUnit = idDistributionUnit;
        this.acronym = acronym;
    }

    public Long getIdPacking() {
        return idDistributionUnit;
    }

    public void setIdDistributionUnit(Long idDistributionUnit) {
        this.idDistributionUnit = idDistributionUnit;
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
    public int hashCode()
    {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.idDistributionUnit);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final ScDistributionUnit other = (ScDistributionUnit) obj;
        if (!Objects.equals(this.idDistributionUnit, other.idDistributionUnit))
        {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return idDistributionUnit.toString()+","+acronym.toString();
    }

   
}
