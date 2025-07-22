/*
 * @(#)InscripcionPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.AluCar;
import domain.model.AluCarId;
import domain.model.Curso;
import domain.model.CursoId;
import domain.model.Inscripcion;
import domain.model.InscripcionId;
import java.util.List;
import domain.model.InscripcionCursoView;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface InscripcionPersistence extends CrudGenericDAO<Inscripcion, Long> {
    List<Inscripcion> getInscripcion(AluCar aluCar, Integer agnoIns, Integer semIns);
    List<Inscripcion> getInscripcionPractica(AluCarId id, Integer agnoIns, Integer semIns);
    int deleteInscripcion(AluCar aluCar, CursoId id, String proceso, Integer rutRea, String user);
    List<InscripcionCursoView> findNomina(Curso curso);
    List<InscripcionCursoView> findNominaRanking(Curso curso);
    void cambioMencion(AluCar aluCar, Integer mencion, Integer agnoIns, Integer semIns);
    List<Integer> getRutInscritos(CursoId id);
    List<Curso> getCursosSwap(AluCar aluCar, CursoId id);
    void swap(AluCarId id, CursoId oldId, CursoId newId );
    void traspaso(AluCarId id, CursoId oldId, CursoId newId);
    void setForce(AluCarId id, InscripcionId idIns, String force);
    String getResumen(Integer tcarrera, Integer especialidad, String jornada, Integer agno, Integer sem, Integer rut, String perfil);
}
