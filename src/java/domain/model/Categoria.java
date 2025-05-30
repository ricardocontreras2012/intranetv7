/*
 * @(#)Categoria.java
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
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer catCod;
    private String catDes;

    /**
     *
     */
    public Categoria() {
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getCatCod() {
        return catCod;
    }

    /**
     * Method description
     *
     * @param catCod
     */
    public void setCatCod(Integer catCod) {
        this.catCod = catCod;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getCatDes() {
        return catDes;
    }

    /**
     * Method description
     *
     * @param catDes
     */
    public void setCatDes(String catDes) {
        this.catDes = catDes;
    }
}
