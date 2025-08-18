/*
 * @(#)ElectivoPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.ElectivoRepository;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.Electivo;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import static org.hibernate.criterion.Restrictions.eq;
import org.hibernate.sql.JoinType;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class ElectivoPersistenceImpl extends CrudAbstractDAO<Electivo, Long> implements ElectivoRepository {

    /**
     * Method description
     *
     * @param asign
     * @param elect
     * @param agno
     * @param sem
     * @return
     */
    @Override
    public Electivo find(Integer asign, String elect, Integer agno, Integer sem) {
        Criteria criteria = getSession().createCriteria(Electivo.class);

        criteria.add(eq("id.eleAsign", asign));
        criteria.add(eq("id.eleElect", elect));
        criteria.add(eq("id.eleAgno", agno));
        criteria.add(eq("id.eleSem", sem));

        return (Electivo) criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Electivo> find(Integer tcarrera, Integer especialidad, Integer agno, Integer sem, Integer rut, String perfil) {

        // Creamos el Criteria para la clase Electivo
        Criteria criteria = getSession().createCriteria(Electivo.class);

        // Alias para las relaciones
        criteria.createAlias("area", "a", JoinType.LEFT_OUTER_JOIN);

        // Restricciones para los valores de agno y sem
        criteria.add(Restrictions.eq("id.eleAgno", agno));
        criteria.add(Restrictions.eq("id.eleSem", sem));

        // Subconsulta con parámetros para evitar inyecciones SQL
        String sql1 = "perfil_intranet_pkg.curso_propio(ele_asign, ?, ?) = 1";
        criteria.add(Restrictions.sqlRestriction(sql1, new Object[]{perfil, rut}, new Type[]{StandardBasicTypes.STRING, StandardBasicTypes.INTEGER}));

        // Subconsulta EXISTS con parámetros para evitar inyecciones SQL
        String sql2 = "exists (select * from malla, carrera where ma_asign = ele_asign and ma_cod_car = car_cod and car_ctip = ? and car_cesp = ?)";
        criteria.add(Restrictions.sqlRestriction(sql2, new Object[]{tcarrera, especialidad}, new Type[]{StandardBasicTypes.INTEGER, StandardBasicTypes.INTEGER}));

        // Ordenación de los resultados
        criteria.addOrder(Order.asc("id.eleAsign"));
        criteria.addOrder(Order.asc("id.eleElect"));

        // Ejecutamos la consulta y devolvemos los resultados
        return criteria.list();
    }

    @Override
    public void add(Integer asign, String elect, String electivo, Integer minor, Integer area, String tipo, Integer agno, Integer sem) {
        // SQL con parámetros
        String sql = "INSERT INTO electivo(ele_asign, ele_elect, ele_agno, ele_sem, ele_nom, ele_asign_minor, ele_area, ele_tipo) VALUES (:asign, :elect, :agno, :sem, :electivo, :minor, :area, :tipo)";

        // Crear insert con Hibernate
        Query query = getSession().createSQLQuery(sql);

        // Asignar los valores de manera segura usando setParameter
        query.setParameter("asign", asign, StandardBasicTypes.INTEGER);
        query.setParameter("elect", elect, StandardBasicTypes.STRING);
        query.setParameter("agno", agno, StandardBasicTypes.INTEGER);
        query.setParameter("sem", sem, StandardBasicTypes.INTEGER);
        query.setParameter("electivo", electivo, StandardBasicTypes.STRING);
        query.setParameter("tipo", tipo, StandardBasicTypes.STRING);
        // Si minor es 0 o null, pasamos null, de lo contrario pasamos el valor
        query.setParameter("minor", minor == null || minor == 0 ? null : minor, StandardBasicTypes.INTEGER);
        query.setParameter("area", area, StandardBasicTypes.INTEGER);

        // Ejecutar la inserción
        query.executeUpdate();
    }

    @Override
    public void modify(Integer asign, String elect, String electivo, Integer minor, Integer area, String tipo, Integer agno, Integer sem) {
        try{
        String sql = "UPDATE electivo SET "
                + "ele_nom = :electivo, "
                + "ele_asign_minor = :minor, "
                + "ele_area = :area, "
                + "ele_tipo = :tipo "
                + "WHERE ele_asign = :asign "
                + "AND ele_elect = :elect "
                + "AND ele_agno = :agno "
                + "AND ele_sem = :sem";

        
            System.out.println("KKKKele_nom = "+electivo
                + "ele_asign_minor = "+minor
                + "ele_area = "+area
                + "ele_tipo = "+tipo
                + "WHERE ele_asign = "+asign
                + "AND ele_elect = "+elect
                + "AND ele_agno = "+agno
                + "AND ele_sem = "+sem);
        // Crear el update con Hibernate
        SQLQuery query = getSession().createSQLQuery(sql);

        // Asignar los valores de manera segura usando setParameter
        query.setParameter("electivo", electivo, StandardBasicTypes.STRING);
        query.setParameter("minor", minor == null || minor <= 0 ? null : minor, StandardBasicTypes.INTEGER); // Si minor es null o <= 0, se pasa como null
        query.setParameter("area", area, StandardBasicTypes.INTEGER);
        query.setParameter("tipo", tipo, StandardBasicTypes.STRING);
        query.setParameter("asign", asign, StandardBasicTypes.INTEGER);
        query.setParameter("elect", elect, StandardBasicTypes.STRING);
        query.setParameter("agno", agno, StandardBasicTypes.INTEGER);
        query.setParameter("sem", sem, StandardBasicTypes.INTEGER);

        // Ejecutar la actualización
        query.executeUpdate();
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void delete(Integer asign, String elect, Integer agno, Integer sem) {
        // Delete SQL con parámetros
        String sql = "DELETE FROM electivo WHERE "
                + "ele_asign = :asign AND "
                + "ele_elect = :elect AND "
                + "ele_agno = :agno AND "
                + "ele_sem = :sem";

        // Crear delete con Hibernate
        SQLQuery query = getSession().createSQLQuery(sql);

        // Asignar los valores de manera segura usando setParameter
        query.setParameter("asign", asign, StandardBasicTypes.INTEGER);
        query.setParameter("elect", elect, StandardBasicTypes.STRING);
        query.setParameter("agno", agno, StandardBasicTypes.INTEGER);
        query.setParameter("sem", sem, StandardBasicTypes.INTEGER);

        // Ejecutar la eliminación
        query.executeUpdate();
    }
}