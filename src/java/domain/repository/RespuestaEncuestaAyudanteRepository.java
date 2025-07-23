/*
 * @(#)RespuestaEncuestaAyudantePersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.AluCar;
import domain.model.CursoAyudante;
import domain.model.EncuestaAyudante;
import domain.model.RespuestaEncuestaAyudante;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface RespuestaEncuestaAyudanteRepository extends CrudGenericDAO<RespuestaEncuestaAyudante, Long> {

    /**
     * Method description
     *
     * @param aluCar
     * @param encuesta
     * @param cursoAyudante
     * @return
     */
    List<RespuestaEncuestaAyudante> find(AluCar aluCar, EncuestaAyudante encuesta, CursoAyudante cursoAyudante);

    /**
     * Method description
     *
     * @param aluCar
     * @param encuesta
     * @return
     */
    Long find(AluCar aluCar, EncuestaAyudante encuesta);

    /**
     * Method description
     *
     * @param aluCar
     * @param cursoAyudante
     * @param preg
     * @param resp
     * @param correl
     */
    void doSave(AluCar aluCar, CursoAyudante cursoAyudante, Integer preg, Integer resp,
            Integer correl);
}
