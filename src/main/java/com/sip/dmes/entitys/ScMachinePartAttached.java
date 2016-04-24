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
 * @author gchavarro88
 */
@Entity
@Table(name = "sc_machine_part_attached", schema = "dmes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ScMachinePartAttached.findAll", query = "SELECT s FROM ScMachinePartAttached s"),
    @NamedQuery(name = "ScMachinePartAttached.findByIdMachinePartAttached", query = "SELECT s FROM ScMachinePartAttached s WHERE s.idMachinePartAttached = :idMachinePartAttached"),
    @NamedQuery(name = "ScMachinePartAttached.findByType", query = "SELECT s FROM ScMachinePartAttached s WHERE s.type = :type"),
    @NamedQuery(name = "ScMachinePartAttached.findByTittle", query = "SELECT s FROM ScMachinePartAttached s WHERE s.tittle = :tittle"),
    @NamedQuery(name = "ScMachinePartAttached.findByDescription", query = "SELECT s FROM ScMachinePartAttached s WHERE s.description = :description")
})
public class ScMachinePartAttached implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscmachinepartattached")
    @SequenceGenerator(name = "dmes.sqscmachinepartattached", sequenceName = "dmes.sqscmachinepartattached", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_machine_part_attached")
    private Long idMachinePartAttached;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "tittle")
    private String tittle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "id_machine_part", referencedColumnName = "id_machine_part")
    @ManyToOne(optional = false)
    private ScMachinePart idMachinePart;

    public ScMachinePartAttached()
    {
    }

    public ScMachinePartAttached(Long idMachinePartAttached)
    {
        this.idMachinePartAttached = idMachinePartAttached;
    }

    public ScMachinePartAttached(Long idMachinePartAttached, String type, String tittle, String description)
    {
        this.idMachinePartAttached = idMachinePartAttached;
        this.type = type;
        this.tittle = tittle;
        this.description = description;
    }

    public Long getIdMachinePartAttached()
    {
        return idMachinePartAttached;
    }

    public void setIdMachinePartAttached(Long idMachinePartAttached)
    {
        this.idMachinePartAttached = idMachinePartAttached;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getTittle()
    {
        return tittle;
    }

    public void setTittle(String tittle)
    {
        this.tittle = tittle;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public ScMachinePart getIdMachinePart()
    {
        return idMachinePart;
    }

    public void setIdMachinePart(ScMachinePart idMachinePart)
    {
        this.idMachinePart = idMachinePart;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idMachinePartAttached != null ? idMachinePartAttached.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScMachinePartAttached))
        {
            return false;
        }
        ScMachinePartAttached other = (ScMachinePartAttached) object;
        if ((this.idMachinePartAttached == null && other.idMachinePartAttached != null) || (this.idMachinePartAttached != null && !this.idMachinePartAttached.equals(other.idMachinePartAttached)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScMachinePartAttached[ idMachinePartAttached=" + idMachinePartAttached + " ]";
    }
    
}
