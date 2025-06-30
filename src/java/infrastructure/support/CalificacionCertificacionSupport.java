/*
 * @(#)CalificacionCertificacionSupport.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.support;

import domain.model.CalificacionId;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CalificacionCertificacionSupport {

    private String calNombre;
    private String calNota;
    private String calProc;
    private String calSitAlu;
    private Integer asiTEL;
    private Integer asiSCT;
    private CalificacionId id;

    /**
     * Method description
     *
     * @return
     */
    public CalificacionId getId() {
        return id;
    }

    /**
     * Method description
     *
     * @param id
     */
    public void setId(CalificacionId id) {
        this.id = id;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getCalNota() {
        return calNota;
    }

    /**
     * Method description
     *
     * @param calNota
     */
    public void setCalNota(String calNota) {
        this.calNota = calNota;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getCalSitAlu() {
        return calSitAlu;
    }

    /**
     * Method description
     *
     * @param calSitAlu
     */
    public void setCalSitAlu(String calSitAlu) {
        this.calSitAlu = calSitAlu;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getCalProc() {
        return calProc;
    }

    /**
     * Method description
     *
     * @param calProc
     */
    public void setCalProc(String calProc) {
        this.calProc = calProc;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getCalNombre() {
        return calNombre;
    }

    /**
     * Method description
     *
     * @param calNombre
     */
    public void setCalNombre(String calNombre) {
        this.calNombre = calNombre;
    }

    public Integer getAsiTEL() {
        return asiTEL;
    }

    public void setAsiTEL(Integer asiTEL) {
        this.asiTEL = asiTEL;
    }

    public Integer getAsiSCT() {
        return asiSCT;
    }

    public void setAsiSCT(Integer asiSCT) {
        this.asiSCT = asiSCT;
    }
}
