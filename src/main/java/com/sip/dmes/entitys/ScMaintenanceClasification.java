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
@Table(name = "sc_maintenance_clasification", schema = "dmes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ScMaintenanceClasification.findAll", query = "SELECT s FROM ScMaintenanceClasification s"),
    @NamedQuery(name = "ScMaintenanceClasification.findByIdMaintenanceClasification", query = "SELECT s FROM ScMaintenanceClasification s WHERE s.idMaintenanceClasification = :idMaintenanceClasification"),
    @NamedQuery(name = "ScMaintenanceClasification.findByClasification", query = "SELECT s FROM ScMaintenanceClasification s WHERE s.clasification = :clasification")
})
public class ScMaintenanceClasification implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_maintenance_clasification")
    private Long idMaintenanceClasification;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "clasification")
    private String clasification;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMaintenanceClasification", fetch = FetchType.EAGER)
    private List<OtMaintenance> otMaintenanceList;

    public ScMaintenanceClasification()
    {
    }

    public ScMaintenanceClasification(Long idMaintenanceClasification)
    {
        this.idMaintenanceClasification = idMaintenanceClasification;
    }

    public ScMaintenanceClasification(Long idMaintenanceClasification, String clasification)
    {
        this.idMaintenanceClasification = idMaintenanceClasification;
        this.clasification = clasification;
    }

    public Long getIdMaintenanceClasification()
    {
        return idMaintenanceClasification;
    }

    public void setIdMaintenanceClasification(Long idMaintenanceClasification)
    {
        this.idMaintenanceClasification = idMaintenanceClasification;
    }

    public String getClasification()
    {
        return clasification;
    }

    public void setClasification(String clasification)
    {
        this.clasification = clasification;
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
        hash += (idMaintenanceClasification != null ? idMaintenanceClasification.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScMaintenanceClasification))
        {
            return false;
        }
        ScMaintenanceClasification other = (ScMaintenanceClasification) object;
        if ((this.idMaintenanceClasification == null && other.idMaintenanceClasification != null) || (this.idMaintenanceClasification != null && !this.idMaintenanceClasification.equals(other.idMaintenanceClasification)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return  idMaintenanceClasification+","+clasification ;
    }
    
}
