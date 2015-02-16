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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author guschaor
 */
@Entity
@Table(name = "sc_input_equivalence")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScInputEquivalence.findAll", query = "SELECT s FROM ScInputEquivalence s"),
    @NamedQuery(name = "ScInputEquivalence.findByIdInputEquivalence", query = "SELECT s FROM ScInputEquivalence s WHERE s.idInputEquivalence = :idInputEquivalence")})
public class ScInputEquivalence implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_input_equivalence")
    private Long idInputEquivalence;
    @JoinColumn(name = "id_input", referencedColumnName = "id_input")
    @ManyToOne(optional = false)
    private ScInput idInput;
    @JoinColumn(name = "id_input_referenced", referencedColumnName = "id_input")
    @ManyToOne(optional = false)
    private ScInput idInputReferenced;

    public ScInputEquivalence() {
    }

    public ScInputEquivalence(Long idInputEquivalence) {
        this.idInputEquivalence = idInputEquivalence;
    }

    public Long getIdInputEquivalence() {
        return idInputEquivalence;
    }

    public void setIdInputEquivalence(Long idInputEquivalence) {
        this.idInputEquivalence = idInputEquivalence;
    }

    public ScInput getIdInput() {
        return idInput;
    }

    public void setIdInput(ScInput idInput) {
        this.idInput = idInput;
    }

    public ScInput getIdInputReferenced() {
        return idInputReferenced;
    }

    public void setIdInputReferenced(ScInput idInputReferenced) {
        this.idInputReferenced = idInputReferenced;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInputEquivalence != null ? idInputEquivalence.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScInputEquivalence)) {
            return false;
        }
        ScInputEquivalence other = (ScInputEquivalence) object;
        if ((this.idInputEquivalence == null && other.idInputEquivalence != null) || (this.idInputEquivalence != null && !this.idInputEquivalence.equals(other.idInputEquivalence))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sip.dmes.entitys.ScInputEquivalence[ idInputEquivalence=" + idInputEquivalence + " ]";
    }
    
}
