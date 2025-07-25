/*
 * @(#)CursoProfesorId.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.model;

//Generated 24-mar-2009 15:19:39 by Hibernate Tools 3.2.1.GA
import java.io.Serializable;

/**
 * CursoProfesorId generated by hbm2java
 */
public class CursoProfesorId implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer cproAgno;
    private Integer cproAsign;
    private String cproCoord;
    private String cproElect;    
    private Integer cproSecc;
    private Integer cproSem;
    private String cproComp;
    private Integer cproRut;

    /**
     *
     */
    public CursoProfesorId() {
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getCproAgno() {
        return cproAgno;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getCproAsign() {
        return cproAsign;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getCproCoord() {
        return cproCoord;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getCproElect() {
        return cproElect;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getCproRut() {
        return cproRut;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getCproSecc() {
        return cproSecc;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getCproSem() {
        return cproSem;
    }

    /**
     * Method description
     *
     * @param cproAgno
     */
    public void setCproAgno(Integer cproAgno) {
        this.cproAgno = cproAgno;
    }

    /**
     * Method description
     *
     * @param cproAsign
     */
    public void setCproAsign(Integer cproAsign) {
        this.cproAsign = cproAsign;
    }

    /**
     * Method description
     *
     * @param cproCoord
     */
    public void setCproCoord(String cproCoord) {
        this.cproCoord = cproCoord;
    }

    /**
     * Method description
     *
     * @param cproElect
     */
    public void setCproElect(String cproElect) {
        this.cproElect = cproElect;
    }

    /**
     * Method description
     *
     * @param cproRut
     */
    public void setCproRut(Integer cproRut) {
        this.cproRut = cproRut;
    }

    /**
     * Method description
     *
     * @param cproSecc
     */
    public void setCproSecc(Integer cproSecc) {
        this.cproSecc = cproSecc;
    }

    /**
     * Method description
     *
     * @param cproSem
     */
    public void setCproSem(Integer cproSem) {
        this.cproSem = cproSem;
    }

    public String getCproComp() {
        return cproComp;
    }

    public void setCproComp(String cproComp) {
        this.cproComp = cproComp;
    }
}
