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
@Table(name = "SC_SUBMODULE_PERMISSION_BY_ROLE")
@NamedQueries(
{
    @NamedQuery(name = "ScSubmodulePermissionByRole.findAll", query = "SELECT s FROM ScSubmodulePermissionByRole s"),
    @NamedQuery(name = "ScSubmodulePermissionByRole.findByIdSubmodulePermissionByRole", query = "SELECT s FROM ScSubmodulePermissionByRole s WHERE s.idSubmodulePermissionByRole = :idSubmodulePermissionByRole"),
    @NamedQuery(name = "ScSubmodulePermissionByRole.findByTypePermission", query = "SELECT s FROM ScSubmodulePermissionByRole s WHERE s.typePermission = :typePermission")
})
public class ScSubmodulePermissionByRole implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_SUBMODULE_PERMISSION_BY_ROLE")
    private Long idSubmodulePermissionByRole;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "TYPE_PERMISSION")
    private String typePermission;
    @JoinColumn(name = "ID_SUBMODULE_PERMISSION", referencedColumnName = "ID_SUBMODULE_PERMISSION")
    @ManyToOne(optional = false)
    private ScSubmodulePermission idSubmodulePermission;
    @JoinColumn(name = "ID_ROLE", referencedColumnName = "ID_ROL")
    @ManyToOne(optional = false)
    private ScRoles idRole;

    public ScSubmodulePermissionByRole()
    {
    }

    public ScSubmodulePermissionByRole(Long idSubmodulePermissionByRole)
    {
        this.idSubmodulePermissionByRole = idSubmodulePermissionByRole;
    }

    public ScSubmodulePermissionByRole(Long idSubmodulePermissionByRole, String typePermission)
    {
        this.idSubmodulePermissionByRole = idSubmodulePermissionByRole;
        this.typePermission = typePermission;
    }

    public Long getIdSubmodulePermissionByRole()
    {
        return idSubmodulePermissionByRole;
    }

    public void setIdSubmodulePermissionByRole(Long idSubmodulePermissionByRole)
    {
        this.idSubmodulePermissionByRole = idSubmodulePermissionByRole;
    }

    public String getTypePermission()
    {
        return typePermission;
    }

    public void setTypePermission(String typePermission)
    {
        this.typePermission = typePermission;
    }

    public ScSubmodulePermission getIdSubmodulePermission()
    {
        return idSubmodulePermission;
    }

    public void setIdSubmodulePermission(ScSubmodulePermission idSubmodulePermission)
    {
        this.idSubmodulePermission = idSubmodulePermission;
    }

    public ScRoles getIdRole()
    {
        return idRole;
    }

    public void setIdRole(ScRoles idRole)
    {
        this.idRole = idRole;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idSubmodulePermissionByRole != null ? idSubmodulePermissionByRole.hashCode() : 0);
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
        if ((this.idSubmodulePermissionByRole == null && other.idSubmodulePermissionByRole != null) || (this.idSubmodulePermissionByRole != null && !this.idSubmodulePermissionByRole.equals(other.idSubmodulePermissionByRole)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScSubmodulePermissionByRole[ idSubmodulePermissionByRole=" + idSubmodulePermissionByRole + " ]";
    }
    
}
