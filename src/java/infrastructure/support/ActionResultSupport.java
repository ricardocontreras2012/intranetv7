/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.support;

/**
 *
 * @author Ricardo
 */
public class ActionResultSupport {
    String actionStatus;
    String actionResult;
    String actionErrorMsg;

    public ActionResultSupport() {
    }

    public String getActionStatus() {
        return actionStatus;
    }

    public void setActionStatus(String actionStatus) {
        this.actionStatus = actionStatus;
    }

    public String getActionResult() {
        return actionResult;
    }

    public void setActionResult(String actionResult) {
        this.actionResult = actionResult;
    }

    public String getActionErrorMsg() {
        return actionErrorMsg;
    }

    public void setActionErrorMsg(String actionErrorMsg) {
        this.actionErrorMsg = actionErrorMsg;
    }
}
