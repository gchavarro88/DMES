/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.entitys;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author carlos guzman
 */
@Entity
@Table(name = "sc_operating_conditions", schema = "dmes")
@NamedQueries(
{
    @NamedQuery(name = "ScOperatingConditions.findAll", query = "SELECT s FROM ScOperatingConditions s"),
    @NamedQuery(name = "ScOperatingConditions.findByIdOperatingCondition", query = "SELECT s FROM ScOperatingConditions s WHERE s.idOperatingCondition = :idOperatingCondition"),
    @NamedQuery(name = "ScOperatingConditions.findByIdMachine", query = "SELECT s FROM ScOperatingConditions s WHERE s.idMachine = :idMachine"),
    @NamedQuery(name = "ScOperatingConditions.findByCreationDate", query = "SELECT s FROM ScOperatingConditions s WHERE s.creationDate = :creationDate"),
    @NamedQuery(name = "ScOperatingConditions.findByModifyDate", query = "SELECT s FROM ScOperatingConditions s WHERE s.modifyDate = :modifyDate"),
    @NamedQuery(name = "ScOperatingConditions.deleteByIdOperatingCondition", query = "DELETE FROM ScOperatingConditions s WHERE s.idOperatingCondition = :idOperatingCondition"),
    @NamedQuery(name = "ScOperatingConditions.deleteByIdMachine", query = "DELETE FROM ScOperatingConditions s WHERE s.idMachine = :idMachine")
})
public class ScOperatingConditions implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscoperatingconditions")
    @SequenceGenerator(name = "dmes.sqscoperatingconditions", sequenceName = "dmes.sqscoperatingconditions", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_operating_condition")
    private Long idOperatingCondition;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "internal")
    private String internal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "external")
    private String external;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "observations")
    private String observations;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "modify_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;
    @JoinColumn(name = "id_machine", referencedColumnName = "id_machine")
    @ManyToOne(optional = false)
    private ScMachine idMachine;

    public ScOperatingConditions()
    {
    }

    public ScOperatingConditions(Long idOperatingCondition) {
        this.idOperatingCondition = idOperatingCondition;
    }

    public ScOperatingConditions(Long idOperatingCondition, String internal, String external, String observations, Date creationDate, Date modifyDate, ScMachine idMachine) {
        this.idOperatingCondition = idOperatingCondition;
        this.internal = internal;
        this.external = external;
        this.observations = observations;
        this.creationDate = creationDate;
        this.modifyDate = modifyDate;
        this.idMachine = idMachine;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idOperatingCondition != null ? idOperatingCondition.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScOperatingConditions))
        {
            return false;
        }
        ScOperatingConditions other = (ScOperatingConditions) object;
        if ((this.idOperatingCondition == null && other.idOperatingCondition != null) || (this.idOperatingCondition != null && !this.idOperatingCondition.equals(other.idOperatingCondition)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScOperatingConditions[ idOperatingCondition=" + idOperatingCondition + " ]";
    }

    public Long getIdOperatingCondition() {
        return idOperatingCondition;
    }

    public void setIdOperatingCondition(Long idOperatingCondition) {
        this.idOperatingCondition = idOperatingCondition;
    }

    public String getInternal() {
        return internal;
    }

    public void setInternal(String internal) {
        this.internal = internal;
    }

    public String getExternal() {
        return external;
    }

    public void setExternal(String external) {
        this.external = external;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public ScMachine getIdMachine() {
        return idMachine;
    }

    public void setIdMachine(ScMachine idMachine) {
        this.idMachine = idMachine;
    }
      
}
