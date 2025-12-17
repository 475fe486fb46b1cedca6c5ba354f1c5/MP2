package at.tuw.mp2.DAOs;

public class LoginDAO {
    String user;
    String pw;

    public LoginDAO(String user, String pw) {
        this.user = user;
        this.pw = pw;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
}
