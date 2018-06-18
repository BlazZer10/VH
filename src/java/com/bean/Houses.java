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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author greit
 */
@Entity
@Table(name = "houses")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Houses.findAll", query = "SELECT h FROM Houses h")
    , @NamedQuery(name = "Houses.findById", query = "SELECT h FROM Houses h WHERE h.id = :id")
    , @NamedQuery(name = "Houses.findByType", query = "SELECT h FROM Houses h WHERE h.type = :type")
    , @NamedQuery(name = "Houses.findByCountry", query = "SELECT h FROM Houses h WHERE h.country = :country")
    , @NamedQuery(name = "Houses.findByCity", query = "SELECT h FROM Houses h WHERE h.city = :city")
    , @NamedQuery(name = "Houses.findByStreet", query = "SELECT h FROM Houses h WHERE h.street = :street")
    , @NamedQuery(name = "Houses.findByCapacity", query = "SELECT h FROM Houses h WHERE h.capacity = :capacity")
    , @NamedQuery(name = "Houses.findByPrice", query = "SELECT h FROM Houses h WHERE h.price = :price")
    , @NamedQuery(name = "Houses.findByCreatedBy", query = "SELECT h FROM Houses h WHERE h.createdBy = :createdBy")
    , @NamedQuery(name = "Houses.findByStatus", query = "SELECT h FROM Houses h WHERE h.status = :status")
    , @NamedQuery(name = "Houses.findByPhotos", query = "SELECT h FROM Houses h WHERE h.photos = :photos")})
public class Houses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "country")
    private String country;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "city")
    private String city;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "street")
    private String street;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "capacity")
    private String capacity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private int price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_by")
    private int createdBy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private boolean status;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "photos")
    private String photos;

    public Houses() {
    }

    public Houses(Integer id) {
        this.id = id;
    }

    public Houses(Integer id, String type, String country, String city, String street, String capacity, int price, int createdBy, boolean status, String photos) {
        this.id = id;
        this.type = type;
        this.country = country;
        this.city = city;
        this.street = street;
        this.capacity = capacity;
        this.price = price;
        this.createdBy = createdBy;
        this.status = status;
        this.photos = photos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
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
        if (!(object instanceof Houses)) {
            return false;
        }
        Houses other = (Houses) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bean.Houses[ id=" + id + " ]";
    }
    
}
