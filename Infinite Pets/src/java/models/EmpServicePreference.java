/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    @NamedQuery(name = "EmpServicePreference.findByEmpServicePreferenceID", query = "SELECT e FROM EmpServicePreference e WHERE e.empServicePreferencePK.empServicePreferenceID = :empServicePreferenceID"),
    @NamedQuery(name = "EmpServicePreference.findByEmployeeID", query = "SELECT e FROM EmpServicePreference e WHERE e.empServicePreferencePK.employeeID = :employeeID"),
    @NamedQuery(name = "EmpServicePreference.findByServiceTypeID", query = "SELECT e FROM EmpServicePreference e WHERE e.empServicePreferencePK.serviceTypeID = :serviceTypeID")})
public class EmpServicePreference implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EmpServicePreferencePK empServicePreferencePK;
    @JoinColumn(name = "EmployeeID", referencedColumnName = "EmployeeID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Employee employee;
    @JoinColumn(name = "ServiceTypeID", referencedColumnName = "ServiceTypeID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ServiceType serviceType;

    public EmpServicePreference() {
    }

    public EmpServicePreference(EmpServicePreferencePK empServicePreferencePK) {
        this.empServicePreferencePK = empServicePreferencePK;
    }

    public EmpServicePreference(int empServicePreferenceID, int employeeID, int serviceTypeID) {
        this.empServicePreferencePK = new EmpServicePreferencePK(empServicePreferenceID, employeeID, serviceTypeID);
    }

    public EmpServicePreferencePK getEmpServicePreferencePK() {
        return empServicePreferencePK;
    }

    public void setEmpServicePreferencePK(EmpServicePreferencePK empServicePreferencePK) {
        this.empServicePreferencePK = empServicePreferencePK;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empServicePreferencePK != null ? empServicePreferencePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmpServicePreference)) {
            return false;
        }
        EmpServicePreference other = (EmpServicePreference) object;
        if ((this.empServicePreferencePK == null && other.empServicePreferencePK != null) || (this.empServicePreferencePK != null && !this.empServicePreferencePK.equals(other.empServicePreferencePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.EmpServicePreference[ empServicePreferencePK=" + empServicePreferencePK + " ]";
    }
    
}
