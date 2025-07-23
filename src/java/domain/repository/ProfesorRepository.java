/*
 * @(#)ProfesorPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.Curso;
import domain.model.CursoId;
import domain.model.CursoProfesor;
import domain.model.Profesor;
import java.util.List;
import domain.model.ProfesorActivoView;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface ProfesorRepository extends CrudGenericDAO<Profesor, Long> {

    /**
     * Method description
     *
     * @param rut
     * @param password
     * @return
     */
    Profesor find(Integer rut, String password);

    /**
     * Method description
     *
     * @param rut
     * @return
     */
    Profesor find(Integer rut);

    /**
     * Method description
     *
     * @param paterno
     * @param materno
     * @param nombre
     * @return
     */
    List<Profesor> find(String paterno, String materno, String nombre);

    /**
     * Method description
     *
     * @param rut
     * @param password
     */
    void setPassword(Integer rut, String password);

    /**
     * Method description
     *
     * @param rut
     * @return
     */
    Profesor getMisDatos(Integer rut);

    /**
     * Method description
     *
     * @param rut
     * @param email
     * @param fono
     */
    void setMisDatos(Integer rut, String email, 
            //Date fechaNac, String direccion, Integer comuna, 
            String fono);

    /**
     * Method description
     *
     * @param rut
     */
    void setLastLogin(Integer rut);

    /**
     * Method description
     *
     * @param rut
     * @return
     */
    List<Curso> findCargaAcademica(Integer rut);

    /**
     * Method description
     *
     * @param rut
     * @return
     */
    List<Curso> findCargaHistorica(Integer rut);

    /**
     * Method description
     *
     * @param rut
     * @param agnoInicio
     * @return
     */
    List<Curso> findCursosMaterialHistorico(Integer rut, Integer agnoInicio);

    /**
     * Method description
     *
     * @param curso
     * @return
     */
    List<CursoProfesor> findProfesor(Curso curso);

    List<CursoProfesor> findProfesor(CursoId cursoId);
    
    List<Profesor> getProfesores(CursoId id);

    /**
     * Method description
     *
     * @param rut
     * @return
     */
    List<CursoProfesor> findCursosActas(Integer rut);

    /**
     * Method description
     *
     * @param facultad
     * @return
     */
    List<ProfesorActivoView> findProfesoresActivosFacultad(Integer facultad);

    /**
     * Method description
     *
     * @param depto
     * @return
     */
    List<ProfesorActivoView> findProfesoresActivosDepartamento(Integer depto);

    /**
     * Method description
     *
     * @param carrera
     * @param mencion
     * @return
     */
    List<ProfesorActivoView> findProfesoresActivosCarrera(Integer carrera, Integer mencion);
    List<ProfesorActivoView> findProfesoresActivosCarrera(Integer uniCod);

    /**
     * Method description
     *
     * @param profesor
     * @param alumno
     * @return
     */
    String esProfesorDe(Integer profesor, Integer alumno);

    List<Profesor> findxUser(Integer rut, String tipo);

    List<Curso> findCursosActaRectificatoria(Integer rut);

    void creaProfesor(Integer rut);

    Profesor trabaja(Integer rut, String trabajo);
}
