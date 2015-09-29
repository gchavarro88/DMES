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
@Table(name = "sc_maintenance_tool", schema = "dmes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ScMaintenanceTool.findAll", query = "SELECT s FROM ScMaintenanceTool s"),
    @NamedQuery(name = "ScMaintenanceTool.findByIdMaintenanceTool", query = "SELECT s FROM ScMaintenanceTool s WHERE s.idMaintenanceTool = :idMaintenanceTool")
})
public class ScMaintenanceTool implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscmaintenancetool")
    @SequenceGenerator(name = "dmes.sqscmaintenancetool", sequenceName = "dmes.sqscmaintenancetool", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_maintenance_tool")
    private Long idMaintenanceTool;
    @JoinColumn(name = "id_tool", referencedColumnName = "id_tool")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ScTool idTool;
    @JoinColumn(name = "id_maintenance", referencedColumnName = "id_maintenance")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private OtMaintenance idMaintenance;

    public ScMaintenanceTool()
    {
    }

    public ScMaintenanceTool(Long idMaintenanceTool)
    {
        this.idMaintenanceTool = idMaintenanceTool;
    }

    public Long getIdMaintenanceTool()
    {
        return idMaintenanceTool;
    }

    public void setIdMaintenanceTool(Long idMaintenanceTool)
    {
        this.idMaintenanceTool = idMaintenanceTool;
    }

    public ScTool getIdTool()
    {
        return idTool;
    }

    public void setIdTool(ScTool idTool)
    {
        this.idTool = idTool;
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
        hash += (idMaintenanceTool != null ? idMaintenanceTool.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScMaintenanceTool))
        {
            return false;
        }
        ScMaintenanceTool other = (ScMaintenanceTool) object;
        if ((this.idMaintenanceTool == null && other.idMaintenanceTool != null) || (this.idMaintenanceTool != null && !this.idMaintenanceTool.equals(other.idMaintenanceTool)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScMaintenanceTool[ idMaintenanceTool=" + idMaintenanceTool + " ]";
    }
    
}
