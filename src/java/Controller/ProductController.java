
package Controller;

import Model.Product;
import Service.ProductService;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;


public class ProductController extends HttpServlet {

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        
        
        try (PrintWriter out = response.getWriter()) {
            
            if(request.getParameter("task").equals("add")){
                JSONObject returnValue = new JSONObject();
                
                if(!request.getParameter("ProductName").isEmpty() &&
                   !request.getParameter("Manufacturer").isEmpty() &&
                   !request.getParameter("Abv").isEmpty() &&
                   !request.getParameter("CurrStock").isEmpty() &&
                   !request.getParameter("MinStock").isEmpty())
                    {
                        String productname = request.getParameter("ProductName");
                        String manufacturer = request.getParameter("Manufacturer");
                        Short abv = Short.parseShort(request.getParameter("Abv"));
                        Integer currstock = Integer.parseInt(request.getParameter("CurrStock"));
                        Integer minstock = Integer.parseInt(request.getParameter("MinStock"));
                        Integer warehouseid = Integer.parseInt(request.getParameter("WarehouseID"));
                        
                        Product p = new Product(0,productname,manufacturer,abv,currstock,minstock,warehouseid,true);
                        
                        String result = ProductService.CProduct(p);
                        returnValue.put("result", result);
                    }
                else {
                    returnValue.put("result", "The data you input is incorrect!");
                }
                out.print(returnValue.toString());
            }
            
            if(request.getParameter("task").equals("RProduct")){
                JSONArray returnValue = new JSONArray();
                List<Product> products = ProductService.RProduct();
                
                if(products.isEmpty()){
                    JSONObject obj = new JSONObject();
                    obj.put("result", "There are no products!");
                    returnValue.put(obj);
                    out.print(returnValue.toString());
                } else {
                    for(Product p : products){
                        returnValue.put(p.toJson());
                    }
                    out.print(returnValue.toString());
                }
            }
            
            if(request.getParameter("task").equals("DProduct")){
                JSONObject returnValue = new JSONObject();
                if(!request.getParameter("id").isEmpty()){
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    
                    Product p = Product.getProductById(id);
                    
                    String result = ProductService.DProduct(p);
                    returnValue.put("result", result);
                } else {
                    returnValue.put("result", "The input data is incorrect!");
                }
                out.print(returnValue.toString());
            }
            
            if(request.getParameter("task").equals("UProduct")){
                JSONObject returnValue = new JSONObject();
                
                if(!request.getParameter("ProductName").isEmpty() &&
                   !request.getParameter("Manufacturer").isEmpty() &&
                   !request.getParameter("Abv").isEmpty() &&
                   !request.getParameter("CurrStock").isEmpty() &&
                   !request.getParameter("MinStock").isEmpty())
                    {
                        
                        Integer id = Integer.parseInt(request.getParameter("ProductId"));
                        String productname = request.getParameter("ProductName");
                        String manufacturer = request.getParameter("Manufacturer");
                        Short abv = Short.parseShort(request.getParameter("Abv"));
                        Integer currstock = Integer.parseInt(request.getParameter("CurrStock"));
                        Integer minstock = Integer.parseInt(request.getParameter("MinStock"));
                        Integer warehouseid = Integer.parseInt(request.getParameter("WarehouseID"));
                        
                        Product p = new Product(id,productname,manufacturer,abv,currstock,minstock,warehouseid,true);
                        
                        String result = ProductService.UProduct(p); 
                        returnValue.put("result", result);
                    
                    
                    } else {
                    returnValue.put("result", "The data you input is incorrect!");
                }
                
                out.print(returnValue.toString());
            }
            
            if(request.getParameter("task").equals("getProductByABV")){
                
                 JSONObject returnValue = new JSONObject();
                 
                try{
                    if(!request.getParameter("iAbv").isEmpty()){
                        
                        Short iAbv = Short.parseShort(request.getParameter("iAbv"));
                        
                        
                        Product p = new Product(0, null, null, iAbv, 0, 0, 0, true);
                        returnValue.put("result", ProductService.getProductByABV(p));                       
                    }else{
                        returnValue.put("result", "The input data must be correct!");
                    }
                  
                    
                    
                }catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
                //out.print(returnValue.toString());  
                out.print(returnValue.toString());
            
            }
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
