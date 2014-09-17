/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.dmes.entitys;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * @author gustavo
 */
@Entity
@Table(name = "SC_MODULE_PERMISSION_BY_ROLE")
@NamedQueries({
    @NamedQuery(name = "ScModulePermissionByRole.findAll", query = "SELECT s FROM ScModulePermissionByRole s"),
    @NamedQuery(name = "ScModulePermissionByRole.findByIdModulePermissionByRole", query = "SELECT s FROM ScModulePermissionByRole s WHERE s.idModulePermissionByRole = :idModulePermissionByRole"),
    @NamedQuery(name = "ScModulePermissionByRole.findByTypePermission", query = "SELECT s FROM ScModulePermissionByRole s WHERE s.typePermission = :typePermission")})
public class ScModulePermissionByRole implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_MODULE_PERMISSION_BY_ROLE")
    private Long idModulePermissionByRole;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "TYPE_PERMISSION")
    private String typePermission;
    @JoinColumn(name = "ID_ROLE", referencedColumnName = "ID_ROLE")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ScRoles idRole;
    @JoinColumn(name = "ID_MODULE_PERMISSION", referencedColumnName = "ID_MODULE_PERMISSION")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ScModulePermission idModulePermission;

    public ScModulePermissionByRole() {
    }

    public ScModulePermissionByRole(Long idModulePermissionByRole) {
        this.idModulePermissionByRole = idModulePermissionByRole;
    }

    public ScModulePermissionByRole(Long idModulePermissionByRole, String typePermission) {
        this.idModulePermissionByRole = idModulePermissionByRole;
        this.typePermission = typePermission;
    }

    public Long getIdModulePermissionByRole() {
        return idModulePermissionByRole;
    }

    public void setIdModulePermissionByRole(Long idModulePermissionByRole) {
        this.idModulePermissionByRole = idModulePermissionByRole;
    }

    public String getTypePermission() {
        return typePermission;
    }

    public void setTypePermission(String typePermission) {
        this.typePermission = typePermission;
    }

    public ScRoles getIdRole() {
        return idRole;
    }

    public void setIdRole(ScRoles idRole) {
        this.idRole = idRole;
    }

    public ScModulePermission getIdModulePermission() {
        return idModulePermission;
    }

    public void setIdModulePermission(ScModulePermission idModulePermission) {
        this.idModulePermission = idModulePermission;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idModulePermissionByRole != null ? idModulePermissionByRole.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScModulePermissionByRole)) {
            return false;
        }
        ScModulePermissionByRole other = (ScModulePermissionByRole) object;
        if ((this.idModulePermissionByRole == null && other.idModulePermissionByRole != null) || (this.idModulePermissionByRole != null && !this.idModulePermissionByRole.equals(other.idModulePermissionByRole))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.dmes.entitys.ScModulePermissionByRole[ idModulePermissionByRole=" + idModulePermissionByRole + " ]";
    }
    
}
