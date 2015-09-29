/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.entitys;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "sc_maintenance_damage", schema = "dmes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ScMaintenanceDamage.findAll", query = "SELECT s FROM ScMaintenanceDamage s"),
    @NamedQuery(name = "ScMaintenanceDamage.findByIdMaintenanceDamage", query = "SELECT s FROM ScMaintenanceDamage s WHERE s.idMaintenanceDamage = :idMaintenanceDamage"),
    @NamedQuery(name = "ScMaintenanceDamage.findByDamage", query = "SELECT s FROM ScMaintenanceDamage s WHERE s.damage = :damage")
})
public class ScMaintenanceDamage implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_maintenance_damage")
    private Long idMaintenanceDamage;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "damage")
    private String damage;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMaintenanceDamage", fetch = FetchType.EAGER)
    private List<OtMaintenance> otMaintenanceList;

    public ScMaintenanceDamage()
    {
    }

    public ScMaintenanceDamage(Long idMaintenanceDamage)
    {
        this.idMaintenanceDamage = idMaintenanceDamage;
    }

    public ScMaintenanceDamage(Long idMaintenanceDamage, String damage)
    {
        this.idMaintenanceDamage = idMaintenanceDamage;
        this.damage = damage;
    }

    public Long getIdMaintenanceDamage()
    {
        return idMaintenanceDamage;
    }

    public void setIdMaintenanceDamage(Long idMaintenanceDamage)
    {
        this.idMaintenanceDamage = idMaintenanceDamage;
    }

    public String getDamage()
    {
        return damage;
    }

    public void setDamage(String damage)
    {
        this.damage = damage;
    }

    @XmlTransient
    public List<OtMaintenance> getOtMaintenanceList()
    {
        return otMaintenanceList;
    }

    public void setOtMaintenanceList(List<OtMaintenance> otMaintenanceList)
    {
        this.otMaintenanceList = otMaintenanceList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idMaintenanceDamage != null ? idMaintenanceDamage.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScMaintenanceDamage))
        {
            return false;
        }
        ScMaintenanceDamage other = (ScMaintenanceDamage) object;
        if ((this.idMaintenanceDamage == null && other.idMaintenanceDamage != null) || (this.idMaintenanceDamage != null && !this.idMaintenanceDamage.equals(other.idMaintenanceDamage)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScMaintenanceDamage[ idMaintenanceDamage=" + idMaintenanceDamage + " ]";
    }
    
}
