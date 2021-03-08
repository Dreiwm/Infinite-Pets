/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "service")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Service.findAll", query = "SELECT s FROM Service s"),
    @NamedQuery(name = "Service.findByServiceID", query = "SELECT s FROM Service s WHERE s.serviceID = :serviceID"),
    @NamedQuery(name = "Service.findByServiceName", query = "SELECT s FROM Service s WHERE s.serviceName = :serviceName"),
    @NamedQuery(name = "Service.findByBasePrice", query = "SELECT s FROM Service s WHERE s.basePrice = :basePrice"),
    @NamedQuery(name = "Service.findByActive", query = "SELECT s FROM Service s WHERE s.active = :active"),
    @NamedQuery(name = "Service.findBySpecifyPet", query = "SELECT s FROM Service s WHERE s.specifyPet = :specifyPet"),
    @NamedQuery(name = "Service.findByDateRange", query = "SELECT s FROM Service s WHERE s.dateRange = :dateRange")})
public class Service implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ServiceID")
    private Integer serviceID;
    @Basic(optional = false)
    @Column(name = "ServiceName")
    private String serviceName;
    @Lob
    @Column(name = "ServiceDescription")
    private String serviceDescription;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "BasePrice")
    private BigDecimal basePrice;
    @Basic(optional = false)
    @Column(name = "Active")
    private boolean active;
    @Basic(optional = false)
    @Column(name = "SpecifyPet")
    private boolean specifyPet;
    @Basic(optional = false)
    @Column(name = "DateRange")
    private boolean dateRange;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceID", fetch = FetchType.EAGER)
    private List<Appointment> appointmentList;

    public Service() {
    }

    public Service(Integer serviceID) {
        this.serviceID = serviceID;
    }

    public Service(Integer serviceID, String serviceName, BigDecimal basePrice, boolean active, boolean specifyPet, boolean dateRange) {
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.basePrice = basePrice;
        this.active = active;
        this.specifyPet = specifyPet;
        this.dateRange = dateRange;
    }

    public Integer getServiceID() {
        return serviceID;
    }

    public void setServiceID(Integer serviceID) {
        this.serviceID = serviceID;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean getSpecifyPet() {
        return specifyPet;
    }

    public void setSpecifyPet(boolean specifyPet) {
        this.specifyPet = specifyPet;
    }

    public boolean getDateRange() {
        return dateRange;
    }

    public void setDateRange(boolean dateRange) {
        this.dateRange = dateRange;
    }

    @XmlTransient
    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serviceID != null ? serviceID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Service)) {
            return false;
        }
        Service other = (Service) object;
        if ((this.serviceID == null && other.serviceID != null) || (this.serviceID != null && !this.serviceID.equals(other.serviceID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Service[ serviceID=" + serviceID + " ]";
    }
    
}
