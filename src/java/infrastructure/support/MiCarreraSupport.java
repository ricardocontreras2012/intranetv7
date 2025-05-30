/*
 * @(#)MiCarreraSupport.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.support;

import java.util.Objects;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class MiCarreraSupport {

    private Integer espCod;
    private String regimen;
    private String nombreCarrera;
    private Integer tcrCtip;

    /**
     *
     */
    public MiCarreraSupport() {
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getEspCod() {
        return espCod;
    }

    /**
     * Method description
     *
     * @param espCod
     */
    public void setEspCod(Integer espCod) {
        this.espCod = espCod;
    }

    public String getRegimen() {
        return regimen;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getNombreCarrera() {
        return nombreCarrera;
    }

    /**
     * Method description
     *
     * @param nombreCarrera
     */
    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getTcrCtip() {
        return tcrCtip;
    }

    /**
     * Method description
     *
     * @param tcrCtip
     */
    public void setTcrCtip(Integer tcrCtip) {
        this.tcrCtip = tcrCtip;
    }

    /**
     * Method description
     *
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof MiCarreraSupport
                && Objects.equals(nombreCarrera, ((MiCarreraSupport) other).nombreCarrera));
    }

    @Override
    public int hashCode() {
        return Objects.hash(espCod, tcrCtip);
    }
}
