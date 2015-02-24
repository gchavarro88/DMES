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
@Table(name = "sc_input_observations", schema = "dmes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScInputObservations.findAll", query = "SELECT s FROM ScInputObservations s"),
    @NamedQuery(name = "ScInputObservations.findByIdInputObservation", query = "SELECT s FROM ScInputObservations s WHERE s.idInputObservation = :idInputObservation"),
    @NamedQuery(name = "ScInputObservations.findByTittle", query = "SELECT s FROM ScInputObservations s WHERE s.tittle = :tittle"),
    @NamedQuery(name = "ScInputObservations.findByDescription", query = "SELECT s FROM ScInputObservations s WHERE s.description = :description")})
public class ScInputObservations implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_input_observation")
    private Long idInputObservation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "tittle")
    private String tittle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "description")
    private String description;
    
    @JoinColumn(name = "id_input", referencedColumnName = "id_input")
    @ManyToOne(optional = false)
    private ScInput idInput;

    public ScInputObservations() {
    }

    public ScInputObservations(Long idInputObservation) {
        this.idInputObservation = idInputObservation;
    }

    public ScInputObservations(Long idInputObservation, String tittle, String description) {
        this.idInputObservation = idInputObservation;
        this.tittle = tittle;
        this.description = description;
    }

    public Long getIdInputObservation() {
        return idInputObservation;
    }

    public void setIdInputObservation(Long idInputObservation) {
        this.idInputObservation = idInputObservation;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        hash += (idInputObservation != null ? idInputObservation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScInputObservations)) {
            return false;
        }
        ScInputObservations other = (ScInputObservations) object;
        if ((this.idInputObservation == null && other.idInputObservation != null) || (this.idInputObservation != null && !this.idInputObservation.equals(other.idInputObservation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sip.dmes.entitys.ScInputObservations[ idInputObservation=" + idInputObservation + " ]";
    }
    
}
