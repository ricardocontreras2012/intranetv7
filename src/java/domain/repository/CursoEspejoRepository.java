/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.repository;

import domain.model.CursoEspejo;
import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.CursoId;
import java.util.List;

/**
 *
 * @author rcontreras
 */
public interface CursoEspejoRepository extends CrudGenericDAO<CursoEspejo, Long> {

    CursoEspejo getEspejo(CursoId curso);

    List<CursoEspejo> find(Integer tcarrera, Integer especialidad, String jornada, Integer agno, Integer sem, Integer rut, String tipo);

    void add(Integer asignTr, String electTr, String coordTr, Integer seccTr, Integer agnoTr, Integer semTr,
            Integer asign, String elect, String coord, Integer secc, Integer agno, Integer sem);

    void remove(Integer asignTr, String electTr, String coordTr, Integer seccTr, Integer agnoTr, Integer semTr,
            Integer asign, String elect, String coord, Integer secc, Integer agno, Integer sem);
}
