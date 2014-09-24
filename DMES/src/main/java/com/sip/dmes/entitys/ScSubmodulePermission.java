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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author gchavarro88
 */
@Entity
@Table(name = "SC_SUBMODULE_PERMISSION")
@NamedQueries(
{
    @NamedQuery(name = "ScSubmodulePermission.findAll", query = "SELECT s FROM ScSubmodulePermission s"),
    @NamedQuery(name = "ScSubmodulePermission.findByIdSubmodulePermission", query = "SELECT s FROM ScSubmodulePermission s WHERE s.idSubmodulePermission = :idSubmodulePermission"),
    @NamedQuery(name = "ScSubmodulePermission.findByName", query = "SELECT s FROM ScSubmodulePermission s WHERE s.name = :name"),
    @NamedQuery(name = "ScSubmodulePermission.findByDescription", query = "SELECT s FROM ScSubmodulePermission s WHERE s.description = :description")
})
public class ScSubmodulePermission implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_SUBMODULE_PERMISSION")
    private Long idSubmodulePermission;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NAME")
    private String name;
    @Size(max = 2000)
    @Column(name = "DESCRIPTION")
    private String description;
    @JoinColumn(name = "ID_MODULE_PERMISSION", referencedColumnName = "ID_MODULE_PERMISSION")
    @ManyToOne(optional = false)
    private ScModulePermission idModulePermission;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSubmodulePermission")
    private List<ScSubmodulePermissionByRole> scSubmodulePermissionByRoleList;

    public ScSubmodulePermission()
    {
    }

    public ScSubmodulePermission(Long idSubmodulePermission)
    {
        this.idSubmodulePermission = idSubmodulePermission;
    }

    public ScSubmodulePermission(Long idSubmodulePermission, String name)
    {
        this.idSubmodulePermission = idSubmodulePermission;
        this.name = name;
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

    public ScModulePermission getIdModulePermission()
    {
        return idModulePermission;
    }

    public void setIdModulePermission(ScModulePermission idModulePermission)
    {
        this.idModulePermission = idModulePermission;
    }

    public List<ScSubmodulePermissionByRole> getScSubmodulePermissionByRoleList()
    {
        return scSubmodulePermissionByRoleList;
    }

    public void setScSubmodulePermissionByRoleList(List<ScSubmodulePermissionByRole> scSubmodulePermissionByRoleList)
    {
        this.scSubmodulePermissionByRoleList = scSubmodulePermissionByRoleList;
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
