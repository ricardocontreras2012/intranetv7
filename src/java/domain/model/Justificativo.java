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
public class Justificativo implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private AluCar aluCar;
    private Curso curso;
    private Tevaluacion tevaluacion;
    private Integer jusEval;
    private Date jusFecha;
    private String jusEstado;
    private String jusMotivo;
    private Integer jusSol;
    private Solicitud solicitud;

    public Justificativo() {
    }

    public AluCar getAluCar() {
        return aluCar;
    }

    public void setAluCar(AluCar aluCar) {
        this.aluCar = aluCar;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Tevaluacion getTevaluacion() {
        return tevaluacion;
    }

    public void setTevaluacion(Tevaluacion tevaluacion) {
        this.tevaluacion = tevaluacion;
    }

    public Integer getJusEval() {
        return jusEval;
    }

    public void setJusEval(Integer jusEval) {
        this.jusEval = jusEval;
    }

    public Date getJusFecha() {
        return jusFecha;
    }

    public void setJusFecha(Date jusFecha) {
        this.jusFecha = jusFecha;
    }

    public String getJusEstado() {
        return jusEstado;
    }

    public void setJusEstado(String jusEstado) {
        this.jusEstado = jusEstado;
    }

    public String getJusMotivo() {
        return jusMotivo;
    }

    public void setJusMotivo(String jusMotivo) {
        this.jusMotivo = jusMotivo;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public Integer getJusSol() {
        return jusSol;
    }

    public void setJusSol(Integer jusSol) {
        this.jusSol = jusSol;
    }
}
