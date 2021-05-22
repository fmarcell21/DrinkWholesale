/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.Worker;
import Repository.WorkerRepo;
import java.util.List;

/**
 *
 * @author wellm
 */
public class WorkerService {
    public static String CWorker(Worker w){
        if(!(w.getFName()).isEmpty() && !(w.getSName()).isEmpty()){
            if(WorkerRepo.CWorker(w)){
                return "The worker was successfully added!";
            }
            else{
                return "The data was correct, however it wasn't added to the database!";
            }
        }
        else{
            return "The input data must be correct!";
        }
    }
    
    public static List<Worker> RWorker(){
        return WorkerRepo.RWorker();
    }
    
    public static String DWorker(Worker w){
        if(WorkerRepo.DWorker(w)){
                return "The worker was successfully deleted!";
            }
            else{
                return "Error! Worker wasn't deleted!";
            }
    }
    
    public static String UWorker(Worker w){
           if(!(w.getFName()).isEmpty() && !(w.getSName()).isEmpty()){
            
            if(WorkerRepo.UWorker(w)){
                
                return "Worker updated successfully!";
                
            } else {
                 
                return "The data was correct, however there was an error adding it to the database!";
            }
        }
        else {
             
            return "The input data must be correcr!";
        } 
       }
    
    public static List<Worker> getWorkerByWarehouse(Worker w){
        return WorkerRepo.getWorkerByWarehouse(w);
    }
}
