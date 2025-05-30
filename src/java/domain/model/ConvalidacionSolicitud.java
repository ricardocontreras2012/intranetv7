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
 * @author rcontreras
 */
public class ConvalidacionSolicitud implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer cosCorrel;
    private AluCar aluCar;
    private ConvalidacionComision comision;
    private Date cosFecha;
    private Integer cosAgno;
    private Integer cosSem;
private String cosEstado;

    public ConvalidacionSolicitud() {
    }

    public Integer getCosCorrel() {
        return cosCorrel;
    }

    public void setCosCorrel(Integer cosCorrel) {
        this.cosCorrel = cosCorrel;
    }

    public AluCar getAluCar() {
        return aluCar;
    }

    public void setAluCar(AluCar aluCar) {
        this.aluCar = aluCar;
    }

    public ConvalidacionComision getComision() {
        return comision;
    }

    public void setComision(ConvalidacionComision comision) {
        this.comision = comision;
    }

    public Date getCosFecha() {
        return cosFecha;
    }

    public void setCosFecha(Date cosFecha) {
        this.cosFecha = cosFecha;
    }

    public Integer getCosAgno() {
        return cosAgno;
    }

    public void setCosAgno(Integer cosAgno) {
        this.cosAgno = cosAgno;
    }

    public Integer getCosSem() {
        return cosSem;
    }

    public void setCosSem(Integer cosSem) {
        this.cosSem = cosSem;
    }

    public String getCosEstado() {
        return cosEstado;
    }

    public void setCosEstado(String cosEstado) {
        this.cosEstado = cosEstado;
    }
}
