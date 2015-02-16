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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author carlos guzman
 */
@Entity
@Table(name = "sc_cost_center", schema = "dmes")
@NamedQueries(
{
    @NamedQuery(name = "ScCostCenter.findAll", query = "SELECT s FROM ScCostCenter s"),
    @NamedQuery(name = "ScCostCenter.findByIdCostCenter", query = "SELECT s FROM ScCostCenter s WHERE s.idCostCenter = :idCostCenter"),
    @NamedQuery(name = "ScCostCenter.findByCostCenter", query = "SELECT s FROM ScCostCenter s WHERE s.costCenter = :costCenter"),
    @NamedQuery(name = "ScCostCenter.findByNit", query = "SELECT s FROM ScCostCenter s WHERE s.nit = :nit"),
    @NamedQuery(name = "ScCostCenter.findByCreationDate", query = "SELECT s FROM ScCostCenter s WHERE s.creationDate = :creationDate"),
    @NamedQuery(name = "ScCostCenter.findByModifyDate", query = "SELECT s FROM ScCostCenter s WHERE s.modifyDate = :modifyDate"),
    @NamedQuery(name = "ScCostCenter.deleteByidCostCenter", query = "DELETE FROM ScCostCenter s WHERE s.idCostCenter = :idCostCenter")
})
public class ScCostCenter implements Serializable
{
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "costCenter")
    private List<ScInput> scInputList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqsccostcenter")
    @SequenceGenerator(name = "dmes.sqsccostcenter", sequenceName = "dmes.sqsccostcenter", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_cost_center")
    private Long idCostCenter;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nit")
    private String nit;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "cost_center")
    private String costCenter;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "modify_date")
    @Temporal(TemporalType.DATE)
    private Date modifyDate;

    public ScCostCenter()
    {
    }

    public ScCostCenter(Long idClassType)
    {
        this.idCostCenter = idCostCenter;
    }

    public ScCostCenter(Long idCostCenter, String nit, String costCenter, Date creationDate, Date modifyDate) {
        this.idCostCenter = idCostCenter;
        this.nit = nit;
        this.costCenter=costCenter;
        this.creationDate = creationDate;
        this.modifyDate = modifyDate;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idCostCenter != null ? idCostCenter.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScCostCenter))
        {
            return false;
        }
        ScCostCenter other = (ScCostCenter) object;
        if ((this.idCostCenter == null && other.idCostCenter != null) || (this.idCostCenter != null && !this.idCostCenter.equals(other.idCostCenter)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScCostCenter[ idCostCenter=" + idCostCenter + " ]";
    }

    public Long getIdCostCenter() {
        return idCostCenter;
    }

    public void setIdCostCenter(Long idCostCenter) {
        this.idCostCenter = idCostCenter;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @XmlTransient
    public List<ScInput> getScInputList() {
        return scInputList;
    }

    public void setScInputList(List<ScInput> scInputList) {
        this.scInputList = scInputList;
    }

   
}
