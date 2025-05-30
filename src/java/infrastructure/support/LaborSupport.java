/*
 * @(#)LaborSupport.java
 *
 * Copyright (c) 2016 FAE-USACH
 */
package infrastructure.support;

import domain.model.LaborRealizada;
import java.util.List;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Ricardo Contreras S.
 */
public class LaborSupport {

    /**
     * Method description
     *
     *
     * @param rut
     * @return
     */
    public static List<LaborRealizada> getDecanoAlumno(Integer rut) {
        return ContextUtil.getDAO().getLaborRealizadaPersistence(ActionUtil.getDBUser()).findAutoridadAlumno("DE", rut);
    }

    /**
     *
     * @param rut
     * @return
     */
    public static List<LaborRealizada> getDecanoProfesor(Integer rut) {
        return ContextUtil.getDAO().getLaborRealizadaPersistence(ActionUtil.getDBUser()).findAutoridadProfesor("DE", rut);
    }

    /**
     * Method description
     *
     *
     * @param rut
     * @return
     */
    public static List<LaborRealizada> getViceDecanoDocAlumno(Integer rut) {
        return ContextUtil.getDAO().getLaborRealizadaPersistence(ActionUtil.getDBUser()).findAutoridadAlumno("VDD", rut);
    }

    /**
     *
     * @param rut
     * @return
     */
    public static List<LaborRealizada> getViceDecanoDocProfesor(Integer rut) {
        return ContextUtil.getDAO().getLaborRealizadaPersistence(ActionUtil.getDBUser()).findAutoridadProfesor("VDD", rut);
    }

    /**
     * Method description
     *
     *
     * @param rut
     * @return
     */
    public static List<LaborRealizada> getViceDecanoInvAlumno(Integer rut) {
        return ContextUtil.getDAO().getLaborRealizadaPersistence(ActionUtil.getDBUser()).findAutoridadAlumno("VDI", rut);
    }

    /**
     *
     * @param rut
     * @return
     */
    public static List<LaborRealizada> getViceDecanoInvProfesor(Integer rut) {
        return ContextUtil.getDAO().getLaborRealizadaPersistence(ActionUtil.getDBUser()).findAutoridadProfesor("VDI", rut);
    }

    /**
     *
     * @param rut
     * @return
     */
    public static List<LaborRealizada> getRegistradorAlumno(Integer rut) {   
        return ContextUtil.getDAO().getLaborRealizadaPersistence(ActionUtil.getDBUser()).findAutoridadAlumno("RC", rut);
    }

    /**
     *
     * @param rut
     * @return
     */
    public static List<LaborRealizada> getAsitenteSocial(Integer rut) {
        return ContextUtil.getDAO().getLaborRealizadaPersistence(ActionUtil.getDBUser()).findAutoridadAlumno("AS", rut);
    }

    /**
     *
     * @param rut
     * @return
     */
    public static List<LaborRealizada> getJefeBiblioteca(Integer rut) {
        return ContextUtil.getDAO().getLaborRealizadaPersistence(ActionUtil.getDBUser()).findAutoridadAlumno("JB", rut);
    }

    /**
     * Method description
     *
     *
     * @param facultad
     * @return
     */
    public static List<LaborRealizada> getJefesCarrera(Integer facultad) {
        return ContextUtil.getDAO().getLaborRealizadaPersistence(ActionUtil.getDBUser()).findAutoridad("JC", facultad);
    }

    /**
     * Method description
     *
     *
     * @param facultad
     * @return
     */
    public static List<LaborRealizada> getDirectoresDepto(Integer facultad) {
        return ContextUtil.getDAO().getLaborRealizadaPersistence(ActionUtil.getDBUser()).findAutoridad("DD", facultad);
    }

    /**
     *
     * @param rut
     * @return
     */
    public static List<LaborRealizada> getJefeCarreraAlumno(Integer rut) {
        return ContextUtil.getDAO().getLaborRealizadaPersistence(ActionUtil.getDBUser()).findAutoridadAcademicaMencionAlumno("JC", rut);
    }

    public static List<LaborRealizada> getSecretariaDocenteAlumno(Integer rut) {
        return ContextUtil.getDAO().getLaborRealizadaPersistence(ActionUtil.getDBUser()).findAutoridadAcademicaMencionAlumno("SM", rut);
    }

    public static List<LaborRealizada> getSecretariaDocenteProfesor(Integer rut) {
        return ContextUtil.getDAO().getLaborRealizadaPersistence(ActionUtil.getDBUser()).findAutoridadAcademicaMencionProfesor("SM", rut);
    }

    /**
     *
     * @param rut
     * @return
     */
    public static List<LaborRealizada> getJefeCarreraProfesor(Integer rut) {
        return ContextUtil.getDAO().getLaborRealizadaPersistence(ActionUtil.getDBUser()).findAutoridadAcademicaMencionProfesor("JC", rut);
    }

    /**
     * Method description
     *
     *
     * @param depto
     *
     * @return
     */
    public static List<LaborRealizada> getDirDepto(Integer depto) {
        return ContextUtil.getDAO().getLaborRealizadaPersistence(ActionUtil.getDBUser()).findAutoridad("DD", depto);
    }

    /**
     * Method description
     *
     *
     * @param facultad
     * @return
     */
    public static List<LaborRealizada> getDirectoresPrograma(Integer facultad) {
        return ContextUtil.getDAO().getLaborRealizadaPersistence(ActionUtil.getDBUser()).findAutoridad("DD", facultad);
    }

    /**
     * Method description
     *
     *
     * @param rut
     *
     * @return
     */
    public static List<LaborRealizada> getDirectorProgramaAlumno(Integer rut) {
        return ContextUtil.getDAO().getLaborRealizadaPersistence(ActionUtil.getDBUser()).findAutoridadAcademicaMencionAlumno("DP", rut);
    }

    /**
     *
     * @param rut
     * @return
     */
    public static List<LaborRealizada> getDirectorProgramaProfesor(Integer rut) { 
        return ContextUtil.getDAO().getLaborRealizadaPersistence(ActionUtil.getDBUser()).findAutoridadAcademicaDeptoProfesor("DP", rut);
    }

    /*public static List<Profesor> getDirectorPrograma(Integer carrera, Integer mencion) {
        return ContextUtil.getDAO().getAutoridadPersistence(
                AppStaticsUtil.USER_TYPE_COMMON).findAutoridadAcademicaMencion("Director de Programa", carrera, mencion);
    }*/
    /**
     *
     * @param rut
     * @return
     */
    public static List<LaborRealizada> getDirectorDepartamentoAlumno(Integer rut) {
        return ContextUtil.getDAO().getLaborRealizadaPersistence(ActionUtil.getDBUser()).findAutoridadAcademicaAlumnoDepto("DD", rut);
    }

    /**
     *
     * @param rut
     * @return
     */
    public static List<LaborRealizada> getDirectorDepartamentoProfesor(Integer rut) {
        return ContextUtil.getDAO().getLaborRealizadaPersistence(ActionUtil.getDBUser()).findAutoridadAcademicaDeptoProfesor("DD", rut);
    }

    /**
     * Method description
     *
     *
     * @param rut
     * @param user
     *
     * @return
     */
    public static boolean is(Integer rut, String user) {
        return ContextUtil.getDAO().getProfesorPersistence(ActionUtil.getDBUser()).trabaja(rut, user) != null;
    }
}
