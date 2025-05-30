/*
 * @(#)CursoTevaluacionId.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.model;

import java.io.Serializable;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public class CursoTevaluacionId implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer ctevaAgno;
    private Integer ctevaAsign;
    private String ctevaCoord;
    private String ctevaElect;
    private Integer ctevaSecc;
    private Integer ctevaSem;
    private String ctevaComp;
    private Integer ctevaTeva;

    /**
     *
     */
    public CursoTevaluacionId() {
    }

    /**
     *
     * @param cursoId
     * @param tipoEval
     */
    public CursoTevaluacionId(CursoId cursoId, Integer tipoEval) {
        ctevaAsign = cursoId.getCurAsign();
        ctevaElect = cursoId.getCurElect();
        ctevaCoord = cursoId.getCurCoord();
        ctevaSecc = cursoId.getCurSecc();
        ctevaAgno = cursoId.getCurAgno();
        ctevaSem = cursoId.getCurSem();
        ctevaComp = cursoId.getCurComp();
        ctevaTeva = tipoEval;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getCtevaAgno() {
        return ctevaAgno;
    }

    /**
     * Method description
     *
     * @param ctevaAgno
     */
    public void setCtevaAgno(Integer ctevaAgno) {
        this.ctevaAgno = ctevaAgno;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getCtevaAsign() {
        return ctevaAsign;
    }

    /**
     * Method description
     *
     * @param ctevaAsign
     */
    public void setCtevaAsign(Integer ctevaAsign) {
        this.ctevaAsign = ctevaAsign;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getCtevaCoord() {
        return ctevaCoord;
    }

    /**
     * Method description
     *
     * @param ctevaCoord
     */
    public void setCtevaCoord(String ctevaCoord) {
        this.ctevaCoord = ctevaCoord;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getCtevaElect() {
        return ctevaElect;
    }

    /**
     * Method description
     *
     * @param ctevaElect
     */
    public void setCtevaElect(String ctevaElect) {
        this.ctevaElect = ctevaElect;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getCtevaSecc() {
        return ctevaSecc;
    }

    /**
     * Method description
     *
     * @param ctevaSecc
     */
    public void setCtevaSecc(Integer ctevaSecc) {
        this.ctevaSecc = ctevaSecc;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getCtevaSem() {
        return ctevaSem;
    }

    /**
     * Method description
     *
     * @param ctevaSem
     */
    public void setCtevaSem(Integer ctevaSem) {
        this.ctevaSem = ctevaSem;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getCtevaTeva() {
        return ctevaTeva;
    }

    /**
     * Method description
     *
     * @param ctevaTeva
     */
    public void setCtevaTeva(Integer ctevaTeva) {
        this.ctevaTeva = ctevaTeva;
    }

    public String getCtevaComp() {
        return ctevaComp;
    }

    public void setCtevaComp(String ctevaComp) {
        this.ctevaComp = ctevaComp;
    }    
}
