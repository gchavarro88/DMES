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
@Table(name = "sc_store", schema = "dmes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScStore.findAll", query = "SELECT s FROM ScStore s ORDER BY s.name"),
    @NamedQuery(name = "ScStore.findByIdStore", query = "SELECT s FROM ScStore s WHERE s.idStore = :idStore"),
    @NamedQuery(name = "ScStore.findByName", query = "SELECT s FROM ScStore s WHERE s.name = :name")})
public class ScStore implements Serializable 
{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscpartners")
    @SequenceGenerator(name = "dmes.sqscpartners", sequenceName = "dmes.sqscpartners", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_store")
    private Long idStore;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idStore")
    private List<ScInputStock> scInputStockList;

    public ScStore() {
    }

    public ScStore(Long idStore) {
        this.idStore = idStore;
    }

    public ScStore(Long idStore, String name) {
        this.idStore = idStore;
        this.name = name;
    }

    public Long getIdStore() {
        return idStore;
    }

    public void setIdStore(Long idStore) {
        this.idStore = idStore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStore != null ? idStore.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScStore)) {
            return false;
        }
        ScStore other = (ScStore) object;
        if ((this.idStore == null && other.idStore != null) || (this.idStore != null && !this.idStore.equals(other.idStore))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idStore.toString()+","+getName().toString();
    }

    @XmlTransient
    public List<ScInputStock> getScInputStockList()
    {
        return scInputStockList;
    }

    public void setScInputStockList(List<ScInputStock> scInputStockList)
    {
        this.scInputStockList = scInputStockList;
    }
    
}
