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
import javax.persistence.FetchType;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gchavarro88
 */
@Entity
@Table(name = "ot_maintenance_schedule", schema = "dmes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "OtMaintenanceSchedule.findAll", query = "SELECT o FROM OtMaintenanceSchedule o"),
    @NamedQuery(name = "OtMaintenanceSchedule.findByIdScheduleMaintenance", query = "SELECT o FROM OtMaintenanceSchedule o WHERE o.idScheduleMaintenance = :idScheduleMaintenance"),
    @NamedQuery(name = "OtMaintenanceSchedule.findByCreationDate", query = "SELECT o FROM OtMaintenanceSchedule o WHERE o.creationDate = :creationDate"),
    @NamedQuery(name = "OtMaintenanceSchedule.findByManyCriterias", query = "SELECT o FROM OtMaintenanceSchedule o WHERE o.creationDate = :creationDate AND o.endDate = :endDate AND o.idMaintenance = :idMaintenance"),
    @NamedQuery(name = "OtMaintenanceSchedule.findByIdMaintenance", query = "SELECT o FROM OtMaintenanceSchedule o WHERE o.idMaintenance = :idMaintenance")
})
public class OtMaintenanceSchedule implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqmaintenanceschedule")
    @SequenceGenerator(name = "dmes.sqmaintenanceschedule", sequenceName = "dmes.sqmaintenanceschedule", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_schedule_maintenance")
    private Long idScheduleMaintenance;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_maintenance") 
    private long idMaintenance;
    @JoinColumn(name = "id_employee", referencedColumnName = "id_employee")
    @ManyToOne(optional = false)
    private ScEmployee idEmployee;

    public OtMaintenanceSchedule()
    {
    }

    public OtMaintenanceSchedule(Long idScheduleMaintenance)
    {
        this.idScheduleMaintenance = idScheduleMaintenance;
    }

    public OtMaintenanceSchedule(Long idScheduleMaintenance, Date creationDate, long idMaintenance)
    {
        this.idScheduleMaintenance = idScheduleMaintenance;
        this.creationDate = creationDate;
        this.idMaintenance = idMaintenance;
    }

    public Long getIdScheduleMaintenance()
    {
        return idScheduleMaintenance;
    }

    public void setIdScheduleMaintenance(Long idScheduleMaintenance)
    {
        this.idScheduleMaintenance = idScheduleMaintenance;
    }

    public Date getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }

    public long getIdMaintenance()
    {
        return idMaintenance;
    }

    public void setIdMaintenance(long idMaintenance)
    {
        this.idMaintenance = idMaintenance;
    }

    public ScEmployee getIdEmployee()
    {
        return idEmployee;
    }

    public void setIdEmployee(ScEmployee idEmployee)
    {
        this.idEmployee = idEmployee;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    
    
    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idScheduleMaintenance != null ? idScheduleMaintenance.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OtMaintenanceSchedule))
        {
            return false;
        }
        OtMaintenanceSchedule other = (OtMaintenanceSchedule) object;
        if ((this.idScheduleMaintenance == null && other.idScheduleMaintenance != null) || (this.idScheduleMaintenance != null && !this.idScheduleMaintenance.equals(other.idScheduleMaintenance)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.OtMaintenanceSchedule[ idScheduleMaintenance=" + idScheduleMaintenance + " ]";
    }
    
}
