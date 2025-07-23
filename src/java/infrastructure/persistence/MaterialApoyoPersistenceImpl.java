/*
 * @(#)MaterialApoyoPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.MaterialApoyoRepository;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.CursoId;
import domain.model.MaterialApoyo;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.sqlRestriction;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class MaterialApoyoPersistenceImpl extends CrudAbstractDAO<MaterialApoyo, Long>
        implements MaterialApoyoRepository {

    /**
     * Method description
     *
     * @param id
     * @param rut
     * @param tipoUsuario
     * @param tipoMaterial
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<MaterialApoyo> find(CursoId id, Integer rut, String tipoUsuario, String tipoMaterial) {
         
        Object[] params = new Object[2];

        params[0] = tipoUsuario;

        Type[] types = new Type[2];

        types[0] = new StringType();
        params[1] = rut;
        types[1] = new IntegerType();

        Criteria criteria = getSession().createCriteria(MaterialApoyo.class);

        criteria.createAlias("curso", "c");
        criteria.createAlias("tmaterial", "t");
        criteria.add(eq("c.id", id));
        criteria.add(eq("matPerfil", tipoMaterial));
        criteria.addOrder(asc("t.tmaDes"));
        criteria.add(sqlRestriction(
                        "decode(mat_perfil,'AL', decode((?),'AL', decode(mat_rut_autor, (?), 1,0), 1 ),1) = 1", params, types));

        return criteria.list();     
    }

    /**
     * Method description
     *
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<MaterialApoyo> findOtros(CursoId id) {
        Criteria criteria = getSession().createCriteria(MaterialApoyo.class);

        criteria.createAlias("curso", "c");
        criteria.createAlias("tmaterial", "t");
        criteria.add(eq("c.id", id));
        criteria.add(eq("matPerfil", "PR"));
        criteria.addOrder(asc("t.tmaDes"));

        return criteria.list();
    }
}
