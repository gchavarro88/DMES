/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.entitys;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author gchavarro88
 */
@Entity
@Table(name = "ot_maintenance", schema = "dmes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "OtMaintenance.findAll", query = "SELECT o FROM OtMaintenance o"),
    @NamedQuery(name = "OtMaintenance.findByIdMaintenance", query = "SELECT o FROM OtMaintenance o WHERE o.idMaintenance = :idMaintenance"),
    @NamedQuery(name = "OtMaintenance.findByDescription", query = "SELECT o FROM OtMaintenance o WHERE o.description = :description"),
    @NamedQuery(name = "OtMaintenance.findByCreationDate", query = "SELECT o FROM OtMaintenance o WHERE o.creationDate = :creationDate"),
    @NamedQuery(name = "OtMaintenance.findByDuration", query = "SELECT o FROM OtMaintenance o WHERE o.duration = :duration"),
    @NamedQuery(name = "OtMaintenance.findByDescriptionDamage", query = "SELECT o FROM OtMaintenance o WHERE o.descriptionDamage = :descriptionDamage")
})
public class OtMaintenance implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqotmaintenance")
    @SequenceGenerator(name = "dmes.sqotmaintenance", sequenceName = "dmes.sqotmaintenance", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_maintenance")
    private Long idMaintenance;
    @Size(max = 400)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creation_date")
    @Temporal(javax.persistence.TemporalType.DATE) 
    private Date creationDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "duration")
    private long duration;
    @Size(max = 400)
    @Column(name = "description_damage")
    private String descriptionDamage;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMaintenance", fetch = FetchType.EAGER)
    private List<ScMaintenanceReplacement> scMaintenanceReplacementList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMaintenance", fetch = FetchType.EAGER)
    private List<ScMaintenanceTool> scMaintenanceToolList;
    @JoinColumn(name = "id_workforce", referencedColumnName = "id_workforce")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ScWorkforce idWorkforce;
    @JoinColumn(name = "id_priority", referencedColumnName = "id_priority")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ScPriority idPriority;
    @JoinColumn(name = "id_maintenance_state", referencedColumnName = "id_maintenance_state")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ScMaintenanceState idMaintenanceState;
    @JoinColumn(name = "id_maintenance_damage", referencedColumnName = "id_maintenance_damage")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ScMaintenanceDamage idMaintenanceDamage;
    @JoinColumn(name = "id_maintenance_clasification", referencedColumnName = "id_maintenance_clasification")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ScMaintenanceClasification idMaintenanceClasification;
    @JoinColumn(name = "id_machine_part", referencedColumnName = "id_machine_part")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ScMachinePart idMachinePart;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMaintenance", fetch = FetchType.EAGER)
    private List<ScMaintenanceActivity> scMaintenanceActivityList;

    public OtMaintenance()
    {
    }

    public OtMaintenance(Long idMaintenance)
    {
        this.idMaintenance = idMaintenance;
    }

    public OtMaintenance(Long idMaintenance, Date creationDate, long duration)
    {
        this.idMaintenance = idMaintenance;
        this.creationDate = creationDate;
        this.duration = duration;
    }

    public Long getIdMaintenance()
    {
        return idMaintenance;
    }

    public void setIdMaintenance(Long idMaintenance)
    {
        this.idMaintenance = idMaintenance;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Date getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }

    public long getDuration()
    {
        return duration;
    }

    public void setDuration(long duration)
    {
        this.duration = duration;
    }

    public String getDescriptionDamage()
    {
        return descriptionDamage;
    }

    public void setDescriptionDamage(String descriptionDamage)
    {
        this.descriptionDamage = descriptionDamage;
    }

    

    @XmlTransient
    public List<ScMaintenanceReplacement> getScMaintenanceReplacementList()
    {
        return scMaintenanceReplacementList;
    }

    public void setScMaintenanceReplacementList(List<ScMaintenanceReplacement> scMaintenanceReplacementList)
    {
        this.scMaintenanceReplacementList = scMaintenanceReplacementList;
    }

    @XmlTransient
    public List<ScMaintenanceTool> getScMaintenanceToolList()
    {
        return scMaintenanceToolList;
    }

    public void setScMaintenanceToolList(List<ScMaintenanceTool> scMaintenanceToolList)
    {
        this.scMaintenanceToolList = scMaintenanceToolList;
    }

    public ScWorkforce getIdWorkforce()
    {
        return idWorkforce;
    }

    public void setIdWorkforce(ScWorkforce idWorkforce)
    {
        this.idWorkforce = idWorkforce;
    }

    public ScPriority getIdPriority()
    {
        return idPriority;
    }

    public void setIdPriority(ScPriority idPriority)
    {
        this.idPriority = idPriority;
    }

    public ScMaintenanceState getIdMaintenanceState()
    {
        return idMaintenanceState;
    }

    public void setIdMaintenanceState(ScMaintenanceState idMaintenanceState)
    {
        this.idMaintenanceState = idMaintenanceState;
    }

    public ScMaintenanceDamage getIdMaintenanceDamage()
    {
        return idMaintenanceDamage;
    }

    public void setIdMaintenanceDamage(ScMaintenanceDamage idMaintenanceDamage)
    {
        this.idMaintenanceDamage = idMaintenanceDamage;
    }

    public ScMaintenanceClasification getIdMaintenanceClasification()
    {
        return idMaintenanceClasification;
    }

    public void setIdMaintenanceClasification(ScMaintenanceClasification idMaintenanceClasification)
    {
        this.idMaintenanceClasification = idMaintenanceClasification;
    }

    public ScMachinePart getIdMachinePart()
    {
        return idMachinePart;
    }

    public void setIdMachinePart(ScMachinePart idMachinePart)
    {
        this.idMachinePart = idMachinePart;
    }

    @XmlTransient
    public List<ScMaintenanceActivity> getScMaintenanceActivityList()
    {
        return scMaintenanceActivityList;
    }

    public void setScMaintenanceActivityList(List<ScMaintenanceActivity> scMaintenanceActivityList)
    {
        this.scMaintenanceActivityList = scMaintenanceActivityList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idMaintenance != null ? idMaintenance.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OtMaintenance))
        {
            return false;
        }
        OtMaintenance other = (OtMaintenance) object;
        if ((this.idMaintenance == null && other.idMaintenance != null) || (this.idMaintenance != null && !this.idMaintenance.equals(other.idMaintenance)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.OtMaintenance[ idMaintenance=" + idMaintenance + " ]";
    }
    
}
