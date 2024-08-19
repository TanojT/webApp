package webapp;

import java.util.HashSet;
import java.util.Set;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
public class App extends Application{
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(RestController.class); // Register your resource class here
        return classes;
    }
    public static void main(String[] args) {
        System.out.println("Hello WOrld!");
        RestController restController = new RestController();
       
        System.out.println( "Response: "+restController.getUsers().toString());
        
    }
    
}
