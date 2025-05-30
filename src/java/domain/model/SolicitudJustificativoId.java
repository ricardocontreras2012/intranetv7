/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.model;

import java.io.Serializable;

/**
 *
 * @author Ricardo
 */
public class SolicitudJustificativoId implements Serializable {
    
    private static final long serialVersionUID = 1L;

    Integer soljSol;
    Integer soljAsign;
    String soljElect;
    String soljCoord;
    Integer soljSecc;
    Integer soljAgno;
    Integer soljSem;
    String soljComp;

    public SolicitudJustificativoId() {
    }

    public Integer getSoljSol() {
        return soljSol;
    }

    public void setSoljSol(Integer soljSol) {
        this.soljSol = soljSol;
    }

    public Integer getSoljAsign() {
        return soljAsign;
    }

    public void setSoljAsign(Integer soljAsign) {
        this.soljAsign = soljAsign;
    }

    public String getSoljElect() {
        return soljElect;
    }

    public void setSoljElect(String soljElect) {
        this.soljElect = soljElect;
    }

    public String getSoljCoord() {
        return soljCoord;
    }

    public void setSoljCoord(String soljCoord) {
        this.soljCoord = soljCoord;
    }

    public Integer getSoljSecc() {
        return soljSecc;
    }

    public void setSoljSecc(Integer soljSecc) {
        this.soljSecc = soljSecc;
    }

    public Integer getSoljAgno() {
        return soljAgno;
    }

    public void setSoljAgno(Integer soljAgno) {
        this.soljAgno = soljAgno;
    }

    public Integer getSoljSem() {
        return soljSem;
    }

    public void setSoljSem(Integer soljSem) {
        this.soljSem = soljSem;
    }

    public String getSoljComp() {
        return soljComp;
    }

    public void setSoljComp(String soljComp) {
        this.soljComp = soljComp;
    }   
}
