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
@Table(name = "sc_tool_attached", schema = "dmes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScToolAttached.findAll", query = "SELECT s FROM ScToolAttached s"),
    @NamedQuery(name = "ScToolAttached.findById", query = "SELECT s FROM ScToolAttached s WHERE s.idToolAttached = :idToolAttached"),
    @NamedQuery(name = "ScToolAttached.findByTittle", query = "SELECT s FROM ScToolAttached s WHERE s.tittle = :tittle"),
    @NamedQuery(name = "ScToolAttached.findByType", query = "SELECT s FROM ScToolAttached s WHERE s.type = :type ORDER BY s.tittle"),
    @NamedQuery(name = "ScToolAttached.findByDescription", query = "SELECT s FROM ScToolAttached s WHERE s.description = :description")})


public class ScToolAttached implements Serializable 
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqsctoolattached")
    @SequenceGenerator(name = "dmes.sqsctoolattached", sequenceName = "dmes.sqsctoolattached", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_tool_attached")
    private Long idToolAttached;
    
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
    @JoinColumn(name = "id_tool", referencedColumnName = "id_tool")
    @ManyToOne(optional = false)
    private ScTool tool;

    public ScToolAttached() {
    }

    public ScToolAttached(Long idToolAttached) {
        this.idToolAttached = idToolAttached;
    }

    public ScToolAttached(Long idToolAttached, String tittle, String description) {
        this.idToolAttached = idToolAttached;
        this.tittle = tittle;
        this.description = description;
    }

    public Long getIdToolAttached()
    {
        return idToolAttached;
    }

    public void setIdToolAttached(Long idToolAttached)
    {
        this.idToolAttached = idToolAttached;
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

    public ScTool getTool()
    {
        return tool;
    }

    public void setTool(ScTool tool)
    {
        this.tool = tool;
    }

    @Override
    public String toString()
    {
        return "ScToolAttached{" + "idToolAttached=" + idToolAttached + ", type=" + type + ", tittle=" + tittle + ", description=" + description + ", tool=" + tool + '}';
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.idToolAttached);
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
        final ScToolAttached other = (ScToolAttached) obj;
        if (!Objects.equals(this.idToolAttached, other.idToolAttached))
        {
            return false;
        }
        return true;
    }


    
}
