/*
 * @(#)ModuloHorarioPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.ModuloHorarioRepository;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.ModuloHorario;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.sqlRestriction;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class ModuloHorarioPersistenceImpl extends CrudAbstractDAO<ModuloHorario, Long>
        implements ModuloHorarioRepository {

    @SuppressWarnings("unchecked")
    @Override
    public List<ModuloHorario> findDocencia(Integer agno, Integer sem) {
        Criteria criteria = getSession().createCriteria(ModuloHorario.class);
        criteria.add(sqlRestriction("mod_version=get_version_horario(" + agno + "," + sem + ")"));
        criteria.add(eq("modTipo", "D"));
        criteria.addOrder(asc("id.modCod"));
        return criteria.list();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<ModuloHorario> findAll(String inicio, String termino) {
        Criteria criteria = getSession().createCriteria(ModuloHorario.class);
        criteria.add(sqlRestriction("mod_version=get_version_horario_x_fecha('" + inicio + "','" + termino + "')"));
        criteria.addOrder(asc("modOrder"));
        return criteria.list();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public ModuloHorario find(Integer agno, Integer sem, Integer modulo) {
        Criteria criteria = getSession().createCriteria(ModuloHorario.class);
        criteria.add(sqlRestriction("mod_version=get_version_horario(" + agno + "," + sem + ")"));
        criteria.add(eq("id.modCod", modulo));
        return (ModuloHorario)criteria.uniqueResult();
    }
}
