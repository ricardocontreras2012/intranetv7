/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Ricardo
 */
public class SolicitudCertificado implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer scertCorrel;
    private AluCar aluCar;
    private Integer scertMonto;
    private Date scertFecha;
    private String scertEstado;

    public SolicitudCertificado() {
    }

    public Integer getScertCorrel() {
        return scertCorrel;
    }

    public void setScertCorrel(Integer scertCorrel) {
        this.scertCorrel = scertCorrel;
    }

    public AluCar getAluCar() {
        return aluCar;
    }

    public void setAluCar(AluCar aluCar) {
        this.aluCar = aluCar;
    }

    public Integer getScertMonto() {
        return scertMonto;
    }

    public void setScertMonto(Integer scertMonto) {
        this.scertMonto = scertMonto;
    }

    public Date getScertFecha() {
        return scertFecha;
    }

    public void setScertFecha(Date scertFecha) {
        this.scertFecha = scertFecha;
    }

    public String getScertEstado() {
        return scertEstado;
    }

    public void setScertEstado(String scertEstado) {
        this.scertEstado = scertEstado;
    }
}
