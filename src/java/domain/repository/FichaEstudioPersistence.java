/*
 * @(#)FichaEstudioPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.FichaEstudio;
import java.util.List;

/**
 *
 * @author Alvaro Romero C.
 */
public interface FichaEstudioPersistence extends CrudGenericDAO<FichaEstudio, Long> {

    /**
     * Method description
     *
     *
     * @param rut
     *
     * @return
     */
    List<FichaEstudio> find(Integer rut);

    /**
     * Method description
     *
     *
     * @param rut
     * @param correl
     *
     * @return
     */
    FichaEstudio find(Integer rut, Integer correl);

    /**
     * Method description
     *
     *
     * @param correl
     * @param pais
     * @param institucionEducacional
     * @param otraInstitucion
     * @param tipoEstudio
     * @param nombreEstudio
     * @param desdeAgno
     * @param desdeMes
     * @param hastaAgno
     * @param hastaMes
     * @param estadoEstudio
     * @param areaEstudio
     */
    void updateEstudio(Integer correl, Integer pais, Integer institucionEducacional, String otraInstitucion,
            Integer tipoEstudio, String nombreEstudio, Integer desdeAgno, Integer desdeMes,
            Integer hastaAgno, Integer hastaMes, Integer estadoEstudio, Integer areaEstudio);

    /**
     * Method description
     *
     *
     * @param correl
     * @param alumno
     * @param pais
     * @param institucionEducacional
     * @param otraInstitucion
     * @param tipoEstudio
     * @param nombreEstudio
     * @param desdeAgno
     * @param desdeMes
     * @param hastaAgno
     * @param hastaMes
     * @param estadoEstudio
     * @param areaEstudio
     */
    void createEstudio(Integer correl, Integer alumno, Integer pais, Integer institucionEducacional,
            String otraInstitucion, Integer tipoEstudio, String nombreEstudio, Integer desdeAgno,
            Integer desdeMes, Integer hastaAgno, Integer hastaMes, Integer estadoEstudio,
            Integer areaEstudio);

    /**
     * Method description
     *
     *
     * @param correl
     * @param alumno
     */
    void deleteEstudio(Integer correl, Integer alumno);
}
