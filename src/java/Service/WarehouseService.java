/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.Warehouse;
import Repository.WarehouseRepo;
import java.util.List;

/**
 *
 * @author wellm
 */
public class WarehouseService {
       public static String CWarehouse(Warehouse w){
        //
        if(w.getCity() > 0 && !w.getAddress().isEmpty()){
            
            if(WarehouseRepo.CWarehouse(w)){
                
                return "Warehouse added successfully!";
                
            } else {
                 
                return "The data was correct, however there was an error adding it to the database!";
            }
        }
        else {
             
            return "Both the Address and City zip must be correct!";
        } 
    
    }
       
       public static List<Warehouse> GWarehouse(){
           
           return WarehouseRepo.RWarehouse();
           
           
       }
       
       public static String DWarehouse(Warehouse w){
           if(WarehouseRepo.DWarehouse(w)){
               return "The warehouse was successfully deleted!";
           } else {
               return "Error! The warehouse wasn't deleted!";
           }
       }
       
       public static String UWarehouse(Warehouse w){
           if(w.getCity() > 0 && !w.getAddress().isEmpty()){
            
            if(WarehouseRepo.UWarehouse(w)){
                
                return "Warehouse updated successfully!";
                
            } else {
                 
                return "The data was correct, however there was an error adding it to the database!";
            }
        }
        else {
             
            return "Both the Address and City zip must be correct!";
        } 
       }
       
       public static List<Warehouse> getWarehouseByCity(Warehouse w){
        return WarehouseRepo.getWarehouseByCity(w);
    }
       
       
}
