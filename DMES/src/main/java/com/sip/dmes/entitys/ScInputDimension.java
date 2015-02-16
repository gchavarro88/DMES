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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "sc_input_dimension")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScInputDimension.findAll", query = "SELECT s FROM ScInputDimension s"),
    @NamedQuery(name = "ScInputDimension.findByIdInputDimension", query = "SELECT s FROM ScInputDimension s WHERE s.idInputDimension = :idInputDimension"),
    @NamedQuery(name = "ScInputDimension.findByHight", query = "SELECT s FROM ScInputDimension s WHERE s.hight = :hight"),
    @NamedQuery(name = "ScInputDimension.findByWidth", query = "SELECT s FROM ScInputDimension s WHERE s.width = :width"),
    @NamedQuery(name = "ScInputDimension.findByLarge", query = "SELECT s FROM ScInputDimension s WHERE s.large = :large"),
    @NamedQuery(name = "ScInputDimension.findByWeight", query = "SELECT s FROM ScInputDimension s WHERE s.weight = :weight"),
    @NamedQuery(name = "ScInputDimension.findByVolume", query = "SELECT s FROM ScInputDimension s WHERE s.volume = :volume"),
    @NamedQuery(name = "ScInputDimension.findByThickness", query = "SELECT s FROM ScInputDimension s WHERE s.thickness = :thickness"),
    @NamedQuery(name = "ScInputDimension.findByRadio", query = "SELECT s FROM ScInputDimension s WHERE s.radio = :radio"),
    @NamedQuery(name = "ScInputDimension.findByObservations", query = "SELECT s FROM ScInputDimension s WHERE s.observations = :observations")})
public class ScInputDimension implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_input_dimension")
    private Long idInputDimension;
    @Size(max = 100)
    @Column(name = "hight")
    private String hight;
    @Size(max = 100)
    @Column(name = "width")
    private String width;
    @Size(max = 100)
    @Column(name = "large")
    private String large;
    @Size(max = 100)
    @Column(name = "weight")
    private String weight;
    @Size(max = 100)
    @Column(name = "volume")
    private String volume;
    @Size(max = 100)
    @Column(name = "thickness")
    private String thickness;
    @Size(max = 100)
    @Column(name = "radio")
    private String radio;
    @Size(max = 2000)
    @Column(name = "observations")
    private String observations;
    @JoinColumn(name = "id_input", referencedColumnName = "id_input")
    @ManyToOne(optional = false)
    private ScInput idInput;

    public ScInputDimension() {
    }

    public ScInputDimension(Long idInputDimension) {
        this.idInputDimension = idInputDimension;
    }

    public Long getIdInputDimension() {
        return idInputDimension;
    }

    public void setIdInputDimension(Long idInputDimension) {
        this.idInputDimension = idInputDimension;
    }

    public String getHight() {
        return hight;
    }

    public void setHight(String hight) {
        this.hight = hight;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getThickness() {
        return thickness;
    }

    public void setThickness(String thickness) {
        this.thickness = thickness;
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public ScInput getIdInput() {
        return idInput;
    }

    public void setIdInput(ScInput idInput) {
        this.idInput = idInput;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInputDimension != null ? idInputDimension.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScInputDimension)) {
            return false;
        }
        ScInputDimension other = (ScInputDimension) object;
        if ((this.idInputDimension == null && other.idInputDimension != null) || (this.idInputDimension != null && !this.idInputDimension.equals(other.idInputDimension))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sip.dmes.entitys.ScInputDimension[ idInputDimension=" + idInputDimension + " ]";
    }
    
}
