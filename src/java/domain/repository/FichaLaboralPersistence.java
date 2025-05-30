/*
 * @(#)FichaLaboralPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.FichaLaboral;
import java.util.List;

/**
 *
 * @author Alvaro Romero C.
 */
public interface FichaLaboralPersistence extends CrudGenericDAO<FichaLaboral, Long> {

    /**
     * Method description
     *
     *
     * @param rut
     *
     * @return
     */
    List<FichaLaboral> find(Integer rut);

    /**
     * Method description
     *
     *
     * @param rut
     * @param correl
     *
     * @return
     */
    FichaLaboral find(Integer rut, Integer correl);

    /**
     * Method description
     *
     *
     * @param correl
     * @param alumnoEmpleador
     * @param areaTrabajo
     * @param region
     * @param comuna
     * @param otroLugar
     * @param cargo
     * @param tipoTrabajo
     * @param sueldo
     * @param desdeAgno
     * @param desdeMes
     * @param hastaAgno
     * @param hastaMes
     * @param descripcion
     */
    void updateLaboral(Integer correl, Integer alumnoEmpleador,
            Integer areaTrabajo, Integer region, Integer comuna, String otroLugar, String cargo, Integer tipoTrabajo,
            Integer sueldo, Integer desdeAgno, Integer desdeMes, Integer hastaAgno, Integer hastaMes,
            String descripcion);

    /**
     * Method description
     *
     *
     * @param correl
     * @param alumnoEmpleador
     * @param areaTrabajo
     * @param region
     * @param comuna
     * @param otroLugar
     * @param cargo
     * @param tipoTrabajo
     * @param sueldo
     * @param desdeAgno
     * @param desdeMes
     * @param hastaAgno
     * @param hastaMes
     * @param descripcion
     */
    void createLaboral(Integer correl, Integer alumnoEmpleador, Integer areaTrabajo,
            Integer region, Integer comuna, String otroLugar, String cargo, Integer tipoTrabajo,
            Integer sueldo, Integer desdeAgno, Integer desdeMes, Integer hastaAgno, Integer hastaMes,
            String descripcion);

    /**
     * Method description
     *
     *
     * @param correl
     */
    void deleteLaboral(Integer correl);
}
