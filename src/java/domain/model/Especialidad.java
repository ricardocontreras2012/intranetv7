/*
 * @(#)Especialidad.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.model;

//Generated 24-mar-2009 15:19:39 by Hibernate Tools 3.2.1.GA
import java.io.Serializable;

/**
 * Especialidad generated by hbm2java
 */
public class Especialidad implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer espCod;
    private String espNom;
    private String espJornada;

    /**
     *
     */
    public Especialidad() {
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getEspCod() {
        return this.espCod;
    }

    /**
     * Method description
     *
     * @param espCod
     */
    public void setEspCod(Integer espCod) {
        this.espCod = espCod;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getEspNom() {
        return this.espNom;
    }

    /**
     * Method description
     *
     * @param espNom
     */
    public void setEspNom(String espNom) {
        this.espNom = espNom;
    }

    public String getEspJornada() {
        return espJornada;
    }

    public void setEspJornada(String espJornada) {
        this.espJornada = espJornada;
    }
}
