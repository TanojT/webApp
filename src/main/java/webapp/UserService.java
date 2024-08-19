package webapp;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;


public class UserService {
    private static final Logger log=LogManager.getLogger(UserService.class);

    private DbConnection dbConnection;

    public void createUser(){
        
    }

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
}
