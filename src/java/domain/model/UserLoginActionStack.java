/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.model;

/**
 *
 * @author Ricardo
 */
public class UserLoginActionStack {
    private static final long serialVersionUID = 1L;

    private UserLoginActionStackId id;
    private String ulasAction;

    public UserLoginActionStack() {
    }

    public UserLoginActionStackId getId() {
        return id;
    }

    public void setId(UserLoginActionStackId id) {
        this.id = id;
    }

    public String getUlasAction() {
        return ulasAction;
    }

    public void setUlasAction(String ulasAction) {
        this.ulasAction = ulasAction;
    }
}
