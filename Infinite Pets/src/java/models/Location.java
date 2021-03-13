/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Riley
 */
@Entity
@Table(name = "location")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Location.findAll", query = "SELECT l FROM Location l"),
    @NamedQuery(name = "Location.findByLocationID", query = "SELECT l FROM Location l WHERE l.locationID = :locationID"),
    @NamedQuery(name = "Location.findByLocationType", query = "SELECT l FROM Location l WHERE l.locationType = :locationType"),
    @NamedQuery(name = "Location.findByPostalCode", query = "SELECT l FROM Location l WHERE l.postalCode = :postalCode"),
    @NamedQuery(name = "Location.findByAddress", query = "SELECT l FROM Location l WHERE l.address = :address"),
    @NamedQuery(name = "Location.findByCity", query = "SELECT l FROM Location l WHERE l.city = :city"),
    @NamedQuery(name = "Location.findByCountry", query = "SELECT l FROM Location l WHERE l.country = :country"),
    @NamedQuery(name = "Location.findByProvince", query = "SELECT l FROM Location l WHERE l.province = :province"),
    @NamedQuery(name = "Location.findByArea", query = "SELECT l FROM Location l WHERE l.area = :area")})
public class Location implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "LocationID")
    private Integer locationID;
    @Basic(optional = false)
    @Column(name = "LocationType")
    private Character locationType;
    @Basic(optional = false)
    @Column(name = "PostalCode")
    private String postalCode;
    @Basic(optional = false)
    @Column(name = "Address")
    private String address;
    @Basic(optional = false)
    @Column(name = "City")
    private String city;
    @Basic(optional = false)
    @Column(name = "Country")
    private String country;
    @Basic(optional = false)
    @Column(name = "Province")
    private String province;
    @Basic(optional = false)
    @Column(name = "Area")
    private String area;

    public Location() {
    }

    public Location(Integer locationID) {
        this.locationID = locationID;
    }

    public Location(Integer locationID, Character locationType, String postalCode, String address, String city, String country, String province, String area) {
        this.locationID = locationID;
        this.locationType = locationType;
        this.postalCode = postalCode;
        this.address = address;
        this.city = city;
        this.country = country;
        this.province = province;
        this.area = area;
    }

    public Integer getLocationID() {
        return locationID;
    }

    public void setLocationID(Integer locationID) {
        this.locationID = locationID;
    }

    public Character getLocationType() {
        return locationType;
    }

    public void setLocationType(Character locationType) {
        this.locationType = locationType;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (locationID != null ? locationID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Location)) {
            return false;
        }
        Location other = (Location) object;
        if ((this.locationID == null && other.locationID != null) || (this.locationID != null && !this.locationID.equals(other.locationID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Location[ locationID=" + locationID + " ]";
    }
    
}
