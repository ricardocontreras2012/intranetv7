/*
 * @(#)RespEnctaCursoView.java
 *
 * Copyright (c) 2019 FAE-USACH
 */
package domain.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Class description
 *
 *
 * @version 7, 30/04/2014
 * @author Ricardo Contreras S.
 */
public class RespEnctaCursoView implements Serializable {

    private static final long serialVersionUID = 1L;
    private BigDecimal cedPromArea;
    private BigDecimal cedPromCur;
    private BigDecimal desv;
    private Integer maximo;
    private Integer minimo;
    private Integer numResp;
    private String pregDes;
    private Integer pregNro;
    private BigDecimal prom;
    private Integer respAgno;
    private Integer respAsign;
    private String respCoord;
    private String respElect;
    private Integer respSecc;
    private Integer respSem;
    private Integer respProf;
    private String edoTipo;

    /**
     *
     */
    public RespEnctaCursoView() {
    }

    /**
     *
     * @return
     */
    public BigDecimal getCedPromArea() {
        return cedPromArea;
    }

    /**
     *
     * @param cedPromArea
     */
    public void setCedPromArea(BigDecimal cedPromArea) {
        this.cedPromArea = cedPromArea;
    }

    /**
     *
     * @return
     */
    public BigDecimal getCedPromCur() {
        return cedPromCur;
    }

    /**
     *
     * @param cedPromCur
     */
    public void setCedPromCur(BigDecimal cedPromCur) {
        this.cedPromCur = cedPromCur;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public BigDecimal getDesv() {
        return desv;
    }

    /**
     * Method description
     *
     *
     * @param desv
     */
    public void setDesv(BigDecimal desv) {
        this.desv = desv;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getMaximo() {
        return maximo;
    }

    /**
     * Method description
     *
     *
     * @param maximo
     */
    public void setMaximo(Integer maximo) {
        this.maximo = maximo;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getMinimo() {
        return minimo;
    }

    /**
     * Method description
     *
     *
     * @param minimo
     */
    public void setMinimo(Integer minimo) {
        this.minimo = minimo;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getNumResp() {
        return numResp;
    }

    /**
     * Method description
     *
     *
     * @param numResp
     */
    public void setNumResp(Integer numResp) {
        this.numResp = numResp;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getPregDes() {
        return pregDes;
    }

    /**
     * Method description
     *
     *
     * @param pregDes
     */
    public void setPregDes(String pregDes) {
        this.pregDes = pregDes;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getPregNro() {
        return pregNro;
    }

    /**
     * Method description
     *
     *
     * @param pregNro
     */
    public void setPregNro(Integer pregNro) {
        this.pregNro = pregNro;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public BigDecimal getProm() {
        return prom;
    }

    /**
     * Method description
     *
     *
     * @param prom
     */
    public void setProm(BigDecimal prom) {
        this.prom = prom;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getRespAgno() {
        return respAgno;
    }

    /**
     * Method description
     *
     *
     * @param respAgno
     */
    public void setRespAgno(Integer respAgno) {
        this.respAgno = respAgno;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getRespAsign() {
        return respAsign;
    }

    /**
     * Method description
     *
     *
     * @param respAsign
     */
    public void setRespAsign(Integer respAsign) {
        this.respAsign = respAsign;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getRespCoord() {
        return respCoord;
    }

    /**
     * Method description
     *
     *
     * @param respCoord
     */
    public void setRespCoord(String respCoord) {
        this.respCoord = respCoord;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getRespElect() {
        return respElect;
    }

    /**
     * Method description
     *
     *
     * @param respElect
     */
    public void setRespElect(String respElect) {
        this.respElect = respElect;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getRespSecc() {
        return respSecc;
    }

    /**
     * Method description
     *
     *
     * @param respSecc
     */
    public void setRespSecc(Integer respSecc) {
        this.respSecc = respSecc;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getRespSem() {
        return respSem;
    }

    /**
     * Method description
     *
     *
     * @param respSem
     */
    public void setRespSem(Integer respSem) {
        this.respSem = respSem;
    }

    public Integer getRespProf() {
        return respProf;
    }

    public void setRespProf(Integer respProf) {
        this.respProf = respProf;
    } 

    public String getEdoTipo() {
        return edoTipo;
    }

    public void setEdoTipo(String edoTipo) {
        this.edoTipo = edoTipo;
    }
}
