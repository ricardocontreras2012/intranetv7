/*
 * @(#)ActaMencionView.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.model;

import java.io.Serializable;
import infrastructure.support.ActaConsultaSupport;
import infrastructure.util.common.CommonActaUtil;

/**
 * Class description
 *
 *
 * @version 7, 30/04/2014
 * @author Ricardo Contreras S.
 */
public class ActaMencionView implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer acalAsign;
    private String acalCoord;
    private String acalElect;
    private Integer acalSecc;
    private String acalTipo;
    private Integer lreaRut;
    private String labUser;
    private Integer espCod;
    private String carRegimen;
    private String estado;
    private ActaCalificacionId id;
    private String nombreCurso;
    private String profesores;
    private Integer tcrCtip;

    /**
     *
     */
    public ActaMencionView() {
    }


    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getEspCod() {
        return espCod;
    }

    /**
     * Method description
     *
     *
     * @param espCod
     */
    public void setEspCod(Integer espCod) {
        this.espCod = espCod;
    }

    public String getCarRegimen() {
        return carRegimen;
    }

    public void setCarRegimen(String carRegimen) {
        this.carRegimen = carRegimen;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Method description
     *
     *
     * @param estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public ActaCalificacionId getId() {
        return id;
    }

    /**
     * Method description
     *
     *
     * @param id
     */
    public void setId(ActaCalificacionId id) {
        this.id = id;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getNombreCurso() {
        return nombreCurso;
    }

    /**
     * Method description
     *
     *
     * @param nombreCurso
     */
    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getProfesores() {
        return profesores;
    }

    /**
     * Method description
     *
     *
     * @param profesores
     */
    public void setProfesores(String profesores) {
        this.profesores = profesores;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getTcrCtip() {
        return tcrCtip;
    }

    /**
     * Method description
     *
     *
     * @param tcrCtip
     */
    public void setTcrCtip(Integer tcrCtip) {
        this.tcrCtip = tcrCtip;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getAcalAsign() {
        return acalAsign;
    }

    /**
     * Method description
     *
     *
     * @param acalAsign
     */
    public void setAcalAsign(Integer acalAsign) {
        this.acalAsign = acalAsign;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getAcalCoord() {
        return acalCoord;
    }

    /**
     * Method description
     *
     *
     * @param acalCoord
     */
    public void setAcalCoord(String acalCoord) {
        this.acalCoord = acalCoord;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getAcalElect() {
        return acalElect;
    }

    /**
     * Method description
     *
     *
     * @param acalElect
     */
    public void setAcalElect(String acalElect) {
        this.acalElect = acalElect;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getAcalSecc() {
        return acalSecc;
    }

    /**
     * Method description
     *
     *
     * @param acalSecc
     */
    public void setAcalSecc(Integer acalSecc) {
        this.acalSecc = acalSecc;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getAcalTipo() {
        return acalTipo;
    }

    /**
     * Method description
     *
     *
     * @param acalTipo
     */
    public void setAcalTipo(String acalTipo) {
        this.acalTipo = acalTipo;
    }

    public Integer getLreaRut() {
        return lreaRut;
    }

    public void setLreaRut(Integer lreaRut) {
        this.lreaRut = lreaRut;
    }

    public String getLabUser() {
        return labUser;
    }

    public void setLabUser(String labUser) {
        this.labUser = labUser;
    }



    /**
     * Method description
     *
     *
     * @return
     */
    public ActaConsultaSupport getActaConsultaSupport() {
        return CommonActaUtil.newActaView(id, acalAsign, acalCoord, acalElect, acalSecc, acalTipo, nombreCurso, profesores, estado);
    }
}
