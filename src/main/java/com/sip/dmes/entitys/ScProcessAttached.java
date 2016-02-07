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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author guschaor
 */
@Entity
@Table(name = "sc_product_attached", schema = "dmes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScProcessAttached.findAll", query = "SELECT s FROM ScProcessAttached s"),
    @NamedQuery(name = "ScProcessAttached.findByIdProcessAttached", query = "SELECT s FROM ScProcessAttached s WHERE s.idProcessAttached = :idProcessAttached"),
    @NamedQuery(name = "ScProcessAttached.findByTittle", query = "SELECT s FROM ScProcessAttached s WHERE s.tittle = :tittle"),
    @NamedQuery(name = "ScProcessAttached.findByType", query = "SELECT s FROM ScProcessAttached s WHERE s.type = :type ORDER BY s.tittle"),
    @NamedQuery(name = "ScProcessAttached.findByDescription", query = "SELECT s FROM ScProcessAttached s WHERE s.description = :description")})
public class ScProcessAttached implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscprocessattached")
    @SequenceGenerator(name = "dmes.sqscprocessattached", sequenceName = "dmes.sqscprocessattached", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_process_attached")
    private Long idProcessAttached;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "type")
    private String type;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "tittle")
    private String tittle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "description")
    private String description;
    
    @JoinColumn(name = "id_process_product", referencedColumnName = "id_process_product")
    @ManyToOne(optional = false)
    private ScProcessProduct processProduct;

    public ScProcessAttached() {
    }

    public ScProcessAttached(Long idProcessAttached) {
        this.idProcessAttached = idProcessAttached;
    }

    public ScProcessAttached(Long idProcessAttached, String tittle, String description) {
        this.idProcessAttached = idProcessAttached;
        this.tittle = tittle;
        this.description = description;
    }

    public Long getiProcessAttachedd()
    {
        return idProcessAttached;
    }

    public void setIdProcessAttachedd(Long idProcessAttached)
    {
        this.idProcessAttached = idProcessAttached;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
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

    public ScProcessProduct getProcessProduct()
    {
        return processProduct;
    }

    public void setProcessProduct(ScProcessProduct processProduct)
    {
        this.processProduct = processProduct;
    }


    
}
