package ro.kepler.rominfo.dto;

import java.io.Serializable;

/**
 * Created by Dragos on 19.05.2017.
 */
public class LoginDto implements Serializable {
    private String email;
    private String password;

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
}
