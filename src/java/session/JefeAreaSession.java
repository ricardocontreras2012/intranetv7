/*
 * @(#)JefeAreaSession.java
 *
 * Copyright (c) 2016 FAE-USACH
 */
package session;

import domain.model.Profesor;
import domain.model.Sala;
import domain.model.Calificacion;
import java.io.Serializable;
import java.util.List;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class JefeAreaSession implements Serializable {

    private static final long serialVersionUID = 1L;
    //private List<Dia> diaList;
    private Profesor profesor;
    private List<Sala> salaList;
    private List<Calificacion> actaRectificatoriaList;


    /**
     *
     */
    public JefeAreaSession() {
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

    /**
     * Method description
     *
     * @return
     */
   /* public List<Dia> getDiaList() {
        return diaList;
    }

    /**
     * Method description
     *
     * @param diaList
     */
    /*public void setDiaList(List<Dia> diaList) {
        this.diaList = diaList;
    }*/    

    /**
     * Method description
     *
     * @return
     */
    public List<Sala> getSalaList() {
        return salaList;
    }

    /**
     * Method description
     *
     * @param salaList
     */
    public void setSalaList(List<Sala> salaList) {
        this.salaList = salaList;
    }

    /**
     *
     * @return
     */
    public List<Calificacion> getActaRectificatoriaList() {
        return actaRectificatoriaList;
    }

    /**
     *
     * @param actaRectificatoriaList
     */
    public void setActaRectificatoriaList(List<Calificacion> actaRectificatoriaList) {
        this.actaRectificatoriaList = actaRectificatoriaList;
    }

   
}
