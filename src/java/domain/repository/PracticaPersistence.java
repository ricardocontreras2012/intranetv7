/*
 * @(#)PracticaPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.AluCar;
import domain.model.AluCarId;
import domain.model.Asignatura;
import domain.model.Practica;
import java.math.BigDecimal;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface PracticaPersistence extends CrudGenericDAO<Practica, Long> {
    String getHoras(Integer asignatura);
    Integer getPracticaxInscribir(AluCar alucar);
    void insert(Practica practica);
    void resolver(Practica practica);
    Practica find(Integer solicitud);
    List<Practica> getNominaxCalificar(Integer asign, Integer ago, Integer sem);
    List<Practica> getNominaxRectificar(Integer asign, Integer agno, Integer sem);
    List<Asignatura> getPracticasCoordinador(Integer rut);
    List<Asignatura> getPracticasSecretaria(Integer rut);
    void crearActa(Integer folio, Integer practica, Integer agno, Integer sem, Integer porcEmp, Integer porc_Coord, String tipo);
    void agregarNomina(AluCarId aluCarId, Integer folio, BigDecimal notaEmp,  BigDecimal notaCoord);
}
