/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.model;

import java.io.Serializable;
import java.util.Date;

public class TareaActividadTeletrabajoId implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer tatelRut;
    private Date tatelFecha;
    private Integer tatelTarea;

    public Integer getTatelRut() {
        return tatelRut;
    }

    public void setTatelRut(Integer tatelRut) {
        this.tatelRut = tatelRut;
    }

    public Date getTatelFecha() {
        return tatelFecha;
    }

    public void setTatelFecha(Date tatelFecha) {
        this.tatelFecha = tatelFecha;
    }

    public Integer getTatelTarea() {
        return tatelTarea;
    }

    public void setTatelTarea(Integer tatelTarea) {
        this.tatelTarea = tatelTarea;
    }
}
