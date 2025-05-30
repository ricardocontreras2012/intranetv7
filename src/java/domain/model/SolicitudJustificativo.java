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
public class SolicitudJustificativo implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    Solicitud solicitud;
    Curso curso;
    SolicitudJustificativoId id;
    String soljEstado;
    String soljRespuesta;

    public SolicitudJustificativo() {
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

    public SolicitudJustificativoId getId() {
        return id;
    }

    public void setId(SolicitudJustificativoId id) {
        this.id = id;
    }

    public String getSoljEstado() {
        return soljEstado;
    }

    public void setSoljEstado(String soljEstado) {
        this.soljEstado = soljEstado;
    }

    public String getSoljRespuesta() {
        return soljRespuesta;
    }

    public void setSoljRespuesta(String soljRespuesta) {
        this.soljRespuesta = soljRespuesta;
    }
}
