/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.entitys;

import java.io.Serializable;
import java.util.List;
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
 * @author gchavarro88
 */
@Entity
@Table(name = "sc_factory_location", schema = "dmes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ScFactoryLocation.findAll", query = "SELECT s FROM ScFactoryLocation s"),
    @NamedQuery(name = "ScFactoryLocation.findByIdFactoryLocation", query = "SELECT s FROM ScFactoryLocation s WHERE s.idFactoryLocation = :idFactoryLocation"),
    @NamedQuery(name = "ScFactoryLocation.findByLocation", query = "SELECT s FROM ScFactoryLocation s WHERE s.location = :location"),
    @NamedQuery(name = "ScFactoryLocation.findByDescription", query = "SELECT s FROM ScFactoryLocation s WHERE s.description = :description")
})
public class ScFactoryLocation implements Serializable
{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscmachineattached")
    @SequenceGenerator(name = "dmes.sqscmachineattached", sequenceName = "dmes.sqscmachineattached", allocationSize = 1)
    @Basic(optional = false) 
    @NotNull
    @Column(name = "id_factory_location")
    private Long idFactoryLocation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "location")
    private String location;
    
    @Size(max = 2000)
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "factoryLocation", fetch = FetchType.EAGER)
    private List<ScMachine> scMachineList;
    public ScFactoryLocation()
    {
    }

    public ScFactoryLocation(Long idFactoryLocation)
    {
        this.idFactoryLocation = idFactoryLocation;
    }

    public ScFactoryLocation(Long idFactoryLocation, String location)
    {
        this.idFactoryLocation = idFactoryLocation;
        this.location = location;
    }

    public Long getIdFactoryLocation()
    {
        return idFactoryLocation;
    }

    public void setIdFactoryLocation(Long idFactoryLocation)
    {
        this.idFactoryLocation = idFactoryLocation;
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

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idFactoryLocation != null ? idFactoryLocation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScFactoryLocation))
        {
            return false;
        }
        ScFactoryLocation other = (ScFactoryLocation) object;
        if ((this.idFactoryLocation == null && other.idFactoryLocation != null) || (this.idFactoryLocation != null && !this.idFactoryLocation.equals(other.idFactoryLocation)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return idFactoryLocation+","+location;
    }

    @XmlTransient
    public List<ScMachine> getScMachineList()
    {
        return scMachineList;
    }

    public void setScMachineList(List<ScMachine> scMachineList)
    {
        this.scMachineList = scMachineList;
    }
    
}
