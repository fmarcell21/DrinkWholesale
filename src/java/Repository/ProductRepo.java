/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import Model.dbconnect;
import Model.Product;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author wellm
 */
public class ProductRepo {
    public static boolean CProduct(Product p){
        
        try{
            EntityManager em = dbconnect.getDbConn();
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("CProduct");
            
            spq.registerStoredProcedureParameter("ProductName", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("Manufacturer", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("Abv", Short.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("CurrStock", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("MinStock", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("WarehouseID", Integer.class, ParameterMode.IN);
            
            spq.setParameter("ProductName", p.getProductName());
            spq.setParameter("Manufacturer", p.getManufacturer());
            spq.setParameter("Abv", p.getAbv());
            spq.setParameter("CurrStock", p.getCurrStock());
            spq.setParameter("MinStock", p.getMinStock());
            spq.setParameter("WarehouseID", p.getWarehouseID());
            
            spq.execute();
            return true;
                    
            
        }
        
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
        
    }
    
    public static List<Product> RProduct(){
        List<Product> result = new ArrayList();
        try{
            EntityManager em = dbconnect.getDbConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("RProduct");
            
            List<Object[]> products = spq.getResultList();
            
            for(Object[] product : products){
                int id = Integer.parseInt(product[5].toString());
                Product p = em.find(Product.class, id);
                result.add(p);
            } 
        } catch(Exception ex){
            System.out.print(ex);
        } finally {
            return result;
        }
    }
    
    public static boolean DProduct(Product p){
        try{
            EntityManager em = dbconnect.getDbConn();

            StoredProcedureQuery spq = em.createStoredProcedureQuery("DProduct");

            spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);

            spq.setParameter("idIN", p.getProductId());

            spq.execute();
            return true;
        } catch(Exception ex){
             return false;
        }
    }
    
    public static boolean UProduct(Product pinput){
        try{
            EntityManager em = dbconnect.getDbConn();
            em.getTransaction().begin();
            
            Product nProd = em.find(Product.class, pinput.getProductId());
            
            nProd.setAbv(pinput.getAbv());
            nProd.setCurrStock(pinput.getCurrStock());
            nProd.setManufacturer(pinput.getManufacturer());
            nProd.setMinStock(pinput.getMinStock());
            nProd.setProductName(pinput.getProductName());
            nProd.setWarehouseID(pinput.getWarehouseID());
            /*
            pinput.setCity(pinput.getCity());
            pinput.setAddress(pinput.getAddress());
            */
            
            
            em.getTransaction().commit();
            return true;
        } catch (Exception ex){
            return false;
        }
    }
    
    public static List<Product> getProductByABV(Product p){
        List<Product> result = new ArrayList();
        try{
            EntityManager em = dbconnect.getDbConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getProductByABV");
            
            spq.registerStoredProcedureParameter("iAbv", Integer.class, ParameterMode.IN);
  
            spq.setParameter("iAbv", p.getAbv());
           // System.out.print(p.getAbv());
            
            
            List<Object[]> ProductObjectList = spq.getResultList();
     
            
            for (Object[] ProductObject : ProductObjectList){
                
                String ProductName = ProductObject[0].toString();
                String Manufacturer = ProductObject[1].toString();
                Short Abv = Short.parseShort(ProductObject[2].toString());
                Integer CurrStock = Integer.parseInt(ProductObject[3].toString());
                Integer MinStock = Integer.parseInt(ProductObject[4].toString());
                Integer ProductId = Integer.parseInt(ProductObject[5].toString());
                Integer WarehouseId = Integer.parseInt(ProductObject[6].toString());
                
                //System.out.print(Manufacturer);
                
                
    
                Product entity = new Product(ProductId, ProductName, Manufacturer, Abv, CurrStock, MinStock, WarehouseId ,true);
               // System.out.print(entity);
               // WarehouseList.add(entity);    
                result.add(entity);
            }
            
            //System.out.print(spq.getResultList());
            em.close();
            return result;
            
        }catch(Exception ex){
           
            return null;
        }
    }
}


