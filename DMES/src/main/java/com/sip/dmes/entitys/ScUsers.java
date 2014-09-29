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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author gchavarro88
 */
@Entity
@Table(name = "sc_users", schema = "dmes")
@NamedQueries(
{
    @NamedQuery(name = "ScUsers.findAll", query = "SELECT s FROM ScUsers s"),
    @NamedQuery(name = "ScUsers.findByIdUser", query = "SELECT s FROM ScUsers s WHERE s.idUser = :idUser"),
    @NamedQuery(name = "ScUsers.findByLogin", query = "SELECT s FROM ScUsers s WHERE s.login = :login"),
    @NamedQuery(name = "ScUsers.findByPassword", query = "SELECT s FROM ScUsers s WHERE s.password = :password"),
    @NamedQuery(name = "ScUsers.findByCreationDate", query = "SELECT s FROM ScUsers s WHERE s.creationDate = :creationDate"),
    @NamedQuery(name = "ScUsers.findByModifyDate", query = "SELECT s FROM ScUsers s WHERE s.modifyDate = :modifyDate")
})
public class ScUsers implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_user")
    private Long idUser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "login")
    private String login;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "modify_date")
    @Temporal(TemporalType.DATE)
    private Date modifyDate;
    @JoinColumn(name = "id_role", referencedColumnName = "id_role")
    @ManyToOne(optional = false)
    private ScRoles idRole;
    @JoinColumn(name = "id_person", referencedColumnName = "id_person")
    @ManyToOne(optional = false)
    private ScPerson idPerson;

    public ScUsers()
    {
    }

    public ScUsers(Long idUser)
    {
        this.idUser = idUser;
    }

    public ScUsers(Long idUser, String login, String password, Date creationDate)
    {
        this.idUser = idUser;
        this.login = login;
        this.password = password;
        this.creationDate = creationDate;
    }

    public Long getIdUser()
    {
        return idUser;
    }

    public void setIdUser(Long idUser)
    {
        this.idUser = idUser;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Date getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }

    public Date getModifyDate()
    {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate)
    {
        this.modifyDate = modifyDate;
    }

    public ScRoles getIdRole()
    {
        return idRole;
    }

    public void setIdRole(ScRoles idRole)
    {
        this.idRole = idRole;
    }

    public ScPerson getIdPerson()
    {
        return idPerson;
    }

    public void setIdPerson(ScPerson idPerson)
    {
        this.idPerson = idPerson;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScUsers))
        {
            return false;
        }
        ScUsers other = (ScUsers) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScUsers[ idUser=" + idUser + " ]";
    }
    
}
