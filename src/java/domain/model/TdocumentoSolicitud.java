/*
 * @(#)TdocumentoSolicitud.java
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
public class TdocumentoSolicitud implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer tdsCod;
    private String tdsDes;
    private String tdsProceso;

    /**
     *
     */
    public TdocumentoSolicitud() {
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getTdsCod() {
        return tdsCod;
    }

    /**
     * Method description
     *
     * @param tdsCod
     */
    public void setTdsCod(Integer tdsCod) {
        this.tdsCod = tdsCod;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getTdsDes() {
        return tdsDes;
    }

    /**
     * Method description
     *
     * @param tdsDes
     */
    public void setTdsDes(String tdsDes) {
        this.tdsDes = tdsDes;
    }

    public String getTdsProceso() {
        return tdsProceso;
    }

    public void setTdsProceso(String tdsProceso) {
        this.tdsProceso = tdsProceso;
    }
}
