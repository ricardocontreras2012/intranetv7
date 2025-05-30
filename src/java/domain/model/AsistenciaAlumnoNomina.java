/*
 * @(#)AsistenciaAlumnoNomina.java
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
public class AsistenciaAlumnoNomina implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer aanAsistio;
    private AluCar aluCar;
    private AsistenciaAlumno asistenciaAlumno;
    private AsistenciaAlumnoNominaId id;

    /**
     *
     */
    public AsistenciaAlumnoNomina() {
    }

    /**
     *
     * @return
     */
    public Integer getAanAsistio() {
        return aanAsistio;
    }

    /**
     *
     * @param aanAsistio
     */
    public void setAanAsistio(Integer aanAsistio) {
        this.aanAsistio = aanAsistio;
    }

    /**
     * Method description
     *
     * @return
     */
    public AsistenciaAlumnoNominaId getId() {
        return id;
    }

    /**
     * Method description
     *
     * @param id
     */
    public void setId(AsistenciaAlumnoNominaId id) {
        this.id = id;
    }

    /**
     * Method description
     *
     * @return
     */
    public AluCar getAluCar() {
        return aluCar;
    }

    /**
     * Method description
     *
     * @param aluCar
     */
    public void setAluCar(AluCar aluCar) {
        this.aluCar = aluCar;
    }

    /**
     * Method description
     *
     * @return
     */
    public AsistenciaAlumno getAsistenciaAlumno() {
        return asistenciaAlumno;
    }

    /**
     * Method description
     *
     * @param asistenciaAlumno
     */
    public void setAsistenciaAlumno(AsistenciaAlumno asistenciaAlumno) {
        this.asistenciaAlumno = asistenciaAlumno;
    }
}
