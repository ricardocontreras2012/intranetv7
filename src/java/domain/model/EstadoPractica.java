/*
 * @(#)EstadoPractica.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.model;

import java.io.Serializable;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public class EstadoPractica implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer epraCod;
    private String epraDes;

    /**
     *
     */
    public EstadoPractica() {
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getEpraCod() {
        return epraCod;
    }

    /**
     * Method description
     *
     * @param epraCod
     */
    public void setEpraCod(Integer epraCod) {
        this.epraCod = epraCod;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getEpraDes() {
        return epraDes;
    }

    /**
     * Method description
     *
     * @param epraDes
     */
    public void setEpraDes(String epraDes) {
        this.epraDes = epraDes;
    }
}
