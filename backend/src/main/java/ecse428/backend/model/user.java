package ecse428.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class user {

    private String username;
    private String phonenumber;


    @Id
    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPhonenumber(){
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber){
        this.phonenumber = phonenumber;
    }



}
