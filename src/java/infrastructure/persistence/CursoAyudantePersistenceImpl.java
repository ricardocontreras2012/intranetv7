/*
 * @(#)CursoAyudantePersistenceImpl.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.CursoAyudantePersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.AluCarId;
import domain.model.CursoAyudante;
import java.util.List;
import org.hibernate.Query;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CursoAyudantePersistenceImpl extends CrudAbstractDAO<CursoAyudante, Long>
        implements CursoAyudantePersistence {

    /**
     * Method description
     *
     *
     * @param id
     * @param agno
     * @param sem
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CursoAyudante> getCursosEncuesta(AluCarId id, Integer agno, Integer sem) {
        String strQuery = "SELECT curso_ayudante.* FROM curso_ayudante, inscripcion WHERE "
                + "cayu_asign = ins_asign AND cayu_elect = ins_elect AND cayu_coord = ins_coord AND "
                + "cayu_secc = ins_secc AND cayu_agno = ins_agno AND cayu_sem = ins_sem AND " + "cayu_agno="
                + agno + " AND cayu_sem=" + sem + " AND " + "ins_rut =" + id.getAcaRut() + " AND "
                + "ins_cod_car=" + id.getAcaCodCar() + " AND " + "ins_agno_ing="
                + id.getAcaAgnoIng() + " AND " + "ins_sem_ing=" + id.getAcaSemIng()
                + " ORDER BY " + "cayu_asign ASC, cayu_elect ASC, cayu_coord ASC, cayu_secc ASC";
        Query query = getSession().createSQLQuery(strQuery).addEntity("CursoAyudante", CursoAyudante.class);

        return query.list();
    }    
}
