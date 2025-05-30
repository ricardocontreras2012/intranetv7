/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.model;

import java.util.Date;

/**
 *
 * @author Ricardo Contreras S.
 */
public class LogAction {

    private Date logDate;
    private String logMessage;
    private Integer logSeq;

    /**
     *
     */
    public LogAction() {
    }

    /**
     *
     * @return
     */
    public Date getLogDate() {
        return logDate;
    }

    /**
     *
     * @param logDate
     */
    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    /**
     *
     * @return
     */
    public String getLogMessage() {
        return logMessage;
    }

    /**
     *
     * @param logMessage
     */
    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }

    /**
     *
     * @return
     */
    public Integer getLogSeq() {
        return logSeq;
    }

    /**
     *
     * @param logSeq
     */
    public void setLogSeq(Integer logSeq) {
        this.logSeq = logSeq;
    }
}
