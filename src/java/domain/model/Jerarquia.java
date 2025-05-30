/*
 * @(#)Jerarquia.java
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
public class Jerarquia implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer jerCod;
    private String jerDes;

    /**
     *
     */
    public Jerarquia() {
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getJerCod() {
        return jerCod;
    }

    /**
     * Method description
     *
     * @param jerCod
     */
    public void setJerCod(Integer jerCod) {
        this.jerCod = jerCod;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getJerDes() {
        return jerDes;
    }

    /**
     * Method description
     *
     * @param jerDes
     */
    public void setJerDes(String jerDes) {
        this.jerDes = jerDes;
    }
}
