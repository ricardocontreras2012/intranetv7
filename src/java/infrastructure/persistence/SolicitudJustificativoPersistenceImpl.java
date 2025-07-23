/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.persistence;

import domain.repository.SolicitudJustificativoRepository;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.CursoId;
import domain.model.SolicitudJustificativo;
import java.util.Arrays;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import static org.hibernate.criterion.Order.desc;
import org.hibernate.criterion.Restrictions;
import static org.hibernate.criterion.Restrictions.eq;
import org.hibernate.type.StandardBasicTypes;

/**
 *
 * @author Ricardo
 */
public final class SolicitudJustificativoPersistenceImpl extends CrudAbstractDAO<SolicitudJustificativo, Long> implements SolicitudJustificativoRepository {

    @Override
    public void doSave(Integer solicitud, CursoId id) {

        String hql = "insert into Solicitud_Justificativo (solj_sol,solj_asign ,solj_elect,solj_coord,solj_secc,solj_agno,solj_sem,solj_comp) VALUES "
                + "(:sol,:asign,:elect,:coord,:secc,:agno,:sem,'T')";
        Query query = getSession().createSQLQuery(hql);

        query.setParameter("sol", solicitud, StandardBasicTypes.INTEGER);
        query.setParameter("asign", id.getCurAsign(), StandardBasicTypes.INTEGER);
        query.setParameter("elect", id.getCurElect(), StandardBasicTypes.STRING);
        query.setParameter("coord", id.getCurCoord(), StandardBasicTypes.STRING);
        query.setParameter("secc", id.getCurSecc(), StandardBasicTypes.INTEGER);
        query.setParameter("agno", id.getCurAgno(), StandardBasicTypes.INTEGER);
        query.setParameter("sem", id.getCurSem(), StandardBasicTypes.INTEGER);
        query.executeUpdate();        
    }

    @Override
    public void doUpdate(Integer solicitud, CursoId id, String respuesta, String estado) {

        String hql = "update Solicitud_Justificativo "
                + "set solj_respuesta = :respuesta, solj_estado = :estado "
                + "where solj_sol = :sol "
                + "and solj_asign = :asign "
                + "and solj_elect = :elect "
                + "and solj_coord = :coord "
                + "and solj_secc = :secc "
                + "and solj_agno = :agno "
                + "and solj_sem = :sem";

        Query query = getSession().createSQLQuery(hql);

        query.setParameter("respuesta", respuesta, StandardBasicTypes.STRING);
        query.setParameter("estado", estado, StandardBasicTypes.STRING);
        query.setParameter("sol", solicitud, StandardBasicTypes.INTEGER);
        query.setParameter("asign", id.getCurAsign(), StandardBasicTypes.INTEGER);
        query.setParameter("elect", id.getCurElect(), StandardBasicTypes.STRING);
        query.setParameter("coord", id.getCurCoord(), StandardBasicTypes.STRING);
        query.setParameter("secc", id.getCurSecc(), StandardBasicTypes.INTEGER);
        query.setParameter("agno", id.getCurAgno(), StandardBasicTypes.INTEGER);
        query.setParameter("sem", id.getCurSem(), StandardBasicTypes.INTEGER);

        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SolicitudJustificativo> getSolicitud(Integer folio) {
        Criteria criteria = getSession().createCriteria(SolicitudJustificativo.class);

        criteria.add(eq("id.soljSol", folio));
        criteria.addOrder(desc("curso.id.curAsign"));
        criteria.addOrder(desc("curso.id.curElect"));
        criteria.addOrder(desc("curso.id.curCoord"));
        criteria.addOrder(desc("curso.id.curSecc"));

        return criteria.list();
    }

    /**
     *
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<SolicitudJustificativo> find(CursoId id) {
        Criteria criteria = getSession().createCriteria(SolicitudJustificativo.class);

        criteria.createAlias("aluCar.alumno", "alumno");
        criteria.createAlias("solicitud.tsolicitud", "tsolicitud");
        criteria.createAlias("solicitud.aluCar", "aluCar");
        criteria.createAlias("solicitud", "solicitud");
        criteria.add(eq("curso.id", id));
        criteria.add(eq("tsolicitud.tsolTipo", "JUS"));
        criteria.add(Restrictions.in("solicitud.solResolucion", Arrays.asList("A", "AP")));

        return criteria.list();
    }
}
