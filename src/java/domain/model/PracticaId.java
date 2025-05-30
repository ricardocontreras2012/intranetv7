/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.model;

import java.io.Serializable;

/**
 *
 * @author Ricardo Contreras S.
 */
public class PracticaId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer praRut;
    private Integer praCodCar;
    private Integer praAgnoIng;
    private Integer praSemIng;
    private Integer praAsign;
    private Integer praAgno;
    private Integer praSem;

    /**
     *
     */
    public PracticaId() {
    }

    /**
     *
     * @return
     */
    public Integer getPraRut() {
        return praRut;
    }

    /**
     *
     * @param praRut
     */
    public void setPraRut(Integer praRut) {
        this.praRut = praRut;
    }

    /**
     *
     * @return
     */
    public Integer getPraCodCar() {
        return praCodCar;
    }

    /**
     *
     * @param praCodCar
     */
    public void setPraCodCar(Integer praCodCar) {
        this.praCodCar = praCodCar;
    }

    /**
     *
     * @return
     */
    public Integer getPraAgnoIng() {
        return praAgnoIng;
    }

    /**
     *
     * @param praAgnoIng
     */
    public void setPraAgnoIng(Integer praAgnoIng) {
        this.praAgnoIng = praAgnoIng;
    }

    /**
     *
     * @return
     */
    public Integer getPraSemIng() {
        return praSemIng;
    }

    /**
     *
     * @param praSemIng
     */
    public void setPraSemIng(Integer praSemIng) {
        this.praSemIng = praSemIng;
    }

    /**
     *
     * @return
     */
    public Integer getPraAsign() {
        return praAsign;
    }

    /**
     *
     * @param praAsign
     */
    public void setPraAsign(Integer praAsign) {
        this.praAsign = praAsign;
    }

    /**
     *
     * @return
     */
    public Integer getPraAgno() {
        return praAgno;
    }

    /**
     *
     * @param praAgno
     */
    public void setPraAgno(Integer praAgno) {
        this.praAgno = praAgno;
    }

    /**
     *
     * @return
     */
    public Integer getPraSem() {
        return praSem;
    }

    /**
     *
     * @param praSem
     */
    public void setPraSem(Integer praSem) {
        this.praSem = praSem;
    }
}
