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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author gchavarro88
 */
@Entity
@Table(name = "sc_submodule_permission_by_role", schema = "dmes")
@NamedQueries(
{
    @NamedQuery(name = "ScSubmodulePermissionByRole.findAll", query = "SELECT s FROM ScSubmodulePermissionByRole s"),
    @NamedQuery(name = "ScSubmodulePermissionByRole.findByIdSubmoduleByRole", query = "SELECT s FROM ScSubmodulePermissionByRole s WHERE s.idSubmoduleByRole = :idSubmoduleByRole"),
    @NamedQuery(name = "ScSubmodulePermissionByRole.findByRoleAndScModulePermission", query = "SELECT s FROM ScSubmodulePermissionByRole s WHERE s.scRoles = :scRoles AND s.scSubmodulePermission.idModulePermission = :idModulePermission" )
})
public class ScSubmodulePermissionByRole implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_submodule_by_role")
    private Long idSubmoduleByRole;

    @Column(name = "id_submodule_permission")
    private ScSubmodulePermission scSubmodulePermission;
    
    @Column(name = "id_role")
    private ScRoles scRoles;
    
    
    public ScSubmodulePermissionByRole()
    {
    }

    public ScSubmodulePermissionByRole(Long idSubmoduleByRole)
    {
        this.idSubmoduleByRole = idSubmoduleByRole;
    }

    public Long getIdSubmoduleByRole()
    {
        return idSubmoduleByRole;
    }

    public void setIdSubmoduleByRole(Long idSubmoduleByRole)
    {
        this.idSubmoduleByRole = idSubmoduleByRole;
    }

    public ScSubmodulePermission getScSubmodulePermission()
    {
        return scSubmodulePermission;
    }

    public void setScSubmodulePermission(ScSubmodulePermission scSubmodulePermission)
    {
        this.scSubmodulePermission = scSubmodulePermission;
    }

    public ScRoles getScRoles()
    {
        return scRoles;
    }

    public void setScRoles(ScRoles scRoles)
    {
        this.scRoles = scRoles;
    }
    
    

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idSubmoduleByRole != null ? idSubmoduleByRole.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScSubmodulePermissionByRole))
        {
            return false;
        }
        ScSubmodulePermissionByRole other = (ScSubmodulePermissionByRole) object;
        if ((this.idSubmoduleByRole == null && other.idSubmoduleByRole != null) || (this.idSubmoduleByRole != null && !this.idSubmoduleByRole.equals(other.idSubmoduleByRole)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScSubmodulePermissionByRole[ idSubmoduleByRole=" + idSubmoduleByRole + " ]";
    }
    
}
