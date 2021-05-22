/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import Model.Product;
import Model.Warehouse;
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
public class WarehouseRepo {
     public static boolean CWarehouse(Warehouse w){
        
        try{
            EntityManager em = dbconnect.getDbConn();
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("CWarehouse");
            
          
            spq.registerStoredProcedureParameter("City", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("Address", String.class, ParameterMode.IN);
           
            
            
            spq.setParameter("City", w.getCity());
            spq.setParameter("Address", w.getAddress());
      
           
            
            spq.execute();
            em.close();
            return true;
                    
            
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
        
    }
     
    public static List<Warehouse> RWarehouse() {
        
        List<Warehouse> result = new ArrayList();
        
        try{
            EntityManager em = dbconnect.getDbConn();
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("RWarehouse");
            
            List<Object[]> warehouses = spq.getResultList();
            
            for(Object[] warehouse : warehouses){
                int id = Integer.parseInt(warehouse[0].toString());
                Warehouse w = em.find(Warehouse.class, id);
                result.add(w);
            }
            
            
        } catch (Exception ex) {
            System.out.print(ex);
        } finally {
            return result;
        }
        
        
        
    }
    
    public static boolean DWarehouse(Warehouse w){
        try{
            EntityManager em = dbconnect.getDbConn();
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("DWarehouse");
            
            spq.registerStoredProcedureParameter("WarehouseID", Integer.class, ParameterMode.IN);
            
            spq.setParameter("WarehouseID", w.getId());
            
            spq.execute();
            em.close();
            
            return true;
        } catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    
    public static boolean UWarehouse(Warehouse winput){
        try{
            EntityManager em = dbconnect.getDbConn();
            em.getTransaction().begin();
            
            Warehouse nWare = em.find(Warehouse.class, winput.getId());
            
            nWare.setCity(winput.getCity());
            nWare.setAddress(winput.getAddress());
            
            
            
            em.getTransaction().commit();
            return true;
        } catch (Exception ex){
            return false;
        }
    }
    
    public static List<Warehouse> getWarehouseByCity(Warehouse w){
        List<Warehouse> result = new ArrayList();
        try{
            EntityManager em = dbconnect.getDbConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getWarehouseByCity");
            
            spq.registerStoredProcedureParameter("cityID", Integer.class, ParameterMode.IN);
            System.out.print(w.getCity());
            spq.setParameter("cityID", w.getCity());
            
            List<Object[]> WarehouseObjectList = spq.getResultList();
     
            
            for (Object[] WarehouseObject : WarehouseObjectList){
                Integer id = Integer.parseInt(WarehouseObject[0].toString());
                Integer city = Integer.parseInt(WarehouseObject[1].toString());
                String address = WarehouseObject[2].toString();
                
                
    
                Warehouse entity = new Warehouse(id,city,address,true);
               // WarehouseList.add(entity);    
                result.add(entity);
            }
            
            System.out.print(spq.getResultList());
            em.close();
            return result;
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
            
    
        
        
        
    


