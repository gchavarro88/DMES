/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.entitys;

import java.io.Serializable;
import java.util.Objects;
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
@Table(name = "sc_replacement_attached", schema = "dmes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScReplacementAttached.findAll", query = "SELECT s FROM ScReplacementAttached s"),
    @NamedQuery(name = "ScReplacementAttached.findById", query = "SELECT s FROM ScReplacementAttached s WHERE s.idReplacementAttached = :idReplacementAttached"),
    @NamedQuery(name = "ScReplacementAttached.findByTittle", query = "SELECT s FROM ScReplacementAttached s WHERE s.tittle = :tittle"),
    @NamedQuery(name = "ScReplacementAttached.findByType", query = "SELECT s FROM ScReplacementAttached s WHERE s.type = :type ORDER BY s.tittle"),
    @NamedQuery(name = "ScReplacementAttached.findByDescription", query = "SELECT s FROM ScReplacementAttached s WHERE s.description = :description")})


public class ScReplacementAttached implements Serializable 
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscreplacementattached")
    @SequenceGenerator(name = "dmes.sqscreplacementattached", sequenceName = "dmes.sqscreplacementattached", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_replacement_attached")
    private Long idReplacementAttached;
    
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
    @JoinColumn(name = "id_replacement", referencedColumnName = "id_replacement")
    @ManyToOne(optional = false)
    private ScReplacement replacement;

    public ScReplacementAttached() {
    }

    public ScReplacementAttached(Long idReplacementAttached) {
        this.idReplacementAttached = idReplacementAttached;
    }

    public ScReplacementAttached(Long idReplacementAttached, String tittle, String description) {
        this.idReplacementAttached = idReplacementAttached;
        this.tittle = tittle;
        this.description = description;
    }

    public Long getIdReplacementAttached()
    {
        return idReplacementAttached;
    }

    public void setIdReplacementAttached(Long idReplacementAttached)
    {
        this.idReplacementAttached = idReplacementAttached;
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

    public ScReplacement getReplacement()
    {
        return replacement;
    }

    public void setReplacement(ScReplacement replacement)
    {
        this.replacement = replacement;
    }

    @Override
    public String toString()
    {
        return "ScReplacementAttached{" + "idReplacementAttached=" + idReplacementAttached + ", type=" + type + ", tittle=" + tittle + ", description=" + description + ", replacement=" + replacement + '}';
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.idReplacementAttached);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final ScReplacementAttached other = (ScReplacementAttached) obj;
        if (!Objects.equals(this.idReplacementAttached, other.idReplacementAttached))
        {
            return false;
        }
        return true;
    }


    
}
