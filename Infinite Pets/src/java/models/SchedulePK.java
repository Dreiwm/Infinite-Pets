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
public class SchedulePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "ScheduleID")
    private int scheduleID;
    @Basic(optional = false)
    @Column(name = "EmployeeID")
    private int employeeID;

    public SchedulePK() {
    }

    public SchedulePK(int scheduleID, int employeeID) {
        this.scheduleID = scheduleID;
        this.employeeID = employeeID;
    }

    public int getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(int scheduleID) {
        this.scheduleID = scheduleID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) scheduleID;
        hash += (int) employeeID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SchedulePK)) {
            return false;
        }
        SchedulePK other = (SchedulePK) object;
        if (this.scheduleID != other.scheduleID) {
            return false;
        }
        if (this.employeeID != other.employeeID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.SchedulePK[ scheduleID=" + scheduleID + ", employeeID=" + employeeID + " ]";
    }
    
}
