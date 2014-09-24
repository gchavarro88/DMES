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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "SC_PARTNER")
@NamedQueries(
{
    @NamedQuery(name = "ScPartner.findAll", query = "SELECT s FROM ScPartner s"),
    @NamedQuery(name = "ScPartner.findByIdPartner", query = "SELECT s FROM ScPartner s WHERE s.idPartner = :idPartner"),
    @NamedQuery(name = "ScPartner.findByActive", query = "SELECT s FROM ScPartner s WHERE s.active = :active"),
    @NamedQuery(name = "ScPartner.findByPosition", query = "SELECT s FROM ScPartner s WHERE s.position = :position"),
    @NamedQuery(name = "ScPartner.findByWebPage", query = "SELECT s FROM ScPartner s WHERE s.webPage = :webPage"),
    @NamedQuery(name = "ScPartner.findByCreationDate", query = "SELECT s FROM ScPartner s WHERE s.creationDate = :creationDate"),
    @NamedQuery(name = "ScPartner.findByModifyDate", query = "SELECT s FROM ScPartner s WHERE s.modifyDate = :modifyDate")
})
public class ScPartner implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PARTNER")
    private Long idPartner;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ACTIVE")
    private String active;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "POSITION")
    private String position;
    @Size(max = 100)
    @Column(name = "WEB_PAGE")
    private String webPage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "MODIFY_DATE")
    @Temporal(TemporalType.DATE)
    private Date modifyDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPartner")
    private List<ScServicesOrProducts> scServicesOrProductsList;
    @JoinColumn(name = "ID_PERSON", referencedColumnName = "ID_PERSON")
    @ManyToOne(optional = false)
    private ScPerson idPerson;
    @JoinColumn(name = "ID_COMPANY", referencedColumnName = "ID_COMPANY")
    @ManyToOne(optional = false)
    private ScCompany idCompany;

    public ScPartner()
    {
    }

    public ScPartner(Long idPartner)
    {
        this.idPartner = idPartner;
    }

    public ScPartner(Long idPartner, String active, String position, Date creationDate)
    {
        this.idPartner = idPartner;
        this.active = active;
        this.position = position;
        this.creationDate = creationDate;
    }

    public Long getIdPartner()
    {
        return idPartner;
    }

    public void setIdPartner(Long idPartner)
    {
        this.idPartner = idPartner;
    }

    public String getActive()
    {
        return active;
    }

    public void setActive(String active)
    {
        this.active = active;
    }

    public String getPosition()
    {
        return position;
    }

    public void setPosition(String position)
    {
        this.position = position;
    }

    public String getWebPage()
    {
        return webPage;
    }

    public void setWebPage(String webPage)
    {
        this.webPage = webPage;
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

    public List<ScServicesOrProducts> getScServicesOrProductsList()
    {
        return scServicesOrProductsList;
    }

    public void setScServicesOrProductsList(List<ScServicesOrProducts> scServicesOrProductsList)
    {
        this.scServicesOrProductsList = scServicesOrProductsList;
    }

    public ScPerson getIdPerson()
    {
        return idPerson;
    }

    public void setIdPerson(ScPerson idPerson)
    {
        this.idPerson = idPerson;
    }

    public ScCompany getIdCompany()
    {
        return idCompany;
    }

    public void setIdCompany(ScCompany idCompany)
    {
        this.idCompany = idCompany;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idPartner != null ? idPartner.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScPartner))
        {
            return false;
        }
        ScPartner other = (ScPartner) object;
        if ((this.idPartner == null && other.idPartner != null) || (this.idPartner != null && !this.idPartner.equals(other.idPartner)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScPartner[ idPartner=" + idPartner + " ]";
    }
    
}
