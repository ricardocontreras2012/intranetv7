/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.persistence;

import domain.repository.SolicitudInscripcionPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.CursoId;
import domain.model.SolicitudInscripcion;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.FetchMode.JOIN;
import org.hibernate.Query;
import static org.hibernate.criterion.Order.desc;
import static org.hibernate.criterion.Restrictions.eq;
import org.hibernate.type.StandardBasicTypes;

/**
 *
 * @author Ricardo
 */
public final class SolicitudInscripcionPersistenceImpl extends CrudAbstractDAO<SolicitudInscripcion, Long> implements SolicitudInscripcionPersistence {
    @Override
    public void doSave(Integer solicitud, CursoId id, Integer motivo, String otro) {
        String hql = "insert into Solicitud_Inscripcion (soli_sol,soli_asign ,soli_elect,soli_coord,soli_secc,soli_agno,soli_sem,soli_comp,soli_motivo,soli_otro_glosa,soli_estado) VALUES "
                + "(:sol,:asign,:elect,:coord,:secc,:agno,:sem,'T',:motivo,:otro,'G')";
        Query query = getSession().createSQLQuery(hql);

        query.setParameter("sol", solicitud, StandardBasicTypes.INTEGER);
        query.setParameter("asign", id.getCurAsign(), StandardBasicTypes.INTEGER);
        query.setParameter("elect", id.getCurElect(), StandardBasicTypes.STRING);
        query.setParameter("coord", id.getCurCoord(), StandardBasicTypes.STRING);
        query.setParameter("secc", id.getCurSecc(), StandardBasicTypes.INTEGER);
        query.setParameter("agno", id.getCurAgno(), StandardBasicTypes.INTEGER);
        query.setParameter("sem", id.getCurSem(), StandardBasicTypes.INTEGER);
        query.setParameter("motivo", motivo, StandardBasicTypes.INTEGER);
        query.setParameter("otro", otro, StandardBasicTypes.STRING);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SolicitudInscripcion> getSolicitud(Integer folio)
    {
        Criteria criteria = getSession().createCriteria(SolicitudInscripcion.class);

        criteria.setFetchMode("curso", JOIN);
        criteria.setFetchMode("motivo", JOIN);
        criteria.add(eq("id.soliSol", folio));
        criteria.addOrder(desc("curso.id.curAsign"));
        criteria.addOrder(desc("curso.id.curElect"));
        criteria.addOrder(desc("curso.id.curCoord"));
        criteria.addOrder(desc("curso.id.curSecc"));

        return criteria.list();
    }

}
