package chimp.service.security;

import chimp.service.ServiceMessage;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (12/5/6, 19:44)
 */
public class LoginServiceMessage implements ServiceMessage {

    private String username;
    private String password;

    public LoginServiceMessage(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
