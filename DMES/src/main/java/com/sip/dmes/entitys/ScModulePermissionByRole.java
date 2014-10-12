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

/**
 *
 * @author gchavarro88
 */
@Entity
@Table(name = "sc_module_permission_by_role", schema = "dmes")
@NamedQueries(
{
    @NamedQuery(name = "ScModulePermissionByRole.findAll", query = "SELECT s FROM ScModulePermissionByRole s"),
    @NamedQuery(name = "ScModulePermissionByRole.findByIdRole", query = "SELECT s FROM ScModulePermissionByRole s WHERE s.idRole = :idRole"),
    @NamedQuery(name = "ScModulePermissionByRole.findByIdModulePermissionByRole", query = "SELECT s FROM ScModulePermissionByRole s WHERE s.idModulePermissionByRole = :idModulePermissionByRole"),
    @NamedQuery(name = "ScModulePermissionByRole.findByIdType", query = "SELECT s FROM ScModulePermissionByRole s WHERE s.idType = :idType")
})
public class ScModulePermissionByRole implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "sqscmodulespermissionbyrole")
    @SequenceGenerator(name = "sqscmodulespermissionbyrole", sequenceName = "sqscmodulespermissionbyrole", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_module_permission_by_role")
    private Long idModulePermissionByRole;
    @Size(max = 5)
    @Column(name = "id_type")
    private String idType;
    @JoinColumn(name = "id_role", referencedColumnName = "id_role")
    @ManyToOne(optional = false)
    private ScRoles idRole;
    @JoinColumn(name = "id_module_permission", referencedColumnName = "id_module_permission")
    @ManyToOne(optional = false)
    private ScModulePermission idModulePermission;
    
    

    public ScModulePermissionByRole()
    {
    }

    public ScModulePermissionByRole(Long idModulePermissionByRole)
    {
        this.idModulePermissionByRole = idModulePermissionByRole;
    }

    public Long getIdModulePermissionByRole()
    {
        return idModulePermissionByRole;
    }

    public void setIdModulePermissionByRole(Long idModulePermissionByRole)
    {
        this.idModulePermissionByRole = idModulePermissionByRole;
    }

    public String getIdType()
    {
        return idType;
    }

    public void setIdType(String idType)
    {
        this.idType = idType;
    }

    public ScRoles getIdRole()
    {
        return idRole;
    }

    public void setIdRole(ScRoles idRole)
    {
        this.idRole = idRole;
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
        hash += (idModulePermissionByRole != null ? idModulePermissionByRole.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScModulePermissionByRole))
        {
            return false;
        }
        ScModulePermissionByRole other = (ScModulePermissionByRole) object;
        if ((this.idModulePermissionByRole == null && other.idModulePermissionByRole != null) || (this.idModulePermissionByRole != null && !this.idModulePermissionByRole.equals(other.idModulePermissionByRole)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScModulePermissionByRole[ idModulePermissionByRole=" + idModulePermissionByRole + " ]";
    }
    
}
