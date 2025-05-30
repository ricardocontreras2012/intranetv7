/*
 * @(#)MencionInfoIntranetProfesorView.java
 *
 * Copyright (c) 2019 FAE-USACH
 */
package domain.model;

import domain.model.MencionInfoIntranetId;
import java.io.Serializable;

/**
 * Class description
 *
 *
 * @version 7, 30/04/2014
 * @author Ricardo Contreras S.
 */
public class MencionInfoIntranetProfesorView implements Serializable {

    private static final long serialVersionUID = 1L;
    private MencionInfoIntranetId id;
    private String miniUrlCalendarioAct;
    private String miniUrlNormativa;
    private String nombreMencion;
    private Integer rut;

    /**
     *
     */
    public MencionInfoIntranetProfesorView() {
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public MencionInfoIntranetId getId() {
        return id;
    }

    /**
     * Method description
     *
     *
     * @param id
     */
    public void setId(MencionInfoIntranetId id) {
        this.id = id;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getRut() {
        return rut;
    }

    /**
     * Method description
     *
     *
     * @param rut
     */
    public void setRut(Integer rut) {
        this.rut = rut;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getMiniUrlNormativa() {
        return miniUrlNormativa;
    }

    /**
     * Method description
     *
     *
     * @param miniUrlNormativa
     */
    public void setMiniUrlNormativa(String miniUrlNormativa) {
        this.miniUrlNormativa = miniUrlNormativa;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getMiniUrlCalendarioAct() {
        return miniUrlCalendarioAct;
    }

    /**
     * Method description
     *
     *
     * @param miniUrlCalendarioAct
     */
    public void setMiniUrlCalendarioAct(String miniUrlCalendarioAct) {
        this.miniUrlCalendarioAct = miniUrlCalendarioAct;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getNombreMencion() {
        return nombreMencion;
    }

    /**
     * Method description
     *
     *
     * @param nombreMencion
     */
    public void setNombreMencion(String nombreMencion) {
        this.nombreMencion = nombreMencion;
    }
}
