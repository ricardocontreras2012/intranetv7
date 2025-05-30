/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Ricardo
 */
public class RespEnctaAyuCursoView implements Serializable {

    private static final long serialVersionUID = 1L;

    private BigDecimal desv;
    private Integer maximo;
    private Integer minimo;
    private Integer numResp;
    private String peaDes;
    private Integer peaPreg;
    private BigDecimal prom;
    private Integer reaAgno;
    private Integer reaAsign;
    private String reaCoord;
    private String reaElect;
    private Integer reaSecc;
    private Integer reaSem;

    public RespEnctaAyuCursoView() {
    }

    public BigDecimal getDesv() {
        return desv;
    }

    public void setDesv(BigDecimal desv) {
        this.desv = desv;
    }

    public Integer getMaximo() {
        return maximo;
    }

    public void setMaximo(Integer maximo) {
        this.maximo = maximo;
    }

    public Integer getMinimo() {
        return minimo;
    }

    public void setMinimo(Integer minimo) {
        this.minimo = minimo;
    }

    public Integer getNumResp() {
        return numResp;
    }

    public void setNumResp(Integer numResp) {
        this.numResp = numResp;
    }

    public String getPeaDes() {
        return peaDes;
    }

    public void setPeaDes(String peaDes) {
        this.peaDes = peaDes;
    }

    public Integer getPeaPreg() {
        return peaPreg;
    }

    public void setPeaPreg(Integer peaPreg) {
        this.peaPreg = peaPreg;
    }

    public BigDecimal getProm() {
        return prom;
    }

    public void setProm(BigDecimal prom) {
        this.prom = prom;
    }

    public Integer getReaAgno() {
        return reaAgno;
    }

    public void setReaAgno(Integer reaAgno) {
        this.reaAgno = reaAgno;
    }

    public Integer getReaAsign() {
        return reaAsign;
    }

    public void setReaAsign(Integer reaAsign) {
        this.reaAsign = reaAsign;
    }

    public String getReaCoord() {
        return reaCoord;
    }

    public void setReaCoord(String reaCoord) {
        this.reaCoord = reaCoord;
    }

    public String getReaElect() {
        return reaElect;
    }

    public void setReaElect(String reaElect) {
        this.reaElect = reaElect;
    }

    public Integer getReaSecc() {
        return reaSecc;
    }

    public void setReaSecc(Integer reaSecc) {
        this.reaSecc = reaSecc;
    }

    public Integer getReaSem() {
        return reaSem;
    }

    public void setReaSem(Integer reaSem) {
        this.reaSem = reaSem;
    }




}
