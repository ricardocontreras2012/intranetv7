/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.persistence;

import domain.repository.AreaPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.Area;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.type.StandardBasicTypes;

/**
 *
 * @author Usach
 */
public class AreaPersistenceImpl extends CrudAbstractDAO<Area, Long> implements AreaPersistence {

    @SuppressWarnings("unchecked")
    @Override
    public List<Area> getAreas(Integer rut, String type) {
        // Definir la consulta SQL de forma segura utilizando parámetros
        String sql = "SELECT * FROM area a WHERE EXISTS (SELECT * FROM labor_realizada lrea, labor lab, unidad u1, unidad u2 "
                + "WHERE lrea.lrea_unidad = u1.uni_cod AND "
                + "lrea.lrea_labor = lab.lab_cod AND lab.lab_user = :type "
                + "AND lrea.lrea_rut = :rut AND a.are_unidad = u2.uni_cod AND "
                + "u2.uni_acad_mayor = departamento_pkg.get_unidad_x_unidad(u1.uni_cod))";

        // Crear la consulta SQL
        SQLQuery query = getSession().createSQLQuery(sql);

        // Establecer los parámetros de la consulta de manera segura
        query.setParameter("rut", rut, StandardBasicTypes.INTEGER);  // Usamos :rut en la consulta SQL
        query.setParameter("type", type, StandardBasicTypes.STRING);  // Usamos :type en la consulta SQL

        // Definir el tipo de los resultados (en este caso, queremos mapearlo a la clase Area)
        query.addEntity(Area.class);

        // Ejecutar la consulta y obtener los resultados
        return query.list();
    }
}