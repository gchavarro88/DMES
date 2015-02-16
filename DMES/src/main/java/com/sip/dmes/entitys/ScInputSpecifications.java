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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author guschaor
 */
@Entity
@Table(name = "sc_input_specifications")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScInputSpecifications.findAll", query = "SELECT s FROM ScInputSpecifications s"),
    @NamedQuery(name = "ScInputSpecifications.findByIdInputSpecifications", query = "SELECT s FROM ScInputSpecifications s WHERE s.idInputSpecifications = :idInputSpecifications"),
    @NamedQuery(name = "ScInputSpecifications.findByDescription", query = "SELECT s FROM ScInputSpecifications s WHERE s.description = :description"),
    @NamedQuery(name = "ScInputSpecifications.findByTittle", query = "SELECT s FROM ScInputSpecifications s WHERE s.tittle = :tittle"),
    @NamedQuery(name = "ScInputSpecifications.findByCreationDate", query = "SELECT s FROM ScInputSpecifications s WHERE s.creationDate = :creationDate"),
    @NamedQuery(name = "ScInputSpecifications.findByIdInput", query = "SELECT s FROM ScInputSpecifications s WHERE s.idInput = :idInput")})
public class ScInputSpecifications implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_input_specifications")
    private Long idInputSpecifications;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "tittle")
    private String tittle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_input")
    private long idInput;
    @JoinColumn(name = "id_input_specifications", referencedColumnName = "id_input", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private ScInput scInput;

    public ScInputSpecifications() {
    }

    public ScInputSpecifications(Long idInputSpecifications) {
        this.idInputSpecifications = idInputSpecifications;
    }

    public ScInputSpecifications(Long idInputSpecifications, String description, String tittle, Date creationDate, long idInput) {
        this.idInputSpecifications = idInputSpecifications;
        this.description = description;
        this.tittle = tittle;
        this.creationDate = creationDate;
        this.idInput = idInput;
    }

    public Long getIdInputSpecifications() {
        return idInputSpecifications;
    }

    public void setIdInputSpecifications(Long idInputSpecifications) {
        this.idInputSpecifications = idInputSpecifications;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public long getIdInput() {
        return idInput;
    }

    public void setIdInput(long idInput) {
        this.idInput = idInput;
    }

    public ScInput getScInput() {
        return scInput;
    }

    public void setScInput(ScInput scInput) {
        this.scInput = scInput;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInputSpecifications != null ? idInputSpecifications.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScInputSpecifications)) {
            return false;
        }
        ScInputSpecifications other = (ScInputSpecifications) object;
        if ((this.idInputSpecifications == null && other.idInputSpecifications != null) || (this.idInputSpecifications != null && !this.idInputSpecifications.equals(other.idInputSpecifications))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sip.dmes.entitys.ScInputSpecifications[ idInputSpecifications=" + idInputSpecifications + " ]";
    }
    
}
