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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Riley
 */
@Entity
@Table(name = "empServicePreference")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmpServicePreference.findAll", query = "SELECT e FROM EmpServicePreference e"),
    @NamedQuery(name = "EmpServicePreference.findByEmpServicePreferenceID", query = "SELECT e FROM EmpServicePreference e WHERE e.empServicePreferenceID = :empServicePreferenceID")})
public class EmpServicePreference implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "EmpServicePreferenceID")
    private Integer empServicePreferenceID;
    @JoinColumn(name = "EmployeeID", referencedColumnName = "EmployeeID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Employee employeeID;
    @JoinColumn(name = "ServiceTypeID", referencedColumnName = "ServiceTypeID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ServiceType serviceTypeID;

    public EmpServicePreference() {
    }

    public EmpServicePreference(Integer empServicePreferenceID) {
        this.empServicePreferenceID = empServicePreferenceID;
    }

    public Integer getEmpServicePreferenceID() {
        return empServicePreferenceID;
    }

    public void setEmpServicePreferenceID(Integer empServicePreferenceID) {
        this.empServicePreferenceID = empServicePreferenceID;
    }

    public Employee getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Employee employeeID) {
        this.employeeID = employeeID;
    }

    public ServiceType getServiceTypeID() {
        return serviceTypeID;
    }

    public void setServiceTypeID(ServiceType serviceTypeID) {
        this.serviceTypeID = serviceTypeID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empServicePreferenceID != null ? empServicePreferenceID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmpServicePreference)) {
            return false;
        }
        EmpServicePreference other = (EmpServicePreference) object;
        if ((this.empServicePreferenceID == null && other.empServicePreferenceID != null) || (this.empServicePreferenceID != null && !this.empServicePreferenceID.equals(other.empServicePreferenceID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.EmpServicePreference[ empServicePreferenceID=" + empServicePreferenceID + " ]";
    }
    
}
