/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.CursoId;
import domain.model.SolicitudInscripcion;
import java.util.List;

/**
 *
 * @author Ricardo
 */
public interface SolicitudInscripcionRepository extends CrudGenericDAO<SolicitudInscripcion, Long> {

    void doSave(Integer solicitud, CursoId id, Integer motivo, String otro);
    List<SolicitudInscripcion> getSolicitud(Integer folio);

}
