/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.model;

import java.util.Date;

/**
 *
 * @author rcontreras
 */
public class ExpedienteNomina implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private Integer expnCorrel;
    private Integer expnLogro;
    private String expnNumero;
    private Integer expnAgno;
    private Date expnFecha;

    public Integer getExpnCorrel() {
        return expnCorrel;
    }

    public void setExpnCorrel(Integer expnCorrel) {
        this.expnCorrel = expnCorrel;
    }

    public Integer getExpnLogro() {
        return expnLogro;
    }

    public void setExpnLogro(Integer expnLogro) {
        this.expnLogro = expnLogro;
    }

    public String getExpnNumero() {
        return expnNumero;
    }

    public void setExpnNumero(String expnNumero) {
        this.expnNumero = expnNumero;
    }

    public Integer getExpnAgno() {
        return expnAgno;
    }

    public void setExpnAgno(Integer expnAgno) {
        this.expnAgno = expnAgno;
    }

    public Date getExpnFecha() {
        return expnFecha;
    }

    public void setExpnFecha(Date expnFecha) {
        this.expnFecha = expnFecha;
    }
}
