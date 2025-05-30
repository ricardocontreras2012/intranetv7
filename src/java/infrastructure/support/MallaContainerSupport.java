/*
 * @(#)MallaContainerSupport.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.support;

import domain.model.Calificacion;
import domain.model.CalificacionLogroAdicional;
import java.io.Serializable;
import java.util.List;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class MallaContainerSupport implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<Calificacion> calificacion;
    private List<CalificacionLogroAdicional> calificacionRequisitoAdicionalLogroList;
    private List<List<MallaNodoSupport>> malla;

    /**
     * Method description
     *
     * @return
     */
    public List<Calificacion> getCalificacion() {
        return calificacion;
    }

    /**
     * Method description
     *
     * @return
     */
    public List<List<MallaNodoSupport>> getMalla() {
        return malla;
    }

    /**
     * Method description
     *
     * @param calificacion
     */
    public void setCalificacion(List<Calificacion> calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * Method description
     *
     * @param malla
     */
    public void setMalla(List<List<MallaNodoSupport>> malla) {
        this.malla= malla;
    }

    /**
     * Method description
     *
     * @return
     */
    public List<CalificacionLogroAdicional> getCalificacionRequisitoAdicionalLogroList() {
        return calificacionRequisitoAdicionalLogroList;
    }

    /**
     * Method description
     *
     * @param calificacionRequisitoAdicionalLogroList
     *
     */
    public void setCalificacionRequisitoAdicionalLogroList(List<CalificacionLogroAdicional> calificacionRequisitoAdicionalLogroList) {
        this.calificacionRequisitoAdicionalLogroList = calificacionRequisitoAdicionalLogroList;
    }
}
