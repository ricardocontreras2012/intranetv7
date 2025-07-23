/*
 * @(#)SacarreraPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.AluCar;
import domain.model.AluCarId;
import domain.model.Sacarrera;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface SacarreraRepository extends CrudGenericDAO<Sacarrera, Long> {

    /**
     * Method description
     *
     * @param aluCar
     * @return
     */
    List<Sacarrera> find(AluCar aluCar);
    void retiroSinExp(AluCarId aluCarId, Integer agno, Integer sem, Integer docto);
    void retiroConExp(AluCarId aluCarId, Integer agno, Integer sem, Integer docto, String fecha);
    void renuncia(AluCarId aluCarId, Integer agno, Integer sem, String motivo, Integer docto);    
    void prorroga(AluCarId aluCarId, Integer agno, Integer sem, Integer docto);
    void reincorporacionEliminacion(AluCarId aluCarId, Integer agno, Integer sem, Integer docto, String fecha, String obs);
    void reincorporacionAbandono(AluCarId aluCarId, Integer agno, Integer sem, Integer docto, String fecha, String obs); 
    void reincorporacionNoTitulacion(AluCarId aluCarId, Integer agno, Integer sem, Integer docto, String fecha, String obs); 
}
