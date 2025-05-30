/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.model;

import java.io.Serializable;

/**
 *
 * @author rcontreras
 */
public class CursoEspejo implements Serializable {
    
    private static final long serialVersionUID = 1L;

    CursoEspejoId id;

    private Integer cesAsignTr;
    private String cesElectTr;
    private String cesCoordTr;
    private Integer cesSeccTr;
    private Integer cesAgnoTr;
    private Integer cesSemTr;
    private String cesCompTr;
    private Curso espejo;
    private Curso transversal;

    public CursoEspejoId getId() {
        return id;
    }

    public void setId(CursoEspejoId id) {
        this.id = id;
    }

    public CursoEspejo() {
    }

    public Integer getCesAsignTr() {
        return cesAsignTr;
    }

    public void setCesAsignTr(Integer cesAsignTr) {
        this.cesAsignTr = cesAsignTr;
    }

    public String getCesElectTr() {
        return cesElectTr;
    }

    public void setCesElectTr(String cesElectTr) {
        this.cesElectTr = cesElectTr;
    }

    public String getCesCoordTr() {
        return cesCoordTr;
    }

    public void setCesCoordTr(String cesCoordTr) {
        this.cesCoordTr = cesCoordTr;
    }

    public Integer getCesSeccTr() {
        return cesSeccTr;
    }

    public void setCesSeccTr(Integer cesSeccTr) {
        this.cesSeccTr = cesSeccTr;
    }

    public Integer getCesAgnoTr() {
        return cesAgnoTr;
    }

    public void setCesAgnoTr(Integer cesAgnoTr) {
        this.cesAgnoTr = cesAgnoTr;
    }

    public Integer getCesSemTr() {
        return cesSemTr;
    }

    public void setCesSemTr(Integer cesSemTr) {
        this.cesSemTr = cesSemTr;
    }

    public Curso getEspejo() {
        return espejo;
    }

    public void setEspejo(Curso espejo) {
        this.espejo = espejo;
    }

    public Curso getTransversal() {
        return transversal;
    }

    public void setTransversal(Curso transversal) {
        this.transversal = transversal;
    }

    public String getCesCompTr() {
        return cesCompTr;
    }

    public void setCesCompTr(String cesCompTr) {
        this.cesCompTr = cesCompTr;
    }
}
