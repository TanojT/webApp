package webapp;

import java.sql.Timestamp;

public class User{

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


    public String toString(){
        return "Current user email:"+this.email+", current username:"+this.userName;
    }
}
