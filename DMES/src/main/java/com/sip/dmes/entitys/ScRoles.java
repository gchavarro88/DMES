/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.entitys;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author gchavarro88
 */
@Entity
@Table(name = "SC_ROLES")
@NamedQueries(
{
    @NamedQuery(name = "ScRoles.findAll", query = "SELECT s FROM ScRoles s"),
    @NamedQuery(name = "ScRoles.findByIdRol", query = "SELECT s FROM ScRoles s WHERE s.idRol = :idRol"),
    @NamedQuery(name = "ScRoles.findByName", query = "SELECT s FROM ScRoles s WHERE s.name = :name"),
    @NamedQuery(name = "ScRoles.findByDescription", query = "SELECT s FROM ScRoles s WHERE s.description = :description"),
    @NamedQuery(name = "ScRoles.findByCreationDate", query = "SELECT s FROM ScRoles s WHERE s.creationDate = :creationDate"),
    @NamedQuery(name = "ScRoles.findByModifyDate", query = "SELECT s FROM ScRoles s WHERE s.modifyDate = :modifyDate")
})
public class ScRoles implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ROL")
    private Long idRol;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NAME")
    private String name;
    @Size(max = 2000)
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "MODIFY_DATE")
    @Temporal(TemporalType.DATE)
    private Date modifyDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRole")
    private List<ScSubmodulePermissionByRole> scSubmodulePermissionByRoleList;

    public ScRoles()
    {
    }

    public ScRoles(Long idRol)
    {
        this.idRol = idRol;
    }

    public ScRoles(Long idRol, String name, Date creationDate)
    {
        this.idRol = idRol;
        this.name = name;
        this.creationDate = creationDate;
    }

    public Long getIdRol()
    {
        return idRol;
    }

    public void setIdRol(Long idRol)
    {
        this.idRol = idRol;
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

    public Date getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }

    public Date getModifyDate()
    {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate)
    {
        this.modifyDate = modifyDate;
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
        hash += (idRol != null ? idRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScRoles))
        {
            return false;
        }
        ScRoles other = (ScRoles) object;
        if ((this.idRol == null && other.idRol != null) || (this.idRol != null && !this.idRol.equals(other.idRol)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScRoles[ idRol=" + idRol + " ]";
    }
    
}
