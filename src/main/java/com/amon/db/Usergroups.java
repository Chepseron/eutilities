/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amon.db;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author amon.sabul
 */
@Entity
@Table(name = "usergroups")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usergroups.findAll", query = "SELECT u FROM Usergroups u")
    , @NamedQuery(name = "Usergroups.findByIdusergroups", query = "SELECT u FROM Usergroups u WHERE u.idusergroups = :idusergroups")
    , @NamedQuery(name = "Usergroups.findByCode", query = "SELECT u FROM Usergroups u WHERE u.code = :code")
    , @NamedQuery(name = "Usergroups.findByName", query = "SELECT u FROM Usergroups u WHERE u.name = :name")})
public class Usergroups implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idusergroups")
    private Integer idusergroups;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usergroup")
    private Collection<Users> usersCollection;

    public Usergroups() {
    }

    public Usergroups(Integer idusergroups) {
        this.idusergroups = idusergroups;
    }

    public Usergroups(Integer idusergroups, String code, String name) {
        this.idusergroups = idusergroups;
        this.code = code;
        this.name = name;
    }

    public Integer getIdusergroups() {
        return idusergroups;
    }

    public void setIdusergroups(Integer idusergroups) {
        this.idusergroups = idusergroups;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusergroups != null ? idusergroups.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usergroups)) {
            return false;
        }
        Usergroups other = (Usergroups) object;
        if ((this.idusergroups == null && other.idusergroups != null) || (this.idusergroups != null && !this.idusergroups.equals(other.idusergroups))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.amon.db.Usergroups[ idusergroups=" + idusergroups + " ]";
    }
    
}
