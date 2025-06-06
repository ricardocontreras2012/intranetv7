/*
 * @(#)Dia.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.model;

import java.io.Serializable;

/**
 * Class description
 *
 * @author Alvaro Romero C.
 * @version 7, 06/05/2025
 */
public class TDocExpLogroId implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer telCodLogro;
    private Integer telCodTdoc;

    /**
     *
     */
    public TDocExpLogroId() {
    }

    public TDocExpLogroId(Integer telCodLogro, Integer telCodTdoc) {
        this.telCodLogro = telCodLogro;
        this.telCodTdoc = telCodTdoc;
    }

    public Integer getTelCodLogro() {
        return telCodLogro;
    }

    public void setTelCodLogro(Integer telCodLogro) {
        this.telCodLogro = telCodLogro;
    }

    public Integer getTelCodTdoc() {
        return telCodTdoc;
    }

    public void setTelCodTdoc(Integer telCodTdoc) {
        this.telCodTdoc = telCodTdoc;
    }
}
