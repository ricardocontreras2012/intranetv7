/*
 * @(#)MencionPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.MencionPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.Mencion;
import java.util.Collections;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import static org.hibernate.criterion.Restrictions.eq;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

/**
 * Class description
 *
 * @author Ricardo Contreras S and Javier Frez V.
 * @version 7, 24/05/2012
 */
public final class MencionPersistenceImpl extends CrudAbstractDAO<Mencion, Long> implements MencionPersistence {

    /**
     * Method description
     *
     * @param menCodCar
     * @param menCodMen
     * @return
     */
    @Override
    public Mencion find(Integer menCodCar, Integer menCodMen) {
        Criteria criteria = getSession().createCriteria(Mencion.class);

        criteria.add(eq("id.menCodCar", menCodCar));
        criteria.add(eq("id.menCodMen", menCodMen));

        return (Mencion) criteria.uniqueResult();

    }

    /**
     * Method description
     *
     * @param tipo
     * @param rut
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Mencion> find(String tipo, Integer rut) {
        if (tipo != null && rut != null) {
            Criteria criteria = getSession().createCriteria(Mencion.class);
            // Crear alias para las relaciones
            criteria.createAlias("carrera", "carrera");
            criteria.createAlias("carrera.tcarrera", "tcarrera");
            criteria.createAlias("carrera.especialidad", "especialidad");

            // Usar Restrictions para la condición sql
            criteria.add(Restrictions.sqlRestriction(
                    "mencion_propia(men_cod_car, men_cod_men, ?, ?) = 1",
                    new Object[]{tipo, rut},
                    new Type[]{StandardBasicTypes.STRING, StandardBasicTypes.INTEGER}
            ));

            // Añadir restricciones adicionales
            criteria.add(Restrictions.ge("id.menCodCar", 1000));

            // Ordenar los resultados
            criteria.addOrder(Order.asc("id.menCodCar"));
            criteria.addOrder(Order.asc("id.menCodMen"));

            return criteria.list();

        }
        return Collections.emptyList();
    }

    /**
     * Method description
     *
     * @param codCar
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Mencion> findByCarrera(Integer codCar) {
        Criteria criteria = getSession().createCriteria(Mencion.class);
        criteria.add(eq("id.menCodCar", codCar));

        return criteria.list();
    }

    /**
     * Method description
     *
     * @param tipo
     * @param rut
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Mencion> findCarrerasProgramas(String tipo, Integer rut, String flag) {
        if (tipo != null && rut != null) {
            Criteria criteria = getSession().createCriteria(Mencion.class);

            // Crear alias para las relaciones
            criteria.createAlias("carrera", "carrera");
            criteria.createAlias("carrera.tcarrera", "tcarrera");
            criteria.createAlias("carrera.especialidad", "especialidad");
            criteria.createAlias("carrera.tprograma", "tprograma");

            // Usar Restrictions.sqlRestriction con parámetros para evitar inyección SQL
            criteria.add(Restrictions.sqlRestriction(
                    "mencion_propia(men_cod_car, men_cod_men, ?, ?) = 1",
                    new Object[]{tipo, rut},
                    new Type[]{StandardBasicTypes.STRING, StandardBasicTypes.INTEGER}
            ));

            // Agregar la restricción para el flag
            String flagValue = "C".equals(flag) ? "S" : "N";
            criteria.add(Restrictions.eq("tprograma.tprFlagCarrera", flagValue));

            // Ordenar los resultados
            criteria.addOrder(Order.asc("id.menCodCar"));

            return criteria.list();
        }
        return Collections.emptyList();
    }

    /**
     *
     * @param carrera
     * @param mencion
     * @return
     */
    @Override
    public String getNombreCarreraFull(Integer carrera, Integer mencion) {

        Query query = getSession().getNamedQuery("NombreCarreraFunction");

        query.setParameter(0, carrera, StandardBasicTypes.INTEGER);
        query.setParameter(1, mencion, StandardBasicTypes.INTEGER);

        return (String) query.uniqueResult();
    }
}
