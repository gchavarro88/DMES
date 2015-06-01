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
import javax.validation.constraints.Size;


@Entity
@Table(name = "sc_tools", schema = "dmes")
@NamedQueries(
{
    @NamedQuery(name = "ScTools.findAll", query = "SELECT s FROM ScTools s"),
    @NamedQuery(name = "ScTools.findByIdTool", query = "SELECT s FROM ScTools s WHERE s.idTool = :idTool"),
    @NamedQuery(name = "ScTools.findByTool", query = "SELECT s FROM ScTools s WHERE s.tool = :tool"),
    @NamedQuery(name = "ScTools.findByIdType", query = "SELECT s FROM ScTools s WHERE s.idType = :idType"),
    @NamedQuery(name = "ScTools.findByIdClassType", query = "SELECT s FROM ScTools s WHERE s.idType.idClassType = :idClassType"),
    @NamedQuery(name = "ScTools.findByCreationDate", query = "SELECT s FROM ScTools s WHERE s.creationDate = :creationDate"),
    @NamedQuery(name = "ScTools.findByModifyDate", query = "SELECT s FROM ScTools s WHERE s.modifyDate = :modifyDate"),
    @NamedQuery(name = "ScTools.deleteByIdTool", query = "DELETE FROM ScTools s WHERE s.idTool = :idTool")
})
public class ScTools implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqsctools")
    @SequenceGenerator(name = "dmes.sqsctools", sequenceName = "dmes.sqsctools", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_tool")
    private Long idTool;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "tool")
    private String tool;
    @JoinColumn(name = "id_type", referencedColumnName = "id_type")
    @ManyToOne(optional = false)
    private ScType idType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "modify_date")
    @Temporal(TemporalType.DATE)
    private Date modifyDate;

    public ScTools()
    {
    }

    public ScTools(Long idTool)
    {
        this.idTool = idTool;
    }

    public ScTools(Long idTool, String tool, ScType idType, Date creationDate, Date modifyDate) {
        this.idTool = idTool;
        this.tool = tool;
        this.idType = idType;
        this.creationDate = creationDate;
        this.modifyDate = modifyDate;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idTool != null ? idTool.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScTools))
        {
            return false;
        }
        ScTools other = (ScTools) object;
        if ((this.idTool == null && other.idTool != null) || (this.idTool != null && !this.idTool.equals(other.idTool)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScTools[ idTool=" + idTool + " ]";
    }

    public Long getIdTool() {
        return idTool;
    }

    public void setIdTool(Long idTool) {
        this.idTool = idTool;
    }

    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = tool;
    }

    public ScType getIdType() {
        return idType;
    }

    public void setIdType(ScType idType) {
        this.idType = idType;
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
       
}
