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
public class SolicitudInscripcionId implements Serializable {
    
    private static final long serialVersionUID = 1L;

    Integer soliSol;
    Integer soliAsign;
    String soliElect;
    String soliCoord;
    Integer soliSecc;
    Integer soliAgno;
    Integer soliSem;
    String soliComp;

    public SolicitudInscripcionId() {
    }

    public Integer getSoliSol() {
        return soliSol;
    }

    public void setSoliSol(Integer soliSol) {
        this.soliSol = soliSol;
    }

    public Integer getSoliAsign() {
        return soliAsign;
    }

    public void setSoliAsign(Integer soliAsign) {
        this.soliAsign = soliAsign;
    }

    public String getSoliElect() {
        return soliElect;
    }

    public void setSoliElect(String soliElect) {
        this.soliElect = soliElect;
    }

    public String getSoliCoord() {
        return soliCoord;
    }

    public void setSoliCoord(String soliCoord) {
        this.soliCoord = soliCoord;
    }

    public Integer getSoliSecc() {
        return soliSecc;
    }

    public void setSoliSecc(Integer soliSecc) {
        this.soliSecc = soliSecc;
    }

    public Integer getSoliAgno() {
        return soliAgno;
    }

    public void setSoliAgno(Integer soliAgno) {
        this.soliAgno = soliAgno;
    }

    public Integer getSoliSem() {
        return soliSem;
    }

    public void setSoliSem(Integer soliSem) {
        this.soliSem = soliSem;
    }

    public String getSoliComp() {
        return soliComp;
    }

    public void setSoliComp(String soliComp) {
        this.soliComp = soliComp;
    }
}
