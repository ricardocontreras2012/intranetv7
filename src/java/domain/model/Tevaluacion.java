/*
 * @(#)Tevaluacion.java
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
public class Tevaluacion implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer tevalCod;
    private String tevalDes;
    private Integer tevalMinRequeridas;

    /**
     *
     */
    public Tevaluacion() {
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getTevalCod() {
        return tevalCod;
    }

    /**
     * Method description
     *
     * @param tevalCod
     */
    public void setTevalCod(Integer tevalCod) {
        this.tevalCod = tevalCod;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getTevalDes() {
        return tevalDes;
    }

    /**
     * Method description
     *
     * @param tevalDes
     */
    public void setTevalDes(String tevalDes) {
        this.tevalDes = tevalDes;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getTevalMinRequeridas() {
        return tevalMinRequeridas;
    }

    /**
     * Method description
     *
     * @param tevalMinRequeridas
     */
    public void setTevalMinRequeridas(Integer tevalMinRequeridas) {
        this.tevalMinRequeridas = tevalMinRequeridas;
    }
}
