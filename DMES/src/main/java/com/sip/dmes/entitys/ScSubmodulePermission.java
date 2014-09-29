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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author gchavarro88
 */
@Entity
@Table(name = "sc_submodule_permission")
@NamedQueries(
{
    @NamedQuery(name = "ScSubmodulePermission.findAll", query = "SELECT s FROM ScSubmodulePermission s"),
    @NamedQuery(name = "ScSubmodulePermission.findByIdSubmodulePermission", query = "SELECT s FROM ScSubmodulePermission s WHERE s.idSubmodulePermission = :idSubmodulePermission"),
    @NamedQuery(name = "ScSubmodulePermission.findByName", query = "SELECT s FROM ScSubmodulePermission s WHERE s.name = :name"),
    @NamedQuery(name = "ScSubmodulePermission.findByDescription", query = "SELECT s FROM ScSubmodulePermission s WHERE s.description = :description"),
    @NamedQuery(name = "ScSubmodulePermission.findByType", query = "SELECT s FROM ScSubmodulePermission s WHERE s.type = :type")
})
public class ScSubmodulePermission implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_submodule_permission")
    private Long idSubmodulePermission;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Size(max = 2000)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "type")
    private String type;
    @JoinColumn(name = "id_module_permission", referencedColumnName = "id_module_permission")
    @ManyToOne(optional = false)
    private ScModulePermission idModulePermission;

    public ScSubmodulePermission()
    {
    }

    public ScSubmodulePermission(Long idSubmodulePermission)
    {
        this.idSubmodulePermission = idSubmodulePermission;
    }

    public ScSubmodulePermission(Long idSubmodulePermission, String name, String type)
    {
        this.idSubmodulePermission = idSubmodulePermission;
        this.name = name;
        this.type = type;
    }

    public Long getIdSubmodulePermission()
    {
        return idSubmodulePermission;
    }

    public void setIdSubmodulePermission(Long idSubmodulePermission)
    {
        this.idSubmodulePermission = idSubmodulePermission;
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

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public ScModulePermission getIdModulePermission()
    {
        return idModulePermission;
    }

    public void setIdModulePermission(ScModulePermission idModulePermission)
    {
        this.idModulePermission = idModulePermission;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idSubmodulePermission != null ? idSubmodulePermission.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScSubmodulePermission))
        {
            return false;
        }
        ScSubmodulePermission other = (ScSubmodulePermission) object;
        if ((this.idSubmodulePermission == null && other.idSubmodulePermission != null) || (this.idSubmodulePermission != null && !this.idSubmodulePermission.equals(other.idSubmodulePermission)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScSubmodulePermission[ idSubmodulePermission=" + idSubmodulePermission + " ]";
    }
    
}
