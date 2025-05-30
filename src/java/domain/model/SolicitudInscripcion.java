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
public class SolicitudInscripcion implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    Solicitud solicitud;
    Curso curso;
    SolicitudInscripcionId id;
    TmotivoSolicitudInscripcion motivo;
    String soliOtroGlosa;
    String soliEstado;

    public SolicitudInscripcion() {
    }

    public SolicitudInscripcionId getId() {
        return id;
    }

    public void setId(SolicitudInscripcionId id) {
        this.id = id;
    }

    public TmotivoSolicitudInscripcion getMotivo() {
        return motivo;
    }

    public void setMotivo(TmotivoSolicitudInscripcion motivo) {
        this.motivo = motivo;
    }

    public String getSoliOtroGlosa() {
        return soliOtroGlosa;
    }

    public void setSoliOtroGlosa(String soliOtroGlosa) {
        this.soliOtroGlosa = soliOtroGlosa;
    }

    public String getSoliEstado() {
        return soliEstado;
    }

    public void setSoliEstado(String soliEstado) {
        this.soliEstado = soliEstado;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
