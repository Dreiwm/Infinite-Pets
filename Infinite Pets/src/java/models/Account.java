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
@Table(name = "account")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
    @NamedQuery(name = "Account.findByUserId", query = "SELECT a FROM Account a WHERE a.userId = :userId"),
    @NamedQuery(name = "Account.findByUsername", query = "SELECT a FROM Account a WHERE a.username = :username"),
    @NamedQuery(name = "Account.findByPassword", query = "SELECT a FROM Account a WHERE a.password = :password"),
    @NamedQuery(name = "Account.findByPasswordHash", query = "SELECT a FROM Account a WHERE a.passwordHash = :passwordHash"),
    @NamedQuery(name = "Account.findByPasswordSalt", query = "SELECT a FROM Account a WHERE a.passwordSalt = :passwordSalt"),
    @NamedQuery(name = "Account.findByEmail", query = "SELECT a FROM Account a WHERE a.email = :email"),
    @NamedQuery(name = "Account.findByFirstName", query = "SELECT a FROM Account a WHERE a.firstName = :firstName"),
    @NamedQuery(name = "Account.findByLastName", query = "SELECT a FROM Account a WHERE a.lastName = :lastName"),
    @NamedQuery(name = "Account.findByIsEmployee", query = "SELECT a FROM Account a WHERE a.isEmployee = :isEmployee"),
    @NamedQuery(name = "Account.findByIsConfirmed", query = "SELECT a FROM Account a WHERE a.isConfirmed = :isConfirmed"),
    @NamedQuery(name = "Account.findByPasswordResetCode", query = "SELECT a FROM Account a WHERE a.passwordResetCode = :passwordResetCode"),
    @NamedQuery(name = "Account.findByDeleteAccountCode", query = "SELECT a FROM Account a WHERE a.deleteAccountCode = :deleteAccountCode"),
    @NamedQuery(name = "Account.findByPasswordResetActive", query = "SELECT a FROM Account a WHERE a.passwordResetActive = :passwordResetActive")})
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "UserId")
    private Integer userId;
    @Basic(optional = false)
    @Column(name = "Username")
    private String username;
    @Basic(optional = false)
    @Column(name = "Password")
    private String password;
    @Column(name = "PasswordHash")
    private String passwordHash;
    @Column(name = "PasswordSalt")
    private String passwordSalt;
    @Basic(optional = false)
    @Column(name = "Email")
    private String email;
    @Basic(optional = false)
    @Column(name = "FirstName")
    private String firstName;
    @Basic(optional = false)
    @Column(name = "LastName")
    private String lastName;
    @Basic(optional = false)
    @Column(name = "IsEmployee")
    private boolean isEmployee;
    @Basic(optional = false)
    @Column(name = "IsConfirmed")
    private boolean isConfirmed;
    @Column(name = "PasswordResetCode")
    private String passwordResetCode;
    @Column(name = "DeleteAccountCode")
    private String deleteAccountCode;
    @Column(name = "PasswordResetActive")
    private Boolean passwordResetActive;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientID", fetch = FetchType.EAGER)
    private List<Appointment> appointmentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID", fetch = FetchType.EAGER)
    private List<Employee> employeeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner", fetch = FetchType.EAGER)
    private List<Pet> petList;

    public Account() {
    }

    public Account(Integer userId) {
        this.userId = userId;
    }

    public Account(Integer userId, String username, String password, String email, String firstName, String lastName, boolean isEmployee, boolean isConfirmed) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isEmployee = isEmployee;
        this.isConfirmed = isConfirmed;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean getIsEmployee() {
        return isEmployee;
    }

    public void setIsEmployee(boolean isEmployee) {
        this.isEmployee = isEmployee;
    }

    public boolean getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    public String getPasswordResetCode() {
        return passwordResetCode;
    }

    public void setPasswordResetCode(String passwordResetCode) {
        this.passwordResetCode = passwordResetCode;
    }

    public String getDeleteAccountCode() {
        return deleteAccountCode;
    }

    public void setDeleteAccountCode(String deleteAccountCode) {
        this.deleteAccountCode = deleteAccountCode;
    }

    public Boolean getPasswordResetActive() {
        return passwordResetActive;
    }

    public void setPasswordResetActive(Boolean passwordResetActive) {
        this.passwordResetActive = passwordResetActive;
    }

    @XmlTransient
    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    @XmlTransient
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @XmlTransient
    public List<Pet> getPetList() {
        return petList;
    }

    public void setPetList(List<Pet> petList) {
        this.petList = petList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Account[ userId=" + userId + " ]";
    }
    
}
