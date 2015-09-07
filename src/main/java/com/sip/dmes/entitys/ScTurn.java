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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gchavarro88
 */
@Entity
@Table(name = "sc_turn", schema = "dmes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ScTurn.findAll", query = "SELECT s FROM ScTurn s"),
    @NamedQuery(name = "ScTurn.findByIdTurn", query = "SELECT s FROM ScTurn s WHERE s.idTurn = :idTurn"),
    @NamedQuery(name = "ScTurn.findByDescription", query = "SELECT s FROM ScTurn s WHERE s.description = :description"),
    @NamedQuery(name = "ScTurn.findByHourAmount", query = "SELECT s FROM ScTurn s WHERE s.hourAmount = :hourAmount")
})
public class ScTurn implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_turn")
    private Long idTurn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hour_amount")
    private long hourAmount;

    public ScTurn()
    {
    }

    public ScTurn(Long idTurn)
    {
        this.idTurn = idTurn;
    }

    public ScTurn(Long idTurn, String description, long hourAmount)
    {
        this.idTurn = idTurn;
        this.description = description;
        this.hourAmount = hourAmount;
    }

    public Long getIdTurn()
    {
        return idTurn;
    }

    public void setIdTurn(Long idTurn)
    {
        this.idTurn = idTurn;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public long getHourAmount()
    {
        return hourAmount;
    }

    public void setHourAmount(long hourAmount)
    {
        this.hourAmount = hourAmount;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idTurn != null ? idTurn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScTurn))
        {
            return false;
        }
        ScTurn other = (ScTurn) object;
        if ((this.idTurn == null && other.idTurn != null) || (this.idTurn != null && !this.idTurn.equals(other.idTurn)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return idTurn+","+description;
    }
    
}
