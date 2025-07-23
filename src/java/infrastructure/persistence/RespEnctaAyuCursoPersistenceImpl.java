/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.persistence;

import domain.repository.RespEnctaAyuCursoRepository;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.CursoId;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Restrictions.eq;
import domain.model.RespEnctaAyuCursoView;

/**
 *
 * @author Ricardo
 */
public class RespEnctaAyuCursoPersistenceImpl extends CrudAbstractDAO<RespEnctaAyuCursoView, Long>
        implements RespEnctaAyuCursoRepository {

    /**
     * Method description
     *
     * @param cursoId
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<RespEnctaAyuCursoView> find(CursoId cursoId) {
        getSession().clear();    // Borra la cache de session (sino repite valores anteriores)

        Criteria criteria = getSession().createCriteria(RespEnctaAyuCursoView.class);

        criteria.add(eq("reaAsign", cursoId.getCurAsign()));
        criteria.add(eq("reaElect", cursoId.getCurElect()));
        criteria.add(eq("reaCoord", cursoId.getCurCoord()));
        criteria.add(eq("reaSecc", cursoId.getCurSecc()));
        criteria.add(eq("reaAgno", cursoId.getCurAgno()));
        criteria.add(eq("reaSem", cursoId.getCurSem()));
        criteria.addOrder(asc("peaPreg"));

        return criteria.list();
    }
}
