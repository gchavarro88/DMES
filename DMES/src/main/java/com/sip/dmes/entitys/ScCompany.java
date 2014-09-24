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
@Table(name = "SC_COMPANY")
@NamedQueries(
{
    @NamedQuery(name = "ScCompany.findAll", query = "SELECT s FROM ScCompany s"),
    @NamedQuery(name = "ScCompany.findByIdCompany", query = "SELECT s FROM ScCompany s WHERE s.idCompany = :idCompany"),
    @NamedQuery(name = "ScCompany.findByName", query = "SELECT s FROM ScCompany s WHERE s.name = :name"),
    @NamedQuery(name = "ScCompany.findByDescription", query = "SELECT s FROM ScCompany s WHERE s.description = :description")
})
public class ScCompany implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_COMPANY")
    private Long idCompany;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NAME")
    private String name;
    @Size(max = 2000)
    @Column(name = "DESCRIPTION")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCompany")
    private List<ScWorkExperience> scWorkExperienceList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCompany")
    private List<ScPartner> scPartnerList;

    public ScCompany()
    {
    }

    public ScCompany(Long idCompany)
    {
        this.idCompany = idCompany;
    }

    public ScCompany(Long idCompany, String name)
    {
        this.idCompany = idCompany;
        this.name = name;
    }

    public Long getIdCompany()
    {
        return idCompany;
    }

    public void setIdCompany(Long idCompany)
    {
        this.idCompany = idCompany;
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

    public List<ScWorkExperience> getScWorkExperienceList()
    {
        return scWorkExperienceList;
    }

    public void setScWorkExperienceList(List<ScWorkExperience> scWorkExperienceList)
    {
        this.scWorkExperienceList = scWorkExperienceList;
    }

    public List<ScPartner> getScPartnerList()
    {
        return scPartnerList;
    }

    public void setScPartnerList(List<ScPartner> scPartnerList)
    {
        this.scPartnerList = scPartnerList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idCompany != null ? idCompany.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScCompany))
        {
            return false;
        }
        ScCompany other = (ScCompany) object;
        if ((this.idCompany == null && other.idCompany != null) || (this.idCompany != null && !this.idCompany.equals(other.idCompany)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScCompany[ idCompany=" + idCompany + " ]";
    }
    
}
