/*
 * @(#)RespuestaEncuestaDocentePersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.AluCar;
import domain.model.CursoProfesor;
import domain.model.EncuestaDocente;
import domain.model.RespuestaEncuestaDocente;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface RespuestaEncuestaDocentePersistence extends CrudGenericDAO<RespuestaEncuestaDocente, Long> {

    /**
     * Method description
     *
     * @param aluCar
     * @param encuesta
     * @param cursoProfesor
     * @return
     */
    List<RespuestaEncuestaDocente> find(AluCar aluCar, EncuestaDocente encuesta, CursoProfesor cursoProfesor);

    /**
     * Method description
     *
     * @param aluCar
     * @param encuesta
     * @return
     */
    Long find(AluCar aluCar, EncuestaDocente encuesta);

    /**
     * Method description
     *
     * @param aluCar
     * @param cursoProfesor
     * @param apartado
     * @param preg
     * @param resp
     * @param correl
     */
    void doSave(AluCar aluCar, CursoProfesor cursoProfesor, Integer apartado, Integer preg, Integer resp, Integer correl);
    
    /**
     * Method description
     *
     * @param aluCar
     * @param cursoProfesor
     * @param preg
     * @param resp
     * @param correl
     */
    void doSaveEncuestaIntermedia(AluCar aluCar, CursoProfesor cursoProfesor, Integer preg, Integer resp, Integer correl);
}
