/*
 * @(#)MallaPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.AluCar;
import domain.model.Malla;
import domain.model.PlanId;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface MallaPersistence extends CrudGenericDAO<Malla, Long> {
    /**
     * Method description
     *
     *
     * @param aluCar
     *
     * @return
     */
    List<Object[]> getCalificacionesMalla(AluCar aluCar);

    /**
     * Method description
     *
     *
     * @param aluCar
     *
     * @return
     */
    List<Object[]> getInscripcionesMalla(AluCar aluCar);

    /**
     * Method description
     *
     *
     * @param aluCar
     *
     * @return
     */
    int getNextElectivo(AluCar aluCar);

    /**
     * Method description
     *
     *
     * @param aluCar
     * @param asignatura
     *
     * @return
     */
    List<Object[]> getCalificacionesAsignatura(AluCar aluCar, int asignatura);

    /**
     * Method description
     *
     *
     * @param planId
     * @param correl
     *
     * @return
     */
    int getAsignaturaElectiva(PlanId planId, int correl);

    /**
     * Method description
     *
     *
     * @param aluCar
     *
     * @return
     */
    Object[] getNextElectivoxTipo(AluCar aluCar);

    /* Object[] getMaxElectivosxTipo(AluCar aluCar); */
    /**
     * Method description
     *
     *
     * @param planId
     * @param asignatura
     *
     * @return
     */
    String getTipoElect(PlanId planId, int asignatura);

    /**
     * Method description
     *
     *
     * @param planId
     * @param correl
     * @param tipo
     *
     * @return
     */
    int getAsignaturaElectivaxTipo(PlanId planId, int correl, String tipo);

    /**
     *
     * @param aluCar
     * @return
     */
    Integer getCreditosNivel(AluCar aluCar);

    /**
     *
     * @param aluCar
     * @return
     */
    Integer getSctNivel(AluCar aluCar);

    /**
     *
     * @param aluCar
     * @return
     */
    List<Object[]> porConvalidar(AluCar aluCar);
    List<Object[]> porConvalidarSolicitud(AluCar aluCar, Integer solicitud);
    List<Malla> getMalla(Integer tcarrera, Integer especialidad, String jornada, Integer rut, String cargo);
    List<Malla> getElectivosMalla(Integer tcarrera, Integer especialidad, String jornada, Integer rut, String cargo);
}
