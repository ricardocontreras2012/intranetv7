/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.persistence;

import domain.model.EstadoDocExp;
import domain.model.ExpedienteLogroId;
import domain.repository.EstadoDocExpRepository;
import infrastructure.persistence.dao.CrudAbstractDAO;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.FetchMode.JOIN;
import org.hibernate.Query;
import static org.hibernate.criterion.Restrictions.eq;
import org.hibernate.type.StandardBasicTypes;

/**
 *
 * @author Ricardo
 */
public class EstadoDocExpPersistenceImpl extends CrudAbstractDAO<EstadoDocExp, Long>
        implements EstadoDocExpRepository {

    @SuppressWarnings("unchecked")
    @Override
    public List<EstadoDocExp> find(ExpedienteLogroId id) {
        Criteria criteria = getSession().createCriteria(EstadoDocExp.class);

        criteria.setFetchMode("expedienteLogro", JOIN);
        criteria.add(eq("expedienteLogro.id", id));
        criteria.setFetchMode("expedienteLogro.planLogro", JOIN);
        criteria.setFetchMode("expedienteLogro.planLogro.logro", JOIN);
        criteria.setFetchMode("tDocExpediente", JOIN);
        return criteria.list();
    }

    public EstadoDocExp find(ExpedienteLogroId id, String tdoc) {
        Criteria criteria = getSession().createCriteria(EstadoDocExp.class);

        criteria.setFetchMode("expedienteLogro", JOIN);
        criteria.setFetchMode("expedienteLogro.planLogro", JOIN);
        criteria.setFetchMode("expedienteLogro.planLogro.logro", JOIN);
        criteria.createAlias("tDocExpediente", "tdoc"); // crea alias para acceder a tdeCod
        criteria.add(eq("expedienteLogro.id", id));
        criteria.add(eq("tdoc.tdeCod", tdoc));
        criteria.setMaxResults(1);

        return (EstadoDocExp) criteria.uniqueResult();
    }

    @Override
    public void updateFile(Integer rut, Integer codCar, Integer agnoIng, Integer semIng, Integer correlLogro, Integer tdoc, String fileName) {
        String hql = "update EstadoDocExp set ede_file = :fileName, ede_estado = 1 , ede_observacion=null "
                + "where ede_rut = :rut and ede_cod_car = :codCar "
                + "and ede_agno_ing = :agnoIng and ede_sem_ing = :semIng "
                + "and ede_correl_logro = :correlLogro and ede_tdoc = :tdoc";

        Query query = getSession().createQuery(hql);

        query.setParameter("rut", rut, StandardBasicTypes.INTEGER);
        query.setParameter("codCar", codCar, StandardBasicTypes.INTEGER);
        query.setParameter("agnoIng", agnoIng, StandardBasicTypes.INTEGER);
        query.setParameter("semIng", semIng, StandardBasicTypes.INTEGER);
        query.setParameter("correlLogro", correlLogro, StandardBasicTypes.INTEGER);
        query.setParameter("tdoc", tdoc, StandardBasicTypes.INTEGER);
        query.setParameter("fileName", fileName, StandardBasicTypes.STRING);
        query.executeUpdate();
    }
}
