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
public interface CursoEspejoPersistence extends CrudGenericDAO<CursoEspejo, Long> {

    CursoEspejo getEspejo(CursoId curso);

    List<CursoEspejo> find(Integer tcarrera, Integer especialidad, String jornada, Integer agno, Integer sem, Integer rut, String tipo);

    void add(Integer asign_tr, String elect_tr, String coord_tr, Integer secc_tr, Integer agno_tr, Integer sem_tr,
            Integer asign, String elect, String coord, Integer secc, Integer agno, Integer sem);

    void remove(Integer asign_tr, String elect_tr, String coord_tr, Integer secc_tr, Integer agno_tr, Integer sem_tr,
            Integer asign, String elect, String coord, Integer secc, Integer agno, Integer sem);
}
