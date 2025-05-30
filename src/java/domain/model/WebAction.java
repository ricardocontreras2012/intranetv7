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
public class WebAction implements Serializable {

    private static final long serialVersionUID = 1L;

    public WebAction() {
    }

    private String waAction;
    private String waDes;
    private String waLog;

    public String getWaAction() {
        return waAction;
    }

    public void setWaAction(String waAction) {
        this.waAction = waAction;
    }

    public String getWaDes() {
        return waDes;
    }

    public void setWaDes(String waDes) {
        this.waDes = waDes;
    }

    public String getWaLog() {
        return waLog;
    }

    public void setWaLog(String waLog) {
        this.waLog = waLog;
    }

}
