package webapp;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.ArrayList;

public class DbConnection {
    
    private static final Logger log=LogManager.getLogger(DbConnection.class);
    
    private Connection connection;
    public void initateConnection(){
        log.trace("Entered DB Connection Initiation method");
        Properties properties = new Properties();
        log.trace("get class:"+this.getClass());
        log.trace("get classloader : " + this.getClass().getClassLoader());
        try(InputStream input = this.getClass().getClassLoader().getResourceAsStream("database.properties")){
        //new FileInputStream("./src/main/resources/database.properties")){
        properties.load(input);
        log.trace("properties file loaded");
        String url = properties.getProperty("db.url");
        String userName = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");
        String driverClassName = properties.getProperty("db.driverclassname");
        Class.forName(driverClassName);
        log.trace("url :"+url+" userName : "+userName+" password :" + password+" className"+ driverClassName);
        input.close();
        connection = DriverManager.getConnection(url, userName, password);
        System.out.println("Connection Established Successfully!!");
        
       }catch(IOException | ClassNotFoundException | SQLException e){
        e.printStackTrace();
        log.error("exception occured in db connection !:"+ e.getMessage());

       }
       log.trace("Leaving connection Initiation method ");
    }

    public void CloseConnection(){
        if(connection!=null){
            try {
                connection.close();
                System.out.println("Connection Closed!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public Boolean addUser(User user){
        log.trace("entered dbconnection adduser");
        try{
            String query = "INSERT INTO SAILPOINT.user(username, password, email, create_time) VALUES (?,?,?,?)";
            log.trace("query established");
            PreparedStatement statement =  connection.prepareStatement(query);
            log.trace("prepared statement established");
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            statement.setString(1,user.getUserName() );
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setTimestamp(4, timestamp);
            log.trace("statements are set");
            statement.executeUpdate();
            log.trace("user added");
            System.out.println("User Added");
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        return true; 

    }
    public List<User> getUsers(){
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM SAILPOINT.user ";
            ResultSet resultSet =null;
            resultSet= statement.executeQuery(query);
            while (resultSet.next()) {
                User user = new User();
                
                // Set fields in the POJO using ResultSet data
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setCreateTime(resultSet.getTimestamp("create_time"));
                // Add the POJO to the list
                users.add(user);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return users;
    }
}
