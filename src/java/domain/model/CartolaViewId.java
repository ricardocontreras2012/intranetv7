/*
 * @(#)CartolaViewId.java
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
public class CartolaViewId implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer cartAgno;
    private Integer cartAgnoIng;
    private Integer cartAsign;
    private Integer cartCodCar;
    private String cartElect;
    private Integer cartRut;
    private Integer cartSem;
    private Integer cartSemIng;

    /**
     * Method description
     *
     * @return
     */
    public Integer getCartAgno() {
        return cartAgno;
    }

    /**
     * Method description
     *
     * @param cartAgno
     */
    public void setCartAgno(Integer cartAgno) {
        this.cartAgno = cartAgno;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getCartAgnoIng() {
        return cartAgnoIng;
    }

    /**
     * Method description
     *
     * @param cartAgnoIng
     */
    public void setCartAgnoIng(Integer cartAgnoIng) {
        this.cartAgnoIng = cartAgnoIng;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getCartAsign() {
        return cartAsign;
    }

    /**
     * Method description
     *
     * @param cartAsign
     */
    public void setCartAsign(Integer cartAsign) {
        this.cartAsign = cartAsign;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getCartCodCar() {
        return cartCodCar;
    }

    /**
     * Method description
     *
     * @param cartCodCar
     */
    public void setCartCodCar(Integer cartCodCar) {
        this.cartCodCar = cartCodCar;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getCartElect() {
        return cartElect;
    }

    /**
     * Method description
     *
     * @param cartElect
     */
    public void setCartElect(String cartElect) {
        this.cartElect = cartElect;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getCartRut() {
        return cartRut;
    }

    /**
     * Method description
     *
     * @param cartRut
     */
    public void setCartRut(Integer cartRut) {
        this.cartRut = cartRut;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getCartSem() {
        return cartSem;
    }

    /**
     * Method description
     *
     * @param cartSem
     */
    public void setCartSem(Integer cartSem) {
        this.cartSem = cartSem;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getCartSemIng() {
        return cartSemIng;
    }

    /**
     * Method description
     *
     * @param cartSemIng
     */
    public void setCartSemIng(Integer cartSemIng) {
        this.cartSemIng = cartSemIng;
    }
}
