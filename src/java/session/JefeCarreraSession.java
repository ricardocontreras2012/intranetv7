/*
 * @(#)JefeCarreraSession.java
 *
 * Copyright (c) 2016 FAE-USACH
 */
package session;

import domain.model.Profesor;
import domain.model.Curso;
import domain.model.AluCar;
import domain.model.Area;
import java.io.Serializable;
import java.util.List;
import infrastructure.util.common.CommonJefeCarreraUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class JefeCarreraSession implements Serializable {

    private static final long serialVersionUID = 1L;
    private Profesor profesor;
    private Curso cursoAdmIzq;
    private Curso cursoAdmDer;
    private List<AluCar> nominaCursoAdmIzq;
    private List<AluCar> nominaCursoAdmDer;
    private int posIzq;
    private int posDer;
    private String json;

    /**
     *
     */
    public JefeCarreraSession() {
    }

    /**
     * Method description
     *
     * @return
     */
    public Profesor getProfesor() {
        return profesor;
    }

    /**
     * Method description
     *
     * @param profesor
     */
    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Curso getCursoAdmIzq() {
        return cursoAdmIzq;
    }

    public void setCursoAdmIzq(Curso cursoAdmIzq) {
        this.cursoAdmIzq = cursoAdmIzq;
    }

    public Curso getCursoAdmDer() {
        return cursoAdmDer;
    }

    public void setCursoAdmDer(Curso cursoAdmDer) {
        this.cursoAdmDer = cursoAdmDer;
    }

    public List<AluCar> getNominaCursoAdmIzq() {
        return nominaCursoAdmIzq;
    }

    public void setNominaCursoAdmIzq(List<AluCar> nominaCursoAdmIzq) {
        this.nominaCursoAdmIzq = nominaCursoAdmIzq;
    }

    public List<AluCar> getNominaCursoAdmDer() {
        return nominaCursoAdmDer;
    }

    public void setNominaCursoAdmDer(List<AluCar> nominaCursoAdmDer) {
        this.nominaCursoAdmDer = nominaCursoAdmDer;
    }

    public int getPosIzq() {
        return posIzq;
    }

    public void setPosIzq(int posIzq) {
        this.posIzq = posIzq;
    }

    public int getPosDer() {
        return posDer;
    }

    public void setPosDer(int posDer) {
        this.posDer = posDer;
    }

    public List<Area> getAreaList(Integer rut) { 
        return CommonJefeCarreraUtil.getAreas(rut, "JC");
    }
    
    public List<Integer> getMinorList(Integer rut) { 
        return CommonJefeCarreraUtil.getMinors(rut, "JC");
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
