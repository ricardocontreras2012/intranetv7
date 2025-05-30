/*
 * @(#)EstadoActa.java
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
public class EstadoActa implements Serializable {

    private static final long serialVersionUID = 1L;
    private String eacCod;
    private String eacDes;

    /**
     *
     */
    public EstadoActa() {
    }

    /**
     * Method description
     *
     * @return
     */
    public String getEacCod() {
        return eacCod;
    }

    /**
     * Method description
     *
     * @param eacCod
     */
    public void setEacCod(String eacCod) {
        this.eacCod = eacCod;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getEacDes() {
        return eacDes;
    }

    /**
     * Method description
     *
     * @param eacDes
     */
    public void setEacDes(String eacDes) {
        this.eacDes = eacDes;
    }
}
