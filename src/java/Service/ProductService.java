/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.*;
import Repository.ProductRepo;
import java.util.List;

/**
 *
 * @author wellm
 */
public class ProductService {
    
    
    public static String CProduct(Product p){
        
        if(!(p.getProductName().isEmpty()) && (p.getProductName().length() < 101) && !(p.getManufacturer().isEmpty()) && (p.getManufacturer().length() < 101)){
            
            if(ProductRepo.CProduct(p)){
                return "Product added successfully!";
            } else {
                return "The data was correct, however there was an error adding it to the database!";
            } 
        }
        else {
            return "Both the ProductName and Manufacturer must fall between the given lenght: 1-100";
        } 
    
    }
    
    public static List<Product> RProduct(){
        return ProductRepo.RProduct();
    }
    
    public static String DProduct(Product p){
        if(ProductRepo.DProduct(p)) {
            return "The product was successfully deleted!";
        } else {
            return "Error! The product wasn't deleted";
        }
    }
    
    public static String UProduct(Product p){
        if(!(p.getProductName().isEmpty()) && (p.getProductName().length() < 101) && !(p.getManufacturer().isEmpty()) && (p.getManufacturer().length() < 101) && !(p.getProductId() == 0)){
            
            if(ProductRepo.UProduct(p)){
                return "Product updated successfully!";
            } else {
                return "The data was correct, however there was an error updating it in the database!";
            } 
        }
        else {
            return "The data you input was incorrect!";
        } 
        
    }
    
    public static List<Product> getProductByABV(Product p){
        return ProductRepo.getProductByABV(p);
    }
}
