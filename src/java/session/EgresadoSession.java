/*
 * @(#)EgresadoSession.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package session;

import domain.model.Alumno;
import domain.model.AlumnoEmpleador;
import domain.model.AreaTrabajo;
import domain.model.FichaEstudio;
import domain.model.FichaLaboral;
import java.util.List;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class EgresadoSession {

    private Alumno alumno;

    /*Alvaro Romero*/
    private FichaEstudio fichaEstudio;
    private List<FichaEstudio> fichaEstudioList;
    private FichaLaboral fichaLaboral;
    private List<FichaLaboral> fichaLaboralList;
    private AlumnoEmpleador alumnoEmpleador;
    private List<AlumnoEmpleador> alumnoEmpleadorList;
    private AreaTrabajo areaTrabajo;
    private List<AreaTrabajo> areaTrabajoList;
    private Integer agno;

    /**
     * Method description
     *
     * @return
     */
    public Alumno getAlumno() {
        return alumno;
    }

    /**
     * Method description
     *
     * @param alumno
     */
    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public FichaEstudio getFichaEstudio() {
        return fichaEstudio;
    }

    public void setFichaEstudio(FichaEstudio fichaEstudio) {
        this.fichaEstudio = fichaEstudio;
    }

    public List<FichaEstudio> getFichaEstudioList() {
        return fichaEstudioList;
    }

    public void setFichaEstudioList(List<FichaEstudio> fichaEstudioList) {
        this.fichaEstudioList = fichaEstudioList;
    }

    public FichaLaboral getFichaLaboral() {
        return fichaLaboral;
    }

    public void setFichaLaboral(FichaLaboral fichaLaboral) {
        this.fichaLaboral = fichaLaboral;
    }

    public List<FichaLaboral> getFichaLaboralList() {
        return fichaLaboralList;
    }

    public void setFichaLaboralList(List<FichaLaboral> fichaLaboralList) {
        this.fichaLaboralList = fichaLaboralList;
    }

    public AlumnoEmpleador getAlumnoEmpleador() {
        return alumnoEmpleador;
    }

    public void setAlumnoEmpleador(AlumnoEmpleador alumnoEmpleador) {
        this.alumnoEmpleador = alumnoEmpleador;
    }

    public List<AlumnoEmpleador> getAlumnoEmpleadorList() {
        return alumnoEmpleadorList;
    }

    public void setAlumnoEmpleadorList(List<AlumnoEmpleador> alumnoEmpleadorList) {
        this.alumnoEmpleadorList = alumnoEmpleadorList;
    }

    public AreaTrabajo getAreaTrabajo() {
        return areaTrabajo;
    }

    public void setAreaTrabajo(AreaTrabajo areaTrabajo) {
        this.areaTrabajo = areaTrabajo;
    }

    public List<AreaTrabajo> getAreaTrabajoList() {
        return areaTrabajoList;
    }

    public void setAreaTrabajoList(List<AreaTrabajo> areaTrabajoList) {
        this.areaTrabajoList = areaTrabajoList;
    }

    public Integer getAgno() {
        return agno;
    }

    public void setAgno(Integer agno) {
        this.agno = agno;
    }
    
}
