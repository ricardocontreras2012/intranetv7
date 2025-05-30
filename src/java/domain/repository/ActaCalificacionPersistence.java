/*
 * @(#)ActaCalificacionPersistence.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.ActaCalificacion;
import domain.model.AluCar;
import domain.model.AluCarId;
import domain.model.Curso;
import domain.model.CursoId;
import java.math.BigDecimal;
import java.util.List;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface ActaCalificacionPersistence extends CrudGenericDAO<ActaCalificacion, Long> {
    

    /**
     * Method description
     *
     * @param curso
     * @return
     */
    List<ActaCalificacion> puedeEmitir(Curso curso);

    /**
     * Method description
     *
     * @param curso
     */
    void emiteActas(Curso curso);   

    /**
     * Method description
     *
     * @param agno
     * @param sem
     * @param correl
     * @param flag
     */
    void generaActasxCarrera(Integer agno, Integer sem, Integer correl, String flag);
    void recepcionarActa(Integer agno, Integer sem, Integer correl);

    /**
     * Method description
     *
     * @param agno
     * @param sem
     * @return
     */
    List<ActaCalificacion> findActasxImprimir(Integer agno, Integer sem);

    /**
     * Method description
     *
     * @param agno
     * @param sem
     * @return
     */

    /**
     * Method description
     *
     *
     * @param folio
     * @param agno
     * @param sem
     * @param estado
     */
    void putActaEstado(Integer folio, Integer agno, Integer sem, String estado);

   
    void crearActa(Integer folio, Integer agno, Integer sem, Integer asign, String elect, String coord, Integer secc, String tel,String tipo, String estado, String tipoAlumno);

    void fix(Integer folio);

    List<ActaCalificacion> findActasxEstado(Integer agno, Integer sem, String estado);

    void generarActaComplementariaCoordinador(AluCarId aluCarId, CursoId id, BigDecimal nota, String concepto, Integer rutReali);
    List<Curso> getCursosActaComplementaria(AluCar aluCar, Integer agnoCal,
            Integer semCal, Integer rut, String user);
    Integer isExisteActaCoordinador(AluCarId aluCarId, CursoId cursoId);
}
