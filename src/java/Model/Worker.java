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
@Table(name = "worker")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Worker.findAll", query = "SELECT w FROM Worker w"),
    @NamedQuery(name = "Worker.findByFName", query = "SELECT w FROM Worker w WHERE w.fName = :fName"),
    @NamedQuery(name = "Worker.findBySName", query = "SELECT w FROM Worker w WHERE w.sName = :sName"),
    @NamedQuery(name = "Worker.findByWorkerId", query = "SELECT w FROM Worker w WHERE w.workerId = :workerId"),
    @NamedQuery(name = "Worker.findByShiftLenght", query = "SELECT w FROM Worker w WHERE w.shiftLenght = :shiftLenght"),
    @NamedQuery(name = "Worker.findByShiftStart", query = "SELECT w FROM Worker w WHERE w.shiftStart = :shiftStart"),
    @NamedQuery(name = "Worker.findByFWarehouseID", query = "SELECT w FROM Worker w WHERE w.fWarehouseID = :fWarehouseID"),
    @NamedQuery(name = "Worker.findByVisible", query = "SELECT w FROM Worker w WHERE w.visible = :visible")})
public class Worker implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "FName")
    private String fName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "SName")
    private String sName;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "WorkerId")
    private Integer workerId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ShiftLenght")
    private short shiftLenght;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ShiftStart")
    private int shiftStart;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FWarehouseID")
    private int fWarehouseID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Visible")
    private boolean visible;

    public Worker() {
    }

    public Worker(Integer workerId) {
        this.workerId = workerId;
    }

    public Worker(Integer workerId, String fName, String sName, short shiftLenght, int shiftStart, int fWarehouseID, boolean visible) {
        this.workerId = workerId;
        this.fName = fName;
        this.sName = sName;
        this.shiftLenght = shiftLenght;
        this.shiftStart = shiftStart;
        this.fWarehouseID = fWarehouseID;
        this.visible = visible;
    }
    
    public JSONObject toJson(){
        JSONObject object = new JSONObject();
        
        object.put("workerId", this.workerId);
        object.put("fName", this.fName);
        object.put("sName", this.sName);
        object.put("shiftLenght", this.shiftLenght);
        object.put("shiftStart", this.shiftStart);
        object.put("fWarehouseID", this.fWarehouseID);
        object.put("visible", this.visible);
        
        return object;
    }
    
    public static Worker getWorkerById(int id){
        EntityManager em = dbconnect.getDbConn();
        return em.find(Worker.class, id);
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getSName() {
        return sName;
    }

    public void setSName(String sName) {
        this.sName = sName;
    }

    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
    }

    public short getShiftLenght() {
        return shiftLenght;
    }

    public void setShiftLenght(short shiftLenght) {
        this.shiftLenght = shiftLenght;
    }

    public int getShiftStart() {
        return shiftStart;
    }

    public void setShiftStart(int shiftStart) {
        this.shiftStart = shiftStart;
    }

    public int getFWarehouseID() {
        return fWarehouseID;
    }

    public void setFWarehouseID(int fWarehouseID) {
        this.fWarehouseID = fWarehouseID;
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
        hash += (workerId != null ? workerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Worker)) {
            return false;
        }
        Worker other = (Worker) object;
        if ((this.workerId == null && other.workerId != null) || (this.workerId != null && !this.workerId.equals(other.workerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Worker[ workerId=" + workerId + " ]";
    }
    
}
