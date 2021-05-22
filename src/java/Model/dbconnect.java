package Model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class dbconnect {
    
    public static EntityManager getDbConn(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DrinkWholesalePU");
        EntityManager em = emf.createEntityManager();
        return em;
    }
    
    
    
    
    
}
