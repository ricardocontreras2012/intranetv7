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
public class Labor implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer labCod;
    private String labDesMasc;
    private String labDesFem;
    private String labUser;

    public Labor() {
    }

    public Integer getLabCod() {
        return labCod;
    }

    public void setLabCod(Integer labCod) {
        this.labCod = labCod;
    }

    public String getLabDesMasc() {
        return labDesMasc;
    }

    public void setLabDesMasc(String labDesMasc) {
        this.labDesMasc = labDesMasc;
    }

    public String getLabDesFem() {
        return labDesFem;
    }

    public void setLabDesFem(String labDesFem) {
        this.labDesFem = labDesFem;
    }

    public String getLabUser() {
        return labUser;
    }

    public void setLabUser(String labUser) {
        this.labUser = labUser;
    }
}
