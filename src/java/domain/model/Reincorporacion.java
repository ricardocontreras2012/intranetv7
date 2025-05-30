/*
 * @(#)Reincorporacion.java
 *
 * Creado por: Ricardo Contreras S.
 * Fecha Actualizacion: 17/07/2014
 *
 * License agreement: Uso exclusivo por FAE
 * Copyright (c) 2025 FAE-USACH
 */
package domain.model;

import java.util.Date;
import static infrastructure.util.DateUtil.getDateGetterSetter;

/**
 *
 * @author Ricardo Contreras S.
 */
public class Reincorporacion {

    private AluCar aluCar;
    private ReincorporacionId id;
    private Date reiFecha;
    private Integer reiEstado;
    private Integer reiSolicitud;
    private Integer reiTipo;
    private Integer reiNomina;

    /**
     *
     */
    public Reincorporacion() {
    }

    /**
     *
     * @return
     */
    public ReincorporacionId getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(ReincorporacionId id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public Integer getReiTipo() {
        return reiTipo;
    }

    /**
     *
     * @param reiTipo
     */
    public void setReiTipo(Integer reiTipo) {
        this.reiTipo = reiTipo;
    }

    /**
     *
     * @return
     */
    public Integer getReiEstado() {
        return reiEstado;
    }

    /**
     *
     * @param reiEstado
     */
    public void setReiEstado(Integer reiEstado) {
        this.reiEstado = reiEstado;
    }

    /**
     *
     * @return
     */
    public Integer getReiSolicitud() {
        return reiSolicitud;
    }

    /**
     *
     * @param reiSolicitud
     */
    public void setReiSolicitud(Integer reiSolicitud) {
        this.reiSolicitud = reiSolicitud;
    }

    /**
     *
     * @return
     */
    public Date getReiFecha() {
        return getDateGetterSetter(reiFecha);
    }

    /**
     *
     * @param reiFecha
     */
    public void setReiFecha(Date reiFecha) {
        this.reiFecha = getDateGetterSetter(reiFecha);
    }

    /**
     *
     * @return
     */
    public AluCar getAluCar() {
        return aluCar;
    }

    /**
     *
     * @param aluCar
     */
    public void setAluCar(AluCar aluCar) {
        this.aluCar = aluCar;
    }

    public Integer getReiNomina() {
        return reiNomina;
    }

    public void setReiNomina(Integer reiNomina) {
        this.reiNomina = reiNomina;
    }

}
