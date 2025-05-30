/*
 * @(#)EvaluacionAlumno.java
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
public class EvaluacionAlumno implements Serializable {
    private static final long serialVersionUID = 1L;

    private AluCar aluCar;
    private Integer evaluCorrel;
    private BigDecimal evaluNota;
    private Evaluacion evaluacion;

    /**
     *
     */
    public EvaluacionAlumno() {
    }

    /**
     * Method description
     *
     * @return
     */
    public BigDecimal getEvaluNota() {
        return evaluNota;
    }

    /**
     * Method description
     *
     * @param evaluNota
     */
    public void setEvaluNota(BigDecimal evaluNota) {
        this.evaluNota = evaluNota;
    }

    /**
     * Method description
     *
     * @return
     */
    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    /**
     * Method description
     *
     * @param evaluacion
     */
    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }

    /**
     * Method description
     *
     * @return
     */
    public AluCar getAluCar() {
        return aluCar;
    }

    /**
     * Method description
     *
     * @param aluCar
     */
    public void setAluCar(AluCar aluCar) {
        this.aluCar = aluCar;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getEvaluCorrel() {
        return evaluCorrel;
    }

    /**
     * Method description
     *
     * @param evaluCorrel
     */
    public void setEvaluCorrel(Integer evaluCorrel) {
        this.evaluCorrel = evaluCorrel;
    }
}
