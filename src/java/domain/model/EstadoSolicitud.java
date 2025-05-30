/*
 * @(#)EstadoSolicitud.java
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
public class EstadoSolicitud implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer esolCod;
    private String esolDes;
    private String esolDesCorta;

    /**
     *
     */
    public EstadoSolicitud() {
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getEsolCod() {
        return esolCod;
    }

    /**
     * Method description
     *
     * @param esolCod
     */
    public void setEsolCod(Integer esolCod) {
        this.esolCod = esolCod;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getEsolDes() {
        return esolDes;
    }

    /**
     * Method description
     *
     * @param esolDes
     */
    public void setEsolDes(String esolDes) {
        this.esolDes = esolDes;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getEsolDesCorta() {
        return esolDesCorta;
    }

    /**
     * Method description
     *
     * @param esolDesCorta
     */
    public void setEsolDesCorta(String esolDesCorta) {
        this.esolDesCorta = esolDesCorta;
    }
}
