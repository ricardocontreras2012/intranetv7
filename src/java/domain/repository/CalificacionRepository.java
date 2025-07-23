/*
 * @(#)CalificacionRepository.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.AluCar;
import domain.model.AluCarId;
import domain.model.Calificacion;
import domain.model.CursoId;
import java.util.List;
import infrastructure.support.CalificacionCertificacionSupport;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface CalificacionRepository extends CrudGenericDAO<Calificacion, Long> {

    /**
     * Method description
     *
     * @param aluCar
     * @return
     */
    List<Calificacion> find(AluCar aluCar);
    List<Calificacion> findCalProgramas(AluCar aluCar);

    /**
     * Method description
     *
     * @param rut
     * @param carrera
     * @param agnoIng
     * @param semIng
     * @param mencion
     * @param plan
     * @return
     */
    List<CalificacionCertificacionSupport> getI4NotasMalla(Integer rut, Integer carrera, Integer agnoIng,
            Integer semIng, Integer mencion, Integer plan);

    /**
     * Method description
     *
     * @param rut
     * @param carrera
     * @param agnoIng
     * @param semIng
     * @param mencion
     * @param plan
     * @return
     */
    List<CalificacionCertificacionSupport> getI4NotasOtras(Integer rut, Integer carrera, Integer agnoIng,
            Integer semIng, Integer mencion, Integer plan);
    
    List<CalificacionCertificacionSupport> getI4NotasAdicionales(Integer rut, Integer carrera, Integer agnoIng,
            Integer semIng, Integer mencion, Integer plan);

    /**
     *
     * @param cursoId
     * @return
     */
    List<Calificacion> find(CursoId cursoId);

    /**
     *
     * @param aluCarId
     * @return
     */
    Integer getUltimoAgnoCalificado(AluCarId aluCarId);
}
