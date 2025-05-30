/*
 * @(#)AsistenciaAlumno.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import static infrastructure.util.DateUtil.getDateGetterSetter;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public class AsistenciaAlumno implements Serializable {

    private static final long serialVersionUID = 1L;
    private Set<AsistenciaAlumnoNomina> nomina = new HashSet<>(0);
    private Integer asaCorrel;
    private Date asaFecha;
    private Curso curso;

    /**
     *
     */
    public AsistenciaAlumno() {
    }

    /**
     * Method description
     *
     * @return a defensive copy of the field. The caller may change the state of
     * the returned object in any way, without affecting the internals of this
     * class.
     */
    public Date getAsaFecha() {
        return getDateGetterSetter(asaFecha);
    }

    /**
     * Method description
     *
     * @param asaFecha
     */
    public void setAsaFecha(Date asaFecha) {
        this.asaFecha = getDateGetterSetter(asaFecha);
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getAsaCorrel() {
        return asaCorrel;
    }

    /**
     * Method description
     *
     * @param asaCorrel
     */
    public void setAsaCorrel(Integer asaCorrel) {
        this.asaCorrel = asaCorrel;
    }

    /**
     * Method description
     *
     * @return
     */
    public Curso getCurso() {
        return curso;
    }

    /**
     * Method description
     *
     * @param curso
     */
    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    /**
     * Method description
     *
     * @return
     */
    public Set<AsistenciaAlumnoNomina> getNomina() {
        return nomina;
    }

    /**
     * Method description
     *
     * @param nomina
     */
    public void setNomina(Set<AsistenciaAlumnoNomina> nomina) {
        this.nomina = nomina;
    }
}
