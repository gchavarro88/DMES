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
@Table(name = "sc_module_permission", schema = "dmes")
@NamedQueries(
{
    @NamedQuery(name = "ScModulePermission.findAll", query = "SELECT s FROM ScModulePermission s"),
    @NamedQuery(name = "ScModulePermission.findByIdModulePermission", query = "SELECT s FROM ScModulePermission s WHERE s.idModulePermission = :idModulePermission"),
    @NamedQuery(name = "ScModulePermission.findByName", query = "SELECT s FROM ScModulePermission s WHERE s.name = :name"),
    @NamedQuery(name = "ScModulePermission.findByDescription", query = "SELECT s FROM ScModulePermission s WHERE s.description = :description"),
    @NamedQuery(name = "ScModulePermission.findByIcone", query = "SELECT s FROM ScModulePermission s WHERE s.icone = :icone"),
    @NamedQuery(name = "ScModulePermission.findByType", query = "SELECT s FROM ScModulePermission s WHERE s.type = :type")
})
public class ScModulePermission implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_module_permission")
    private Long idModulePermission;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Size(max = 2000)
    @Column(name = "description")
    private String description;
    @Size(max = 2000)
    @Column(name = "icone")
    private String icone;
    @Size(max = 50)
    @Column(name = "type")
    private String type;
    @Column(name = "id_father")
    private Long father;
    @Size(max = 50)
    @Column(name = "page")
    private String page;
    

    public ScModulePermission()
    {
    }

    public ScModulePermission(Long idModulePermission)
    {
        this.idModulePermission = idModulePermission;
    }

    public ScModulePermission(Long idModulePermission, String name)
    {
        this.idModulePermission = idModulePermission;
        this.name = name;
    }

    public Long getIdModulePermission()
    {
        return idModulePermission;
    }

    public void setIdModulePermission(Long idModulePermission)
    {
        this.idModulePermission = idModulePermission;
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

    public String getIcone()
    {
        return icone;
    }

    public void setIcone(String icone)
    {
        this.icone = icone;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public Long getFather()
    {
        return father;
    }

    public void setFather(Long father)
    {
        this.father = father;
    }

    public String getPage()
    {
        return page;
    }

    public void setPage(String page)
    {
        this.page = page;
    }



    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idModulePermission != null ? idModulePermission.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScModulePermission))
        {
            return false;
        }
        ScModulePermission other = (ScModulePermission) object;
        if ((this.idModulePermission == null && other.idModulePermission != null) || (this.idModulePermission != null && !this.idModulePermission.equals(other.idModulePermission)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScModulePermission[ idModulePermission=" + idModulePermission + " ]";
    }
    
}
