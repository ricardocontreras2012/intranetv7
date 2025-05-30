/*
 * @(#)CartolaView.java
 *
 * Copyright (c) 2019 FAE-USACH
 */
package domain.model;

import java.io.Serializable;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public class CartolaView implements Serializable {

    private static final long serialVersionUID = 1L;
    private String cartCoord;
    private Integer cartHC;
    private String cartNombreCompleto;
    private String cartNotaFin;
    private String cartProc;
    private Integer cartSecc;
    private String cartSitAlu;
    private CartolaViewId id;

    /**
     * Method description
     *
     * @return
     */
    public String getCartCoord() {
        return cartCoord;
    }

    /**
     * Method description
     *
     * @param cartCoord
     */
    public void setCartCoord(String cartCoord) {
        this.cartCoord = cartCoord;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getCartNombreCompleto() {
        return cartNombreCompleto;
    }

    /**
     * Method description
     *
     * @param cartNombreCompleto
     */
    public void setCartNombreCompleto(String cartNombreCompleto) {
        this.cartNombreCompleto = cartNombreCompleto;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getCartNotaFin() {
        return cartNotaFin;
    }

    /**
     * Method description
     *
     * @param cartNotaFin
     */
    public void setCartNotaFin(String cartNotaFin) {
        this.cartNotaFin = cartNotaFin;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getCartProc() {
        return cartProc;
    }

    /**
     * Method description
     *
     * @param cartProc
     */
    public void setCartProc(String cartProc) {
        this.cartProc = cartProc;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getCartSecc() {
        return cartSecc;
    }

    /**
     * Method description
     *
     * @param cartSecc
     */
    public void setCartSecc(Integer cartSecc) {
        this.cartSecc = cartSecc;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getCartSitAlu() {
        return cartSitAlu;
    }

    /**
     * Method description
     *
     * @param cartSitAlu
     */
    public void setCartSitAlu(String cartSitAlu) {
        this.cartSitAlu = cartSitAlu;
    }

    /**
     * Method description
     *
     * @return
     */
    public CartolaViewId getId() {
        return id;
    }

    /**
     * Method description
     *
     * @param id
     */
    public void setId(CartolaViewId id) {
        this.id = id;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getCartHC() {
        return cartHC;
    }

    /**
     * Method description
     *
     * @param cartHC
     */
    public void setCartHC(Integer cartHC) {
        this.cartHC = cartHC;
    }
}
