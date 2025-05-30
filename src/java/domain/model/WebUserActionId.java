/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.model;

import java.io.Serializable;

/**
 *
 * @author Ricardo
 */
public class WebUserActionId implements Serializable {

    private static final long serialVersionUID = 1L;

    public WebUserActionId() {
    }

    private String wuaUser;
    private String wuaAction;

    public String getWuaUser() {
        return wuaUser;
    }

    public void setWuaUser(String wuaUser) {
        this.wuaUser = wuaUser;
    }

    public String getWuaAction() {
        return wuaAction;
    }

    public void setWuaAction(String wuaAction) {
        this.wuaAction = wuaAction;
    }

}
