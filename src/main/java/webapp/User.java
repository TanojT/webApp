package webapp;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String userName;
    private String email;
    private String password;
    private Timestamp createTime;

    public User(String userName, String email, String password, Timestamp createTime) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.createTime = createTime;
    }
    public int getId() {
        return id;
	}

   public void setId(int id){
        this.id =id;
   }

    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public Timestamp getCreateTime() {
        return createTime;
    }


    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }


    public User(String userName,String email,String password){
        this.userName=userName;
        this.email=email;
        this.password=password;
        this.createTime=new Timestamp(0);
    }

    
    public User() {
    }



    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", userName='" + getUserName() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            "}";
    }


	
}
