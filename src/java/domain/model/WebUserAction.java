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
public class WebUserAction implements Serializable {

    private static final long serialVersionUID = 1L;
    private WebUserActionId id;
    private WebUser webUser;
    private WebAction webAction;

    public WebUserAction() {
    }

    public WebUserActionId getId() {
        return id;
    }

    public void setId(WebUserActionId id) {
        this.id = id;
    }

    public WebUser getWebUser() {
        return webUser;
    }

    public void setWebUser(WebUser webUser) {
        this.webUser = webUser;
    }

    public WebAction getWebAction() {
        return webAction;
    }

    public void setWebAction(WebAction webAction) {
        this.webAction = webAction;
    }
}
