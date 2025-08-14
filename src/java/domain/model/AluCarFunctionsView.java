/*
 * @(#)AluCarFunctionsView.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
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
public class AluCarFunctionsView implements Serializable {

    private static final long serialVersionUID = 1L;
    private Float factorAvance;
    private Float factorEficiencia;
    private AluCarId id;
    private Integer nivel;
    private String progresion;
    private Float promedio;
    private Float promedioAprobados;
    private Integer reprobaciones;
    private String nombreFacultad;
    private Integer unidadFacultad;
    private Integer flagNuevo;

    /**
     * Method description
     *
     *
     * @return
     */
    public Float getFactorAvance() {
        return factorAvance;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Float getFactorEficiencia() {
        return factorEficiencia;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public AluCarId getId() {
        return id;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getNivel() {
        return nivel;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Float getPromedio() {
        return promedio;
    }

    /**
     * Method description
     *
     *
     * @param factorAvance
     */
    public void setFactorAvance(Float factorAvance) {
        this.factorAvance = factorAvance;
    }

    /**
     * Method description
     *
     *
     * @param factorEficiencia
     */
    public void setFactorEficiencia(Float factorEficiencia) {
        this.factorEficiencia = factorEficiencia;
    }

    /**
     * Method description
     *
     *
     * @param id
     */
    public void setId(AluCarId id) {
        this.id = id;
    }

    /**
     * Method description
     *
     *
     * @param nivel
     */
    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    /**
     * Method description
     *
     *
     * @param promedio
     */
    public void setPromedio(Float promedio) {
        this.promedio = promedio;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Float getPromedioAprobados() {
        return promedioAprobados;
    }

    /**
     * Method description
     *
     *
     * @param promedioAprobados
     */
    public void setPromedioAprobados(Float promedioAprobados) {
        this.promedioAprobados = promedioAprobados;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getReprobaciones() {
        return reprobaciones;
    }

    /**
     * Method description
     *
     *
     * @param reprobaciones
     */
    public void setReprobaciones(Integer reprobaciones) {
        this.reprobaciones = reprobaciones;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getProgresion() {
        return progresion;
    }

    /**
     * Method description
     *
     *
     * @param progresion
     */
    public void setProgresion(String progresion) {
        this.progresion = progresion;
    }

    /**
     *
     * @return
     */
    public String getNombreFacultad() {
        return nombreFacultad;
    }

    /**
     *
     * @param nombreFacultad
     */
    public void setNombreFacultad(String nombreFacultad) {
        this.nombreFacultad = nombreFacultad;
    }

    /**
     *
     * @return
     */
    public Integer getUnidadFacultad() {
        return unidadFacultad;
    }

    /**
     *
     * @param unidadFacultad
     */
    public void setUnidadFacultad(Integer unidadFacultad) {
        this.unidadFacultad = unidadFacultad;
    }

    public Integer getFlagNuevo() {
        return flagNuevo;
    }

    public void setFlagNuevo(Integer flagNuevo) {
        this.flagNuevo = flagNuevo;
    }
}
