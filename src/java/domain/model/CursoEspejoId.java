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
public class CursoEspejoId implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Integer cesAsign;
    private String cesElect;
    private String cesCoord;
    private Integer cesSecc;
    private Integer cesAgno;
    private Integer cesSem;
    private String cesComp;

    public CursoEspejoId() {
    }

    public Integer getCesAsign() {
        return cesAsign;
    }

    public void setCesAsign(Integer cesAsign) {
        this.cesAsign = cesAsign;
    }

    public String getCesElect() {
        return cesElect;
    }

    public void setCesElect(String cesElect) {
        this.cesElect = cesElect;
    }

    public String getCesCoord() {
        return cesCoord;
    }

    public void setCesCoord(String cesCoord) {
        this.cesCoord = cesCoord;
    }

    public Integer getCesSecc() {
        return cesSecc;
    }

    public void setCesSecc(Integer cesSecc) {
        this.cesSecc = cesSecc;
    }

    public Integer getCesAgno() {
        return cesAgno;
    }

    public void setCesAgno(Integer cesAgno) {
        this.cesAgno = cesAgno;
    }

    public Integer getCesSem() {
        return cesSem;
    }

    public void setCesSem(Integer cesSem) {
        this.cesSem = cesSem;
    }

    public String getCesComp() {
        return cesComp;
    }

    public void setCesComp(String cesComp) {
        this.cesComp = cesComp;
    }
}
