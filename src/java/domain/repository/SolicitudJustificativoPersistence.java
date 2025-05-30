/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.CursoId;
import domain.model.SolicitudJustificativo;
import java.util.List;

/**
 *
 * @author Ricardo
 */
public interface SolicitudJustificativoPersistence extends CrudGenericDAO<SolicitudJustificativo, Long> {

    void doSave(Integer solicitud, CursoId id);
    void doUpdate(Integer solicitud, CursoId id, String respuesta, String estado);
    List<SolicitudJustificativo> getSolicitud(Integer folio);
    List<SolicitudJustificativo> find(CursoId id);
}
