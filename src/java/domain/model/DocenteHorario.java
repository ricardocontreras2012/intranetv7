/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.model;

import java.io.Serializable;

/**
 *
 * @author Administrador
 */
public class DocenteHorario implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer dhorId;
    private Curso curso;
    private CursoActualView cursoActual;
    private Profesor profesor;
    private Ayudante ayudante;
    private String dhorDia;
    private Integer dhorModulo;
    private String dhorTipo;

    public DocenteHorario() {
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public CursoActualView getCursoActual() {
        return cursoActual;
    }

    public void setCursoActual(CursoActualView cursoActual) {
        this.cursoActual = cursoActual;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Integer getDhorId() {
        return dhorId;
    }

    public void setDhorId(Integer dhorId) {
        this.dhorId = dhorId;
    }

    public Ayudante getAyudante() {
        return ayudante;
    }

    public void setAyudante(Ayudante ayudante) {
        this.ayudante = ayudante;
    }

    public String getDhorDia() {
        return dhorDia;
    }

    public void setDhorDia(String dhorDia) {
        this.dhorDia = dhorDia;
    }

    public Integer getDhorModulo() {
        return dhorModulo;
    }

    public void setDhorModulo(Integer dhorModulo) {
        this.dhorModulo = dhorModulo;
    }

    public String getDhorTipo() {
        return dhorTipo;
    }

    public void setDhorTipo(String dhorTipo) {
        this.dhorTipo = dhorTipo;
    }
}
