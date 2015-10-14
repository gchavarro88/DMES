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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "sc_workforce", schema = "dmes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ScWorkforce.findAll", query = "SELECT s FROM ScWorkforce s"),
    @NamedQuery(name = "ScWorkforce.findByIdWorkforce", query = "SELECT s FROM ScWorkforce s WHERE s.idWorkforce = :idWorkforce"),
    @NamedQuery(name = "ScWorkforce.findByWorkforce", query = "SELECT s FROM ScWorkforce s WHERE s.workforce = :workforce")
})
public class ScWorkforce implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscworkforce")
    @SequenceGenerator(name = "dmes.sqscworkforce", sequenceName = "dmes.sqscworkforce", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_workforce")
    private Long idWorkforce;
    @Size(min = 1, max = 200)
    @Column(name = "workforce")
    private String workforce;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "type_workforce")
    private String typeWorkforce;
    @JoinColumn(name = "id_employee", referencedColumnName = "id_employee")
    @ManyToOne(fetch = FetchType.EAGER)
    private ScEmployee idEmployee;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idWorkforce", fetch = FetchType.EAGER)
    private List<OtMaintenance> otMaintenanceList;

    public ScWorkforce()
    {
    }

    public ScWorkforce(Long idWorkforce)
    {
        this.idWorkforce = idWorkforce;
    }

    public ScWorkforce(Long idWorkforce, String workforce)
    {
        this.idWorkforce = idWorkforce;
        this.workforce = workforce;
    }

    public Long getIdWorkforce()
    {
        return idWorkforce;
    }

    public void setIdWorkforce(Long idWorkforce)
    {
        this.idWorkforce = idWorkforce;
    }

    public String getWorkforce()
    {
        return workforce;
    }

    public void setWorkforce(String workforce)
    {
        this.workforce = workforce;
    }

    public ScEmployee getIdEmployee()
    {
        return idEmployee;
    }

    public void setIdEmployee(ScEmployee idEmployee)
    {
        this.idEmployee = idEmployee;
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

    public String getTypeWorkforce()
    {
        return typeWorkforce;
    }

    public void setTypeWorkforce(String typeWorkforce)
    {
        this.typeWorkforce = typeWorkforce;
    }

    
    
    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idWorkforce != null ? idWorkforce.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScWorkforce))
        {
            return false;
        }
        ScWorkforce other = (ScWorkforce) object;
        if ((this.idWorkforce == null && other.idWorkforce != null) || (this.idWorkforce != null && !this.idWorkforce.equals(other.idWorkforce)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScWorkforce[ idWorkforce=" + idWorkforce + " ]";
    }
    
}
