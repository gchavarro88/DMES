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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gchavarro88
 */
@Entity
@Table(name = "sc_maintenance_replacement", schema = "dmes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ScMaintenanceReplacement.findAll", query = "SELECT s FROM ScMaintenanceReplacement s"),
    @NamedQuery(name = "ScMaintenanceReplacement.findByIdMaintenanceReplacement", query = "SELECT s FROM ScMaintenanceReplacement s WHERE s.idMaintenanceReplacement = :idMaintenanceReplacement")
})
public class ScMaintenanceReplacement implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscmaintenancereplacement")
    @SequenceGenerator(name = "dmes.sqscmaintenancereplacement", sequenceName = "dmes.sqscmaintenancereplacement", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_maintenance_replacement")
    private Long idMaintenanceReplacement;
    @JoinColumn(name = "id_replacement", referencedColumnName = "id_replacement")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ScReplacement idReplacement;
    @JoinColumn(name = "id_maintenance", referencedColumnName = "id_maintenance")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private OtMaintenance idMaintenance;

    public ScMaintenanceReplacement()
    {
    }

    public ScMaintenanceReplacement(Long idMaintenanceReplacement)
    {
        this.idMaintenanceReplacement = idMaintenanceReplacement;
    }

    public Long getIdMaintenanceReplacement()
    {
        return idMaintenanceReplacement;
    }

    public void setIdMaintenanceReplacement(Long idMaintenanceReplacement)
    {
        this.idMaintenanceReplacement = idMaintenanceReplacement;
    }

    public ScReplacement getIdReplacement()
    {
        return idReplacement;
    }

    public void setIdReplacement(ScReplacement idReplacement)
    {
        this.idReplacement = idReplacement;
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
        hash += (idMaintenanceReplacement != null ? idMaintenanceReplacement.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScMaintenanceReplacement))
        {
            return false;
        }
        ScMaintenanceReplacement other = (ScMaintenanceReplacement) object;
        if ((this.idMaintenanceReplacement == null && other.idMaintenanceReplacement != null) || (this.idMaintenanceReplacement != null && !this.idMaintenanceReplacement.equals(other.idMaintenanceReplacement)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScMaintenanceReplacement[ idMaintenanceReplacement=" + idMaintenanceReplacement + " ]";
    }
    
}
