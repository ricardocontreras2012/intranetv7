/*
 * @(#)AsignaturaPersistenceImpl.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.AsignaturaPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.AluCar;
import domain.model.Asignatura;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Restrictions.eq;
import org.hibernate.type.StandardBasicTypes;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class AsignaturaPersistenceImpl extends CrudAbstractDAO<Asignatura, Long> implements AsignaturaPersistence {
    /**
     * Method description
     *
     * @param tipo
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Asignatura> find(String tipo) {
        Criteria criteria = getSession().createCriteria(Asignatura.class);

        criteria.add(eq("asiTipo", tipo));
        criteria.addOrder(asc("asiCod"));
        criteria.addOrder(asc("asiNom"));

        return criteria.list();
    }   

    /**
     *
     * @param aluCar
     * @param tipoSolicitud
     * @return
     */
    @Override
    public Asignatura find(AluCar aluCar, Integer tipoSolicitud) {        
        String sql = "SELECT asignatura.* FROM asignatura, malla, tsolicitud WHERE"
                + " tsol_codigo=:tipo"
                + " AND instr(tsol_descrip,asi_nom) > 0"
                + " AND ma_asign = asi_cod  AND ma_cod_car =:carrera"
                + " AND ma_cod_men=:mencion AND ma_cod_plan=:plan";

        SQLQuery query = getSession().createSQLQuery(sql).addEntity("asignatura", Asignatura.class);
        query.setParameter("tipo", tipoSolicitud, StandardBasicTypes.INTEGER);
        query.setParameter("carrera",  aluCar.getId().getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter("mencion", aluCar.getAcaCodMen(), StandardBasicTypes.INTEGER);
        query.setParameter("plan", aluCar.getAcaCodPlan(), StandardBasicTypes.INTEGER);

        return (Asignatura) query.uniqueResult();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Asignatura> findFI(Integer rut, String type) {               
        String sql = "SELECT * FROM asignatura WHERE exists (select * from labor_realizada, labor, area_asignatura where "+
                "lrea_labor = lab_cod and lrea_unidad = are_unidad and asi_area = are_cod and "+
                "lrea_rut = :rut and lab_user=:type) ORDER BY asi_nom";
        
        SQLQuery query = getSession().createSQLQuery(sql).addEntity("asignatura", Asignatura.class);
        // Establecer los parámetros de la consulta de manera segura
        query.setParameter("rut", rut, StandardBasicTypes.INTEGER); 
        query.setParameter("type", type, StandardBasicTypes.STRING);
        
        return query.list();
    }
}
