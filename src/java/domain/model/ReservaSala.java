/*
 * @(#)ReservaSala.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public class ReservaSala implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer rsalCorrel;
    private Integer rsalModulo;
    private String rsalDia;
    private String rsalMotivo;
    private Date rsalFecha;
    private Sala sala;
    private Date rsalFechaInicio;
    private Date rsalFechaTermino;
    private Administrativo administrativo;

    /**
     *
     */
    public ReservaSala() {
    }

    public Integer getRsalCorrel() {
        return rsalCorrel;
    }

    public void setRsalCorrel(Integer rsalCorrel) {
        this.rsalCorrel = rsalCorrel;
    }

    public String getRsalMotivo() {
        return rsalMotivo;
    }

    public void setRsalMotivo(String rsalMotivo) {
        this.rsalMotivo = rsalMotivo;
    }

    public Administrativo getAdministrativo() {
        return administrativo;
    }

    public void setAdministrativo(Administrativo administrativo) {
        this.administrativo = administrativo;
    } 

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Date getRsalFecha() {
        return rsalFecha;
    }

    public void setRsalFecha(Date rsalFecha) {
        this.rsalFecha = rsalFecha;
    }

    public Integer getRsalModulo() {
        return rsalModulo;
    }

    public void setRsalModulo(Integer rsalModulo) {
        this.rsalModulo = rsalModulo;
    }

    public Date getRsalFechaInicio() {
        return rsalFechaInicio;
    }

    public void setRsalFechaInicio(Date rsalFechaInicio) {
        this.rsalFechaInicio = rsalFechaInicio;
    }

    public Date getRsalFechaTermino() {
        return rsalFechaTermino;
    }

    public void setRsalFechaTermino(Date rsalFechaTermino) {
        this.rsalFechaTermino = rsalFechaTermino;
    }

    public String getRsalDia() {
        return rsalDia;
    }

    public void setRsalDia(String rsalDia) {
        this.rsalDia = rsalDia;
    }
    
    
}
