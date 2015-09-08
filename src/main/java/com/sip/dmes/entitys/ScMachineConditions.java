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
import javax.persistence.FetchType;
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
 * @author gchavarro88
 */
@Entity
@Table(name = "sc_machine_conditions", schema = "dmes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ScMachineConditions.findAll", query = "SELECT s FROM ScMachineConditions s"),
    @NamedQuery(name = "ScMachineConditions.findByIdCondition", query = "SELECT s FROM ScMachineConditions s WHERE s.idCondition = :idCondition"),
    @NamedQuery(name = "ScMachineConditions.findByType", query = "SELECT s FROM ScMachineConditions s WHERE s.type = :type"),
    @NamedQuery(name = "ScMachineConditions.findByDescription", query = "SELECT s FROM ScMachineConditions s WHERE s.description = :description")
})
public class ScMachineConditions implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_condition")
    private Long idCondition;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "type")
    private String type;
    @Size(max = 400)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "id_machine", referencedColumnName = "id_machine")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ScMachine idMachine;

    public ScMachineConditions()
    {
    }

    public ScMachineConditions(Long idCondition)
    {
        this.idCondition = idCondition;
    }

    public ScMachineConditions(Long idCondition, String type)
    {
        this.idCondition = idCondition;
        this.type = type;
    }

    public Long getIdCondition()
    {
        return idCondition;
    }

    public void setIdCondition(Long idCondition)
    {
        this.idCondition = idCondition;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public ScMachine getIdMachine()
    {
        return idMachine;
    }

    public void setIdMachine(ScMachine idMachine)
    {
        this.idMachine = idMachine;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idCondition != null ? idCondition.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScMachineConditions))
        {
            return false;
        }
        ScMachineConditions other = (ScMachineConditions) object;
        if ((this.idCondition == null && other.idCondition != null) || (this.idCondition != null && !this.idCondition.equals(other.idCondition)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScMachineConditions[ idCondition=" + idCondition + " ]";
    }
    
}
