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
public class TDocExpLogro implements Serializable {

    private static final long serialVersionUID = 1L;
    private TDocExpLogroId id;
    private TDocExpediente tDocExpediente;
    private Integer telCodTprog;

    /**
     *
     */
    public TDocExpLogro() {
    }

    public TDocExpLogroId getId() {
        return id;
    }

    public void setId(TDocExpLogroId id) {
        this.id = id;
    }

    public Integer getTelCodTprog() {
        return telCodTprog;
    }

    public void setTelCodTprog(Integer telCodTprog) {
        this.telCodTprog = telCodTprog;
    }

    public TDocExpediente gettDocExpediente() {
        return tDocExpediente;
    }

    public void settDocExpediente(TDocExpediente tDocExpediente) {
        this.tDocExpediente = tDocExpediente;
    }
}
