/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.model;

import java.io.Serializable;
import java.util.Date;

public class EvidenciaTareaActTeletrabajoId implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer etatelRut;
    private Date etatelFecha;
    private Integer etatelTarea;
    private Integer etatelCorrel;

    public Integer getEtatelRut() {
        return etatelRut;
    }

    public void setEtatelRut(Integer etatelRut) {
        this.etatelRut = etatelRut;
    }

    public Date getEtatelFecha() {
        return etatelFecha;
    }

    public void setEtatelFecha(Date etatelFecha) {
        this.etatelFecha = etatelFecha;
    }

    public Integer getEtatelTarea() {
        return etatelTarea;
    }

    public void setEtatelTarea(Integer etatelTarea) {
        this.etatelTarea = etatelTarea;
    }

    public Integer getEtatelCorrel() {
        return etatelCorrel;
    }

    public void setEtatelCorrel(Integer etatelCorrel) {
        this.etatelCorrel = etatelCorrel;
    }
}