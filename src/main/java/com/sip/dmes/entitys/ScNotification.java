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
import javax.persistence.FetchType;
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
@Table(name = "sc_notification", schema = "dmes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ScNotification.findAll", query = "SELECT s FROM ScNotification s"),
    @NamedQuery(name = "ScNotification.findByIdStopMachine", query = "SELECT s FROM ScNotification s WHERE s.idStopMachine = :idStopMachine"),
    @NamedQuery(name = "ScNotification.findByPassword", query = "SELECT s FROM ScNotification s WHERE s.password = :password")
})
public class ScNotification implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscnotification")
    @SequenceGenerator(name = "dmes.sqscnotification", sequenceName = "dmes.sqscnotification", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_stop_machine")
    private Long idStopMachine;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "password")
    private String password;
    @JoinColumn(name = "id_group", referencedColumnName = "id_group")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ScGroup idGroup;

    public ScNotification()
    {
    }

    public ScNotification(Long idStopMachine)
    {
        this.idStopMachine = idStopMachine;
    }

    public ScNotification(Long idStopMachine, String password)
    {
        this.idStopMachine = idStopMachine;
        this.password = password;
    }

    public Long getIdStopMachine()
    {
        return idStopMachine;
    }

    public void setIdStopMachine(Long idStopMachine)
    {
        this.idStopMachine = idStopMachine;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public ScGroup getIdGroup()
    {
        return idGroup;
    }

    public void setIdGroup(ScGroup idGroup)
    {
        this.idGroup = idGroup;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idStopMachine != null ? idStopMachine.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScNotification))
        {
            return false;
        }
        ScNotification other = (ScNotification) object;
        if ((this.idStopMachine == null && other.idStopMachine != null) || (this.idStopMachine != null && !this.idStopMachine.equals(other.idStopMachine)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScNotification[ idStopMachine=" + idStopMachine + " ]";
    }
    
}
