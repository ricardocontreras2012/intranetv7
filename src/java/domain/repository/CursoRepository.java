/*
 * @(#)CursoRepository.java
 *
 * Copyright (c) Copyright (c) 2025 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.AluCar;
import domain.model.Curso;
import domain.model.CursoId;
import java.util.List;
import infrastructure.support.CursoResumenSupport;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface CursoRepository extends CrudGenericDAO<Curso, Long> {

    /**
     *
     * @param rut
     * @param agno
     * @param sem
     * @param proyecto
     * @return
     */
    List<Curso> find(Integer rut, Integer agno, Integer sem, String proyecto);
    List<Curso> findAyudantia(Integer rut, Integer agno, Integer sem, String proyecto);

    /**
     * Method description
     *
     * @param asignatura
     * @return
     */
    List<Curso> find(Integer asignatura);

    /**
     * Method description
     *
     * @param asignatura
     * @param agno
     * @param sem
     * @param carrera
     * @return
     */
    List<Curso> find(Integer asignatura, Integer agno, Integer sem, Integer carrera);

    /**
     *
     * @param rut
     * @param userType
     * @param asignatura
     * @param agno
     * @param sem
     * @return
     */
    List<Curso> findAll(Integer rut, String userType,Integer asignatura, Integer agno, Integer sem);

    /**
     * Method description
     *
     * @param curso
     * @return
     */
    Curso find(Curso curso);

    /**
     * Method description
     *
     * @param cursoId
     * @return
     */
    Curso find(CursoId cursoId);

    List<Curso> findTransversales( Integer agno, Integer sem);

    /**
     * Method description
     *
     * @param agno
     * @param sem
     * @param tipoCarrera
     * @param especialidad
     * @param jornada
     * @return
     */
    List<CursoResumenSupport> findResumenReportes(Integer agno, Integer sem, Integer tipoCarrera,
            Integer especialidad, String jornada);

    /**
     *
     * @param rut
     * @param agno
     * @param sem
     * @return
     */
    List<CursoResumenSupport> findResumenReportes(Integer rut, Integer agno, Integer sem);

    /**
     *
     * @param rut
     * @param agno
     * @param sem
     * @return
     */
    List<CursoResumenSupport> findResumenMateriales(Integer rut, Integer agno, Integer sem);

    /**
     * Method description
     *
     * @param agno
     * @param sem
     * @param tipoCarrera
     * @param especialidad
     * @param jornada
     * @return
     */
    List<CursoResumenSupport> findResumenMateriales(Integer agno, Integer sem, Integer tipoCarrera,
            Integer especialidad, String jornada);

    /**
     *
     * @param cursoId
     * @param modo
     */
    void setModoEvaluacion(CursoId cursoId, String modo);

    List<Curso> find(Integer tcarrera, Integer especialidad, String jornada, Integer agno, Integer sem, Integer rut, String perfil, String tipo);
    List<Curso> findxPerfilPeriodo(Integer agno, Integer sem, Integer rut, String perfil);

    List<Curso> findxUser(Integer rut, String tipo);

    //List<Curso> find(Integer agno, Integer sem, Integer tipoCarrera, Integer especialidad, String jornada, Integer rut, String tipo);

    List<Curso> getCursosSolicitudInscripcion(AluCar aluCar, Integer agno, Integer sem);
    int remove(CursoId cursoId);

    void addTransversal(
            Integer asign, String elect, String coord, Integer secc, Integer agno, Integer sem);
    void removeTransversal(
            Integer asign, String elect, String coord, Integer secc, Integer agno, Integer sem);
}
