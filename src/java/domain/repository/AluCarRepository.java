/*
 * @(#)AluCarRepository.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.AluCar;
import domain.model.AluCarId;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface AluCarRepository extends CrudGenericDAO<AluCar, Long> {

    /**
     * Method description
     *
     * @param rut
     * @return
     */
    List<AluCar> find(Integer rut);

    /**
     * Method description
     *
     * @param aluCarId
     * @return
     */
    AluCar find(AluCarId aluCarId);

    /**
     * Method description
     *
     * @param rut
     * @param tipoCarrera
     * @param especialida
     * @param jornada
     * @return
     */
    List<AluCar> findActivo(Integer rut, Integer tipoCarrera, Integer especialida, String jornada);

    /**
     * Method description
     *
     * @param rut
     * @return
     */
    List<AluCar> findEgresado(Integer rut);

    /**
     *
     * @param aluCarId
     * @return
     */
    String tipoAlumno(AluCarId aluCarId);

    void generaExamenAP(AluCarId id);

    Integer isAlumnoPropio(AluCar aluCar, Integer rut, String userType);
    
    AluCar getAluCarCertificado(Integer correl);
    
    Float getPromedioEgreso(AluCarId id, Integer mencion,  Integer plan);
    Float getPromedioRanking(AluCarId id, Integer mencion,  Integer plan);
    List<AluCar> find(Integer tipoCarrera, Integer especialidad, String regimen);
    void generaLogros(AluCarId id, Integer mencion, Integer plan);
}
