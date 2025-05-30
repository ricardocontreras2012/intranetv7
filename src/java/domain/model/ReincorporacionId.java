/*
 * @(#)ReincorporacionId.java
 *
 * Creado por: Ricardo Contreras S.
 * Fecha Actualizacion: 17/07/2014
 *
 * License agreement: Uso exclusivo por FAE
 * Copyright (c) 2025 FAE-USACH
 */
package domain.model;

import java.io.Serializable;

/**
 *
 * @author Ricardo Contreras S.
 */
public class ReincorporacionId implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer reiAgno;
    private Integer reiAgnoIng;
    private Integer reiCodCar;
    private Integer reiRut;
    private Integer reiSem;
    private Integer reiSemIng;

    /**
     *
     */
    public ReincorporacionId() {
    }

    /**
     *
     * @return
     */
    public Integer getReiAgnoIng() {
        return reiAgnoIng;
    }

    /**
     *
     * @param reiAgnoIng
     */
    public void setReiAgnoIng(Integer reiAgnoIng) {
        this.reiAgnoIng = reiAgnoIng;
    }

    /**
     *
     * @return
     */
    public Integer getReiCodCar() {
        return reiCodCar;
    }

    /**
     *
     * @param reiCodCar
     */
    public void setReiCodCar(Integer reiCodCar) {
        this.reiCodCar = reiCodCar;
    }

    /**
     *
     * @return
     */
    public Integer getReiRut() {
        return reiRut;
    }

    /**
     *
     * @param reiRut
     */
    public void setReiRut(Integer reiRut) {
        this.reiRut = reiRut;
    }

    /**
     *
     * @return
     */
    public Integer getReiSemIng() {
        return reiSemIng;
    }

    /**
     *
     * @param reiSemIng
     */
    public void setReiSemIng(Integer reiSemIng) {
        this.reiSemIng = reiSemIng;
    }

    /**
     *
     * @return
     */
    public Integer getReiAgno() {
        return reiAgno;
    }

    /**
     *
     * @param reiAgno
     */
    public void setReiAgno(Integer reiAgno) {
        this.reiAgno = reiAgno;
    }

    /**
     *
     * @return
     */
    public Integer getReiSem() {
        return reiSem;
    }

    /**
     *
     * @param reiSem
     */
    public void setReiSem(Integer reiSem) {
        this.reiSem = reiSem;
    }
}
