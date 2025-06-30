/*
 * @(#)ParametroSesionSupport.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.support;

import java.io.Serializable;
import java.util.Date;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class ParametroSesionSupport implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer agnoAct;
    private Integer agnoCal;
    private Integer agnoEnc;
    private Integer agnoIns;    
    private String puedeInscribirMalla;
    private String puedeInscribirFormacionIntegral;
    private String puedeEliminar;
    private String puedeModificar;
    private String bloqueada;
    private Integer maxAsignInscritas;
    private Integer maxNivelAdelanto;
    private Integer semAct;
    private Integer semCal;
    private Integer semEnc;
    private Integer semIns;
    private Integer agnoMat;
    private Integer semMat;
    private Date terminoFechaCorte;

    /**
     * Method description
     *
     * @return
     */
    public Integer getAgnoIns() {
        return agnoIns;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getAgnoAct() {
        return agnoAct;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getSemAct() {
        return semAct;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getSemIns() {
        return semIns;
    }

    /**
     * Method description
     *
     * @param agnIns
     */
    public void setAgnoIns(Integer agnIns) {
        this.agnoIns = agnIns;
    }

    /**
     * Method description
     *
     * @param agnoAct
     */
    public void setAgnoAct(Integer agnoAct) {
        this.agnoAct = agnoAct;
    }

    /**
     * Method description
     *
     * @param semAct
     */
    public void setSemAct(Integer semAct) {
        this.semAct = semAct;
    }

    /**
     * Method description
     *
     * @param semIns
     */
    public void setSemIns(Integer semIns) {
        this.semIns = semIns;
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
     * @return
     */
    public Integer getMaxAsignInscritas() {
        return maxAsignInscritas;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getMaxNivelAdelanto() {
        return maxNivelAdelanto;
    }

    /**
     * Method description
     *
     * @param maxAsignInscritas
     */
    public void setMaxAsignInscritas(Integer maxAsignInscritas) {
        this.maxAsignInscritas = maxAsignInscritas;
    }

    /**
     * Method description
     *
     * @param maxNivelAdelanto
     */
    public void setMaxNivelAdelanto(Integer maxNivelAdelanto) {
        this.maxNivelAdelanto = maxNivelAdelanto;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getAgnoCal() {
        return agnoCal;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getSemCal() {
        return semCal;
    }

    /**
     * Method description
     *
     * @param agnoCal
     */
    public void setAgnoCal(Integer agnoCal) {
        this.agnoCal = agnoCal;
    }

    /**
     * Method description
     *
     * @param semCal
     */
    public void setSemCal(Integer semCal) {
        this.semCal = semCal;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getAgnoEnc() {
        return agnoEnc;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getSemEnc() {
        return semEnc;
    }

    /**
     * Method description
     *
     * @param agnoEnc
     */
    public void setAgnoEnc(Integer agnoEnc) {
        this.agnoEnc = agnoEnc;
    }

    /**
     * Method description
     *
     * @param semEnc
     */
    public void setSemEnc(Integer semEnc) {
        this.semEnc = semEnc;
    }

    /**
     *
     * @return
     */
    public Integer getAgnoMat() {
        return agnoMat;
    }

    /**
     *
     * @param agnoMat
     */
    public void setAgnoMat(Integer agnoMat) {
        this.agnoMat = agnoMat;
    }

    /**
     *
     * @return
     */
    public Integer getSemMat() {
        return semMat;
    }

    /**
     *
     * @param semMat
     */
    public void setSemMat(Integer semMat) {
        this.semMat = semMat;
    }

    public String getBloqueada() {
        return bloqueada;
    }

    public void setBloqueada(String bloqueada) {
        this.bloqueada = bloqueada;
    }

    public Date getTerminoFechaCorte() {
        return terminoFechaCorte;
    }

    public void setTerminoFechaCorte(Date terminoFechaCorte) {
        this.terminoFechaCorte = terminoFechaCorte;
    }
}
