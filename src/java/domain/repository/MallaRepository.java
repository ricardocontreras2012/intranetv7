/*
 * @(#)MallaPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.AluCar;
import domain.model.Malla;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface MallaRepository extends CrudGenericDAO<Malla, Long> {
    String getMallaJson(AluCar aluCar);   
    List<Object[]> getCalificacionesAsignatura(AluCar aluCar, int asignatura);
    Integer getSctNivel(AluCar aluCar);
    List<Object[]> porConvalidar(AluCar aluCar);
    List<Object[]> porConvalidarSolicitud(AluCar aluCar, Integer solicitud);
    List<Malla> getMalla(Integer tcarrera, Integer especialidad, String jornada, Integer rut, String cargo);    
    List<Malla> getElectivosMalla(Integer tcarrera, Integer especialidad, String jornada, Integer rut, String cargo);
    Integer getCreditosNivel(AluCar aluCar);
}
