package webapp;

import java.sql.Timestamp;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;


public class UserService {
    private static final Logger log=LogManager.getLogger(UserService.class);

    private DbConnection dbConnection;

    

    public String getUsers() {
        log.trace("Entering this getUsers method");
        dbConnection = new DbConnection();
        dbConnection.initateConnection();
        List<User> users= dbConnection.getUsers();
        dbConnection.CloseConnection();
        ObjectMapper objectMapper = new ObjectMapper();
        String response = "";
        try {
            log.debug("writing users as:"+users);
            response = objectMapper.writeValueAsString(users);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error occured in getUsers method db close:"+e.getMessage());
        }
        
        log.trace("Exiting this getUsers method with response:"+response);
        return response;

    }

    public String addUser(User user) {
        log.trace("Entered add User");
        dbConnection = new DbConnection();
        dbConnection.initateConnection();
        Boolean isRecordAdded = dbConnection.addUser(user);
        dbConnection.CloseConnection();
        if(isRecordAdded){
        
            return "User added to Database";
        }

        return "Error occured while adding user to the database";

    }
    public String saveUser(User user){
        UserDAO userDAO =  new UserDAO();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        user.setCreateTime(timestamp);
        userDAO.save(user);
        return "user saved";
    }
    public String fetchUser(){
        UserDAO userDAO =  new UserDAO();
        log.trace("Entering this getUsers method");
        
        List<User> users= userDAO.getUsers();
        ObjectMapper objectMapper = new ObjectMapper();
        String response = "";
        try {
            log.debug("writing users as:"+users);
            response = objectMapper.writeValueAsString(users);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error occured in getUsers method db close:"+e.getMessage());
        }
        
        log.trace("Exiting this getUsers method with response:"+response);
        return response;
    }
}
