/*
 * @(#)PregEnctaId.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.model;

//Generated 24-mar-2009 15:19:39 by Hibernate Tools 3.2.1.GA
import java.io.Serializable;

/**
 * PregEnctaId generated by hbm2java
 */
public class PreguntaAutoEvaluacionId implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer padEnc;
    private Integer padPreg;

    /**
     *
     */
    public PreguntaAutoEvaluacionId() {
    }

    public Integer getPadEnc() {
        return padEnc;
    }

    public void setPadEnc(Integer padEnc) {
        this.padEnc = padEnc;
    }

    public Integer getPadPreg() {
        return padPreg;
    }

    public void setPadPreg(Integer padPreg) {
        this.padPreg = padPreg;
    }
}
