/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.entitys;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author gchavarro88
 */
@Entity
@Table(name = "sc_work_experience", schema = "dmes")
@NamedQueries(
{
    @NamedQuery(name = "ScWorkExperience.findAll", query = "SELECT s FROM ScWorkExperience s"),
    @NamedQuery(name = "ScWorkExperience.findByIdWorkExperience", query = "SELECT s FROM ScWorkExperience s WHERE s.idWorkExperience = :idWorkExperience"),
    @NamedQuery(name = "ScWorkExperience.findByInitDate", query = "SELECT s FROM ScWorkExperience s WHERE s.initDate = :initDate"),
    @NamedQuery(name = "ScWorkExperience.findByEndDate", query = "SELECT s FROM ScWorkExperience s WHERE s.endDate = :endDate"),
    @NamedQuery(name = "ScWorkExperience.deleteByIdEmployee", query = "DELETE FROM ScWorkExperience s WHERE s.idEmployee = :idEmployee")
})
public class ScWorkExperience implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscworkexperience")
    @SequenceGenerator(name = "dmes.sqscworkexperience", sequenceName = "dmes.sqscworkexperience", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_work_experience")
    private Long idWorkExperience;
    @Basic(optional = false)
    @NotNull
    @Column(name = "init_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date initDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @JoinColumn(name = "id_employee", referencedColumnName = "id_employee")
    @ManyToOne(optional = false)
    private ScEmployee idEmployee;
    @Column(name = "company_name")
    private String companyName;

    public ScWorkExperience()
    {
    }

    public ScWorkExperience(Long idWorkExperience)
    {
        this.idWorkExperience = idWorkExperience;
    }

    public ScWorkExperience(Long idWorkExperience, Date initDate, Date endDate)
    {
        this.idWorkExperience = idWorkExperience;
        this.initDate = initDate;
        this.endDate = endDate;
    }

    public Long getIdWorkExperience()
    {
        return idWorkExperience;
    }

    public void setIdWorkExperience(Long idWorkExperience)
    {
        this.idWorkExperience = idWorkExperience;
    }

    public Date getInitDate()
    {
        return initDate;
    }

    public void setInitDate(Date initDate)
    {
        this.initDate = initDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public ScEmployee getIdEmployee()
    {
        return idEmployee;
    }

    public void setIdEmployee(ScEmployee idEmployee)
    {
        this.idEmployee = idEmployee;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    
    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idWorkExperience != null ? idWorkExperience.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScWorkExperience))
        {
            return false;
        }
        ScWorkExperience other = (ScWorkExperience) object;
        if ((this.idWorkExperience == null && other.idWorkExperience != null) || (this.idWorkExperience != null && !this.idWorkExperience.equals(other.idWorkExperience)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScWorkExperience[ idWorkExperience=" + idWorkExperience + " ]";
    }
    
}
