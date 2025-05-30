/*
 * @(#)CursoProfesor.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.model;

import java.io.Serializable;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public class CursoProfesor implements Serializable {

    private static final long serialVersionUID = 1L;
    private Curso curso;
    private CursoActualView cursoActual;
    private CursoProfesorId id;
    private Profesor profesor;

    /**
     * Method description
     *
     * @return
     */
    public CursoProfesorId getId() {
        return id;
    }

    /**
     * Method description
     *
     * @param id
     */
    public void setId(CursoProfesorId id) {
        this.id = id;
    }

    /**
     * Method description
     *
     * @return
     */
    public Profesor getProfesor() {
        return profesor;
    }

    /**
     * Method description
     *
     * @param profesor
     */
    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    /**
     * Method description
     *
     * @return
     */
    public CursoActualView getCursoActual() {
        return cursoActual;
    }

    /**
     * Method description
     *
     * @param cursoActual
     */
    public void setCursoActual(CursoActualView cursoActual) {
        this.cursoActual = cursoActual;
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
}
