/*
 * @(#)ElectivoPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.Electivo;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface ElectivoRepository extends CrudGenericDAO<Electivo, Long> {

    /**
     * Method description
     *
     * @param asign
     * @param elect
     * @param agno
     * @param sem
     * @return
     */
    Electivo find(Integer asign, String elect, Integer agno, Integer sem);
    List<Electivo> find(Integer tcarrera, Integer especialidad, Integer agno, Integer sem, Integer rut, String perfil);
    void add(Integer asign, String elect, String electivo, Integer minor, Integer area, String Tipo, Integer agno, Integer sem);
    void modify(Integer asign, String elect, String electivo, Integer minor, Integer area, String Tipo, Integer agno, Integer sem);
    void delete(Integer asign, String elect, Integer agno, Integer sem);
}
