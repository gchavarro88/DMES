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
@Table(name = "SC_COMPETENCIES")
@NamedQueries(
{
    @NamedQuery(name = "ScCompetencies.findAll", query = "SELECT s FROM ScCompetencies s"),
    @NamedQuery(name = "ScCompetencies.findByIdCompetencies", query = "SELECT s FROM ScCompetencies s WHERE s.idCompetencies = :idCompetencies"),
    @NamedQuery(name = "ScCompetencies.findByTittle", query = "SELECT s FROM ScCompetencies s WHERE s.tittle = :tittle"),
    @NamedQuery(name = "ScCompetencies.findByDescription", query = "SELECT s FROM ScCompetencies s WHERE s.description = :description")
})
public class ScCompetencies implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_COMPETENCIES")
    private Long idCompetencies;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TITTLE")
    private String tittle;
    @Size(max = 2000)
    @Column(name = "DESCRIPTION")
    private String description;
    @JoinColumn(name = "ID_EMPLOYEE", referencedColumnName = "ID_EMPLOYEE")
    @ManyToOne(optional = false)
    private ScEmployee idEmployee;

    public ScCompetencies()
    {
    }

    public ScCompetencies(Long idCompetencies)
    {
        this.idCompetencies = idCompetencies;
    }

    public ScCompetencies(Long idCompetencies, String tittle)
    {
        this.idCompetencies = idCompetencies;
        this.tittle = tittle;
    }

    public Long getIdCompetencies()
    {
        return idCompetencies;
    }

    public void setIdCompetencies(Long idCompetencies)
    {
        this.idCompetencies = idCompetencies;
    }

    public String getTittle()
    {
        return tittle;
    }

    public void setTittle(String tittle)
    {
        this.tittle = tittle;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public ScEmployee getIdEmployee()
    {
        return idEmployee;
    }

    public void setIdEmployee(ScEmployee idEmployee)
    {
        this.idEmployee = idEmployee;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idCompetencies != null ? idCompetencies.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScCompetencies))
        {
            return false;
        }
        ScCompetencies other = (ScCompetencies) object;
        if ((this.idCompetencies == null && other.idCompetencies != null) || (this.idCompetencies != null && !this.idCompetencies.equals(other.idCompetencies)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScCompetencies[ idCompetencies=" + idCompetencies + " ]";
    }
    
}
