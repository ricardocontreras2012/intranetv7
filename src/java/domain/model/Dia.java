/*
 * @(#)Dia.java
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
public class Dia implements Serializable {

    private static final long serialVersionUID = 1L;
    private String diaCod;
    private Integer diaCorrel;
    private String diaNom;
    private String diaClases;

    /**
     *
     */
    public Dia() {
    }

    /**
     * Method description
     *
     * @return
     */
    public String getDiaCod() {
        return diaCod;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getDiaNom() {
        return diaNom;
    }

    /**
     * Method description
     *
     * @param diaCod
     */
    public void setDiaCod(String diaCod) {
        this.diaCod = diaCod;
    }

    /**
     * Method description
     *
     * @param diaNom
     */
    public void setDiaNom(String diaNom) {
        this.diaNom = diaNom;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getDiaCorrel() {
        return diaCorrel;
    }

    /**
     * Method description
     *
     * @param diaCorrel
     */
    public void setDiaCorrel(Integer diaCorrel) {
        this.diaCorrel = diaCorrel;
    }

    public String getDiaClases() {
        return diaClases;
    }

    public void setDiaClases(String diaClases) {
        this.diaClases = diaClases;
    }
}
