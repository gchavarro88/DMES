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
@Table(name = "ot_maintenance_preventive", schema = "dmes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "OtMaintenancePreventive.findAll", query = "SELECT o FROM OtMaintenancePreventive o"),
    @NamedQuery(name = "OtMaintenancePreventive.findByIdMaintenancePreventive", query = "SELECT o FROM OtMaintenancePreventive o WHERE o.idMaintenancePreventive = :idMaintenancePreventive"),
    @NamedQuery(name = "OtMaintenancePreventive.findByName", query = "SELECT o FROM OtMaintenancePreventive o WHERE o.name = :name"),
    @NamedQuery(name = "OtMaintenancePreventive.findByToday", query = "SELECT o FROM OtMaintenancePreventive o WHERE o.idMaintenance.creationDate >= :creationDate"),
    @NamedQuery(name = "OtMaintenancePreventive.findByDescription", query = "SELECT o FROM OtMaintenancePreventive o WHERE o.description = :description")
})
public class OtMaintenancePreventive implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqotmaintenancepreventive")
    @SequenceGenerator(name = "dmes.sqotmaintenancepreventive", sequenceName = "dmes.sqotmaintenancepreventive", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_maintenance_preventive")
    private Long idMaintenancePreventive;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "type_frequency")
    private String typeFrecuency;
    @Column(name = "amount_schedule")
    private long amountSchedule;
    @Column(name = "duration")
    @Size(max = 100)
    private String duration;
    @Size(max = 400)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "id_maintenance", referencedColumnName = "id_maintenance")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private OtMaintenance idMaintenance;

    public OtMaintenancePreventive()
    {
    }

    public OtMaintenancePreventive(Long idMaintenancePreventive)
    {
        this.idMaintenancePreventive = idMaintenancePreventive;
    }

    public OtMaintenancePreventive(Long idMaintenancePreventive, String name)
    {
        this.idMaintenancePreventive = idMaintenancePreventive;
        this.name = name;
    }

    public Long getIdMaintenancePreventive()
    {
        return idMaintenancePreventive;
    }

    public void setIdMaintenancePreventive(Long idMaintenancePreventive)
    {
        this.idMaintenancePreventive = idMaintenancePreventive;
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

    public String getTypeFrecuency()
    {
        return typeFrecuency;
    }

    public void setTypeFrecuency(String typeFrecuency)
    {
        this.typeFrecuency = typeFrecuency;
    }

    public long getAmountSchedule()
    {
        return amountSchedule;
    }

    public void setAmountSchedule(long amountSchedule)
    {
        this.amountSchedule = amountSchedule;
    }

    public String getDuration()
    {
        return duration;
    }

    public void setDuration(String duration)
    {
        this.duration = duration;
    }
    
    

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idMaintenancePreventive != null ? idMaintenancePreventive.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OtMaintenancePreventive))
        {
            return false;
        }
        OtMaintenancePreventive other = (OtMaintenancePreventive) object;
        if ((this.idMaintenancePreventive == null && other.idMaintenancePreventive != null) || (this.idMaintenancePreventive != null && !this.idMaintenancePreventive.equals(other.idMaintenancePreventive)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.OtMaintenancePreventive[ idMaintenancePreventive=" + idMaintenancePreventive + " ]";
    }
    
}
