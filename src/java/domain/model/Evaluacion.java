/*
 * @(#)Evaluacion.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public class Evaluacion implements Serializable {

    private static final long serialVersionUID = 1L;
    private CursoTevaluacion cursoTevaluacion;
    private BigDecimal evalPorc;
    private String evalStatus;
    private EvaluacionId id;
    private String evalLabel;

    /**
     *
     */
    public Evaluacion() {
    }

    /**
     * Method description
     *
     * @return
     */
    public CursoTevaluacion getCursoTevaluacion() {
        return cursoTevaluacion;
    }

    /**
     * Method description
     *
     * @param cursoTevaluacion
     */
    public void setCursoTevaluacion(CursoTevaluacion cursoTevaluacion) {
        this.cursoTevaluacion = cursoTevaluacion;
    }

    public BigDecimal getEvalPorc() {
        return evalPorc;
    }

    public void setEvalPorc(BigDecimal evalPorc) {
        this.evalPorc = evalPorc;
    }

    /**
     * Method description
     *
     * @return
     */
    public EvaluacionId getId() {
        return id;
    }

    /**
     * Method description
     *
     * @param id
     */
    public void setId(EvaluacionId id) {
        this.id = id;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getEvalStatus() {
        return evalStatus;
    }

    /**
     * Method description
     *
     * @param evalStatus
     */
    public void setEvalStatus(String evalStatus) {
        this.evalStatus = evalStatus;
    }

    public String getEvalLabel() {
        return evalLabel;
    }

    public void setEvalLabel(String evalLabel) {
        this.evalLabel = evalLabel;
    }
}
