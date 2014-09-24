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
@Table(name = "SC_PERSON_SPECIFICATIONS")
@NamedQueries(
{
    @NamedQuery(name = "ScPersonSpecifications.findAll", query = "SELECT s FROM ScPersonSpecifications s"),
    @NamedQuery(name = "ScPersonSpecifications.findByIdPersonSpecifications", query = "SELECT s FROM ScPersonSpecifications s WHERE s.idPersonSpecifications = :idPersonSpecifications"),
    @NamedQuery(name = "ScPersonSpecifications.findByTittle", query = "SELECT s FROM ScPersonSpecifications s WHERE s.tittle = :tittle"),
    @NamedQuery(name = "ScPersonSpecifications.findBySpecification", query = "SELECT s FROM ScPersonSpecifications s WHERE s.specification = :specification")
})
public class ScPersonSpecifications implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PERSON_SPECIFICATIONS")
    private Long idPersonSpecifications;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "TITTLE")
    private String tittle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "SPECIFICATION")
    private String specification;
    @JoinColumn(name = "ID_PERSON", referencedColumnName = "ID_PERSON")
    @ManyToOne(optional = false)
    private ScPerson idPerson;

    public ScPersonSpecifications()
    {
    }

    public ScPersonSpecifications(Long idPersonSpecifications)
    {
        this.idPersonSpecifications = idPersonSpecifications;
    }

    public ScPersonSpecifications(Long idPersonSpecifications, String tittle, String specification)
    {
        this.idPersonSpecifications = idPersonSpecifications;
        this.tittle = tittle;
        this.specification = specification;
    }

    public Long getIdPersonSpecifications()
    {
        return idPersonSpecifications;
    }

    public void setIdPersonSpecifications(Long idPersonSpecifications)
    {
        this.idPersonSpecifications = idPersonSpecifications;
    }

    public String getTittle()
    {
        return tittle;
    }

    public void setTittle(String tittle)
    {
        this.tittle = tittle;
    }

    public String getSpecification()
    {
        return specification;
    }

    public void setSpecification(String specification)
    {
        this.specification = specification;
    }

    public ScPerson getIdPerson()
    {
        return idPerson;
    }

    public void setIdPerson(ScPerson idPerson)
    {
        this.idPerson = idPerson;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idPersonSpecifications != null ? idPersonSpecifications.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScPersonSpecifications))
        {
            return false;
        }
        ScPersonSpecifications other = (ScPersonSpecifications) object;
        if ((this.idPersonSpecifications == null && other.idPersonSpecifications != null) || (this.idPersonSpecifications != null && !this.idPersonSpecifications.equals(other.idPersonSpecifications)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScPersonSpecifications[ idPersonSpecifications=" + idPersonSpecifications + " ]";
    }
    
}
