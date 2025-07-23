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
        System.out.println("UPDATE ede");
        String hql = "update EstadoDocExp set ede_file = :fileName, ede_estado = 1 , ede_observacion=null "
                + "where ede_rut = :rut and ede_cod_car = :codCar "
                + "and ede_agno_ing = :agnoIng and ede_sem_ing = :semIng "
                + "and ede_correl_logro = :correlLogro and ede_tdoc = :tdoc";
        //Query query = getSession().createSQLQuery(hql);
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
    /*public void updateFile(Integer rut, Integer cod_car, Integer agno_ing, Integer sem_ing,
            Integer correl_logro, Integer tdoc, String file) {
        System.out.println("UPDATE ede");

        // 1. Crear una clave compuesta si tu entidad la usa como @IdClass o @EmbeddedId
        EstadoDocExpId id = new EstadoDocExpId(rut, cod_car, agno_ing, sem_ing, correl_logro, tdoc);

        // 2. Buscar la entidad
        EstadoDocExp entity = (EstadoDocExp) getSession().get(EstadoDocExp.class, id);

        // 3. Verificar si existe y actualizar
        if (entity != null) {
            entity.setEdeFile(file);
            getSession().update(entity);  // Aunque no es estrictamente necesario si la entidad está en sesión
        } else {
            System.out.println("Entidad no encontrada para los parámetros dados");
        }
    }*/
}
