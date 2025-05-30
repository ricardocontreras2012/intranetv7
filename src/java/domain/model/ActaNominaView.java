/*
 * @(#)ActaNominaView.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Ricardo Contreras S.
 */
public class ActaNominaView implements Serializable {

    private static final long serialVersionUID = 1L;
    private String acanConcep;
    private BigDecimal acanFinal;
    private Integer acalAgno;
    private Integer acalAsign;
    private String acalCoord;
    private String acalElect;
    private String acalEst;
    private Integer acalFolio;
    private Integer acalSecc;
    private Integer acalSem;
    private String aluDv;
    private String aluMaterno;
    private String aluNombre;
    private String aluPaterno;
    private Integer aluRut;
    private Integer acaCodCar;
    private Integer acaCodMen;

    /**
     *
     */
    public ActaNominaView() {
    }

    /**
     *
     * @return
     */
    public String getAcanConcep() {
        return acanConcep;
    }

    /**
     *
     * @param acanConcep
     */
    public void setAcanConcep(String acanConcep) {
        this.acanConcep = acanConcep;
    }

    /**
     *
     * @return
     */
    public BigDecimal getAcanFinal() {
        return acanFinal;
    }

    /**
     *
     * @param acanFinal
     */
    public void setAcanFinal(BigDecimal acanFinal) {
        this.acanFinal = acanFinal;
    }

    /**
     *
     * @return
     */
    public Integer getAcalAgno() {
        return acalAgno;
    }

    /**
     *
     * @param acalAgno
     */
    public void setAcalAgno(Integer acalAgno) {
        this.acalAgno = acalAgno;
    }

    /**
     *
     * @return
     */
    public Integer getAcalAsign() {
        return acalAsign;
    }

    /**
     *
     * @param acalAsign
     */
    public void setAcalAsign(Integer acalAsign) {
        this.acalAsign = acalAsign;
    }

    /**
     *
     * @return
     */
    public String getAcalCoord() {
        return acalCoord;
    }

    /**
     *
     * @param acalCoord
     */
    public void setAcalCoord(String acalCoord) {
        this.acalCoord = acalCoord;
    }

    /**
     *
     * @return
     */
    public String getAcalElect() {
        return acalElect;
    }

    /**
     *
     * @param acalElect
     */
    public void setAcalElect(String acalElect) {
        this.acalElect = acalElect;
    }

    /**
     *
     * @return
     */
    public String getAcalEst() {
        return acalEst;
    }

    /**
     *
     * @param acalEst
     */
    public void setAcalEst(String acalEst) {
        this.acalEst = acalEst;
    }

    /**
     *
     * @return
     */
    public Integer getAcalFolio() {
        return acalFolio;
    }

    /**
     *
     * @param acalFolio
     */
    public void setAcalFolio(Integer acalFolio) {
        this.acalFolio = acalFolio;
    }

    /**
     *
     * @return
     */
    public Integer getAcalSecc() {
        return acalSecc;
    }

    /**
     *
     * @param acalSecc
     */
    public void setAcalSecc(Integer acalSecc) {
        this.acalSecc = acalSecc;
    }

    /**
     *
     * @return
     */
    public Integer getAcalSem() {
        return acalSem;
    }

    /**
     *
     * @param acalSem
     */
    public void setAcalSem(Integer acalSem) {
        this.acalSem = acalSem;
    }

    /**
     *
     * @return
     */
    public String getAluDv() {
        return aluDv;
    }

    /**
     *
     * @param aluDv
     */
    public void setAluDv(String aluDv) {
        this.aluDv = aluDv;
    }

    /**
     *
     * @return
     */
    public String getAluMaterno() {
        return aluMaterno;
    }

    /**
     *
     * @param aluMaterno
     */
    public void setAluMaterno(String aluMaterno) {
        this.aluMaterno = aluMaterno;
    }

    /**
     *
     * @return
     */
    public String getAluNombre() {
        return aluNombre;
    }

    /**
     *
     * @param aluNombre
     */
    public void setAluNombre(String aluNombre) {
        this.aluNombre = aluNombre;
    }

    /**
     *
     * @return
     */
    public String getAluPaterno() {
        return aluPaterno;
    }

    /**
     *
     * @param aluPaterno
     */
    public void setAluPaterno(String aluPaterno) {
        this.aluPaterno = aluPaterno;
    }

    /**
     *
     * @return
     */
    public Integer getAluRut() {
        return aluRut;
    }

    /**
     *
     * @param aluRut
     */
    public void setAluRut(Integer aluRut) {
        this.aluRut = aluRut;
    }

    public Integer getAcaCodCar() {
        return acaCodCar;
    }

    public void setAcaCodCar(Integer acaCodCar) {
        this.acaCodCar = acaCodCar;
    }

    public Integer getAcaCodMen() {
        return acaCodMen;
    }

    public void setAcaCodMen(Integer acaCodMen) {
        this.acaCodMen = acaCodMen;
    }
}
