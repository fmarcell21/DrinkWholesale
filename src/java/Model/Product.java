/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.json.JSONObject;

/**
 *
 * @author wellm
 */
@Entity
@Table(name = "product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.findByProductName", query = "SELECT p FROM Product p WHERE p.productName = :productName"),
    @NamedQuery(name = "Product.findByManufacturer", query = "SELECT p FROM Product p WHERE p.manufacturer = :manufacturer"),
    @NamedQuery(name = "Product.findByAbv", query = "SELECT p FROM Product p WHERE p.abv = :abv"),
    @NamedQuery(name = "Product.findByCurrStock", query = "SELECT p FROM Product p WHERE p.currStock = :currStock"),
    @NamedQuery(name = "Product.findByMinStock", query = "SELECT p FROM Product p WHERE p.minStock = :minStock"),
    @NamedQuery(name = "Product.findByProductId", query = "SELECT p FROM Product p WHERE p.productId = :productId"),
    @NamedQuery(name = "Product.findByWarehouseID", query = "SELECT p FROM Product p WHERE p.warehouseID = :warehouseID"),
    @NamedQuery(name = "Product.findByVisible", query = "SELECT p FROM Product p WHERE p.visible = :visible")})
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ProductName")
    private String productName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Manufacturer")
    private String manufacturer;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Abv")
    private short abv;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CurrStock")
    private int currStock;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MinStock")
    private int minStock;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ProductId")
    private Integer productId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "WarehouseID")
    private int warehouseID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Visible")
    private boolean visible;

    public Product() {
    }

    public Product(Integer productId) {
        this.productId = productId;
    }

    public Product(Integer productId, String productName, String manufacturer, short abv, int currStock, int minStock, int warehouseID, boolean visible) {
        this.productId = productId;
        this.productName = productName;
        this.manufacturer = manufacturer;
        this.abv = abv;
        this.currStock = currStock;
        this.minStock = minStock;
        this.warehouseID = warehouseID;
        this.visible = visible;
    }
    
    public JSONObject toJson(){
        JSONObject object = new JSONObject();
        
        object.put("productId",this.productId);
        object.put("productName",this.productName);
        object.put("manufacturer",this.manufacturer);
        object.put("abv",this.abv);
        object.put("currStock",this.currStock);
        object.put("minStock",this.minStock);
        object.put("warehouseID",this.warehouseID);
        object.put("visible",this.visible);
        
        
        
        return object;
    }
    
    public static Product getProductById(int id){
        EntityManager em = dbconnect.getDbConn();
        return em.find(Product.class, id);
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public short getAbv() {
        return abv;
    }

    public void setAbv(short abv) {
        this.abv = abv;
    }

    public int getCurrStock() {
        return currStock;
    }

    public void setCurrStock(int currStock) {
        this.currStock = currStock;
    }

    public int getMinStock() {
        return minStock;
    }

    public void setMinStock(int minStock) {
        this.minStock = minStock;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public int getWarehouseID() {
        return warehouseID;
    }

    public void setWarehouseID(int warehouseID) {
        this.warehouseID = warehouseID;
    }

    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productId != null ? productId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Product[ productId=" + productId + " ]";
    }
    
}
