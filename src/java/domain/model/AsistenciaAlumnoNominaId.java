/*
 * @(#)AsistenciaAlumnoNominaId.java
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
public class AsistenciaAlumnoNominaId implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer aanAgnoIng;
    private Integer aanCodCar;
    private Integer aanCorrel;
    private Integer aanRut;
    private Integer aanSemIng;

    /**
     *
     */
    public AsistenciaAlumnoNominaId() {
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getAanAgnoIng() {
        return aanAgnoIng;
    }

    /**
     * Method description
     *
     * @param aanAgnoIng
     */
    public void setAanAgnoIng(Integer aanAgnoIng) {
        this.aanAgnoIng = aanAgnoIng;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getAanCodCar() {
        return aanCodCar;
    }

    /**
     * Method description
     *
     * @param aanCodCar
     */
    public void setAanCodCar(Integer aanCodCar) {
        this.aanCodCar = aanCodCar;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getAanCorrel() {
        return aanCorrel;
    }

    /**
     * Method description
     *
     * @param aanCorrel
     */
    public void setAanCorrel(Integer aanCorrel) {
        this.aanCorrel = aanCorrel;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getAanRut() {
        return aanRut;
    }

    /**
     * Method description
     *
     * @param aanRut
     */
    public void setAanRut(Integer aanRut) {
        this.aanRut = aanRut;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getAanSemIng() {
        return aanSemIng;
    }

    /**
     * Method description
     *
     * @param aanSemIng
     */
    public void setAanSemIng(Integer aanSemIng) {
        this.aanSemIng = aanSemIng;
    }
}
