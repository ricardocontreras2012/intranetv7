/*
 * @(#)SalaPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.SalaPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.Sala;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import infrastructure.support.ReservaSalaSupport;
import java.util.stream.Collectors;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class SalaPersistenceImpl extends CrudAbstractDAO<Sala, Long> implements SalaPersistence {

    /**
     * Method description
     *
     * @param rut
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Sala> findPropias(Integer rut) {
        // Usamos parámetros para evitar inyecciones SQL
        String sqlFilter = "ssa_unidad IN (SELECT facultad_pkg.get_unidad_x_unidad(lrea_unidad) "
                + "FROM labor_realizada WHERE lrea_rut = :rut)";

        // Creamos la consulta Criteria
        Criteria criteria = getSession().createCriteria(Sala.class);

        // Alias para la relación
        criteria.createAlias("unidad", "unidad");

        // Usamos sqlRestriction con el parámetro
        criteria.add(Restrictions.sqlRestriction(sqlFilter, new Object[]{rut},
                new Type[]{IntegerType.INSTANCE}));

        // Ordenamos los resultados por salaNum
        criteria.addOrder(Order.asc("salaNum"));

        // Ejecutamos la consulta y retornamos los resultados
        return criteria.list();
    }

    /**
     *
     * @param sala
     * @param inicio
     * @param termino
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ReservaSalaSupport> getHorario(Sala sala, String inicio, String termino) {
        Query query = getSession().getNamedQuery("GetHorarioSalaFunction");

        query.setParameter(0, sala.getSalaNum(), StandardBasicTypes.STRING);
        query.setParameter(1, inicio, StandardBasicTypes.STRING);
        query.setParameter(2, termino, StandardBasicTypes.STRING);
        List<Object[]> results = query.list();

        return results.stream()
                .map(obj -> {
                    ReservaSalaSupport reserva = new ReservaSalaSupport();
                    reserva.setSala((String) obj[0]);
                    reserva.setDia((String) obj[1]);
                    reserva.setModulo(((Number) obj[2]).intValue());
                    reserva.setFecha((Date) obj[3]);

                    String uso = (String) obj[4];
                    if (uso != null) {
                        if (uso.startsWith("C:")) {
                            reserva.setTipo("C");
                            reserva.setColor(uso.substring(2, 8));
                            reserva.setCurso(uso.substring(9));
                        } else {
                            reserva.setTipo("R");
                            reserva.setReserva(Integer.valueOf(uso.substring(2)));
                        }
                    }
                    return reserva;
                })
                .collect(Collectors.toList());
    }

}
