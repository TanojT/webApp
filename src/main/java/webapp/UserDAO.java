package webapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UserDAO {
    private User user;
    private Configuration config;
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;
    
    private static final Logger log=LogManager.getLogger(UserDAO.class);
    
    private void beginSession(){
        try {
            log.trace("Begin Session started");
            config= new Configuration().configure().addAnnotatedClass(User.class);
            sessionFactory = config.buildSessionFactory();
            session= sessionFactory.openSession();
            transaction =  session.beginTransaction();
            log.trace("Session started succefully returning to original function");
        } catch (Exception e) {
            log.error("Error occured in setting up the session");
        }
        
    }

    public void save(User user){
        log.trace("Entered save User");
        this.beginSession(); 
        session.persist(user);
        this.closeSession();
        log.trace("User saved successfully");
    }

    private void closeSession(){
        log.trace("Entered close Session");
        transaction.commit();
        session.close();
        log.trace("Closed session successfully");
    }
    public List<User> getUsers(){
        log.trace("Entered get Users");
        this.beginSession(); 
        List<User> users = session.createQuery("FROM User", User.class).list();
        this.closeSession();
        log.trace("User fetched successfully");
        return users;
    }
    public List<User> findAllUsers() {
        return session.createQuery("SELECT * FROM User ", User.class).getResultList();      
    }
}
