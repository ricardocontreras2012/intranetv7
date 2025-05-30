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
public class TmotivoSolicitudInscripcion implements Serializable {

    private static final long serialVersionUID = 1L;
    Integer tmsiCod;
    String tmsiDes;

    public TmotivoSolicitudInscripcion() {
    }

    public Integer getTmsiCod() {
        return tmsiCod;
    }

    public void setTmsiCod(Integer tmsiCod) {
        this.tmsiCod = tmsiCod;
    }

    public String getTmsiDes() {
        return tmsiDes;
    }

    public void setTmsiDes(String tmsiDes) {
        this.tmsiDes = tmsiDes;
    }

}
