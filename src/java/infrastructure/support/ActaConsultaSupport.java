/*
 * @(#)ActaConsultaSupport.java
 *
 * Copyright (c) 2016 FAE-USACH
 */
package infrastructure.support;

import domain.model.ActaCalificacionId;
import domain.model.Curso;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class ActaConsultaSupport {

    private Integer acalAsign;
    private String acalCoord;
    private String acalElect;
    private Integer acalSecc;
    private String acalTipo;
    private Integer admRut;
    private Curso curso;
    private String estado;
    private ActaCalificacionId id;
    private String nombreCurso;
    private String profesores;

    /**
     * Method description
     *
     * @return
     */
    public Integer getAdmRut() {
        return admRut;
    }

    /**
     * Method description
     *
     * @param admRut
     */
    public void setAdmRut(Integer admRut) {
        this.admRut = admRut;
    }

    /**
     * Method description
     *
     * @return
     */
    public Curso getCurso() {
        return curso;
    }

    /**
     * Method description
     *
     * @param curso
     */
    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Method description
     *
     * @param estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Method description
     *
     * @return
     */
    public ActaCalificacionId getId() {
        return id;
    }

    /**
     * Method description
     *
     * @param id
     */
    public void setId(ActaCalificacionId id) {
        this.id = id;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getNombreCurso() {
        return nombreCurso;
    }

    /**
     * Method description
     *
     * @param nombreCurso
     */
    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getProfesores() {
        return profesores;
    }

    /**
     * Method description
     *
     * @param profesores
     */
    public void setProfesores(String profesores) {
        this.profesores = profesores;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getAcalAsign() {
        return acalAsign;
    }

    /**
     * Method description
     *
     * @param acalAsign
     */
    public void setAcalAsign(Integer acalAsign) {
        this.acalAsign = acalAsign;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getAcalCoord() {
        return acalCoord;
    }

    /**
     * Method description
     *
     * @param acalCoord
     */
    public void setAcalCoord(String acalCoord) {
        this.acalCoord = acalCoord;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getAcalElect() {
        return acalElect;
    }

    /**
     * Method description
     *
     * @param acalElect
     */
    public void setAcalElect(String acalElect) {
        this.acalElect = acalElect;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getAcalSecc() {
        return acalSecc;
    }

    /**
     * Method description
     *
     * @param acalSecc
     */
    public void setAcalSecc(Integer acalSecc) {
        this.acalSecc = acalSecc;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getAcalTipo() {
        return acalTipo;
    }

    /**
     * Method description
     *
     * @param acalTipo
     */
    public void setAcalTipo(String acalTipo) {
        this.acalTipo = acalTipo;
    }
}
