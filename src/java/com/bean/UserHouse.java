/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author greit
 */
@Entity
@Table(name = "user_house")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserHouse.findAll", query = "SELECT u FROM UserHouse u")
    , @NamedQuery(name = "UserHouse.findById", query = "SELECT u FROM UserHouse u WHERE u.id = :id")
    , @NamedQuery(name = "UserHouse.findByUserId", query = "SELECT u FROM UserHouse u WHERE u.userId = :userId")
    , @NamedQuery(name = "UserHouse.findByHouseId", query = "SELECT u FROM UserHouse u WHERE u.houseId = :houseId")})
public class UserHouse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "house_id")
    private int houseId;

    public UserHouse() {
    }

    public UserHouse(Integer id) {
        this.id = id;
    }

    public UserHouse(Integer id, int userId, int houseId) {
        this.id = id;
        this.userId = userId;
        this.houseId = houseId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserHouse)) {
            return false;
        }
        UserHouse other = (UserHouse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bean.UserHouse[ id=" + id + " ]";
    }
    
}
