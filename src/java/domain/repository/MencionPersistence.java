/*
 * @(#)MencionPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.Mencion;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S and Javier Frez V.
 *
 */
public interface MencionPersistence extends CrudGenericDAO<Mencion, Long> {
    /**
     * Method description
     *
     * @param menCodCar
     * @param menCodMen
     * @return
     */
    Mencion find(Integer menCodCar, Integer menCodMen);

    /**
     * Method description
     *
     * @param tipo
     * @param rut
     * @return
     */
    List<Mencion> find(String tipo, Integer rut);
    
    /**
     * Method description
     *
     * @param codCar
     * @return
     */
    List<Mencion> findByCarrera(Integer codCar);
    
    /**
     * Method description
     *
     * @param tipo
     * @param rut
     * @param flag
     * @return
     */
    List<Mencion> findCarrerasProgramas(String tipo, Integer rut, String flag);

    /**
     *
     * @param carrera
     * @param mencion
     * @return
     */
    String getNombreCarreraFull(Integer carrera, Integer mencion);   
}
