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
@Table(name = "ot_maintenance_corrective", schema = "dmes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "OtMaintenanceCorrective.findAll", query = "SELECT o FROM OtMaintenanceCorrective o"),
    @NamedQuery(name = "OtMaintenanceCorrective.findByIdMaintenanceCorrective", query = "SELECT o FROM OtMaintenanceCorrective o WHERE o.idMaintenanceCorrective = :idMaintenanceCorrective"),
    @NamedQuery(name = "OtMaintenanceCorrective.findByName", query = "SELECT o FROM OtMaintenanceCorrective o WHERE o.name = :name"),
    @NamedQuery(name = "OtMaintenanceCorrective.findByDescription", query = "SELECT o FROM OtMaintenanceCorrective o WHERE o.description = :description")
})
public class OtMaintenanceCorrective implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqotmaintenancecorrective")
    @SequenceGenerator(name = "dmes.sqotmaintenancecorrective", sequenceName = "dmes.sqotmaintenancecorrective", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_maintenance_corrective")
    private Long idMaintenanceCorrective;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "name")
    private String name;
    @Size(max = 400)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "id_maintenance", referencedColumnName = "id_maintenance")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private OtMaintenance idMaintenance;

    public OtMaintenanceCorrective()
    {
    }

    public OtMaintenanceCorrective(Long idMaintenanceCorrective)
    {
        this.idMaintenanceCorrective = idMaintenanceCorrective;
    }

    public OtMaintenanceCorrective(Long idMaintenanceCorrective, String name)
    {
        this.idMaintenanceCorrective = idMaintenanceCorrective;
        this.name = name;
    }

    public Long getIdMaintenanceCorrective()
    {
        return idMaintenanceCorrective;
    }

    public void setIdMaintenanceCorrective(Long idMaintenanceCorrective)
    {
        this.idMaintenanceCorrective = idMaintenanceCorrective;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public OtMaintenance getIdMaintenance()
    {
        return idMaintenance;
    }

    public void setIdMaintenance(OtMaintenance idMaintenance)
    {
        this.idMaintenance = idMaintenance;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idMaintenanceCorrective != null ? idMaintenanceCorrective.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OtMaintenanceCorrective))
        {
            return false;
        }
        OtMaintenanceCorrective other = (OtMaintenanceCorrective) object;
        if ((this.idMaintenanceCorrective == null && other.idMaintenanceCorrective != null) || (this.idMaintenanceCorrective != null && !this.idMaintenanceCorrective.equals(other.idMaintenanceCorrective)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.OtMaintenanceCorrective[ idMaintenanceCorrective=" + idMaintenanceCorrective + " ]";
    }
    
}
