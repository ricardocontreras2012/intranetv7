/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.support;

/**
 *
 * @author Ricardo Contreras S.
 */
public class UserEmailSupport {

    private String user;
    private String password;

    /**
     *
     */
    public UserEmailSupport() {
    }

    /**
     *
     * @param user
     * @param password
     */
    public UserEmailSupport(String user, String password) {
        this.user = user;
        this.password = password;
    }

    /**
     *
     * @return
     */
    public String getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
