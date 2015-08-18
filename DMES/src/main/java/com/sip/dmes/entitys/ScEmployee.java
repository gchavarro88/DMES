/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.entitys;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "sc_employee", schema = "dmes")
@NamedQueries(
{
    @NamedQuery(name = "ScEmployee.findAll", query = "SELECT s FROM ScEmployee s"),
    @NamedQuery(name = "ScEmployee.findByIdEmployee", query = "SELECT s FROM ScEmployee s WHERE s.idEmployee = :idEmployee"),
    @NamedQuery(name = "ScEmployee.findByPosition", query = "SELECT s FROM ScEmployee s WHERE s.position = :position"),
    @NamedQuery(name = "ScEmployee.findByFormation", query = "SELECT s FROM ScEmployee s WHERE s.formation = :formation"),
    @NamedQuery(name = "ScEmployee.findByAdmissionDate", query = "SELECT s FROM ScEmployee s WHERE s.admissionDate = :admissionDate"),
    @NamedQuery(name = "ScEmployee.findByRetirementDate", query = "SELECT s FROM ScEmployee s WHERE s.retirementDate = :retirementDate"),
    @NamedQuery(name = "ScEmployee.findByActive", query = "SELECT s FROM ScEmployee s WHERE s.active = :active"),
    @NamedQuery(name = "ScEmployee.findBySalary", query = "SELECT s FROM ScEmployee s WHERE s.salary = :salary"),
    @NamedQuery(name = "ScEmployee.findByHourValue", query = "SELECT s FROM ScEmployee s WHERE s.hourValue = :hourValue"),
    @NamedQuery(name = "ScEmployee.findByCreationDate", query = "SELECT s FROM ScEmployee s WHERE s.creationDate = :creationDate"),
    @NamedQuery(name = "ScEmployee.findByModifyDate", query = "SELECT s FROM ScEmployee s WHERE s.modifyDate = :modifyDate"),
    @NamedQuery(name = "ScEmployee.deleteByPerson", query = "DELETE FROM ScEmployee s WHERE s.idPerson = :idPerson"),
    @NamedQuery(name = "ScEmployee.deleteByIdEmployee", query = "DELETE FROM ScEmployee s WHERE s.idEmployee = :idEmployee")
})
public class ScEmployee implements Serializable, Cloneable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscemployee")
    @SequenceGenerator(name = "dmes.sqscemployee", sequenceName = "dmes.sqscemployee", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_employee")
    private Long idEmployee;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "position")
    private String position;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "formation")
    private String formation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "admission_date")
    @Temporal(TemporalType.DATE)
    private Date admissionDate;
    @Column(name = "retirement_date")
    @Temporal(TemporalType.DATE)
    private Date retirementDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private Character active;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "salary")
    private BigDecimal salary;
    @Column(name = "hour_value")
    private BigDecimal hourValue;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "modify_date")
    @Temporal(TemporalType.DATE)
    private Date modifyDate;
    @JoinColumn(name = "id_person", referencedColumnName = "id_person")
    @ManyToOne(optional = false)
    private ScPerson idPerson;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmployee", fetch = FetchType.EAGER)
    private List<ScWorkExperience> scWorkExperienceList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmployee", fetch = FetchType.EAGER)
    private List<ScCompetencies> scCompetenciesList;

    public ScEmployee()
    {
    }

    public ScEmployee(Long idEmployee)
    {
        this.idEmployee = idEmployee;
    }

    public ScEmployee(Long idEmployee, String position, String formation, Date admissionDate, Character active, Date creationDate)
    {
        this.idEmployee = idEmployee;
        this.position = position;
        this.formation = formation;
        this.admissionDate = admissionDate;
        this.active = active;
        this.creationDate = creationDate;
    }

    public Long getIdEmployee()
    {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee)
    {
        this.idEmployee = idEmployee;
    }

    public String getPosition()
    {
        return position;
    }

    public void setPosition(String position)
    {
        this.position = position;
    }

    public String getFormation()
    {
        return formation;
    }

    public void setFormation(String formation)
    {
        this.formation = formation;
    }

    public Date getAdmissionDate()
    {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate)
    {
        this.admissionDate = admissionDate;
    }

    public Date getRetirementDate()
    {
        return retirementDate;
    }

    public void setRetirementDate(Date retirementDate)
    {
        this.retirementDate = retirementDate;
    }

    public Character getActive()
    {
        return active;
    }

    public void setActive(Character active)
    {
        this.active = active;
    }

    public BigDecimal getSalary()
    {
        return salary;
    }

    public void setSalary(BigDecimal salary)
    {
        this.salary = salary;
    }

    public BigDecimal getHourValue()
    {
        return hourValue;
    }

    public void setHourValue(BigDecimal hourValue)
    {
        this.hourValue = hourValue;
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

    public ScPerson getIdPerson()
    {
        return idPerson;
    }

    public void setIdPerson(ScPerson idPerson)
    {
        this.idPerson = idPerson;
    }

    public List<ScWorkExperience> getScWorkExperienceList()
    {
        return scWorkExperienceList;
    }

    public void setScWorkExperienceList(List<ScWorkExperience> scWorkExperienceList)
    {
        this.scWorkExperienceList = scWorkExperienceList;
    }

    public List<ScCompetencies> getScCompetenciesList()
    {
        return scCompetenciesList;
    }

    public void setScCompetenciesList(List<ScCompetencies> scCompetenciesList)
    {
        this.scCompetenciesList = scCompetenciesList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idEmployee != null ? idEmployee.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScEmployee))
        {
            return false;
        }
        ScEmployee other = (ScEmployee) object;
        return !((this.idEmployee == null && other.idEmployee != null) || (this.idEmployee != null && !this.idEmployee.equals(other.idEmployee)));
    }

    @Override
    public String toString()
    {
        return idEmployee.toString()+","+position;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException 
    {
        return super.clone();
    }
    
}
