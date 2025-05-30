/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.model;

import java.io.Serializable;

/**
 *
 * @author rcontreras
 */
public class Logro implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer logrCod;
    private String logrDes;
    private Tlogro tlogro;
    private Integer logrValor;

    public Logro() {
    }

    public Integer getLogrCod() {
        return logrCod;
    }

    public void setLogrCod(Integer logrCod) {
        this.logrCod = logrCod;
    }

    public String getLogrDes() {
        return logrDes;
    }

    public void setLogrDes(String logrDes) {
        this.logrDes = logrDes;
    }

    public Tlogro getTlogro() {
        return tlogro;
    }

    public void setTlogro(Tlogro tlogro) {
        this.tlogro = tlogro;
    }

    public Integer getLogrValor() {
        return logrValor;
    }

    public void setLogrValor(Integer logrValor) {
        this.logrValor = logrValor;
    }    
}
