/*
 * @(#)PrerequisitoElectivo.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.model;

//Generated 24-mar-2009 15:19:39 by Hibernate Tools 3.2.1.GA
import java.io.Serializable;

/**
 * PrerequisitoElectivo generated by hbm2java
 */
public class PrerequisitoElectivo implements Serializable {

    private static final long serialVersionUID = 1L;
    private PrerequisitoElectivoId id;

    /**
     *
     */
    public PrerequisitoElectivo() {
    }

    /**
     * Method description
     *
     * @return
     */
    public PrerequisitoElectivoId getId() {
        return this.id;
    }

    /**
     * Method description
     *
     * @param id
     */
    public void setId(PrerequisitoElectivoId id) {
        this.id = id;
    }
}
