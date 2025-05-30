/*
 * @(#)Jornada.java
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
public class Jornada implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer jorCod;
    private String jorDes;

    /**
     *
     */
    public Jornada() {
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getJorCod() {
        return jorCod;
    }

    /**
     * Method description
     *
     * @param jorCod
     */
    public void setJorCod(Integer jorCod) {
        this.jorCod = jorCod;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getJorDes() {
        return jorDes;
    }

    /**
     * Method description
     *
     * @param jorDes
     */
    public void setJorDes(String jorDes) {
        this.jorDes = jorDes;
    }
}
