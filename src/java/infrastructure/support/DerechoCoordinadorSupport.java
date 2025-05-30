/*
 * @(#)DerechoCoordinadorSupport.java
 *
 * Copyright (c) 2016 FAE-USACH
 */
package infrastructure.support;

import java.io.Serializable;

/**
 *
 * @author Ricardo Contreras S.
 */
public class DerechoCoordinadorSupport implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer derAsign;
    private Integer derCred;
    private Integer derNivel;
    private String derNom;
    private Integer derReq;
    private Integer derSct;

    /**
     *
     */
    public DerechoCoordinadorSupport() {
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getDerAsign() {
        return derAsign;
    }

    /**
     * Method description
     *
     *
     * @param derAsign
     */
    public void setDerAsign(Integer derAsign) {
        this.derAsign = derAsign;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getDerNom() {
        return derNom;
    }

    /**
     * Method description
     *
     *
     * @param derNom
     */
    public void setDerNom(String derNom) {
        this.derNom = derNom;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getDerNivel() {
        return derNivel;
    }

    /**
     * Method description
     *
     *
     * @param derNivel
     */
    public void setDerNivel(Integer derNivel) {
        this.derNivel = derNivel;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getDerCred() {
        return derCred;
    }

    /**
     * Method description
     *
     *
     * @param derCred
     */
    public void setDerCred(Integer derCred) {
        this.derCred = derCred;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getDerSct() {
        return derSct;
    }

    /**
     * Method description
     *
     *
     * @param derSct
     */
    public void setDerSct(Integer derSct) {
        this.derSct = derSct;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getDerReq() {
        return derReq;
    }

    /**
     * Method description
     *
     *
     * @param derReq
     */
    public void setDerReq(Integer derReq) {
        this.derReq = derReq;
    }
}
