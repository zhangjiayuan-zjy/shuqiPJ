package Bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
    private String email;
    private String userName;
    private String pass;
    private int UID;
    private int state;
    private String dateJoined;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public int getUID() {
        return UID;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setDateJoined(String date) {
        this.dateJoined = date;
        /*DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.dateJoined=dateFormat.format(date);*/
    }

    public String getDateJoined() {
        return dateJoined;
    }
}

