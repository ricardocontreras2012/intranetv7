/*
 * @(#)AyudantePersistence.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.Ayudante;
import domain.model.Curso;
import domain.model.CursoAyudante;
import domain.model.CursoId;
import java.util.List;
import domain.model.AyudanteActivoView;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface AyudantePersistence extends CrudGenericDAO<Ayudante, Long> {

    /**
     * Method description
     *
     * @param rut
     * @param password
     * @return
     */
    Ayudante find(Integer rut, String password);

    /**
     * Method description
     *
     * @param rut
     * @return
     */
    Ayudante find(Integer rut);

    /**
     * Method description
     *
     * @param paterno
     * @param materno
     * @param nombre
     * @return
     */
    List<Ayudante> find(String paterno, String materno, String nombre);

    /**
     *
     * @param rut
     * @return
     */
    Ayudante findFull(Integer rut);
    /**
     * Method description
     *
     * @param rut
     * @return
     */
    List<Curso> findCarga(Integer rut);

    /**
     * Method description
     *
     * @param curso
     * @return
     */
    List<CursoAyudante> find(Curso curso);

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
     * @param email
     */
    void setEmail(Integer rut, String email);

    /**
     * Method description
     *
     * @param rut
     */
    void setLastLogin(Integer rut);

    /**
     * Method description
     *
     * @param facultad
     * @return
     */
    List<AyudanteActivoView> findAyudantesActivosFacultad(Integer facultad);

    /**
     * Method description
     *
     * @param depto
     * @return
     */
    List<AyudanteActivoView> findAyudantesActivosDepartamento(Integer depto);

    /**
     * Method description
     *
     * @param carrera
     * @param mencion
     * @return
     */
    List<AyudanteActivoView> findAyudantesActivosCarrera(Integer carrera, Integer mencion);

    List<CursoAyudante> findCargaHistorica(Integer rut);
    List<CursoAyudante> find(CursoId cursoId);
    void creaAyudante(Integer rut);
    List<Curso> getCursos(Integer rut);
}
