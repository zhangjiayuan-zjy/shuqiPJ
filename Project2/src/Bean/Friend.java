package Bean;

public class Friend {
    private int UID;
    private String userName;
    private String email;
    private int state;

    public void setUID(int UID) {
        this.UID = UID;
    }

    public int getUID() {
        return UID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }
}
