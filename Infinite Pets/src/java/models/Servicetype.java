/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author StormCloud
 */
@Entity
@Table(name = "servicetype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Servicetype.findAll", query = "SELECT s FROM Servicetype s")
    , @NamedQuery(name = "Servicetype.findByServiceTypeID", query = "SELECT s FROM Servicetype s WHERE s.serviceTypeID = :serviceTypeID")
    , @NamedQuery(name = "Servicetype.findByServiceType", query = "SELECT s FROM Servicetype s WHERE s.serviceType = :serviceType")})
public class Servicetype implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ServiceTypeID")
    private Integer serviceTypeID;
    @Basic(optional = false)
    @Column(name = "ServiceType")
    private String serviceType;
    @ManyToMany(mappedBy = "servicetypeCollection")
    private Collection<Employee> employeeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceTypeID")
    private Collection<Service> serviceCollection;

    public Servicetype() {
    }

    public Servicetype(Integer serviceTypeID) {
        this.serviceTypeID = serviceTypeID;
    }

    public Servicetype(Integer serviceTypeID, String serviceType) {
        this.serviceTypeID = serviceTypeID;
        this.serviceType = serviceType;
    }

    public Integer getServiceTypeID() {
        return serviceTypeID;
    }

    public void setServiceTypeID(Integer serviceTypeID) {
        this.serviceTypeID = serviceTypeID;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    @XmlTransient
    public Collection<Employee> getEmployeeCollection() {
        return employeeCollection;
    }

    public void setEmployeeCollection(Collection<Employee> employeeCollection) {
        this.employeeCollection = employeeCollection;
    }

    @XmlTransient
    public Collection<Service> getServiceCollection() {
        return serviceCollection;
    }

    public void setServiceCollection(Collection<Service> serviceCollection) {
        this.serviceCollection = serviceCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serviceTypeID != null ? serviceTypeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servicetype)) {
            return false;
        }
        Servicetype other = (Servicetype) object;
        if ((this.serviceTypeID == null && other.serviceTypeID != null) || (this.serviceTypeID != null && !this.serviceTypeID.equals(other.serviceTypeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Servicetype[ serviceTypeID=" + serviceTypeID + " ]";
    }
    
}
