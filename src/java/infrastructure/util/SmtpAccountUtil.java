/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.util;

/**
 *
 * @author Ricardo
 */
public class SmtpAccountUtil {
    private final String username;
    private final String password;

    public SmtpAccountUtil(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }

    public String getPassword() { return password; }
}