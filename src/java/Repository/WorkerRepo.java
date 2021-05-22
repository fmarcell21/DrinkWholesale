/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import Model.Worker;
import Model.dbconnect;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author wellm
 */
public class WorkerRepo {
    
    public static boolean CWorker(Worker w){
        try{
            EntityManager em = dbconnect.getDbConn();
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("CWorker");
        
            spq.registerStoredProcedureParameter("FName", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("SName", String.class, ParameterMode.IN);
           // spq.registerStoredProcedureParameter("WorkerId", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("ShiftLenght", Short.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("ShiftStart", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("FWarehouseID", Integer.class, ParameterMode.IN);
           // spq.registerStoredProcedureParameter("Visible", Integer.class, ParameterMode.IN);
            
            spq.setParameter("FName", w.getFName());
            spq.setParameter("SName", w.getSName());
           // spq.setParameter("WorkerId", w.getWorkerId());
            spq.setParameter("ShiftLenght", w.getShiftLenght());
            spq.setParameter("ShiftStart", w.getShiftStart());
            spq.setParameter("FWarehouseID", w.getFWarehouseID());
          //  spq.setParameter("statusIN", w.getVisible());
            
            spq.execute();
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            System.out.print(ex);
            return false;
        }
    }
    
    public static List<Worker> RWorker(){
        List<Worker> result = new ArrayList();
        try{
            EntityManager em = dbconnect.getDbConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("RWoker");
            
            List<Object[]> workers = spq.getResultList();
            for(Object[] worker : workers){
                int id = Integer.parseInt(worker[2].toString());
                Worker w = em.find(Worker.class, id);
                result.add(w);
            }
        }
        catch(Exception ex){
        }
        finally{
            return result;
        }
        
    
    }
    public static boolean DWorker(Worker w){
        try{
            EntityManager em = dbconnect.getDbConn();
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("DWorker");
        
            spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("idIN", w.getWorkerId());
            
            spq.execute();
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
        
    }
    
    
    public static boolean UWorker(Worker winput){
        try{
            EntityManager em = dbconnect.getDbConn();
            em.getTransaction().begin();
            
            Worker nWrk = em.find(Worker.class, winput.getWorkerId());
            
            nWrk.setFName(winput.getFName());
            nWrk.setSName(winput.getSName());
            nWrk.setFWarehouseID(winput.getFWarehouseID());
            nWrk.setShiftLenght(winput.getShiftLenght());
            nWrk.setShiftStart(winput.getShiftStart());
            
            
            /*nWare.setCity(winput.getCity());
            nWare.setAddress(winput.getAddress());
            */
            
            
            em.getTransaction().commit();
            return true;
        } catch (Exception ex){
            return false;
        }
    }
    
    public static List<Worker> getWorkerByWarehouse(Worker w){
        List<Worker> result = new ArrayList();
        try{
            EntityManager em = dbconnect.getDbConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getWorkerByWarehouse");
            
            spq.registerStoredProcedureParameter("whID", Integer.class, ParameterMode.IN);
            System.out.print(w.getFWarehouseID());
            spq.setParameter("whID", w.getFWarehouseID());
            
            List<Object[]> WorkerObjectList = spq.getResultList();
     
            
            for (Object[] WorkerObject : WorkerObjectList){
                
                String fname = WorkerObject[0].toString();
                String sname = WorkerObject[1].toString();
                Integer workerid = Integer.parseInt(WorkerObject[2].toString());
                Short shiftlength = Short.parseShort(WorkerObject[3].toString());
                Integer shiftstart = Integer.parseInt(WorkerObject[4].toString());
                Integer fwarehouseid = Integer.parseInt(WorkerObject[5].toString());
                
                
                
                
                
    
                Worker entity = new Worker(workerid, fname, sname, shiftlength, shiftstart, fwarehouseid, true);
               // WarehouseList.add(entity);    
                result.add(entity);
            }
            
           // System.out.print(spq.getResultList());
            em.close();
            return result;
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    
}
