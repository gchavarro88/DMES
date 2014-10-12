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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
@Table(name = "sc_roles", schema = "dmes")
@NamedQueries(
{
    @NamedQuery(name = "ScRoles.findAll", query = "SELECT s FROM ScRoles s"),
    @NamedQuery(name = "ScRoles.findByIdRole", query = "SELECT s FROM ScRoles s WHERE s.idRole = :idRole"),
    @NamedQuery(name = "ScRoles.findByName", query = "SELECT s FROM ScRoles s WHERE s.name = :name"),
    @NamedQuery(name = "ScRoles.findByDescription", query = "SELECT s FROM ScRoles s WHERE s.description = :description"),
    @NamedQuery(name = "ScRoles.findByCreationDate", query = "SELECT s FROM ScRoles s WHERE s.creationDate = :creationDate"),
    @NamedQuery(name = "ScRoles.findByModifyDate", query = "SELECT s FROM ScRoles s WHERE s.modifyDate = :modifyDate")
})
public class ScRoles implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "sqscroles")
    @SequenceGenerator(name = "sqscroles", sequenceName = "sqscroles", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_role")
    private Long idRole;
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
    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "modify_date")
    @Temporal(TemporalType.DATE)
    private Date modifyDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRole")
    private List<ScUsers> scUsersList;

    public ScRoles()
    {
    }

    public ScRoles(Long idRole)
    {
        this.idRole = idRole;
    }

    public ScRoles(Long idRole, String name, Date creationDate)
    {
        this.idRole = idRole;
        this.name = name;
        this.creationDate = creationDate;
    }

    public Long getIdRole()
    {
        return idRole;
    }

    public void setIdRole(Long idRole)
    {
        this.idRole = idRole;
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

    public List<ScUsers> getScUsersList()
    {
        return scUsersList;
    }

    public void setScUsersList(List<ScUsers> scUsersList)
    {
        this.scUsersList = scUsersList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idRole != null ? idRole.hashCode() : 0);
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
        if ((this.idRole == null && other.idRole != null) || (this.idRole != null && !this.idRole.equals(other.idRole)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScRoles[ idRole=" + idRole + " ]";
    }
    
}
