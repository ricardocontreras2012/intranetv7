/*
 * @(#)Regimen.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.model;

//Generated 24-mar-2009 15:19:39 by Hibernate Tools 3.2.1.GA
import java.io.Serializable;

/**
 * Regimen generated by hbm2java
 */
public class Regimen implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer regCiclosAnual;
    private Integer regCod;
    private String regNom;

    /**
     *
     */
    public Regimen() {
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getRegCod() {
        return this.regCod;
    }

    /**
     * Method description
     *
     * @param regCod
     */
    public void setRegCod(Integer regCod) {
        this.regCod = regCod;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getRegNom() {
        return this.regNom;
    }

    /**
     * Method description
     *
     * @param regNom
     */
    public void setRegNom(String regNom) {
        this.regNom = regNom;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getRegCiclosAnual() {
        return this.regCiclosAnual;
    }

    /**
     * Method description
     *
     * @param regCiclosAnual
     */
    public void setRegCiclosAnual(Integer regCiclosAnual) {
        this.regCiclosAnual = regCiclosAnual;
    }
}
