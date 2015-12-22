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
@Table(name = "sc_machine_attached", schema = "dmes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ScMachineAttached.findAll", query = "SELECT s FROM ScMachineAttached s"),
    @NamedQuery(name = "ScMachineAttached.findByIdAttached", query = "SELECT s FROM ScMachineAttached s WHERE s.idAttached = :idAttached"),
    @NamedQuery(name = "ScMachineAttached.findByType", query = "SELECT s FROM ScMachineAttached s WHERE s.type = :type"),
    @NamedQuery(name = "ScMachineAttached.findByTittle", query = "SELECT s FROM ScMachineAttached s WHERE s.tittle = :tittle"),
    @NamedQuery(name = "ScMachineAttached.findByDescription", query = "SELECT s FROM ScMachineAttached s WHERE s.description = :description")
})
public class ScMachineAttached implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscfactoryLocation")
    @SequenceGenerator(name = "dmes.sqscfactoryLocation", sequenceName = "dmes.sqscfactoryLocation", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_attached")
    private Long idAttached;
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
    @JoinColumn(name = "id_machine", referencedColumnName = "id_machine")
    @ManyToOne(optional = false)
    private ScMachine idMachine;

    public ScMachineAttached()
    {
    }

    public ScMachineAttached(Long idAttached)
    {
        this.idAttached = idAttached;
    }

    public ScMachineAttached(Long idAttached, String type, String tittle, String description)
    {
        this.idAttached = idAttached;
        this.type = type;
        this.tittle = tittle;
        this.description = description;
    }

    public Long getIdAttached()
    {
        return idAttached;
    }

    public void setIdAttached(Long idAttached)
    {
        this.idAttached = idAttached;
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
        hash += (idAttached != null ? idAttached.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScMachineAttached))
        {
            return false;
        }
        ScMachineAttached other = (ScMachineAttached) object;
        if ((this.idAttached == null && other.idAttached != null) || (this.idAttached != null && !this.idAttached.equals(other.idAttached)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScMachineAttached[ idAttached=" + idAttached + " ]";
    }
    
}
