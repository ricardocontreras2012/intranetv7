/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.LaborRealizada;
import java.util.List;

/**
 *
 * @author Ricardo
 */
public interface LaborRealizadaRepository extends CrudGenericDAO<LaborRealizada, Long> {

    List<LaborRealizada> findAutoridadAcademicaDeptoProfesor(String cargo, Integer rut);
    List<LaborRealizada> findAutoridadAcademicaAlumnoDepto(String cargo, Integer rut);
    List<LaborRealizada> findAutoridadAcademicaMencionAlumno(String cargo, Integer rut);
    List<LaborRealizada> findAutoridadAcademicaMencionProfesor(String cargo, Integer rut);
    List<LaborRealizada> findAutoridadAlumno(String cargo, Integer rut);
    List<LaborRealizada> findAutoridadProfesor(String cargo, Integer rut);
    List<LaborRealizada> findAutoridad(String cargo, Integer facultad);
    LaborRealizada findAutoridad(String cargo);
    LaborRealizada findLaborRealizadaMencion(Integer carrera, Integer mencion, String cargo);
}
