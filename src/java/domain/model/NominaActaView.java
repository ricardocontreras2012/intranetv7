/*
 * @(#)NominaActaView.java
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
public class NominaActaView implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer acalAgno;
    private Integer acalAsign;
    private String acalCoord;
    private String acalElect;
    private Integer acalFolio;
    private Integer acalSecc;
    private Integer acalSem;
    private String asign;
    private Integer nomCorrel;

    /**
     *
     */
    public NominaActaView() {
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
    public String getAsign() {
        return asign;
    }

    /**
     *
     * @param asign
     */
    public void setAsign(String asign) {
        this.asign = asign;
    }

    /**
     *
     * @return
     */
    public Integer getNomCorrel() {
        return nomCorrel;
    }

    /**
     *
     * @param nomCorrel
     */
    public void setNomCorrel(Integer nomCorrel) {
        this.nomCorrel = nomCorrel;
    }
}
