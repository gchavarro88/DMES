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
@Table(name = "sc_machine_location", schema = "dmes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ScMachineLocation.findAll", query = "SELECT s FROM ScMachineLocation s"),
    @NamedQuery(name = "ScMachineLocation.findByIdLocation", query = "SELECT s FROM ScMachineLocation s WHERE s.idLocation = :idLocation"),
    @NamedQuery(name = "ScMachineLocation.findByLocation", query = "SELECT s FROM ScMachineLocation s WHERE s.location = :location"),
    @NamedQuery(name = "ScMachineLocation.findByDescription", query = "SELECT s FROM ScMachineLocation s WHERE s.description = :description"),
    @NamedQuery(name = "ScMachineLocation.findByMachine", query = "SELECT s FROM ScMachineLocation s WHERE s.idMachine = :machine")
})
public class ScMachineLocation implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_location")
    private Long idLocation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "location")
    private String location;
    @Size(max = 2000)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "id_machine", referencedColumnName = "id_machine")
    @ManyToOne(fetch = FetchType.EAGER)
    private ScMachine idMachine;

    public ScMachineLocation()
    {
    }

    public ScMachineLocation(Long idLocation)
    {
        this.idLocation = idLocation;
    }

    public ScMachineLocation(Long idLocation, String location)
    {
        this.idLocation = idLocation;
        this.location = location;
    }

    public Long getIdLocation()
    {
        return idLocation;
    }

    public void setIdLocation(Long idLocation)
    {
        this.idLocation = idLocation;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
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
        hash += (idLocation != null ? idLocation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScMachineLocation))
        {
            return false;
        }
        ScMachineLocation other = (ScMachineLocation) object;
        if ((this.idLocation == null && other.idLocation != null) || (this.idLocation != null && !this.idLocation.equals(other.idLocation)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScMachineLocation[ idLocation=" + idLocation + " ]";
    }
    
}
