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
public class AyudanteHorario implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer ahorId;
    private Curso curso;
    private CursoActualView cursoActual;
    private Ayudante ayudante;
    private String ahorDia;
    private Integer ahorModulo;
    private Character ahorTipo;

    public AyudanteHorario() {
    }

    public Integer getAhorId() {
        return ahorId;
    }

    public void setAhorId(Integer ahorId) {
        this.ahorId = ahorId;
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

    public Ayudante getAyudante() {
        return ayudante;
    }

    public void setAyudante(Ayudante ayudante) {
        this.ayudante = ayudante;
    }

    public Character getAhorTipo() {
        return ahorTipo;
    }

    public void setAhorTipo(Character ahorTipo) {
        this.ahorTipo = ahorTipo;
    }

    public String getAhorDia() {
        return ahorDia;
    }

    public void setAhorDia(String ahorDia) {
        this.ahorDia = ahorDia;
    }

    public Integer getAhorModulo() {
        return ahorModulo;
    }

    public void setAhorModulo(Integer ahorModulo) {
        this.ahorModulo = ahorModulo;
    }
    
    
}
