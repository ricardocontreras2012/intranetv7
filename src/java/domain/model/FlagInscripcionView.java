/*
 * @(#)FlagInscripcionView.java
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
public class FlagInscripcionView implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer paramCar;
    private Integer paramMen;
    private String puedeInscribirMalla;
    private String puedeInscribirFormacionIntegral;
    private String puedeEliminar;
    private String puedeModificar;
    private String bloqueada;

    /**
     *
     */
    public FlagInscripcionView() {
    }

    public String getPuedeInscribirMalla() {
        return puedeInscribirMalla;
    }

    public void setPuedeInscribirMalla(String puedeInscribirMalla) {
        this.puedeInscribirMalla = puedeInscribirMalla;
    }

    public String getPuedeInscribirFormacionIntegral() {
        return puedeInscribirFormacionIntegral;
    }

    public void setPuedeInscribirFormacionIntegral(String puedeInscribirFormacionIntegral) {
        this.puedeInscribirFormacionIntegral = puedeInscribirFormacionIntegral;
    }

    public String getPuedeModificar() {
        return puedeModificar;
    }

    public void setPuedeModificar(String puedeModificar) {
        this.puedeModificar = puedeModificar;
    }

    public String getPuedeEliminar() {
        return puedeEliminar;
    }

    public void setPuedeEliminar(String puedeEliminar) {
        this.puedeEliminar = puedeEliminar;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getParamCar() {
        return paramCar;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getParamMen() {
        return paramMen;
    }

    /**
     * Method description
     *
     *
     * @param paramCar
     */
    public void setParamCar(Integer paramCar) {
        this.paramCar = paramCar;
    }

    /**
     * Method description
     *
     *
     * @param paramMen
     */
    public void setParamMen(Integer paramMen) {
        this.paramMen = paramMen;
    }

    public String getBloqueada() {
        return bloqueada;
    }

    public void setBloqueada(String bloqueada) {
        this.bloqueada = bloqueada;
    }    
}
