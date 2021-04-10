/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Riley
 */
@Embeddable
public class EmpServicePreferencePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "EmpServicePreferenceID")
    private int empServicePreferenceID;
    @Basic(optional = false)
    @Column(name = "EmployeeID")
    private int employeeID;
    @Basic(optional = false)
    @Column(name = "ServiceTypeID")
    private int serviceTypeID;

    public EmpServicePreferencePK() {
    }

    public EmpServicePreferencePK(int empServicePreferenceID, int employeeID, int serviceTypeID) {
        this.empServicePreferenceID = empServicePreferenceID;
        this.employeeID = employeeID;
        this.serviceTypeID = serviceTypeID;
    }

    public int getEmpServicePreferenceID() {
        return empServicePreferenceID;
    }

    public void setEmpServicePreferenceID(int empServicePreferenceID) {
        this.empServicePreferenceID = empServicePreferenceID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getServiceTypeID() {
        return serviceTypeID;
    }

    public void setServiceTypeID(int serviceTypeID) {
        this.serviceTypeID = serviceTypeID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) empServicePreferenceID;
        hash += (int) employeeID;
        hash += (int) serviceTypeID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmpServicePreferencePK)) {
            return false;
        }
        EmpServicePreferencePK other = (EmpServicePreferencePK) object;
        if (this.empServicePreferenceID != other.empServicePreferenceID) {
            return false;
        }
        if (this.employeeID != other.employeeID) {
            return false;
        }
        if (this.serviceTypeID != other.serviceTypeID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.EmpServicePreferencePK[ empServicePreferenceID=" + empServicePreferenceID + ", employeeID=" + employeeID + ", serviceTypeID=" + serviceTypeID + " ]";
    }
    
}
