/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Product;
import Model.Warehouse;
import Service.ProductService;
import Service.WarehouseService;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author wellm
 */
@WebServlet(name = "WarehouseController", urlPatterns = {"/WarehouseController"})
public class WarehouseController extends HttpServlet {

        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        
        
        try (PrintWriter out = response.getWriter()) {
            //CWarehouse
            if(request.getParameter("task").equals("add")){
                JSONObject returnValue = new JSONObject();
                
                if(!request.getParameter("City").isEmpty() &&
                   !request.getParameter("Address").isEmpty())
                    {
                        Integer city = Integer.parseInt(request.getParameter("City"));
                        String address = request.getParameter("Address");
                        
                       // Integer warehouseid = Integer.parseInt(request.getParameter("WarehouseID"));
                        
                        Warehouse w = new Warehouse(0, city, address, true);
                        
                        String result = WarehouseService.CWarehouse(w);
                        returnValue.put("result", result);
                    }
                else {
                    returnValue.put("result", "The data you input is incorrect!");
                }
                out.print(returnValue.toString());
            }
            
            //RWarehouse
            if(request.getParameter("task").equals("RWarehouse")){
                JSONArray returnValue = new JSONArray();
                List<Warehouse> warehouses = WarehouseService.GWarehouse();
                
                if(warehouses.isEmpty()) {
                    JSONObject obj = new JSONObject();
                    obj.put("result", "There are no warehouses!");
                    returnValue.put(obj);
                    out.print(returnValue.toString());
                } else {
                    for(Warehouse w : warehouses){
                      returnValue.put(w.toJson());
                      
                    }
                    out.print(returnValue.toString());
                }
                
                
            }
            
            //logic delete
            if(request.getParameter("task").equals("DWarehouse")){
                JSONObject returnValue = new JSONObject();
                //id validation
                     // getWarehouseByID
                     
                if(!request.getParameter("WarehouseID").isEmpty()){
                    
                    Integer warehouseID = Integer.parseInt(request.getParameter("WarehouseID"));
                    Warehouse w = Warehouse.getWarehouseByID(warehouseID);
                    
                    String result = WarehouseService.DWarehouse(w);
                    returnValue.put("result", result);
                }  else {
                    returnValue.put("result", "The Id you input is incorrect!");
                }
                out.print(returnValue.toString());
            }
            
            if(request.getParameter("task").equals("UWarehouse")){
                JSONObject returnValue = new JSONObject();
                
                if(!request.getParameter("City").isEmpty() &&
                   !request.getParameter("Address").isEmpty() &&
                   !request.getParameter("Id").isEmpty())
                    {
                        
                        Integer Id = Integer.parseInt(request.getParameter("Id"));
                        Integer city = Integer.parseInt(request.getParameter("City"));
                        String address = request.getParameter("Address");
                        
                       
                        
                        Warehouse w = new Warehouse(Id, city, address, true);
                        
                        String result = WarehouseService.UWarehouse(w);
                        returnValue.put("result", result);
                    
                    
                    } else {
                    returnValue.put("result", "The data you input is incorrect!");
                }
                
                out.print(returnValue.toString());
            }
            
            if(request.getParameter("task").equals("getWarehouseByCity")){
                
                 JSONObject returnValue = new JSONObject();
                 
                try{
                    if(!request.getParameter("cityID").isEmpty()){
                        
                        Integer cityID = Integer.parseInt(request.getParameter("cityID"));
                        
                        Warehouse w = new Warehouse(0,cityID,null, true);
                        returnValue.put("result", WarehouseService.getWarehouseByCity(w));                       
                    }else{
                        returnValue.put("result", "The input data must be correct!");
                    }
                  
                    
                    
                }catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
                out.print(returnValue.toString());  
                
            
            }   
        } 
        catch( Exception ex ){
               System.out.print(ex.getMessage());
               System.out.print("json ex");
            }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
