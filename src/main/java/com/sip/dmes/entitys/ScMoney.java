/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.entitys;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author guschaor
 */
@Entity
@Table(name = "sc_money", schema = "dmes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScMoney.findAll", query = "SELECT s FROM ScMoney s ORDER BY s.description"),
    @NamedQuery(name = "ScMoney.findByIdMoney", query = "SELECT s FROM ScMoney s WHERE s.idMoney = :idMoney"),
    @NamedQuery(name = "ScMoney.findByDescription", query = "SELECT s FROM ScMoney s WHERE s.description = :description"),
    @NamedQuery(name = "ScMoney.findByAcronym", query = "SELECT s FROM ScMoney s WHERE s.acronym = :acronym")})
public class ScMoney implements Serializable {
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscmoney")
    @SequenceGenerator(name = "dmes.sqscmoney", sequenceName = "dmes.sqscmoney", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_money")
    private Long idMoney;
    @Size(max = 200)
    @Column(name = "description")
    private String description;
    @Column(name = "trm")
    private Double trm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "acronym")
    private String acronym;

    
    public ScMoney() {
    }

    public ScMoney(Long idPacking) {
        this.idMoney = idPacking;
    }

    public ScMoney(Long idMoney, String acronym) {
        this.idMoney = idMoney;
        this.acronym = acronym;
    }

    public Long getIdMoney() {
        return idMoney;
    }

    public void setIdMoney(Long idPacking) {
        this.idMoney = idPacking;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }


    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMoney != null ? idMoney.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScMoney)) {
            return false;
        }
        ScMoney other = (ScMoney) object;
        if ((this.idMoney == null && other.idMoney != null) || 
                (this.idMoney != null && !this.idMoney.equals(other.idMoney))) 
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idMoney.toString()+","+getAcronym().toString();
    }


    public Double getTrm()
    {
        return trm;
    }

    public void setTrm(Double trm)
    {
        this.trm = trm;
    }

   
}
