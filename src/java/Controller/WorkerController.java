/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Worker;
import Service.WorkerService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author wellm
 */
public class WorkerController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            //Add
            if(request.getParameter("task").equals("add")){
                JSONObject returnValue = new JSONObject();
                
                if(!request.getParameter("FName").isEmpty() &&  !request.getParameter("SName").isEmpty() && !request.getParameter("ShiftLenght").isEmpty() && !request.getParameter("ShiftStart").isEmpty() && !request.getParameter("FWarehouseID").isEmpty()){
                    
                    String FName = request.getParameter("FName");
                    String SName = request.getParameter("SName");
                    
                    Short ShiftLenght  = Short.parseShort(request.getParameter("ShiftLenght"));
                    Integer ShiftStart = Integer.parseInt(request.getParameter("ShiftStart"));
                    Integer FWarehouseID = Integer.parseInt(request.getParameter("FWarehouseID"));
                    
                    
                    
                    
                    Worker w = new Worker(0,FName,SName,ShiftLenght,ShiftStart,FWarehouseID,true);
                    
                    String result = WorkerService.CWorker(w);
                    returnValue.put("result", result);
                }
                else{
                    returnValue.put("result", "The input data is incorrect!");
                }
                out.print(returnValue.toString());
            }
            
            if(request.getParameter("task").equals("RWorker")){
                JSONArray returnValue = new JSONArray();
                List<Worker> worker = WorkerService.RWorker();
                
                if(worker.isEmpty()){
                    JSONObject obj = new JSONObject();
                    obj.put("result", "There are no workers!");
                    returnValue.put(obj);
                    out.print(returnValue.toString());
                }
                else{
                    for(Worker w : worker){
                        returnValue.put(w.toJson());
                        
                    }
                    out.print(returnValue.toString());
                }
                
                
            }
            if(request.getParameter("task").equals("DWorker")){
                JSONObject returnValue = new JSONObject();
              
                if(!request.getParameter("id").isEmpty()){
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    
                    Worker w = Worker.getWorkerById(id);
                    
                    String result = WorkerService.DWorker(w);
                    returnValue.put("result", result);
                }
                else{
                    returnValue.put("result", "The input data must be correct!");
                }
                out.print(returnValue.toString());
            }
            
            if(request.getParameter("task").equals("UWorker")){
                JSONObject returnValue = new JSONObject();
                
                if(!request.getParameter("FName").isEmpty() &&  !request.getParameter("SName").isEmpty() && !request.getParameter("ShiftLenght").isEmpty() && !request.getParameter("ShiftStart").isEmpty() && !request.getParameter("FWarehouseID").isEmpty())
                    {
                        
                        String FName = request.getParameter("FName");
                        String SName = request.getParameter("SName");
                        
                        Integer id = Integer.parseInt(request.getParameter("WorkerId"));
                        Short ShiftLenght  = Short.parseShort(request.getParameter("ShiftLenght"));
                        Integer ShiftStart = Integer.parseInt(request.getParameter("ShiftStart"));
                        Integer FWarehouseID = Integer.parseInt(request.getParameter("FWarehouseID"));

                        
                        Worker w = new Worker(id,FName,SName,ShiftLenght,ShiftStart,FWarehouseID,true);
                    
                        String result = WorkerService.UWorker(w);
                        returnValue.put("result", result);
                    
                    
                    } else {
                    returnValue.put("result", "The data you input is incorrect!");
                }
                
                out.print(returnValue.toString());
            }
            
            if(request.getParameter("task").equals("getWorkerByWarehouse")){
                
                 JSONObject returnValue = new JSONObject();
                 
                try{
                    if(!request.getParameter("whID").isEmpty()){
                        
                        Integer whid = Integer.parseInt(request.getParameter("whID"));
                        Short shiftlenght = Short.parseShort("0");
                        
                        Worker w = new Worker(0,null, null, shiftlenght, 0, whid, true);
                        returnValue.put("result", WorkerService.getWorkerByWarehouse(w));                       
                    }else{
                        returnValue.put("result", "The input data must be correct!");
                    }
                  
                    
                    
                }catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
                out.print(returnValue.toString());  
                
            
            }
            
            
            
            
            
            
            
            
    }catch(Exception ex){
            System.out.print(ex);
            System.out.print("JSON exception van");
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
