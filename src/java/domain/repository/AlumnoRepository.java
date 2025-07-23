/*
 * @(#)AlumnoRepository.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.Alumno;
import java.util.List;
import domain.model.AlumnoActivoView;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface AlumnoRepository extends CrudGenericDAO<Alumno, Long> {

    /**
     * Method description
     *
     * @param rut
     * @param password
     * @return
     */
    Alumno find(Integer rut, String password);

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
    Alumno getMisDatos(Integer rut);

    /**
     * Method description
     *
     * @param rut
     * @param email
     * @param emailLaboral
     * @param direccion
     * @param comuna
     * @param fono
     * @param estadoCivil
     */
    void setMisDatos(Integer rut, String email, String emailLaboral, String direccion, Integer comuna, String fono, Integer estadoCivil);

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
    Alumno find(Integer rut);

    /**
     * Method description
     *
     * @param rut
     * @return
     */
    Alumno findFull(Integer rut);

    /**
     * Method description
     *
     * @param paterno
     * @param materno
     * @param nombre
     * @return
     */
    List<Alumno> find(String paterno, String materno, String nombre);

    /**
     * Method description
     *
     * @param facultad
     * @return
     */
    List<AlumnoActivoView> findAlumnosActivosFacultad(Integer facultad);

    /**
     * Method description
     *
     * @param depto
     * @return
     */
    List<AlumnoActivoView> findAlumnosActivosDepartamento(Integer depto);

    /**
     * Method description
     *
     * @param carrera
     * @param mencion
     * @return
     */
    List<AlumnoActivoView> findAlumnosActivosCarrera(Integer carrera, Integer mencion);
    List<AlumnoActivoView> findAlumnosActivosCarrera(Integer uniCod);
    List<AlumnoActivoView> findAlumnosActivosNivelCarrera(Integer carrera, Integer mencion, Integer nivel);
    void setEmail(Integer rut, String mail);
   
}
