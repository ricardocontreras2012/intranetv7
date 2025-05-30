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
public class Tlogro implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer tloCod;
    private String tloDes;
    private String tloCorto;
    private String tloProc;
    private String tloDesLarga;
    private String tloDesLargaPlural;

    public Tlogro() {
    }

    public Integer getTloCod() {
        return tloCod;
    }

    public void setTloCod(Integer tloCod) {
        this.tloCod = tloCod;
    }

    public String getTloDes() {
        return tloDes;
    }

    public void setTloDes(String tloDes) {
        this.tloDes = tloDes;
    }

    public String getTloCorto() {
        return tloCorto;
    }

    public void setTloCorto(String tloCorto) {
        this.tloCorto = tloCorto;
    }

    public String getTloProc() {
        return tloProc;
    }

    public void setTloProc(String tloProc) {
        this.tloProc = tloProc;
    } 

    public String getTloDesLarga() {
        return tloDesLarga;
    }

    public void setTloDesLarga(String tloDesLarga) {
        this.tloDesLarga = tloDesLarga;
    }

    public String getTloDesLargaPlural() {
        return tloDesLargaPlural;
    }

    public void setTloDesLargaPlural(String tloDesLargaPlural) {
        this.tloDesLargaPlural = tloDesLargaPlural;
    }
}
