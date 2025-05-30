/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.support;

import java.util.Date;

/**
 *
 * @author Administrador
 */
public class TeleTrabajoSupport {
    
    Date fecha;
    String estado;

    public TeleTrabajoSupport() {
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}