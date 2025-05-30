package infrastructure.persistence;

import domain.repository.ReincorporacionPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.Reincorporacion;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import static org.hibernate.criterion.Restrictions.eq;
import org.hibernate.type.StandardBasicTypes;

/**
 *
 * @author Ricardo Contreras S.
 */
public class ReincorporacionPersistenceImpl extends CrudAbstractDAO<Reincorporacion, Long>
        implements ReincorporacionPersistence {

    /**
     *
     * @param agno
     * @param sem
     * @param nomina
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Reincorporacion> find(Integer agno, Integer sem, Integer nomina, String tipo) {
        Criteria criteria = getSession().createCriteria(Reincorporacion.class);
        criteria.createAlias("aluCar.alumno", "alumno");
        criteria.createAlias("aluCar", "aluCar");
        criteria.add(eq("id.reiAgno", agno));
        criteria.add(eq("id.reiSem", sem));
        criteria.add(eq("reiEstado", 0));
        criteria.add(eq("reiNomina", nomina));
        
        if (tipo != null && !tipo.trim().isEmpty()) {
            Integer[] tiposArray = Arrays.stream(tipo.split(","))
                                     .map(Integer::valueOf)
                                     .toArray(Integer[]::new);
            criteria.add(Restrictions.in("reiTipo", tiposArray));
            return criteria.list();
        }
        return Collections.emptyList();
    }

    /**
     *
     * @param solicitud
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public Reincorporacion find(Integer solicitud) {
        Criteria criteria = getSession().createCriteria(Reincorporacion.class);
        criteria.createAlias("aluCar.alumno", "alumno");
        criteria.createAlias("aluCar", "aluCar");
        criteria.add(eq("reiSolicitud", solicitud));

        return (Reincorporacion) criteria.uniqueResult();
    }

    /**
     *
     * @param nomina
     */
    @Override
    public void marcarProcesado(Integer nomina) {
        String hql = "update Reincorporacion set rei_estado=1 where rei_nomina =:nomina";
        Query query = getSession().createQuery(hql);
        query.setParameter("nomina", nomina, StandardBasicTypes.INTEGER);
        query.executeUpdate();
    }

    /**
     *
     * @param agno
     * @param sem
     * @param nomina
     */
    @Override
    public void reincorporaEliminados(Integer agno, Integer sem, Integer nomina) {
        Query query = getSession().createSQLQuery("{ call situacion_pkg.reinc_eliminados(?,?,?) }");
        query.setParameter(0, agno, StandardBasicTypes.INTEGER);
        query.setParameter(1, sem, StandardBasicTypes.INTEGER);
        query.setParameter(2, nomina, StandardBasicTypes.INTEGER);

        query.executeUpdate();
    }

    /**
     *
     * @param agno
     * @param sem
     */
    @Override
    public void reincorporaEliminadosFull(Integer agno, Integer sem, Integer nomina) {
        Query query = getSession().createSQLQuery("{ call situacion_pkg.reinc_eliminados_full(?,?,?) }");
        query.setParameter(0, agno, StandardBasicTypes.INTEGER);
        query.setParameter(1, sem, StandardBasicTypes.INTEGER);
        query.setParameter(2, nomina, StandardBasicTypes.INTEGER);

        query.executeUpdate();
    }

    /**
     *
     * @param agno
     * @param sem
     * @param nomina
     */
    @Override
    public void reincorporaRetiros(Integer agno, Integer sem, Integer nomina) {
        Query query = getSession().createSQLQuery("{ call situacion_pkg.reinc_retiro(?,?,?) }");
        query.setParameter(0, agno, StandardBasicTypes.INTEGER);
        query.setParameter(1, sem, StandardBasicTypes.INTEGER);
        query.setParameter(2, nomina, StandardBasicTypes.INTEGER);

        query.executeUpdate();
    }

    /**
     *
     * @param agno
     * @param sem
     * @param nomina
     */
    @Override
    public void reincorporaProrrogas(Integer agno, Integer sem, Integer nomina) {
        Query query = getSession().createSQLQuery("{ call situacion_pkg.reinc_prorroga(?,?,?) }");
        query.setParameter(0, agno, StandardBasicTypes.INTEGER);
        query.setParameter(1, sem, StandardBasicTypes.INTEGER);
        query.setParameter(2, nomina, StandardBasicTypes.INTEGER);

        query.executeUpdate();
    }
}
