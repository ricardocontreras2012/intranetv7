/*
 * @(#)EvaluacionId.java
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
public class EvaluacionId implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer evalAgno;
    private Integer evalAsign;
    private String evalCoord;
    private String evalElect;
    private Integer evalEval;
    private Integer evalSecc;
    private Integer evalSem;
    private String evalComp;
    private Integer evalTeva;

    /**
     *
     */
    public EvaluacionId() {
    }

    /**
     *
     * @param cursoTevaluacionId
     * @param eval
     */
    public EvaluacionId(CursoTevaluacionId cursoTevaluacionId, Integer eval) {
        evalAsign = cursoTevaluacionId.getCtevaAsign();
        evalElect = cursoTevaluacionId.getCtevaElect();
        evalCoord = cursoTevaluacionId.getCtevaCoord();
        evalSecc = cursoTevaluacionId.getCtevaSecc();
        evalAgno = cursoTevaluacionId.getCtevaAgno();
        evalSem = cursoTevaluacionId.getCtevaSem();
        evalComp = cursoTevaluacionId.getCtevaComp();
        evalTeva = cursoTevaluacionId.getCtevaTeva();
        evalEval = eval;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getEvalAgno() {
        return evalAgno;
    }

    /**
     * Method description
     *
     * @param evalAgno
     */
    public void setEvalAgno(Integer evalAgno) {
        this.evalAgno = evalAgno;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getEvalAsign() {
        return evalAsign;
    }

    /**
     * Method description
     *
     * @param evalAsign
     */
    public void setEvalAsign(Integer evalAsign) {
        this.evalAsign = evalAsign;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getEvalCoord() {
        return evalCoord;
    }

    /**
     * Method description
     *
     * @param evalCoord
     */
    public void setEvalCoord(String evalCoord) {
        this.evalCoord = evalCoord;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getEvalEval() {
        return evalEval;
    }

    /**
     * Method description
     *
     * @param evalEval
     */
    public void setEvalEval(Integer evalEval) {
        this.evalEval = evalEval;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getEvalElect() {
        return evalElect;
    }

    /**
     * Method description
     *
     * @param evalElect
     */
    public void setEvalElect(String evalElect) {
        this.evalElect = evalElect;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getEvalSecc() {
        return evalSecc;
    }

    /**
     * Method description
     *
     * @param evalSecc
     */
    public void setEvalSecc(Integer evalSecc) {
        this.evalSecc = evalSecc;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getEvalSem() {
        return evalSem;
    }

    /**
     * Method description
     *
     * @param evalSem
     */
    public void setEvalSem(Integer evalSem) {
        this.evalSem = evalSem;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getEvalTeva() {
        return evalTeva;
    }

    /**
     * Method description
     *
     * @param evalTeva
     */
    public void setEvalTeva(Integer evalTeva) {
        this.evalTeva = evalTeva;
    }

    public String getEvalComp() {
        return evalComp;
    }

    public void setEvalComp(String evalComp) {
        this.evalComp = evalComp;
    }
}
