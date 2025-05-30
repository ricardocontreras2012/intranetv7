/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author rcontreras
 */
public class ConvalidacionSolicitudAsign implements Serializable {

    private static final long serialVersionUID = 1L;
    private ConvalidacionSolicitudAsignId id;
    private ConvalidacionSolicitud solicitud;
    private Asignatura asignatura;
    private String csaCursada;
    private String csaElectivo;
    private String csaEstado;
    private String csaObs;
    private BigDecimal csaNota;

    public ConvalidacionSolicitudAsign() {
    }

    public ConvalidacionSolicitudAsignId getId() {
        return id;
    }

    public void setId(ConvalidacionSolicitudAsignId id) {
        this.id = id;
    }

    public ConvalidacionSolicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(ConvalidacionSolicitud solicitud) {
        this.solicitud = solicitud;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public String getCsaCursada() {
        return csaCursada;
    }

    public void setCsaCursada(String csaCursada) {
        this.csaCursada = csaCursada;
    }

    public String getCsaElectivo() {
        return csaElectivo;
    }

    public void setCsaElectivo(String csaElectivo) {
        this.csaElectivo = csaElectivo;
    }

    public String getCsaEstado() {
        return csaEstado;
    }

    public void setCsaEstado(String csaEstado) {
        this.csaEstado = csaEstado;
    }

    public String getCsaObs() {
        return csaObs;
    }

    public void setCsaObs(String csaObs) {
        this.csaObs = csaObs;
    }

    public BigDecimal getCsaNota() {
        return csaNota;
    }

    public void setCsaNota(BigDecimal csaNota) {
        this.csaNota = csaNota;
    }
}
