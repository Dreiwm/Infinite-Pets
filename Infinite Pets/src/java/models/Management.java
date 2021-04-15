/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @author BTran
 */
@Entity
@Table(name = "management")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Management.findAll", query = "SELECT m FROM Management m")
    , @NamedQuery(name = "Management.findByItemID", query = "SELECT m FROM Management m WHERE m.itemID = :itemID")
    , @NamedQuery(name = "Management.findByItemName", query = "SELECT m FROM Management m WHERE m.itemName = :itemName")
    , @NamedQuery(name = "Management.findByItemDescription", query = "SELECT m FROM Management m WHERE m.itemDescription = :itemDescription")
    , @NamedQuery(name = "Management.findByItemLink", query = "SELECT m FROM Management m WHERE m.itemLink = :itemLink")
    , @NamedQuery(name = "Management.findByItemPrice", query = "SELECT m FROM Management m WHERE m.itemPrice = :itemPrice")})
public class Management implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ItemID")
    private Integer itemID;
    @Column(name = "ItemName")
    private String itemName;
    @Column(name = "ItemDescription")
    private String itemDescription;
    @Column(name = "ItemLink")
    private String itemLink;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ItemPrice")
    private BigDecimal itemPrice;

    public Management() {
    }

    public Management(Integer itemID) {
        this.itemID = itemID;
    }

    public Integer getItemID() {
        return itemID;
    }

    public void setItemID(Integer itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemLink() {
        return itemLink;
    }

    public void setItemLink(String itemLink) {
        this.itemLink = itemLink;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemID != null ? itemID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Management)) {
            return false;
        }
        Management other = (Management) object;
        if ((this.itemID == null && other.itemID != null) || (this.itemID != null && !this.itemID.equals(other.itemID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Management[ itemID=" + itemID + " ]";
    }
    
}
