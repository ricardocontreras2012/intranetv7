/*
 * @(#)Dia.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.model;

import java.io.Serializable;

/**
 * Class description
 *
 * @author Alvaro Romero C.
 * @version 7, 06/05/2025
 */
public class EstadoDocExp implements Serializable {

    private static final long serialVersionUID = 1L;
    private EstadoDocExpId id;
    private ExpedienteLogro expedienteLogro;
    private TDocExpediente tDocExpediente;
    private Integer edeEstado;
    private String edeFile;
    private String edeObservacion;

    /**
     *
     */
    public EstadoDocExp() {
    }

    public EstadoDocExpId getId() {
        return id;
    }

    public void setId(EstadoDocExpId id) {
        this.id = id;
    }

    public ExpedienteLogro getExpedienteLogro() {
        return expedienteLogro;
    }

    public void setExpedienteLogro(ExpedienteLogro expedienteLogro) {
        this.expedienteLogro = expedienteLogro;
    }

    public TDocExpediente gettDocExpediente() {
        return tDocExpediente;
    }

    public void settDocExpediente(TDocExpediente tDocExpediente) {
        this.tDocExpediente = tDocExpediente;
    }

   

    public Integer getEdeEstado() {
        return edeEstado;
    }

    public void setEdeEstado(Integer edeEstado) {
        this.edeEstado = edeEstado;
    }

    public String getEdeFile() {
        return edeFile;
    }

    public void setEdeFile(String edeFile) {
        this.edeFile = edeFile;
    }

    public String getEdeObservacion() {
        return edeObservacion;
    }

    public void setEdeObservacion(String edeObservacion) {
        this.edeObservacion = edeObservacion;
    }
}
