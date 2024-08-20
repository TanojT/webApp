package webapp;

import java.sql.Timestamp;
import java.util.Map;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/v1")
public class RestController{
   
    private UserService  userService = new UserService();

    @GET
    @Produces("text/plain")
    @Path("/temp")
    public String Hello(){
       // UserService userService = new UserService();
        return "Hello World";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getUsers")
    public String getUsers(){
        //String response = userService.getUsers();
        String response = userService.fetchUser();
        return response;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/addUser")
    public String addUser(User user){
        //return userService.addUser(user);
        return userService.saveUser(user);
    }

     @POST
     @Consumes(MediaType.APPLICATION_JSON)
     @Produces(MediaType.APPLICATION_JSON)
     @Path("/addUsers")
     public String addUsers(Map<String,String> user){
         User tempUser=new User(user.get("userName"),user.get("email"),user.get("password"),new Timestamp(System.currentTimeMillis()));
         // return userService.addUser(tempUser);
         return userService.saveUser(tempUser);
     }
}