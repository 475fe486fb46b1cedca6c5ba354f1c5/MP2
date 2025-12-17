package at.tuw.mp2.DAOs;

public class RegisterDAO {
    String user;
    String pw;
    String pwrep;

    public RegisterDAO(String user, String pw, String pwrep) {
        this.user = user;
        this.pw = pw;
        this.pwrep = pwrep;
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

    public String getPwrep() {
        return pwrep;
    }

    public void setPwrep(String pwrep) {
        this.pwrep = pwrep;
    }
}
