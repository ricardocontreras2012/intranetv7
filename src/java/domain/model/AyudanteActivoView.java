/*
 * @(#)AyudanteActivoView.java
 *
 * Copyright (c) 2019 FAE-USACH
 */
package domain.model;

import java.io.Serializable;

/**
 * Class description
 *
 *
 * @version 7, 30/04/2014
 * @author Ricardo Contreras S.
 */
public class AyudanteActivoView implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer carrera;
    private Integer depto;
    private Integer facultad;
    private String email;
    private Integer mencion;
    private Integer rut;

    /**
     *
     */
    public AyudanteActivoView() {
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getCarrera() {
        return carrera;
    }

    /**
     * Method description
     *
     *
     * @param carrera
     */
    public void setCarrera(Integer carrera) {
        this.carrera = carrera;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getDepto() {
        return depto;
    }

    /**
     * Method description
     *
     *
     * @param depto
     */
    public void setDepto(Integer depto) {
        this.depto = depto;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getMencion() {
        return mencion;
    }

    /**
     * Method description
     *
     *
     * @param mencion
     */
    public void setMencion(Integer mencion) {
        this.mencion = mencion;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getRut() {
        return rut;
    }

    /**
     * Method description
     *
     *
     * @param rut
     */
    public void setRut(Integer rut) {
        this.rut = rut;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method description
     *
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getFacultad() {
        return facultad;
    }

    public void setFacultad(Integer facultad) {
        this.facultad = facultad;
    }
}
