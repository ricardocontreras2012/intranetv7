/*
 * @(#)CursoTevaluacion.java
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
public class CursoTevaluacion implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer ctevaNumEval;
    private Integer ctevaPorc;
    private Curso curso;
    private CursoTevaluacionId id;
    private Tevaluacion tevaluacion;

    /**
     *
     */
    public CursoTevaluacion() {
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getCtevaNumEval() {
        return ctevaNumEval;
    }

    /**
     * Method description
     *
     * @param ctevaNumEval
     */
    public void setCtevaNumEval(Integer ctevaNumEval) {
        this.ctevaNumEval = ctevaNumEval;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getCtevaPorc() {
        return ctevaPorc;
    }

    /**
     * Method description
     *
     * @param ctevaPorc
     */
    public void setCtevaPorc(Integer ctevaPorc) {
        this.ctevaPorc = ctevaPorc;
    }

    /**
     * Method description
     *
     * @return
     */
    public CursoTevaluacionId getId() {
        return id;
    }

    /**
     * Method description
     *
     * @param id
     */
    public void setId(CursoTevaluacionId id) {
        this.id = id;
    }

    /**
     * Method description
     *
     * @return
     */
    public Tevaluacion getTevaluacion() {
        return tevaluacion;
    }

    /**
     * Method description
     *
     * @param tevaluacion
     */
    public void setTevaluacion(Tevaluacion tevaluacion) {
        this.tevaluacion = tevaluacion;
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
