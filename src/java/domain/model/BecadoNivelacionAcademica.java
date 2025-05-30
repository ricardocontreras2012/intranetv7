/*
 * @(#)BecadoNivelacionAcademica.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.model;

import java.io.Serializable;

/**
 *
 * @author Ricardo Contreras S.
 */
public class BecadoNivelacionAcademica implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer bnaAgno;
    private Integer bnaRut;

    /**
     *
     */
    public BecadoNivelacionAcademica() {
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getBnaRut() {
        return bnaRut;
    }

    /**
     * Method description
     *
     *
     * @param bnaRut
     */
    public void setBnaRut(Integer bnaRut) {
        this.bnaRut = bnaRut;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getBnaAgno() {
        return bnaAgno;
    }

    /**
     * Method description
     *
     *
     * @param bnaAgno
     */
    public void setBnaAgno(Integer bnaAgno) {
        this.bnaAgno = bnaAgno;
    }
}
