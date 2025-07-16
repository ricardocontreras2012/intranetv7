/*
 * @(#)SolicitudPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.SolicitudPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.AluCar;
import domain.model.Solicitud;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.FetchMode.JOIN;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Order.desc;
import org.hibernate.criterion.Restrictions;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.ge;
import static org.hibernate.criterion.Restrictions.le;
import static org.hibernate.criterion.Restrictions.sqlRestriction;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class SolicitudPersistenceImpl extends CrudAbstractDAO<Solicitud, Long> implements SolicitudPersistence {

    /**
     * Method description
     *
     * @param aluCar
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Solicitud> find(AluCar aluCar) {
        Criteria criteria = getSession().createCriteria(Solicitud.class);

        criteria.setFetchMode("tsolicitud", JOIN);
        criteria.setFetchMode("estadoSolicitud", JOIN);
        criteria.add(eq("aluCar", aluCar));
        criteria.addOrder(desc("solFolio"));

        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Solicitud> findPracticasxEstado(Integer rut, Integer autoridad, Integer estado) {
        Criteria criteria = getSession().createCriteria(Solicitud.class);

        // Configuración de los 'fetch modes' para las relaciones
        criteria.setFetchMode("aluCar.alumno", JOIN);
        criteria.setFetchMode("aluCar", JOIN);
        criteria.setFetchMode("tsolicitud", JOIN);
        criteria.createAlias("estadoSolicitud", "estadoSolicitud");

        // Agregar restricción para el estado de la solicitud
        criteria.add(Restrictions.eq("estadoSolicitud.esolCod", estado));

        // Usar sqlRestriction con parámetros para evitar la concatenación directa
        if (rut != null && autoridad != null) {
            String sqlFilter = "exists (select 1 from labor_realizada, unidad u1, unidad u2, mencion "
                    + "where u1.uni_acad_mayor = u2.uni_cod and "
                    + "u1.uni_cod = lrea_unidad and men_unidad = u2.uni_cod and "
                    + "men_cod_car = aca_cod_car and men_cod_men= aca_cod_men and "
                    + "lrea_labor=:autoridad and lrea_rut =:rut)";

            criteria.add(Restrictions.sqlRestriction(sqlFilter, new Object[]{autoridad, rut}, new Type[]{IntegerType.INSTANCE, IntegerType.INSTANCE}));

            // Segunda subconsulta para verificar si la práctica existe
            criteria.add(Restrictions.sqlRestriction(
                    "exists (select 1 from practica p where p.pra_solicitud = sol_folio and sol_estado = ?)",
                    new Object[]{estado},
                    new Type[]{IntegerType.INSTANCE}
            ));

            // Ordenar según el estado
            if (estado == 10) {
                criteria.addOrder(Order.asc("solFolio"));
            } else {
                criteria.addOrder(Order.desc("solFolio"));
            }

            // Ejecutar la consulta y devolver los resultados
            return criteria.list();
        }
        return Collections.emptyList();
    }

    /**
     *
     * @param folio
     * @param resolucion
     * @param respuesta
     */
    @Override
    public void saveResolucion(Integer folio, String resolucion, String respuesta, Integer estado) {
        String hql = "update Solicitud set sol_estado= :estado, sol_resolucion=:resolucion, sol_respuesta=:respuesta where sol_folio = :folio";

        Query query = getSession().createQuery(hql);
        query.setParameter("folio", folio, StandardBasicTypes.INTEGER);
        query.setParameter("resolucion", resolucion, StandardBasicTypes.STRING);
        query.setParameter("respuesta", respuesta, StandardBasicTypes.STRING);
        query.setParameter("estado", estado, StandardBasicTypes.INTEGER);
        query.executeUpdate();
    }

    /**
     * Method description
     *
     * @param rut
     * @param fechaInicio
     * @param fechaFinal
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Solicitud> findSolicitudBetweenDates(Integer rut, String user, Integer estado, Date fechaInicio, Date fechaFinal) {
        Criteria criteria = getSession().createCriteria(Solicitud.class);

        criteria.setFetchMode("aluCar.aaingreso.aaiViaIng", JOIN);
        criteria.setFetchMode("aluCar.aaingreso", JOIN);
        criteria.setFetchMode("aluCar.alumno", JOIN);
        criteria.setFetchMode("aluCar", JOIN);
        criteria.createAlias("tsolicitud", "tsolicitud");
        criteria.createAlias("estadoSolicitud", "estadoSolicitud");
        criteria.add(eq("estadoSolicitud.esolCod", estado));

        String sqlFilter;

        switch (user) {
            case "JC":
                sqlFilter = "exists (select * from labor_realizada, mencion "
                        + "where men_unidad = lrea_unidad and "
                        + "men_cod_car = aca_cod_car and men_cod_men= aca_cod_men and "
                        + "lrea_labor =60 and lrea_rut =" + rut + ")";

                criteria.add(sqlRestriction(sqlFilter));
                criteria.add(eq("tsolicitud.tsolTipo", "INS"));
                break;
            case "VDD":
                criteria.add(Restrictions.in("tsolicitud.tsolCodigo", new Integer[]{11, 25, 30, 35, 40}));
                break;
            case "SF":
                criteria.add(Restrictions.in("tsolicitud.tsolCodigo", new Integer[]{40}));
                break;
            case "SM":
                sqlFilter = "exists (select * from labor_realizada, mencion "
                        + "where men_unidad = lrea_unidad and "
                        + "men_cod_car = aca_cod_car and men_cod_men= aca_cod_men and "
                        + "lrea_labor =410 and lrea_rut =" + rut + ") and "
                        + "mencion_propia (aca_cod_car, aca_cod_men,'" + user + "'," + rut + ")=1";

                criteria.add(sqlRestriction(sqlFilter));
                criteria.add(eq("tsolicitud.tsolTipo", "JUS"));
                break;
            default:
        }

        criteria.add(ge("solFecha", fechaInicio));
        criteria.add(le("solFecha", fechaFinal));

        if (estado == 10) {
            criteria.addOrder(asc("solFolio"));
        } else {
            criteria.addOrder(desc("solFolio"));
        }

        return criteria.list();
    }

    @Override
    public void modify(Integer folio, String solicita) {
        String hql = "update Solicitud set sol_solicita = :solicita where sol_folio = :folio";
        Query query = getSession().createQuery(hql);

        query.setParameter("folio", folio, StandardBasicTypes.INTEGER);
        query.setParameter("solicita", solicita, StandardBasicTypes.STRING);

        query.executeUpdate();
    }

    /**
     *
     * @param folio
     * @return
     */
    @Override
    public Solicitud find(Integer folio) {
        Criteria criteria = getSession().createCriteria(Solicitud.class);

        criteria.setFetchMode("aluCar.alumno", JOIN);
        criteria.setFetchMode("aluCar", JOIN);
        criteria.createAlias("tsolicitud", "tsolicitud");
        criteria.createAlias("estadoSolicitud", "estadoSolicitud");
        criteria.add(eq("solFolio", folio));

        return (Solicitud) criteria.uniqueResult();

    }
}