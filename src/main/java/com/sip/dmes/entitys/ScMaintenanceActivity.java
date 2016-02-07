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
@Table(name = "sc_maintenance_activity", schema = "dmes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ScMaintenanceActivity.findAll", query = "SELECT s FROM ScMaintenanceActivity s"),
    @NamedQuery(name = "ScMaintenanceActivity.findByIdMaintenanceActivity", query = "SELECT s FROM ScMaintenanceActivity s WHERE s.idMaintenanceActivity = :idMaintenanceActivity"),
    @NamedQuery(name = "ScMaintenanceActivity.findByName", query = "SELECT s FROM ScMaintenanceActivity s WHERE s.name = :name"),
    @NamedQuery(name = "ScMaintenanceActivity.findByDescription", query = "SELECT s FROM ScMaintenanceActivity s WHERE s.description = :description")
})
public class ScMaintenanceActivity implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscmaintenanceactivity")
    @SequenceGenerator(name = "dmes.sqscmaintenanceactivity", sequenceName = "dmes.sqscmaintenanceactivity", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_maintenance_activity")
    private Long idMaintenanceActivity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "name")
    private String name;
    @Size(max = 400)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "id_maintenance", referencedColumnName = "id_maintenance")
    @ManyToOne(optional = false) 
    private OtMaintenance idMaintenance;

    public ScMaintenanceActivity()
    {
    }

    public ScMaintenanceActivity(Long idMaintenanceActivity)
    {
        this.idMaintenanceActivity = idMaintenanceActivity;
    }

    public ScMaintenanceActivity(Long idMaintenanceActivity, String name)
    {
        this.idMaintenanceActivity = idMaintenanceActivity;
        this.name = name;
    }

    public Long getIdMaintenanceActivity()
    {
        return idMaintenanceActivity;
    }

    public void setIdMaintenanceActivity(Long idMaintenanceActivity)
    {
        this.idMaintenanceActivity = idMaintenanceActivity;
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
        hash += (idMaintenanceActivity != null ? idMaintenanceActivity.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScMaintenanceActivity))
        {
            return false;
        }
        ScMaintenanceActivity other = (ScMaintenanceActivity) object;
        if ((this.idMaintenanceActivity == null && other.idMaintenanceActivity != null) || (this.idMaintenanceActivity != null && !this.idMaintenanceActivity.equals(other.idMaintenanceActivity)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScMaintenanceActivity[ idMaintenanceActivity=" + idMaintenanceActivity + " ]";
    }
    
}
