/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Riley
 */
@Entity
@Table(name = "serviceType")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServiceType.findAll", query = "SELECT s FROM ServiceType s"),
    @NamedQuery(name = "ServiceType.findByServiceTypeID", query = "SELECT s FROM ServiceType s WHERE s.serviceTypeID = :serviceTypeID"),
    @NamedQuery(name = "ServiceType.findByServiceType", query = "SELECT s FROM ServiceType s WHERE s.serviceType = :serviceType")})
public class ServiceType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ServiceTypeID")
    private Integer serviceTypeID;
    @Basic(optional = false)
    @Column(name = "ServiceType")
    private String serviceType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceTypeID", fetch = FetchType.EAGER)
    private List<EmpServicePreference> empServicePreferenceList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceTypeID", fetch = FetchType.EAGER)
    private List<Service> serviceList;

    public ServiceType() {
    }

    public ServiceType(Integer serviceTypeID) {
        this.serviceTypeID = serviceTypeID;
    }

    public ServiceType(Integer serviceTypeID, String serviceType) {
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
    public List<EmpServicePreference> getEmpServicePreferenceList() {
        return empServicePreferenceList;
    }

    public void setEmpServicePreferenceList(List<EmpServicePreference> empServicePreferenceList) {
        this.empServicePreferenceList = empServicePreferenceList;
    }

    @XmlTransient
    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
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
        if (!(object instanceof ServiceType)) {
            return false;
        }
        ServiceType other = (ServiceType) object;
        if ((this.serviceTypeID == null && other.serviceTypeID != null) || (this.serviceTypeID != null && !this.serviceTypeID.equals(other.serviceTypeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.ServiceType[ serviceTypeID=" + serviceTypeID + " ]";
    }
    
}
